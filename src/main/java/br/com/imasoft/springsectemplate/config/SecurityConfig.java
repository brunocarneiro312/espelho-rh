package br.com.imasoft.springsectemplate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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

                // Concede acesso autenticado à API privada (/api/v1)
                authorize.antMatchers(getApiAntMatchers()).authenticated();

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
     * -------------------------------------
     * InMemory Authentication Configuration
     * -------------------------------------
     */
    @Override
    protected UserDetailsService userDetailsService() {

        UserDetails common = User.withUsername("common")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("123456")
                .roles("COMMON")
                .build();

        UserDetails admin = User.withUsername("admin")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("admin")
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(common);
        userDetailsManager.createUser(admin);

        return userDetailsManager;
    }

    /**
     * --------------
     * H2 AntMatchers
     * --------------
     * @return
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

}
