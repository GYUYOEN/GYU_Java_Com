DROP TABLE COMMENTS;
DROP SEQUENCE COMMENTS_SEQ;

CREATE TABLE COMMENTS (
       ID NUMBER PRIMARY KEY
     , BID NUMBER
     , CONTENT VARCHAR2(1000) NOT NULL
     , EMPID NUMBER
     , CREATEDATE DATE DEFAULT(SYSDATE)
     , DELETED VARCHAR2(1) CHECK(DELETED IN ('Y', 'N'))
     , CONSTRAINT COMMENTS_BID_FK FOREIGN KEY(BID) REFERENCES EMP_BOARDS(ID)
     , CONSTRAINT COMMENTS_EMPID_FK FOREIGN KEY(EMPID) REFERENCES EMPLOYEES(EMPLOYEE_ID)
);

CREATE SEQUENCE COMMENTS_SEQ NOCACHE;


/*
 * 특정 게시물에 대한 댓글 조회
 */
SELECT *
  FROM EMP_BOARDS B
  JOIN COMMENTS C
    ON B.ID = C.BID
  JOIN EMPLOYEES E
    ON C.EMPID = E.EMPLOYEE_ID
 WHERE C.BID = 1;

/*
 * 댓글 추가
 */
INSERT INTO COMMENTS(ID, BID, CONTENT, EMPID, DELETED)
     VALUES(COMMENTS_SEQ.NEXTVAL, 1, '내용', 100, 'N');

/*
 * 댓글 수정
 */
UPDATE COMMENTS
   SET CONTENT = '내용 수정'
 WHERE ID = 1;

/*
 * 댓글 삭제
 */
UPDATE COMMENTS
   SET DELETED = 'Y'
 WHERE ID = 1;
