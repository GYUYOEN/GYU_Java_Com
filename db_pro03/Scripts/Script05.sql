-- 인덱스 : 검색을 최적화하기 위해 사용하는 ORACLE 객체
-- 		  인덱스를 위한 추가 공간이 필요하며, 검색 속도는 향상시키지만 데이터의 변경
-- 		  작업이 자주 일어나는 경우에는 전체적인 성능이 저하될 수 있다.

-- 테이블을 조회할때 예상 계획이 나옴(왼쪽 맨 밑(실행 계획 보기) 아이콘)

-- PRIMARY KEY가 없는 테이블
-- SELECT * FROM USER_MOCK_DATA_ORIGIN WHERER ID = 10000;
-- 실행 계획 FULL : 있을때까지 전체 확인 -> 비효휼적 
-- -> 이때 사용하는 게 INDEX : 검색속도는 빨라지지만 별도의 저장공간 필요
-- 책에서 목차라고 생각하면됨
-- PRIMARY KEY, UNIQUE 사용하면 INDEX 자동으로 부여됨

-- PRIMARY KEY가 있는 테이블
-- SELECT * FROM USER_MOCK_DATA WHERER ID = 10000;
-- 실행 계획 해보면 cost가 확 줄어듬 : INDEX로 스캔해서(FULL X)

-- INDEX가 부여된 정보 확인
-- SELECT * FROM USER_INDEXES;
-- 해당 인덱스로 묶여 있는 컬럼확인 가능
-- SELECT * FROM USER_IND_COLUMNS;

-- 고유 인덱스 : 중복 X, PK에 자동부여
-- 비고유 인덱스 : 중복 O, 일반 컬럼에 설정

-- 고유 인덱스 만드는 법 : 중복이 있으면 안됨
-- CREATE UNIQUE INDEX IDX_USER_MOCK_DATA_ID ON USER_MOCK_DATA(ID);

-- 비고유 인덱스 만드는 법
-- IDX_USER_MOCK_DATA_FIRST_NAME : INDEX명, 왠만하면 테이블명 + 컬렴명
-- CREATE INDEX IDX_USER_MOCK_DATA_FIRST_NAME ON USER_MOCK_DATA(FIRST_NAME);

-- 결합 인덱스 : 두개 이상 컬럼을 묶을수 있음
-- CREATE INDEX IDX_USER_MOCK_DATA_ID_FIRST_NAME ON USER_MOCK_DATA(ID, FIRST_NAME);
-- SELECT * FROM USER_MOCK_DATA WHERE ID = 14936 AND FIRST_NAME = 'Jone';
-- SELECT * FROM USER_MOCK_DATA_ORIGIN WHERE ID = 14936 AND FIRST_NAME = 'Jone';

-- 함수기반 인덱스 : 함수 또는 계산식 사용

-- INDEX 삭제
-- DROP INDEX IDX_USER_MOCK_DATA_FIRST_NAME;

-- INDEX 다시 부여, 재조정
-- ALTER INDEX IDX_USER_MOCK_DATA_ORI_FIRST REBUILD;

-- INSERT, UPDATE, DELET : 인덱스 생성 작업 추가 -> 속도가 오히려 느려짐 
