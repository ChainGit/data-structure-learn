package com.chain.ds.deque;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.chain.ds.Iterator;

/**
 * 链表双端队列
 * 
 * @author Chain
 *
 * @param <E>
 */
public class LinkedDeque<E> extends AbstractDeque<E> {

	// 没有额外的控制节点
	private DequeNode headNode;
	private DequeNode tailNode;

	private int count;

	@Override
	public boolean offerFirst(E e) {
		if (isEmpty()) {
			initDeque(e);
			return true;
		}

		DequeNode node = new DequeNode(e);
		headNode.prevNode = node;
		node.nextNode = headNode;
		headNode = node;

		count++;

		return true;
	}

	private void initDeque(E e) {
		DequeNode node = new DequeNode(e);
		headNode = tailNode = node;
		count++;
	}

	// Deque中的pollFirst相当于Queue中的poll
	@Override
	public E pollFirst() {
		return poll();
	}

	// Deque中的peekFirst相当于Queue中的peek
	@Override
	public E peekFirst() {
		return peek();
	}

	// Deque中的offerLast相当于Queue中的offer
	@Override
	public boolean offerLast(E e) {
		return offer(e);
	}

	@Override
	public E pollLast() {
		if (isEmpty())
			return null;

		E value = tailNode.value;
		tailNode = tailNode.prevNode;
		if (tailNode != null)
			tailNode.nextNode = null;
		count--;
		if (isEmpty()) {
			headNode = null;
			tailNode = null;
		}
		return value;
	}

	@Override
	public E peekLast() {
		if (isEmpty())
			return null;

		E value = tailNode.value;
		return value;
	}

	@Override
	public boolean offer(E e) {
		if (isEmpty()) {
			initDeque(e);
			return true;
		}

		DequeNode node = new DequeNode(e);
		tailNode.nextNode = node;
		node.prevNode = tailNode;
		tailNode = node;

		count++;

		return true;
	}

	@Override
	public E poll() {
		if (isEmpty())
			return null;

		E value = headNode.value;
		headNode = headNode.nextNode;
		if (headNode != null)
			headNode.prevNode = null;
		count--;
		if (isEmpty()) {
			headNode = null;
			tailNode = null;
		}
		return value;
	}

	@Override
	public E peek() {
		if (isEmpty())
			return null;

		E value = headNode.value;
		return value;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public void clear() {
		headNode = tailNode = null;
		count = 0;
	}

	/**
	 * 链表双端队列的节点
	 * 
	 * @author Chain
	 *
	 */
	@SuppressWarnings("unused")
	private class DequeNode {
		private E value;
		private DequeNode prevNode;
		private DequeNode nextNode;

		public DequeNode(E value) {
			super();
			this.value = value;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public DequeNode getPrevNode() {
			return prevNode;
		}

		public void setPrevNode(DequeNode prevNode) {
			this.prevNode = prevNode;
		}

		public DequeNode getNextNode() {
			return nextNode;
		}

		public void setNextNode(DequeNode nextNode) {
			this.nextNode = nextNode;
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
			DequeNode other = (DequeNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@SuppressWarnings("rawtypes")
		private LinkedDeque getOuterType() {
			return LinkedDeque.this;
		}

		@Override
		public String toString() {
			return value.toString();
		}

	}

	/**
	 * 链表双端队列的迭代器
	 * 
	 * @author Chain
	 *
	 */
	private class Itr implements Iterator<E> {
		DequeNode cursor;
		DequeNode lastRet;
		int expectCount;

		{
			cursor = headNode;
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
			DequeNode i = cursor;
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
			// 在队列中元素是有序的
			// 双端队列的首尾元素是可以被移除的
			// LinkedList最灵活，功能最强大
			if (lastRet == headNode) {
				try {
					// 迭代器中使用抛出异常的remove
					LinkedDeque.this.remove();
					cursor = lastRet;
					lastRet = null;
					expectCount = count;
				} catch (Exception e) {
					throw new ConcurrentModificationException();
				}
			} else if (lastRet == tailNode) {
				try {
					// 迭代器中使用抛出异常的removeLast
					LinkedDeque.this.removeLast();
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

}
