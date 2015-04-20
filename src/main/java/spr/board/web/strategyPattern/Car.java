package spr.board.web.strategyPattern;

public class Car {
	private String name;
	private String price;
	
	public Car(String name, String price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		final String TAB = "	";
		StringBuffer sb = new StringBuffer();
		
		sb.append("Car (");
		sb.append(super.toString() + TAB);
		sb.append("name = " +name+TAB);
		sb.append("prive = " + price + TAB);
		sb.append(")");
		
		return sb.toString();
	}
	
	
}
