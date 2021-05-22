package com.rodrigo.stockQuoteManagerAPI.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
		basePackages = "com.rodrigo.stockQuoteManagerAPI.model",
		entityManagerFactoryRef = "entityManagerFactory"
	)
public class DataSourceConfig {

    private final Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "dataSourceTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("dataSource") DataSource dataSource
    ) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.rodrigo.stockQuoteManagerAPI.model")
                .persistenceUnit("dataSource")
                .build();
    }
    

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setPoolName("dataSourceHikariPool");
        hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("spring.datasource.maximum-pool-size")));
        hikariConfig.setMinimumIdle(Integer.parseInt(environment.getProperty("spring.datasource.minimum-idle")));
        hikariConfig.setAutoCommit(Boolean.parseBoolean(environment.getProperty("spring.datasource.auto-commit")));

        return new HikariDataSource(hikariConfig);
    }

}