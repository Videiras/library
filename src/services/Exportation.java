package services;

import entities.Author;
import entities.Book;
import entities.Client;
import entities.History;

import java.io.*;
import java.util.List;

public class Exportation {

    public static void exportCatalog(List<Book> catalog) {
        for(Book b : catalog) {
            String path = ".\\books\\" + b.getTITLE();
            File directory = new File(path);
            File book = new File(path + "\\" + b.getTITLE() + ".txt");
            File loanHistory = new File(path + "\\Historico de emprestimos.txt");

            try {
                if (!directory.exists() && !directory.isDirectory()) {
                    new File(path).mkdirs();
                }
                if (!book.exists()) {
                    book.createNewFile();
                }
                if (!loanHistory.exists()) {
                    loanHistory.createNewFile();
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

            for(History history : b.getLoanHistory()) {
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(loanHistory))) {
                    bw.write(history.toFile());
                    bw.newLine();
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
        File file = new File(".\\books\\1 - Livros.txt");

        try{
            if(!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for(Book book : catalog) {
                bw.write(book.getTITLE());
                bw.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void exportClients(List<Client> clients) {
        String path =".\\clients";
        File directory = new File(path);
        File file = new File(directory + "\\clients.txt");

        try {
            if(!directory.exists() && !directory.isDirectory()) {
                new File(path).mkdir();
            }
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    public static void exportAuthors(List<Author> authors) {
        String path = ".\\authors";
        File directory = new File(path);
        File file = new File(directory + "\\authors.txt");

        try {
            if (!directory.exists() && !directory.exists()) {
                new File(path).mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            for(Author author : authors) {
                bw.write(author.toFile());
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
