CREATE TABLE DEPARTMENT (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15), PRIMARY KEY (SEQ_NAME))
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
-----------------------------------------------------------------
UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + ? WHERE SEQ_NAME = ?
	bind => [50, SEQ_GEN]
SELECT SEQ_COUNT FROM SEQUENCE WHERE SEQ_NAME = ?
	bind => [SEQ_GEN]
INSERT INTO DEPARTMENT (ID, NAME) VALUES (?, ?)
	bind => [1, management]
------------------------------------------------------------------
SELECT ID, NAME FROM DEPARTMENT

