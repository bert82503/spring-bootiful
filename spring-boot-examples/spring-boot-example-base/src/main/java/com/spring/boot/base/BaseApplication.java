package com.spring.boot.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用运行入口。
 *
 * @since 2019-07-22
 */
@SpringBootApplication
public class BaseApplication {
  /**
   * 应用运行入口方法。
   */
  public static void main(String[] args) {
    SpringApplication.run(BaseApplication.class, args);
  }
}
