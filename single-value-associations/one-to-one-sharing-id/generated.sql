CREATE TABLE POST (ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL, TITLE VARCHAR(255), PRIMARY KEY (ID))

CREATE TABLE POSTDETAIL (CONTENT CLOB(64000), POST_ID BIGINT NOT NULL, PRIMARY KEY (POST_ID))

ALTER TABLE POSTDETAIL ADD CONSTRAINT POSTDETAIL_POST_ID FOREIGN KEY (POST_ID) REFERENCES POST (ID)
-------------------------------------------------------------------------------------

INSERT INTO POST (TITLE) VALUES (?)
	bind => [jpa]

INSERT INTO POSTDETAIL (CONTENT, POST_ID) VALUES (?, ?)
	bind => [sharing id in a mandatory one-to-one relationships, 1]
-------------------------------------------------------------------------------------
SELECT ID, TITLE FROM POST

-**-*-*-accessing lazy association PostDetail*-*-*-*-
SELECT POST_ID FROM POSTDETAIL WHERE (POST_ID = ?)
	bind => [1]
-*-*-*-*-*-*-*accessing lazy content
SELECT CONTENT, POST_ID FROM POSTDETAIL WHERE (POST_ID = ?)
	bind => [1]
-------------------------------------------------------------------------------------
SELECT ID, TITLE FROM POST
SELECT POST_ID FROM POSTDETAIL WHERE (POST_ID = ?)
	bind => [1]
DELETE FROM POSTDETAIL WHERE (POST_ID = ?)
	bind => [1]
DELETE FROM POST WHERE (ID = ?)
	bind => [1]

