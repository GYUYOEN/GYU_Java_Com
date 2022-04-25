package exam03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * Map 계열 걸렉션
		 */
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		aMap.put("a", 100); aMap.put("b", 200); aMap.put("c", 300);
		aMap.put("d", 100); aMap.put("b", 400); aMap.put("e", 500);
		aMap.put("f", 600); aMap.put("g", 700); aMap.put("h", 800); 
		
		// 중복된 키가 있으면 기존값이 리턴되고 기존값이 새로운 값으로 바뀜
		Integer result1 = aMap.put("h", 900);
//		if(result1 != null) {
//			aMap.put("h", result1);
//		}
		System.out.println(result1 + " " + aMap);
		
		result1 = aMap.put("i", 800);
		System.out.println(result1 + " " + aMap);
		
//		if(!aMap.containsValue(Integer.valueOf(900))) {
//			aMap.put("j", 900);
//		}
//		System.out.println(aMap);
		
		boolean result2;
		result2 = aMap.containsValue(Integer.valueOf(900));
		System.out.println(result2);
		
		result2 = aMap.containsKey("a");
		System.out.println(result2);
		
		Set<Entry<String, Integer>> entrys = aMap.entrySet();
		// 이제 부터 Set 처럼 활용
		Iterator<Entry<String, Integer>> iter = entrys.iterator();
		while(iter.hasNext()) {
			Entry<String, Integer> entry = iter.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
			
		}
		
		for(Entry<String, Integer> entry: aMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		for(String s: aMap.keySet()) {
			System.out.print(s + "\t");
		}
		System.out.println();
		
		for(Integer i: aMap.values()) {
			System.out.print(i + "\t");
		}
		System.out.println();
		
		result1 = aMap.get("a");
		System.out.println(result1);
		
		result1 = aMap.get("k");
		System.out.println(result1);
		
		// k 에 값이 없으면 디폴트값(Integer.valueOf(0))을 씀. (0)에 무엇을 쓰냐에 따라 달라짐 
		result1 = aMap.getOrDefault("k", Integer.valueOf(0));
		System.out.println(result1);
		
		result1 = aMap.remove("a");
		System.out.println(result1 + " " + aMap);
		
		// 두개 다 메치가 되는 거 삭제
		aMap.remove("b", Integer.valueOf(300));
		System.out.println(aMap);
		aMap.remove("b", Integer.valueOf(400));
		System.out.println(aMap);
	}

}
