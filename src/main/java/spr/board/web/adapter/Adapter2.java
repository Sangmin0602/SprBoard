package spr.board.web.adapter;

public class Adapter2 implements Target2{
	// 객체를 만들고
	Adaptee2 adaptee = new Adaptee2();

	@Override
	public void request() {
		//객체를 연결한다.
		adaptee.specificRequest();
	}

}
