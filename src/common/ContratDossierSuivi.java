package common;

 import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ContratDossierSuivi extends Remote {
	String getEtatSante() throws RemoteException;
	void setEtatSante(String etatSante) throws RemoteException;
	public void ajouterObservation( String obs) throws RemoteException;
	List<String> gethistorique() throws RemoteException;
  }
