package com.v2solve.goal.management;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.v2solve.app.security.config.EnableSecurity;

/**
 * This is the application configuration class. 
 * It creates appropriate beans required for serving API requests 
 * on the controllers. There are other security configuration 
 * classes as well in the package.
 * @author Saurin Magiawala
 *
 */
@Configuration
@EnableSecurity
public class AppConfig 
{
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
