package com.spring.boot.base.boot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 自定义的应用运行器。
 *
 * @see org.springframework.boot.ApplicationRunner
 * @since 2019-12-24
 */
@Component("customApplicationRunner")
public class CustomApplicationRunner implements ApplicationRunner {

  private static final Logger logger = LoggerFactory.getLogger(CustomApplicationRunner.class);

  public CustomApplicationRunner() {
    logger.info("create {}", this);
  }

  @Override
  public void run(ApplicationArguments args) {
    if (logger.isInfoEnabled()) {
      logger.info("application arguments:{}", Arrays.toString(args.getSourceArgs()));
    }
  }

}
