package entities;

import entities.enums.Disponibility;
import entities.enums.IsGuest;
import entities.enums.Pending;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Library {

    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public Library(){
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBooks(Book book){
        books.add(book);
    }

    public void removeBooks(Book book){
        books.remove(book);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
    }

    public void removeAuthor(Author author){
        authors.remove(author);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void registerBook(Scanner sc, DateTimeFormatter dtf, Library library) {
        boolean isEverythingCorrect = false;
        while(!isEverythingCorrect) {
            System.out.print("Digite o título do livro: ");
            String title = sc.nextLine();
            System.out.println("Autores disponíveis: ");

            for (Author author : library.getAuthors()) {
                System.out.println(author);
            }

            System.out.println("Digite o ID do autor, 0 para registrar um novo autor ou 1 se for um autor desconhecido!");
            int ID = sc.nextInt();
            sc.nextLine();

            if (ID == 0) {
                library.registerAuthor(sc, dtf);
            }

            else if (ID == 1) {
                library.addBooks(new Book(title, library.getAuthors().getFirst()));
            }

            else {
                for(Author author : library.getAuthors()) {
                    if (author.getId() == ID) {
                        System.out.print("Todos os dados estão corretos (s/n)?");
                        char confirmation = sc.next().charAt(0);

                        if(confirmation == 's') {
                            System.out.println("Livro: " + title + " adicionado ao catálogo!");
                            library.addBooks(new Book(title, author));
                            isEverythingCorrect = true;
                        }
                        else {
                            isEverythingCorrect = false;
                        }
                    }
                }
            }
        }
    }

    public void registerAuthor(Scanner sc, DateTimeFormatter dtf){
        System.out.print("Nome do autor: ");
        String name = sc.nextLine();

        LocalDate bornDate = null;
        boolean validDate = false;

        while (!validDate) {
            try {
                System.out.print("Data de nascimento (dd/MM/aaaa): ");
                String date = sc.nextLine();
                bornDate = LocalDate.parse(date, dtf);
                validDate = true;
                authors.add(new Author(name, bornDate));
            } catch (DateTimeParseException e) {
                System.out.println("Formato da data inválido, por favor, utilize o formato: dd/MM/yyyy");
            }
        }
    }

    public Client registerClient(Scanner sc, DateTimeFormatter dtf) {
        System.out.print("Digite seu nome completo: ");
        String name = sc.nextLine();
        System.out.print("Digite sua data de nascimento (dd/MM/yyyy): ");
        LocalDate bornDate = null;
        boolean validDate = false;
        while(!validDate) {
            try {
                bornDate = LocalDate.parse(sc.nextLine(), dtf);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido, por favor, digite uma data no formato (dd/MM/yyyy)");
            }
        }
        System.out.print("Digite seu E-mail: ");
        String email = sc.nextLine();
        boolean clientExists = false;

        Client newClient = new Client(name, bornDate, email, IsGuest.NOT_GUEST);

        while(!clientExists) {
            for (Client c : clients) {
                if (c.equals(newClient)) {
                    System.out.println("Já existe um cliente com este E-mail! Tente outro E-mail: ");
                    newClient.setEmail(sc.nextLine());
                }
            }
            clientExists = true;
        }

        if(clientExists) {
            clients.add(newClient);
            System.out.println("Cliente registrado com sucesso!");
        }
        return newClient;
    }

    public void loan(Scanner sc, Client loggedClient, DateTimeFormatter dtf){
        System.out.println("Catálogo disponível para empréstimo: ");
        for(Book b : books) {
            if(b.getDisponibility() == Disponibility.available) {
                System.out.println(b);
            }
        }
        System.out.println();
        System.out.print("Digite o ID de um livro do catálogo para espréstimo: ");
        int id = sc.nextInt();
        boolean bookFounded = false;

        while(!bookFounded) {
            if(loggedClient.getPending() == Pending.yes) {
                System.out.println("Você já tem um empréstimo pendente, favor realizar a devolução antes de solicitar um novo empréstimo!");
                bookFounded = true;
            }
            else {
                for(Book b : books) {
                    if(id == b.getID()) {
                        System.out.println("Empréstimo realizado com sucesso! Data de devolução: " + dtf.format(LocalDate.now().plusWeeks(1)));
                        b.addLoanHistory(new History(loggedClient, LocalDate.now(), null));
                        b.setDisponibility(Disponibility.unavailable);
                        loggedClient.setPending(Pending.yes);
                        loggedClient.setBorrowedBook(b.getID());
                        bookFounded = true;
                        break;
                    }
                }
            }
        }
    }

    public void devolution(Client loggedClient, Library library, DateTimeFormatter dtf, Scanner sc) {
        if(loggedClient.getBorrowedBook() == 0 || loggedClient.getPending() == Pending.no) {
            System.out.println("Você não tem nenhum empréstimo pendente!");
        }
        else {
            boolean validDate = false;
            while (!validDate) {
                try {
                    System.out.print("Digite a data de retirada do livro (dd/MM/yyyy): ");
                    LocalDate checkoutDate = LocalDate.parse(sc.nextLine(), dtf);
                    History history = new History(loggedClient, checkoutDate, null);

                    for (Book b : library.getBooks()) {
                        if (Objects.equals(b.getID(), loggedClient.getBorrowedBook())) {
                            for(History h : b.getLoanHistory()) {
                                if (h.equals(history)) {
                                    System.out.println("Devolução realizada com sucesso!");
                                    h.setDueDate(LocalDate.now());
                                    b.setDisponibility(Disponibility.available);
                                    loggedClient.setBorrowedBook(0);
                                    loggedClient.setPending(Pending.no);
                                    b.setUpdateDate(LocalDate.now());
                                    validDate = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                catch (DateTimeParseException e) {
                    System.out.println("Formato da data inválido, por favor tente novamente!");
                }
            }
        }
    }
}
