package spr.board.web.strategyPattern;

public class TicoFactory implements CarFactory {
	
	@Override
	public Car makeCar() throws Exception {
		// TODO Auto-generated method stub
		return new Car("Tico", "1000원");
	}

}
