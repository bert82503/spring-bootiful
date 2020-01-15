package com.spring.boot.base;

import com.spring.boot.base.lang.CustomThreadUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用主类。
 *
 * @since 2019-07-22
 */
@SpringBootApplication
public class BaseApplication {

  static {
    /*
     * java.lang.NullPointerException: null
     * 	   at com.spring.boot.base.BaseApplication.main(BaseApplication.java:26) ~[classes/:na]
     */
    // 场景2：设置默认的未捕获异常处理器(JVM实例级别)
    Thread.setDefaultUncaughtExceptionHandler(new CustomThreadUncaughtExceptionHandler());
  }

  /**
   * 运行应用的入口方法。
   */
  public static void main(String[] args) {
    SpringApplication.run(BaseApplication.class, args);

    /*
     * Exception in thread "main" java.lang.NullPointerException
     * 	   at com.spring.boot.base.BaseApplication.main(BaseApplication.java:29)
     */
    // 场景1：未设置默认的未捕获异常处理器
    throw new NullPointerException();
  }

}
