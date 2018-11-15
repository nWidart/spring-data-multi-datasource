package com.example.multidatasources;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class MultiDatasourcesApplication {

  public static void main(String[] args) {
    SpringApplication.run(MultiDatasourcesApplication.class, args);
  }

  @Bean
  @Primary
  DataSource customDatasources() {
    RoutingDataSource routingDataSource = new RoutingDataSource();
    HashMap<Object, Object> map = new HashMap<>();
    map.put("dataSourceClient1", dataSourceClient1());
    map.put("dataSourceClient2", dataSourceClient2());
    routingDataSource.setTargetDataSources(map);

    return routingDataSource;
  }


  DataSource dataSourceClient1() {
    return DataSourceBuilder.create()
        .url("jdbc:mysql://localhost:3306/source1")
        .username("root")
        .password("root")
        .build();
  }

  DataSource dataSourceClient2() {
    return DataSourceBuilder.create()
        .url("jdbc:mysql://localhost:3306/source2")
        .username("root")
        .password("root")
        .build();
  }
}
