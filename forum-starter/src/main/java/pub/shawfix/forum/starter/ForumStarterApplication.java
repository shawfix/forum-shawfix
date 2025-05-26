package pub.shawfix.forum.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "pub.shawfix.forum")
@MapperScan(value = {"pub.shawfix.forum.infrastructure.dal.dao"})
// TODO: SpringBootServletInitializer类是做什么的
public class ForumStarterApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ForumStarterApplication.class, args);
  }

  // TODO: 重写的意义
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(ForumStarterApplication.class);
  }

}
