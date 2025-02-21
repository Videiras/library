import entities.Book;
import entities.Client;
import entities.Library;
import entities.enums.IsGuest;
import services.Exportation;
import services.Importation;
import services.LoginSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Library library = new Library();

        Importation.importClients(library);
        Importation.importAuthors(library.getAuthors());
        Importation.importCatalog(library.getAuthors(), library, library.getClients());

        System.out.println("Biblioteca!");
        Client loggedClient = new Client("Convidado", LocalDate.now(), null, IsGuest.GUEST);
        System.out.println();
        boolean exit = false;

        while(!exit) {
            System.out.println("Menu: ");
            System.out.println("1 - Ver catálogo completo");
            System.out.println("2 - Registrar-se");
            System.out.println("3 - Fazer Login");
            System.out.println("4 - Solicitar empréstimo de um livro");
            System.out.println("5 - Realizar devolução de empréstimo");
            System.out.println("6 - Adicionar um livro ao catálogo");
            System.out.println("7 - Sair");
            int selection = sc.nextInt();
            sc.nextLine();

            if (selection == 1) {
                for (Book b : library.getBooks()) {
                    System.out.println(b.getTITLE());
                }
                System.out.println();
            }

            else if (selection == 2) {
                library.registerClient(sc, dtf);
            }

            else if (selection == 3) {
                loggedClient = LoginSystem.login(sc, library.getClients(), library);
            }

            else if (selection == 4) {
                if (loggedClient.getIsGuest() != IsGuest.GUEST) {
                    library.loan(sc, loggedClient, dtf);
                }
                else {
                    System.out.println("Somente clientes com registro podem solicitar empréstimos!");
                }
            }

            else if (selection == 5) {
                library.devolution(loggedClient, library, dtf, sc);
            }

            else if (selection == 6) {
                library.registerBook(sc, dtf, library);
            }

            else if (selection == 7) {
                System.out.println("Obrigado por visitar a biblioteca!");
                Exportation.exportAuthors(library.getAuthors());
                Exportation.exportCatalog(library.getBooks());
                Exportation.exportClients(library.getClients());
                exit = true;
            }
        }
    }
}