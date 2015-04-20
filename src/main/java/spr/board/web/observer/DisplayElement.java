package spr.board.web.observer;

//서로 상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는디자인을 사용해야 한다.
public interface DisplayElement {
	public void display();
}
