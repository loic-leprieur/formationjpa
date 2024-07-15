package fr.dawan.jpa.entities;

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

@Embeddable
public class PersonneAdressePK {

    private long idAdresse;

    private long idPersonne;
}
