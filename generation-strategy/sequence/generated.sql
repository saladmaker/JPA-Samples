CREATE TABLE DEPARTMENT (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))

CREATE SEQUENCE SEQ_GEN_SEQUENCE INCREMENT BY 50 START WITH 50
---------------------------------------------------------------------------------
/* once every 50*/
VALUES(NEXT VALUE FOR SEQ_GEN_SEQUENCE)

INSERT INTO DEPARTMENT (ID, NAME) VALUES (?, ?)
	bind => [1, management]
----------------------------------------------------------------------------------
SELECT ID, NAME FROM DEPARTMENT

