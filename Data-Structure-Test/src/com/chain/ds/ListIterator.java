package com.chain.ds;

/**
 * 用于List的迭代器
 * 
 * 指针指向元素之间
 * 
 * @author Chain
 *
 * @param <E>
 */
public interface ListIterator<E> extends Iterator<E> {

	@Override
	boolean hasNext();

	@Override
	E next();

	/**
	 * 是否有上一个元素
	 * 
	 * @return
	 */
	boolean hasPrevious();

	/**
	 * 获得上一个元素
	 * 
	 * @return
	 */
	E previous();

	/**
	 * 获得下一个元素所在的下标
	 * 
	 * @return
	 */
	int nextIndex();

	/**
	 * 获得上一个元素所在的下标
	 * 
	 * @return
	 */
	int previousIndex();

	/**
	 * 删除next()和previous()返回的元素，remove()方法调用的基础是add()方法没有被调用。
	 * 
	 */
	@Override
	void remove();

	/**
	 * 更新（替换）next()和previous()返回的元素，set()方法调用的基础是add()和remove()方法没有被调用
	 * 
	 * @param e
	 */
	void set(E e);

	/**
	 * 插入元素到next()返回的元素的前面，previous()返回的元素的后面
	 * 
	 * @param e
	 */
	void add(E e);

}
