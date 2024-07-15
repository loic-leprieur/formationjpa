package fr.dawan.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Adresse {

    @Column(length = 150)
    private String ville;

    private String rue;

    @Column(length = 15, name = "code_postal")
    private String codePostal;
}
