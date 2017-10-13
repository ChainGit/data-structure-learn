package com.chain.ds.array;

import java.util.Arrays;

/**
 * 
 * 数组抽象类实现
 * 
 * @author Chain
 *
 */
public abstract class AbstractArray<T> implements Array<T> {

	// 注意不同的集合的扩展方法不同
	@Override
	public abstract void extend();

	// 2倍指数扩展
	public T[] extend(T[] a) {
		if (a == null)
			return null;
		int len = a.length;
		if (len < 1)
			return a;
		int newLen = len << 1;
		return Arrays.copyOf(a, newLen);
	}
}
