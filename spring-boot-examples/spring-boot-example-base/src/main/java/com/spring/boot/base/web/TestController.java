package com.spring.boot.base.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试API。
 *
 * @since 2019-07-26
 */
@RestController("testController")
@RequestMapping(path = "/test")
@SuppressWarnings("unused")
public class TestController {
  /**
   * {@code
   * http://localhost:8080/test/singleParam?str=dannong
   * http://localhost:8080/test/singleParam
   * }
   */
  @GetMapping(path = "/singleParam")
  public String singleParam(String str) {
    return str;
  }

  /**
   * {@code
   * http://localhost:8080/test/requestBody
   * "dannong"
   * {"str":"dannong"}
   * }
   *
   * <a href="https://spring.io/guides/tutorials/bookmarks/">Building REST services with Spring</a>
   */
  @RequestMapping(path = "/requestBody")
  public String requestBody(@RequestBody String str) {
    return str;
  }

  /**
   * {@code
   * http://localhost:8080/test/beanRequestBody
   * {"name":"dannong","age":33}
   * {"name":"dannong"}
   * {"age":33}
   * {}
   * }
   */
  @RequestMapping(path = "/beanRequestBody")
  public User beanRequestBody(@RequestBody User user) {
    return user;
  }

  private static class User {
    private String name;
    private int age;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }
}
