package fr.dawan.jpa.entities.heritage;

import java.io.Serializable;

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
@Table(name = "compte_eparnge")
//@DiscriminatorValue("CE")
public class CompteEpargne extends CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;

    private double taux;

}
