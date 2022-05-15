SELECT * FROM ALL_ALL_TABLES WHERE TABLE_NAME = 'EMPLOYEES';

-- 관리자 계정(SYSTEM)에서는 소유자(PUSER1)를 지칭하면 사용가능
-- 다른 소유자 계정(DEVADMIN)으로는 지칭해서 사용불가능 : 계정자체가 서로 다르기 때문
SELECT * FROM PUSER1.EMPLOYEES;

-- 개별 계정에서 동의어를 만들 수 있도록 권한 부여(PRIVATE 동의어만 생성 가능)
GRANT CREATE SYNONYM TO PUSER1;
COMMIT;

-- 긴 테이블명을 짧게 바꿔줌
-- 바꾼 테이블명은 PUSER1에서만 사용 가능, 다른 계정에서는 사용 불가능
CREATE SYNONYM EMP FOR EMPLOYEES;
DROP SYNONYM EMP;

SELECT * FROM EMPLOYEES;
-- 소유자 계정으로 SYNONYM 바꾼거므로 관리자 계정에서는 사용이 안됨(별칭 사용해야함)
SELECT * FROM EMP;

-- 원래 테이블명과 동의어 전부 사용 가능
SELECT * FROM PUSER1.EMPLOYEES;
SELECT * FROM PUSER1.EMP;

-- 관리자 계정으로 PUBLIC 동의어 생성
CREATE PUBLIC SYNONYM EMP FOR PUSER1.EMPLOYEES;
CREATE PUBLIC SYNONYM DEP FOR PUSER1.DEPARTMENTS;
CREATE SYNONYM JOB FOR PUSER1.JOBS; -- 비공개

-- GRANT SELECT ANY TABLE TO PUSER1;
-- REVOKE SELECT ANY TABLE FROM PUSER1;
-- REVOKE SELECT ANY TABLE FROM DEVADMIN;

-- DEVADMIN 계정이 EMP 테이블을 조회할 수 있도록 조회권한 부여 
-- ANY TABLE : 모든 테이블을 조회할 수 있도록 권한 부여
GRANT SELECT ON EMP TO DEVADMIN;
GRANT SELECT ON DEP TO DEVADMIN;
GRANT SELECT ON JOB TO DEVADMIN;

REVOKE SELECT ANY TABLE FROM DEVADMIN;

SELECT * FROM EMP;
SELECT * FROM DEP;
SELECT * FROM JOB; -- 조회 권한이 있어도 비공개 되어 있어서 보이지 않음

/*
 * ORACLE 은 "SELECT ANY TABEL" 권한만 있으면
 *   SELECT * FROM 사용자명.테이블명;
 * 형식으로 다른 계정의 테이블에 접근 가능.
 * 
 * 다른 계정의 테이블에 접근하지 못하게 하고, 테이블 이름도 다른 이름으로 저장하여 
 * 실제 테이블 이름을 알 수 없게 만들기 위해 SYNONYM 로 별칭을 부여
 * 
 * SYNONYM 을 만들 때 기본이 PRIVATE 이며, 이를 다른계정이 사용할 수 있게 
 * PUBLIC 으로 생성하려면 관리자 권한을 가지는 계정으로 PUBLIC SYNONYM 을 생성하고 
 * 만들어진 SYSNONMY 에 대한 SELECT 권한만을 부여하기 위해 "SELECT ON SYSNONYM명"으로 권한을 부여
 * 
 * SYNONYM은 보안을 높여주기 위해서 사용
 */