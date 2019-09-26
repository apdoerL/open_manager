package org.apdoer.manager.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/25 14:29
 */
class OpenManagerThreadFactory {
    private ThreadFactory threadFactory;


    private OpenManagerThreadFactory(){
        threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-openManager-%d").build();
    }

    private static class InnerThreadFactory{
        private static final ThreadFactory INSTANCE = new OpenManagerThreadFactory().threadFactory;
    }

    static ThreadFactory getInstance(){
        return InnerThreadFactory.INSTANCE;
    }
}
