CREATE TABLE DIESELCAR (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 BRAND VARCHAR(255) NOT NULL, MAXSPEED INTEGER, MODEL VARCHAR(255) NOT NULL,
 NUMBEROFCYLINDER INTEGER, POWER INTEGER NOT NULL,
 VEHICLEUNIQUENUMBER VARCHAR(255) NOT NULL UNIQUE, PRIMARY KEY (ID))

CREATE TABLE ELECTRICCAR (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
 ACCELERATION FLOAT, BRAND VARCHAR(255) NOT NULL, DISTANCEPERFULCHARGE INTEGER,
 MODEL VARCHAR(255) NOT NULL, POWER INTEGER NOT NULL,
 VEHICLEUNIQUENUMBER VARCHAR(255) NOT NULL UNIQUE, PRIMARY KEY (ID))

INSERT INTO DIESELCAR (BRAND, MAXSPEED, MODEL, NUMBEROFCYLINDER, POWER,
 VEHICLEUNIQUENUMBER) VALUES (?, ?, ?, ?, ?, ?)
	bind => [porch, 300, 911, 8, 300, dslfjl34l]

INSERT INTO ELECTRICCAR (ACCELERATION, BRAND, DISTANCEPERFULCHARGE, MODEL,
 POWER, VEHICLEUNIQUENUMBER) VALUES (?, ?, ?, ?, ?, ?)
	bind => [9.0, tesla, 200, 420, 100, 3123jl231l23]

SELECT ID, ACCELERATION, BRAND, DISTANCEPERFULCHARGE, MODEL, POWER,
 VEHICLEUNIQUENUMBER FROM ELECTRICCAR

SELECT ID, BRAND, MAXSPEED, MODEL, NUMBEROFCYLINDER, POWER,
 VEHICLEUNIQUENUMBER FROM DIESELCAR

SELECT ID, BRAND, MAXSPEED, MODEL, NUMBEROFCYLINDER, POWER,
 VEHICLEUNIQUENUMBER FROM DIESELCAR

SELECT ID, ACCELERATION, BRAND, DISTANCEPERFULCHARGE, MODEL, POWER,
 VEHICLEUNIQUENUMBER FROM ELECTRICCAR