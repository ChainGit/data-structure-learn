package com.chain.ds.queue;

import java.util.NoSuchElementException;

import com.chain.ds.AbstractCollection;

/**
 * 队列的抽象实现类
 * 
 * @author Chain
 *
 * @param <E>
 */
public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {

	// 抽象类不能被直接实例化，只能由子类实例化
	protected AbstractQueue() {
	}

	@Override
	public boolean add(E e) {
		if (offer(e))
			return true;
		else
			throw new IllegalStateException("queue add fail");
	}

	@Override
	public abstract boolean offer(E e);

	@Override
	public E remove() {
		E x = poll();
		if (x != null)
			return x;
		else
			throw new NoSuchElementException("queue remove fail");
	}

	@Override
	public abstract E poll();

	@Override
	public E element() {
		E x = peek();
		if (x != null)
			return x;
		else
			throw new NoSuchElementException("queue element fail");
	}

	@Override
	public abstract E peek();

	// 默认实现是一个一个依次poll()
	@Override
	public void clear() {
		while (poll() != null)
			;
	}

}
