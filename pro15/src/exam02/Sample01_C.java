package exam02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * Set 컬레션
		 */
		Set<Integer> aSet = new HashSet<Integer>();
	
		// 값이 중복되서 하나라도 추가가 되지 않았으면 false
		boolean result1;
		result1 = aSet.add(100);
		System.out.println(result1 + " " + aSet);
		result1 = aSet.add(200);
		System.out.println(result1 + " " + aSet);
		result1 = aSet.add(200);
		System.out.println(result1 + " " + aSet);
	
		Set<Integer> bSet = new HashSet<Integer>();
		bSet.add(300); bSet.add(400);
	
		result1 = aSet.addAll(bSet);
		System.out.println(result1 + " " + aSet);
		
		// 꼭 hashSet이 아니라도 collection 계열이면 됨
		// ArrayList는 하나라도 추가가 됬으면 true, 모든 데이터가 추가가 안됐으면 false 
		List<Integer> cList = new ArrayList<Integer>();
		cList.add(500); cList.add(600); cList.add(100);
	
		result1 = aSet.addAll(cList);
		System.out.println(result1 + " " + aSet);
	
		result1 = aSet.contains(Integer.valueOf(100));
		System.out.println(result1);
		result1 = aSet.contains(Integer.valueOf(150));
		System.out.println(result1);
	
		result1 = aSet.isEmpty();
		System.out.println(result1);
	
		bSet.clear();
		result1 = bSet.isEmpty();
		System.out.println(result1);
	
		int len = aSet.size();
		System.out.println(len + " " + aSet);
	
		result1 = aSet.remove(Integer.valueOf(100));
		System.out.println(result1 + " " + aSet);
		
		// 150이 aSet 항목에 없으므로 제거 x -> false
		result1 = aSet.remove(Integer.valueOf(150));
		System.out.println(result1 + " " + aSet);
	
		// 반복문 사용가능하게 함
		Iterator<Integer> iter = aSet.iterator();
		while(iter.hasNext()) {
			Integer i1 = iter.next();
			System.out.print(i1 + "\t");
		}
		System.out.println();
		
		for(Integer i1: aSet) {
			System.out.print(i1 + "\t");
		}
		System.out.println();
		
		// Set을 List로 변경
		List<Integer> aList = new ArrayList<Integer>(aSet);
		System.out.println(aList);
		
		ListIterator<Integer> iter1 = aList.listIterator(aList.size()-1);
		
//		while(iter1.hasNext()) {
//			Integer data = iter1.next();
//			System.out.println("Next : " + data);
//		}
		
		// 그냥 사용할 순없고 객체에 인덱스 번호(aList.size()-1)를 지정해 주어야함
		// 양방향이기 때문에 hasNext를 해준 뒤 previous 사용
		while(iter1.hasPrevious()) {
			Integer data = iter1.previous();
			System.out.println("Previous : " + data);
		}
		
		// List를 Set으로 변경
		Set<Integer> cSet = new HashSet<Integer>(aList);
		System.out.println(cSet);
		
		// 배열로 변경
		Integer[] iArr = aList.toArray(new Integer[aList.size()]);
		System.out.println(Arrays.toString(iArr));
		iArr = cSet.toArray(new Integer[cSet.size()]);
	}

}
