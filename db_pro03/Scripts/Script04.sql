/*
 * 프로시져에 매개변수를 사용하는 방법\
 */
-- IN : 외부에서 들어온 데이터를 내부에서 사용할 수 있도록 함(입력)
CREATE OR REPLACE PROCEDURE PROC_PARAM(x IN NUMBER, y IN VARCHAR2)
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('x -> ' || x);
    DBMS_OUTPUT.PUT_LINE('y -> ' || y);
END;

BEGIN
    PROC_PARAM(10, 'Hello');
END;


/*
 * 프로시져로 실행한 결과를 리턴 받는 방법
 *     - 프로시져는 함수와 유사하나 함수 처럼 결과를 리턴시키는 기능은 없다.
 * 		 (리턴이 없는 함수)
 */
-- OUT : 내부에서 작업한 내용을 외부에 저장(출력), 
-- 외부에 연결된 y(OUT : 출력), 내부에 사용될 x(IN : 입력)
CREATE OR REPLACE PROCEDURE PROC_PARAM(x IN NUMBER, y OUT VARCHAR2)
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('x -> ' || x);
    y := 'Done!';
END;

-- x1과 프로시저의 y를 연결시켜줌(바인드)
-- 참조주소가 같음 -> 외부에서 수정하든 내부에서 수정하든 같은 수정값이 나옴
DECLARE
    x1 VARCHAR2(10);
BEGIN
    PROC_PARAM(10, x1);
    DBMS_OUTPUT.PUT_LINE('x1 -> ' || x1);
END;

-- SQL의 경우 OUT(출력)할 때
-- VARIABLE x1 VARCHAR2(10);
-- EXEC PROC_PARAM(10, :x1); ":" 를 써줘야함

-- SET SERVEROUTPUT ON;
-- EXEC PROC_PARAM(10, :x1); : x -> 10 만 출력됨
-- PRINT x1; : x1 -> Done! 출력(OUT은 PRINT로 출력)








DROP TABLE 재고입출고;
DROP TABLE 재고관리;

-- '재고입출고'와 '재고관리'는 서로 관계가 없음
CREATE TABLE 재고입출고(
       ID    NUMBER        CONSTRAINT PK_재고입출고_ID PRIMARY KEY
     , PNAME VARCHAR2(100) CONSTRAINT NN_재고입출고_PNAME NOT NULL
     , PTYPE CHAR(1)       CONSTRAINT CK_재고입출고_PTYPE CHECK(PTYPE IN ('I', 'O'))
     , CNT   NUMBER        DEFAULT(1) CONSTRAINT NN_재고입출고_CNT NOT NULL
     , PDATE DATE          CONSTRAINT NN_재고입출고_PDATE NOT NULL
);

CREATE SEQUENCE SEQ_재고번호 NOCACHE;
DROP SEQUENCE SEQ_재고번호;

CREATE TABLE 재고관리(
       ID    NUMBER        CONSTRAINT PK_재고관리_ID    PRIMARY KEY
     , PNAME VARCHAR2(100) CONSTRAINT NN_재고관리_PNAME NOT NULL
     , CNT   NUMBER        CONSTRAINT NN_재고관리_CNT   NOT NULL
);

CREATE SEQUENCE SEQ_재고관리번호 NOCACHE;
DROP SEQUENCE SEQ_재고관리번호;
/*
 * 제품명, 입고/출고 타입, 수량, 날짜 정보를 전달하면 재고입출고 테이블에
 * 해당 정보가 추가 되며, 재고관리 테이블에도 동일한 정보를 추가한다
 * 단, 이미 등록된 제품의 경우 수량만 변경하도록 한다.
 * 
 * 만약 출고인 경우 재고관리에 동일한 제품에 대한 수량이 부족한 경우 출고가 안되게
 * 해야 한다.
 */

CREATE OR REPLACE PROCEDURE PROC_재고입출등록(
	 -- 입력받는 용도
       name IN VARCHAR2
     , in_out IN VARCHAR2
     , inout_cnt IN NUMBER
     , inout_date IN DATE
     , res OUT NUMBER
)
IS
	-- 변수를 지정
	-- UPPER(in_out) : 소문자로 출력했을 경우 대문자로 나오게 해줌
	io_type       VARCHAR2(1) := UPPER(in_out);
    row_data      재고관리%ROWTYPE;
   	row_cnt 	  NUMBER;
    invalid_type  EXCEPTION;
    no_out_data   EXCEPTION;
    not_enough_count EXCEPTION;
