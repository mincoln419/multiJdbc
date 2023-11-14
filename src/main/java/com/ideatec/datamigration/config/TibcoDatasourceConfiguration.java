package com.ideatec.datamigration.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * <pre>
 * com.ideatec.datamigration.config
 * DatasourceConfiguration.java
 * </pre>
 *
 * @author : minco
 * @date : 2023. 11. 13. 오후 12:16:22
 * @desc :
 * @version : x.x
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.ideatec.datamigration.bwmonitor.dao", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class TibcoDatasourceConfiguration {

	@Bean(name = "db2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "db2SqlSessionFactory")
	public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(db2DataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mapper/tibco/*.xml"));
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource("mapper/mybatis-config2.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "db2SqlSessionTemplate")
	public SqlSessionTemplate db2SqlSessionTemplate(SqlSessionFactory sessionFactory) throws Exception {
		return new SqlSessionTemplate(sessionFactory);
	}

}
