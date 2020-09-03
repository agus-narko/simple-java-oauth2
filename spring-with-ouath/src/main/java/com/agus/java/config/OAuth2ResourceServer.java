package com.agus.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests().antMatchers("/swagger-ui/**","/service/api/loginService","/service/api/refreshTokenService").permitAll().and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/service/api/**")
				.access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.POST, "/service/api/**")
				.access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PUT, "/service/api/**")
				.access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.DELETE, "/service/api/**")
				.access("#oauth2.hasScope('trust')").and()
				.logout().logoutUrl("/logout")
				.deleteCookies("JSESSIONID")
				.and()
				.csrf().disable().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}