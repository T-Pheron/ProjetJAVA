package bataillenaval.controller;


import bataillenaval.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import bataillenaval.model.Flotte;
import bataillenaval.model.Plateau;

public class Sauvegarde {
    
    String nomPartie;
    int numeroJoueur;                
    int niveauIA;          
    boolean premierTour;  
    static Plateau plateauDeJeuCopy = new Plateau();
    Cuirasse flotteJoueur0Copy = new Cuirasse();
    List<Flotte> flotteJoueur1Copy = new ArrayList<Flotte>();
    
    
    public Sauvegarde(){

    }

    public int savePartie(int emplacementSauvegarde){
        
        
        this.nomPartie = Jeu.nomPartie;
        this.numeroJoueur = Jeu.numeroJoueur;
        this.niveauIA = Jeu.niveauIA;
        this.premierTour = Jeu.premierTour; 
        Sauvegarde.plateauDeJeuCopy = Jeu.plateauDeJeu;
        this.flotteJoueur0Copy = (Cuirasse) Jeu.flotteJoueur0.get(0);
        this.flotteJoueur1Copy = Jeu.flotteJoueur1;

        System.out.println(flotteJoueur0Copy.nom );
        
        try{
            try (FileOutputStream file = new FileOutputStream("save1"); ObjectOutputStream out = new ObjectOutputStream(file)) {

                out.writeObject(nomPartie);
                out.writeInt(numeroJoueur);
                out.writeInt(niveauIA);
                out.writeBoolean(premierTour);
                out.writeObject(plateauDeJeuCopy);
                //out.writeObject(flotteJoueur0Copy);
                //out.writeObject(flotteJoueur1Copy);

                return 1; //La partie a bien été sauvegardé
            }
            
        } catch (IOException e) {
            System.out.println("Erreur_save! "+"Le fichier n'a pas pu être créé.");
        }

        return 0;           //La partie n'a pas été sauvegardé 
    }

    public void chargementPartie( int emplacementSauvegarde) throws ClassNotFoundException, InterruptedException{

        
        try (FileInputStream fichier = new FileInputStream("save1"); ObjectInputStream in = new ObjectInputStream(fichier)) {
                
            nomPartie = (String) in.readObject();
            numeroJoueur = (int) in.readInt();
            niveauIA = (int) in.readInt();
            premierTour = (Boolean) in.readBoolean();
            plateauDeJeuCopy = (Plateau) in.readObject();
            
            
            Jeu jeu2 = new Jeu(nomPartie, numeroJoueur, niveauIA, premierTour, plateauDeJeuCopy);
            
            jeu2.lancementJeu();
            
        } catch (IOException e) {
            System.out.println("Erreur_chargementPartie! "+"Le fichier n'a pas pu être ouvert.");
        }
    }


}
