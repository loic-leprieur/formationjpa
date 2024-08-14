package fr.dawan.exercicejpa.entities.bibliotheque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class StatLivre {
    private String data;
    
    private long count;
}
