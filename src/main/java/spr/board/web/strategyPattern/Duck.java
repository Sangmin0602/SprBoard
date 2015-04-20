package spr.board.web.strategyPattern;

public abstract class Duck {
	
	// 행동을 추상화한 인터페잇 멤버변수
	private Flyable flyBehavior;
	private Quackable quackBehavior;
	
	//오리마다 다른모습
	public abstract void display();
	
	public void performFly() {
		flyBehavior.fly();
	}
	
	// 행동
	public void performQuack() {
		quackBehavior.quack();
	}
	
	// 행동 setter
	public void setFlyBehavior(Flyable fb) {
		this.flyBehavior = fb;
	}
	
	public void setQuack(Quackable qb) {
		this.quackBehavior = qb;
	}
	
}
