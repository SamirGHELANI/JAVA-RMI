package serveur;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.ContratCabinet;
import common.ContratDossierSuivi;
import common.Contratanimal;
import common.Espece;
  

 

public class serveurMain {
	
	 public static void main(String[] args) {
 	        try {
 	        	System.setProperty("java.rmi.server.hostname", "localhost");
 	        	Registry registre = LocateRegistry.createRegistry(1099);
 	        	Espece espc1 = new Espece("chien", 5);
 	        	Espece espc2 = new Espece("cat",4 );
 	        	Espece espc3 = new Espece("CAW", 9);
 	        	ContratDossierSuivi dossier = new DossierSuiviImp("bon etat ");
 	            Contratanimal monAnimalImp1 = new AnimalImp("1734","roki","michel", "haski",espc1,13 , "ble" ,dossier);
 	            Contratanimal monAnimalImp2 = new AnimalImp("1434","siamo","sina", "raww",espc2, 13,"gros", dossier);
 	            Contratanimal monAnimalImp3 = new AnimalImp("1234","lind","till", "ajmi",espc3,14 , "fro", dossier);
 	            Contratanimal monAnimalImp4 = new AnimalImp("2345","linZd","tAill", "Aajmi",espc3,14 , "fZro", dossier);

  	        	ContratCabinet cabinet1 = new CabinetImp();
  	        	 cabinet1.ajouterPatient(monAnimalImp1);
  	        	 cabinet1.ajouterPatient(monAnimalImp2);
  	        	 cabinet1.ajouterPatient(monAnimalImp3);
  	        	 cabinet1.ajouterPatient(monAnimalImp4);

 	        	registre.rebind("cabinet1", cabinet1);
 	        	System.out.println("serveur pret !");
 	        	
	            
	        } catch (Exception e) {
	            System.err.println("Server exception: " + e);
	            e.printStackTrace();
	        }
	    }
}
