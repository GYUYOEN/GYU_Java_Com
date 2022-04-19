package game.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 파일 입출력
public class PenaltyDatabase_C {
	
	private String userName;
	private String[] penaltys;
	private File file;
	
	public PenaltyDatabase_C() {
		// 알아서 home 찾아줌
		// 사용자 계정 directory = home directory
		String userHome = System.getProperty("user.home");
		this.file = new File(userHome + "/up_down.penalty");
		// 패널티 배열에 벌칙을 넣어주기 위해
		// _load(데이터 불러오기) -> _parser(벌칙 배열 만들기)
		// _load(문자열) -> this.penaltys(문자배열) 최종 반환
		this._parser(_load());
	}
	
	public void add(String data) {
		this.penaltys = Arrays.copyOf(this.penaltys, this.penaltys.length + 1);
		try(FileWriter fw = new FileWriter(file, true)) {
			this.penaltys[this.penaltys.length - 1] = data;
			fw.write(data + "\n");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] getList() {
//		try(FileReader fr = new FileReader(this.file)) {
//			char[] buffer = new char[1024];
//			char[] data = new char[0];
//			while(true) {
//				int rCnt = fr.read(buffer);
//				
//				if(rCnt == -1) {
//					break;
//				}
//				
//				int stIdx = data.length;
//				data = Arrays.copyOf(data, data.length + rCnt);
//				System.arraycopy(buffer, 0, data, stIdx, rCnt);
//			}
//			
//			StringTokenizer st = new StringTokenizer(new String(data), "\n");
//			String[] res = new String[st.countTokens()];
//			int idx = 0;
//			while(st.hasMoreTokens()) {
//				res[idx++] = st.nextToken();
//			}
//			return res;
//		} catch(FileNotFoundException e) {
//			return null;
//		} catch(IOException e) {
//			return null;
//		}
		
		// 위에 다 지운 후
//		String data = this._load();
//		StringTokenizer st = new StringTokenizer(new String(data), "\n");
//		String[] res = new String[st.countTokens()];
//		int idx = 0;
//		while(st.hasMoreTokens()) {
//			res[idx++] = st.nextToken();
//		} // -> _parser() 로 옮김
		
		
		// clone 쓰고 안 쓰고 차이
		// 쓰면 settingMenu에 수정,삭제 등 작업 반영 x, 독립적임, 다른 배열사용
		// 안 쓰면 반영 o, 공유해서 사용
		// clone을 쓴 이유 : 이 클래스에서만 수정, 삭제 작업이 가능하게 하기 위해서
		// settingMenu는 수정, 삭제 요청만 함
		return this.penaltys.clone();
	}

	public void modify(int index, String penalty) {
//		String[] penaltys = this.getList();
//		penaltys[index] = penalty;
//		
//		try(FileWriter fw = new FileWriter(file)) {
//			for(int i = 0; i < penaltys.length; i++) {
//				fw.write(penaltys[i] + "\n");
//			}
//			fw.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} // -> _save()
		
		this.penaltys[index] = penalty;
		
		this._save();
	}

	public void remove(int index) {
//		String[] penaltys = this.getList();
		
		String[] temp = new String[this.penaltys.length - 1];
		System.arraycopy(this.penaltys, 0, temp, 0, index);
		System.arraycopy(this.penaltys, index + 1, temp, index, this.penaltys.length - (index + 1));
		this.penaltys = temp;
		
//		try(FileWriter fw = new FileWriter(file)) {
//		for(int i = 0; i < penaltys.length; i++) {
//			fw.write(penaltys[i] + "\n");
//		}
//			fw.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} // -> _save()
		
		this._save();
	}
	
	// 분류해서 매킹 작업
	private void _parser(String data) {
		// StringTokenizer : 문자열 타입 요구 (문자배열(new String(data)) -> 문자열(data))
		// StringTokenizer 로 벌칙배열을 만들기 위해 data를 가져옴
		StringTokenizer st = new StringTokenizer(data, "\n");
		String[] res = new String[st.countTokens()];
		int idx = 0;
		while(st.hasMoreTokens()) {
			res[idx++] = st.nextToken();
		}
		// this.penaltys : 벌칙 배열(벌칙배열을 만들기 위해 StringTokenizer 작업 실행)
		this.penaltys = res.clone();
	}
	
	private String _load() {
		try(FileReader fr = new FileReader(this.file)) {
			char[] buffer = new char[1024];
			char[] data = new char[0];
			while(true) {
				int rCnt = fr.read(buffer);
				
				if(rCnt == -1) {
					break;
				}
				
				int stIdx = data.length;
				data = Arrays.copyOf(data, data.length + rCnt);
				System.arraycopy(buffer, 0, data, stIdx, rCnt);
			}
			return new String(data);
		} catch(FileNotFoundException e) {
			return null;
		} catch(IOException e) {
			return null;
		}
	}
	
	private void _save() {
		try(FileWriter fw = new FileWriter(file)) {
			for(int i = 0; i < this.penaltys.length; i++) {
				fw.write(this.penaltys[i] + "\n");
			}                                                                                
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
