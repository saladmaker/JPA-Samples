CREATE TABLE DEP (DEP_id BIGINT NOT NULL, DEP_name VARCHAR,
 PRIMARY KEY (DEP_id))

CREATE TABLE EMP (EMP_id BIGINT NOT NULL, first_name VARCHAR, last_name VARCHAR,
 DEPARTMENT_DEP_id BIGINT, PRIMARY KEY (EMP_id))

ALTER TABLE EMP ADD CONSTRAINT FK_EMP_DEPARTMENT_DEP_id 
FOREIGN KEY (DEPARTMENT_DEP_id) REFERENCES DEP (DEP_id)


INSERT INTO DEP (DEP_id, DEP_name) VALUES (?, ?)
	bind => [1, management]

INSERT INTO EMP (EMP_id, first_name, last_name, DEPARTMENT_DEP_id) 
VALUES (?, ?, ?, ?)

bind => [2, mohamed, ali, null]

UPDATE EMP SET DEPARTMENT_DEP_id = ? WHERE (EMP_id = ?)

bind => [1, 2]
