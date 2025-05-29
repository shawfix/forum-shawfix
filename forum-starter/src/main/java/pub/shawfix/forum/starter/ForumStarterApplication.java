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
// 继承SpringBootServletInitializer是为了打包成war包通过外部的web容器启动时，告诉SpringBoot应该如何初始化应用
public class ForumStarterApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ForumStarterApplication.class, args);
  }


  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(ForumStarterApplication.class);
  }

}
