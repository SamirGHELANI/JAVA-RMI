package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;import java.util.Observable;

import common.ContratDossierSuivi;
import common.ContratObservateur;

public class DossierSuiviImp extends UnicastRemoteObject implements ContratDossierSuivi {
	private String etatSante;
	private List<String> historique;
 	protected DossierSuiviImp(String etatSante) throws RemoteException {
		super();
		this.etatSante = etatSante;
		this.historique = new ArrayList<String>();
 		// TODO Auto-generated constructor stub
	}

	@Override
	public String getEtatSante() throws RemoteException {
		// TODO Auto-generated method stub
		return this.etatSante;
	}

	

	@Override
	public List<String> gethistorique() throws RemoteException {
		// TODO Auto-generated method stub
		return this.historique;
	}

	 

	@Override
	public void setEtatSante(String etatSante) throws RemoteException {
		// TODO Auto-generated method stub
		this.etatSante = etatSante;
 	}
	public void ajouterObservation(String obs) throws RemoteException {
		this.historique.add(obs); }

	 
	 
	 
}
