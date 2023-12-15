package agencias.service.security;

import agencias.service.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    AuthenticationProvider provider;

    JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(AuthenticationProvider provider, JwtAuthenticationFilter authenticationFilter) {
        this.provider = provider;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/error").permitAll();
                        auth.requestMatchers("/api/login").permitAll();
                        auth.requestMatchers(
                                "/api/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/configuration/**").permitAll();
                        auth.requestMatchers("/api/vuelo/listarVuelos").hasAnyRole("USER", "ADMIN");
                        auth.requestMatchers("/api/usuario").hasAnyRole("USER", "ADMIN");
                        auth.requestMatchers("/api/reserva/alta").hasAnyRole("USER", "ADMIN");
                        auth.requestMatchers("/api/reserva/user/{id}").hasRole("AGENTE_VENTAS");
                        auth.anyRequest().hasRole("ADMIN");
                    })
                .build();
    }
}
