/*
 * DDL
 * 		- Data Definition Language : 데이터 정의어
 * 		- 객체를 정의하기 위한 CREATE(생성), ALTER(수정), DROP(삭제) 구분이 있다.
 * 		- 오라클 객체는 TABLE, VIEW, SEQUENCE, FUNCTION, PROCEDUAL, USER, ... 등이 있다. 
 */

/*
 * CREATE TABLE 테이블명(
 *	   컬렴명 자료형(크기) [제약조건]
 *	 , ...
 * ); 
 */

/* 제약조건(CONSTRAINT)
 * 	 테이블에 데이터를 저장할 때 
 *   저장되는 데이터를 제한하기 위해 사용하는 조건
 *   이는 데이터의 무결성 보정을 목적으로 한다.
 *   입력되는 데이터의 오류 확인도 할 수 있다.
 * 
 * NOT NULL : NULL 데이터를 사용하지 않음
 * UNIQUE : 중복값을 허용하지 않음
 * PRIMARY KEY : NOT NULL + UNIQUE 결합된 제약조건
 * 				 유일한 고유값을 사용하기 위해 쓰인다.
 *   	 		 PK 또는 기본키 라고 한다.
 * 				 데이터 식별자로 사용하기 위해
 * FOREIGN KEY : 외래키(FK), 참조키(값) 라고 한다.
 * 				 다른 테이블의 값을 참조하기 위한 용도로 사용
 * 				 참조값을 있는 경우에만 저장할 수 있게 제약.
 * 				 참조 되는 데이터가 있는 경우 수정, 삭제를 
 * 				 임의로 하지 못하게 계약
 * 		ON DELETE RESTRICTED 옵션
 *      ON DELETE SET NULL 옵션 : 참조하고 있는 테이블이 지워지면 NULL로 바꿔줌
 * 		ON DELETE CASCASE 옵션 : 참조하고 있는 테이블이 지워지면 같이 지워짐
 * 
 * CHECK : 미리 설정한 값만 지정할 수 있도록 검사를 하여 제약
 * 
 * 컬럼 레벨
 * 		: 컬럼에 직접 명시하여 작성
 * 테이블 레벨
 * 		: 컬럼 외로 명시하여 작성
 * 		  복합 키 설정이 필요한 경우 사용
 */

-- VARCHAR2(50) : 50 바이트
-- 영문자 : 50 바이트, 한글 50/3 = 16 바이트
CREATE TABLE sample_t (
	   u_id      NUMBER       -- PRIMARY KEY
	 , jumin 	 CHAR(13)     -- UNIQUE
	 , name 	 VARCHAR2(50) NOT NULL
	 , age 		 NUMBER(3)    DEFAULT(0)
	 , gender 	 CHAR(1)      CHECK(GENDER IN ('M', 'F'))
	 , birth_day DATE         DEFAULT(SYSDATE)
	 , ref_col	 NUMBER       -- REFERENCES ref_t(r_id)
	 , CONSTRAINT PK_U_ID PRIMARY KEY(u_id)
	 , CONSTRAINT UK_JUMIN UNIQUE(jumin)
	 , CONSTRAINT FK_REF_T_R_ID FOREIGN KEY(ref_col) REFERENCES ref_t(r_id)
);
CREATE TABLE ref_t (
	   r_id NUMBER PRIMARY KEY
	 , note VARCHAR2(100)
);
-- 테이블 삭제
DROP TABLE sample_t;
DROP TABLE ref_t;

SELECT * FROM sample_t;

-- 테이블 확인해보는 방법
SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = 'SAMPLE_T';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'SAMPLE_T';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'SAMPLE_T';
-- DESC SAMPLE_T;

COMMENT ON COLUMN sample_t.name IS '이름';
COMMENT ON COLUMN sample_t.age IS '나이';
COMMENT ON COLUMN sample_t.gender IS '성별(M : 남자, F : 여자)';
COMMENT ON COLUMN sample_t.birth_day IS '생년월일';

