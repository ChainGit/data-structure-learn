package com.chain.ds.test;

import org.junit.Test;

import com.chain.ds.Iterator;
import com.chain.ds.queue.ArrayQueue;

/**
 * 队列测试
 * 
 * @author Chain
 *
 */
public class QueueTest {

	// 测试单端数组对列
	// 测试的时候要循环往返的 从空 到 插入 到 随机插入和删除 再回到 空
	@Test
	public void test1() {
		ArrayQueue<Integer> queue = new ArrayQueue<>();

		// 空队列测试
		System.out.println(queue);
		System.out.println("size：" + queue.size());

		System.out.println();

		// 空队列迭代
		Iterator<Integer> iterator = queue.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(next);
			iterator.remove();
		}

		// 添加元素和队列扩容测试
		for (int i = 100; i < 140; i++) {
			if (i == 120)
				// null值测试，不建议在队列中添加null值，以免使用remove方法时出现错误判断
				queue.add(null);
			else
				queue.add(i);
		}

		System.out.println(queue);
		System.out.println("size：" + queue.size());

		System.out.println();

		// 随机增加、删除和打印队列交替测试
		int m = (int) (Math.random() * 100);
		System.out.println("max：" + m);
		for (int i = 0; i < 100; i++) {
			int r = (int) (Math.random() * 100);
			// 修改小于判断的上限可以模拟插入多删除少，或者删除多插入少，或者插入和删除频次均衡的情况
			if (r < m) {
				// 不能写int，如果queue.poll返回为null，则会发生空指针异常，自动装拆箱失败
				Integer p = queue.poll();
				System.out.println("poll：" + p);
			} else {
				queue.offer(i);
				System.out.println("add：" + i);
			}
			System.out.println(queue);
			System.out.println("size：" + queue.size());
		}

		System.out.println(queue);
		System.out.println("size：" + queue.size());

		System.out.println();

		// 非空队列迭代
		iterator = queue.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(next);
			// 验证迭代的安全性
			// iterator.remove();
		}

		System.out.println();

		// remove(obj)测试
		System.out.println(queue.remove(queue.peek()));

		System.out.println(queue);
		System.out.println("size：" + queue.size());

		System.out.println();

		// 队列是否包含元素测试
		System.out.println(queue.contains(87));

		System.out.println();

		// 清空队列测试
		queue.clear();
		System.out.println(queue.isEmpty());

		System.out.println();

		System.out.println(queue);
		System.out.println("size：" + queue.size());

		// 空队列迭代
		iterator = queue.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(next);
			iterator.remove();
		}

	}

}
