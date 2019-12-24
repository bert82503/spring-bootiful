package com.spring.boot.base.boot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 自定义的命令行运行器。
 *
 * @see org.springframework.boot.CommandLineRunner
 * @since 2019-12-24
 */
@Component("customCommandLineRunner")
public class CustomCommandLineRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(CustomCommandLineRunner.class);

  public CustomCommandLineRunner() {
    logger.info("create {}", this);
  }

  @Override
  public void run(String... args) {
    if (logger.isInfoEnabled()) {
      logger.info("command line arguments:{}", Arrays.toString(args));
    }
  }

}
