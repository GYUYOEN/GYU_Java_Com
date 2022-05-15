/*
 * 김규연
 * 1991년 3월 1일과 1997년 02월 20일 사이에 입사한 사원이름, 급여, 입사일을 출력하시오.
 * 컬럼명은 한글로 별칭을 부여하시오.
 * (사원의 이름은 두 이름(LAST_NAME, FIRST_NAME)을 합쳐서 출력되도록 하시오.)
 */

SELECT LAST_NAME || ' ' || FIRST_NAME 사원이름
	 , SALARY 급여
	 , HIRE_DATE 입사일
  FROM EMPLOYEES
 WHERE HIRE_DATE BETWEEN '91.03.01' AND '97.02.20';