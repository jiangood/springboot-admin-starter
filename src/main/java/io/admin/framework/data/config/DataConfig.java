package io.admin.framework.data.config;

import io.admin.framework.data.JdbcTool;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    @Bean
    public JdbcTool jdbcTool(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
        Dialect dialect = sessionFactory.getJdbcServices().getDialect();
        return new JdbcTool(dataSource, dialect);
    }
}
