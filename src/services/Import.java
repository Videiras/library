package services;

import entities.*;
import entities.enums.Disponibility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Import {

    public List<Book> importCatalog(List<Author> authors, Library library, String[] clients, List<Client> client) {
        for (String s : clients) {
            File catalog = new File(".\\" + s  + "\\" + s + ".txt");

            try {
                if (!catalog.exists()) {
                    catalog.createNewFile();
                }

                List<History> loanHistory = new ArrayList<>();

                BufferedReader history = new BufferedReader(new FileReader("\\" + s + "\\loanHistory.txt"));
                String historyLine = history.readLine();
                while(historyLine != null) {
                    String[] historyItems = historyLine.split(",");
                    int ID = Integer.parseInt(historyItems[0]);
                    LocalDate CHECKOUT_DATE = LocalDate.parse(historyItems[1]);
                    LocalDate DUE_DATE = LocalDate.parse(historyItems[2]);
                    for(Client c : client) {
                        if(c.getID() == ID) {
                            loanHistory.add(new History(c, CHECKOUT_DATE, DUE_DATE));
                        }
                    }
                    historyLine = history.readLine();
                }

                BufferedReader book = new BufferedReader(new FileReader(catalog));
                String line = book.readLine();

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

                    line = book.readLine();
                }

            } catch (IOException e) {
                e.getMessage();
            }
        }

        return null;

    }
}
