CREATE TABLE POST (ID BIGINT NOT NULL, CONTENT CLOB(64000), TITLE VARCHAR(255),
 PRIMARY KEY (ID))
CREATE TABLE COMMENT (ID BIGINT NOT NULL, CONTENT VARCHAR(255),
 PRIMARY KEY (ID))
-CREATE TABLE POST_COMMENT (Post_ID BIGINT NOT NULL,
 comments_ID BIGINT NOT NULL, PRIMARY KEY (Post_ID, comments_ID))
ALTER TABLE POST_COMMENT ADD CONSTRAINT POSTCOMMENTPost_ID FOREIGN KEY
 (Post_ID) REFERENCES POST (ID)
ALTER TABLE POST_COMMENT ADD CONSTRAINT PSTCOMMENTcmmntsID FOREIGN KEY 
(comments_ID) REFERENCES COMMENT (ID)

INSERT INTO COMMENT (ID, CONTENT) VALUES (?, ?)
	bind => [4, cadcading all operation is dangerous featurethat must be used judiciously!]
INSERT INTO COMMENT (ID, CONTENT) VALUES (?, ?)
	bind => [2, bidirectional mapping is the way to go!]
INSERT INTO COMMENT (ID, CONTENT) VALUES (?, ?)
	bind => [3, using Map as type is overcomplication I think!]
INSERT INTO POST (ID, CONTENT, TITLE) VALUES (?, ?, ?)
	bind => [1, one to many unidirectional mapping is when the owner side contains
                  a Collection of the target entity. you can use
                  Collection, List, Set, Map,...etc.
                  but it isn't the ideal mapping solution!
, JPA one to many unidirectional mapping.]
INSERT INTO POST_COMMENT (comments_ID, Post_ID) VALUES (?, ?)
	bind => [2, 1]
INSERT INTO POST_COMMENT (comments_ID, Post_ID) VALUES (?, ?)
	bind => [3, 1]
INSERT INTO POST_COMMENT (comments_ID, Post_ID) VALUES (?, ?)
	bind => [4, 1]

-*-*-* accessing post
SELECT ID, TITLE FROM POST
-*-*accessing comments-*-*
SELECT t1.ID, t1.CONTENT FROM POST_COMMENT t0, COMMENT t1
 WHERE ((t0.Post_ID = ?) AND (t1.ID = t0.comments_ID))
	bind => [1]
