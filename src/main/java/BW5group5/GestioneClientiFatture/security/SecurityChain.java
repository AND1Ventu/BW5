package BW5group5.GestioneClientiFatture.security;

import BW5group5.GestioneClientiFatture.model.Tipologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityChain {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/auth/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/utenti/**").hasAnyAuthority(Tipologia.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/comuni/**").hasAnyAuthority(Tipologia.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/provincie/**").hasAnyAuthority(Tipologia.ADMIN.name()));
       httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/clienti/**").hasAnyAuthority(Tipologia.ADMIN.name()));
       httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/fatture/**").hasAnyAuthority(Tipologia.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/indirizzi/**").hasAnyAuthority(Tipologia.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/**").hasAnyAuthority(Tipologia.USER.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").denyAll());


        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOrigin("http://www.example.com");
        cors.addAllowedMethod(HttpMethod.GET);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("*/**", cors);
        return configurationSource;
    }
}