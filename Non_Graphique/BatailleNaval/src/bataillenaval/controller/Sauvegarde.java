package bataillenaval.controller;


import java.io.*;
import java.util.*;

import bataillenaval.model.Flotte;

import bataillenaval.model.Plateau;

public class Sauvegarde {

    
    public Sauvegarde(){

    }

    public int savePartie(int emplacementSauvegarde, String nomPartie){

        String nomSauvegarde="A";
        if (emplacementSauvegarde==1) nomSauvegarde="saveFiles/save1";
        if (emplacementSauvegarde==2) nomSauvegarde="saveFiles/save2";
        if (emplacementSauvegarde==3) nomSauvegarde="saveFiles/save3";


        try{
            try (FileOutputStream fichier = new FileOutputStream(nomSauvegarde); ObjectOutputStream out = new ObjectOutputStream(fichier)) {

                out.writeObject(nomPartie);
                out.writeInt(Jeu.numeroJoueur);
                out.writeInt(Jeu.niveauIA);
                out.writeObject(Jeu.ia);
                out.writeBoolean(Jeu.premierTour);


                for(int x=0; x<15 ; x++){
                    for (int y=0; y<15 ;y++){
                        out.writeChar((char) Jeu.plateauDeJeu.get(x, y, 0, 0));
                        out.writeInt((int) Jeu.plateauDeJeu.get(x, y, 0, 1));
                        out.writeObject((String) Jeu.plateauDeJeu.get(x, y, 1, 0));
                        out.writeObject((String) Jeu.plateauDeJeu.get(x, y, 1, 1));

                        out.writeChar((char) Jeu.plateauDeJeu.get(x, y, 2, 0));
                        out.writeInt((int) Jeu.plateauDeJeu.get(x, y, 2, 1));
                        out.writeObject((String) Jeu.plateauDeJeu.get(x, y, 3, 0));
                        out.writeObject((String) Jeu.plateauDeJeu.get(x, y, 3, 1));
                    }
                }
                
                
                for (int i=0; i<10; i++){
                    out.writeBoolean(Jeu.flotteJoueur0.get(i).etat);
                    out.writeObject(Jeu.flotteJoueur0.get(i).nom);
                    out.writeInt(Jeu.flotteJoueur0.get(i).taille);

                    for (int j=0; j<Jeu.flotteJoueur0.get(i).taille; j++){
                        out.writeInt(Jeu.flotteJoueur0.get(i).coordonnees[j][0]);
                        out.writeInt(Jeu.flotteJoueur0.get(i).coordonnees[j][1]);
                        out.writeInt(Jeu.flotteJoueur0.get(i).coordonnees[j][2]);
                    }

                    out.writeInt(Jeu.flotteJoueur0.get(i).direction);
                    out.writeInt(Jeu.flotteJoueur0.get(i).puissance);
                    out.writeChar(Jeu.flotteJoueur0.get(i).lRef);
                    out.writeInt(Jeu.flotteJoueur0.get(i).nRef);
                    out.writeBoolean(Jeu.flotteJoueur0.get(i).premierTire);

                    out.writeBoolean(Jeu.flotteJoueur1.get(i).etat);
                    out.writeObject(Jeu.flotteJoueur1.get(i).nom);
                    out.writeInt(Jeu.flotteJoueur1.get(i).taille);

                    for (int j=0; j<Jeu.flotteJoueur1.get(i).taille; j++){
                        out.writeInt(Jeu.flotteJoueur1.get(i).coordonnees[j][0]);
                        out.writeInt(Jeu.flotteJoueur1.get(i).coordonnees[j][1]);
                        out.writeInt(Jeu.flotteJoueur1.get(i).coordonnees[j][2]);
                    }

                    out.writeInt(Jeu.flotteJoueur1.get(i).direction);
                    out.writeInt(Jeu.flotteJoueur1.get(i).puissance);
                    out.writeChar(Jeu.flotteJoueur1.get(i).lRef);
                    out.writeInt(Jeu.flotteJoueur1.get(i).nRef);
                    out.writeBoolean(Jeu.flotteJoueur1.get(i).premierTire);
                }
                

                return 4; //La partie a bien été sauvegardé
            }
            
        } catch (IOException e) {
            System.out.println("Erreur_save! "+"Le fichier n'a pas pu être créé.");
        }

        return 2;           //La partie n'a pas été sauvegardé 
    }

