package spr.board.web.observer;

import java.util.Observable;
import java.util.Observer;

public class CurrentCoditionDisplay implements Observer, DisplayElement {

	Observable observable;
	
	private float temperature;
	private float humidity;
	
	public CurrentCoditionDisplay (Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	public CurrentCoditionDisplay() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable obs, Object arg) {
		if(obs instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) obs;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}
	
	@Override
	public void display() {
		System.out.println("현재온도:" + temperature + "도, 현재 습도 :" + humidity + "%");
		
	}

}
