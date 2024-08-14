package fr.dawan.jpa.entities.heritage;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
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
// Héritage => trois façons d’organiser l’héritage

//1 - SINGLE_TABLE => Le parent et les enfants vont être placé dans la même table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Une colonne "Discriminator" définit le type de la classe enregistrée
@DiscriminatorColumn(name = "type_compte")
//Pour la classe CompteBancaire la valeur dans la colonne discriminator => CB
@DiscriminatorValue("CB")

//2 - TABLE_PER_CLASS => le parent et chaque enfant auront leur propre table
//la table enfant aura les colonnes des variables d'instance de la classe parent
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//3 - JOINED => on aura une jointure entre la table de la classe parent et la table de la classe enfant
//@Inheritance(strategy = InheritanceType.JOINED)
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    // Avec TABLE_PER_CLASS -> on ne peut pas utiliser GenerationType.IDENTITY
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "cb_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cb_seq")
    private long id;

    private String titulaire;

    private double solde;

    public CompteBancaire(String titulaire, double solde) {
        this.titulaire = titulaire;
        this.solde = solde;
    }

}
