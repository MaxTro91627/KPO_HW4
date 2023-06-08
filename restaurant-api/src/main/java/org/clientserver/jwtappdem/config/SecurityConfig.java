package org.clientserver.jwtappdem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CREATE_ORDER_EP = "/api/order/create";
    private static final String SHOW_ORDER_EP = "/api/order/show";
    private static final String SHOW_MENU_EP = "/api/menu/show";
    private static final String ADD_DISH_EP = "/api/dish/add";
    private static final String CHANGE_DISH_EP = "/api/dish/change";
    private static final String DELETE_DISH_EP = "/api/dish/delete";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(CREATE_ORDER_EP, SHOW_ORDER_EP, SHOW_MENU_EP,
                        ADD_DISH_EP, CHANGE_DISH_EP, DELETE_DISH_EP).permitAll()
                .anyRequest().authenticated()
                .and();
    }
}