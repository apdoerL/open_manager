package org.apdoer.manager.config;


import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description 线程管理
 * created by apdoer 2019/08/26
 */
public class OpenManagerThreadPool {
	
	private static OpenManagerThreadPool openManagerThreadPool;

	/**
	 * default initial capacity size
	 */
	private final static int INITIAL_CAPACITY          = 500000;
	private final static int DEFAULT_CORE_POOLSIZE     = 10;
	private final static int DEFAULT_MAX_POOLSIZE 	   = 50;
	private final static long DEFAULT_KEEP_ALIVE_TIME  = 10L;
	
	private ThreadPoolExecutor threadPoolExecutor;


	/**
	 * private constractor
	 */
	private OpenManagerThreadPool() {
		this.threadPoolExecutor = new ThreadPoolExecutor(DEFAULT_CORE_POOLSIZE, DEFAULT_MAX_POOLSIZE,
				DEFAULT_KEEP_ALIVE_TIME, TimeUnit.SECONDS, new PriorityBlockingQueue<>(INITIAL_CAPACITY));
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
