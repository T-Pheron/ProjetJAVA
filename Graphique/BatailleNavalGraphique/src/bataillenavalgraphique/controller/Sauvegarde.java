package bataillenavalgraphique.controller;



import bataillenavalgraphique.view.AffichageSauvegardeGraphique;
import bataillenavalgraphique.bataillenaval.controller.JeuNGraphique;
import java.io.*;
import java.util.*;
import bataillenavalgraphique.bataillenaval.model.Flotte;
import bataillenavalgraphique.bataillenaval.model.Plateau;
import javafx.stage.Stage;

public class Sauvegarde {

    
    public Sauvegarde(){

    }
    
    public void lancementChargement(){
        
    }
    

    public int savePartie(String nomSauvegarde, String nomPartie, boolean sauvegardeEtQuitter){

 
        JeuGraphique.partieSauvegarde=true;
        AffichageSauvegardeGraphique affichageSauvegarde = new AffichageSauvegardeGraphique();
        affichageSauvegarde.sauvegardeEnCours();
        
        try (FileOutputStream fichier = new FileOutputStream(nomSauvegarde); ObjectOutputStream out = new ObjectOutputStream(fichier)) {

            //On stock les informations nécéssaires pour recommencer à jouer plus tard
            out.writeObject(nomPartie);
            out.writeInt(JeuGraphique.numeroJoueur);
            out.writeInt(JeuGraphique.niveauIA);
            
            //On stock les informations de l'IA
            out.writeObject(JeuGraphique.ia.getInfoIA());           
            
            out.writeBoolean(JeuGraphique.premierTour);
            out.writeInt(JeuGraphique.compteurTourHumain);
            out.writeInt(JeuGraphique.compteurTourIA);


            //On stock dans le fichier le plateau
            for(int x=0; x<15 ; x++){
                for (int y=0; y<15 ;y++){
                    out.writeChar((char) JeuGraphique.plateauDeJeu.get(x, y, 0, 0));
                    out.writeInt((int) JeuGraphique.plateauDeJeu.get(x, y, 0, 1));
                    out.writeObject((String) JeuGraphique.plateauDeJeu.get(x, y, 1, 0));
                    out.writeObject((String) JeuGraphique.plateauDeJeu.get(x, y, 1, 1));

                    out.writeChar((char) JeuGraphique.plateauDeJeu.get(x, y, 2, 0));
                    out.writeInt((int) JeuGraphique.plateauDeJeu.get(x, y, 2, 1));
                    out.writeObject((String) JeuGraphique.plateauDeJeu.get(x, y, 3, 0));
                    out.writeObject((String) JeuGraphique.plateauDeJeu.get(x, y, 3, 1));
                }
            }
            
            //On stock dans le fichier la flotte du joueur
            for (int i=0; i<10; i++){
                out.writeBoolean(JeuGraphique.flotteJoueur0.get(i).etat);
                out.writeObject(JeuGraphique.flotteJoueur0.get(i).nom);
                out.writeInt(JeuGraphique.flotteJoueur0.get(i).taille);

                for (int j=0; j<JeuGraphique.flotteJoueur0.get(i).taille; j++){
                    out.writeInt(JeuGraphique.flotteJoueur0.get(i).coordonnees[j][0]);
                    out.writeInt(JeuGraphique.flotteJoueur0.get(i).coordonnees[j][1]);
                    out.writeInt(JeuGraphique.flotteJoueur0.get(i).coordonnees[j][2]);
                }

                out.writeInt(JeuGraphique.flotteJoueur0.get(i).direction);
                out.writeInt(JeuGraphique.flotteJoueur0.get(i).puissance);
                out.writeChar(JeuGraphique.flotteJoueur0.get(i).lRef);
                out.writeInt(JeuGraphique.flotteJoueur0.get(i).nRef);
                out.writeBoolean(JeuGraphique.flotteJoueur0.get(i).premierTire);

                //On stock dans le fichier la flotte de l'IA
                out.writeBoolean(JeuGraphique.flotteJoueur1.get(i).etat);
                out.writeObject(JeuGraphique.flotteJoueur1.get(i).nom);
                out.writeInt(JeuGraphique.flotteJoueur1.get(i).taille);

                for (int j=0; j<JeuGraphique.flotteJoueur1.get(i).taille; j++){
                    out.writeInt(JeuGraphique.flotteJoueur1.get(i).coordonnees[j][0]);
                    out.writeInt(JeuGraphique.flotteJoueur1.get(i).coordonnees[j][1]);
                    out.writeInt(JeuGraphique.flotteJoueur1.get(i).coordonnees[j][2]);
                }

                out.writeInt(JeuGraphique.flotteJoueur1.get(i).direction);
                out.writeInt(JeuGraphique.flotteJoueur1.get(i).puissance);
                out.writeChar(JeuGraphique.flotteJoueur1.get(i).lRef);
                out.writeInt(JeuGraphique.flotteJoueur1.get(i).nRef);
                out.writeBoolean(JeuGraphique.flotteJoueur1.get(i).premierTire);
            }
            
            if (sauvegardeEtQuitter ==true){
                affichageSauvegarde.sauvegardeEffectuee();
            }
            else {
                affichageSauvegarde.sauvegardeEffectueeSauvegardeSimple();
            }
            return 4;
        
        } catch (IOException e) {
            System.out.println("Erreur_save! "+"Le fichier n'a pas pu être créé.");
        }

        return 2;           //La partie n'a pas été sauvegardé 
    }

    public int supprimerPartie(String nomSauvegarde, Stage stage){

        AffichageSauvegardeGraphique affichageSauvegarde = new AffichageSauvegardeGraphique();
        affichageSauvegarde.suppressionEffectue(stage);
        
        try (FileOutputStream fichier = new FileOutputStream(nomSauvegarde); ObjectOutputStream out = new ObjectOutputStream(fichier)) {

            //On stock les informations nécéssaires pour recommencer à jouer plus tard
            out.writeObject(null);
            
            return 4;
        
        } catch (IOException e) {
            System.out.println("Erreur_save! "+"Le fichier n'a pas pu être créé.");
        }

        return 2;           //La partie n'a pas été sauvegardé 
    }

    public void chargementPartie( String nomSauvegarde) throws ClassNotFoundException, InterruptedException{
        
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
            ia = new IA((Object[]) in.readObject());
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
            
            JeuGraphique jeu = new JeuGraphique(numeroJoueur, niveauIA, ia, premierTour, plateauDeJeuCopy, flotteJoueur0Copy, flotteJoueur1Copy, compteurTourHumain, compteurTourIA);            //On crée un objet de type Jeu a qui on affecte tout ce qu'on lui a donné
            
            jeu.lancementJeuGraphique();            //On lance la partie
            
        } catch (IOException e) {
            System.out.println("Erreur_chargementPartie! "+"Le fichier n'a pas pu être ouvert.");           //On affiche un message d'erreur
        }
    }
}
