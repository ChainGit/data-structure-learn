package com.chain.ds.stack;

/**
 * 栈的公共接口
 * 
 * 栈是一个后进先出(LIFO)的数据结构
 * 
 * 双端队列(Deque)可以实现栈的功能，且比Stack接口能提供更多的功能
 * 
 * @author Chain
 *
 */
public interface Stack<E> {

	/**
	 * 查看栈顶元素
	 * 
	 * @return
	 */
	E peek();

	/**
	 * 栈顶元素弹出
	 * 
	 * @return
	 */
	E pop();

	/**
	 * 元素压入栈顶
	 * 
	 * @return
	 */
	boolean push();

}
