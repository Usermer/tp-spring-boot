package ma.ensaf.security_tuto.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor

@Entity
public class DetailProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fabricant;
    private String paysOrigine;
    private String composition;
    private String dosage;
    private String indications;

    @OneToOne(mappedBy = "detailProduit")
    @JsonIgnore
    private Produit produit;


}