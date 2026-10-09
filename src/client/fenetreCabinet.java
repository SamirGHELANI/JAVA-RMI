package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;
import common.Contratanimal;

public class fenetreCabinet extends JFrame {

    private GestionnaireCabinet logique;
    private JTable tablePatients;
    private DefaultTableModel tableModel;
    private JTextArea zoneNotifications;
    
    // Déclare tes boutons ici en attributs (pour que le contrôleur puisse y accéder après)
    public JButton btnActualiser, btnRechercher, btnAjouter, btnObservation, btnAbonner,btnAfficherDossier,  btnModifieretatsante, btnDesAbonner;

    public fenetreCabinet(GestionnaireCabinet logique) {
        this.logique = logique;

        // Configuration de la fenêtre principale
        setTitle("Gestion de Cabinet Vétérinaire (RMI + Swing)");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));
        
        JLabel titre = new JLabel(" ESPACE GESTION CABINET VÉTÉRINAIRE", JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titre, BorderLayout.NORTH);
        
        String[] colonnes = {"ID", "Nom", "Maître", "Espèce", "Race", "Âge", "État de santé"};
        
        tableModel = new DefaultTableModel(colonnes, 0);
        tablePatients = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePatients);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Patients Actuels"));
        add(scrollPane, BorderLayout.CENTER);
        
        zoneNotifications = new JTextArea();
        zoneNotifications.setEditable(false);
        zoneNotifications.setBackground(new Color(245, 245, 245));
        JScrollPane scrollNotes = new JScrollPane(zoneNotifications);
        scrollNotes.setBorder(BorderFactory.createTitledBorder("Journal des Alertes en Temps Réel"));
        scrollNotes.setPreferredSize(new Dimension(300, 0));
        add(scrollNotes, BorderLayout.EAST);
        
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        // Initialisation des boutons (sans "1.", "2." pour que ce soit plus propre avec le MVC)
        btnActualiser = new JButton("Actualiser");
        btnRechercher = new JButton("Rechercher");
        btnAjouter = new JButton("Nouveau Patient");
        btnObservation = new JButton("Ajouter Observation");
        btnAbonner = new JButton("S'abonner");
        btnDesAbonner = new JButton("Desabonner");
        btnModifieretatsante = new JButton("Modifier Etat de santé");
        btnAfficherDossier = new JButton("Afficher Dossier");
        
        panelBoutons.add(btnActualiser);
        panelBoutons.add(btnRechercher);
        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnObservation);
        panelBoutons.add(btnAfficherDossier);
        panelBoutons.add(btnModifieretatsante);
        panelBoutons.add(btnAbonner);
        panelBoutons.add(btnDesAbonner);
        
        add(panelBoutons, BorderLayout.SOUTH);
    }
    public void chargerPatients() {
    	try {
            tableModel.setRowCount(0); // Vide le tableau avant de recharger
            List<Contratanimal> patients = logique.getTousLesPatients();
            for (Contratanimal p : patients) {
                Object[] ligne = {
                    p.getId(),
                    p.getNom(),
                    p.getMaitre(),
                    p.getEspece().getNom(),
                    p.getRace(),
                    p.getAge() + " ans",
                    "En bonne santé"
                };
                tableModel.addRow(ligne);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Erreur de communication : " + e.getMessage(), "Erreur RMI", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void logMessage(String msg) {
        zoneNotifications.append(msg + "\n");
        zoneNotifications.setCaretPosition(zoneNotifications.getDocument().getLength());
    }
    
}