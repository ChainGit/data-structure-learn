package com.chain.ds.queue;

/**
 * 单端链表队列的节点
 * 
 * @author Chain
 *
 * @param <T>
 */
public class QueueNode<T> {

	private T value;
	private QueueNode<T> nextNode;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public QueueNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(QueueNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	public QueueNode(T value) {
		super();
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueueNode other = (QueueNode) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
