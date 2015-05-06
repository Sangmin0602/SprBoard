package spr.board.web.adapter;

public class Adapter extends Adaptee implements Target {

	@Override
	public void request() {
		// 다중상속
		this.specificRequest();
	}

}
