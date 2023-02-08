CREATE TABLE CUSTOMER (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 EMAIL VARCHAR(255) NOT NULL UNIQUE, FIRSTNAME VARCHAR(255),
 LASTNAME VARCHAR(255), PRIMARY KEY (ID))

CREATE TABLE ADRESS (CUSTOMER_ID BIGINT, ADRESSES VARCHAR(255),
 CITY VARCHAR(255) NOT NULL, STREET VARCHAR(255) NOT NULL,
 ZIPCODE VARCHAR(255) NOT NULL)

ALTER TABLE ADRESS ADD CONSTRAINT ADRESS_CUSTOMER_ID FOREIGN KEY (CUSTOMER_ID) 
REFERENCES CUSTOMER (ID)
--------------------------------------------------------------------------------
INSERT INTO CUSTOMER (EMAIL, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)
	bind => [kah@gm.com, Paul, MPRPHY]

INSERT INTO ADRESS (CUSTOMER_ID, ADRESSES, CITY, STREET, ZIPCODE) 
VALUES (?, ?, ?, ?, ?)
	bind => [1, HOME, Arizone, lds, arizona14]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]
SELECT t0.CITY, t0.STREET, t0.ZIPCODE, t0.ADRESSES FROM ADRESS t0 WHERE (t0.CUSTOMER_ID = ?)
	bind => [1]
--------------------------------------------------------------------------------
INSERT INTO CUSTOMER (EMAIL, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)
	bind => [BOBY@gm.com, BOBBY, FISCHER]
INSERT INTO ADRESS (CUSTOMER_ID, ADRESSES, CITY, STREET, ZIPCODE) 
VALUES (?, ?, ?, ?, ?)
	bind => [2, WORK, Iceland, SDFLS, 234]

INSERT INTO ADRESS (CUSTOMER_ID, ADRESSES, CITY, STREET, ZIPCODE) 
VALUES (?, ?, ?, ?, ?)
	bind => [2, HOME, Chicago, GJHG, 453]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]
SELECT t0.CITY, t0.STREET, t0.ZIPCODE, t0.ADRESSES FROM ADRESS t0 
WHERE (t0.CUSTOMER_ID = ?)
	bind => [1]

INSERT INTO ADRESS (CUSTOMER_ID, ADRESSES, CITY, STREET, ZIPCODE) 
VALUES (?, ?, ?, ?, ?)
	bind => [1, WORK, arizona, Arizona's court, arizona14]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]

SELECT t0.CITY, t0.STREET, t0.ZIPCODE, t0.ADRESSES FROM ADRESS t0
 WHERE (t0.CUSTOMER_ID = ?)
	bind => [1]

DELETE FROM ADRESS WHERE ((CUSTOMER_ID = ?) AND (((CITY = ?) AND (STREET = ?)) 
AND (ZIPCODE = ?)))
	bind => [1, Arizone, lds, arizona14]

INSERT INTO ADRESS (CUSTOMER_ID, ADRESSES, CITY, STREET, ZIPCODE) 
VALUES (?, ?, ?, ?, ?)
	bind => [1, ALTERNATIVE, fds, ZXC, kjk]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, FIRSTNAME, LASTNAME FROM CUSTOMER WHERE (EMAIL = ?)
	bind => [kah@gm.com]
SELECT t0.CITY, t0.STREET, t0.ZIPCODE, t0.ADRESSES FROM ADRESS t0 
WHERE (t0.CUSTOMER_ID = ?)

	bind => [1]