package com.example.multidatasources;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "client2EntityManagerFactory",
    transactionManagerRef = "client2TransactionManager")
public class Client2Config {

  @Bean
  public DataSource client2DataSource() {
    return DataSourceBuilder.create()
        .url("jdbc:mysql://localhost:3306/source2")
        .username("root")
        .password("root")
        .build();
  }

  @Bean
  PlatformTransactionManager client2TransactionManager() {
    return new JpaTransactionManager(client2EntityManagerFactory().getObject());
  }

  @Bean
  LocalContainerEntityManagerFactoryBean client2EntityManagerFactory() {

    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

    factoryBean.setDataSource(client2DataSource());
    factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    factoryBean.setPackagesToScan("com.example.multidatasources");

    return factoryBean;
  }
}
