package filippotimo;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bw1backendpu");


    public static void main(String[] args) {

        System.out.println("Hello World!");
    }
}
