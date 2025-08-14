package com.example.hibernate_flyway_example.config;

import com.example.hibernate_flyway_example.entity.Order;
import com.example.hibernate_flyway_example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        // Створюємо ServiceRegistry
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .applySetting("hibernate.hbm2ddl.auto", "update")
                .applySetting("hibernate.show_sql", "true")
                .applySetting("hibernate.format_sql", "true")
                .applySetting("hibernate.connection.datasource", dataSource)
                .build();

        // Створюємо Metadata
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Order.class) // якщо у User є orders
                .buildMetadata();

        // Повертаємо SessionFactory
        return metadata.buildSessionFactory();
    }
}

