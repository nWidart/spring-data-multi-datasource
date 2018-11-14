package com.example.multidatasources;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "client1EntityManagerFactory",
    transactionManagerRef = "client1TransactionManager")
public class Client1Config {

  @Bean
  @Primary
  public DataSource client1DataSource() {
    return DataSourceBuilder.create()
        .url("jdbc:mysql://localhost:3306/source1")
        .username("root")
        .password("root")
        .build();
  }

  @Bean
  @Primary
  PlatformTransactionManager client1TransactionManager() {
    return new JpaTransactionManager(client1EntityManagerFactory().getObject());
  }

  @Bean
  @Primary
  LocalContainerEntityManagerFactoryBean client1EntityManagerFactory() {

    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

    factoryBean.setDataSource(client1DataSource());
    factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    factoryBean.setPackagesToScan("com.example.multidatasources");

    return factoryBean;
  }
}
