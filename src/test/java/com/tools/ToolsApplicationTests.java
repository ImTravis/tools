package com.tools;

import com.tools.common.db.Book;
import com.tools.controller.TestController;
import com.tools.redis.scene.distributedLock.RedisService;
import com.tools.redis.scene.distributedLock.ThreadRedis;
import com.tools.redis.utils.RedisSetOption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolsApplicationTests {

	@Autowired
	RedisSetOption redisSetOption;

	@Autowired
	TestController testController;
	@Autowired
	RedisService service;


	/**
	 * redis set集合求差集
	 */
	@Test
	public void contextLoads() {
		redisSetOption.diffSets();
	}
	/**
	 * redis hash
	 * Redis的Hash实际是内部存储的Value为一个HashMap，并提供了直接存取这个Map成员的接口
	 */
	@Test
	public void hash() {
		redisSetOption.hashOption();
	}

	@Test
	public void regist(){
		testController.registInfo();
	}


	@Test
	public void reidsLock(){

		for (int i = 0; i < 2; i++) {
			ThreadRedis threadA = new ThreadRedis(service);
			threadA.start();
		}
	}

	@Test
	public void entry(){


	}

	@Test
	public void test2() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
		new Thread(() -> {
			// 模拟执行耗时任务
			System.out.println("task doing...");
			try {
				Thread.sleep(3000);
				int i = 1/1;
			} catch (Exception e) {
				// 告诉completableFuture任务发生异常了
				completableFuture.completeExceptionally(e);
			}
			// 告诉completableFuture任务已经完成
			completableFuture.complete(1);
		}).start();
		// 获取任务结果，如果没有完成会一直阻塞等待
		Integer result = completableFuture.get();
		System.out.println("计算结果:" + result);
	}

	@Test
	public void test1(){
		long start = System.currentTimeMillis();
		// 结果集
		List<String> list = new ArrayList<>();

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
		// 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
		CompletableFuture[] cfs = taskList.stream()
				.map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
						.thenApply(h->Integer.toString(h))
						.whenComplete((s, e) -> {
							System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
							list.add(s);
						})
				).toArray(CompletableFuture[]::new);
		// 封装后无返回值，必须自己whenComplete()获取
		CompletableFuture.anyOf(cfs).join();
		System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));
	}

	public int calc(Integer i) {
		try {
			if (i == 1) {
				Thread.sleep(3000);//任务1耗时3秒
			} else if (i == 5) {
				Thread.sleep(5000);//任务5耗时5秒
			} else {
				Thread.sleep(1000);//其它任务耗时1秒
			}
			System.out.println("task线程：" + Thread.currentThread().getName()
					+ "任务i=" + i + ",完成！+" + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}

}

