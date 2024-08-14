package fr.dawan.jpa.entities.heritage;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
//@Table(name = "compte_epargne") // @Table n'est pas autorisé avec SINGLE_TABLE
//pour SINGLE_TABLE
//Pour la classe CompteEpargne la valeur dans la colonne discriminator => CE
@DiscriminatorValue("CE")
public class CompteEpargne extends CompteBancaire {

    private static final long serialVersionUID = 1L;

    private double taux;

    public CompteEpargne(String titulaire, double solde, double taux) {
        super(titulaire, solde);
        this.taux = taux;
    }

}
