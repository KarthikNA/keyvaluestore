package com.assignment.keyvaluestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class Main extends SpringBootServletInitializer {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }
}
