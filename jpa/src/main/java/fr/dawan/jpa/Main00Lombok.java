package fr.dawan.jpa;

import fr.dawan.jpa.lombok.Article;
import lombok.extern.java.Log;

/**
 * Hello world!
 *
 */
@Log
public class Main00Lombok {
    public static void main(String[] args) {

        Article a1 = new Article(1.0, "Bonbon à la banane", null);
        System.out.println(a1.toString());

        // Builder
        Article a2 = Article.builder().description("Bonbon à la fraise").prix(1.30).build();
        System.out.println(a2);

        a2.testLogger();

    }
}
