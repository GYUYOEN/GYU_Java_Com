/*
 * HR 데이터 정보 확인
 */

-- 직원 테이블
SELECT * FROM EMPLOYEES;
-- SELECT에 명시한 것만 출력됨
SELECT  EMPLOYEE_ID, FIRST_NAME, LAST_NAME FROM EMPLOYEES;
-- EMPLOYEE_ID가 100인 것만 조회, 100이 아닌것만 조회 : != or <>
SELECT  EMPLOYEE_ID, FIRST_NAME, LAST_NAME FROM EMPLOYEES WHERE EMPLOYEE_ID = 100;
SELECT  EMPLOYEE_ID, FIRST_NAME, LAST_NAME FROM EMPLOYEES WHERE EMPLOYEE_ID > 100;
SELECT  EMPLOYEE_ID, FIRST_NAME, LAST_NAME FROM EMPLOYEES WHERE EMPLOYEE_ID != 100;
SELECT  EMPLOYEE_ID, FIRST_NAME, LAST_NAME FROM EMPLOYEES WHERE EMPLOYEE_ID <> 100;

-- 부서 테이블
SELECT * FROM DEPARTMENTS;
SELECT  DEPARTMENT_ID, DEPARTMENT_NAME FROM DEPARTMENTS;

-- 직급 테이블
SELECT * FROM JOBS;

-- 국가 테이블
SELECT * FROM COUNTRIES;

-- 국가의 지역 테이블
SELECT * FROM LOCATIONS;

-- 대륙 정보 테이블
SELECT * FROM REGIONS;




-- 컬럼명에 별칭 부여
-- (AS 생략가능, "" 생략가능 하지만 띄어쓰기 있을 경우 반드시 작성)
SELECT EMPLOYEE_ID AS "사번"
	 , FIRST_NAME "이름"
	 , LAST_NAME 성
	 , EMAIL "이메일 주소"
FROM EMPLOYEES;

-- 컬럼과의 연산(문자열과의 결합)
SELECT EMPLOYEE_ID "사번"
	 , LAST_NAME || ' ' || FIRST_NAME "성명"
	 , SALARY * 12 || '달러' "연봉"
FROM EMPLOYEES;

-- 조건절
-- 별칭을 바꿔도 EMPLOYEES로 츨력
-- 실행순서 : FROM > WHERE > SELECT 
-- 3
SELECT EMPLOYEE_ID "사번"
	, FIRST_NAME
	, LAST_NAME
	, EMAIL
	, PHONE_NUMBER
	, HIRE_DATE
	, JOB_ID
	, SALARY
	, COMMISSION_PCT
	, MANAGER_ID
	, DEPARTMENT_ID
-- 1
FROM EMPLOYEES
-- 200 보다 큰거만 호출
-- 2
WHERE EMPLOYEE_ID > 200;

-- 조건절에서 사용하는 연산자
-- AND, OR, IN, NOT, NOT IN, BETWEEN ... AND, 
-- LIKE, IS NULL, IS NOT NULL
SELECT * FROM EMPLOYEES
 WHERE EMPLOYEE_ID > 200
-- 교집합
 	AND SALARY >= 10000;
 
SELECT * FROM EMPLOYEES
 WHERE EMPLOYEE_ID > 200
-- 합집합
 	OR SALARY >= 10000;
 
SELECT * FROM EMPLOYEES
-- 200보다 큰 사원이 아닌 것
-- 연산자 우선순위 NOT > AND > OR, 아니면 소괄호 사용
 WHERE NOT EMPLOYEE_ID > 200
	AND SALARY >= 10000;

SELECT * FROM EMPLOYEES
-- 소괄호에 포함되는 값만 출력
 WHERE EMPLOYEE_ID IN (200, 201, 202, 203);

SELECT * FROM EMPLOYEES
-- 소괄호에 포함하지 않는 값 출력
 WHERE EMPLOYEE_ID NOT IN (200, 201, 202, 203);
 	
SELECT * FROM EMPLOYEES
-- 사이에 해당하는 값 출력
 WHERE  EMPLOYEE_ID BETWEEN 200 AND 203;

SELECT * FROM EMPLOYEES
-- 사이에 해당하지 않는 값 출력
 WHERE  EMPLOYEE_ID NOT BETWEEN 200 AND 203;

-- LIKE : 특정 문자열에 대한 범위 출력
SELECT * FROM EMPLOYEES
-- AD라는 문자열이 포함된 거 출력(% 가 뒤에 붙은 경우 : AD로 시작하는 문자열 출력)
 WHERE JOB_ID LIKE 'AD%';

SELECT * FROM EMPLOYEES
-- % 가 앞에 붙은 경우 : VP로 끝나는 문자열 출력
 WHERE JOB_ID LIKE '%VP';

SELECT * FROM EMPLOYEES
-- % 가 앞,뒤에 붙은 경우 : A를 포함하고 있는 문자열 출력
 WHERE JOB_ID LIKE '%A%'
 
SELECT * FROM EMPLOYEES
-- _ : 자릿수 지정, 자릿수에 해당하는 문자열 출력(3.3.4)
 WHERE PHONE_NUMBER LIKE '___.___.____';

-- % : 자릿수 지정 X
-- _ : 자릿수 지정 O

SELECT * FROM EMPLOYEES
 WHERE PHONE_NUMBER LIKE '___.127.____';
 
SELECT * FROM EMPLOYEES
-- # : _라는 문자를 자릿수 지정할 때말고 _가 포함된 문자열을 출력하고 싶을 때 사용
-- #_(1) + ___(3) -> 언더바 총 4개
-- -> _OOO에 해당하는 문자 출력
 WHERE JOB_ID LIKE '%#____' ESCAPE  '#';

-- # 말고도 ESCAPE 문자를 지정해주면 어떤 문자로도 사용 가능
SELECT * FROM EMPLOYEES
 WHERE JOB_ID LIKE '%\____' ESCAPE  '\';

SELECT * FROM EMPLOYEES
 WHERE JOB_ID LIKE '%&____' ESCAPE  '&';

SELECT EMPLOYEE_ID
	, FIRST_NAME
	, LAST_NAME
	, COMMISSION_PCT
 FROM EMPLOYEES
-- COMMISSION_PCT가 NULL 인 것 출력
-- IS 말고 =을 사용하면 조회 불가능
 WHERE COMMISSION_PCT IS NULL;
 
SELECT EMPLOYEE_ID
	, FIRST_NAME
	, LAST_NAME
	, COMMISSION_PCT
 FROM EMPLOYEES
-- COMMISSION_PCT가 NULL 이 아닌 것 출력
-- IS 말고 != 나 <>를 사용하면 조회 불가능
 WHERE COMMISSION_PCT IS NOT NULL;

