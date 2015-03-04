package spr.board.web;
/**
 * 
 * @author sangmin
 *
 * A a1 = A.newInstance();
 * A a2 = A.newInstance();
 * 
 * �������� �̱��� ����
 * 
 * �ݷ�) ���� �̷��� �����ϰ� �� �ʿ䰡 �ֳ�?
 *       �׳� �����ڸ� �� ���� ȣ���ؼ� �װ͸� ���� �̱����ε�...
 *       
 */
public class A {
	private static A instance;
	
	private A(){}
	
	static {
		instance = new A();
	}
	
	public static A newInstance() {
		return instance;
	}
}
