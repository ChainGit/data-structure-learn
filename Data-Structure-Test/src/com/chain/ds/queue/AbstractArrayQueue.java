package com.chain.ds.queue;

import com.chain.ds.array.Array;

/**
 * 数组实现单端队列的抽象类
 * 
 * @author Chain
 *
 * @param <E>
 */
public abstract class AbstractArrayQueue<E> extends AbstractQueue<E> implements Queue<E>, Array<E> {

	/**
	 * 队列是否已满
	 * 
	 * @return
	 */
	protected abstract boolean isFull();

	/**
	 * 扩大数组容量
	 * 
	 * @param a
	 * @return
	 */
	public abstract void extend();

}
