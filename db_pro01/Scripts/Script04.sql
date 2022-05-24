
SELECT FIRST_NAME
	 , LENGTH(FIRST_NAME) AS "길이"
  FROM EMPLOYEES;
  
-- DUAL : 조회할 데이터 없이 출력 가능 -> 함수에 대한 테스트 용도으로 많이 사용
-- LENGTH : 문자열의 글자수
 SELECT LENGTH('Hello Oracle')
      , LENGTH('오라클 안녕')
   FROM DUAL;
  
-- LENGTHB : 문자열의 바이트수(한글 : 한글자에 3바이트)
 SELECT LENGTHB('Hello Oracle')
      , LENGTHB('오라클 안녕')
   FROM DUAL;
   
-- @ 가 몇번째에 있냐
-- 1 : 찾는 순서가 앞에서 부터
SELECT INSTR('sample@example@com', '@', 1)
     -- -1 : 찾는 순서가 뒤에서 부터, 뒤에 @ 번지수 출력
	 , INSTR('sample@example@com', '@', -1)
	 -- -1, 2 : 뒤에서 부터 시작해서 2번째에 해당하는 @ 번지수 출력
	 , INSTR('sample@example@com', '@', -1, 2)
  FROM DUAL;
  
-- Left Padding(왼쪽 여백) : A를 포함해서 4글자의 여백 = 총여백 3개,  
-- AB를 포함해서 4글자의 여백 = 총여백 2개...
-- Right Padding(오른쪽 여백)
SELECT '!' || LPAD('A', 4)
	 , '!' || LPAD('AB', 4)
	 , '!' || LPAD('ABC', 4)
	 -- 지정한 여백을 넘기면 4글자까지 잘려서 출력
	 , '!' || LPAD('ABCDE', 4)
	 -- 공백 말고 _ 사용하여 출력
	 , '!' || LPAD('A', 4, '_')
	 , RPAD('A', 4) || '!'
	 , RPAD('AB', 4) || '!'
	 , RPAD('ABC', 4) || '!'
	 , RPAD('ABCDE', 4) || '!'
	 -- 공백 말고 0 사용하여 출력
	 , RPAD('A', 4, '0') || '!'
  FROM DUAL;

-- 오른쪽에 지정한 문자열 제거
-- RTRIM : 오른쪽 기준, LTRIM : 왼쪽 기준
SELECT RTRIM('userId@example.com', '@example.com')
	 , LTRIM('010-1234-5678', '010-')
	 , LTRIM(RTRIM('   PASSWORD INPUT   '))
	 , TRIM('   PASSWORD INPUT   ')
	 -- 오른쪽 문자열에서 A를 제거
	 , TRIM('A' FROM 'AAAAHelloAAAA')
	 -- LEADING : 앞쪽 A 제거
	 , TRIM(LEADING 'A' FROM 'AAAAHelloAAAA')
	 -- TRAILING : 뒤쪽 A 제거
	 , TRIM(TRAILING 'A' FROM 'AAAAHelloAAAA')
	 -- BOTH : 양쪽 A 제거
	 , TRIM(BOTH 'A' FROM 'AAAAHelloAAAA')
  FROM DUAL;

-- 자바의 인덱스 번호는 0부터, 오라클의 인덱스 번호는 1부터 시작
-- 1위치에서 6글자수까지 출력 
SELECT SUBSTR('userId@example.com', 1, 6)
	 -- 8위치에서 7글자수까지 출력 
	 , SUBSTR('userId@example.com', 8, 7)
	 -- 글자수 지정 안하면 끝까지 출력
	 , SUBSTR('userId@example.com', 8)
	 -- 뒤에서 3번째 위치에서 부터 끝까지 출력
	 , SUBSTR('userId@example.com', -3)
  FROM DUAL;
  
SELECT LOWER('userId@example.com')
	 , UPPER('userId@example.com')
	 -- 단어가 구분이 되는 형태에서 첫글자만 대문자로 만들어주고 나머지는 소문자로 출력
	 , INITCAP('usrId@example.com')
  FROM DUAL;
  
-- 문자열 결합할 때 (|| 연결연산자 사용 가능)
SELECT CONCAT('Hello', ' World')
  FROM DUAL;
--               원본                   찾을 문자열        변경할 문자열  
SELECT  REPLACE('userId@example.com', '@example.com', '@example.co.kr')
  FROM DUAL;

-- 음수값을 정수값으로
SELECT ABS(10)
     , ABS(-10)
     , ABS(-10.12)
  FROM DUAL;

