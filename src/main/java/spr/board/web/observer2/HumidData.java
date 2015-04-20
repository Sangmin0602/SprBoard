package spr.board.web.observer2;

import java.util.Observable;
import java.util.Observer;

public class HumidData implements Observer, DisplayWeahter {
	private float humid;
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weather = (WeatherData) o;
			this.humid = weather.getHumid();
			display();
		}
	}
	
	@Override
	public void display() {
		System.out.println("Humid" + " " + humid);
		
	}

		
}
