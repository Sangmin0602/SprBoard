package spr.board.web.facade;

import java.util.ArrayList;
import java.util.Collection;

public class Node {
	
	String name = null; //이름
	String value = null; //값
	
	Node parent = null; //부모노드
	
	//이펙티브 자바항목 34(p205) : 인터페이스 타입으로 객체를 참조하라.
	//자식노드들.
	Collection<Node> childs = new ArrayList<Node>();
	
	Node(Node parent, String attributeName, String attributeValue) {
		this.parent = parent;
		if(parent != null) 
			parent.addChilde(this);
		
		name = attributeName;
		value = attributeValue;
	}

	private void addChilde(Node child) {
		childs.add(child);
	}
	
}
