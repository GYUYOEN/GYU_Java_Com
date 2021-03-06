package com.conn.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class DBConn {
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static String BASE_URL = "jdbc:oracle:thin:@";
	
	private String url_address;
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	// 파일이 잘 읽히는지 확인
	// 파일 읽기을 읽어서 데이터베이스와 연결하는 작업
	public DBConn(File config) throws Exception {
			Map<String, String> map = new HashMap<String,String>();
			// StringBuilder sb = new StringBuilder();
			// FileReader fr = new FileReader(config);
			BufferedReader br = new BufferedReader(new FileReader(config)); // 보조스트림을 이용하여 줄단위로 분류
			while(br.ready()) {
				// sb.append((char)fr.read());
				String[] keyValue = br.readLine().split("=");
				map.put(keyValue[0].strip(), keyValue[1].strip()); // "=" 앞뒤 공백 삭제
			}
			if(map.get("host") != null) {
				url_address = String.format("%s:%s/%s", map.get("host"), map.get("port"), map.get("service"));			
			} else if(map.get("tns_alias") != null) {
				url_address = String.format("%s?TNS_ADMIN=%s", map.get("tns_alias"), map.get("wallet_path"));
			} else {
				System.out.println("DB 연결 파일 구성이 잘못되었습니다.");
			}
			this.initConnect(map.get("username"), map.get("password"));
			// String[] lines = sb.toString.split("\r\n"); // 문자열로 변환, 열로 분류
			//System.out.println(lines[0]); // 분류된 열 출력
			//System.out.println(lines[1]);
			//System.out.println(lines[2]);
	}
	
//	// 파일이 잘 읽히는지 확인
//	public static void main(String[] args) {
//		String homePath = System.getProperty("user.home");
//		DBConn db = new DBConn(new File(homePath + "/oracle_db.conf"));
//	}
	
	// 로컬 or 도커에 연결하는 형식
	public DBConn(String domain, String port, String serviceName, String username, String password) throws Exception {
		url_address = String.format("%s:%s/%s", domain, port, serviceName);
		this.initConnect(username, password);
	}
	
	// oracle cloud에 연결하는 형식
	public DBConn(String tnsAlias, String walletPath, String username, String password) throws Exception {
		url_address = String.format("%s?TNS_ADMIN=%s", tnsAlias, walletPath);
		this.initConnect(username, password);
	}
	
	private void initConnect(String username, String password) throws Exception {
		// 1. Driver 등록
		Class.forName(DRIVER_NAME);
		
		// 2. DBMS 연결
		conn = DriverManager.getConnection(BASE_URL + url_address, username, password);
		// true : 자동커밋 사용 , false : 자동커밋 사용x (기본상테 : true)
		conn.setAutoCommit(false);
		
		// 3. Statement 생성 -> PreparedStatement 로 변경함
//		stat = conn.createStatement();
	}
	
	public PreparedStatement getPstat(String sql) throws Exception {
		pstat = conn.prepareStatement(sql);
		return pstat;
	}
	
	public ResultSet sendSelectQuery() throws Exception {
//		ResultSet rs = this.stat.executeQuery(sql);
		ResultSet rs = this.pstat.executeQuery();
		return rs; // 다양하게 활용하기 위해 반환(반환안하면 한가지 형태로만 사용가능)
	}
	
	// 행에 대한 업데이트(n행이 반영되었습니다) -> int(INSERT, DELETE도 같음)
	public int sendUpdateQuery() throws Exception {
//		int rs = this.stat.executeUpdate(sql);
		int rs = this.pstat.executeUpdate();
		return rs;
	}
	
	public int sendInsertQuery() throws Exception {
//		int rs = this.stat.executeUpdate(sql);
		int rs = this.pstat.executeUpdate();
		return rs;
	}
	
	public int sendDeleteQuery() throws Exception {
//		int rs = this.stat.executeUpdate();
		int rs = this.pstat.executeUpdate();
		return rs;
	}
	
	public void commit() throws Exception {
		this.conn.commit();
	}
	
	public void rollback() throws Exception {
		this.conn.rollback();
	}
	
	public void close() throws Exception {
		// 5. 연결 해제
//		this.stat.close();
		this.pstat.close();
		this.conn.close();
	}

//	public static void main(String[] args) throws Exception {
//		// 로컬, 도커의 경우
//		DBConn myDB = new DBConn("localhost", "1521", "XEPDB1", "puser1", "puser1");
//		// oracle cloud의 경우
//		DBConn myDB = new DBConn("db202204211241_medium", "C:\\Users\\user1\\eclipse\\oracle\\Wallet_DB202204211241"
//				, "puser1", "Database1234");
//		// -> 여기까지만 하면 "Statement 생성" 까지 다 실행됨 
//	
//		int rowCount = myDB.sendInsertQuery("INSERT INTO DEPARTMENTS VALUES(280, 'Dept Test', NULL, 1700)");
//		int rowCount = myDB.sendUpdateQuery("UPDATE DEPARTMENTS SET DEPARTMENT_NAME = 'Tester' WHERE DEPARTMENT_ID = 280");
//		int rowCount = myDB.sendDeleteQuery("DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = 280");
//		System.out.println(rowCount + " 개 행이 반영되었습니다.");
//		
		// DEPARTMENTS 모두 조회
//		ResultSet rs = myDB.sendSelectQuery("SELECT * FROM DEPARTMENTS");
//		// next가 있으면(true면) 커서가 움직임(행데이터 이동(다음행으로 이동))
		// 거짓이 나올때까지 반복
//		while(rs.next()) {
//			System.out.print(rs.getInt("DEPARTMENT_ID") + "\t");
//			System.out.print(rs.getString("DEPARTMENT_NAME") + "\t");
//			System.out.print(rs.getInt("MANAGER_ID") + "\t");
//			System.out.print(rs.getInt("LOCATION_ID") + "\n");
//		}
		// rollback을 하면 자바에서는 수정된게 보이는데 DBeaver에서는 안보여짐
//		// myDB.rollback();
//		// commit을 하면 뜸
//		myDB.commit(); 
//		// 아무것도 안쓰면 자동 커밋	
//		
//		rs.close();
//		myDB.close();
//	}
	
	
	// 로컬, 도커 계정의 경우에만 사용가능
//	public void localConnect() throws Exception {
//		// 1. Driver 등록
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		
//		// 2. DBMS 연결
//		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "puser1", "puser1");
//		
//		// 3. Statement 생성
//		Statement stat = conn.createStatement();
//		
//		// 4. SQL 전송 후 결과 받기
//		ResultSet rs = stat.executeQuery("SELECT 'Hello' FROM DUAL"); // 세미콜론 넣으면 안됨
		// 다음 커서가 있냐없냐 확인
//		if(rs.next()) {
//			System.out.println(rs.getString(1)); // 1 = "Hello"
//		}
//		
//		// 5. 연결 해제
//		rs.close();
//		stat.close();
//		conn.close();
//	}
	
//	// 파일이 잘 읽히는지 확인
//	// 파일 읽기을 읽어서 데이터베이스와 연결하는 작업
//	public DBConn(File config) {
//			Map<String, String> map = new HashMap<String,String>();
//			// StringBuilder sb = new StringBuilder();
			// FileReader fr = new FileReader(config);
//			BufferedReader br = new BufferedReader(new FileReader(config)); // 보조스트림을 이용하여 줄단위로 분류
//			while(br.ready()) {
//				// sb.append((char)fr.read());
//				String[] keyValue = br.readLine().split("=");
//				map.put(keyValue[0].strip(), keyValue[1].strip()); // "=" 앞뒤 공백 삭제
//			}
//			url_address = String.format("%s:%s/%s", map.get("host"), map.get("port"), map.get("service"));
//			this.initConnect(map.get("username"), map.get("password"));
//			// String[] lines = sb.toString.split("\r\n"); // 문자열로 변환, 열로 분류
//			//System.out.println(lines[0]); // 분류된 열 출력
//			//System.out.println(lines[1]);
//			//System.out.println(lines[2]);
//	}
	
//	// 파일이 잘 읽히는지 확인
//	public static void main(String[] args) {
//		String homePath = System.getProperty("user.home");
//		DBConn db = new DBConn(new File(homePath + "/oracle_db.conf"));
//	}
//	
//	public static void main(String[] args) throws Exception {
//		DBConn myDB = new DBConn();
//		myDB.localConnect();
//	}

}
