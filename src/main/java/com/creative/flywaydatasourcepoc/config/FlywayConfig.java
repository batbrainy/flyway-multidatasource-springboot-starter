package com.creative.flywaydatasourcepoc.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {
  @Bean(name = "flyway1")
  public Flyway flyway1(@Qualifier("dataSource1") DataSource dataSource1) {
    Flyway flyway = new Flyway(Flyway.configure()
        .dataSource(dataSource1)
        .locations("classpath:db/migration/db1")
        .baselineOnMigrate(true));
    flyway.migrate();
    return flyway;
  }

  @Bean(name = "flyway2")
  public Flyway flyway2(@Qualifier("dataSource2") DataSource dataSource2) {
    Flyway flyway = new Flyway(Flyway.configure()
        .dataSource(dataSource2)
        .locations("classpath:db/migration/db2")
        .baselineOnMigrate(true));
    flyway.migrate();
    return flyway;
  }
}
