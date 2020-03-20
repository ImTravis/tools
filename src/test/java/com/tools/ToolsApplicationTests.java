package com.tools;

import com.tools.Utils.CSVUtils;
import com.tools.Utils.DateUtils;
import com.tools.Utils.RandomStringUtil;
import com.tools.common.db.Book;
import com.tools.common.db.Person;
import com.tools.controller.TestController;
import com.tools.redis.scene.distributedLock.RedisService;
import com.tools.redis.scene.distributedLock.ThreadRedis;
import com.tools.redis.utils.RedisSetOption;
import com.tools.threadMax.Processor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
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
	@Autowired
	CSVUtils csvUtils;


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

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
		// 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
		CompletableFuture[] cfs = taskList.stream()
				.map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
						.thenApply(h->Integer.toString(h))
						.whenComplete((s, e) -> {
							list.add(s);
						})
				).toArray(CompletableFuture[]::new);
		// 封装后无返回值，必须自己whenComplete()获取
		CompletableFuture.anyOf(cfs).join();
		System.out.println("\nlist="+list+",耗时="+(System.currentTimeMillis()-start));
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
			System.out.println("\nTASK线程：" + Thread.currentThread().getName()
					+ "任务i=" + i + ",完成");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}


	@Test
	public void testOne(){
		csvUtils.startCsv();
	}

	@Autowired
	Processor processor;
	@Test
	public void testTwo(){

//		CREATE TABLE `person` (
//				`id` int(64) NOT NULL AUTO_INCREMENT,
//				`name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
//		`des` varchar(64) COLLATE utf8_bin DEFAULT NULL,
//		`dno` int(11) DEFAULT NULL,
//		`createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
//				PRIMARY KEY (`id`)
//) ENGINE=InnoDB AUTO_INCREMENT=29101 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
//
//
		List<Person> personList = new ArrayList<>();
		Person person = null;
		Date date1 = new Date();
		for(int k=0;k<10000;k++){
			person = new Person();

			int number = new Random().nextInt(100) + 1;
			person.setDno(number);
			number = new Random().nextInt(10) + 1;
			person.setName(RandomStringUtil.getRandomStr(number));
			person.setDes(RandomStringUtil.getRandomStr(number));
			personList.add(person);
			person.setCreateTime(DateUtils.dateStr3(new Date()));
		}


		if(processor.handler(personList)){
			int mill = DateUtils.millisecondBetween(date1,new Date());
			System.out.print("使用时间："+mill+"\n");
		}


	}

}

