CREATE TABLE DEPARTMENT (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))

CREATE TABLE MY_GENERATOR (GEN_NAME VARCHAR(50) NOT NULL, GEN_COUNT DECIMAL(15), PRIMARY KEY (GEN_NAME))

INSERT INTO MY_GENERATOR(GEN_NAME, GEN_COUNT) values ('dep_id', 5)


---------------------------------------------------------------------------
UPDATE MY_GENERATOR SET GEN_COUNT = GEN_COUNT + ? WHERE GEN_NAME = ?
	bind => [50, dep_id]
SELECT GEN_COUNT FROM MY_GENERATOR WHERE GEN_NAME = ?
	bind => [dep_id]
INSERT INTO DEPARTMENT (ID, NAME) VALUES (?, ?)
	bind => [6, management]
-----------------------------------------------------------------------------
SELECT ID, NAME FROM DEPARTMEN