package fr.dawan.jpa.entities.relations;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)

@Entity
@Table(name = "villes")
public class Ville extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 100, nullable = false)
    private String nom;

    // @OneToOne => Relation 1,1
    // ici, Un maire n'est que d'une seul ville et une ville à seul maire

    // Relation @OneToOne Unidirectionnel
    // on a uniquement une relation Ville -> Maire
    // et pas de relation Maire -> Ville , on n'a pas accés au Ville depuis le Maire

    @OneToOne
    // @JoinColumn(name="id_maire") -> @JoinColumn permet de modifier le nom par défaut
    private Maire maire;
    
    // Relation @OneToOne Bidirectionnel => voir dans la classe Maire
    // on a une relation Ville -> Maire et Maire -> Ville


}
