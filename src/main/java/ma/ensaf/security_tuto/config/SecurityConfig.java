package ma.ensaf.security_tuto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())  // pour simplifier si tu utilises seulement formulaire

                .authorizeHttpRequests(auth -> auth
                        // Ressources publiques
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()

                        // Gestion des admins (utilisateurs + produits + catégories)
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Gestion des produits / médicaments (ADMIN + PHARMACIEN)
                        .requestMatchers("/medicaments/**").hasAnyRole("ADMIN", "PHARMACIEN")
                        .requestMatchers("/produits/**").hasAnyRole("ADMIN", "PHARMACIEN")

                        // Gestion des ventes (CAISSIER + ADMIN)
                        .requestMatchers("/ventes/**").hasAnyRole("ADMIN", "CAISSIER")

                        // Pour le site vitrine / consultation
                        .requestMatchers("/", "/home").authenticated()

                        // Tout le reste nécessite connexion
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )


                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied") // page pour ROLE insuffisant
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
