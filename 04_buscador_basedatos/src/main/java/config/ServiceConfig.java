package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@ComponentScan(basePackages = {"service", "repository"})
@EnableTransactionManagement
@Configuration
public class ServiceConfig {
	String driver="com.mysql.cj.jdbc.Driver"; //el driver en este caso de mysql
	String cadena="jdbc:mysql://localhost:3306/buscador"; //donde esta la base de datos
	String usuario="root";
	String pw="root";
	
	//creación del objeto DataSource con los datos de conexión a la BD
		@Bean
		public DataSource crearDatasource() {
			DriverManagerDataSource data=new DriverManagerDataSource();
			data.setDriverClassName(driver);
			data.setUrl(cadena);
			data.setUsername(usuario);
			data.setPassword(pw);
			return data;
		} 
		//adaptador de Hibernate
		@Bean
		public HibernateJpaVendorAdapter adapter() {
			HibernateJpaVendorAdapter adp=new HibernateJpaVendorAdapter();
			adp.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
			return adp;
		} 
		//factoria EntityManager: Objeto para acceder a capa de persistencia con JPA
		@Bean
		public LocalContainerEntityManagerFactoryBean factory() {
			LocalContainerEntityManagerFactoryBean factory=new LocalContainerEntityManagerFactoryBean();
			//factory.setPersistenceUnitName("buscadorPU");
			factory.setDataSource(crearDatasource());//creamos datasource
			factory.setPackagesToScan("model"); //le decimos donde estan las entidades
			factory.setJpaVendorAdapter(adapter());//creamos adapter
			return factory;
		} 
		//creación y configuración del transactionmanager
		@Bean
		public JpaTransactionManager txManager() {
			JpaTransactionManager manager=new JpaTransactionManager();
			manager.setEntityManagerFactory(factory().getObject());
			return manager;
		} 
}
