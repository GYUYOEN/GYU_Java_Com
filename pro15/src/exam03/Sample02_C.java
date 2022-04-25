package exam03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Sample02_C {

	public static void main(String[] args) {
		String userHome = System.getProperty("user.home");
		String pathName = "/eclipse/jee-2021-12/eclipse/configuration/config.ini";
		Properties prop = new Properties();
		
		try {
			// 불러옴
			prop.load(new FileInputStream(new File(userHome + pathName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop);
		// eclipse.product 키에 해당하는 값 출력
		System.out.println(prop.get("eclipse.product"));
		// 차이 : get은 메서드 값 Object, getProperty는 메서드 값 String
		// = (차이가 있긴하지만 사용하는 것은 같음
		System.out.println(prop.getProperty("eclipse.product"));
		
		// 문자열로만 작성해야함
		// put : Object, setProperty : String
		prop.put("x", "100");
		prop.put("prop", "content");
		prop.setProperty("set", "text");
		
		// 파일로 쓰기 위한 작업
		String newPath =  "/eclipse/jee-2021-12/eclipse/configuration/config.copy";
		try {
			// "Comment Write" : 주석에 들어갈 내용
			prop.store(new FileWriter(new File(userHome + newPath)), "Comment Write");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
