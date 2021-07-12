package com.zg.r2.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
	 
    @Autowired
    UserDetailService userDetailService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                
                .antMatchers(HttpMethod.POST, "/book/add").authenticated()
                .antMatchers(HttpMethod.GET, "/book/add").authenticated()
                .antMatchers(HttpMethod.GET,"/book/delete/{bookId}").authenticated()
                .antMatchers(HttpMethod.POST,"/book/edit").authenticated()
                .antMatchers("/publisher/**").hasRole("admin")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()       
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                
                .logoutSuccessUrl("/login?logout")
           
                .permitAll();
       
       
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
