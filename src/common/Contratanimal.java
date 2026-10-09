package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

 

public interface Contratanimal extends Remote {
		String getNom() throws RemoteException;
	    String getMaitre() throws RemoteException;
	    String getRace() throws RemoteException;
	    Espece getEspece() throws RemoteException;   
	    String getId() throws java.rmi.RemoteException;
	    int getAge() throws java.rmi.RemoteException;
	    String getDescriptionPhysique() throws java.rmi.RemoteException;
	    ContratDossierSuivi getDossierSuivi() throws RemoteException;
	     void setDossierSuivi(ContratDossierSuivi dossier) throws RemoteException;
	    public void ajouterObservateur(ContratObservateur obs) throws RemoteException;
	    public void retirerObservateur(ContratObservateur obs) throws RemoteException;
	    void modifierEtatSante(String nouvelEtat , ContratObservateur emetteur) throws RemoteException;
	    void creerObservation(String observation , ContratObservateur emetteur ) throws RemoteException;
}
