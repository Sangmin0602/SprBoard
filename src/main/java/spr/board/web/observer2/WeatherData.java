package spr.board.web.observer2;

import java.util.Observable;

public class WeatherData extends Observable{
	private float humid;
	private float temp;
	
	public WeatherData(){}
	
	public void setMeasurement(float humid, float temp) {
		this.humid = humid;
		this.temp = temp;
		changeWeather();
	}
	
	public void changeWeather() {
		setChanged(); // false=> true(변경된 내용이 있다.)
		notifyObservers();  // Observable에 등록된 옵저버들의 update를 실행한다.
	}

	public float getHumid() {
		return humid;
	}
	
	public float getTemp() {
		return  temp;
	}
}
