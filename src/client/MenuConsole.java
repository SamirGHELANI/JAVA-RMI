package client;

import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Scanner;

import common.Contratanimal;

public class MenuConsole {
    
     private GestionnaireCabinet logique;
    private Scanner scanner;

     public MenuConsole(GestionnaireCabinet logique) {
        this.logique = logique;
        this.scanner = new Scanner(System.in);
    }
    private void afficherMenu() {
    	System.out.println("\n========================================");
    	System.out.println("       MENU CABINET VÉTÉRINAIRE         ");
    	System.out.println("========================================");
    	System.out.println(" 1. Lister les patients actuels");
    	System.out.println(" 2. Rechercher un patient par son nom");
    	System.out.println(" 3. Enregistrer un nouveau patient");
    	System.out.println(" 4. Ajouter une observation au dossier");
    	System.out.println(" 5. Récupérer les observations d'un patient");
    	System.out.println(" 6. Modifier l'état de santé");
    	System.out.println(" 7. S'abonner aux alertes d'un patient");
    	System.out.println(" 8. Se désabonner d'un dossier");
    	System.out.println(" 9. Affichage Dossier Patient");
    	System.out.println(" 0. Quitter");
    	System.out.println("========================================");
    	System.out.print("Votre choix : ");
    }
    private int lireEntier(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("-> Erreur : Saisie invalide. Veuillez entrer un nombre entier.");
            }
        }
    }
    private void gererAffichagePatients() throws Exception {
         List<Contratanimal> patients = logique.getTousLesPatients();
        
        System.out.println("\n--- LISTE DES PATIENTS (" + patients.size() + ") ---");
        if(patients.isEmpty()) {
        	System.out.println("Aucun patient enregistré dans le cabinet.");
        }else{
        for (Contratanimal p : patients) {
            System.out.println("- " + p.getNom() + " (Maître: " + p.getMaitre() + ")"+"ID :"+p.getId());
        }}
        
    }
    private void gererAffichagedossierPatients() {
        System.out.print("Entrez l'ID à 4 chiffres du patient (ex: 0001) : ");
        String id = scanner.nextLine().trim();
        
        try {
          
            String dossierFormate = logique.getDossierPatient(id);
            
  
            
            if (dossierFormate != null) {
                System.out.println("\n========================================");
                System.out.println("         DOSSIER MÉDICAL DU PATIENT     ");
                System.out.println("========================================");
                
        
                System.out.println(dossierFormate);
                System.out.println("========================================");
            } else {
                System.out.println("\n[Erreur] Aucun patient trouvé avec cet ID : " + id);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du dossier : " + e.getMessage());
        }
    }
    
    
    private void gererModificationEtatSante() {
        System.out.print("Entrez l'ID à 4 chiffres du patient : ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Entrez le nouvel état de santé   : ");
        String nouvelEtat = scanner.nextLine().trim();
        
        try {
            logique.modifierEtatSantePatient(id, nouvelEtat);
            System.out.println("\n[Succès] L'état de santé du patient a été mis à jour avec succès !");
        } catch (Exception e) {
            System.err.println("\n[Erreur] Impossible de modifier l'état : " + e.getMessage());
        }
        
         
    }
    
    private void attendreToucheEntree() {
        System.out.println("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    private void gererAjoutPatient() throws Exception {
        System.out.println("\n--- NOUVEAU PATIENT ---");
        System.out.print("Nom de l'animal : ");
        String nom = scanner.nextLine();
        if (nom.isEmpty()) {
            System.out.println("\n[Annulé] Retour au menu principal.");
            return; 
        }
        System.out.print("Nom du maître : ");
        String maitre = scanner.nextLine();
        System.out.print("Race : ");
        String race = scanner.nextLine();
        System.out.print("Espèce (ex: Chat, Chien) : ");
        String espece = scanner.nextLine();
        int dureeVie = lireEntier("Durée de vie (en années) : ");
        int age = lireEntier("Âge actuel de l'animal : ");
        
        System.out.print("Description physique : ");
        String description = scanner.nextLine();
        
         logique.ajouterNouveauPatient(nom, maitre, race, espece, dureeVie, age ,description);
        System.out.println("-> Le patient " + nom + " a été ajouté avec succès !");
    }
    private void gererRecherchePatient() throws Exception {
        System.out.print("Entrez le id du patient à rechercher : ");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            System.out.println("\n[Annulé] Retour au menu principal.");
            return;  
        }
        
        List<Contratanimal> patients = logique.chercherPatient(id);
        
        if (patients == null || patients.isEmpty()) {
            System.out.println("-> Aucun patient trouvé avec le id : " + id);
        } else if (patients.size() == 1) {
            // Un seul patient trouvé, on affiche ses détails directement
            Contratanimal p = patients.get(0);
            System.out.println("-> Patient trouvé ! Nom : " + p.getNom() + " | Maître : " + p.getMaitre() + " | ID : " + p.getId() + " | Âge : " + p.getAge() + " ans");
        } else {
            // Plusieurs homonymes ! On liste tout avec les ID
            System.out.println("-> Attention : " + patients.size() + " patients portent le  '" + id + "' :");
            for (Contratanimal p : patients) {
                System.out.println("   - Nom : " + p.getNom() + " | Maître : " + p.getMaitre() + " | ID : " + p.getId() + " | Âge : " + p.getAge() + " ans");
            }
        }
    }
    private void gererAjoutObservation() throws Exception {
        System.out.print("\nId du patient à mettre à jour : ");
        String nom = scanner.nextLine();
        if (nom.isEmpty()) {
            System.out.println("\n[Annulé] Retour au menu principal.");
            return; 
        }
        System.out.print("Nouvelle observation : ");
        String observation = scanner.nextLine();
        
        boolean succes = logique.ajouterObservationPatient(nom, observation);
        if (succes) {
            System.out.println("-> L'observation a bien été ajoutée au dossier !");
        } else {
            System.out.println("-> Erreur : Impossible de mettre à jour, patient introuvable.");
        }
    }
    
    private void gererAbonnement(boolean abonner) throws Exception {
        System.out.print("\nNom du patient concerné : ");
        String nom = scanner.nextLine();
        if (nom.isEmpty()) {
            System.out.println("\n[Annulé] Retour au menu principal.");
            return; }
        if (abonner) {
            boolean succes = logique.sabonnerAlertes(nom);
            if (succes) System.out.println("-> Vous êtes maintenant abonné aux alertes de " + nom + ".");
            else System.out.println("-> Erreur : Patient introuvable.");
        } else {
            boolean succes = logique.seDesabonnerAlertes(nom);
            if (succes) System.out.println("-> Vous êtes désabonné des alertes de " + nom + ".");
            else System.out.println("-> Erreur : Patient introuvable.");
        }
    
    }
    public void demarrer() {
        boolean continuer = true;
        System.out.println("===========================================");
        System.out.println("  BIENVENUE DANS LA GESTION DU CABINET");
        System.out.println("===========================================");

        while (continuer) {
            afficherMenu();
            String choixStr = scanner.nextLine();
            int choix;

            try {
                
                choix = Integer.parseInt(choixStr);
            } catch (NumberFormatException e) {
                System.out.println("-> Erreur : Veuillez taper un chiffre valide.");
                continue;
            }

       
            try {
            	switch (choix) {
                case 1: 
                    gererAffichagePatients(); 
                    break;
                case 2: 
                    gererRecherchePatient(); 
                    break;
                case 3: 
                    gererAjoutPatient(); 
                    break;
                case 4: 
                    gererAjoutObservation(); 
                    break;
                case 5: 
                    gererAffichagedossierPatients(); 
                    break;
                case 6: 
                    gererModificationEtatSante(); 
                    break;
                case 7: 
                    gererAbonnement(true); 
                    break;
                case 8: 
                    gererAbonnement(true); 
                    break;
                case 9 :
                	gererAffichagedossierPatients();
                case 0 : 
                    continuer = false; 
                    System.out.println("\nAu revoir !"); 
                    continue; 
                default: 
                    System.out.println("\n[Erreur] Option invalide, réessaye.");
            }
                attendreToucheEntree();
            } catch (Exception e) {
                System.out.println("-> Erreur de communication avec le serveur : " + e.getMessage());
            }
        }
    }
}