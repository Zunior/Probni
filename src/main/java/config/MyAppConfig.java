package config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {
		"main",
		"service"
})
@EnableJpaRepositories(basePackages = {
		"repository"
})
@EnableTransactionManagement
public class MyAppConfig {
	//config za entity manager i datasource
	//configure datasource
		@Bean
		public DataSource datasource() {
			DriverManagerDataSource datasource = new DriverManagerDataSource();
			datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			datasource.setUrl("jdbc:mysql://localhost:3306/engineering_spring");
			datasource.setUsername("root");
			datasource.setPassword("12345");
			return datasource;
		}
		
		@Bean(name = "entityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
			em.setDataSource(datasource());
			//gde su entity klase
			em.setPackagesToScan(new String[] {"domain"});
			//JPA provider
			JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
			em.setJpaVendorAdapter(jpaVendorAdapter);
			
			em.setJpaProperties(getAdditionPropertiies());
			return em;
		}

		private Properties getAdditionPropertiies() {
			Properties properties = new Properties();
			properties.setProperty("hibernate.show_sql", "true");
			return properties;
		}
		
		@Bean
		public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(emf);
			return transactionManager;
		}
	
}
