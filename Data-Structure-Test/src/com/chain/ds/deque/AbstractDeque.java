package com.chain.ds.deque;

import com.chain.ds.queue.AbstractQueue;

/**
 * 双端队列的抽象实现类
 * 
 * @author Chain
 *
 * @param <E>
 */
public abstract class AbstractDeque<E> extends AbstractQueue<E> implements Deque<E> {

	/**
	 * 弹出栈顶元素
	 * 
	 * 值得注意的是： 双端队列Deque充当Stack使用时，在JavaAPI中，压栈和出栈都是在队列<b>首部</b>实现的
	 * 
	 * 使用的是抛出异常的removeFirst方法
	 */
	@Override
	public E pop() {
		return removeFirst();
	}

	/**
	 * 栈顶压入元素
	 * 
	 * 值得注意的是：同pop，操作是在顶端完成的
	 * 
	 * 使用的是抛出异常的addFirst方法
	 * 
	 */
	@Override
	public boolean push(E e) {
		return addFirst(e);
	}

	@Override
	public boolean addFirst(E e) {
		if (offerFirst(e))
			return true;
		else
			throw new IllegalStateException("deque addFirst fail");
	}

	@Override
	public E removeFirst() {
		E e = pollFirst();
		if (e != null)
			return e;
		else
			throw new IllegalStateException("deque removeFirst fail");
	}

	@Override
	public E getFirst() {
		E e = peekFirst();
		if (e != null)
			return e;
		else
			throw new IllegalStateException("deque getFirst fail");
	}

	@Override
	public boolean addLast(E e) {
		if (offerLast(e))
			return true;
		else
			throw new IllegalStateException("deque addLast fail");
	}

	@Override
	public E removeLast() {
		E e = pollLast();
		if (e != null)
			return e;
		else
			throw new IllegalStateException("deque removeLast fail");
	}

	@Override
	public E getLast() {
		E e = peekLast();
		if (e != null)
			return e;
		else
			throw new IllegalStateException("deque getLast fail");
	}

}
