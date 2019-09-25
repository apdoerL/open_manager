package org.apdoer.manager.config;



import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.*;

/**
 * @author apdoer
 * @description 线程管理
 */
public class OpenManagerThreadPool {
	/**
	 * default initial capacity size
	 */
	private final static int INITIAL_CAPACITY          = 500000;
	/**
	 * if your program calculate most coreSize = cpu * 2 or cpu + 1
	 * if your program IO most coreSize = (int)(cpu/(1-IOPayload)) payload between 0.8  and 0.9
	 *
	 */
	private final static int DEFAULT_CORE_POOLSIZE     = 20;
	private final static int DEFAULT_MAX_POOLSIZE 	   = 200;
	/**
	 * 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
	 */
	private final static long DEFAULT_KEEP_ALIVE_TIME  = 10L;
	
	private ThreadPoolExecutor threadPoolExecutor;


	/**
	 * private constractor
	 */
	private OpenManagerThreadPool() {
		ThreadFactory threadFactory = OpenManagerThreadFactory.getInstance();
		this.threadPoolExecutor = new ThreadPoolExecutor(DEFAULT_CORE_POOLSIZE, DEFAULT_MAX_POOLSIZE,
				DEFAULT_KEEP_ALIVE_TIME, SECONDS, new PriorityBlockingQueue<>(INITIAL_CAPACITY), threadFactory);
	}

	/**
	 * private static inner class for singlton Object
	 */
	private static class ThreadPool{
		private static final OpenManagerThreadPool INSTANCE = new OpenManagerThreadPool();
	}

	/**
	 * static method for instance; final element cannot be override
	 * @return MessagePushThreadPool Singleton Object
	 */
	public static OpenManagerThreadPool getInstance() {
		return ThreadPool.INSTANCE;
	}

	
	public int size() {
		return this.threadPoolExecutor.getQueue().size();
	}
	
	public void execute(Runnable runnable) {
		this.threadPoolExecutor.execute(runnable);
	}
	
	public void shutdown() {
		this.threadPoolExecutor.shutdown();
	}

}
