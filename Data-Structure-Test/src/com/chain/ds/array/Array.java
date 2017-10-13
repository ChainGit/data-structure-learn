package com.chain.ds.array;

import com.chain.ds.Collection;

/**
 * 数组接口
 * 
 * @author Chain
 *
 */
public interface Array<T> extends Collection<T> {

	/**
	 * 扩大数组容量
	 */
	public void extend();
}
