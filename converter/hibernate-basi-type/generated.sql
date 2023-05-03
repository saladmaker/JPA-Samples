create table Product (inception smallint, id bigint not null,
 name varchar(255) not null unique, primary key (id))

select next value for Product_SEQ
insert into Product (inception,name,id) values (?,?,?)
select p1_0.id,p1_0.inception,p1_0.name from Product p1_0
