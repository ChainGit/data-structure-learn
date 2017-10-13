package com.chain.ds;

/**
 * 集合类公共接口
 * 
 * 所有集合类的根接口
 * 
 * @author Chain
 *
 */
public interface Collection<E> extends Iterable<E> {

	/**
	 * 获得迭代器
	 */
	@Override
	Iterator<E> iterator();

	/**
	 * 集合内元素的个数
	 * 
	 * @return
	 */
	int size();

	/**
	 * 集合是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 清空集合
	 */
	void clear();

	/**
	 * 添加元素
	 * 
	 * 使用泛型能确保安全
	 * 
	 * @param e
	 * @return
	 */
	boolean add(E e);

	/**
	 * 移除元素
	 * 
	 * 不使用泛型
	 * 
	 * @param obj
	 * @return
	 */
	boolean remove(Object obj);

	/**
	 * 集合内是否包含某元素
	 * 
	 * 不使用泛型
	 * 
	 * @param o
	 * @return
	 */
	boolean contains(Object o);

	/**
	 * 重写equals必须重写hashCode方法
	 * 
	 * hash涉及到元素的存储
	 * 
	 * @return
	 */
	@Override
	int hashCode();

	/**
	 * 集合内元素相等往往是元素中内容的判断
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	boolean equals(Object obj);

	/**
	 * 主要用于调试输出
	 * 
	 * @return
	 */
	@Override
	String toString();
}