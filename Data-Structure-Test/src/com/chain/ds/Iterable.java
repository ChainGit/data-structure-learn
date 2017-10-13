package com.chain.ds;

/**
 * 代表可迭代的
 * 
 * @author Chain
 *
 * @param <T>
 */
public interface Iterable<T> {

	/**
	 * 获得该集合的迭代器
	 * 
	 * @return
	 */
	Iterator<T> iterator();
}
