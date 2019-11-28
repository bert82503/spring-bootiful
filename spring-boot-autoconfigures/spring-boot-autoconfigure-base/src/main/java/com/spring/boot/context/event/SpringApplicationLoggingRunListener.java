package com.spring.boot.context.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Spring应用运行监视器日志记录器，记录Spring应用生命周期的各个阶段。
 *
 * @see org.springframework.boot.context.event.EventPublishingRunListener
 * @since 2019-07-21
 */
public class SpringApplicationLoggingRunListener
    implements SpringApplicationRunListener, Ordered {

  private static final Logger logger = LoggerFactory
      .getLogger(SpringApplicationLoggingRunListener.class);

  private final SpringApplication application;

  private final String[] args;

  public SpringApplicationLoggingRunListener(
      SpringApplication application, String[] args) {
    logger.info("create {}", this);
    this.application = application;
    this.args = args;

    logger.info("main application class, path:{}, package name:{}",
        getMainApplicationClassAbsolutePath(), getMainApplicationClassPackageName());
  }

  private String getMainApplicationClassAbsolutePath() {
    Class<?> mainApplicationClass = application.getMainApplicationClass();

    // ~/Documents/workspace/GitHub/spring-bootiful/spring-boot-examples/spring-boot-example-base/target/classes
//    ApplicationHome home = new ApplicationHome(mainApplicationClass);
//    return (home.getSource() != null) ? home.getSource().getAbsolutePath()
//        : "";

    // ~/Documents/workspace/GitHub/spring-bootiful/spring-boot-examples/spring-boot-example-base/target/classes/com/spring/boot/base
    String path = mainApplicationClass.getResource(mainApplicationClass.getSimpleName() + ".class")
        .getPath();
    path = path.substring(0, path.lastIndexOf(System.getProperty("file.separator")));
    return path;
  }

  private String getMainApplicationClassPackageName() {
    Class<?> mainApplicationClass = application.getMainApplicationClass();
    // com.spring.boot.base.BaseApplication
    String className = mainApplicationClass.getName();
    // com.spring.boot.base
    return className.substring(0, className.lastIndexOf('.'));
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  @Override
  public void starting() {
    logger.info("starting spring application");
  }

  @Override
  public void environmentPrepared(ConfigurableEnvironment environment) {
    logger.info("environmentPrepared spring application");
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    logger.info("contextPrepared spring application");
  }

  @Override
  public void contextLoaded(ConfigurableApplicationContext context) {
    logger.info("contextLoaded spring application");
  }

  @Override
  public void finished(ConfigurableApplicationContext context, Throwable exception) {
    logger.info("finished spring application");
  }

}
