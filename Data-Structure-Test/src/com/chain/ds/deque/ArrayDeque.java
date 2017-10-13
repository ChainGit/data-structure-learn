package com.chain.ds.deque;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.chain.ds.Iterator;
import com.chain.ds.array.Array;
import com.chain.ds.queue.ArrayQueue;

/**
 * 数组双端队列
 * 
 * @author Chain
 *
 * @param <E>
 */
public class ArrayDeque<E> extends AbstractDeque<E> implements Array<E> {

	private Object[] data;

	// 不使用count也是可以的
	private int count;

	// 指向队列的头元素所在的位置的下标（要删除或查看的元素位置）
	private int head;
	// 指向队列的尾元素所在的位置的后一个下标（要插入的元素位置）
	private int tail;

	/**
	 * 初始化容量的大小为2的倍数
	 * 
	 * 原因：<br>
	 * head = (head - 1) & (elements.length -1) 这段代码相当于模取余，同时解决了head为负值的情况。
	 * 
	 * 因为elements.length必需是2的指数倍， elements - 1就是二进制低位全1，跟head - 1相与之后就起到了取模的作用;
	 * 如果head - 1为负数（其实只可能是-1），则相当于对其取相对于elements.length的补码。
	 * 
	 * 逻辑运算比数学运算要快
	 */
	private static int capacity = 8;

	public ArrayDeque() {
		super();
		this.data = new Object[capacity];
	}

	@Override
	public boolean offerFirst(E e) {
		if (isFull()) {
			extend();
		}

		data[head = (head - 1) & (capacity - 1)] = e;
		count++;

		// 数组已满
		if (head == tail)
			extend();

		return true;
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

	@SuppressWarnings("unchecked")
	@Override
	public E pollLast() {
		if (isEmpty()) {
			return null;
		}

		int t = (tail - 1) & (capacity - 1);
		E value = (E) data[t];
		data[t] = null;
		tail = t;
		count--;
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peekLast() {
		if (isEmpty()) {
			return null;
		}

		int t = (tail - 1) & (capacity - 1);
		E value = (E) data[t];
		return value;
	}

	@Override
	public boolean offer(E e) {
		if (isFull()) {
			extend();
		}

		data[tail] = e;
		tail = (tail + 1) & (capacity - 1);
		count++;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E poll() {
		if (isEmpty())
			return null;

		int i = head;
		E value = (E) data[i];
		data[i] = null;
		head = (head + 1) & (capacity - 1);
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		if (isEmpty())
			return null;

		E value = (E) data[head];
		return value;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	@Override
	public void clear() {
		head = tail = count = 0;
		capacity = 8;
		data = new Object[capacity];
	}

	@Override
	public boolean isEmpty() {
		// 如果head和tail重叠，则为空
		// 注意如果是offerFirst操作head==tail则为已满
		return head == tail;

		// 也可以这样做
		// return size() == 0;
	}

	// 队列容量是否已满(这里的已满并不是数组已用完)
	private boolean isFull() {
		// 假设head和tail是不重叠的，即tail和head之间空一格
		if (((tail + 1) & (capacity - 1)) == head)
			return true;
		return false;

		// 也可以这样做
		// return size() == capacity;
	}

	@Override
	public int size() {
		// 就是head和tail中存储元素部分的大小
		int size = (tail - head) & (capacity - 1);
		return size;

		// 也可以这样做
		// return count;
	}

	@Override
	public void extend() {
		try {
			// 方法很多，这里使用poll再push的方法
			// 2倍指数扩展
			int newCapacity = capacity << 1;
			Object[] newData = new Object[newCapacity];
			int size = size();
			int index = 0;
			while (size-- > 0) {
				newData[index++] = poll();
			}

			head = 0;
			tail = count = index;
			capacity = newCapacity;
			data = newData;
		} catch (Exception e) {
			throw new RuntimeException("array queue extend");
		}
	}

	private class Itr implements Iterator<E> {
		int cursor;
		int expectCount;
		int lastRet = DEFAULT;

		static final int DEFAULT = -1;

		{
			expectCount = count;
			cursor = head;
		}

		@Override
		public boolean hasNext() {
			int remain = (capacity - (cursor - tail)) % capacity;
			return remain > 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			checkForComodification();
			// 提取出来先写在前面主要为了线程访问安全
			// 也用来记录cursor的历史值
			int i = cursor;
			// 在使用next之前需要先调用一下hasNext
			if (!hasNext())
				throw new NoSuchElementException();
			Object[] elementData = ArrayDeque.this.data;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			// 不能直接使用cursor++
			cursor = (i + 1) % capacity;
			return (E) elementData[lastRet = i];
		}

		@Override
		public void remove() {
			// 必须在使用了next时才能使用remove
			if (lastRet < 0)
				throw new IllegalStateException();
			checkForComodification();
			// 在队列中元素是有序的
			// 双端队列的首尾元素是可以被移除的
			// LinkedList最灵活，功能最强大
			if (lastRet == head) {
				try {
					// 迭代器中使用抛出异常的remove
					ArrayDeque.this.remove();
					cursor = lastRet;
					lastRet = DEFAULT;
					expectCount = count;
				} catch (Exception e) {
					throw new ConcurrentModificationException();
				}
			} else if (lastRet == tail) {
				try {
					// 迭代器中使用抛出异常的removeLast
					ArrayDeque.this.removeLast();
					cursor = lastRet;
					lastRet = DEFAULT;
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
