package exam10;

//공통된 부분
public abstract class Operator_C {
	public abstract int add(int n1, int n2);
	public abstract double add(int n1, double n2);
	public abstract double add(double n1, int n2);
	public abstract double add(double n1, double n2);
	
	public abstract int sub(int n1, int n2);
	public abstract double sub(int n1, double n2);
	public abstract double sub(double n1, int n2);
	public abstract double sub(double n1, double n2);
	
	public abstract int mul(int n1, int n2);
	public abstract double mul(int n1, double n2);
	public abstract double mul(double n1, int n2);
	public abstract double mul(double n1, double n2);
	
	public abstract int div(int n1, int n2);
	public abstract double div(int n1, double n2);
	public abstract double div(double n1, int n2);
	public abstract double div(double n1, double n2);

}
