CREATE TABLE EMPLOYEE (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL, employee_type VARCHAR(31), SSN VARCHAR(255) NOT NULL UNIQUE,
 FIRSTNAME VARCHAR(255) NOT NULL UNIQUE, LASTNAME VARCHAR(255) NOT NULL UNIQUE,
 RATE DECIMAL(15), SALARY DECIMAL(15), PRIMARY KEY (ID))
/*-------------------------------------*/
INSERT INTO EMPLOYEE (SSN, FIRSTNAME, LASTNAME, SALARY, employee_type) VALUES (?, ?, ?, ?, ?)
	bind => [sfkhsk, sfkhsdf, skdfhsdk, 0, FE]

INSERT INTO EMPLOYEE (SSN, FIRSTNAME, LASTNAME, RATE, employee_type) VALUES (?, ?, ?, ?, ?)
	bind => [kdfhsk, sdf;ls, sdfls, 1, PE]

/*---------------------------*/

SELECT ID, employee_type, SSN, FIRSTNAME, LASTNAME, SALARY FROM EMPLOYEE WHERE (employee_type = ?)
	bind => [FE]

SELECT ID, employee_type, SSN, FIRSTNAME, LASTNAME, RATE FROM EMPLOYEE WHERE (employee_type = ?)
	bind => [PE]
SELECT ID, employee_type, SSN, FIRSTNAME, LASTNAME, SALARY, RATE FROM EMPLOYEE
