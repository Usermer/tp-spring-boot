package ma.ensaf.security_tuto.init;

import ma.ensaf.security_tuto.model.Role;
import ma.ensaf.security_tuto.model.User;
import ma.ensaf.security_tuto.repository.RoleRepository;
import ma.ensaf.security_tuto.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // --- 1) CRÉATION DES RÔLES ---
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));

        Role pharmaRole = roleRepository.findByName("PHARMACIEN")
                .orElseGet(() -> roleRepository.save(new Role(null, "PHARMACIEN")));

        Role caisseRole = roleRepository.findByName("CAISSIER")
                .orElseGet(() -> roleRepository.save(new Role(null, "CAISSIER")));


        // --- 2) CRÉATION DES USERS ---

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEnabled(true);
            admin.setRols(Set.of(adminRole));
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("pharma").isEmpty()) {
            User pharma = new User();
            pharma.setUsername("pharma");
            pharma.setPassword(passwordEncoder.encode("pharma123"));
            pharma.setEnabled(true);
            pharma.setRols(Set.of(pharmaRole));
            userRepository.save(pharma);
        }

        if (userRepository.findByUsername("caisse").isEmpty()) {
            User caisse = new User();
            caisse.setUsername("caisse");
            caisse.setPassword(passwordEncoder.encode("caisse123"));
            caisse.setEnabled(true);
            caisse.setRols(Set.of(caisseRole));
            userRepository.save(caisse);
        }

        System.out.println("=== Données de sécurité initialisées ===");
    }
}
