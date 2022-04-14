package exam10;

public class ProgrammerCalc_C extends Calculator_C implements Programmer_C{

	@Override
	public String binary(int n1) {
		String b = "";
		while(n1 > 1) {
			b = n1 % 2 + b;
			n1 /= 2;
		}
		return n1 + b;
	}

	@Override
	public String octal(int n1) {
		String o = "";
		while(n1 > 1) {
			o = n1 % 8 + o;
			n1 /= 8;
		}
		return n1 + o;
	}

	@Override
	public String hexa(int n1) {
		String h = "";
		while(n1 > 1) {
			int x = n1 % 16;
			if(x >= 10) {
				h = _hexCode(x) + h;
			} else {
				h = x + h;
			}
			n1 /= 16;
		}
		return (n1 >= 10 ? _hexCode(n1) : n1) + h;
	}
	
	private char _hexCode(int n1) {
		return (char)(n1 + 55);
	}
}
