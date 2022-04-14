package exam03;

import java.io.Serializable;

// Serializable : 직렬화 기능 
// 객체 -> 바이트 데이터 : 직렬화
// 바이트 -> 객체 : 역직렬화 
public class ObjSample_C implements Serializable{
	private int num;
	private double point;
	private boolean ys;
	private String name;

	public ObjSample_C(int i, double d, boolean b, String s) {
		num = i;
		point = d;
		ys = b;
		name = s;
	}
	
	@Override
	public String toString() {
		return "ObjSample_C [num=" + num + ", point=" + point + ", ys=" + ys + ", name=" + name + "]";
	}
}
