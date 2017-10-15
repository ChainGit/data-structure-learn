package com.chain.ds.test;

import java.util.List;

import org.junit.Test;

import com.chain.ds.tree.BTree;
import com.chain.ds.tree.BinaryTree;

/**
 * 树的测试
 * 
 * @author Chain
 *
 */
public class TreeTest {

	@Test
	public void testTree() {
		BTree<Integer> tree = new BinaryTree<>();

		System.out.println(tree.size());
		System.out.println(tree.isEmpty());

		System.out.println(tree.bfsToList());
		System.out.println(tree.preOrderToList());
		System.out.println(tree.inOrderToList());
		System.out.println(tree.postOrderToList());

		System.out.println(tree.preOrderLoopToList());
		System.out.println(tree.inOrderLoopToList());
		System.out.println(tree.postOrderLoopToList());

		tree.add(32);
		tree.add(28);
		tree.add(56);
		tree.add(14);
		tree.add(25);
		tree.add(51);
		tree.add(68);
		tree.add(78);
		tree.add(44);
		tree.add(41);
		tree.add(40);
		tree.add(42);
		tree.add(72);
		tree.add(79);

		System.out.println(tree.size());
		System.out.println(tree.isEmpty());

		System.out.println(tree.bfsToList());
		System.out.println(tree.preOrderToList());
		System.out.println(tree.inOrderToList());
		System.out.println(tree.postOrderToList());

		// 二叉树的中序遍历输出的即是已经排序好的数组
		// 要求：查找二叉排序树的最接近k的节点

		int k = 24;
		List<Integer> list = tree.inOrderToList();

		int value = getClosest(list, k);
		System.out.println(value);

		// 测试二叉树的删除
		System.out.println(tree.remove(51));

		System.out.println(tree.size());
		System.out.println(tree.isEmpty());

		System.out.println(tree.bfsToList());
		System.out.println(tree.preOrderToList());
		System.out.println(tree.inOrderToList());
		System.out.println(tree.postOrderToList());

		// 镜像二叉树
		// 由顶而上，有大到小，利用递归实现
		tree.mirror();

		System.out.println(tree.size());
		System.out.println(tree.isEmpty());

		System.out.println(tree.bfsToList());
		System.out.println(tree.preOrderToList());
		System.out.println(tree.inOrderToList());
		System.out.println(tree.postOrderToList());

		// 镜像二叉树循环做法
		tree.mirrorLoop();

		System.out.println(tree.size());
		System.out.println(tree.isEmpty());

		System.out.println(tree.bfsToList());
		System.out.println(tree.preOrderToList());
		System.out.println(tree.inOrderToList());
		System.out.println(tree.postOrderToList());

		// 树的递归的循环做法
		System.out.println();
		System.out.println(tree.preOrderLoopToList());
		System.out.println(tree.inOrderLoopToList());
		System.out.println(tree.postOrderLoopToList());

		System.out.println();
		// 迭代器测试
		System.out.println(tree);
	}

	// 查找二叉排序树的最接近k的节点
	private int getClosest(List<Integer> list, int k) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			int current = list.get(i);
			if (current == k) {
				return current;
			} else if (current > k) {
				if (i > 0) {
					int before = list.get(i - 1);
					int d1 = current - k;
					int d2 = k - before;
					// 如果相等的话就返回排在前面的值
					return d2 > d1 ? current : before;
				} else {
					return current;
				}
			}
		}
		return list.get(size - 1);
	}

}
