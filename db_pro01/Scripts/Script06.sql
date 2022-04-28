SELECT 10 * 0 AS COL1
  FROM DUAL;
  
SELECT 10 * NULL AS COL1
  FROM DUAL;
 
-- NULL이면 오른쪽 값 출력 
SELECT 10 * NVL(NULL, 0) AS COL1
  FROM DUAL;

 -- DECODE(표현식, 조건1, 결과1, 조건2, 결과2, ..., DEFAULT)
 -- 범위보다는 특정 값일 때 사용
SELECT COUNTRY_ID
	 , COUNTRY_NAME
	 , DECODE(COUNTRY_ID, 'IT', '이테리', 'JP', '일본', 'US', '미국', 'CA', '캐나다', COUNTRY_NAME) AS COUNTRY_KOR
  FROM COUNTRIES;
 
-- CASE WHEN 조건1 THEN 결과1
--		WHEN 조건2 THEN 결과2 ... 
-- 		ELSE 결과N
-- END
-- 범위일때 사용
SELECT EMPLOYEE_ID
	 , FIRST_NAME
	 , LAST_NAME
	 , CASE WHEN SALARY >= 1000 AND SALARY <= 3000 THEN '하위소득'
			WHEN SALARY >= 3000 AND SALARY <= 6000 THEN '중위소득'
			WHEN SALARY >= 6000 AND SALARY <= 10000 THEN '고소득'
			ELSE '미분류'
	   END AS "소득분류"
  FROM EMPLOYEES;

-- 합계
SELECT SUM(SALARY)
	 , SUM(COMMISSION_PCT)
  FROM EMPLOYEES;

-- 평균
SELECT AVG(SALARY)
  FROM EMPLOYEES;
 
-- 주의사항 : NULL 데이터가 포함되어 있는 경우
SELECT AVG(COMMISSION_PCT)
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NOT NULL;

-- 최소값 
SELECT MIN(COMMISSION_PCT)
	 , MIN(HIRE_DATE)
	 -- 글자길이의 최소값
	 , MIN(FIRST_NAME)
  FROM EMPLOYEES;

-- 최대값
SELECT MAX(COMMISSION_PCT)
	 , MAX(HIRE_DATE)
	 -- 글자길이의 최대값
	 , MAX(FIRST_NAME)
  FROM EMPLOYEES;
  
-- 총 개수
SELECT COUNT(*)
  FROM EMPLOYEES
 -- COMMISSION_PCT가 NULL인 총 개수
 WHERE COMMISSION_PCT IS NULL;