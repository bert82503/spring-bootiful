package com.spring.boot.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用主类。
 *
 * @since 2019-07-22
 */
@SpringBootApplication
public class BaseApplication {

  /**
   * 运行应用的入口方法。
   */
  public static void main(String[] args) {
    SpringApplication.run(BaseApplication.class, args);
  }

}
