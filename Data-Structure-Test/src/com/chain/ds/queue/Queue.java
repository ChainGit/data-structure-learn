package com.chain.ds.queue;

import com.chain.ds.Collection;

/**
 * 单端队列公共接口
 * 
 * 队列是FIFO
 * 
 * <br>
 * 
 * <table BORDER CELLPADDING=3 CELLSPACING=1><br>
 * <caption>Queue的6个方法</caption>
 * <tr>
 * <td></td>
 * <td ALIGN=CENTER><em>会抛出异常</em></td>
 * <td ALIGN=CENTER><em>返回特殊值</em></td>
 * </tr>
 * <tr>
 * <td><b>插入</b></td>
 * <td>{@link Queue#add add(e)}</td>
 * <td>{@link Queue#offer offer(e)}</td>
 * </tr>
 * <tr>
 * <td><b>移除</b></td>
 * <td>{@link Queue#remove remove()}</td>
 * <td>{@link Queue#poll poll()}</td>
 * </tr>
 * <tr>
 * <td><b>查看</b></td>
 * <td>{@link Queue#element element()}</td>
 * <td>{@link Queue#peek peek()}</td>
 * </tr>
 * </table>
 * 
 * @author Chain
 *
 */
public interface Queue<E> extends Collection<E> {

	/**
	 * 添加元素到队列末尾，如果队列已满则抛出异常
	 */
	@Override
	boolean add(E e);

	/**
	 * 添加元素到队列末尾，如果队列已满则返回false
	 */
	boolean offer(E e);

	/**
	 * 从队列头部获得并删除元素，如果队列为空则抛出异常
	 * 
	 * @return
	 */
	E remove();

	/**
	 * 从队列头部获得并删除元素，如果队列为空则返回null
	 * 
	 * @return
	 */
	E poll();

	/**
	 * 查看队列头部的元素，如果队列为空则抛出异常
	 * 
	 * @return
	 */
	E element();

	/**
	 * 查看队列头部的元素，如果队列为空则返回null
	 * 
	 * @return
	 */
	E peek();

}
