package com.epam.esm.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * The type Data source configuration.
 */
@Configuration
public class DataSourceConfiguration {
    private static final String DATABASE_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";
    private static final String DATABASE_URL = "spring.datasource.url";
    private static final String DATABASE_USERNAME = "spring.datasource.username";
    private static final String DATABASE_PASSWORD = "spring.datasource.password";
    private static final String DATABASE_POOL_MAX_SIZE = "spring.datasource.hikari.maximum-pool-size";
    private static final String CREATE_DATABASE_SCRIPT = "classpath:script/schema.sql";
    private static final String FILL_DATABASE_WITH_DATA_SCRIPT = "classpath:script/data.sql";

    private Environment environment;

    /**
     * Sets environment.
     *
     * @param environment the environment
     */
    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Data source data source.
     *
     * @return the data source
     */
    @Profile("prod")
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty(DATABASE_DRIVER_CLASS_NAME));
        dataSource.setJdbcUrl(environment.getProperty(DATABASE_URL));
        dataSource.setUsername(environment.getProperty(DATABASE_USERNAME));
        dataSource.setPassword(environment.getProperty(DATABASE_PASSWORD));
        dataSource.setMaximumPoolSize(Integer.parseInt(environment.getProperty(DATABASE_POOL_MAX_SIZE)));
        return dataSource;
    }

    /**
     * Embedded data source data source.
     *
     * @return the data source
     */
    @Profile("dev")
    @Bean
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(CREATE_DATABASE_SCRIPT)
                .addScript(FILL_DATABASE_WITH_DATA_SCRIPT)
                .build();
    }
}
