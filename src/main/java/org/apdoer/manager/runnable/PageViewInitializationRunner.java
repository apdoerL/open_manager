package org.apdoer.manager.runnable;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.PageViewHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化pv 统计
 *
 * 这个类的作用是在项目启动的时候执行某些操作
 *      为了实现在项目启动时候执行某些操作,有两种方式
 *      1.实现 ApplicationRunner 接口
 *      2.实现 CommandLineRunner 接口
 *      区别在于 一个中的 run 方法参数为 ApplicationArguments,另一个是 String数组,类似于main 方法
 *      这里使用的是 实现 ApplicationRunner 接口来实现
 * @author apdoer
 */
@Component
@Slf4j
public class PageViewInitializationRunner implements ApplicationRunner {
    private PageViewHandler pageViewHandler;



    @Autowired
    public void setPageViewHandler(PageViewHandler pageViewHandler) {
        this.pageViewHandler = pageViewHandler;
    }

    @Override
    public void run(ApplicationArguments args){
        log.info("====================pv count initial start==================");
        pageViewHandler.count();
        log.info("============pv count initial success=======================");
    }
}