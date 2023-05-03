package com.khaled.jpa.learning.hibernate.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.internal.util.IndexedConsumer;
import org.hibernate.metamodel.mapping.BasicValuedMapping;
import org.hibernate.metamodel.mapping.JdbcMapping;
import org.hibernate.metamodel.mapping.MappingType;
import org.hibernate.query.sqm.SqmExpressible;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;
import org.hibernate.type.internal.BasicTypeImpl;
import org.hibernate.usertype.UserType;

public class YearType implements UserType<Year>,  SqmExpressible<Year>, BasicValuedMapping {

    YearTypeDescriptor yearTypeDescriptor = YearTypeDescriptor.INSTANCE;
    
    SmallIntJdbcType jdbcType = SmallIntJdbcType.INSTANCE;

    JdbcMapping jdbcMapping = new BasicTypeImpl<Year>(yearTypeDescriptor, jdbcType);

    @Override
    public boolean equals(Year year1, Year year2) {
        return yearTypeDescriptor.areEquals(year1, year2);
    }

    @Override
    public int hashCode(Year value) {
        return yearTypeDescriptor.extractHashCode(value);
    }

    @Override
    public Year nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
            throws SQLException {
        return jdbcType
                .getExtractor(yearTypeDescriptor)
                .extract(rs, position, session);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Year value, int position, SharedSessionContractImplementor session)
            throws SQLException {
                jdbcType.getBinder(yearTypeDescriptor)
                .bind(st, value, position, session);
    }

    @Override
    public Year deepCopy(Year year) {
        return yearTypeDescriptor.getMutabilityPlan().deepCopy(year);
    }

    @Override
    public boolean isMutable() {
        return yearTypeDescriptor.getMutabilityPlan().isMutable();
    }

    @Override
    public Serializable disassemble(Year year) {
        return yearTypeDescriptor.getMutabilityPlan().disassemble(year, null);
    }

    @Override
    public Year assemble(Serializable cached, Object owner) {
        return yearTypeDescriptor.getMutabilityPlan().assemble(cached, null);
    }

    @Override
    public Year replace(Year detached, Year managed, Object owner){
            return deepCopy(detached);
    }

    @Override
    public int getSqlType() {
        return jdbcType.getJdbcTypeCode();
    }

    @Override
    public Class<Year> returnedClass() {
        return Year.class;
    }

    @Override
    public Class<Year> getBindableJavaType() {
        return Year.class;
    }
    @Override
    public YearTypeDescriptor getExpressibleJavaType(){
        return yearTypeDescriptor;
    }

    @Override
    public JdbcMapping getJdbcMapping() {
        return jdbcMapping;
    }
    
    @Override
    public MappingType getMappedType() {
        return jdbcMapping;
    }

    @Override
    public <X, Y> int forEachDisassembledJdbcValue(Object value, int offset, X x, Y y,
            JdbcValuesBiConsumer<X, Y> valueConsumer, SharedSessionContractImplementor session) {
                valueConsumer.consume(offset, x, y, session, jdbcMapping);
                return getJdbcTypeCount();
    }


    @Override
    public int forEachJdbcType(int offset, IndexedConsumer<JdbcMapping> action) {
        action.accept(offset, jdbcMapping);
        return getJdbcTypeCount();
    }
    
    
}
