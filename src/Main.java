import entities.Author;
import entities.Book;
import entities.Client;
import entities.Library;
import services.LoginSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        Client client1 = new Client("Victor Alexandre", LocalDate.of(2004, 7, 2), "jevunidos@gmail.com");

        library.addClient(client1);



        Book book1 = new Book("Pai Rico Pai Pobre", library.getAuthors().getFirst());
        Book book2 = new Book("Harry Potter e isso ai", library.getAuthors().getFirst());
        Book book3 = new Book("Harry potter e isso ai 2", library.getAuthors().getFirst());

        library.addBooks(book1);
        library.addBooks(book2);
        library.addBooks(book3);

        for(Book b : library.getBooks()) {
            System.out.println(b.toFile());
        }

        Client loggedClient = LoginSystem.login(sc, library.getClients(), library);

        if(loggedClient == null) {
            System.out.println("Login falhou, tente novamente!");
        }
        else {
            library.loan(sc, loggedClient, dtf);
        }

    }
}