package br.com.bcoder.appMed.config;

import br.com.bcoder.appMed.filter.CsrfCookieFilter;
import br.com.bcoder.appMed.filter.JwtAuthFilter;
import br.com.bcoder.appMed.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.security.Key;
import java.util.Collections;

@Configuration
public class AppSecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;

    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    public static final String[] ENDPOINTS_USERS = {
            "/consultations/**",
            "/users/**",
            "/exams/**",
            "/medics/**"
    };

    public static final String[] ENDPOINTS_PERMIT_ALL = {
            "/register/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/actuator/**",
    };

    public static final String[] ignoringRequestMatchers = {
            "/register/**",
    };

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        return http.cors().configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration(); // Setting Cors configuration
                    configuration.setAllowedOrigins(Collections.singletonList("*")); // Setting Cors Allowed Origins
                    configuration.setAllowedMethods(Collections.singletonList("*")); // Setting Cors Allowed Methods
                    configuration.setAllowCredentials(true);
                    configuration.setAllowedHeaders(Collections.singletonList("*")); // Setting Cors Allowed Headers
                    configuration.setMaxAge(3600L);
                    return configuration;
                }).and()
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestAttributeHandler).ignoringRequestMatchers(ignoringRequestMatchers)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(ENDPOINTS_PERMIT_ALL).permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers(ENDPOINTS_USERS).authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
