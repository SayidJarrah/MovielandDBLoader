package com.dkorniichuk.utils;

import com.dkorniichuk.entity.DataSourceProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.dkorniichuk"})
public class AppConfig {




    @Bean
    public DataSource dataSource()throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/db.properties"));
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      //  dataSource.setUrl("jdbc:mysql://localhost/whd2");
      //  dataSource.setUsername("whd2");
     //   dataSource.setPassword("whd2");
       //dataSource.setDriverClassName("jdbc.driver");
        dataSource.setUrl(prop.getProperty("jdbc.url"));
        dataSource.setUsername(prop.getProperty("jdbc.username"));
        dataSource.setPassword(prop.getProperty("jdbc.password"));
        return dataSource;
    }

   @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

}
