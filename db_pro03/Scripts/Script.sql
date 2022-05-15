CREATE TABLE accounts(
	   userid		VARCHAR2(20)	PRIMARY KEY
	 , userpw		VARCHAR2(20)
	 , username		VARCHAR2(20)
	 , gender		CHAR(1)
	 , age			NUMBER
	 , createDate	DATE
);

DROP TABLE accounts;

SELECT * FROM accounts;
