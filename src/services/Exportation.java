package services;

import entities.Book;
import entities.Client;
import entities.History;

import java.io.*;
import java.util.List;

public class Exportation {

    public void exportCatalog(List<Book> catalog, String[] books) {
        for(Book b : catalog) {
            String path = ".\\books" + b.getTITLE();
            File directory = new File(path);
            File book = new File(path + "\\" + b.getTITLE() + ".txt");
            File loanHistory = new File(path + "\\loanHistory.txt");

            try {
                if (!directory.exists() && !directory.isDirectory()) {
                    new File(path).mkdir();
                }
                if (!book.exists()) {
                    book.createNewFile();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            for(History history : b.getLoanHistory()) {
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\loanHistory.txt"))) {
                    bw.write(history.toFile());
                }
                catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(book))) {
                bw.write(b.toFile());
                bw.newLine();
            }
            catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }



    }

    public void exportClients(List<Client> clients) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(".\\clients\\clients.txt"))) {
            for(Client client : clients) {
                bw.write(client.toFile());
                bw.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
