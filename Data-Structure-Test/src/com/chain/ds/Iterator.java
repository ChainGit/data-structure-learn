package com.chain.ds;

/**
 * 迭代器公共接口
 * 
 * @author Chain
 *
 * @param <E>
 */
public interface Iterator<E> {

	/**
	 * 是否有下一个元素
	 * 
	 * @return
	 */
	boolean hasNext();

	/**
	 * 获得下一个元素
	 * 
	 * @return
	 */
	E next();

	/**
	 * 迭代过程中元素是不可以删除的
	 */
	default void remove() {
		throw new UnsupportedOperationException("remove");
	}

}
