package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContratObservateur extends Remote   {
	void actualiser (String message) throws RemoteException;

}
