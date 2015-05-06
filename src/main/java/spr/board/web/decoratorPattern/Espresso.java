package spr.board.web.decoratorPattern;

public class Espresso extends Beverage{
	// getDescrpiton() 가 함께 있다.
	
	public Espresso() {
		//Beverage 로부터 상속받음
		description = "에스프레소";
	}
	
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 1.99;
	}

}
