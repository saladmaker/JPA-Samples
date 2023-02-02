CREATE TABLE CUSTOMER (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 EMAIL VARCHAR(255) NOT NULL UNIQUE, FIRSTNAME VARCHAR(255),
 LASTNAME VARCHAR(255), PRIMARY KEY (ID))

CREATE TABLE ADRESS (CITY VARCHAR(255) NOT NULL, STREET VARCHAR(255) NOT NULL,
 ZIPCODE VARCHAR(255) NOT NULL, CUSTOMER_ID BIGINT, ORDER_ADRESS INTEGER)

ALTER TABLE ADRESS ADD CONSTRAINT ADRESS_CUSTOMER_ID 
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (ID)
--------------------------------------------------------------------------------
INSERT INTO CUSTOMER (EMAIL, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)
	bind => [kah@gm.com, Paul, MPRPHY]
INSERT INTO ADRESS (CUSTOMER_ID, ORDER_ADRESS, CITY, STREET, ZIPCODE) VALUES (?, ?, ?, ?, ?)
	bind => [1, 0, Arizone, lds, arizona14]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]
SELECT CITY, STREET, ZIPCODE, ORDER_ADRESS, CUSTOMER_ID FROM ADRESS WHERE (CUSTOMER_ID = ?)
	bind => [1]
--------------------------------------------------------------------------------
INSERT INTO CUSTOMER (EMAIL, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)
	bind => [BOBY@gm.com, BOBBY, FISCHER]
INSERT INTO ADRESS (CUSTOMER_ID, ORDER_ADRESS, CITY, STREET, ZIPCODE) VALUES (?, ?, ?, ?, ?)
	bind => [2, 0, Chicago, GJHG, 453]

INSERT INTO ADRESS (CUSTOMER_ID, ORDER_ADRESS, CITY, STREET, ZIPCODE) VALUES (?, ?, ?, ?, ?)
	bind => [2, 1, Iceland, SDFLS, 234]

--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]
SELECT CITY, STREET, ZIPCODE, ORDER_ADRESS, CUSTOMER_ID FROM ADRESS WHERE (CUSTOMER_ID = ?)
	bind => [1]
UPDATE ADRESS SET ORDER_ADRESS = ? WHERE ((ORDER_ADRESS = ?) AND (CUSTOMER_ID = ?))
	bind => [1, 0, 1]
INSERT INTO ADRESS (CUSTOMER_ID, ORDER_ADRESS, CITY, STREET, ZIPCODE) VALUES (?, ?, ?, ?, ?)
	bind => [1, 0, arizona, Arizona's court, arizona14]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]
SELECT CITY, STREET, ZIPCODE, ORDER_ADRESS, CUSTOMER_ID FROM ADRESS WHERE (CUSTOMER_ID = ?)
	bind => [1]

DELETE FROM ADRESS WHERE ((((CITY = ?) AND (STREET = ?)) AND (ZIPCODE = ?)) AND (CUSTOMER_ID = ?))
	bind => [Arizone, lds, arizona14, 1]
INSERT INTO ADRESS (CUSTOMER_ID, ORDER_ADRESS, CITY, STREET, ZIPCODE) VALUES (?, ?, ?, ?, ?)
	bind => [1, 1, fds, ZXC, kjk]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]
SELECT CITY, STREET, ZIPCODE, ORDER_ADRESS, CUSTOMER_ID FROM ADRESS WHERE (CUSTOMER_ID = ?)
	bind => [1]
the adress Adress{street=ZXC, zipCode=kjk, city=fds} is true 
UPDATE ADRESS SET ORDER_ADRESS = ? WHERE ((ORDER_ADRESS = ?) AND (CUSTOMER_ID = ?))
	bind => [2, 1, 1]
-- NOW PAUL'S ADRESSES START FROM 1 NOT FROM ZERO AS YOU WOULD EXPECT