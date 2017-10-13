package com.chain.ds.test;

import org.junit.Test;

import com.chain.ds.Iterator;
import com.chain.ds.deque.ArrayDeque;
import com.chain.ds.deque.Deque;
import com.chain.ds.deque.LinkedDeque;

/**
 * 双端队列测试
 * 
 * @author Chain
 *
 */
public class DequeTest {

	@Test
	public void test1() {
		System.out.println("ArrayDeque：");
		Deque<Integer> deque = new ArrayDeque<>();
		testDeque(deque);
	}

	@Test
	public void test2() {
		System.out.println("LinkedDeque：");
		Deque<Integer> deque = new LinkedDeque<>();
		testDeque(deque);
	}

	public void testDeque(Deque<Integer> deque) {
		// 空队列测试
		System.out.println(deque);
		System.out.println("size：" + deque.size());

		System.out.println();

		// 空队列迭代
		Iterator<Integer> iterator = deque.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(next);
			iterator.remove();
		}

		// 添加元素和队列扩容测试
		for (int i = 100; i < 140; i++) {
			if (i == 120)
				// null值测试，不建议在队列中添加null值，以免使用remove方法时出现错误判断
				deque.add(null);
			else
				deque.add(i);
		}

		System.out.println(deque);
		System.out.println("size：" + deque.size());

		System.out.println();

		// 随机增加、删除操作
		for (int i = 0; i < 100; i++) {
			int r = (int) (Math.random() * 10);
			Integer e = null;
			switch (r) {
			case 0:
				deque.offerFirst(i);
				System.out.println("offerFirst：" + i);
				break;
			case 1:
				deque.offerLast(i);
				System.out.println("offerLast：" + i);
				break;
			case 2:
				deque.add(i);
				System.out.println("add：" + i);
				break;
			case 3:
			case 4:
				e = deque.poll();
				System.out.println("poll：" + e);
				break;
			case 5:
			case 6:
				e = deque.pollFirst();
				System.out.println("pollFirst：" + e);
				break;
			case 7:
			case 8:
			case 9:
				e = deque.pollLast();
				System.out.println("pollLast：" + e);
				break;
			}
			System.out.println(deque);
			System.out.println("size：" + deque.size());
		}

		System.out.println();

		// 栈功能测试
		deque.push(128);
		System.out.println(deque);
		System.out.println("size：" + deque.size());
		Integer pop = deque.pop();
		System.out.println(pop);
		System.out.println(deque);
		System.out.println("size：" + deque.size());

		System.out.println();

		// 非空队列迭代
		iterator = deque.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(next);
			// 验证迭代的安全性
			// iterator.remove();
		}

		System.out.println();

		// remove(obj)测试
		System.out.println(deque.remove(deque.peek()));

		System.out.println(deque);
		System.out.println("size：" + deque.size());

		System.out.println();

		// 队列是否包含元素测试
		System.out.println(deque.contains(87));

		System.out.println();

		// 清空队列测试
		deque.clear();
		System.out.println(deque.isEmpty());

		System.out.println();

		System.out.println(deque);
		System.out.println("size：" + deque.size());

		// 空队列迭代
		iterator = deque.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(next);
			iterator.remove();
		}

	}
}
