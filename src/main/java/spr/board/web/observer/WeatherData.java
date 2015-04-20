package spr.board.web.observer;

import java.util.Observable;

public class WeatherData extends Observable{
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
		
	}
	//새로운 데이터를 전달 받아 갱신하고 새로운데이터가 들어왔음을 알린다.
	//기상스테이션(WeatherStation)에서는 주기적으로 이 함수를 사용해 최신 데이터를 전달한다.
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		measurementsChanged();
	}
	
	//갱신할 새로운 데이터 여부의 플래그 값을 변경하고(setChanged())
	//옵저버들에게 새로운 데이터를 전달한다
	private void measurementsChanged() {
		setChanged();
		notifyObservers();
	}
	
	//온도값 반환
	public float getTemperature() {
		return temperature;
	}
	
	//습도값 반환
	public float getHumidity() {
		return humidity;
	}

	//기압갑 반환
	public float getPressure() {
		return pressure;
	}
}
