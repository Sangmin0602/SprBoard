package spr.board.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message-list")
public class ConvertToXmlTestList {

	@XmlElement(name = "message")
	private List<ConvertToXmlTest> messages;
	
	public ConvertToXmlTestList() {}
	
	public ConvertToXmlTestList(List<ConvertToXmlTest> messages) {
		this.messages = messages;
	}
	
	public List<ConvertToXmlTest> getMessages() {
		return messages;
	}
}
