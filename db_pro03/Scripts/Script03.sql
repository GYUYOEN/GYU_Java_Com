/*
 * 프로시져(PROCEDUAE)
 *     - PL/SQL 을 사용하여 DBMS 시스템에서 실행 할 프로그램을 만들기 위해 사용하는 객체
 *     - 반복 작업 및 복잡한 SQL 구문을 프로시져로 저장하여 재사용하는 용도로 사용될 수 있다.
 *     - DBMS 에 컴파일 된 상태로 동작하기 때문에 기존 SQL 스크립트 보다는 빠른 처리를 기대할 수 있다.
 */

CREATE OR REPLACE PROCEDURE PROC_TEST
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello PROCEDURE');
END;

-- PROCEDURE 실행
-- EXEC : SQL 클래스에서 사용되는 명령어, ORACLE(DBeaver)에서는 지원 X
-- EXEC PROC_TEST;

-- DBeaver에서 PROCEDURE 사용하는 방법
BEGIN
    PROC_TEST;
END;

-- PROCEDURE 지우는 방법
DROP PROCEDURE PROC_TEST;

-- 생성된 PROCEDURE 확인
SELECT * FROM USER_PROCEDURES;
-- ERROR가 뜬 이유를 확인(누적X, 제대로 실행되면 사라짐)
-- 로직상의 ERROR는 직접 실행해봐야함
SELECT * FROM USER_ERRORS;