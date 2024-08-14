package fr.dawan.jpa.entities.interceptor;

import fr.dawan.jpa.listeners.AuditListenner;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name="utilisateurs")
public class Utilisateur extends BaseAuditable{

    private static final long serialVersionUID = 1L;

    private String prenom;
    
    private String nom;
    
    private String email;
    
    private String password;
    
}
