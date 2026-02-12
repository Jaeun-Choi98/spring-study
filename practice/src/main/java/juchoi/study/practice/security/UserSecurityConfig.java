package juchoi.study.practice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

  private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Bean
  UserDetailsService authentication() {
    UserDetails jaeun = User.builder()
        .username("jaeun")
        .password(passwordEncoder.encode(
            "1234"))
        .roles("USER", "ADMIN")
        .build();

    UserDetails u = User.builder()
        .username("user")
        .password(passwordEncoder.encode(
            "1234"))
        .roles("USER")
        .build();

    System.out.println(" >>> Jaeun's password: " + jaeun.getPassword());
    return new InMemoryUserDetailsManager(jaeun, u);
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests()
        .requestMatchers("/users/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic()
        .and()
        .build();
  }

}