BEGIN
	-- EXCEPTION
	IF io_type NOT IN ('I', 'O') THEN
	    RAISE invalid_type;
	END IF;
	
	-- 입출고인지 확인
	IF io_type = 'O' THEN 
		-- 출고면 기존의 제고가 있는지 확인
		SELECT COUNT(*)
     	  INTO row_cnt
     	  FROM 재고관리 
   	 	 WHERE PNAME = name;
         
        -- 재고가 있으면 입출고 등록하고 제고관리에서 빼줌
        IF row_cnt != 0 THEN
         	INSERT INTO 재고입출고 VALUES(SEQ_재고번호.NEXTVAL, name, io_type, inout_cnt, inout_date);
         
            SELECT ID, PNAME, CNT
              INTO row_data
              FROM 재고관리
             WHERE PNAME = name;
            
         	UPDATE 재고관리
    		   SET CNT = CNT - inout_cnt
    		 WHERE ID = row_data.ID;
    		
    		-- EXCEPTION
    		SELECT CNT
              INTO row_cnt
              FROM 재고관리
             WHERE ID = row_data.ID;
            
            IF row_cnt < 0 THEN
                RAISE not_enough_count;
            END IF;
        -- EXCEPTION
    	ELSE
    		RAISE no_out_data;
        END IF;
    ELSE -- 입고이면
    	-- 수량만 확인하기 위한 테이블
		-- 동일한 제품이 들어오면 CNT값만 바뀌도록 UPDATE
		-- SELECT 한 결과를 INTO에 저장
		-- 재고관리에서 WHERE PNAME = name에 해당하는 행 갯수를 row_cnt에 저장 
    
    	-- 재고관리에 동일한 재품이 있는지 확인
        SELECT COUNT(*)
      	  INTO row_cnt
     	  FROM 재고관리 WHERE PNAME = name;
		
     	-- 입고라서 무조건 추가
     	INSERT INTO 재고입출고 VALUES(SEQ_재고번호.NEXTVAL, name, io_type, inout_cnt, inout_date);
	
     	-- 재고관리에 데이터가 없는 경우 추가
		IF row_cnt = 0 THEN
			INSERT INTO 재고관리 VALUES(SEQ_재고관리번호.NEXTVAL, name, inout_cnt);
		ELSE -- 동일한 데이터가 있는 경우 UPDATE
            SELECT ID, PNAME, CNT
              INTO row_data
              FROM 재고관리
             WHERE PNAME = name;
            
		    UPDATE 재고관리
    		   SET CNT = CNT + inout_cnt
    		 WHERE ID = row_data.ID;
		END IF;
	END IF;
	res := 1;
	COMMIT;
EXCEPTION
    WHEN invalid_type THEN
        DBMS_OUTPUT.PUT_LINE('입출력 타입을 잘못 지정하였습니다. (''I'' 또는 ''O'')');
    WHEN no_out_data THEN
        DBMS_OUTPUT.PUT_LINE('출고 할 상품이 없습니다. 또는 수량이 부족합니다.');
    WHEN not_enough_count THEN
        DBMS_OUTPUT.PUT_LINE('출고 수량이 부족합니다.');
        ROLLBACK;
	-- ROLLBACK시 수정해도 값이 그대로 이어야함
    -- 입출고내역도 그대로 이어야함
END;

SELECT * FROM USER_ERRORS;

DECLARE
    res NUMBER;
BEGIN
    PROC_재고입출등록('제품A', 'i', 10, TO_DATE(20220502), res);
    DBMS_OUTPUT.PUT_LINE('실행 결과: ' || res);
END;

DECLARE
    res NUMBER;
BEGIN
    PROC_재고입출등록('제품B', 'o', 45, TO_DATE(20220502), res);
    DBMS_OUTPUT.PUT_LINE('실행 결과: ' || res);
END;

SELECT * FROM 재고입출고;
SELECT * FROM 재고관리;

DELETE FROM 재고입출고;
DELETE FROM 재고관리;