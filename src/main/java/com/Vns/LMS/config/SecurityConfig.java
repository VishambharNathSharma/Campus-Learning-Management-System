package com.Vns.LMS.config;


import com.Vns.LMS.security.CustomUserDetailsService;
import com.Vns.LMS.security.JWTAuthenticationfilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTAuthenticationfilter jwtAuthenticationfilter;
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,JWTAuthenticationfilter jwtAuthenticationfilter){
    this.customUserDetailsService = customUserDetailsService;
    this.jwtAuthenticationfilter = jwtAuthenticationfilter;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/enrollments/**").permitAll()
                .requestMatchers("/api/courses/**").permitAll()
                .requestMatchers("/api/student/**").hasRole("STUDENT")
                .requestMatchers("/api/teacher/**").hasRole("TEACHER")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/exams/**")
                .hasAnyRole("ADMIN", "TEACHER")
                .requestMatchers(HttpMethod.PUT, "/api/exams/**")
                .hasAnyRole("ADMIN", "TEACHER")
                .requestMatchers(HttpMethod.DELETE, "/api/exams/**")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/exams/**")
                .authenticated()
                .requestMatchers(HttpMethod.POST, "/api/attendance/**")
                .hasAnyRole("ADMIN", "TEACHER")
                .requestMatchers(HttpMethod.PUT, "/api/attendance/**")
                .hasAnyRole("ADMIN", "TEACHER").requestMatchers(HttpMethod.DELETE, "/api/attendance/**")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/attendance/**")
                .authenticated()
                .anyRequest().authenticated()
        );
        return http.build();

    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);


        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}
