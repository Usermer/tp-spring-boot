package ma.ensaf.security_tuto.repository;


import ma.ensaf.security_tuto.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
