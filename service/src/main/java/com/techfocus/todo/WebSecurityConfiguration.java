package com.techfocus.todo;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	
	@Resource
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

  /*@Resource
  AccountRepository userRepository;
  
  @Resource
  CustomUserDetailsService  userDetailsService;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }


}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Resource
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}*/
	
 /* @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
    	httpBasic().and().
    	addFilterBefore(new AuthenticationFilter(), BasicAuthenticationFilter.class)
    	.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
    	.csrf().disable()
     
     .authorizeRequests()
     .antMatchers("/socket/**").permitAll()
     .anyRequest().authenticated();
  }*/
  
  /*private CsrfTokenRepository csrfTokenRepository() {
	  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
	  repository.setSessionAttributeName("_csrf");
	  repository.setHeaderName("X-XSRF-TOKEN");
	  return repository;
	}*/
	
	
  
}