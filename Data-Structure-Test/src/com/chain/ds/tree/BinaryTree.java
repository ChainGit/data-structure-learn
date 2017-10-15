package com.chain.ds.tree;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.chain.ds.Iterator;
import com.chain.ds.queue.LinkedQueue;
import com.chain.ds.queue.Queue;

/**
 * 二叉树
 * 
 * @author Chain
 *
 * @param <E>
 */
public class BinaryTree<E> extends AbstractBinaryTree<E> {

	private BinaryTreeNode<E> rootNode;

	private int count;

	/**
	 * 构建排序二叉树
	 * 
	 */
	@Override
	public boolean add(E value) {
		if (rootNode == null) {
			rootNode = new BinaryTreeNode<>(value);
			count++;
			return true;
		}

		BinaryTreeNode<E> node = new BinaryTreeNode<>(value);

		BinaryTreeNode<E> currentNode = rootNode;
		BinaryTreeNode<E> lastNode = currentNode;
		boolean flag = true;
		while (currentNode != null) {
			lastNode = currentNode;
			if (currentNode.compareTo(node) >= 0) {
				currentNode = currentNode.getLeft();
				flag = true;
			} else {
				currentNode = currentNode.getRight();
				flag = false;
			}
		}

		if (currentNode == null) {
			if (flag) {
				lastNode.setLeft(node);
			} else {
				lastNode.setRight(node);
			}
		}

		count++;

		return true;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		if (rootNode == null)
			return true;
		return false;
		// return count == 0;
	}

	@Override
	public void clear() {
		rootNode = null;
		count = 0;
	}

	/**
	 * 广度优先遍历
	 * 
	 */
	@Override
	public List<E> bfsToList() {
		List<E> lst = new ArrayList<>();
		if (isEmpty())
			return lst;

		// Queue<BinaryTreeNode<E>> queue = new ArrayQueue<>();
		Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
		queue.add(rootNode);

		getLeftAndRightThenPushToQueue(lst, queue);

		return lst;
	}

	private void getLeftAndRightThenPushToQueue(List<E> lst, Queue<BinaryTreeNode<E>> queue) {
		int size = size();
		for (int i = 0; i < size; i++) {
			BinaryTreeNode<E> parentNode = queue.poll();
			lst.add(parentNode.getValue());
			BinaryTreeNode<E> leftNode = parentNode.getLeft();
			BinaryTreeNode<E> rightNode = parentNode.getRight();
			if (leftNode != null)
				queue.add(leftNode);
			if (rightNode != null)
				queue.add(rightNode);
		}
		if (!queue.isEmpty())
			getLeftAndRightThenPushToQueue(lst, queue);
	}

	protected static class BinaryTreeNode<E> implements Comparable<BinaryTreeNode<E>> {

		private E value;
		private BinaryTreeNode<E> left;
		private BinaryTreeNode<E> right;

		public BinaryTreeNode(E value) {
			super();
			this.value = value;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public BinaryTreeNode<E> getLeft() {
			return left;
		}

		public void setLeft(BinaryTreeNode<E> left) {
			this.left = left;
		}

		public BinaryTreeNode<E> getRight() {
			return right;
		}

		public void setRight(BinaryTreeNode<E> right) {
			this.right = right;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;
			if (obj == null)
				return false;
			if (obj.getClass() != BinaryTreeNode.class)
				return false;
			BinaryTreeNode node = (BinaryTreeNode) obj;
			return this.compareTo(node) == 0;
		}

		@Override
		public int compareTo(BinaryTreeNode<E> other) {
			if (other == null)
				throw new RuntimeException("node is null");

			E otherValue = other.getValue();

			if (otherValue instanceof Integer && value instanceof Integer)
				return ((Integer) value).compareTo((Integer) otherValue);

			return 0;
		}

		@Override
		public String toString() {
			return value.toString();
		}

	}

	@Override
	public List<E> inOrderToList() {
		List<E> lst = new ArrayList<>();
		inOrder(rootNode, lst);
		return lst;
	}

	private void inOrder(BinaryTreeNode<E> node, List<E> lst) {
		if (node == null)
			return;

		inOrder(node.getLeft(), lst);
		lst.add(node.getValue());
		inOrder(node.getRight(), lst);
	}

	@Override
	public List<E> preOrderToList() {
		List<E> lst = new ArrayList<>();
		preOrder(rootNode, lst);
		return lst;
	}

	private void preOrder(BinaryTreeNode<E> node, List<E> lst) {
		if (node == null)
			return;

		lst.add(node.getValue());
		preOrder(node.getLeft(), lst);
		preOrder(node.getRight(), lst);
	}

	@Override
	public List<E> postOrderToList() {
		List<E> lst = new ArrayList<>();
		postOrder(rootNode, lst);
		return lst;
	}

