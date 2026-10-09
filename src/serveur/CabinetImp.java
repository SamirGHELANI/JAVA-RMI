package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

 
import common.ContratCabinet;
import common.ContratDossierSuivi;
import common.ContratObservateur;
import common.Contratanimal;
import common.Espece;


 
public class CabinetImp extends UnicastRemoteObject implements ContratCabinet {
	 List<Contratanimal> patients;
	 List<ContratObservateur> observateurs = new ArrayList<>();
	 private int compteurId = 1 ;
 	protected CabinetImp() throws RemoteException {
		super();
		this.patients = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Contratanimal> getPatients() throws RemoteException {
		// TODO Auto-generated method stub
		return patients ;
	}

	@Override
	public List<Contratanimal> getPatientParNom(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		List<Contratanimal> resultats = new ArrayList<>();
		for (Contratanimal animal : patients) {
			if(animal.getNom().equalsIgnoreCase(nom)) {
				resultats.add(animal);
			};
		}
		return resultats;
	}
	@Override
	public Contratanimal getPatientParId(String id) throws RemoteException {
		// TODO Auto-generated method stub
		for (Contratanimal animal : patients) {
	        if (animal.getId().equals(id)) {
	            return animal;
	        }
	    }
	    return null;
	}
	
	
	@Override
	public void ajouterPatient(Contratanimal animal) throws RemoteException {
		// TODO Auto-generated method stub
		patients.add(animal);
		
	}

	@Override
	public void creerEtAjouterPatient(String nom, String maitre, String race, Espece espece , String etatsante, int age , String descriptionPhysique) throws RemoteException {
		// TODO Auto-generated method stub
		String idFormate = String.format("%04d", compteurId++);
		Contratanimal newPatient = new AnimalImp(idFormate , nom , maitre , race , espece ,age ,descriptionPhysique);
		ContratDossierSuivi newDossierSuivi = new DossierSuiviImp(etatsante);
		newPatient.setDossierSuivi(newDossierSuivi);
		this.patients.add(newPatient);
		int total = this.patients.size();
		if (total == 4 || total == 500 || total == 1000) {
		    String alerte = "ALERTE : Le cabinet vient de franchir le seuil des " + total + " patients !";
		    
 		    List<ContratObservateur> clientsDeconnectes = new ArrayList<>();
		    
		    for (ContratObservateur obs : observateurs) {
		        try {
		            obs.actualiser(alerte);  		   
		            } catch (RemoteException e) {
		          
		            clientsDeconnectes.add(obs);
		        }
		    }
		    observateurs.removeAll(clientsDeconnectes);
		
	}
	}

	@Override
	public void abonner(ContratObservateur obs) throws RemoteException {
		// TODO Auto-generated method stub
		observateurs.add(obs);
		
	}

	@Override
	public void desabonner(ContratObservateur obs) throws RemoteException {
		// TODO Auto-generated method stub
		observateurs.remove(obs);
	}

	

}
