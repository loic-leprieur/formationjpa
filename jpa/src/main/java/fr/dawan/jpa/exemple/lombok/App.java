package fr.dawan.jpa.exemple.lombok;

public class App {
    public static void main(String[] args) {
        
        // Exemple lombok
        Article a1 = new Article(400.0);
        // a1.setDescription(null); // @NonNull -> entraîne la génération de
        // vérification par rapport à la valeur null
        a1.setDescription("TV");
        System.out.println(a1);

        Article a2 = new Article(30.0, "Clavier", new Marque("marque A"));
        System.out.println(a2);

        a2.testLogger();

        // @Builder
        Article a3 = Article.builder().prix(30.0).description("Souris sans-fils").build();
        System.out.println(a3);
    }
}
