/**
 *
 */
package com.br.alldreams.socialmidia.conf;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.br.alldreams.socialmidia.conf.beans.DatabaseDataConfBeans;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 18:10:33
 * @version 1.0
 */
@Slf4j
@Configuration
public class Database {

	@Autowired
	private DatabaseDataConfBeans config;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource database = null;
		try {
			database = new DriverManagerDataSource();
			database.setDriverClassName("com.mysql.cj.jdbc.Driver");
			database.setUrl(config.getHost());
			database.setUsername(config.getUser());
			database.setPassword(config.getPass());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return database;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(jpaVendorAdapter);
		bean.setPackagesToScan("com.br.alldreams");
		return bean;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
		bean.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
		bean.setGenerateDdl(true);
		bean.setShowSql(true);
		bean.setGenerateDdl(true);
		return bean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
