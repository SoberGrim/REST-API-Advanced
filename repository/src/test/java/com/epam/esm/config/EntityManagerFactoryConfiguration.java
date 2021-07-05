package com.epam.esm.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * The type Entity manager factory configuration.
 */
@TestConfiguration
public class EntityManagerFactoryConfiguration {
    private static final String CREATE_DATABASE_SCRIPT = "classpath:script/schema.sql";
    private static final String FILL_DATABASE_WITH_DATA_SCRIPT = "classpath:script/data.sql";
    private static final String PACKAGE_TO_SCAN = "com.epam.esm";

    /**
     * Entity manager factory local container entity manager factory bean.
     *
     * @return the local container entity manager factory bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(embeddedDataSource());
        em.setPackagesToScan(PACKAGE_TO_SCAN);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    /**
     * Embedded data source data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(CREATE_DATABASE_SCRIPT)
                .addScript(FILL_DATABASE_WITH_DATA_SCRIPT)
                .build();
    }
}
