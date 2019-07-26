package com.spring.boot.base.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主页API，用于健康检测等。
 *
 * @since 2019-07-22
 */
@RestController("homeController")
@RequestMapping(path = "/base")
@SuppressWarnings("unused")
public class HomeController {
  @GetMapping(path = "")
  public String home() {
    return "spring-boot-example-base";
  }

  @GetMapping(path = "/checkHealth")
  public String checkHealth() {
    return "ok";
  }
}
