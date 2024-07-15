package fr.dawan.jpa.entities;

import java.io.Serializable;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "entreprises")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Entreprise extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ent_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ent_seq")
    private long id;

    private String nom;

}
