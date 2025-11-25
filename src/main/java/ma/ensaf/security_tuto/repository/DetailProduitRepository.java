package ma.ensaf.security_tuto.repository;

import ma.ensaf.security_tuto.model.DetailProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailProduitRepository extends JpaRepository<DetailProduit,Long> {
}
