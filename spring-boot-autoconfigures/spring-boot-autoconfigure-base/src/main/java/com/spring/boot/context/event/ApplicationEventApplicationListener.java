package com.spring.boot.context.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * Spring应用生命周期事件的应用监视器。
 *
 * @since 2019-07-21
 */
public class ApplicationEventApplicationListener
    implements ApplicationListener<ApplicationEvent>, Ordered {
  private static final Logger logger = LoggerFactory.getLogger(ApplicationEventApplicationListener.class);

  public ApplicationEventApplicationListener() {
    logger.info("create {}", this);
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    // 记录应用生命周期各阶段触发的事件
    // EmbeddedServletContainerInitializedEvent, ServletRequestHandledEvent, ...
    logger.info("handle {}", event);
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