    public void chargementPartie( int emplacementSauvegarde) throws ClassNotFoundException, InterruptedException{

        /*Sélection de la sauvegarde*************************/
        String nomSauvegarde="A";
        if (emplacementSauvegarde==1) nomSauvegarde="saveFiles/save1";
        if (emplacementSauvegarde==2) nomSauvegarde="saveFiles/save2";
        if (emplacementSauvegarde==3) nomSauvegarde="saveFiles/save3";
        
        int numeroJoueur;                
        int niveauIA;
        IA ia;          
        boolean premierTour;

        Plateau plateauDeJeuCopy = new Plateau();
        List<Flotte> flotteJoueur0Copy = ArrayList();
        List<Flotte> flotteJoueur1Copy = ArrayList();   

        flotteJoueur0Copy = Jeu.createFlotte();
        flotteJoueur1Copy = Jeu.createFlotte();
        
        try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {
            
            in.readObject();
            numeroJoueur = (int) in.readInt();
            niveauIA = (int) in.readInt();
            ia = (IA) in.readObject();
            premierTour = (Boolean) in.readBoolean();
            
            for(int x=0; x<15 ; x++){
                for (int y=0; y<15 ;y++){
                    plateauDeJeuCopy.modification(x, y, 0, 0, in.readChar());
                    plateauDeJeuCopy.modification(x, y, 0, 1, in.readInt());
                    plateauDeJeuCopy.modification(x, y, 1, 0, (String) in.readObject());
                    plateauDeJeuCopy.modification(x, y, 1, 1, (String) in.readObject());

                    plateauDeJeuCopy.modification(x, y, 2, 0, in.readChar());
                    plateauDeJeuCopy.modification(x, y, 2, 1, in.readInt());
                    plateauDeJeuCopy.modification(x, y, 3, 0, (String) in.readObject());
                    plateauDeJeuCopy.modification(x, y, 3, 1, (String) in.readObject());
                }
            }
        
            for (int i=0; i<10; i++){
                flotteJoueur0Copy.get(i).etat = (boolean) in.readBoolean();
                flotteJoueur0Copy.get(i).nom = (String) in.readObject();
                flotteJoueur0Copy.get(i).taille = in.readInt();
                
                for (int j=0; j<flotteJoueur0Copy.get(i).taille; j++){
                    flotteJoueur0Copy.get(i).coordonnees[j][0] =  in.readInt();
                    System.out.print(flotteJoueur0Copy.get(i).coordonnees[j][0]+"_");
                    flotteJoueur0Copy.get(i).coordonnees[j][1] =  in.readInt();
                    System.out.print(flotteJoueur0Copy.get(i).coordonnees[j][1]+"_");
                    flotteJoueur0Copy.get(i).coordonnees[j][2] =  in.readInt();
                    System.out.println(flotteJoueur0Copy.get(i).coordonnees[j][2]);
                }
                    
                flotteJoueur0Copy.get(i).direction = in.readInt();
                flotteJoueur0Copy.get(i).puissance = in.readInt();
                flotteJoueur0Copy.get(i).lRef = in.readChar();
                flotteJoueur0Copy.get(i).nRef = in.readInt();
                flotteJoueur0Copy.get(i).premierTire = in.readBoolean();

                flotteJoueur1Copy.get(i).etat = in.readBoolean();
                flotteJoueur1Copy.get(i).nom = (String) in.readObject();
                flotteJoueur1Copy.get(i).taille = in.readInt();
                
                for (int j=0; j<flotteJoueur1Copy.get(i).taille; j++){
                    flotteJoueur1Copy.get(i).coordonnees[j][0] =  in.readInt();
                    flotteJoueur1Copy.get(i).coordonnees[j][1] =  in.readInt();
                    flotteJoueur1Copy.get(i).coordonnees[j][2] =  in.readInt();
                }

                flotteJoueur1Copy.get(i).direction = in.readInt();
                flotteJoueur1Copy.get(i).puissance = in.readInt();
                flotteJoueur1Copy.get(i).lRef = in.readChar();
                flotteJoueur1Copy.get(i).nRef = in.readInt();
                flotteJoueur1Copy.get(i).premierTire = in.readBoolean();
            }
            
            Jeu jeu2 = new Jeu(numeroJoueur, niveauIA, ia, premierTour, plateauDeJeuCopy, flotteJoueur0Copy, flotteJoueur1Copy);
            
            jeu2.lancementJeu();
            
        } catch (IOException e) {
            System.out.println("Erreur_chargementPartie! "+"Le fichier n'a pas pu être ouvert.");
        }
    }

    private List<Flotte> ArrayList() {
        return null;
    }


}
