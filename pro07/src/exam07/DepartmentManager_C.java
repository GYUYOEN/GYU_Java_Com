package exam07;

//과장
public class DepartmentManager_C extends AssistantManager_C implements TeamManager_C {
	private boolean teamManager; // 팀장직을 가지고 있는지 true, false로 구분
//	private int corpCardTotal;

	public DepartmentManager_C(String name, int age) {
		super(name, age);
		this.setSalary(4000);
	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
		case 4: case 8: case 12:
			System.out.printf("보너스 : %,.0f 원\n", getSalary() * 0.25 * 10000);
		}
	}


	@Override
	public void teamPayBonus() {
		// 팀장 직책으로 숳행하는 경우 연봉의 10% 를 보너스
		if(isTeamManager()) {
			System.out.println("팀장직 수행 보너스 : " + getSalary() / 0.1 / 12 * 10000 + " 원");
		}
	}
	
	public boolean isTeamManager() {
		return teamManager;
	}

	public void setTeamManager(boolean teamManager) {
		this.teamManager = teamManager;
	}
//	public void corpCard(int amount) {
//		if((getSalary() * 0.015) * 10000 > corpCardTotal + amount) {
//			System.out.printf("%,d 원을 법인카드로 지출하였습니다.\n", amount);
//			corpCardTotal += amount;
//		} else {
//			System.out.println("범인 카드 한도를 초과하였습니다.");
//			System.out.printf("현재까지 사용액은 %,d원 입니다.\n", corpCardTotal);			
//			System.out.printf("한도내에서 %,.0f 원 만큼만 사용할 수 있습니다.\n", (getSalary() * 0.015) * 10000 - corpCardTotal);			
//		}
//	}
	
	
	
}
