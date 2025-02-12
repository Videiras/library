package services;

import entities.Client;
import entities.Library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LoginSystem {

    public static Client login(Scanner sc, List<Client> clients, Library library){
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();
        boolean clientFound = false;
        Client loggedClient = new Client("Convidado", LocalDate.now(), email);

        for(Client c : clients) {
            if(c.equals(loggedClient)) {
                System.out.println("Boas vindas! " + c.getName());
                loggedClient = c;
                clientFound = true;
                break;
            }
        }

        if(!clientFound){
            System.out.println("Cliente não encontrado! Deseja cadastrar um novo cliente?(s/n) ");
            char confirmation = sc.nextLine().charAt(0);

            if(confirmation == 's') {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                loggedClient = library.registerClient(sc, dtf);
            }
            else {
                return null;
            }
        }
        return loggedClient;
    }

}
