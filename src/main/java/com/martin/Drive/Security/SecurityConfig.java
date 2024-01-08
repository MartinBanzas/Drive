package com.martin.Drive.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@CrossOrigin(origins = "http://localhost:3000")
public class SecurityConfig {

    @Bean
    protected UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
                );


        return http.build();
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


}
