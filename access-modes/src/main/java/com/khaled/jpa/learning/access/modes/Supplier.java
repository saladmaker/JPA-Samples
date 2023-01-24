package com.khaled.jpa.learning.access.modes;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
@Access(AccessType.FIELD)
public class Supplier {
    private static final String LOCAL_AREA = "+213";
    private static final String THE_STARTER_ZERO="0";
    @Id
    @GeneratedValue
    private long id;
    private String name;
    /*
       this is must be taken care of because we are mxing access 
       in order to instruct the provider that this field is not part of 
       the presisted state
    */
    @Transient
    private String phoneNumber; 

    public Supplier() {
    }

    public Supplier(String name, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.name=name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /*
    using the Property Access
    defining getter and setter for this property
    using a restiricting access modifier protected or package private
    add column name and length
    */
    @Access(AccessType.PROPERTY)
    @Column(name = "phone_number",length = 13)
    protected String getCannonicalPhoneNumber() {
        if (null != phoneNumber && phoneNumber.length()==10) {
            // adding +213 and removing the starter zero
            return LOCAL_AREA + phoneNumber.substring(1);
        }
        return phoneNumber;
    }
    protected void setCannonicalPhoneNumber(String phoneNumber){
        if (phoneNumber.startsWith(LOCAL_AREA)) {
            this.phoneNumber = phoneNumber.substring(3);
        }
        this.phoneNumber = THE_STARTER_ZERO.concat(phoneNumber);
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Supplier s) {
            return Objects.equals(id, s.getId());
        }
        return false;
    }
    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + '}';
    }
}
