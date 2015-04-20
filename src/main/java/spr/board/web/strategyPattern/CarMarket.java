package spr.board.web.strategyPattern;

public class CarMarket {
	public Car carDisplay(CarFactory factory) throws Exception {
		return factory.makeCar();
	}
}
