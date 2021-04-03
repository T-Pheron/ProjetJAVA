package bataillenavalgraphique.bataillenaval.controller;


import java.io.*;
import java.util.*;
import bataillenavalgraphique.controller.IA;
import bataillenavalgraphique.bataillenaval.model.Flotte;
import bataillenavalgraphique.bataillenaval.model.Plateau;

public class SauvegardeNGraphique {

    
    public SauvegardeNGraphique(){

    }

    public int savePartie(int emplacementSauvegarde, String nomPartie){

        String nomSauvegarde="A";           //On initialise par défaut le nom de la sauvegarde à A
        if (emplacementSauvegarde==1) nomSauvegarde="saveFiles/save1";          //Si le joueur a choisi l'emplacement 1
        if (emplacementSauvegarde==2) nomSauvegarde="saveFiles/save2";          //Si le joueur a choisi l'emplacement 2
        if (emplacementSauvegarde==3) nomSauvegarde="saveFiles/save3";          //Si le joueur a choisi l'emplacement 3


        
        try (FileOutputStream fichier = new FileOutputStream(nomSauvegarde); ObjectOutputStream out = new ObjectOutputStream(fichier)) {

            //On stock les informations nécéssaires pour recommencer à jouer plus tard
            out.writeObject(nomPartie);
            out.writeInt(JeuNGraphique.numeroJoueur);
            out.writeInt(JeuNGraphique.niveauIA);
            out.writeObject(JeuNGraphique.ia);
            out.writeBoolean(JeuNGraphique.premierTour);
            out.writeInt(JeuNGraphique.compteurTourHumain);
            out.writeInt(JeuNGraphique.compteurTourIA);


            //On stock dans le fichier le plateau
            for(int x=0; x<15 ; x++){
                for (int y=0; y<15 ;y++){
                    out.writeChar((char) JeuNGraphique.plateauDeJeu.get(x, y, 0, 0));
                    out.writeInt((int) JeuNGraphique.plateauDeJeu.get(x, y, 0, 1));
                    out.writeObject((String) JeuNGraphique.plateauDeJeu.get(x, y, 1, 0));
                    out.writeObject((String) JeuNGraphique.plateauDeJeu.get(x, y, 1, 1));

                    out.writeChar((char) JeuNGraphique.plateauDeJeu.get(x, y, 2, 0));
                    out.writeInt((int) JeuNGraphique.plateauDeJeu.get(x, y, 2, 1));
                    out.writeObject((String) JeuNGraphique.plateauDeJeu.get(x, y, 3, 0));
                    out.writeObject((String) JeuNGraphique.plateauDeJeu.get(x, y, 3, 1));
                }
            }
            
            //On stock dans le fichier la flotte du joueur
            for (int i=0; i<10; i++){
                out.writeBoolean(JeuNGraphique.flotteJoueur0.get(i).etat);
                out.writeObject(JeuNGraphique.flotteJoueur0.get(i).nom);
                out.writeInt(JeuNGraphique.flotteJoueur0.get(i).taille);

                for (int j=0; j<JeuNGraphique.flotteJoueur0.get(i).taille; j++){
                    out.writeInt(JeuNGraphique.flotteJoueur0.get(i).coordonnees[j][0]);
                    out.writeInt(JeuNGraphique.flotteJoueur0.get(i).coordonnees[j][1]);
                    out.writeInt(JeuNGraphique.flotteJoueur0.get(i).coordonnees[j][2]);
                }

                out.writeInt(JeuNGraphique.flotteJoueur0.get(i).direction);
                out.writeInt(JeuNGraphique.flotteJoueur0.get(i).puissance);
                out.writeChar(JeuNGraphique.flotteJoueur0.get(i).lRef);
                out.writeInt(JeuNGraphique.flotteJoueur0.get(i).nRef);
                out.writeBoolean(JeuNGraphique.flotteJoueur0.get(i).premierTire);

                //On stock dans le fichier la flotte de l'IA
                out.writeBoolean(JeuNGraphique.flotteJoueur1.get(i).etat);
                out.writeObject(JeuNGraphique.flotteJoueur1.get(i).nom);
                out.writeInt(JeuNGraphique.flotteJoueur1.get(i).taille);

                for (int j=0; j<JeuNGraphique.flotteJoueur1.get(i).taille; j++){
                    out.writeInt(JeuNGraphique.flotteJoueur1.get(i).coordonnees[j][0]);
                    out.writeInt(JeuNGraphique.flotteJoueur1.get(i).coordonnees[j][1]);
                    out.writeInt(JeuNGraphique.flotteJoueur1.get(i).coordonnees[j][2]);
                }

                out.writeInt(JeuNGraphique.flotteJoueur1.get(i).direction);
                out.writeInt(JeuNGraphique.flotteJoueur1.get(i).puissance);
                out.writeChar(JeuNGraphique.flotteJoueur1.get(i).lRef);
                out.writeInt(JeuNGraphique.flotteJoueur1.get(i).nRef);
                out.writeBoolean(JeuNGraphique.flotteJoueur1.get(i).premierTire);
            }
            

            return 4; //La partie a bien été sauvegardé
        
        } catch (IOException e) {
            System.out.println("Erreur_save! "+"Le fichier n'a pas pu être créé.");
        }

        return 2;           //La partie n'a pas été sauvegardé 
    }

