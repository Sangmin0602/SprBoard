package spr.board.model;

import java.util.List;

public class jQueryJsonList {

	private List<JQueryJson> message;
	
	public jQueryJsonList(){}
	
	public jQueryJsonList(List<JQueryJson> message) {
		this.message = message;
	}
	
	public List<JQueryJson> getMessages() {
		return message;
	}
}
