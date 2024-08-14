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
@Table(name = "maires")
public class Maire extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 50, nullable = false)
    private String prenom;

    @NonNull
    @Column(length = 100, nullable = false)
    private String nom;

    // Relation @OneToOne bidirectionnelle
    // C'est la relation retour Maire -> Ville
    // pour la réaliser, on place sur la variable d'instance de type Ville
    // on utilise l'annotation @OneToOne avec l'attribut mappedBy qui a pour valeur
    // le nom de variable d'instance de l'autre coté de la relation -> ici maire
    // dans ce cas, on peut connaitre le maire à partir de la ville et la ville
    // depuis le maire
    @OneToOne(mappedBy = "maire")
    private Ville ville;

    // Pour Relation @OneToOne unidirectionnelle
    // On n'a pas de type Ville dans Maire, pour réaliser la relation retour
    // dans ce cas, on peut connaitre le maire à partir de la ville, mais pas la
    // ville depuis le maire
}
