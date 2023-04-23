CREATE TABLE DEPARTMENT (ID VARCHAR(255) NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))

------------------------------------------------------------------------------------------
INSERT INTO DEPARTMENT (ID, NAME) VALUES (?, ?)
	bind => [7f1a66de-3304-4bba-90f5-987cccf88b04, management]

------------------------------------------------------------------------------------------
SELECT ID, NAME FROM DEPARTMENT

