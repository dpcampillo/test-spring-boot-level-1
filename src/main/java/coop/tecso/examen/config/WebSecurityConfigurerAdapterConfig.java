package coop.tecso.examen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import coop.tecso.examen.config.filter.JwtAuthenticationFilter;
import coop.tecso.examen.config.filter.JwtAuthorizationFilter;
import coop.tecso.examen.service.UserService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true) // Habilitamos la securización de nuestra API con @Secured
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilitamos la securización de nuestra API con @Secured
public class WebSecurityConfigurerAdapterConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public WebSecurityConfigurerAdapterConfig(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
		return new JwtAuthorizationFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Configuración de la clase que recupera los usuarios y algorito para procesar
		// las passwords
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and()
				.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
				.anyRequest().authenticated().and()
				.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

}
