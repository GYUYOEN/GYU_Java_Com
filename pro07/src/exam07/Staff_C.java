package exam07;

public class Staff_C extends Employee_C {
	public Staff_C(String name, int age) {
		super(name, age);
		this.setSalary(2000);
	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
			case 6: case 12:
					System.out.printf("보너스 : %,.0f 원\n", getSalary() * 0.25 * 10000);
		}
	}
}