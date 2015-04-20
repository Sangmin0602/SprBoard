package spr.board.web.observer;

public class WeatherStation {

	static WeatherData weatherData; //weatherData import
	static CurrentCoditionDisplay currentDisplay;
	static ForecastDisplay forecastDisplay;
	
	public static void weatherStation() {
		weatherData = new WeatherData(); //WeatherData 객체 생성
		//currentDisplay = new CurrentCoditionDisplay(weatherData);
		//forecastDisplay = new ForecastDisplay(weatherData);
		
		currentDisplay = new CurrentCoditionDisplay();
		forecastDisplay = new ForecastDisplay();
		
		weatherData.addObserver(currentDisplay);
		weatherData.addObserver(forecastDisplay);
	}
	
	//WeatherData의 setMeasureMents 함수 실행
	public static void changeWeather(float temp, float humity, float pressure) {
		weatherData.setMeasurements(temp, humity, pressure);
	}
	
	public static void main(String[] args) {
		weatherStation(); //WeatherStation 생성
		
		//weatherStation에서 날씨의 변화를 입력한다.
		System.out.println("------날씨가 변한다.-------");
		changeWeather(40,50,10);
		
		System.out.println("");
		
		System.out.println("-------날씨가 변한다.-------");
		changeWeather(50,60,20);
		
		System.out.println("");
	}
}
