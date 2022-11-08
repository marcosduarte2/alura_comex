package br.com.alura.comex.config.secutity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationManagerConfig  extends AuthorizationServerConfigurerAdapter{
		
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
	    private PasswordEncoder passwordEncoder;

		@Value("${jwt.secret}")
		private String jwtSecret;

		@Value("${jwt.accesstoken.expiration}")
		private Integer accessExpiration;

		@Value("${jwt.refreshtoken.expiration}")
		private Integer refreshExpiration;
		@Value("${api.client}")
		private String cliente;
		@Value("${api.client.secret}")
		private String secret;



		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
				.withClient(this.cliente)
				.secret(this.passwordEncoder.encode(this.secret))
				.scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(this.accessExpiration)
				.refreshTokenValiditySeconds(this.refreshExpiration);

		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
				.tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager);
		}

		@Bean
		public JwtAccessTokenConverter accessTokenConverter() {
			JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
			accessTokenConverter.setSigningKey(this.jwtSecret);
			return accessTokenConverter;
		}
	
		@Bean
		public TokenStore tokenStore() {
			return new JwtTokenStore(accessTokenConverter());
			
		}
		

}
