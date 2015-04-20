package spr.board.web.strategyPattern;

public class MuteQuack implements Quackable {

	@Override
	public void quack() {
		System.out.println("울 수 없어요");

	}

}
