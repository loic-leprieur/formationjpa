package fr.dawan.jpa.entities.interceptor;

import java.time.LocalDate;

import org.hibernate.query.sqm.TemporalUnit;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Log
@Entity
@Table(name = "contrats")
public class Contrat extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NonNull
    private String nonClient;

    @NonNull
    private LocalDate debut;

    @NonNull
    private Integer duree;

    @Transient
    private LocalDate fin;

    @PrePersist
    public void onPrepersit() {
        log.info("Pre persist");
    }

    @PostLoad
    public void calculFinContrat() {
        log.info("Post Load");
        fin = debut.plusYears(duree);
    }

}
