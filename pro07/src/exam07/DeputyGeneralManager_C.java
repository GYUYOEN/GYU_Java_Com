package exam07;

//차장
public class DeputyGeneralManager_C extends DepartmentManager_C implements HeadManager_C {
//	private boolean teamManager; // 팀장직을 가지고 있는지 true, false로 구분, 과장에 있으므로 뺌
	private boolean headManager; // 본부장직을 가지고 있는지 true, false로 구분
//	private int corpCardTotal;
	
	public DeputyGeneralManager_C(String name, int age) {
		super(name, age);
		this.setSalary(5500);
	}
	
//	public void corpCard(int amount) {
//		if((getSalary() * 0.015) * 10000 > corpCardTotal + amount) {
//			System.out.printf("%,d 원을 법인카드로 지출하였습니다.", amount);
//			corpCardTotal += amount;
//		} else {
//			System.out.println("범인 카드 한도를 초과하였습니다.");
//			System.out.printf("현재까지 사용액은 %,d원 입니다.\n", corpCardTotal);			
//			System.out.printf("한도내에서 %,.0f 원 만큼만 사용할 수 있습니다.\n", (getSalary() * 0.015) * 10000 - corpCardTotal);			
//		}
//	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
			case 4: case 8: case 12:
				System.out.printf("보너스 : %,.0f 원\n", getSalary() * 0.25 * 10000);
		}
	}

	@Override
	public void headPayBonus() {
		if(isHeadManager()) {
			System.out.println("본부장직 수행 보너스 : " + getSalary() / 0.2 / 12 * 10000 + " 원");
		}
	}

	public boolean isHeadManager() {
		return headManager;
	}

	public void setHeadManager(boolean headManager) {
		this.headManager = headManager;
	}

	
	
}
