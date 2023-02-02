CREATE TABLE USERS (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 EMAIL VARCHAR(255) UNIQUE, PASSWORD VARCHAR(255), PRIMARY KEY (ID))

CREATE TABLE INTREST (USERS_ID BIGINT, INTREST_NAME VARCHAR(255) NOT NULL,
 INTREST_ORDER INTEGER)


ALTER TABLE INTREST ADD CONSTRAINT INTREST_USERS_ID FOREIGN KEY (USERS_ID)
 REFERENCES USERS (ID)
-----------------------------------------------------------------------------
EclipseLink, version: Eclipse Persistence Services - 4.0.0.v202210051929
INSERT INTO USERS (EMAIL, PASSWORD) VALUES (?, ?)
	bind => [dsdfsf, dsfsd]
INSERT INTO INTREST (USERS_ID, INTREST_NAME, INTREST_ORDER) VALUES (?, ?, ?)
	bind => [1, Java, 0]
INSERT INTO INTREST (USERS_ID, INTREST_NAME, INTREST_ORDER) VALUES (?, ?, ?)
	bind => [1, Jakarta EE, 1]

--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS
SELECT t0.INTREST_NAME, t0.INTREST_ORDER FROM INTREST t0 WHERE (t0.USERS_ID = ?)
	bind => [1]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS
SELECT t0.INTREST_NAME, t0.INTREST_ORDER FROM INTREST t0 WHERE (t0.USERS_ID = ?)
	bind => [1]
DELETE FROM INTREST WHERE ((USERS_ID = ?) AND (INTREST_NAME = ?))
	bind => [1, Jakarta EE]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS
SELECT t0.INTREST_NAME, t0.INTREST_ORDER FROM INTREST t0 WHERE (t0.USERS_ID = ?)
	bind => [1]
INSERT INTO INTREST (USERS_ID, INTREST_NAME, INTREST_ORDER) VALUES (?, ?, ?)
	bind => [1, Helidon, 0]
UPDATE INTREST SET INTREST_ORDER = ? WHERE (((USERS_ID = ?) 
AND (INTREST_NAME = ?)) AND (INTREST_ORDER = ?))
	bind => [1, 1, Java, 0]
--------------------------------------------------------------------------------
SELECT ID, EMAIL, PASSWORD FROM USERS
SELECT t0.INTREST_NAME, t0.INTREST_ORDER FROM INTREST t0 WHERE (t0.USERS_ID = ?)
	bind => [1]
INSERT INTO INTREST (USERS_ID, INTREST_NAME, INTREST_ORDER) VALUES (?, ?, ?)
	bind => [1, MicroProfile, 1]
UPDATE INTREST SET INTREST_ORDER = ? WHERE (((USERS_ID = ?) 
AND (INTREST_NAME = ?)) AND (INTREST_ORDER = ?))
	bind => [2, 1, Java, 1]