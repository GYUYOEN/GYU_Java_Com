/*
 * 지출내역서(가계부)를 위한 테이블을 만들어 본다.
 * 		- 테이블 이름은 지출내역서_T 라고 한다.
 * 		- 날짜, 입금액, 출금액, 비고를 자장할 수 있는 컬럼 필요
 * 		- 비고의 경우 한글 150자 까지 저장 가능해야 한다.
 */

CREATE TABLE 지출내역서_T(
	   날짜 DATE
	 , 입금액 NUMBER
	 , 출금액 NUMBER
	 , 비고 VARCHAR2(450)
);

/*
 * 위에서 만든 지출내역서에 다음의 컬럼 정보를 추가 및 수정한다.
 * 		- 행 데이터를 식별하기 위한 식별자용 컬럼을 추가 이 컬럼 이름은 ACCOUNT_ID 로 한다.
 * 		- 비고의 데이터 저장 크기를 기존보다 2배 늘린다.
 * 		- 지출내역 항목을 구분하기 위한 ACCOUNT_TYPE 컬럼을 추가하고
 * 		  해당 컬럼은 FK로 만든다.
 * 		- ACCOUNT_TYPE 컬럼이 참조하는 테이블은 "지출내역구분_T" 라는 테이블로 만들어 둔다.
 * 		- 지출내역구분 테이블에는 식별자용 컬럼, 구분명 컬럼이 있어야 한다.
 */ 

ALTER TABLE 지출내역서_T ADD ACCOUNT_ID NUMBER CONSTRAINT PK_지출내역서_T_ACCOUNT_ID PRIMARY KEY;
ALTER TABLE 지출내역서_T MODIFY 비고 VARCHAR2(900);

CREATE TABLE 지출내역구분_T (
	   구분ID NUMBER CONSTRAINT PK_지출내역구분_구분ID PRIMARY KEY
	 , 구분명 VARCHAR2(100)
);

ALTER TABLE 지출내역서_T ADD ACCOUNT_TYPE NUMBER CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE REFERENCES 지출내역구분_T(구분ID);

SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME LIKE '지출내역%';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME LIKE '지출내역%';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME LIKE '지출내역%';





INSERT INTO 지출내역구분_T VALUES(1, '은행예금');
--INSERT INTO 지출내역구분_T VALUES(1, '은행적금'); -- 중복 : PRIMARY KEY로 인해 오류 
INSERT INTO 지출내역구분_T VALUES(2, '은행적금');
INSERT INTO 지출내역구분_T(구분ID, 구분명) VALUES(3, '교통비');
INSERT INTO 지출내역구분_T(구분명, 구분ID) VALUES('통신비', 4);

CREATE TABLE 지출내역구분_COPY (
	   구분ID NUMBER CONSTRAINT PK_지출내역구분_COPY_구분ID PRIMARY KEY
	 , 구분명 VARCHAR2(100)
);

-- 서브쿼리를 이용한 INSERT ALL(두개 이상의 테이블에 한 번에 삽입)
INSERT INTO 지출내역구분_COPY(
	   SELECT 구분ID, 구분명
	     FROM 지출내역구분_T
);

-- COMMIT;
-- DELETE 하기전에 SELECT해서 확인하고 지우기
SELECT * FROM 지출내역구분_COPY;
DELETE FROM 지출내역구분_COPY;
-- 오토로 안할 때 COMMIT, ROLLBACK 사용
-- ROLLBACK; : 뒷단계로 복구, 주의 : COMMIT 해버리면 복구 불가능

-- 서브쿼리 이용해서 테이블 생성
-- WHERE 1 = 0 : 조건이 일부로 아무것도 없게 만듬 -> 아무것도 출력이 안됨(빈테이블)
-- WHERE 1 = 1 : 다 출력됨
CREATE TABLE 지출내역구분_COPY2
	AS SELECT 구분명 FROM 지출내역구분_T
	    WHERE 1 = 0;

INSERT ALL
  INTO 지출내역구분_COPY VALUES(구분ID, 구분명)
  INTO 지출내역구분_COPY2 VALUES(구분명)
SELECT 구분ID, 구분명
  FROM 지출내역구분_T;

-- SELECT에 사용되는 컬럼명이랑 VALUES에 작성되는 이름이 매치되어야 함 
INSERT ALL
  INTO 지출내역구분_COPY VALUES(idx, name)
  INTO 지출내역구분_COPY2 VALUES(name)
SELECT 구분ID AS idx, 구분명 AS name
  FROM 지출내역구분_T;
  
