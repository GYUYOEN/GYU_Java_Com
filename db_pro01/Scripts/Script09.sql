/*
 * 집합 연산자 : 2개 이상의 결과셋을 하나의 결과셋으로 묶어서 연산하는 연산자
 * 		- UNION		: 합집합(중복 제외)
 * 		- UNION	ALL	: 합집합(중복 허용)
 * 		- INTERSECT	: 교집합
 * 		- MINUS		: 차집합
 */
SELECT *
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID = 100
INTERSECT
SELECT *
  FROM EMPLOYEES
 WHERE SALARY = 9000;

SELECT DEPARTMENT_ID
     , NULL AS JOB_ID
     , COUNT(*)
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY DEPARTMENT_ID
UNION ALL
SELECT NULL AS DEPARTMENT_ID
     , JOB_ID
     , COUNT(*)
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY JOB_ID;

SELECT DEPARTMENT_ID
	 , JOB_ID
	 , COUNT(*)
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY GROUPING SETS(DEPARTMENT_ID, JOB_ID)
 ORDER BY 1;
