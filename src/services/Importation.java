package services;

import entities.*;
import entities.enums.Disponibility;
import entities.enums.IsGuest;
import entities.enums.Pending;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Importation {

    public static void importCatalog(List<Author> authors, Library library, List<Client> client) {

        String path = ".\\books\\1 - Livros.txt";
        String[] books = null;

        try(BufferedReader br = new BufferedReader((new FileReader(path)))) {
            books = br.readLine().split(",");
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        for(String book : books) {
            File file = new File(".\\books\\" + book + "\\" + book + ".txt");

            try {
                List<History> loanHistory = new ArrayList<>();

                BufferedReader history = new BufferedReader(new FileReader(".\\books\\" + book + "\\Historico de emprestimos.txt"));
                String historyLine = history.readLine();
                while (historyLine != null) {
                    String[] historyItems = historyLine.split(",");
                    int ClientID = Integer.parseInt(historyItems[0]);
                    LocalDate CHECKOUT_DATE = LocalDate.parse(historyItems[1]);
                    if(!historyItems[2].equals("null")) {
                        LocalDate DUE_DATE = LocalDate.parse(historyItems[2]);
                        for (Client c : client) {
                            if (c.getID() == ClientID) {
                                loanHistory.add(new History(c, CHECKOUT_DATE, DUE_DATE));
                            }
                        }
                    }
                    else {
                        for (Client c : client) {
                            if (c.getID() == ClientID) {
                                loanHistory.add(new History(c, CHECKOUT_DATE, null));
                            }
                        }
                    }
                    historyLine = history.readLine();
                }

                BufferedReader readBook = new BufferedReader(new FileReader(file));
                String line = readBook.readLine();

                while (line != null) {
                    String[] catalogItens = line.split(",");
                    int ID = Integer.parseInt(catalogItens[0]);
                    String TITLE = catalogItens[1];
                    int authorID = Integer.parseInt(catalogItens[2]);
                    Disponibility disponibility = Disponibility.valueOf(catalogItens[3]);
                    LocalDate REGISTRATION_DATE = LocalDate.parse(catalogItens[4]);
                    LocalDate updateDate = LocalDate.parse(catalogItens[5]);

                    for (Author a : authors) {
                        if (a.getId() == authorID) {
                            library.addBooks(new Book(ID, TITLE, a, disponibility, REGISTRATION_DATE, updateDate, loanHistory));
                        }
                    }

                    line = readBook.readLine();
                }

            }
            catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
}

    public static void importClients(Library library) {
        File file = new File(".\\clients\\clients.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();
            while (line != null) {
                String[] clientItems = line.split(",");
                int ID = Integer.parseInt(clientItems[0]);
                String name = clientItems[1];
                LocalDate BORN_DATE = LocalDate.parse(clientItems[2]);
                String email = clientItems[3];
                Pending pending = Pending.valueOf(clientItems[4]);
                int borrowedBook = Integer.parseInt(clientItems[5]);
                IsGuest isGuest = IsGuest.valueOf(clientItems[6]);

                library.addClient(new Client(ID, name, BORN_DATE, email, pending, borrowedBook, isGuest));
                line = br.readLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void importAuthors(List<Author> authors) {
        File author = new File(".\\authors\\authors.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(author))) {
            String line = br.readLine();
            while(line != null) {
                String[] authorItems = line.split(",");
                int authorID = Integer.parseInt(authorItems[0]);
                String name = authorItems[1];
                LocalDate bornDate = LocalDate.parse(authorItems[2]);

                authors.add(new Author(authorID, name, bornDate));
                line = br.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
