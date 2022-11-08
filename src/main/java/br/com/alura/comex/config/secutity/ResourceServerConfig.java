package br.com.alura.comex.config.secutity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;



@Configuration
@EnableResourceServer	
public class ResourceServerConfig extends  ResourceServerConfigurerAdapter {

		@Autowired
	    private AutenticacaoService autenticacaoService;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(autenticacaoService).passwordEncoder(this.passwordEncoder);
		}


		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/oauth/token").permitAll()
					.antMatchers("/swagger-ui.html").permitAll()
					.antMatchers("/swagger-ui/**").permitAll()
					.antMatchers("/v3/**").permitAll()
					.antMatchers("/actuator/**").permitAll()
					.anyRequest().authenticated()
					.and().formLogin().and().httpBasic().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.csrf().disable();

		}
		
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.stateless(true);

		}
}
