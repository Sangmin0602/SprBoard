package spr.board.web.observer;

import java.util.ArrayList;
import java.util.Observer;

public class WeatherData2 implements Observerable {
	 private ArrayList<Observer> observers;            // 등록할 옵저버 리스트
	    private float temperature;                        // 온도
	    private float humidity;                            // 습도
	    private float pressure;                            // 기압
	    
	    public WeatherData2(){                            // 생성자
	        observers = new ArrayList<Observer>();        // 옵저버를 등록할 수 있는 ArrayList 생성
	    }
	    
	    // 옵저버 등록
	    public void addObserver(Observer o){            
	        observers.add(o);
	    }
	    
	    // 옵저버 제거
	    public void deleteObserver(Observer o){            
	        int i = observers.indexOf(o);                
	        if(i>=0){
	            observers.remove(i);
	        }
	    }
	    
	    // 옵저버에 데이터 전달
	    public void notifyObservers(){                            
	        for(int i=0; i<observers.size(); i++){
	            Observer observer = (Observer)observers.get(i);
	            observer.update(null,this);    // update로 옵저버에게 전달한다.
	        }
	    }
	    
	    // 최신 데이터 갱신,  기상 스테이션에서 이 메소드로 최신의 데이터를 던져준다.
	    public void setMeasurements(float temperature, float humidity, float pressure){
	        this.temperature = temperature;
	        this.humidity = humidity;
	        this.pressure = pressure;
	        measurementsChanged();
	    }
	    
	    // 데이터 변경 발생
	    public void measurementsChanged(){
	        notifyObservers();                // 데이터 변경 발생시 옵저버들에게 데이터를 전달한다.
	    }

		public ArrayList<Observer> getObservers() {
			return observers;
		}

		public void setObservers(ArrayList<Observer> observers) {
			this.observers = observers;
		}

		public float getTemperature() {
			return temperature;
		}

		public void setTemperature(float temperature) {
			this.temperature = temperature;
		}

		public float getHumidity() {
			return humidity;
		}

		public void setHumidity(float humidity) {
			this.humidity = humidity;
		}

		public float getPressure() {
			return pressure;
		}

		public void setPressure(float pressure) {
			this.pressure = pressure;
		}
	    
	    // getter, setter 구현
	   //  getter, setter 생략....  소스코드 확인

}
