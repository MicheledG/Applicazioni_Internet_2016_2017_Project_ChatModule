package it.polito.ai.chat.config;

import it.polito.ai.chat.security.JWTAuthenticationEntryPoint;
import it.polito.ai.chat.security.JWTAuthenticationFilter;
import it.polito.ai.chat.security.JWTAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Boolean SECURITY = false;

    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JWTAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Define a custom authentication provider
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!SECURITY) {
            http
	            .csrf().disable()
	            .authorizeRequests().anyRequest().permitAll();
        } else {
            http
	            // Disable CSRF protection since tokens are immune to it
	            .csrf().disable()
	            // If the user is not authenticated, returns 401
	            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
	            // This is a stateless application, disable sessions
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	            // Security policy
	            .authorizeRequests()
	            // Allow anonymous access for creating a new profile (from AuthModule)
	            // Excepted for CORS preflighted requests
	            .antMatchers(HttpMethod.OPTIONS).permitAll()
	            // Allow only requests on the web socket api
	            .antMatchers("/chat/**").permitAll()
	            // Any request must be authenticated
	            .anyRequest().authenticated().and()
	            // Custom filter for authenticating users using tokens
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            // Disable resource caching, enable only if the client app is external to this module
            	.headers().cacheControl();
        }

    }

}
