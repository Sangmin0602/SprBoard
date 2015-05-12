package spr.board.web.decoratorPattern;

public abstract class Beverage {
	protected String description = "제목없음";
	
	public abstract double cost();
	
	public String getDescription() {
		return description;
	}
}
