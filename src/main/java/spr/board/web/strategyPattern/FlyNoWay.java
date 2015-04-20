package spr.board.web.strategyPattern;

public class FlyNoWay implements Flyable {

	@Override
	public void fly() {
		System.out.println("날 수 없습니다.");

	}

}
