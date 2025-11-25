package ma.ensaf.security_tuto.service;

import ma.ensaf.security_tuto.model.User;
import ma.ensaf.security_tuto.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.User.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        //orElseThrrow donc user repository doit renvoir un optionnal
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true, true, true,
                user.getRols()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                        .collect(Collectors.toList())
        );
    }
}
