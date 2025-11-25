package ma.ensaf.security_tuto.repository;

import ma.ensaf.security_tuto.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