    public void chargementPartie( int emplacementSauvegarde) throws ClassNotFoundException, InterruptedException{

        /*Sélection de la sauvegarde*************************/
        String nomSauvegarde="A";           //On initialise par défaut le nom de la sauvegarde à A
        if (emplacementSauvegarde==1) nomSauvegarde="saveFiles/save1";          //Si le joueur a choisi l'emplacement 1
        if (emplacementSauvegarde==2) nomSauvegarde="saveFiles/save2";          //Si le joueur a choisi l'emplacement 2
        if (emplacementSauvegarde==3) nomSauvegarde="saveFiles/save3";          //Si le joueur a choisi l'emplacement 3
        
        //On initialise des variables pour stocker toutes les données du jeu
        int numeroJoueur;                  
        int niveauIA;           
        IA ia;          
        boolean premierTour;
        int compteurTourHumain;
        int compteurTourIA;

        Plateau plateauDeJeuCopy = new Plateau();
        List<Flotte> flotteJoueur0Copy;
        List<Flotte> flotteJoueur1Copy;   

        flotteJoueur0Copy = JeuNGraphique.createFlotte();         
        flotteJoueur1Copy = JeuNGraphique.createFlotte();         
        
        /*Récupération des données dans le fichier************************************/
        try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {
            
            in.readObject();            //On lit le nom de la partie

            //On lit et stock toutes les informations comprises dans le fichier
            numeroJoueur = (int) in.readInt();
            niveauIA = (int) in.readInt();
            ia = (IA) in.readObject();
            premierTour = (Boolean) in.readBoolean();
            compteurTourHumain = in.readInt();
            compteurTourIA = in.readInt();


            //On stock dans le fichier le plateau
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
        
            //On stock dans le fichier la flotte du joueur
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

                //On stock dans le fichier la flotte de l'IA
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
            
            //JeuGraphique jeu2 = new JeuNGraphique(numeroJoueur, niveauIA, ia, premierTour, plateauDeJeuCopy, flotteJoueur0Copy, flotteJoueur1Copy, compteurTourHumain, compteurTourIA);            //On crée un objet de type Jeu a qui on affecte tout ce qu'on lui a donné
            
            //jeu2.lancementJeuGraphique();            //On lance la partie
            
        } catch (IOException e) {
            System.out.println("Erreur_chargementPartie! "+"Le fichier n'a pas pu être ouvert.");           //On affiche un message d'erreur
        }
    }
}
