CREATE TABLE USERS (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 EMAIL VARCHAR(255) UNIQUE, PASSWORD VARCHAR(255), PRIMARY KEY (ID))

CREATE TABLE INTREST (USERS_ID BIGINT, INTREST_NAME VARCHAR(255) NOT NULL)

ALTER TABLE INTREST ADD CONSTRAINT INTREST_USERS_ID 
FOREIGN KEY (USERS_ID) REFERENCES USERS (ID)
--------------------------------------------------------------------------------
INSERT INTO USERS (EMAIL, PASSWORD) VALUES (?, ?)
	bind => [dsdfsf, dsfsd]
INSERT INTO INTREST (USERS_ID, INTREST_NAME) VALUES (?, ?)
	bind => [1, Java]
INSERT INTO INTREST (USERS_ID, INTREST_NAME) VALUES (?, ?)
	bind => [1, Jakarta EE]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS

SELECT t0.INTREST_NAME FROM INTREST t0 WHERE (t0.USERS_ID = ?) 
ORDER BY t0.INTREST_NAME ASC
	bind => [1]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS

SELECT t0.INTREST_NAME FROM INTREST t0 WHERE (t0.USERS_ID = ?) 
ORDER BY t0.INTREST_NAME ASC
	bind => [1]

DELETE FROM INTREST WHERE ((USERS_ID = ?) AND (INTREST_NAME = ?))
	bind => [1, Jakarta EE]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS

SELECT t0.INTREST_NAME FROM INTREST t0 WHERE (t0.USERS_ID = ?) 
ORDER BY t0.INTREST_NAME ASC
	bind => [1]

INSERT INTO INTREST (USERS_ID, INTREST_NAME) VALUES (?, ?)
	bind => [1, Helidon]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS

SELECT t0.INTREST_NAME FROM INTREST t0 WHERE (t0.USERS_ID = ?) 
ORDER BY t0.INTREST_NAME ASC
	bind => [1]

INSERT INTO INTREST (USERS_ID, INTREST_NAME) VALUES (?, ?)
	bind => [1, MicroProfile]
