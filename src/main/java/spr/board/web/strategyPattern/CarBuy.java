package spr.board.web.strategyPattern;

public class CarBuy {
	public static void main(String[] args) {
		//자동차 판매점
		CarMarket market = new CarMarket();
		//자동차 구입
		
		try {
			Car tico = market.carDisplay(new TicoFactory());
			Car bmw = market.carDisplay(new BmwFactory());
			System.out.println(tico.getName()+" : "+tico.getPrice());
			System.out.println(bmw.getName()+" : "+bmw.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
