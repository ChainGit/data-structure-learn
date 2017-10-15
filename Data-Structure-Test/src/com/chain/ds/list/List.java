package com.chain.ds.list;

import com.chain.ds.Collection;
import com.chain.ds.ListIterator;

/**
 * 列表公共接口
 * 
 * @author Chain
 *
 * @param <E>
 */
public interface List<E> extends Collection<E> {

	/**
	 * 获得下标为index的元素
	 * 
	 * @param index
	 * @return
	 */
	E get(int index);

	/**
	 * 更新（替换）下标为index的元素
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	E set(int index, E element);

	/**
	 * 在下标为index的位置增加元素（后半部分元素后移）
	 * 
	 * @param index
	 * @param element
	 */
	void add(int index, E element);

	/**
	 * 移除下标为index的元素（后半部分元素前移）
	 * 
	 * @param index
	 * @return
	 */
	E remove(int index);

	/**
	 * 返回obj所在的下标index（从左往右第一次出现的位置）
	 * 
	 * @param o
	 * @return
	 */
	int indexOf(Object o);

	/**
	 * 返回obj所在的下标index（从右往左第一次出现的位置，或从左往右最后一次出现的位置）
	 * 
	 * @param o
	 * @return
	 */
	int lastIndexOf(Object o);

	ListIterator<E> listIterator();
}