-- MOD : 나머지를 구함
-- 10 을 3 으로 나누었을 때 나머지
SELECT MOD(10, 3)
	 , MOD(-10, 3)
	 , MOD(10.5, 3)
	 , MOD(-10.5, 3)
  FROM DUAL;

-- 반올림
SELECT ROUND(10.4)
     , ROUND(10.5)
     , ROUND(10.45)
     -- 1 : 표현할 소수점 자릿수 출력
     , ROUND(10.45, 1)
     , ROUND(10.456, 2)
     -- -1 : 정수 일의 자릿수 반올림 하여 출력
     , ROUND(18.456, -1)
     , ROUND(278.456, -2)
  FROM DUAL;

-- FLOOR : 소수점 자리를 버림
-- (음수 : 0이랑 멀어지는 방향, 양수 : 0이랑 가까워지는 방향으로 버림처리) -> 왼쪽방향 버림
-- CEIL : 소수점 자리를 올림
-- (음수 : 0이랑 가까워지는 방향, 양수 : 0이랑 멀어지는 방향으로 올림처리) -> 오른쪽방향 올림
SELECT FLOOR(10.34)
     , FLOOR(-10.34)
     , CEIL(10.34)
	 , CEIL(-10.34)
  FROM DUAL;

-- 소수점 1번째 자리까지 절사
SELECT TRUNC(10.34, 1)
	 , TRUNC(10.34, 1)
	 , TRUNC(10.3456, 2)
	 -- 정수자릿수에 대한 절사, -1 : 1의 자릿수 절사
	 , TRUNC(1234, -1)
  FROM DUAL;

ALTER SESSION SET NLS_LANGUAGE = KOREAN;
-- 언어를 AMERICA 언어로 바꿔줌
-- ALTER SESSION SET NLS_LANGUAGE = AMERICAN;
-- '금요일' -> 'FRIDAY', '금' -> 'FRI'

-- 현재 언어가 무엇으로 설정되어 있는지 확인가능
SELECT * FROM V$NLS_PARAMETERS WHERE PARAMETER LIKE '%LANG%';

-- SYSDATE : 현재 시스템의 날짜
SELECT SYSDATE
	 -- 현재 날짜에서 3월을 더함
	 , ADD_MONTHS(SYSDATE, 3)
	 -- 현재 날짜에서 3월을 뺌
	 , ADD_MONTHS(SYSDATE, -3)
	 -- 현재 날짜를 기준으로 그 달의 마지막 날
	 , LAST_DAY(SYSDATE)
	 -- 현재 날짜를 기준으로 가장 가까운 금요일
	 , NEXT_DAY(SYSDATE, '금요일')
	 , NEXT_DAY(SYSDATE, '금')
	      -- , NEXT_DAY(SYSDATE, 'FRI')
     -- , NEXT_DAY(SYSDATE, 'FRIDAY')
	 -- 1 : 일요일, 2 : 월요일, ... 6 : 금요일, 7 : 토요일
	 , NEXT_DAY(SYSDATE, 6)
  FROM DUAL;
 
-- 해당 날짜(SYSDATE) or 시간(SYSTIMESTAMP)만 따로 뽑아냄
-- 한국 표준시간 X, UTC 기준
SELECT EXTRACT(YEAR FROM SYSDATE)
 	 , EXTRACT(MONTH FROM SYSDATE)
 	 , EXTRACT(DAY FROM SYSDATE)
 	 , EXTRACT(HOUR FROM SYSTIMESTAMP)
 	 , EXTRACT(MINUTE FROM SYSTIMESTAMP)
 	 , EXTRACT(SECOND FROM SYSTIMESTAMP)
 	 , SYSDATE
 	 , SYSTIMESTAMP
  FROM DUAL;

-- 두 날짜의 차이
-- 앞에서 뒤에거 뺌
SELECT MONTHS_BETWEEN(SYSDATE, ADD_MONTHS(SYSDATE, 3))
	 , MONTHS_BETWEEN(ADD_MONTHS(SYSDATE, 3), SYSDATE)
  FROM DUAL;

-- 날짜를 지정한 숫자씩 더하거나 뺌
SELECT  SYSDATE 
	 , SYSDATE + 1
	 , SYSDATE + 2
	 , SYSDATE - 1
	 , SYSDATE - 2
	 , SYSDATE + INTERVAL ' 1' DAY
	 , SYSDATE + INTERVAL ' 1' MONTH
	 , SYSDATE + INTERVAL ' 1' YEAR
	 , SYSDATE + INTERVAL ' 1' HOUR
	 , SYSDATE + INTERVAL ' 1' MINUTE
	 , SYSDATE + INTERVAL ' 1' SECOND
  FROM DUAL;
  
