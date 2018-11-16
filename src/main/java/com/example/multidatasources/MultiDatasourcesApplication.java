package com.example.multidatasources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MultiDatasourcesApplication {

  private List<String> configs = List.of("source1", "source2");

  public static void main(String[] args) {
    SpringApplication.run(MultiDatasourcesApplication.class, args);
  }

  @Bean
  DataSource customDatasources() {
    Map<Object, Object> dataSources = new HashMap<>();
    for (String config : configs) {
      dataSources.put(config, DataSourceBuilder.create()
          .url("jdbc:mysql://localhost:3306/" + config)
          .username("root")
          .password("root")
          .build());
    }
    RoutingDataSource routingDataSource = new RoutingDataSource();
    routingDataSource.setTargetDataSources(dataSources);

    return routingDataSource;
  }
}
