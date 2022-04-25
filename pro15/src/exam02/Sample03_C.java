package exam02;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Sample03_C {
	
	public static void main(String[] args) {
		Set<Integer> hashSet = new HashSet<Integer>();
		Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();
		Set<Integer> treeSet = new TreeSet<Integer>();
		
		// 내가 넣은 값의 중복이 없는 16 진수 hash 값 순으로 넣음(무작위는 아님)
		hashSet.add(600); hashSet.add(200); hashSet.add(500);
		hashSet.add(400); hashSet.add(900); hashSet.add(700);
		hashSet.add(300); hashSet.add(100); hashSet.add(800);
		
		// 입력힌 순서가 List처럼 유지 됨
		linkedHashSet.add(600); linkedHashSet.add(200); linkedHashSet.add(500);
		linkedHashSet.add(400); linkedHashSet.add(900); linkedHashSet.add(700);
		linkedHashSet.add(300); linkedHashSet.add(100); linkedHashSet.add(800);
		
		// 정렬이 되면서 들어감
		treeSet.add(600); treeSet.add(200); treeSet.add(500);
		treeSet.add(400); treeSet.add(900); treeSet.add(700);
		treeSet.add(300); treeSet.add(100); treeSet.add(800);
		
		System.out.println(hashSet);
		System.out.println(linkedHashSet);
		System.out.println(treeSet);
		
		// hashSet -> linkedHashSet -> treeSet 순으로 속도가 느려짐
//		Set<Integer> treeSet1 = new TreeSet<Integer>(hashSet);
//		System.out.println(treeSet1);
	}
}
