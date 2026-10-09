package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import common.ContratCabinet;
import common.ContratObservateur;

public class clientMain {
    public static void main(String[] args) {
        // Lecture des arguments avec valeurs par défaut
    	String host = (args.length > 0) ? args[0] : "localhost";
    	int port = 1099;
    	if (args.length > 1) {
    	    try {
    	        port = Integer.parseInt(args[1]);
    	    } catch (NumberFormatException e) {
    	        System.out.println("Port invalide, utilisation de 1099.");
    	    }
    	}

        try {
            System.out.println("Connexion au cabinet sur " + host + ":" + port + "...");
            Registry registry = LocateRegistry.getRegistry(host, port);
            ContratCabinet stub = (ContratCabinet) registry.lookup("cabinet1");
            
            GestionnaireCabinet logique = new GestionnaireCabinet(stub);
            MenuConsole menu = new MenuConsole(logique);
            
            menu.demarrer();  
            
        } catch (Exception e) {
            System.err.println("Erreur de connexion au serveur : " + e.getMessage());
        }
    }
}