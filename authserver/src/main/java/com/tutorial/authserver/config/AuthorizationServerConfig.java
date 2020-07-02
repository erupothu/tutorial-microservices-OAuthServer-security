package com.tutorial.authserver.config;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter implements AuthorizationServerConfigurer {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthorizationServerConfig.class);

	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private DataSource dataSource;
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
//    @Bean
//    TokenStore jdbcTokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
    
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;

        try {
            accessToken = new DefaultOAuth2AccessToken(tokenValue);
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token "+tokenValue);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for " +tokenValue,e);
        }

        return accessToken;
    }

	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
	}

//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
//	}
	
	public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.inMemory().withClient("mobile").secret(passwordEncoder.encode("pin")).redirectUris("http://localhost:8081/login", "http://localhost:8071/login", "http://localhost:8082/login", "http://localhost:8083/login").scopes("READ", "WRITE").authorizedGrantTypes("password", "authorization_code").autoApprove(true);
    }

	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(readAccessToken());
        endpoints.authenticationManager(authenticationManager);
        endpoints.approvalStoreDisabled();
	}
	
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("krish").password(passwordEncoder.encode("krishpass")).roles("USER","ADMIN","MANAGER").and()
//                .withUser("suranga").password(passwordEncoder.encode("surpass")).roles("USER").and()
//                .withUser("harish").password(passwordEncoder.encode("harish")).roles("ADMIN","ENDPOINT","USER");
//    }
    
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.formLogin().permitAll().and()
            .authorizeRequests().antMatchers("/api/library/welcome", "/api/library/testing", "/login", "/oauth/authorize").permitAll().and()
            .authorizeRequests().antMatchers("/h2-console/**").hasRole("admin").and()
            .authorizeRequests().antMatchers("/swagger-ui.html/**").hasRole("admin").and()
            .authorizeRequests().antMatchers("/actuator/metrics/**").hasRole("admin")
            .anyRequest().authenticated();
        http.headers().frameOptions().disable();
      }

}
