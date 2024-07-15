package fr.dawan.jpa.entities.interceptor;

import java.io.Serializable;
import java.time.LocalDate;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import jakarta.persistence.Column;
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
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter

@Log
@Entity
@Table(name = "contrats")
public class Contrat extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "nom_client")
    @NonNull
    private String nomClient;

    @NonNull
    private LocalDate debut;
    
    @NonNull
    private Integer duree;
    
    @Transient
    private LocalDate fin;
    
    @PrePersist
    public void onPrepersist() {
        log.info("Pre persis");
    }
    
    @PostLoad
    public void calculFinContrat() {
        fin = debut.plusYears(duree);
    }
}