SELECT * FROM 지출내역구분_COPY;
SELECT * FROM 지출내역구분_COPY2;
DELETE FROM 지출내역구분_COPY;
DELETE FROM 지출내역구분_COPY2;

/*
 * COMMISSION_PCT 유무에 따라 
 * 		커미션이 있는 경우 EMP_COMMISSION 테이블에 사번, 이름, 급여, 커미션PCT 정보를 저장하고
 * 		커미션이 없는 경우 EMP_NO_COMMISSION 테이블에 사번, 이름, 급여 정보를 저장한다.
 */
CREATE TABLE EMP_COMMISSION
	AS SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME
		    , SALARY, COMMISSION_PCT
		 FROM EMPLOYEES
		WHERE 1 = 0; -- 빈테이블 만들기 위해 사용

INSERT ALL
  INTO EMP_COMMISSION VALUES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT)
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME
	 , SALARY, COMMISSION_PCT
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NOT NULL;

CREATE TABLE EMP_NO_COMMISSION
	AS SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME
		    , SALARY
		 FROM EMPLOYEES
		WHERE 1 = 0;

INSERT ALL
  INTO EMP_NO_COMMISSION VALUES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY)
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME
	 , SALARY
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NULL;

INSERT ALL
  WHEN COMMISSION_PCT IS NULL THEN
 	   INTO EMP_NO_COMMISSION VALUES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY)
  WHEN COMMISSION_PCT IS NOT NULL THEN
 	   INTO EMP_COMMISSION VALUES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT)
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT
  FROM EMPLOYEES; 
 
SELECT * FROM EMP_COMMISSION;
SELECT * FROM EMP_NO_COMMISSION;

DELETE FROM EMP_COMMISSION;
DELETE FROM EMP_NO_COMMISSION;

COMMIT;

-- UPDATE 하기 전에 SELECT로 먼저 확인해보면 좋음
SELECT * FROM EMP_COMMISSION WHERE EMPLOYEE_ID <= 149;

UPDATE EMP_COMMISSION
   SET COMMISSION_PCT = 0.5
  WHERE EMPLOYEE_ID <= 149;

ROLLBACK;
 
-- 145번의 급여와 PCT를 147에 적용
UPDATE EMP_COMMISSION
   SET SALARY = (SELECT SALARY FROM EMP_COMMISSION WHERE EMPLOYEE_ID = 145)
     , COMMISSION_PCT = (SELECT COMMISSION_PCT FROM EMP_COMMISSION WHERE EMPLOYEE_ID = 145)
 WHERE EMPLOYEE_ID = 147;

-- 위의 식 : 조회할 컬럼만 다르고 나머지 내용은 같은 경우
UPDATE EMP_COMMISSION
   SET (SALARY, COMMISSION_PCT) =(SELECT SALARY, COMMISSION_PCT FROM EMP_COMMISSION WHERE EMPLOYEE_ID = 145)
 WHERE EMPLOYEE_ID = 147;
 
ROLLBACK;

SELECT * FROM USER_ALL_TABLES;
SELECT * FROM 지출내역서_T;
INSERT INTO 지출내역서_T VALUES(SYSDATE, 1000, 0, 'Test', 1, 2);
SELECT * FROM 지출내역구분_T;
DELETE FROM 지출내역구분_T WHERE 구분 ID = 2; -- 에러 : 참조중(지출내역서에 2가 구분ID=2를 참조)
INSERT INTO 지출내역구분_T VALUES(2, '은행적금');

-- 하지마세요... 꼬임..
-- 임시적으로 비활성화 : 제약조건위배가 뜨지 않게 함
ALTER TABLE 지출내역서_T DISABLE CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE CASCADE;
-- 다시 활성화
ALTER TABLE 지출내역서_T ENABLE CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE;

ALTER TABLE 지출내역서_T DROP CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE;
-- ON DELETE CASCADE : 행 다지움
-- ON DELETE SET NULL : ACCOUNT_TYPE을 NULL로 바꿈
ALTER TABLE 지출내역서_T ADD CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE
                     	  FOREIGN KEY(ACCOUNT_TYPE) REFERENCES 지출내역서구분_T(구분ID) ON DELETE CASCADE; -- SET NULL

-- 제약 조건에 지정해준 이름 확인
SELECT * FROM  USER_CONSTRAINTS WHERE TABLE_NAME = '지출내역서_T';

SELECT * FROM 지출내역서_T;
-- 행데이터를 다지움
DELETE FROM 지출내역서_T;
TRUNCATE TABLE 지출내역서_T; -- ROLLBACK 해도 복원이 안됨
-- DELETE하면 COMMIT하기 전까지는 ROLLBACK 가능
ROLLBACK;