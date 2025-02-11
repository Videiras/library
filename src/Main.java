import entities.Author;
import entities.Book;
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
        LocalDate bornDate = LocalDate.of(2000, 1, 10);
        Author author = new Author("Autor", bornDate);

        Library library = new Library();

        library.registerAuthor(sc, dtf);
        System.out.println(library.getAuthors());
        library.registerBook(sc, dtf);
        System.out.println(library.getBooks());

    }
}