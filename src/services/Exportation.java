package services;

import entities.Author;
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
        String path =".\\clients";
        File directory = new File(path);
        File file = new File(directory + "\\clients.txt");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(".\\clients\\clients.txt"))) {
            if(!directory.exists() && !directory.isDirectory()) {
                new File(path).mkdir();
            }
            if(!file.exists()) {
                file.createNewFile();
            }

            for(Client client : clients) {
                bw.write(client.toFile());
                bw.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportAuthors(List<Author> authors) {
        String path = ".\\authors";
        File directory = new File(path);
        File file = new File(directory + "\\authors");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            if(!directory.exists() && !directory.exists()) {
                new File(path).mkdir();
            }
            if(!file.exists()) {
                file.createNewFile();
            }

            for(Author author : authors) {
                bw.write(author.toFile());
                bw.newLine();
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
