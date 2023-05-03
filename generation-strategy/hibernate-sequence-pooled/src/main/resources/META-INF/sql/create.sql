drop table if exists Department 
create table Department (id bigint not null, primary key (id))
drop sequence if exists dep_seq
create sequence dep_seq start with 6 increment by 4
insert into Department(id) values(1);
insert into Department(id) values(2);
