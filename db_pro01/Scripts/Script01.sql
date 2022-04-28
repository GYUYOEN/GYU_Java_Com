SELECT 'Hello Local, Docker' FROM DUAL;

/*
 * 아래의 작업은 관리자 권한이 있는 계정을 사용해야 합니다.
 * 사용자(User)가 system 계정을 사용 중
 * 
 * 위의 계정은 관리자 계정이다.
 * 
 * 일반 사용자 계정을 새로 만들어 관리자 계정과 분리.
 */

-- 계정과 패스워드 생성하는 방법
CREATE USER puser1 IDENTIFIED BY puser1;

-- 생성한 계정에 권한 부여
-- CONNECT : 접속과 관련된 묶음, 
-- RESOURCE : 데이터베이스의 자원을 사용할 수 있게 해줌
GRANT RESOURCE, CONNECT TO puser1;
GRANT INSERT ANY TABLE, UPDATE ANY TABLE
	, DELETE ANY TABLE, CREATE VIEW
	TO puser1;

-- 테이블스페이스 사용 권한 부여
ALTER USER puser1 quota 10M ON USERS;
-- 계정 목록 확인
SELECT USERNAME FROM ALL_USERS;

-- 계정에 부여한 권한 확인 (관리자 계정으로 확인 바람)
SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE = 'PUSER1';

-- 계정에 부여한 롤(Role) 확인 (관리자 계정으로 확인 바람)
SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'PUSER1';