SELECT SYSTIMESTAMP
	 , SYSTIMESTAMP - 1
	 , SYSTIMESTAMP + INTERVAL '3' DAY
	 , SYSTIMESTAMP + INTERVAL '3' MONTH
	 , SYSTIMESTAMP + INTERVAL '3' YEAR
	 -- 30 초 더함
	 , SYSTIMESTAMP + INTERVAL '30' SECOND
	 -- 30 분 더함
	 , SYSTIMESTAMP + INTERVAL '30' MINUTE
	 -- 3시간 더함
	 , SYSTIMESTAMP + INTERVAL '3' HOUR
	 -- 30 초 뺌
	 , SYSTIMESTAMP - INTERVAL '30' SECOND
	 -- 30 분 뺌
	 , SYSTIMESTAMP - INTERVAL '30' MINUTE
	 -- 3시간 뺌
	 , SYSTIMESTAMP - INTERVAL '3' HOUR
  FROM DUAL;

SELECT * FROM V$NLS_PARAMETERS;
-- 통화 단위의 심볼 변경이 필요한 경우 다음의 ARTER 문을 사용
-- ￦에서 $로 바꿈
ALTER  SESSION SET NLS_CURRENCY = '$';
SELECT * FROM V$NLS_PARAMETERS WHERE PARAMETER = 'NLS_CURRENCY';
  
 SELECT 1234
	  -- 숫자를 문자로 변환
 	  , TO_CHAR(1234)
 	  -- 날짜를 오른쪽에 지정해준 형식으로 변환 : 문자열
 	  -- 날짜를 문자로 변환
 	  , TO_CHAR(SYSDATE, 'YYYYMMDD')
 	  , TO_CHAR(SYSDATE, 'YYYY/MM/DD')
 	  , TO_CHAR(SYSDATE, 'YYYY-MM-DD')
 	  , TO_CHAR(SYSDATE, 'YYYY"년" MM"월" DD"일"')
 	  -- AM : 오전, 오후 표기
 	  , TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS')
 	  -- 밀리세컨즈 표기 할 때(SYSTIMESTAMP 사용해야 함)
 	  , TO_CHAR(SYSTIMESTAMP , 'YYYY-MM-DD AM HH:MI:SS.FF3')
 	  -- HH24 : 24시로 표기
 	  , TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')
 	  -- 오른쪽 형식으로 숫자형식 변경
 	  -- 9를 사용 했을 때 : 앞에 여백이 공백으로 채워짐
 	  , TO_CHAR(1000000, '999,999,999')
 	  -- 0을 사용 했을 때 : 앞에 여백이 0으로 채워짐
 	  , TO_CHAR(1000000, '000,000,000')
 	  -- L : 원화 표시
 	  , TO_CHAR(1000000, '999,999,999L')
 	  , TO_CHAR(1000000, 'L999,999,999')
   FROM DUAL;

-- 문자열을 날짜로 변환
SELECT TO_DATE('20220425', 'YYYYMMDD')
	 -- 생략가능
	 , TO_DATE('20220425')
	 -- 숫자를 날자로 변환
	 , TO_DATE(20220425)
	 , TO_DATE('2022/04/25')
	 , TO_DATE('2022-04-25')
	 , TO_DATE('2022.04.25')
	 -- 특이한 포멧인 경우 생략 불가능 -> 포멧을 지정해 주어야함
	 , TO_DATE('2022년 04월 25일', 'YYYY"년" MM"월" DD"일"')
  FROM DUAL;

-- 문자열을 숫자로 변환
SELECT TO_NUMBER('20220425')
	 -- 콤마가 들어가 있는 경우 형식을 지정 해주어얌
	 , TO_NUMBER('20,220,425', '999,999,999')
	 -- 16 진수로 변환이 필요한경우, 앞에 있는 값 16 진수로 변환
	 , TO_NUMBER('FFFF', 'XXXX')
  FROM DUAL;
  
-- 날짜를 숫자로 변환하는 법 : 날짜를 문자로 바꾼 후 문자를 숫자로 바꿈
-- 이때 문자의 형식은 반드시 'YYYYMMDD'형식으로 지정
SELECT TO_NUMBER(TO_CHAR(SYSDATE, 'YYYYMMDD'))
  FROM DUAL;