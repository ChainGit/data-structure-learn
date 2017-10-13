package com.chain.ds;

/**
 * 抽象集合类
 * 
 * 默认操作是使用Iterator内的方法实现的
 * 
 * @author Chain
 *
 * @param <E>
 */
public abstract class AbstractCollection<E> implements Collection<E> {

	// 抽象类不能被直接实例化，只能由子类实例化
	protected AbstractCollection() {

	}

	public abstract Iterator<E> iterator();

	public abstract int size();

	// 直接判断size是否为0即可
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	// null也是可以的
	@Override
	public boolean contains(Object o) {
		Iterator<E> it = iterator();
		if (o == null) {
			while (it.hasNext())
				if (it.next() == null)
					return true;
		} else {
			while (it.hasNext())
				if (o.equals(it.next()))
					return true;
		}
		return false;
	}

	// 抽象集合类还不能使用add方法，因为没有存储元素的地方
	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException("abstract class");
	}

	// null也是可以的
	// 注意区别有些数据结构如队列中的remove()方法
	@Override
	public boolean remove(Object o) {
		Iterator<E> it = iterator();
		if (o == null) {
			while (it.hasNext()) {
				if (it.next() == null) {
					it.remove();
					return true;
				}
			}
		} else {
			while (it.hasNext()) {
				if (o.equals(it.next())) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void clear() {
		Iterator<E> it = iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	@Override
	public String toString() {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			E e = it.next();
			sb.append(e == this ? "(this Collection)" : e);
			if (!it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}
}
