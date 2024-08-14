package fr.dawan.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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

//Une classe intégrable va stocker ses données dans la
//table de l’entité mère ce qui va créer des colonnes supplémentaires

//On annote une classe intégrable avec @Embeddable
@Embeddable
public class Adresse {

    private String rue;

    @Column(length = 150)
    private String ville;

    @Column(length = 15, name = "code_postal")
    private String codePostal;
}