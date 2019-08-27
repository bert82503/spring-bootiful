package com.spring.boot.context.event;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * Spring应用生命周期事件的应用监视器。
 *
 * @author dannong.lihg
 * @since 2019-07-21
 */
public class SpringApplicationEventApplicationListener implements ApplicationListener<SpringApplicationEvent>, Ordered {

  private static final Logger logger = LoggerFactory.getLogger(SpringApplicationEventApplicationListener.class);

  /**
   * 应用启动完成标识。
   * <p>
   * 注意：事件可能被重复触发，需保障操作幂等。
   */
  private static final AtomicBoolean applicationReady = new AtomicBoolean(false);

  public SpringApplicationEventApplicationListener() {
    logger.info("create {}", this);
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  @Override
  public void onApplicationEvent(SpringApplicationEvent springApplicationEvent) {
    logger.info("handle {}", springApplicationEvent);
    if (springApplicationEvent instanceof ApplicationReadyEvent) {
      if (!applicationReady.compareAndSet(false, true)) {
        return;
      }
      // 应用启动完成，初始化...
      ApplicationReadyEvent applicationReadyEvent = (ApplicationReadyEvent) springApplicationEvent;
    } else if (springApplicationEvent instanceof ApplicationFailedEvent) {
      // 应用启动失败，上报...
      ApplicationFailedEvent applicationFailedEvent = (ApplicationFailedEvent) springApplicationEvent;
    }
  }

}
