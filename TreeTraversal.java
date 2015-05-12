package Algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class TreeTraversal {

	private static class Node {
		int val;
		Node left;
		Node right;

		public Node(int val) {
			this.val = val;
		}
	}

	private static Node root;

	/**
	 * 构造有序二叉树
	 * @param newNode
	 */
	public static void buildBinaryTree(int newNode) {
		root = insert(root, newNode);
	}

	private static Node insert(Node cur, int newNode) {
		if (cur == null) {
			cur = new Node(newNode);
		} else {
			if (newNode <= cur.val) {
				cur.left = insert(cur.left, newNode);
			} else {
				cur.right = insert(cur.right, newNode);
			}
		}
		return cur;

	}

	/**
	 * 树的深度周游算法
	 * @param cur
	 */
	public static void traverseTree(Node cur) {
		if (cur == null) {
			return;
		} else {
			// 通过放置此处三条语句的位置，可以实现前序，后序和中序的遍历
			traverseTree(cur.left);
			traverseTree(cur.right);
			System.out.println(cur.val);
		}
	}

	/**
	 * 树的广度周游算法
	 */
	public static void breadthFirst() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node cur = queue.peek();
			if (cur.left != null) {
				queue.offer(cur.left);
			}
			if (cur.right != null) {
				queue.offer(cur.right);
			}
			System.out.println(cur.val);
			queue.remove();
		}

	}

	/**
	 * 前根周游算法：非递归形式
	 */
	public static void preOrderTraversal() {
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visited = new HashSet<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.peek();
			if (!visited.contains(cur)) {
				System.out.println(cur.val);
				visited.add(cur);
			}
			if (cur.left != null && !visited.contains(cur.left)) {
				stack.push(cur.left);
				continue;
			}
			if (cur.right != null && !visited.contains(cur.right)) {
				stack.push(cur.right);
				continue;
			}
			stack.pop();
		}
	}

	/**
	 * 中根周游算法：非递归形式
	 */
	public static void medOrderTraversal() {
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visited = new HashSet<>();
		stack.push(root);
		visited.add(root);
		while (!stack.isEmpty()) {
			Node cur = stack.peek();
			if (cur.left != null && !visited.contains(cur.left)) {
				stack.push(cur.left);
				visited.add(cur.left);
				continue;
			}
			System.out.println(cur.val);
			stack.pop();
			if (cur.right != null && !visited.contains(cur.right)) {
				stack.push(cur.right);
				visited.add(cur.right);
				continue;
			}
		}
	}

	/**
	 * 后根周游算法，非递归形式
	 */
	public static void postOrderTraversal() {
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visited = new HashSet<>();
		stack.push(root);
		visited.add(root);
		while (!stack.isEmpty()) {
			Node cur = stack.peek();
			if (cur.left != null && !visited.contains(cur.left)) {
				stack.push(cur.left);
				visited.add(cur.left);
				continue;
			}
			if (cur.right != null && !visited.contains(cur.right)) {
				stack.push(cur.right);
				visited.add(cur.right);
				continue;
			}
			System.out.println(cur.val);
			stack.pop();
		}
	}

	public static void main(String[] args) {
		int[] values = { 5, 4, 1, 2, 8, 6, 10 };
		for (int i : values) {
			buildBinaryTree(i);
		}
		System.out.println("--------------------");
		traverseTree(root);
		System.out.println("--------------------");
		breadthFirst();
		System.out.println("--------------------");
		postOrderTraversal();
		System.out.println("--------------------");
		medOrderTraversal();
		System.out.println("--------------------");
		preOrderTraversal();
	}

}
