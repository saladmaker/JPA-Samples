CREATE TABLE SOMEENTITY (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 SOMEPERSISTEDSATE INTEGER, PRIMARY KEY (ID))

INSERT INTO SOMEENTITY (SOMEPERSISTEDSATE) VALUES (?)
	bind => [5]
