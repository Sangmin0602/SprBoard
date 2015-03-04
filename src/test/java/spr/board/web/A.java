package spr.board.web;
/**
 * 
 * @author sangmin
 *
 * A a1 = A.newInstance();
 * A a2 = A.newInstance();
 * 
 * 전통적인 싱글톤 패턴
 * 
 * 반론) 굳이 이렇게 복잡하게 할 필요가 있냐?
 *       그냥 생성자를 한 번만 호출해서 그것만 쓰면 싱글톤인데...
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
