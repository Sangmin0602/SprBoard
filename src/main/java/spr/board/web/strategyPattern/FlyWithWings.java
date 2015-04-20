package spr.board.web.strategyPattern;

public class FlyWithWings implements Flyable {

	@Override
	public void fly() {
		System.out.println("날개로 훨훨 날아갑니다.");

	}

}
