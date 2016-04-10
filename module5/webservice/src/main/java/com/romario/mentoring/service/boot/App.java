package com.romario.mentoring.service.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Raman_Zhuravski on 2/27/2016.
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.romario.mentoring")
public class App
{
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
