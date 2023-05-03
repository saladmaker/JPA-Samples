CREATE TABLE PRODUCT (ID BIGINT NOT NULL, NAME VARCHAR(255) NOT NULL UNIQUE,
 AMOUNT DECIMAL(15), CURRENCY VARCHAR(255), PRIMARY KEY (ID));

----------------------------------------------------------------------
INSERT INTO PRODUCT (ID, NAME, AMOUNT, CURRENCY) VALUES (?, ?, ?, ?)
	bind => [1, The Tree Hollyday, 234, EUR]
---------------------------------------------------------------------
SELECT ID, NAME, AMOUNT, CURRENCY FROM PRODUCT