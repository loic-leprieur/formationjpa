package fr.dawan.requetejpa.entities;

import java.io.Serializable;

import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

@Entity
@Table(name = "logos")
@Audited(withModifiedFlag = true)
public class Logo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String nom;
    
    @OneToOne(mappedBy = "logo")
    @Exclude
    private Fournisseur fournisseur;

}
