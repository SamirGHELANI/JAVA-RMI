package client;

import java.rmi.*;
import java.util.*;
import common.ContratCabinet;
import common.Contratanimal;
import common.Espece;
import common.ContratObservateur;

public class GestionnaireCabinet {

	private ContratCabinet stub;
	private ObservateurImp monObservateur;
	
	public GestionnaireCabinet(ContratCabinet stub, ObservateurImp obs) throws RemoteException{
		this.stub = stub;
		this.monObservateur = obs;
	}
	public GestionnaireCabinet(ContratCabinet stub) throws RemoteException {
        this.stub = stub;
        this.monObservateur = new ObservateurImp();
    }
	public ObservateurImp getMonObservateur() {
        return monObservateur;
    }
	public List<Contratanimal> getTousLesPatients() throws RemoteException {
		return stub.getPatients();
	}
	public String getDossierPatient(String idPatient) throws RemoteException {
	    Contratanimal patient = stub.getPatientParId(idPatient);
	    if (patient != null) {
	        
	        List<String> obsList = patient.getDossierSuivi().gethistorique();
	        StringBuilder sb = new StringBuilder("Dossier du patient " + patient.getNom() + " :\n");
	        for (String obs : obsList) {
	            sb.append("- ").append(obs).append("\n");
	        }
	        return sb.toString();
	    }
	    return null;
	}
	// Méthode de la logique pour modifier l'état de santé d'un patient via son ID
	public void modifierEtatSantePatient(String idPatient, String nouvelEtat) throws RemoteException {
	    Contratanimal patient = stub.getPatientParId(idPatient);
	    
	    if (patient != null) {
	        patient.modifierEtatSante(nouvelEtat, monObservateur);
	    } else {
	        throw new RemoteException("Aucun patient trouvé avec l'ID : " + idPatient);
	    }
	}
	public List<Contratanimal> chercherPatient(String nomCherche) throws RemoteException {
		return stub.getPatientParNom(nomCherche);
		
	}
	public Contratanimal chercherPatientID(String IdCherche) throws RemoteException {
		return stub.getPatientParId(IdCherche);
		
	}
	public void ajouterNouveauPatient(String nomAnimal, String nomMaitre, String race, String nomEspece, int dureeVieEspece , int age , String descriptionPhysique) throws RemoteException {
        // On utilise les paramètres pour créer la bonne espèce au lieu de la coder en dur
        Espece espece = new Espece(nomEspece, dureeVieEspece);
        
        // On transmet toutes les informations au serveur via le stub
        stub.creerEtAjouterPatient(nomAnimal, nomMaitre, race, espece, "En bonne santé",age ,descriptionPhysique);
    }
	public boolean ajouterObservationPatient(String IdPatient, String nouvelleObservation) throws RemoteException {
        Contratanimal patient = stub.getPatientParId(IdPatient);
        
        if (patient != null) {
           
            patient.creerObservation(nouvelleObservation, monObservateur);
            return true;  
        }
        
        return false;  
    }
	public boolean sabonnerAlertes(String IdPatient) throws RemoteException {
        Contratanimal animal = stub.getPatientParId(IdPatient);
        if (animal != null) {
            animal.ajouterObservateur(monObservateur);
            return(true);
        }
        return false;
    }
	public boolean seDesabonnerAlertes(String IdPatient) throws RemoteException {
        Contratanimal animal = stub.getPatientParId(IdPatient);
        if (animal != null) {
            animal.retirerObservateur(monObservateur);
            return(false);
        }
        return false;
    }
	
	
}
