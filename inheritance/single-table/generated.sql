CREATE TABLE EMPLOYEE (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 DTYPE VARCHAR(31), SSN VARCHAR(255) NOT NULL UNIQUE,
 FIRSTNAME VARCHAR(255) NOT NULL UNIQUE, LASTNAME VARCHAR(255) NOT NULL UNIQUE,
 RATE DECIMAL(15), SALARY DECIMAL(15), PRIMARY KEY (ID))


INSERT INTO EMPLOYEE (SSN, FIRSTNAME, LASTNAME, SALARY, DTYPE) VALUES (?, ?, ?, ?, ?)
	bind => [sfkhsk, sfkhsdf, skdfhsdk, 0, FullTimeEmployee]
INSERT INTO EMPLOYEE (SSN, FIRSTNAME, LASTNAME, RATE, DTYPE) VALUES (?, ?, ?, ?, ?)
	bind => [kdfhsk, sdf;ls, sdfls, 1, PartTimeEmployee]
SELECT ID, DTYPE, SSN, FIRSTNAME, LASTNAME, SALARY FROM EMPLOYEE WHERE (DTYPE = ?)
	bind => [FullTimeEmployee]
SELECT ID, DTYPE, SSN, FIRSTNAME, LASTNAME, RATE FROM EMPLOYEE WHERE (DTYPE = ?)
	bind => [PartTimeEmployee]
SELECT ID, DTYPE, SSN, FIRSTNAME, LASTNAME, SALARY, RATE FROM EMPLOYEE