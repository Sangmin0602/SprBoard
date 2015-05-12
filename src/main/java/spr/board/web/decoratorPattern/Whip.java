package spr.board.web.decoratorPattern;

public class Whip extends CondimentDecorator{
	Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage =  beverage;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription() + ", Whipping";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return beverage.cost() + 3.0;
	}

}
