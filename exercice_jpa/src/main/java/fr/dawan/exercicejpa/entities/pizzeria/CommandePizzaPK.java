package fr.dawan.exercicejpa.entities.pizzeria;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Embeddable
public class CommandePizzaPK {

    private long pizzaId;
    
    private long commandeId;
    
}
