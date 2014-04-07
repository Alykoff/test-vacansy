/**
 * @author Alykov Gali
 * @date 20.02.2014
 * 
 * 
 * Разработать алгоритм, который:
 * 1) по указанной одномерной последовательности строит дерево из объектов класса Node 
 * (дочерние узлы запиываются в поле childList, числа, входящие в узел, – в numberList);
 * 2) выполняет обход дерева и выводит в консоль сумму чисел из поля numberList для каждого 
 * узла.
 * 
 */
// package by.clojurecourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		String[] treeStr = { 
				"[", "1", 
					"[", "222222", "3", 
						"[", "2", 
							"[", "3", "]",
							"[", "11", "]", 
						"]", 
					"]", 
					"4", 
					"[", "5", 
						"[", "6", "7", "]",
					"]", 
					"[", "8", "]", 
				"]" };
//		String[] treeStr2 = {"[", "1", "]"};
		System.out.println(Node.build(treeStr));
	}
}

class Node {
	private static final String LEFT_BRAKE = "[";
	private static final String RIGHT_BRAKE = "]";

	public List<Node> childList;
	public List<Integer> numberList;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Node() {
		childList = new ArrayList();
		numberList = new ArrayList();
	}

	public Node(List<Integer> numberList, List<Node> childNode) {
		if (numberList != null) {
			this.numberList = new ArrayList<Integer>(numberList);
		}
		if (childNode != null) {
			this.childList = new ArrayList<Node>(childNode);
		}
	}
	
	static Node build(List<String> tree) {
		Node node = new Node();
		Queue<String> brackets = new LinkedList<String>();
		List<String> subTree = new LinkedList<String>();
		String element = null;
		while (!tree.isEmpty()) {
			element = tree.remove(0);
			if (!brackets.isEmpty()) {
				subTree.add(element);
				if (element.equals(LEFT_BRAKE)) {
					brackets.offer(LEFT_BRAKE);
				} else if (element.equals(RIGHT_BRAKE)) {
					String headBrackets = brackets.poll();
					if (!headBrackets.equals(LEFT_BRAKE)) {
						throw new RuntimeException("Bad out params!");
					}
				}
				if (brackets.isEmpty()) {
					List<String> subN = subTree.subList(1, subTree.size() - 1);
					Node childNode = build(subN);
					node.childList.add(childNode);
					
					brackets = new LinkedList<String>();
					subTree = new LinkedList<String>();
				}
			} else if (element.equals(LEFT_BRAKE)) {
				subTree.add(element);
				brackets.offer(element);
			} else {
				try {
					Integer valueElement = Integer.parseInt(element);
					node.numberList.add(valueElement);
				} catch (NumberFormatException e) {
					throw new RuntimeException("In position = ", e);
				}
			}
		}
		return node;
	}

	static Node build(String[] tree) {
		if (tree == null) {
			throw new NullPointerException("bad args");
		}
		if (!tree[0].equals(LEFT_BRAKE)
				|| !tree[tree.length - 1].equals(RIGHT_BRAKE)) {
			throw new RuntimeException("Bad seq '[' and ']'");
		}
		if (tree.length == 2) {
			return new Node();
		}
		List<String> rawElements = new LinkedList<String>(Arrays.asList(tree));
		List<String> elements = rawElements.subList(1, rawElements.size() - 1);
		return build(elements);
	}

	public String toString(int level) {
		int sum = 0;
		for (Integer value : numberList) {
			sum += value;
		}
		StringBuilder builder = new StringBuilder();
		String tabs = "";
		for (int i = 0; i < level; i++) {
			if (i == level - 1) {
				int countSpace = 1;
				while (countSpace++ < level) tabs += " ";
				tabs += "|_";
			} else {
				tabs += " ";
			}
		}
		builder.append(tabs);
		builder.append(sum);
		builder.append("\n");
		for (Node child : childList) {
			builder.append(child.toString(level + 1));
		}
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return toString(0);
	}
}