	private void postOrder(BinaryTreeNode<E> node, List<E> lst) {
		if (node == null)
			return;

		postOrder(node.getLeft(), lst);
		postOrder(node.getRight(), lst);
		lst.add(node.getValue());
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object value) {
		// 二叉树删除节点总共有四种情况，需要考虑进根节点
		BinaryTreeNode<E> currentNode = rootNode;
		BinaryTreeNode<E> parentNode = rootNode;
		// 如果为空树
		if (currentNode == null || value == null)
			return false;

		BinaryTreeNode<E> findNode = new BinaryTreeNode<>((E) value);

		// 类似插入节点，从根节点依次往下找，找到这个节点或直到为空
		// 往左为true
		boolean flag = true;
		while (true) {
			// 往左走
			if (currentNode.compareTo(findNode) > 0) {
				parentNode = currentNode;
				currentNode = currentNode.getLeft();
				flag = true;
			}
			// 往右走
			else if (currentNode.compareTo(findNode) < 0) {
				parentNode = currentNode;
				currentNode = currentNode.getRight();
				flag = false;
			}
			// 相等
			else {
				break;
			}
			// 没有找到该节点
			if (currentNode == null) {
				return false;
			}
		}

		// System.out.println(currentNode.value);
		// System.out.println(parentNode.value);

		// 如果找到的节点为叶子节点，即没有左右孩子
		if (currentNode.getLeft() == null && currentNode.getRight() == null) {
			if (currentNode == rootNode) {
				rootNode = null;
			}
			// 是父节点的左孩子
			else if (flag) {
				parentNode.setLeft(null);
			}
			// 是父节点的右孩子
			else if (!flag) {
				parentNode.setRight(null);
			}
		}
		// 如果找到的节点有右孩子
		else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
			BinaryTreeNode<E> right = currentNode.getRight();
			if (currentNode == rootNode) {
				rootNode = right;
			} else if (flag) {
				parentNode.setLeft(right);
			} else if (!flag) {
				parentNode.setRight(right);
			}
		}
		// 如果找到的节点有左孩子
		else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
			BinaryTreeNode<E> left = currentNode.getLeft();
			if (currentNode == rootNode) {
				rootNode = left;
			} else if (flag) {
				parentNode.setLeft(left);
			} else if (!flag) {
				parentNode.setRight(left);
			}
		}
		// 如果找到的节点有两个孩子，找到中序后继节点
		else {
			BinaryTreeNode<E> successorNode = getSuccessor(currentNode);

			// 然后将currentNode替换成successorNode
			if (currentNode == rootNode) {
				rootNode = successorNode;
			} else if (flag) {
				parentNode.setLeft(successorNode);
			} else if (!flag) {
				parentNode.setRight(successorNode);
			}

			// 只可能有这种情况
			successorNode.setLeft(currentNode.getLeft());
		}

		count--;

