/**
 * 
 */
package spr.board.web.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author sangmin
 *
 */
public class ForecastDisplay implements Observer, DisplayElement {
	
	Observable observable; //등록될 Observable
	
	private float currentPressure = 29.92f;
	private float lastPressure;
	
	public ForecastDisplay (Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	public ForecastDisplay() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object arg) {
		if(obs instanceof WeatherData) {
			WeatherData weather = (WeatherData) obs;
			this.lastPressure = currentPressure;
			this.currentPressure = weather.getPressure();
			display();
		}

	}
	
	
	/* (non-Javadoc)
	 * @see spr.board.web.observer.DisplayElement#display()
	 */
	@Override
	public void display() {
		System.out.println("Forecast");
		if(currentPressure > lastPressure) {
			System.out.println("기압증가");
		} else if(currentPressure == lastPressure) {
			System.out.println("기압 변동 없음");
		} else if(currentPressure < lastPressure) {
			System.out.println("기압 하강");
		}
	}

}
