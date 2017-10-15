package com.chain.ds.tree;

import java.util.List;

/**
 * 二叉树的公共接口
 * 
 * @author Chain
 *
 * @param <E>
 */
public interface BTree<E> extends Tree<E> {

	/**
	 * 构建排序二叉树
	 * 
	 */
	@Override
	boolean add(E value);

	/**
	 * 删除二叉树的节点
	 * 
	 * @return
	 */
	@Override
	boolean remove(Object value);

	/**
	 * 广度优先遍历
	 * 
	 */
	List<E> bfsToList();

	/**
	 * 中序遍历
	 * 
	 * @return
	 */
	List<E> inOrderToList();

	/**
	 * 前序遍历
	 * 
	 * @return
	 */
	List<E> preOrderToList();

	/**
	 * 后序遍历
	 * 
	 * @return
	 */
	List<E> postOrderToList();

	/**
	 * 中序遍历(循环)
	 * 
	 * @return
	 */
	List<E> inOrderLoopToList();

	/**
	 * 前序遍历(循环)
	 * 
	 * @return
	 */
	List<E> preOrderLoopToList();

	/**
	 * 后序遍历(循环)
	 * 
	 * @return
	 */
	List<E> postOrderLoopToList();

	/**
	 * 镜像二叉树
	 */
	public void mirror();

	/**
	 * 镜像二叉树(循环)
	 */
	public void mirrorLoop();
}
