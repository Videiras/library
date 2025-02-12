import entities.Author;
import entities.Book;
import entities.Client;
import entities.Library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        Client client1 = new Client("Victor", LocalDate.of(2000, 10, 5), "jevunidos@gmail.com");
        Client client2 = new Client("Jose", LocalDate.of(2004, 2, 4), "jevunidos@gmail.com");
        Client client3 = new Client("Carlos", LocalDate.of(2006, 1, 30), "carlos@gmail.com");

        library.addClient(client1);
        library.addClient(client2);
        library.addClient(client3);

        System.out.println(client1.equals(client2));
        System.out.println(client2.equals(client3));
        System.out.println(client1.equals(client3));

        for(Client c : library.getClients()) {
            System.out.println(c);
        }

        for(int i = 0; i < 3; i++) {
            library.registerClient(sc, dtf);
        }
        library.login(sc);

    }
}