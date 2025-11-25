package ma.ensaf.security_tuto.repository;

import ma.ensaf.security_tuto.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
}
