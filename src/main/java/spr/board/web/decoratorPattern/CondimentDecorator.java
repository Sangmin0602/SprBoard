package spr.board.web.decoratorPattern;

public abstract class CondimentDecorator extends Beverage{
	//모든 첨가물 데코레이터에서 getDescription() 메소드를 새로 구현하도록 만들 계획
	public abstract String getDescription();
}
