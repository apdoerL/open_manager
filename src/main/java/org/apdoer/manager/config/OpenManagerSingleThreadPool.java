package org.apdoer.manager.config;



import java.util.concurrent.*;


/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/25 14:28
 */
public class OpenManagerSingleThreadPool {

    private ExecutorService executorService;

    private OpenManagerSingleThreadPool(){
        ThreadFactory threadFactory = OpenManagerThreadFactory.getInstance();
        this.executorService = new ThreadPoolExecutor(1, 1,0L,TimeUnit.MILLISECONDS , new LinkedBlockingQueue<>(), threadFactory);
    }

    private static class InnerSingleThreadPool{
        private static final ExecutorService INSTANCE = new OpenManagerSingleThreadPool().executorService;
    }

    public static ExecutorService getInstance(){
        return OpenManagerSingleThreadPool.InnerSingleThreadPool.INSTANCE;
    }
}
