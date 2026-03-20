package init.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Value("${db.security.driver}")
	private String driver;
	@Value("${db.security.url}")
	private String url;
	@Value("${db.security.user}")
	private String user;
	@Value("${db.security.password}")
	private String pass;
	
	@Bean
	public JdbcUserDetailsManager users() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);
		JdbcUserDetailsManager jdbc=new JdbcUserDetailsManager(ds);
		jdbc.setUsersByUsernameQuery("select user,pwd,enabled from users where user=?");
		jdbc.setAuthoritiesByUsernameQuery("select user,rol from roles where user=?");
		return jdbc;
	}
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		http.csrf(c->c.disable())
		.authorizeHttpRequests(
				aut->aut.requestMatchers( HttpMethod.PUT, "/productos/**").hasRole("ADMINS")
						.requestMatchers(HttpMethod.POST, "/productos/**").hasRole("ADMINS")
						.anyRequest().permitAll()
				)
		.httpBasic(Customizer.withDefaults());
		return http.build();
		
	}
}
