package spr.board.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class ConvertToXmlTestList2 {

	private List<ConvertToXmlTest> messages;
	
	public ConvertToXmlTestList2() {}
	
	public ConvertToXmlTestList2(List<ConvertToXmlTest> messages) {
		this.messages = messages;
	}
	
	public List<ConvertToXmlTest> getMessages() {
		return messages;
	}
}
