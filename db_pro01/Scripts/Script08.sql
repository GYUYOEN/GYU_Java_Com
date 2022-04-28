/*
 * 모든 컬렴은 한글로 별칭을 부여하여 조회한다.
 * FIRST_NAME 과 LAST_NAME 을 하나의 컬럼(이름)으로 만들어서 조회되도록 한다.
 * PHONE_NUMBER 에서 사용한 구분자 . 은 -으로 변경하여 조회 하도록 한다.
 * EMAIL 주소는 @example.com을 추가로 덧붙여서 조회되도록 한다.(소문자로만 나오게 한다.)
 * HIRE_DATE 는 YYYY년 MM월 DD일 형식으로 조회되도록 하며, 추가로 입사일부터 현재일까지의 근년일수가 계산되어
 * 조회되도록 한다.
 * SALARY는 원화 단위로 변환시켜 조회하며, COMMISSION_PCT 가 있는 경우 이를 계산한 SALARY 금액이 나오게 한다.
 * 추가로 100원 단위는 절사한다.
 * 입사일을 기준으로 오름차순 정력하여 조회하도록 한다.
 */
SELECT EMPLOYEE_ID 
	 , CONCAT(FIRST_NAME, CONCAT(' ', LAST_NAME)) "이름"
	 , FIRST_NAME || ' ' || LAST_NAME "이름"
	 , REPLACE(PHONE_NUMBER, '.', '-') "전화번호"
	 , CONCAT(LOWER(EMAIL), '@example.com')"이메일"
	 , TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일"')
	 , FLOOR(SYSDATE - HIRE_DATE) "근속일"
	 , FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) "근속월"
	 , FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) "근속년"
	 , TRUNC(SALARY * 1262 * (1 + NVL(COMMISSION_PCT, 0)), -3) "급여"
  FROM EMPLOYEES
 ORDER BY HIRE_DATE;
 
/*
 * 전화번호 회신을 집계하기 위한 조회 쿼리를 만드시오.
 * 		- 전화번호 회신은 515, 500, 650, 011, 603 별로 구분하여 얼마나 사용되고 있는지 조회하도록 한다.
 * 		- 번호별 회신 수에 추가로 전채 회신 수가 조회될 수 있도록 한다.
 */
SELECT NVL(SUBSTR(PHONE_NUMBER, 1, 3), '총합') "회신번호"
	 , COUNT(*) "회신수"
  FROM EMPLOYEES
 GROUP BY ROLLUP(SUBSTR(PHONE_NUMBER, 1, 3));
 
/*
 * MANAGER_ID 는 해당 EMPLOYEE_ID 를 관리하는 관리자 정보가 연결되어 있는 정보이다.
 * 		- 한 명의 관리자가 얼마나 많은 직원을 관리하고 있는지를 알 수 있도록 조회 쿼리를 작성한다.
 * 		- MANAGER_ID 가 NULL 인 경우는 제외하여 조회하도록 한다.
 */
SELECT MANAGER_ID "관리자ID"
	 , COUNT(*) "인원수"
  FROM EMPLOYEES
 WHERE MANAGER_ID IS NOT NULL
 GROUP BY MANAGER_ID;