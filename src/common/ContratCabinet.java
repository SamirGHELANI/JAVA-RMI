package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ContratCabinet extends Remote {
    List<Contratanimal> getPatients() throws RemoteException;
    List<ContratObservateur> observateurs = new ArrayList<>();
    List<Contratanimal> getPatientParNom(String nom) throws RemoteException;
    Contratanimal getPatientParId(String id) throws RemoteException;

    void ajouterPatient(Contratanimal animal) throws RemoteException; 
    void creerEtAjouterPatient(String nom, String maitre, String race, Espece espece , String etatsantéinitial , int age , String descriptionPhysique) throws RemoteException;
    public void abonner(ContratObservateur obs) throws RemoteException;
    public void desabonner(ContratObservateur obs) throws RemoteException;
}