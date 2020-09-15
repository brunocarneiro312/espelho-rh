package br.com.imasoft.springsectemplate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;

/**
 * --------------
 * SecurityConfig
 * --------------
 * @author brunocarneiro
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailService;

    /**
     * ---------------------------
     * Http Security Configuration
     * ---------------------------
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> {

                // Libera acesso aos recursos do H2
                authorize.antMatchers(getH2AntMatchers()).permitAll();

                // Libera acesso à API pública (/api/v1/public/)
                authorize.antMatchers(HttpMethod.GET, getPublicApiAntMatchers()).permitAll();

                // Concede acesso autenticado à role de ADMIN para a API de admin (/api/v1/admin)
                authorize.antMatchers(getRootApiAntMatchers()).hasRole("ADMIN");

            })
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic()
            .and()
            .userDetailsService(userDetailsService());

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    /**
     * ------------------
     * JPA Authentication
     * ------------------
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    /**
     * --------------
     * H2 AntMatchers
     * --------------
     */
    private String[] getH2AntMatchers() {
        return new String[] {
                "/console/**",
                "/h2-console/**"
        };
    }

    /**
     * ---------------
     * Api AntMatchers
     * ---------------
     */
    private String[] getApiAntMatchers() {
        return new String [] {
                "/api/v1/service/**"
        };
    }

    /**
     * ----------------------
     * Public Api AntMatchers
     * ----------------------
     */
    private String[] getPublicApiAntMatchers() {
        return new String[] {
                "/api/v1/public/**"
        };
    }

    /**
     * --------------------
     * Root Api AntMatchers
     * --------------------
     */
    private String[] getRootApiAntMatchers() {
        return new String[] {
                "/api/v1/admin/**"
        };
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5000000);
        return multipartResolver;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
