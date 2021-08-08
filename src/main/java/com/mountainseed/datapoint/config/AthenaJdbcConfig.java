package com.mountainseed.datapoint.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AthenaJdbcConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        PoolConfiguration configuration = ((org.apache.tomcat.jdbc.pool.DataSource) dataSource).getPoolProperties();
        Properties properties = configuration.getDbProperties();
        properties.put("s3_staging_dir", "s3://kc-textract-results/");
        properties.put("aws_credentials_provider_class","com.simba.athena.amazonaws.auth.PropertiesFileCredentialsProvider");
        // aws.credentials are supposed to be configured, with a default profile
        properties.put("aws_credentials_provider_arguments","/Users/kchen/.aws/aws_credentials");
        configuration.setDbProperties(properties);
        return new JdbcTemplate(dataSource);
    }
}
