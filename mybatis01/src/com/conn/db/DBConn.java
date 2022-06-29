package com.conn.db;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.data.vo.EmpComplexVO;
import com.data.vo.EmpSelectVO;
import com.data.vo.EmpVO;
import com.data.vo.TestVO;

public class DBConn {
	// MyBatis 활용 객체 생성(session)
	public static SqlSession getSqlSession() {
		SqlSession sess = null;
		
		String config = "resources/mybatis-config.xml"; // mybatis-config에 대한 경로 설정. 참조
		try {
			InputStream is = Resources.getResourceAsStream(config);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is); // Session Factory Builder -> Session Factory
//			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is, "development"); // "" mybatis에 environment의 지칭할 id를 작성
			// true를 넣어주면 자동 커밋 활성화(autoCommit)
			sess = ssf.openSession(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sess;
	}
	
	public static void main(String[] args) {
		
		SqlSession session = DBConn.getSqlSession();
		
		// 검색 결과가 여러개 나오면 selectList(0~n row), 한개 나오면 selectOne(1 row)
//		List<Object> result = session.selectList("testMapper.test");
//		System.out.println(result.get(0).getEmployee_id() + ", " + result.get(0).getFirst_name());
		
//		List<Object> result = session.selectList("testMapper.test"); 
//		List<EmpVO> result = session.selectList("testMapper.employee"); // WHERE EMPLOYEE_ID = 99 작성하면 selectList : [] 빈list 출력 (조회결과x), selectOne : null 출력 (조회결과x) 
//		EmpVO result = session.selectOne("testMapper.employee");
//		System.out.println(result);
		// result.size() : 몇개의 데이터가 답겨졌는지 알 수 있음
		
//		EmpVO result = session.selectOne("testMapper.employee", 100); // 100 : parameterType="_int" -> EMPLOYEE_ID = #{id} (여러개 사용 가능 -> Map 사용)
//		System.out.println(result); // 참조 주소로 출력, 값을 출력할려면 아래와 같이 작성
//		System.out.println(result.getEmployee_id() + ", " + result.getFirst_name());
		
		// 여러개 쓰기 위해 반복문을 활용
//		for(int i = 100; i <= 110; i++) {
//			EmpVO result = session.selectOne("testMapper.employee", i);
//			System.out.println(result);
//			System.out.println(result.getEmployee_id() + ", " + result.getFirst_name());
//		}
		
//		Map<String, Integer> data = new HashMap<String, Integer>();
//		data.put("id1", 100);
//		data.put("id2", 110);
//		List<EmpVO> result = session.selectList("testMapper.employee", data);
//		System.out.println(result);
//		for(EmpVO d: result) {
//			System.out.println(d.getEmployee_id() + ", " + d.getFirst_name());
//		}
		
		int res1 = session.selectOne("testMapper.test1"); // mapper의 namespace.id
		System.out.println(res1);
		
		String res2 = session.selectOne("testMapper.test2");
		System.out.println(res2);
		
		List<Map<String, Object>> res3 = session.selectList("testMapper.test3");
		for(Map<String, Object> data: res3) {
			System.out.println(data.get("EPLOYEE_ID") + ", " +data.get("FIRST_NAME"));
		}
		
		List<EmpVO> res4 = session.selectList("testMapper.test4");
		for(EmpVO data: res4) {
			System.out.println(data.getEmpId() + ", " + data.getfName());
		}
		
		List<EmpVO> res5 = session.selectList("testMapper.test5");
		for(EmpVO data: res5) {
			System.out.println(data.getEmpId() + ", " + data.getfName());
		}
		
		// idx = #{id}
		for(int idx = 100; idx < 110; idx++) {
			EmpVO res6 = session.selectOne("testMapper.test6", idx); // #{id} = idx
			System.out.println(res6.getEmpId() + ", " + res6.getfName());
		}
		
		List<EmpVO> res7 = session.selectList("testMapper.test7", "Steven");
		for(EmpVO data: res7) {
			System.out.println(data.getEmpId() + ", " + data.getfName());
		}
		
		List<EmpVO> res8 = session.selectList("testMapper.test8", "on");
		for(EmpVO data: res8) {
			System.out.println(data.getEmpId() + ", " + data.getfName());
		}
		
		Map<String, Integer> mapData = new HashMap<String, Integer>();
		mapData.put("start", 100); // (키, 값) mapData.get("start") : 키(start)를 이용하여 값(100)을 추출 -> #{start} 같은 의미
		mapData.put("end", 109);
		List<EmpVO> res9 = session.selectList("testMapper.test9", mapData);
		for(EmpVO data: res9) {
			System.out.println(data.getEmpId() + ", " + data.getfName());
		}
		
		EmpVO empData = new EmpVO();
		empData.setEmpId(200); // empData.getEmpId(); -> #{empId}
		EmpVO res10 = session.selectOne("testMapper.test10", empData);
		System.out.println(res10.getEmpId() + ", " + res10.getfName());
		
		
		// 테스트를 위해 임시로 주석함, 필요한 경우 주석을 제거하여 테스트 진행하면 됨
		// INSERT
//		TestVO insertData = new TestVO();
//		insertData.setId(4); insertData.setName("test"); insertData.setToday(new java.sql.Date(new Date().getTime()));		
		
		// 에러가 뜨지 않게 초기작업 해줌 => INSERT 하기 전에 먼저 동일한 id 값이 저장되었는지 확인
//		TestVO checkData = session.selectOne("testMapper.test15", insertData.getId());
//		if(checkData == null) {
//			int res11 = session.insert("testMapper.test11", insertData);
//			System.out.println(res11 + " 개의 행이 추가 되었습니다.");
//			session.commit(); // ssf.openSession(false) 일 때 수동적으로 commit 해줌
//		} else {
//			System.out.println("동일한 ID가 존재합니다.");
//			session.rollback();
//		}
		
		// 시퀀스 객체를 사용하면 동일한 id 값이 저장되었는지 확인할 필요가 없다.
//		TestVO insertData = new TestVO();
//		insertData.setId(4); insertData.setName("test"); insertData.setToday(new java.sql.Date(new Date().getTime()));		
//		int res11 = session.insert("testMapper.test11", insertData);
//		session.commit();
//		System.out.println(res11 + " 개의 행이 추가 되었습니다.");
		
		// UPDATE : Map 이용
//		Map<String, Object> updateData = new HashMap<String, Object>();
//		updateData.put("id", 1);
//		updateData.put("name", "change");
//		int res12 = session.update("testMapper.test12", updateData);
//		System.out.println(res12 + " 개의 행이 업데이트 되었습니다.");
		
		// UPDATE : TestVO 이용
//		TestVO objectData = new TestVO();
//		objectData.setId(1); objectData.setName("object");
//		int res13 = session.update("testMapper.test13", objectData);
//		System.out.println(res13 + " 개의 행이 업데이트 되었습니다.");
		
		// DELETE
//		int res14 = session.delete("testMapper.test14", 1);
//		System.out.println(res14 + " 개의 행이 삭제 되었씁니다.");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date stDate = null;
		Date edDate = null;
		try {
			stDate = sdf.parse("1990/01/01");
			edDate = sdf.parse("2000/12/31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 각각 null인 값 주석 달면서 확인
		EmpSelectVO dynamicData = new EmpSelectVO();
		dynamicData.setSalary(10000);
		dynamicData.setStartDate(new java.sql.Date(stDate.getTime())); // #{startDate} 에 적용하기 위해 자동으로 sql에 사용하는 Date 타입으로 맞춰줌
		dynamicData.setEndDate(new java.sql.Date(edDate.getTime()));
		dynamicData.setDeptId(80);
//		dynamicDate.setStrStartDate("1990/01/01"); // 문자열로
//		dynamicDate.setStrStartDate("2000/12/31");
		
		List<Integer> deptList = new ArrayList<Integer>();
		deptList.add(80); deptList.add(90); deptList.add(100);
		dynamicData.setDeptIdList(deptList);
		
		List<EmpVO> res15 = session.selectList("testMapper.test16", dynamicData);
		System.out.println(res15.size() + " 개 행 데이터가 조회되었습니다.");
		
		// 두가지 방법이 있음 -> 1. Map 사용 2. Vo 사용 
		// 아래는 Map을 이용함
		
		// 하나의 단일 값을 전달 할 때
//		Map<String, Integer> mapParam = new HashMap<String, Integer>();
//		mapParam.put("empId", 80);
		// 데이터 값이 하나, 두개, 여러개, 없음 일 수 있으므로 list 사용
//		List<Object> res16 = session.selectList("exampleMapper.empOfDeptCount", mapParam);
		
		// IN ()
//		Map<String, List<Integer>> mapParam = new HashMap<String, List<Integer>>();
//		List<Integer> deptList2 = new ArrayList<Integer>();
//		deptList2.add(80); deptList2.add(90); deptList.add(100);
//		mapParam.put("deptList", deptList2);
//		
//		List<Map<String, Object>> res16 = session.selectList("exampleMapper.empOfDeptCount", mapParam);
			
		// BETWEEN AND
		Map<String, Integer> mapParam = new HashMap<String, Integer>();
		mapParam.put("stDeptId", 80);
		mapParam.put("edDeptId", 100);
		
		List<Map<String, Object>> res16 = session.selectList("exampleMapper.empOfDeptCount", mapParam);
		
		if(res16.size() != 0) {
			for(Map<String, Object> record: res16) {
				System.out.println("총원 : " + record.get("TOTAL"));
				System.out.println("부서명 : " + record.get("DEPT_NAME"));
				System.out.println("부서코드 : " + record.get("DEPT_CODE"));
			} 
		} else {
			System.out.println("해당 부서는 존재하지 않습니다.");
		}
		
		// 시퀀스 테이블을 사용할 때 현재 시퀀스 번호를 알기 위한 로직
		TestVO insertData2 = new TestVO();
		insertData2.setName("getSeq"); insertData2.setToday(new java.sql.Date(new Date().getTime()));		
		
		int res17 = session.insert("testMapper.seqGetInsert", insertData2);
		session.commit();
		System.out.println(res17 + " 개의 행이 추가 되었습니다. 자동 생성된 ID 는 " + insertData2.getId() + "입니다.");
		
		EmpComplexVO res18 = session.selectOne("testMapper.empComplexSelect", 100);
		System.out.println(res18);
 	}
}
