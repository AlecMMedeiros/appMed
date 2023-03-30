package br.com.bcoder.appMed.config;

import br.com.bcoder.appMed.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
public class AppSecurityConfig {

  public static final String[] ENDPOINTS_USERS = {
      "/consultations/**",
      "/users/**",
      "/exams/**",
      "/medics/**"
  };

  public static final String[] ENDPOINTS_PERMIT_ALL = {
      "/register/**"
  };

  public static final String[] ignoringRequestMatchers = {
      "/register/**",
  };

  @Bean
  SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http) throws Exception {
    CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
    http.cors().configurationSource(request -> {
          CorsConfiguration configuration = new CorsConfiguration(); // Setting Cors configuration
          configuration.setAllowedOrigins(Collections.singletonList("*")); // Setting Cors Allowed Origins
          configuration.setAllowedMethods(Collections.singletonList("*")); // Setting Cors Allowed Methods
          configuration.setAllowCredentials(true);
          configuration.setAllowedHeaders(Collections.singletonList("*")); // Setting Cors Allowed Headers
          configuration.setMaxAge(3600L);
          return configuration;
        }).and()
        .csrf((csrf)-> csrf.csrfTokenRequestHandler(requestAttributeHandler).ignoringRequestMatchers(ignoringRequestMatchers )
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
        .authorizeHttpRequests()
        .requestMatchers(ENDPOINTS_USERS).authenticated()
        .requestMatchers(ENDPOINTS_PERMIT_ALL).permitAll()
        .and().formLogin()
        .and().httpBasic();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12 );
  }
}
