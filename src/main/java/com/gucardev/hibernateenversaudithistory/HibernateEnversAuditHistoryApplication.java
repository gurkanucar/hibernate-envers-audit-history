package com.gucardev.hibernateenversaudithistory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@Slf4j
public class HibernateEnversAuditHistoryApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(HibernateEnversAuditHistoryApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(HibernateEnversAuditHistoryApplication.class);
  }
}