		return true;
	}

	// 找到中序后继节点：在剩余子树中找出所有比被删除节点的值 大 的所有数，并在这些数中找出一个 最小 的数来
	private BinaryTreeNode<E> getSuccessor(BinaryTreeNode<E> deleteNode) {
		BinaryTreeNode<E> currentNode = deleteNode;
		// 右节点不为空，比要删除节点大的都在右子树
		BinaryTreeNode<E> successorNode = currentNode.getRight();
		BinaryTreeNode<E> successorParentNode = successorNode;
		while (currentNode != null) {
			successorParentNode = successorNode;
			successorNode = currentNode;
			// 最小的节点又都在左子树
			currentNode = currentNode.getLeft();
		}
		// 如果中序后继节点不是要删除的节点的右孩子
		if (successorNode != deleteNode.getRight()) {
			// 中序后继节点只可能有右孩子，将其放到中序后继的位置
			successorParentNode.setLeft(successorNode.getRight());
			// 中继后继的节点的右孩子（肯定存在）连接到要删除的节点的右孩子上（也肯定存在）防止出现游离状态而被回收
			successorNode.setRight(deleteNode.getRight());
		}
		return successorNode;
	}

	@Override
	public void mirror() {
		BinaryTreeNode<E> currentNode = rootNode;

		mirrorFun(currentNode);
	}

	private void mirrorFun(BinaryTreeNode<E> currentNode) {
		if (currentNode == null)
			return;

		// 交换当前节点的左右孩子节点，无论是否为空
		BinaryTreeNode<E> tempNode = currentNode.getLeft();
		currentNode.setLeft(currentNode.getRight());
		currentNode.setRight(tempNode);

		// 递归继续交换
		mirrorFun(currentNode.getLeft());
		mirrorFun(currentNode.getRight());
	}

	@Override
	public List<E> inOrderLoopToList() {
		List<E> lst = new ArrayList<>();
		inOrderLoop(lst);
		return lst;
	}

	// 递归也是栈操作，那么循环做法其实是手动栈，不会存在栈溢出的问题
	private void inOrderLoop(List<E> lst) {
		LinkedList<BinaryTreeNode<E>> stack = new LinkedList<>();
		BinaryTreeNode<E> currentNode = rootNode;
		while (currentNode != null || !stack.isEmpty()) {
			if (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.getLeft();
			} else {
				currentNode = stack.pop();
				lst.add(currentNode.getValue());
				currentNode = currentNode.getRight();
			}
		}
	}

	@Override
	public List<E> preOrderLoopToList() {
		List<E> lst = new ArrayList<>();
		preOrderLoop(lst);
		return lst;
	}

	private void preOrderLoop(List<E> lst) {
		LinkedList<BinaryTreeNode<E>> stack = new LinkedList<>();
		BinaryTreeNode<E> currentNode = rootNode;
		while (currentNode != null || !stack.isEmpty()) {
			if (currentNode != null) {
				stack.push(currentNode);
				lst.add(currentNode.getValue());
				currentNode = currentNode.getLeft();
			} else {
				currentNode = stack.pop();
				currentNode = currentNode.getRight();
			}
		}
	}

	@Override
	public List<E> postOrderLoopToList() {
		List<E> lst = new ArrayList<>();
		postOrderLoop(lst);
		return lst;
	}

	// 用于后序遍历的循环做法的标志
	// 1：访问过左节点 2：访问过右节点
	private static final int LEFT_VISITED = 1;
	// 后序遍历的情况下，如果为2则代表也访问过了左节点
	private static final int RIGHT_VISITED = 2;

	// 后序遍历的循环做法复杂一些，需要对原来的树的节点加上访问标记
	private void postOrderLoop(List<E> lst) {
		LinkedList<BinaryTreeNode<E>> stack = new LinkedList<>();
		BinaryTreeNode<E> currentNode = rootNode;
		Map<BinaryTreeNode<E>, Integer> flags = new HashMap<>(count);
		// 先访问左节点 ，再访问右节点，最后访问根节点
		while (currentNode != null || !stack.isEmpty()) {
			// 访问左节点
			while (currentNode != null) {
				stack.push(currentNode);
				flags.put(currentNode, LEFT_VISITED);
				currentNode = currentNode.getLeft();
			}
			// 左右子树均访问完毕，此时输出子树的根节点
			while (!stack.isEmpty() && (flags.get(stack.peek()) == RIGHT_VISITED)) {
				BinaryTreeNode<E> node = stack.pop();
				lst.add(node.getValue());
			}
			// 访问右节点
			if (!stack.isEmpty()) {
				currentNode = stack.peek();
				flags.put(currentNode, RIGHT_VISITED);
				currentNode = currentNode.getRight();
			}
		}
	}

	// 利用自定义栈代替程序栈
	@Override
	public void mirrorLoop() {
		if (rootNode == null)
			return;
		LinkedList<BinaryTreeNode<E>> stack = new LinkedList<>();
		stack.push(rootNode);
		BinaryTreeNode<E> currentNode = null;
		while (!stack.isEmpty()) {
			currentNode = stack.pop();

			BinaryTreeNode<E> tempNode = currentNode.getLeft();
			currentNode.setLeft(currentNode.getRight());
			currentNode.setRight(tempNode);

			BinaryTreeNode<E> leftNode = currentNode.getLeft();
			if (leftNode != null)
				stack.push(leftNode);

			BinaryTreeNode<E> rightNode = currentNode.getRight();
			if (rightNode != null)
				stack.push(rightNode);
		}

	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * 二叉树的迭代器
	 * 
	 * 中序遍历的结果
	 * 
	 * @author Chain
	 *
	 * @param <E>
	 */
	private class Itr implements Iterator<E> {
		int cursor;
		int expectCount;

		// 中序遍历的结果
		List<E> lst;

		{
			expectCount = count;
			lst = inOrderLoopToList();
		}

		@Override
		public boolean hasNext() {
			return cursor < count;
		}

		@Override
		public E next() {
			checkForComodification();
			int i = cursor;
			// 在使用next之前需要先调用一下hasNext
			if (!hasNext())
				throw new NoSuchElementException();
			List<E> list = lst;
			if (i >= list.size())
				throw new ConcurrentModificationException();
			// 不能直接使用cursor++
			cursor = i + 1;
			return list.get(i);
		}

		final void checkForComodification() {
			// 如果队列中的元素和迭代器中存储的数量不一致，则代表在迭代过程进行了增加或者删除操作。
			if (expectCount != count) {
				throw new ConcurrentModificationException();
			}
		}

	}
}
