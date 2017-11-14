package in.happycamp.management.config;

import in.happycamp.management.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(getPasswordEncoder());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/css/**", "/fonts/**", "/js/**", "/templates/**", "/images/**").permitAll()
				.antMatchers("/").hasAnyAuthority("ADMIN, USER")
				.antMatchers("/users/register").permitAll()
				.antMatchers("/users/login").permitAll()
				.antMatchers("/users/new").hasAuthority("ADMIN")
				.antMatchers("/users/").hasAuthority("ADMIN")
				.anyRequest().fullyAuthenticated()
				.and()
				.exceptionHandling().accessDeniedPage("/")
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll();
		//		http.headers().cacheControl().disable();

	}

}