SELECT * FROM USER_COL_COMMENTS WHERE TABLE_NAME = 'SAMPLE_T';




-- 테이블 정보를 수정할 때 기존 데이터 유무와 
-- 다른 테이블과의 관계 대한 사항을 고려하여 작성해야한다.
-- 영향이 있으면 안된다.
CREATE  TABLE ALT_T (
	    u_id NUMBER
      ,	name VARCHAR(10)
      , g_type CHAR(1)
      , now_date DATE
      , remark VARCHAR(100)
);
CREATE TABLE MY_TEST_REF(
	   ref_id NUMBER PRIMARY KEY
	 , ref_text VARCHAR(100)
);

-- 테이블명을 ALT_T에서 MY_TEST로 변경
ALTER TABLE ALT_T RENAME TO MY_TEST; -- 비추천!

ALTER TABLE MY_TEST RENAME COLUMN now_date TO birth_date; -- 비추천!
ALTER TABLE MY_TEST ADD age NUMBER(3) DEFAULT(0) NOT NULL;
ALTER TABLE MY_TEST ADD ref_col NUMBER;
ALTER TABLE MY_TEST MODIFY name VARCHAR(50); -- 타입 크기 변경
-- 데이터의 크기를 늘리는 방향으로 사용하는 것을 추천
-- VARCHAR(5) 처럼 크기를 줄이면 안됨
-- ALTER TABLE MY_TEST MODIFY name NUMBER; 
-- 타입도 변경X -> 오류뜸, 어쩔수 없이 변경해야 할땐 백업하고 다시 만들어야함
ALTER TABLE MY_TEST DROP COLUMN remark; -- 비추천!

ALTER TABLE MY_TEST MODIFY u_id CONSTRAINT PK_MY_TEST_U_ID PRIMARY KEY; -- 컬럼 레벨로 컬럼 수정하면서 추가
ALTER TABLE MY_TEST ADD CONSTRAINT PK_MY_TEST_U_ID PRIMARY KEY(u_id); -- 테이블 레벨로 추가
ALTER TABLE MY_TEST MODIFY name CONSTRAINT UK_MY_TEST_NAME UNIQUE;
ALTER TABLE MY_TEST ADD CONSTRAINT UK_MY_TEST_NAME UNIQUE(name); 
ALTER TABLE MY_TEST MODIFY ref_col CONSTRAINT FK_MY_TEST_REF_COL REFERENCES MY_TEST_REF(ref_id);
ALTER TABLE MY_TEST ADD CONSTRAINT FK_MY_TEST_REF_COL FOREIGN KEY(ref_col) REFERENCES MY_TEST_REF(ref_id);

ALTER TABLE MY_TEST MODIFY g_type CONSTRAINTS NN_MY_TEST_G_TYPE NOT NULL;
ALTER TABLE MY_TEST MODIFY age CONSTRAINTS CK_MY_TEST_AGE CHECK(AGE BETWEEN 0 AND 150);

ALTER TABLE MY_TEST 
	   DROP CONSTRAINT PK_MY_TEST_U_ID DROP CONSTRAINT FK_MY_TEST_REF_COL
	   DROP CONSTRAINT UK_MY_TEST_NAME DROP CONSTRAINT NM_MY_TEST_G_TYPE
	   DROP CONSTRAINT CK_MY_TEST_AGE;
	  
ALTER TABLE MY_TEST DROP CONSTRAINT SYS_C007907;

-- 관계되어 있는 참조 모두 삭제
ALTER TABLE MY_TEST_REF DROP COLUMN ref_id CASCADE CONSTRAINT; -- 매우 비추천..
DROP TABLE MY_TEST_REF CASCADE CONSTRAINT; -- 비추천

SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = 'MY_TEST';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'MY_TEST';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'MY_TEST';

