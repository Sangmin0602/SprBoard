package spr.board.web.facade;

import java.util.Collection;

/**
 * 간단한 트리
 * @author sangmin
 *
 *
 *최소 지식 원칙 - 정말 친한 치구하고만 얘기하라
 *-> Facade 패턴을 사용하여 정말 친한 친구를 만들어라
 *-> Facade 패턴 : 일련의 행동을 그룹화 시켜라
 *시스템을 디자인 할때, 어떤 객체든 그 객체와 상호작용하는 것들에 주의하라
 *의존ㄴ도를 복잡하게 만들면, 코드 변경시 낭패.
 */
public class Tree {
	Node root = null;
	
	Tree(String rootName, String rootValue) {
		createRootNode(rootName, rootValue);
	}
	/**
	 * 노드의 앞부분을 출력한다.
	 * @param node 부모노드
	 * @param depth 트리깊이
	 * @return 자식노드
	 */
	static Collection<Node> print(Node node, int depth) {
		
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.println("<" + node.name + ">");
		
		if(!(node.value instanceof String) || !(node.value.equals(""))) {
			for(int i = 0; i <= depth; i++) {
				System.out.print("\t");
			}
			System.out.println(node.value);
		}
		
		return node.childs;
	}
	/*
	 * 노드의 뒷부분을 출력한다.
	 * ex</NAME>
	 * 
	 * @param node 부모노드
	 * @param depth 트리깊이
	 * @return 자식노드
	 */
	static Collection<Node> printEnd(Node node, int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.println("</" + node.name + ">");
		return node.childs;
	}
	/*
	 * 트리를 출력한다.
	 * @param root root 노드
	 * @param depth root의 깊이
	 */
	static void printAll(Node root, int depth) {
		Collection<Node> childnodes = null;
		
		childnodes = print(root, depth);
		
		if(childnodes.size() > 0) 
			++depth;
		
		for(Node child : childnodes) {
			printAll(child, depth);
		}
		
		if(childnodes.size() > 0) 
			--depth;
		
		printEnd(root, depth);
		
	}
	/*
	 * root노드를 생성한다.
	 * @param attributeName root노드 이름
	 * @param attributeValue root노드 값
	 */
	private void createRootNode(String attributeName, String attributeValue) {
		root = new Node(null, attributeName, attributeValue);
	}
	/*
	 * 자식노드를 생성한다.
	 * @param parent 자식노드의 부모
	 * @param attributeName 자식노드의 이름
	 * @param attributeValue 자식노드의 값
	 * @return 생성된 자식노드
	 */
	static Node createChildNode(Node parent, String attributeName, String atributeValue) {
		return new Node(parent, attributeName, atributeValue);
	}
	
	Node getRoot() {
		return root;
	}
	
	
	/*
	 * 
	 * 스터디 회원목록을 출력한다.
	 * @pram args
	 */
	public static void main(String[] args) {
		Tree tree = new Tree("스터디", "");
		
		Node root = tree.getRoot();
		
			Node oracle = tree.createChildNode(root, "그룹", "대용량데이터베이스");
				Node ora1 = tree.createChildNode(oracle, "멤버", "많음....;");
			
			Node java = tree.createChildNode(root, "그룹", "자바웹개발");
				Node member1 = tree.createChildNode(java, "멤버", "조재근");
					Node member1a = tree.createChildNode(member1, "디자인패턴", "템플릿 메소드");

				Node member2 = tree.createChildNode(java, "멤버", "최종인");
					Node member2a = tree.createChildNode(member2, "디자인패턴", "어댑터 & 파사드");
					Node member2b= tree.createChildNode(member2, "이펙티브자바", "c구분 바꾸기");
					
				Node member3 = tree.createChildNode(java, "멤버", "임상민");
					Node member3a = tree.createChildNode(member2, "디자인패턴", "어댑터 & 파사드");
					Node member3b= tree.createChildNode(member2, "이펙티브자바", "c구분 바꾸기");
					
		tree.printAll(tree.root, 0);
	}
	
}
