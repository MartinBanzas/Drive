package com.martin.Drive.config;

import com.martin.Drive.filter.JwtFilter;
import com.martin.Drive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:3000")
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public UserDetailsService userDetailsService() {return  new UserService();}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(configurer -> configurer
                        .requestMatchers("/api/ficheroes").permitAll()
                        .requestMatchers("http://localhost:3000/trello/**").hasRole("ADMIN")
                        .requestMatchers("/trello").hasRole("ADMIN")

                )
                .formLogin(form ->
                        form
                                .loginPage("http://localhost:3000/signIn")
                                .loginProcessingUrl("http://localhost:3000/authenticateTheUser")
                                .permitAll()
                )
                .rememberMe(rememberMe ->
                        rememberMe
                                .key("ashkdfjas212766-vzbxghgh-782163") // Cambia esto por una clave única
                                .rememberMeCookieName("cookieWebMartin")
                                .tokenValiditySeconds(6048000) // Duración del token en segundos (10 semanas en este ejemplo)
                )

                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .maximumSessions(1) // Número máximo de sesiones simultáneas permitidas
                                .maxSessionsPreventsLogin(false) // Si establecer esto en true debe evitar que un usuario inicie sesión en otra ubicación si ya tiene una sesión activa.
                                .expiredUrl("http://localhost:3000/signIn?expired") // URL a la que redirigir si la sesión expira
                )

                .logout(logout -> logout
                        .logoutUrl("http://localhost:3000/signIn") // URL de cierre de sesión personalizada
                        .deleteCookies("cookieWebMartin") // Eliminar la cookie "Recuérdame" al cerrar sesión
                        .invalidateHttpSession(true) // Invalidar la sesión HTTP
                        .logoutSuccessUrl("/juegos/lista") // Redirigir a la página de inicio de sesión con un mensaje de cierre de sesión
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();;


        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Permitir solicitudes desde http://localhost:3000
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
