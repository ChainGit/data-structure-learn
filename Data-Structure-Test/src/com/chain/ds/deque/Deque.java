package com.chain.ds.deque;

import com.chain.ds.queue.Queue;
import com.chain.ds.stack.Stack;

/**
 * 双端队列的接口<br>
 * 
 * 双端队列，即两边都可以进和出<br>
 * 
 * 双端队列可以充当栈、单端队列来使用，具体的见List<br>
 * 
 * <table BORDER CELLPADDING=3 CELLSPACING=1><br>
 * <caption>Deque接口的方法</caption><br>
 * <tr>
 * <td></td>
 * <td ALIGN=CENTER COLSPAN = 2><b>首元素（第一个元素）</b></td>
 * <td ALIGN=CENTER COLSPAN = 2><b>尾元素（最后一个元素）</b></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td ALIGN=CENTER><em>失败会抛出异常</em></td>
 * <td ALIGN=CENTER><em>失败返回特殊值</em></td>
 * <td ALIGN=CENTER><em>失败会抛出异常</em></td>
 * <td ALIGN=CENTER><em>失败返回特殊值</em></td>
 * </tr>
 * <tr>
 * <td><b>Insert</b></td>
 * <td>{@link Deque#addFirst addFirst(e)}</td>
 * <td>{@link Deque#offerFirst offerFirst(e)}</td>
 * <td>{@link Deque#addLast addLast(e)}</td>
 * <td>{@link Deque#offerLast offerLast(e)}</td>
 * </tr>
 * <tr>
 * <td><b>Remove</b></td>
 * <td>{@link Deque#removeFirst removeFirst()}</td>
 * <td>{@link Deque#pollFirst pollFirst()}</td>
 * <td>{@link Deque#removeLast removeLast()}</td>
 * <td>{@link Deque#pollLast pollLast()}</td>
 * </tr>
 * <tr>
 * <td><b>Examine</b></td>
 * <td>{@link Deque#getFirst getFirst()}</td>
 * <td>{@link Deque#peekFirst peekFirst()}</td>
 * <td>{@link Deque#getLast getLast()}</td>
 * <td>{@link Deque#peekLast peekLast()}</td>
 * </tr>
 * </table>
 * 
 * 
 * <table BORDER CELLPADDING=3 CELLSPACING=1><br>
 * <caption>Queue和Deque之间的对应关系</caption><br>
 * <tr>
 * <td ALIGN=CENTER><b>{@code Queue} 中的方法</b></td>
 * <td ALIGN=CENTER><b>Equivalent {@code Deque} 中的方法</b></td>
 * </tr>
 * <tr>
 * <td>{@link java.util.Queue#add add(e)}</td>
 * <td>{@link #addLast addLast(e)}</td>
 * </tr>
 * <tr>
 * <td>{@link java.util.Queue#offer offer(e)}</td>
 * <td>{@link #offerLast offerLast(e)}</td>
 * </tr>
 * <tr>
 * <td>{@link java.util.Queue#remove remove()}</td>
 * <td>{@link #removeFirst removeFirst()}</td>
 * </tr>
 * <tr>
 * <td>{@link java.util.Queue#poll poll()}</td>
 * <td>{@link #pollFirst pollFirst()}</td>
 * </tr>
 * <tr>
 * <td>{@link java.util.Queue#element element()}</td>
 * <td>{@link #getFirst getFirst()}</td>
 * </tr>
 * <tr>
 * <td>{@link java.util.Queue#peek peek()}</td>
 * <td>{@link #peek peekFirst()}</td>
 * </tr>
 * </table>
 * 
 * <table BORDER CELLPADDING=3 CELLSPACING=1><br>
 * <caption>Stack和Deque之间的对应关系</caption><br>
 * <tr>
 * <td ALIGN=CENTER><b>{@code Stack} 中的方法</b></td>
 * <td ALIGN=CENTER><b>{@code Deque} 中的方法</b></td>
 * </tr>
 * <tr>
 * <td>{@link #push push(e)}</td>
 * <td>{@link #addFirst addFirst(e)}</td>
 * </tr>
 * <tr>
 * <td>{@link #pop pop()}</td>
 * <td>{@link #removeFirst removeFirst()}</td>
 * </tr>
 * <tr>
 * <td>{@link #peek peek()}</td>
 * <td>{@link #peekFirst peekFirst()}</td>
 * </tr>
 * </table>
 * 
 * @author Chain
 *
 */
public interface Deque<E> extends Queue<E>, Stack<E> {

	/**
	 * 添加到队列首端
	 * 
	 * 如果添加失败会抛出异常
	 * 
	 * @param e
	 * @return
	 */
	boolean addFirst(E e);

	/**
	 * 添加到队列首端
	 * 
	 * 如果添加失败会返回false
	 * 
	 * @param e
	 * @return
	 */
	boolean offerFirst(E e);

	/**
	 * 移除队列首元素
	 * 
	 * 如果移除失败则抛出异常
	 * 
	 * @return
	 */
	E removeFirst();

	/**
	 * 移除队列首元素
	 * 
	 * 如果移除失败则返回null
	 * 
	 * @return
	 */
	E pollFirst();

	/**
	 * 查看队列首元素
	 * 
	 * 如果查看失败则抛出异常
	 * 
	 * @return
	 */
	E getFirst();

	/**
	 * 查看队列首元素
	 * 
	 * 如果查看失败则返回null
	 * 
	 * @return
	 */
	E peekFirst();

	/////////////////////////////////////////////////////////

	/**
	 * 添加到队列尾端
	 * 
	 * 如果添加失败会抛出异常
	 * 
	 * @param e
	 * @return
	 */
	boolean addLast(E e);

	/**
	 * 添加到队列尾端
	 * 
	 * 如果添加失败会返回false
	 * 
	 * @param e
	 * @return
	 */
	boolean offerLast(E e);

	/**
	 * 移除队列尾元素
	 * 
	 * 如果移除失败则抛出异常
	 * 
	 * @return
	 */
	E removeLast();

	/**
	 * 移除队列尾元素
	 * 
	 * 如果移除失败则返回null
	 * 
	 * @return
	 */
	E pollLast();

	/**
	 * 查看队列尾元素
	 * 
	 * 如果查看失败则抛出异常
	 * 
	 * @return
	 */
	E getLast();

	/**
	 * 查看队列尾元素
	 * 
	 * 如果查看失败则返回null
	 * 
	 * @return
	 */
	E peekLast();

}
