package com.chain.ds.queue;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.chain.ds.Iterator;

/**
 * 链表单端队列
 * 
 * @author Chain
 *
 * @param <E>
 */
public class LinkedQueue<E> extends AbstractQueue<E> {

	// 为了操作方便，单端队列的首节点不存储元素
	private QueueNode headNode;

	private int count;

	public LinkedQueue() {
		super();
		headNode = new QueueNode(null);
	}

	@Override
	public boolean offer(E e) {
		QueueNode node = new QueueNode(e);

		QueueNode currentNode = headNode;
		QueueNode lastNode = currentNode;
		while (currentNode != null) {
			lastNode = currentNode;
			currentNode = currentNode.getNextNode();
		}

		lastNode.setNextNode(node);
		count++;

		return true;
	}

	@Override
	public E poll() {
		if (isEmpty()) {
			return null;
		}

		QueueNode nextNode = headNode.getNextNode();
		E val = nextNode.getValue();
		QueueNode nextNextNode = nextNode.getNextNode();
		if (nextNextNode == null)
			headNode.setNextNode(null);
		else
			headNode.setNextNode(nextNextNode);

		count--;

		return val;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			return null;
		}

		QueueNode nextNode = headNode.getNextNode();
		E val = nextNode.getValue();
		return val;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * 队列是否为空
	 * 
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		QueueNode nextNode = headNode.getNextNode();
		return nextNode == null;

		// 也可以这样做
		// return count == 0;
	}

	/**
	 * 
	 * 清空队列
	 * 
	 */
	@Override
	public void clear() {
		headNode.setNextNode(null);
		count = 0;
	}

	/**
	 * 队列的现有节点数
	 * 
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * 链表单端队列的迭代器
	 * 
	 * @author Chain
	 *
	 * @param <E>
	 */
	private class Itr implements Iterator<E> {
		QueueNode cursor;
		QueueNode lastRet;
		int expectCount;

		{
			cursor = headNode.nextNode;
			expectCount = count;
		}

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public E next() {
			checkForComodification();
			// 提取出来先写在前面主要为了线程访问安全
			// 也用来记录cursor的历史值
			QueueNode i = cursor;
			// 在使用next之前需要先调用一下hasNext
			if (!hasNext())
				throw new NoSuchElementException();
			// 不能直接使用cursor++
			cursor = i.nextNode;
			return (lastRet = i).value;
		}

		@Override
		public void remove() {
			// 必须在使用了next时才能使用remove
			if (lastRet == null)
				throw new IllegalStateException();
			checkForComodification();
			// 在队列中元素是有序的，所以第一个元素就是队列首，可以移除
			// 但是队列中间的部分和尾部由于队列的定义不能被移除
			// LinkedList最灵活，功能最强大
			if (lastRet == headNode.nextNode) {
				try {
					// 迭代器中使用抛出异常的remove
					LinkedQueue.this.remove();
					cursor = lastRet;
					lastRet = null;
					expectCount = count;
				} catch (Exception e) {
					throw new ConcurrentModificationException();
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}

		final void checkForComodification() {
			// 如果队列中的元素和迭代器中存储的数量不一致，则代表在迭代过程进行了增加或者删除操作。
			if (expectCount != count) {
				throw new ConcurrentModificationException();
			}
		}

	}

	/**
	 * 单端链表队列的节点
	 * 
	 * @author Chain
	 *
	 * @param <T>
	 */
	private class QueueNode {

		private E value;
		private QueueNode nextNode;

		public E getValue() {
			return value;
		}

		@SuppressWarnings("unused")
		public void setValue(E value) {
			this.value = value;
		}

		public QueueNode getNextNode() {
			return nextNode;
		}

		public void setNextNode(QueueNode nextNode) {
			this.nextNode = nextNode;
		}

		public QueueNode(E value) {
			super();
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			QueueNode other = (QueueNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return value.toString();
		}

		@SuppressWarnings("rawtypes")
		private LinkedQueue getOuterType() {
			return LinkedQueue.this;
		}

	}

}
