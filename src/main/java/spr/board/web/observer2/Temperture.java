package spr.board.web.observer2;

import java.util.Observable;
import java.util.Observer;

public class Temperture implements Observer, DisplayWeahter {
	
	private float temp;
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weather = (WeatherData) o;
			this.temp = weather.getTemp();
			display();
		}

	}

	@Override
	public void display() {
		System.out.println("Temperture" + " " + temp);
		
	}

}
