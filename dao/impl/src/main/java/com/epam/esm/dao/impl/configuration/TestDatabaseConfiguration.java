package com.epam.esm.dao.impl.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
@ComponentScan("com.epam.esm.dao")
public class TestDatabaseConfiguration {

    @Bean
    public DataSource createDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("classpath:script/create_table_gift_certificate.sql")
                .addScript("classpath:script/fill_table_gift_certificate.sql")
                .addScript("classpath:script/create_table_tag_has_gift_certificate.sql")
                .addScript("classpath:script/fill_table_tag_has_gift_certificate.sql")
                .addScript("classpath:script/create_table_tag.sql")
                .addScript("classpath:script/fill_table_tag.sql")
                .build();
    }

    @Bean
    public JdbcTemplate createJdbcTemplate(@Qualifier("createDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
