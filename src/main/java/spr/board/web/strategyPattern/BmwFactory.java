package spr.board.web.strategyPattern;

public class BmwFactory implements CarFactory {

	@Override
	public Car makeCar() throws Exception {
		// TODO Auto-generated method stub
		return new Car("BMW", "5000원");
	}

}
