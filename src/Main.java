import entities.Author;
import entities.Book;
import entities.Client;
import entities.Library;
import services.Exportation;
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

        Author author = new Author("Nome teste", LocalDate.of(1987, 10, 30));

        library.addAuthor(author);

        Book book1 = new Book("Pai Rico Pai Pobre", author);
        Book book2 = new Book("Pai Pobre Filho Mais", author);

        library.addBooks(book1);
        library.addBooks(book2);

        Exportation.exportCatalog(library.getBooks());

    }
}