package ma.ensaf.security_tuto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@NoArgsConstructor
@Data
@AllArgsConstructor


@Entity
@Table(name="user")
public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @Column(unique = true,length = 50)
     private String username;


     private String password;
     private Boolean enabled=true;

     @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> rols;




}
