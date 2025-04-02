package com.creative.flywaydatasourcepoc.config.one;

import com.creative.flywaydatasourcepoc.model.one.TableOne;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.creative.flywaydatasourcepoc.repository.one"},
    entityManagerFactoryRef = "tableOneEntityManagerFactory",
    transactionManagerRef = "tableOneTransactionManager"
)
public class TableOneJpaConfiguration {

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean tableOneEntityManagerFactory(
      @Qualifier("dataSource1") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource)
        .packages(TableOne.class)
        .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager tableOneTransactionManager(
      @Qualifier("tableOneEntityManagerFactory") LocalContainerEntityManagerFactoryBean tableOneEntityManagerFactory) {
    return new JpaTransactionManager(Objects.requireNonNull(tableOneEntityManagerFactory.getObject()));
  }

}
