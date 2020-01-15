package com.spring.boot.base.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义的线程未捕获异常处理器。
 *
 * @see java.lang.Thread.UncaughtExceptionHandler
 * @since 2020-01-15
 */
public class CustomThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomThreadUncaughtExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        logger.error("uncaught exception in thread {}", t, e);
    }
}
