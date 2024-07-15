package fr.dawan.jpa.entities.interceptor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "utilisateurs")
public class Utilisateur extends BaseAuditable{

    private static final long serialVersionUID = 1L;
    
    private String nom;
    
    private String prenom;
    
    private String email;
    
    private String password;
}
