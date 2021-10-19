package com.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ecommerce.configData.GoogleOAtuhSuccessHandler;
import com.ecommerce.entity.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	final CustomUserDetailsService customUserDetailsService;
	final GoogleOAtuhSuccessHandler googleOAtuhSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable();
		http.authorizeRequests().
			antMatchers("/","/shop/**","/register").
			permitAll().
			antMatchers("/admin/**").hasRole("ADMIN").
			anyRequest().
			authenticated().
			and().
			formLogin().
			loginPage("/login").
			permitAll().
			failureUrl("/login?error=true").
			defaultSuccessUrl("/").
			usernameParameter("email").
			passwordParameter("password").
			and().
			oauth2Login().
			loginPage("/login").
			successHandler(googleOAtuhSuccessHandler).
			and().
			logout().
			logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
			logoutSuccessUrl("/login").
			invalidateHttpSession(true).
			deleteCookies("JSESSIONID").
			and().
			exceptionHandling().
			and().
			csrf().disable();
			
			
			
		http.headers().frameOptions().disable();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/images/**", "/productImages/**", "/css/**", "/js/**");
		
	}
	
}
