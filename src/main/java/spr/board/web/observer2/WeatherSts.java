package spr.board.web.observer2;

public class WeatherSts {

/*	1. WeatherStation에서는 온도, 습도, 기압 정보를 수집한다.
	2. WeatherStation에서는 주기적으로 현재 데이터를 WeatherData에 던져준다.
	3. WeatherData는 새로운 데이터를받으면, Obserable에 Observer로 등록되어 있는, 현재상태 출력장비와
	기압변동 출력장비에 새로운 데이터를 전달한다.
	4.현재상태 출력장비와 기압변동 출력장비는 새로운 데이터를 각자의 활용방법에 따라 화면에 출력한다.*/
	
/*	쉽게 말하면 내가 데이터를 받을때마다 나의 정보를 구독하길 원하는 대상들에게 데이터를 던져주는 것입니다.
	
	Observable 클래스와 Observer 인터페이스는 java.util 패키지에 들어 있습니다. 자바내장 API입니다.
	Observable 클래스는 등록된  옵저버들을 관리하며, 새로운 데이터가 들어오면 등록된 옵저버에게 데이터를 전달합니다.
	Observer 인터페이스를 implements 하여 등록된 옵저버들은 Obserable로 부터 데이터를 받을 수 있습니다.
	이 자바 내장 옵저버 패턴을 사용하면 옵저버 패턴을 직접 구현할 필요 없이, 간단히 해당 클래스를 상속하면 됩니다.*/
	
/*	1. Observable클래스를 상속한 클래스를 만들고, 새로운 데이터가 들어오면 setChanged(), notifyObservers()를 호출하도록 구현
	
	2. Observer를 implements한 클래스를 만들고 Obserable에 addObserver(this)로 자신을 Observaer로 등록한다. Update()함수를 구현하여
	전달받은 데이터를 처리해준다.*/
	
	
	
	public static void main(String[] args) {
		
		WeatherData weather = new WeatherData();
		weather.addObserver(new HumidData());
		weather.addObserver(new Temperture());
		
		System.out.println("-------날씨와 습도------");
		weather.setMeasurement(10, 20);
		System.out.println();
		
		System.out.println("-------날씨와 습도------");
		weather.setMeasurement(30, 50);
		System.out.println();
	}

}
