package entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public Library(){
        authors.add(new Author("Autor desconhecido", LocalDate.now()));
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

    public void registerBook(Scanner sc, DateTimeFormatter dtf) {
        System.out.print("Título do livro: ");
        String title = sc.nextLine();
        System.out.print("ID do Autor: ");

        Author author = null;
        boolean authorExists = false;

        while (!authorExists) {
            int authorID = sc.nextInt();
            sc.nextLine();

            for (Author a : authors) {
                if (authorID == a.getId()) {
                    author = a;
                    authorExists = true;
                    break;
                }
            }

            if (!authorExists) {
                System.out.print("Autor não encontrado, digitação do ID está correta? (s/n) ");
                char confirmation = sc.next().toLowerCase().charAt(0);
                sc.nextLine();

                if (confirmation == 's') {
                    author = authors.get(0);
                    authorExists = true;
                }
                else {
                    System.out.println("Tente digitar o ID novamente.");
                }
            }
        }
        books.add(new Book(title, author));
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

    public void registerClient(Scanner sc, DateTimeFormatter dtf) {
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
        boolean clientExists = true;

        Client newClient = new Client(name, bornDate, email);

        while(clientExists) {
            for (Client c : clients) {
                if (c.equals(newClient)) {
                    System.out.println("Já existe um cliente com este E-mail! Tente outro E-mail: ");
                    email = sc.nextLine();
                } else {
                    clientExists = false;
                }
            }
        }

        if(!clientExists) {
            clients.add(newClient);
            System.out.println("Cliente registrado com sucesso!");
        }
    }

    public void login(Scanner sc){
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();
        boolean clientFound = false;


        for(Client c : clients) {
            if(c.getEmail() == email) {
                System.out.println("Boas vindas! " + c.getName());
                clientFound = true;
                break;
            }
        }

        if(!clientFound){
            System.out.println("Cliente não encontrado!");

        }
    }
}
