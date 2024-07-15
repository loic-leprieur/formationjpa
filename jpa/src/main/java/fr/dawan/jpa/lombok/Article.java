package fr.dawan.jpa.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@Builder
//@Data
//@Value
@Log
public class Article {

    @lombok.ToString.Include(name = "prixUnitaire", rank = 0)
    private double prix;

    @Include
    @NonNull
    @lombok.ToString.Include(rank = 1)
    private String description;

    // @Include
    // @Exclude
    private Marque marque;

    public void testLogger() {
        log.info("test du logger");
    }

//    public Article(double prix) {
//        super();
//        this.prix = prix;
//    }
}
