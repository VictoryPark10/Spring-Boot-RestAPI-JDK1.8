package com.vicsoft.basicapi8.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "itemEntityManagerFactory",
        transactionManagerRef = "itemTransactionManager",
        basePackages = { "com.vicsoft.basicapi8.repository.item" }
)
public class ItemDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("item.datasource")
    public DataSourceProperties itemDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("item.datasource.hikari")
    public DataSource itemDataSource(@Qualifier("itemDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean itemEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("itemDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.vicsoft.basicapi8.entity.item")
                .persistenceUnit("itemEntityManager")
                .build();
    }

    @Bean
    public PlatformTransactionManager itemTransactionManager(@Qualifier("itemEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
