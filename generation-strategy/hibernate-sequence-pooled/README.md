# Using an existing sequence with hibernate 
    let's suppose we and an existing database:
    
        - Department (id,...etc) table
        -a sequence `dep_seq`  that increment by 4
    we define a sequence generator and use it 
    ```java
    @SequenceGenerator(name = "dep_seq", sequenceName = "dep_seq" , allocationSize = 4)
    @GeneratedValue(strategy = SEQUENCE, generator = "dep_seq")
    @Id private Long id;
    ```


 
```sql
drop table if exists Department 
create table Department (id bigint not null, primary key (id))
drop sequence if exists dep_seq

create sequence dep_seq start with 6 increment by 4
insert into Department(id) values(1);
insert into Department(id) values(2);
```
the current value if the highest boundary    
6 - 4 = 2
    the next available values are:
        3
        4
        5
        6

- inserting 4 rows:
```sql
select next value for dep_seq

insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
```

- inserting 5 rows:
```sql
select next value for dep_seq
select next value for dep_seq
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
```
- inserting 10 rows:
```sql
select next value for dep_seq
select next value for dep_seq
select next value for dep_seq
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
insert into Department (id) values (?)
```
