package com.spring.boot.context.event;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * Spring应用上下文生命周期事件的应用监视器。
 *
 * @since 2019-07-21
 */
public class ApplicationContextEventApplicationListener
    implements ApplicationListener<ApplicationContextEvent>, Ordered {

  private static final Logger logger = LoggerFactory
      .getLogger(ApplicationContextEventApplicationListener.class);

  /**
   * 应用上下文启动完成开关。
   * <p>
   * 注意：当存在父子应用上下文时，事件可能被重复触发，需保障操作幂等。
   */
  private static final AtomicBoolean contextRefreshed = new AtomicBoolean(false);

  public ApplicationContextEventApplicationListener() {
    logger.info("create {}", this);
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  @Override
  public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
    logger.info("handle {}", applicationContextEvent);
    if (applicationContextEvent instanceof ContextRefreshedEvent) {
      logger.info("contextRefreshed:{}", contextRefreshed);
      if (!contextRefreshed.compareAndSet(false, true)) {
        return;
      }
      // 应用上下文刷新完成，初始化...
      ContextRefreshedEvent contextRefreshedEvent = (ContextRefreshedEvent) applicationContextEvent;
    } else if (applicationContextEvent instanceof ContextClosedEvent) {
      // 应用上下文关闭完成，清理资源...
      ContextClosedEvent contextClosedEvent = (ContextClosedEvent) applicationContextEvent;
    }
  }

}
