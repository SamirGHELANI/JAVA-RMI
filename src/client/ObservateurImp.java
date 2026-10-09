package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 
import common.ContratObservateur;
 
public class ObservateurImp extends UnicastRemoteObject implements ContratObservateur {
 	protected ObservateurImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	@Override
	public void actualiser(String message) throws RemoteException {
	    System.out.println("notification recue :" + message);
	    
	   
	}
}
