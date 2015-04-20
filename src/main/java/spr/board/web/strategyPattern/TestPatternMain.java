package spr.board.web.strategyPattern;

public class TestPatternMain {
	public static void main(String[] args) {
		//오리선언
		Duck mallard = new MallardDuck();
		Duck rubber = new RubberDuck();
		
		//각 행동 정의
		Flyable fly = new FlyWithWings();
		Flyable nofly = new FlyNoWay();
		Quackable quack = new Squack();
		Quackable muteQuack = new MuteQuack();
		
		//청둥 오리와 고무 오리에게 행동 주입
		mallard.setFlyBehavior(fly);
		mallard.setQuack(quack);
		rubber.setFlyBehavior(nofly);
		rubber.setQuack(muteQuack);
		
		//행동 수행
		mallard.display();
		mallard.performFly();
		mallard.performQuack();

		rubber.display();
		rubber.performFly();
		rubber.performQuack();
		
	}
}
