package spr.board.web.decoratorPattern;


public class StarbuzzCoffee {
	public static void main(String[] args) {
		//에스프레소 커피
		Beverage espresso = new Espresso();
		System.out.println(espresso.getDescription() + "  :  " + espresso.cost());
		
		//다크로스트 커피 + 모카 + 모카 + 휘핑 크림
		Beverage darkRoast = new DarkRoast();
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Whip(darkRoast);
		System.out.println(darkRoast.getDescription() +"  :  " + darkRoast.cost());
		
		//하우스 블랜드 커피, 두유, 모카, 휘핑크림
		Beverage houseBlend = new HouseBlend();
		houseBlend = new Soy(houseBlend);
		houseBlend = new Mocha(houseBlend);
		houseBlend = new Whip(houseBlend);
		System.out.println(houseBlend.getDescription() + "  :  " + houseBlend.cost());
		
		
	}
}
