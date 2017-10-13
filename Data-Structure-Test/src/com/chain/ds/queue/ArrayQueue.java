package com.chain.ds.queue;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.chain.ds.Iterator;
import com.chain.ds.array.Array;

/**
 * 数组实现单端队列
 * 
 * 队列的队中元素的访问可以使用迭代器，无需开辟额外的方法
 * 
 * @author Chain
 *
 * @param <E>
 */
public class ArrayQueue<E> extends AbstractQueue<E> implements Array<E> {

	private Object[] data;

	// 不使用count也是可以的
	private int count;

	// 指向队列的头元素所在的位置的下标
	private int head;
	// 指向队列的尾元素所在的位置的后一个下标，这样做会浪费一个数组位置
	// 如果使用count来作为isEmpty，isFull，和size的判断的话无需指向后一个下标，可以直接指向所在位置的下标
	private int tail;

	private static int capacity = 8;

	public ArrayQueue() {
		super();
		this.data = new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		return super.add(e);
	}

	@Override
	public E remove() {
		return super.remove();
	}

	@Override
	public E element() {
		return super.element();
	}

	@Override
	public boolean offer(E value) {
		if (isFull()) {
			extend();
		}

		data[tail] = value;
		tail = (tail + 1) % capacity;
		count++;

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E poll() {
		if (isEmpty())
			return null;

		E temp = (E) data[head];
		data[head] = null;
		head = (head + 1) % capacity;
		count--;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		if (isEmpty())
			return null;

		E temp = (E) data[head];
		return temp;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	@Override
	public int size() {
		int size = (capacity - (head - tail)) % capacity;
		return size;

		// 也可以这样做
		// return count;
	}

	// 队列当前的容量是否已满，可用于容量扩展
	private boolean isFull() {
		// 假设head和tail是不重叠的，即tail和head之间空一格
		if ((tail + 1) % capacity == head)
			return true;
		return false;

		// 也可以这样做
		// return size() == capacity;
	}

	@Override
	public boolean isEmpty() {
		// 如果head和tail重叠，则为空
		if (head == tail)
			return true;
		return false;

		// 也可以这样做
		// return size() == 0;
	}

	@Override
	public void clear() {
		head = tail = count = 0;
		capacity = 8;
		data = new Object[capacity];
	}

	@Override
	public synchronized void extend() {
		try {
			// 方法很多，这里使用pull再push的方法
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

	/**
	 * ArrayQueue的迭代器
	 * 
	 * @author Chain
	 *
	 */
	private class Itr implements Iterator<E> {
		// 指针，指向迭代器当前所指的元素
		int cursor;
		// 迭代器内存储的ArrayQueue中的元素个数
		int expectCount;
		// 用于remove
		int lastRet = DEFAULT;

		static final int DEFAULT = -1;

		{
			// 初始化为head
			cursor = head;
			// 初始化为原存储ArrayQueue元素的个数
			expectCount = count;
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
			Object[] elementData = ArrayQueue.this.data;
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
			// 在队列中元素是有序的，所以第一个元素就是队列首，可以移除
			// 但是队列中间的部分和尾部由于队列的定义不能被移除
			// LinkedList最灵活，功能最强大
			if (lastRet == head) {
				try {
					// 迭代器中使用抛出异常的remove
					ArrayQueue.this.remove();
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
