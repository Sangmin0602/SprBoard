package spr.board.web.decoratorPattern;

//모카는 데코레이터기 때문에 CondimentDecorator를 확장 합니다.
public class Mocha extends CondimentDecorator{

	Beverage beverage;
	
	//생성자를 이용해서 감싸고자 하는 음료 객체를 전달한다.
	public Mocha (Beverage beverage) {
		this.beverage = beverage;
	}

	//데코레이션 상속
	@Override
	public String getDescription() {
		// 음료 명에 첨가물명을 추가한다.
		return beverage.getDescription() + ", 모카";
	}

	//Beverage 비용상속
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return beverage.cost() + .20;
	}
	
}
