package com.haizhen;

import java.util.HashMap;

/**
 * 前缀树
 * 
 * @author mahaizhen
 *
 * @date 2020年7月31日
 */
public class Trie<V> {

	private int size;

	private Node<V> root;

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		size = 0;
		root = null;

	}

	public V get(String key) {
		Node<V> node = node(key);
		return node != null && node.word ? node.value : null;
	}

	public boolean contains(String key) {
		Node<V> node = node(key);
		return node != null && node.word;
	}

	public V add(String key, V value) {
		keyCheck(key);
		if (root == null) {
			root = new Node<>(null);
		}
		Node<V> node = root;
		int len = key.length();
		for (int i = 0; i < len; i++) {
			char c = key.charAt(i);
			boolean emptyChildren = node.children == null;
			Node<V> childNode = emptyChildren ? null : node.children.get(c);
			if (childNode == null) {
				childNode = new Node<>(node);
				childNode.character = c;
				node.children = emptyChildren ? new HashMap<>() : node.children;
				node.children.put(c, childNode);
			}
			node = childNode;
		}
		if (node.word) {
			// 不是新增的单词
			V oldValue = node.value;
			node.value = value;
			return oldValue;
		}
		// 新增一个单词
		node.word = true;
		node.value = value;
		size++;
		return null;

	}

	public V remove(String key) {
		// 删除完整的单词
		// 找到最后一个节点
		Node<V> node = node(key);
		if (node == null || !node.word) {
			return null;
		}
		size--;
		V oldValue = node.value;
		// 如果还有子节点,
		if (node.children != null && !node.children.isEmpty()) {
			node.word = false;
			node.value = null;
			return oldValue;
		}
		// 没有子节点
		Node<V> parent = null;
		while ((parent = node.parent) != null) {
			parent.children.remove(node.character);
			// 表示还有其他的分支, 还有其他的子节点 , 父节点是红色的话, 也要退出外循环
			if (parent.word || !parent.children.isEmpty()) {
				// 退出外循环
				break;
			}
			node = parent;
		}
		return oldValue;
	}

	public boolean startsWith(String prefix) {
		return node(prefix) != null;
	}

	private Node<V> node(String key) {
		keyCheck(key);
		Node<V> node = root;
		int len = key.length();
		for (int i = 0; i < len; i++) {
			if (node == null || node.children == null || node.children.isEmpty()) {
				return null;
			}
			char c = key.charAt(i);
			node = node.children.get(c);
		}
		return node;

	}

	private void keyCheck(String key) {
		if (key == null || key.length() == 0) {
			throw new IllegalArgumentException("key must not be empty");

		}
	}

	private static class Node<V> {
		Node<V> parent;
		HashMap<Character, Node<V>> children;
		V value;
		boolean word; // 是否是单词的结尾, 默认是false 为true 的时候表示是单词的结尾
		Character character;

		public Node(Node<V> parent) {
			this.parent = parent;
		}

	}

}
