CREATE TABLE CUSTOMER (ID BIGINT NOT NULL, DATEOFBIRTH DATE, first_name VARCHAR(50) NOT NULL, LASTNAME VARCHAR, PRIMARY KEY (ID))

INSERT INTO CUSTOMER (ID, DATEOFBIRTH, first_name, LASTNAME) VALUES (?, ?, ?, ?)
	bind => [1, 1994-11-26, f1, l1]

--------------------------------------------------------------------------------

CREATE TABLE CUSTOMER (ID BIGINT NOT NULL, DATEOFBIRTH DATE, first_name VARCHAR(50) NOT NULL, LASTNAME VARCHAR, PRIMARY KEY (ID))

INSERT INTO CUSTOMER (ID, DATEOFBIRTH, first_name, LASTNAME) VALUES (?, ?, ?, ?)
	bind => [1, 1994-11-26, f2, l2]
UPDATE CUSTOMER SET LASTNAME = ? WHERE (ID = ?)
	bind => [B, 1]
SELECT ID, DATEOFBIRTH, first_name, LASTNAME FROM CUSTOMER

---------------------------------------------------------------------------------

CREATE TABLE CUSTOMER (ID BIGINT NOT NULL, DATEOFBIRTH DATE, first_name VARCHAR(50) NOT NULL, LASTNAME VARCHAR, PRIMARY KEY (ID))

INSERT INTO CUSTOMER (ID, DATEOFBIRTH, first_name, LASTNAME) VALUES (?, ?, ?, ?)
	bind => [1, 1995-11-26, f3, l3]
DELETE FROM CUSTOMER WHERE (ID = ?)
	bind => [1]
SELECT ID, DATEOFBIRTH, first_name, LASTNAME FROM CUSTOMER


