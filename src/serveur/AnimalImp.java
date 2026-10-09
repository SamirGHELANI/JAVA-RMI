package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import common.ContratDossierSuivi;
import common.ContratObservateur;
import common.Contratanimal;
import common.Espece;

public class AnimalImp extends UnicastRemoteObject implements Contratanimal {
 		private String nom;
		private String maitre;
		private String race;
		private Espece espece;
		private String id;
	    private int age;
	    private String descriptionPhysique;
		private ContratDossierSuivi dossierSuivi; 
		private List<ContratObservateur> observateurs = new ArrayList<>(); // Les clients abonnés à cet animal
		
		
		public AnimalImp(String id , String nom , String maitre , String race , Espece espece , int age , String Descphysique, ContratDossierSuivi dossierSuivi) throws RemoteException{
			super();
			this.id = id;
			this.nom = nom ;
			this.maitre = maitre;
			this.race = race;
			this.espece = espece;
			this.age = age;
			this.descriptionPhysique = Descphysique;
			this.dossierSuivi = dossierSuivi;
		}
		public AnimalImp(String id, String nom , String maitre , String race , Espece espece , int age , String Descphysique) throws RemoteException{
			super();
			this.id = id;
			this.nom = nom ;
			this.maitre = maitre;
			this.race = race;
			this.espece = espece;
			this.age = age;
			this.descriptionPhysique = Descphysique;
 		}
		 
 
		@Override
		public String getNom() throws RemoteException {
			// TODO Auto-generated method stub
			return this.nom;
		}

		@Override
		public String getMaitre() throws RemoteException {
			// TODO Auto-generated method stub
			return this.maitre;
		}

		@Override
		public String getRace() throws RemoteException {
			// TODO Auto-generated method stub
			return this.race;
		}

		@Override
		public Espece getEspece() throws RemoteException {
			// TODO Auto-generated method stub
			return this.espece;
		}
		@Override
		public String getId() throws RemoteException {
			// TODO Auto-generated method stub
			return id;
		}
		
		@Override
		public int getAge() throws RemoteException {
			// TODO Auto-generated method stub
			return age;
		}
		@Override
		public String getDescriptionPhysique() throws RemoteException {
			// TODO Auto-generated method stub
			return descriptionPhysique;
		}

		public ContratDossierSuivi getDossierSuivi() throws RemoteException {
			// TODO Auto-generated method stub
			return this.dossierSuivi;
		}
		@Override
		public void setDossierSuivi(ContratDossierSuivi dossier) throws RemoteException {
			// TODO Auto-generated method stub
			this.dossierSuivi=dossier;
			}
		
		
		
		@Override
	    public void ajouterObservateur(ContratObservateur obs) throws RemoteException {
	        this.observateurs.add(obs);
	    }
		public synchronized void modifierEtatSante(String nouvelEtat,ContratObservateur emetteur) throws RemoteException {

			dossierSuivi.setEtatSante(nouvelEtat);

			List<ContratObservateur> clientsMorts = new ArrayList<>(); // La "poubelle"

			for (ContratObservateur observateur : observateurs) {

			if(observateur.equals(emetteur)) {

			continue;}

			try {

			observateur.actualiser("L'animal [" + this.nom + "] a un nouvel état : " + nouvelEtat);

			} catch (RemoteException e) {

			// Le client ne répond plus, on le met dans la liste des morts

			clientsMorts.add(observateur);

			}


			observateurs.removeAll(clientsMorts);

			}


			}

		@Override
		public synchronized void creerObservation(String observation , ContratObservateur emetteur) throws RemoteException {
 			dossierSuivi.ajouterObservation(observation);
 			 List<ContratObservateur> clientsMorts = new ArrayList<>(); // La "poubelle"

 		    for (ContratObservateur observateur : observateurs) {
 		    	if(observateur.equals(emetteur)) {
 	 		    	continue;}
 		    	try {
		        observateur.actualiser("[NOUVELLE OBSERVATION] " + this.nom + " -> " + observation);
 		    	}catch (RemoteException e) {
					// TODO: handle exception
 		    		clientsMorts.add(observateur);
				}
 	 		   observateurs.removeAll(clientsMorts);

		}
	 
}
		@Override
		public void retirerObservateur(ContratObservateur obs) throws RemoteException {
			// TODO Auto-generated method stub
			this.observateurs.remove(obs);
		}
		
}