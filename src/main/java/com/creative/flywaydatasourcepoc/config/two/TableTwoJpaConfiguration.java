package com.creative.flywaydatasourcepoc.config.two;

import com.creative.flywaydatasourcepoc.model.two.TableTwo;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.creative.flywaydatasourcepoc.repository.two"},
    entityManagerFactoryRef = "tableTwoEntityManagerFactory",
    transactionManagerRef = "tableTwoTransactionManager")
public class TableTwoJpaConfiguration {

  @Bean
  public LocalContainerEntityManagerFactoryBean tableTwoEntityManagerFactory(
      @Qualifier("dataSource2") DataSource dataSource, EntityManagerFactoryBuilder builder) {
    return builder.dataSource(dataSource).packages(TableTwo.class).build();
  }

  @Bean
  public PlatformTransactionManager tableTwoTransactionManager(
      @Qualifier("tableTwoEntityManagerFactory")
          LocalContainerEntityManagerFactoryBean tableTwoEntityManagerFactory) {
    return new JpaTransactionManager(
        Objects.requireNonNull(tableTwoEntityManagerFactory.getObject()));
  }
}
