package exam10;

public class Main_C {

	public static void main(String[] args) {
		Operator_C op1 = new Calculator_C();
		
		System.out.println(op1.add(4, 2));
		System.out.println(op1.sub(4, 2));
		System.out.println(op1.mul(4, 2));
		System.out.println(op1.div(4, 2));
		
		Operator_C op2 = new EngineerCalc_C();
		
		System.out.println(op2.add(4, 2));
		System.out.println(op2.sub(4, 2));
		System.out.println(op2.mul(4, 2));
		System.out.println(op2.div(4, 2));
		
		// 형변화
		Engineer_C eng = (Engineer_C)op2;
		System.out.println(eng.mod(4, 2));
		System.out.println(eng.sqrd(4, 2));
		System.out.println(eng.abs(-4));
		
		// ======================================
		
		Operator_C op3 = new ProgrammerCalc_C();
		
		System.out.println(op2.add(4, 2));
		System.out.println(op2.sub(4, 2));
		System.out.println(op2.mul(4, 2));
		System.out.println(op2.div(4, 2));
		
		// 형변환
		Programmer_C pro = (Programmer_C)op3;
		System.out.println(pro.binary(100));
		System.out.println(pro.octal(100));
		System.out.println(pro.hexa(100));
		

	}

}
