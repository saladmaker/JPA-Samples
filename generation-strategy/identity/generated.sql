CREATE TABLE DEPARTMENT (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))

---------------------------------------------------------------------------------
INSERT INTO DEPARTMENT (NAME) VALUES (?)
	bind => [management]

---------------------------------
SELECT ID, NAME FROM DEPARTMENT

