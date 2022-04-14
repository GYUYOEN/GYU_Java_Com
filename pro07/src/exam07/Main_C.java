package exam07;

import java.util.Random;

public class Main_C {

	public static void main(String[] args) {
		Random rand = new Random();
		
		Employee_C e1 = new Staff_C("김사원", 28);
		Employee_C e2 = new AssistantManager_C("박대리", 32);
		Employee_C e3 = new DepartmentManager_C("이과장", 38);
		Employee_C e4 = new DeputyGeneralManager_C("차차장", 43);
		Employee_C e5 = new Director_C("곽부장", 48);
		
		Employee_C[] empArr = new Employee_C[5];
		empArr[0] = e1; empArr[1] = e2; empArr[2] = e3;
		empArr[3] = e4; empArr[4] = e5;		
		
		((DepartmentManager_C)e3).setTeamManager(true);
		((DeputyGeneralManager_C)e4).setHeadManager(true);
		((Director_C)e5).setTeamManager(true);
		((Director_C)e5).setHeadManager(true);
		
		int year = 2022;
		for(int i = 0; i < 30; i++) {
			int month = (i + 1) % 12 == 0 ? 12 : (i + 1) % 12;
			for(int idx = 0; idx < empArr.length; idx++) {
//				if((i + 1) % 12 == 0) {
//					month = 12;
//				} else {
//					month = (i + 1) % 12;
//				}
				
				System.out.printf("%d년 %d월 급여명세", year + (i + 1) / 12, month);
				System.out.printf("이름 : %s\n", empArr[idx].getName());
				empArr[idx].payMonth();
				empArr[idx].bonus(month);
				if(empArr[idx] instanceof TeamManager_C) {
					((TeamManager_C)empArr[idx]).teamPayBonus();
				}
				if(empArr[idx] instanceof HeadManager_C) {
					((HeadManager_C)empArr[idx]).headPayBonus();
				}
//				if(empArr[idx] instanceof Staff_C || empArr[idx] instanceof AssistantManager_C) {
//					switch(month) {
//						case 6: case 12:
//							empArr[idx].bonus();					
//					}
//				} else if(empArr[idx] instanceof DepartmentManager_C || empArr[idx] instanceof DeputyGeneralManager_C) {
//					switch(month) {
//						case 4: case 8: case 12:
//							empArr[idx].bonus();
//					}
//				} else if(empArr[idx] instanceof Director_C) {
//					switch(month) {
//						case 1:
//							empArr[idx].bonus();
//					}
//				}
				System.out.println("=================================");
			}
			if((rand.nextInt(9) + 1) % 4 == 0) {
				int loc = rand.nextInt(4) + 1;
				System.out.printf("%s 가 ", empArr[loc].getName());
				((AssistantManager_C)empArr[loc]).corpCard(3000000);
						
			}
		}
		
//		e1.payMonth();  e1.bonus();
//		e2.payMonth();  e2.bonus();  ((AssistantManager_C)e2).corpCard(460000);
//		e3.payMonth();  e3.bonus();  ((DepartmentManager_C)e3).corpCard(610000);
//		e4.payMonth();  e4.bonus();  ((DeputyGeneralManager_C)e4).corpCard(826000);
//		e5.payMonth();  e5.bonus();  ((Director_C)e5).corpCard(1300000);
	}

}