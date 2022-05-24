/*
 * 함수(Function)
 * 별도의 크기지정 X
 * RETURN 타입이 있음
 */
CREATE OR REPLACE FUNCTION FUNC_test1(v1 NUMBER) RETURN NUMBER
IS
BEGIN
	RETURN v1 * 10;
END;

-- SELECT CONCAT('Hello', 'Function') FROM DUAL;
SELECT FUNC_test1(2) FROM DUAL;


CREATE OR REPLACE FUNCTION FUNC_test2(v1 VARCHAR2) RETURN VARCHAR2
IS
BEGIN
    RETURN 'Result: ' || v1;
END;

SELECT FUNC_test2('Hello') FROM DUAL;

CREATE OR REPLACE FUNCTION FUNC_test3(v1 VARCHAR2) RETURN VARCHAR2
IS
	-- IS 안에서는 크기 지정 해줘야 함
    x    NUMBER := 1;
    y    VARCHAR2(10) := 'Hello';
BEGIN
    -- 로직
	-- 값을 전달하면 값을 통해 로직에 계산된 걀과가 나올수 있게끔
    RETURN 'Result: ' || v1 || x || y;
END;

SELECT FUNC_test3('Hello') FROM DUAL;