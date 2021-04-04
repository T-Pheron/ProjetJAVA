package bataillenavalgraphique.controller;

import bataillenavalgraphique.bataillenaval.model.Chrono;
import bataillenavalgraphique.view.AffichageJeuGraphique;
import bataillenavalgraphique.bataillenaval.view.Affichage;
import bataillenavalgraphique.bataillenaval.controller.JeuNGraphique;
import bataillenavalgraphique.bataillenaval.model.Flotte;
import bataillenavalgraphique.bataillenaval.model.Plateau;

import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
   

/**
 * Classe JeuGraphique.
 * Toutes les informations et méthodes principales du jeu.
 * @author Théric PHERON and Joé LHUERRE
 */
public class JeuGraphique{

    //**************************************************************************
    public static Plateau plateauDeJeu = new Plateau();             //Variable qui stocke l'ensemble du plateau de jeu 
    public static List <Flotte> flotteJoueur0 ;               //List qui stocke l'ensemble de la flotte du joueur 0
    public static List <Flotte> flotteJoueur1 ;               //List qui stocke l'ensemble de la flotte du joueur 1
    
    public static int numeroJoueur;                 //Variable qui permet de connaître à quelle joueur c'est la tour
    public static int niveauIA=0;           //Variable qui permet de stoker le niveau de l'IA
    public static IA ia;           //On initialise une variable qui permet de faire fonctionner l'IA
    public static boolean premierTour = true;        //Variable utilisé pour savoir si c'est le premier tir
    public static int compteurTourIA;               //Variable qui compte le nombre de tour de l'IA
    public static int compteurTourHumain;           //variable qui compte le nombre de tour du joueur humain
    public static Chrono chronometre;
    
    public static Stage fenetreJeu = new Stage();           //On déclare une nouvelle fenètre 
    public static boolean selectionJoueur=false;            //Variable qui permet de savoir si le joueur à bien sélectionner un navire
    public static boolean partieSauvegarde=false;           //Variable qui permet de savoir si une partie est sauvegardé ou pas
    public static boolean victoire=false;
    //**************************************************************************

    
    //**************************************************************************
    /**
     * Constructeur de la classe JeuGraphique qui permt de lancer une partie sauvegarder.
     * On donne en paramètre toutes les informations sauvegardées dans un fichier au constructeur pour relancer une partie sauvegardé.
     * @param numeroJoueur Le numéro du joueur
     * @param niveauIA Le niveau de l'IA
     * @param ia L'IA
     * @param premierTour La variable qui dit si c'est le premier tour
     * @param plateauDeJeu Le plateau de jeu
     * @param flotteJoueur0 La flotte du joueur humain 
     * @param flotteJoueur1 La flotte de l'IA
     * @param compteurTourIA Le compteur de tour de l'IA
     * @param compteurTourHumain Le compteur de tour du joueur humain
     * @param chronometre Le chronomètre
     */
    public JeuGraphique(int numeroJoueur, int niveauIA,IA ia, boolean premierTour, Plateau plateauDeJeu, List <Flotte> flotteJoueur0, List <Flotte> flotteJoueur1, int compteurTourIA, int compteurTourHumain, Chrono chronometre){
        
        JeuGraphique.numeroJoueur = numeroJoueur;               //On affecte les informations sauvegardé à la partie de jeu
        JeuGraphique.niveauIA= niveauIA;
        JeuGraphique.ia = ia;
        JeuGraphique.premierTour= premierTour;
        JeuGraphique.compteurTourHumain =compteurTourHumain;
        JeuGraphique.compteurTourIA = compteurTourIA;
        JeuGraphique.chronometre = chronometre;
        JeuGraphique.plateauDeJeu = plateauDeJeu;
        JeuGraphique.flotteJoueur0 = flotteJoueur0;
        JeuGraphique.flotteJoueur1 = flotteJoueur1;
        partieSauvegarde=true;
    }
    

    //**************************************************************************
    /**
     * Constructeur de la classe JeuGraphique pour la lancement de nouvelle partie.
     * Ce constructeur est utilisé pour la création d'une nouvelle partie de jeu.
     * @param niveauIA Le niveau de l'IA, 1 facile, 2 moyen, 3 difficile
     */
    public JeuGraphique(int niveauIA){
        JeuGraphique.niveauIA= niveauIA;                //on créé une nouvelle partie et on met tous les éléments à leurs valeur par défaut
        JeuGraphique.ia = new IA(niveauIA);
        JeuGraphique.plateauDeJeu = new Plateau();
        JeuGraphique.flotteJoueur0 =null;
        JeuGraphique.flotteJoueur1 =null;
    
        JeuGraphique.numeroJoueur=0;
        JeuGraphique.premierTour = true;
        JeuGraphique.compteurTourIA =0;
        JeuGraphique.compteurTourHumain = 0;
        JeuGraphique.chronometre = new Chrono();
    
        JeuGraphique.fenetreJeu = new Stage();
        JeuGraphique.selectionJoueur=false;
        JeuGraphique.partieSauvegarde=false;
    }

     
    //**************************************************************************
    /**
     * Méthode qui permet le lancement d'une partie.
     * La méthode créé de nouvelle flotte pour chaque joueur ainsi que le plateau de jeu.
     * Cette méthode est celle qui possède la boucle de jeu qui permet de faire les différents tour de chaque joueur.
     * @throws InterruptedException 
     * @throws ClassNotFoundException
     */
    public void lancementJeuGraphique() throws InterruptedException, ClassNotFoundException{
        
        /*Initialisation des variables*****************************************/

        if (premierTour==true){
            //Initialisation des flottes************************
            flotteJoueur0 = JeuNGraphique.createFlotte();            //On créé une flotte pour le joueur 0
            flotteJoueur1 = JeuNGraphique.createFlotte();            //On créé une flotte pour le joueur 1  
            
            placementFlotteGraphique(flotteJoueur0, 0);            //On place aléatoirement la flotte du joueur 0 sur sa grille de navire
            placementFlotteGraphique(flotteJoueur1, 1);            //On place aléatoirement la flotte du joueur 1 sur sa grille de navire
        
            numeroJoueur=0;         //On met le joueur qui joue par défaut à 0
            premierTour=false;
            
            chronometre.start();
        }
        else chronometre.resume();

        JeuGraphique.fenetreJeu.setTitle("Bataille Navale - Jeu");          //On donne un titre à la fenètre
        JeuGraphique.fenetreJeu.setWidth(1400);         //On lui donne une dimension
        JeuGraphique.fenetreJeu.setHeight(900);
        JeuGraphique.fenetreJeu.setResizable(false);            //On met qu'on peut pas rédimensionner la fenêtre de jeu
        JeuGraphique.fenetreJeu.setOnCloseRequest(event -> {            //Si l'utilisateur décide de fermé la fenêtre de jeu
            if (partieSauvegarde==false){           //Si la partie ne sort pas d'une sauvegarde
                event.consume();            //
                Alert boiteDeFermetureUrgente = new Alert(AlertType.CONFIRMATION);          //On déclare un pop up qui viendra demander confirmation
                boiteDeFermetureUrgente.setTitle("Bataille Navale - Fermeture");            //On lui donne un titre
                boiteDeFermetureUrgente.setHeaderText("La partie n'est pas sauvegardée!");
                boiteDeFermetureUrgente.setContentText("Etes vous sûr de vouloir quitter sans sauvegarder ?");
                ButtonType boutonOui = new ButtonType("Oui");           //On déclare deux boutons qui sont les possibilités du joueur
                ButtonType boutonNon = new ButtonType("Non");
                boiteDeFermetureUrgente.getButtonTypes().setAll(boutonOui, boutonNon);          //On les ajoute à la boite de dialogue

                Optional<ButtonType>choix = boiteDeFermetureUrgente.showAndWait();          //On affiche et on attend
                if (choix.get() == boutonOui) {         
                    JeuGraphique.fenetreJeu.close();            //On ferme la fenètre pop up
                }
                else if (choix.get() == boutonNon) {
                    AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();          //On déclare un objet de type AffichageJeuGraphique
                    affichageJeuGraphique.affichageJoueur();            //On retourne à l'affichage du menu joueur
                }
            }
        });
        JeuGraphique.fenetreJeu.getIcons().add(new Image("/images/iconNaval.png"));             //On donne l'endroit où se trouve le sticker
        
        
        AffichageJeuGraphique choixJoueur = new AffichageJeuGraphique();
        choixJoueur.affichageJoueur();
    }
        
    public void tourIA() throws InterruptedException{
        
        Affichage.afficher(numeroJoueur, 0, JeuGraphique.plateauDeJeu);         //Pour les test on affiche les plateaux de l'IA
        System.out.println();
        Affichage.afficher(numeroJoueur, 1, JeuGraphique.plateauDeJeu);

        ia.jouer();            //On lance la méthode qui permet à l'IA de jouer

    }
    

    
    //**************************************************************************
    /**
     * Méthode de placement aléatoire d'une flotte de navire.
     * Cette méthode permet de placer aléatoirement une flotte sur la grille de jeu
     * @param flotte La flotte du joueur
     * @param numeroJoueur La numéro du joueur
     */
    public void placementFlotteGraphique(List<Flotte> flotte, int numeroJoueur){
        
        for (int pListe=0; pListe<10; pListe++){            //On fait une boucle pour parcourir toute la liste contenant la flotte
            int x, y, direction;            //Variable qui permet de stocke les informations générées aléatoirement
            
            x= (int) (Math.random()*15);            //On stocke une valeur aléatoire comprise entre 0 et 14 dans x
            y= (int) (Math.random()*15);    
                    //On stocke une valeur aléatoire comprise entre 0 et 14 dans y
            direction= (int) (Math.random()*2);            //On stocke une valeur aléatoire 0 ou 1 dans direction (0: Horizontal, 1: Vertical)
            
            if (plateauDeJeu.verifEmplacementVide(numeroJoueur, x, y, direction, flotte.get(pListe).taille, flotte.get(pListe).nRef, flotte.get(pListe).lRef)==true){            //Si l'emplacement est possible
                placementNavireGraphique(flotte, x, y, pListe, numeroJoueur, direction);            //On place le navire sur la grille du joueur
            }
            else{            //Sinon
                pListe--;            //On refait le tour
            }
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de placer le navire sur la grille du joueur.
     * Elle permet de rentrer les informations dans le plateau de jeu
     * et dans les coordonnées du navire
     * @param flotte La liste qui contient la flotte du joueur
     * @param x La valeur de l'axe x dans la grille du joueur
     * @param y La valeur de l'axe y dans la grille du joueur
     * @param pListe La position du navire dans la liste flotte
     * @param numeroJoueur La numéro du joueur
     * @param direction La direction du navire
     */
    public void placementNavireGraphique(List<Flotte> flotte,int x, int y,int pListe, int numeroJoueur, int direction){
        
        int numeroEtage = Plateau.numeroEtage(numeroJoueur,0);              //On déduir le numéro de l'étage dans le plateau de jeu avec le numéro du joueur
        int [][] coordonnees = new int [flotte.get(pListe).taille][3];          //On créé un tableau de coordonnée qui permet de stocker les informations des coordonneés du navire
        char lRef = flotte.get(pListe).lRef;               //On récupère la lettre de référence du navire
        int nPlateau = Flotte.pListeToNPlateau(lRef, pListe);
        
        if (direction ==0){             //Si la direction est horizontale
            for (int j=0; j<flotte.get(pListe).taille ; j++){           //On fait une boucle qui fait la taille du navire pour stocker toutes les informations nécésaires
                plateauDeJeu.modification(x+j, y, numeroEtage, 0, lRef);            //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock la lettre du navire
                plateauDeJeu.modification(x+j, y, numeroEtage, 1, nPlateau);            //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock le numéro plateau du navire
                coordonnees[j][0]=x+j;              //On stocke la valeur x dans le tableau coordonnés
                coordonnees[j][1]=y;                //On stocke la valeur y dans le tableau coordonnés
                coordonnees[j][2]=1;                //On stocke la valeur par défaut 1 qui signifie cette casse du navire n'a pas été touchée
            }
        }
        
        if (direction ==1){         //Si la direction est vertical
            for (int j=0; j<flotte.get(pListe).taille ; j++){               //On fait une boucle qui fait la taille du navire pour stocker toutes les informations nécésaires
                plateauDeJeu.modification(x, y+j, numeroEtage, 0, lRef);                //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock la lettre du navire
                plateauDeJeu.modification(x, y+j, numeroEtage, 1, nPlateau);                //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock le numéro plateau du navire
                coordonnees[j][0]=x;                //On stocke la valeur x dans le tableau coordonnés
                coordonnees[j][1]=y+j;              //On stocke la valeur y dans le tableau coordonnés
                coordonnees[j][2]=1;                //On stocke la valeur par défaut 1 qui signifie cette casse du navire n'a pas été touchée
            } 
        }
        
        flotte.get(pListe).coordonnees=coordonnees;         //On stocke le tableau coordonnées dans le tableau coordonnées du navire
        flotte.get(pListe).direction=direction;             //On stocke la direction du navire dans les informations du navire
    }
    


    //**************************************************************************
    /**
     * Méhode qui permet à l'utilisateur de bouger un de ses navires.
     * Elle permet de vérifier si toutes les conditions sont réunis pour bouger un navire et le déplace.
     * @param pListe La position dans la liste du navire
     * @param xBouge La position x de déplacement choisie
     * @param yBouge La position y de déplacement choisie
     */
    public static void bougerNavire(int pListe, int xBouge, int yBouge){
        
        List<Flotte> flotte = JeuGraphique.flotteJoueur0;

        /*Trouver toutes les posibilités de placement du navire****************/
        int  [] possibilite = new int[4];            //Tableau utilisé pour stocker les 4 posibilitées de placement du navire 
        
        if (flotte.get(pListe).direction == 0){         //Si la direction du navire est horizontale
            
            int xCord = flotte.get(pListe).coordonnees[0][0];           //On récupère la valeur du premier x dans les coordonées du navire
            if (xCord==0) possibilite[0]=90;         //Si x est égale à 0, un déplacement vers la gauche n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[0]= xCord - 1;          //Sinon, on stock la coordonnées x de la case de gauche dans le tableau possibilité
            
            xCord = flotte.get(pListe).coordonnees[flotte.get(pListe).taille - 1][0];           //On récupère la valeur du dernier x dans les coordonées du navire
            if (xCord==14) possibilite[1]=90;           //Si x est égale à 14, un déplacement vers la gauche n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[1]= xCord + 1;             //Sinon, on stock la coordonnées x de la case de droite dans le tableau possibilité
            
            
            int yCord = flotte.get(pListe).coordonnees[0][1];           //On récupère la valeur du premier y dans les coordonées du navire
            switch (yCord) {
                case 0:             //Si y est égale à 0 un déplacement vers le haut n'est pas possible
                    possibilite[2]= 90;         //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[3]= yCord +1;           //Et on stock la coordonées y de la ligne en dessous dans le tableau possibilité
                    break;
                case 14:            //Si y est égale à 14 un déplacement vers le bas n'est pas possible
                    possibilite[3]= 90;         //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[2]= yCord -1;           //Et on stock la coordonées y de la ligne au dessus dans le tableau possibilité
                    break;
                default:            //Sinon aucun des cas est vérifié
                    possibilite[3]= yCord +1;           //On stock la coordonées y de la ligne en dessous dans le tableau possibilité
                    possibilite[2]= yCord -1;           //On stock la coordonées y de la ligne au dessus dans le tableau possibilité
                    break;
            }
        }
        
        if (flotte.get(pListe).direction == 1){         //Si la direction du navire est vertical
            
            int yCord = flotte.get(pListe).coordonnees[0][1];           //On récupère la valeur du premier y dans les coordonées du navire
            if (yCord==0) possibilite[2]= 90;           //Si y est égale à 0, un déplacement vers le haut n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[2]= yCord - 1 ;            //Sinon, on stock la coordonnées y de la case au dessus dans le tableau possibilité
            
            yCord = flotte.get(pListe).coordonnees[flotte.get(pListe).taille - 1][1];
            if (yCord ==14) possibilite[3]= 90;         //Si y est égale à 0, un déplacement vers le bas n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[3]= yCord + 1;             //Sinon, on stock la coordonnées y de la case en dessous dans le tableau possibilité
            
            
            int xCord = flotte.get(pListe).coordonnees[0][0];           //On récupère la valeur du premier x dans les coordonées du navire
            switch (xCord) {
                case 0:             //Si x est égale à 0 un déplacement vers la gauche n'est pas possible
                    possibilite[0]= 90;          //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[1]= xCord + 1;          //Et on stock la coordonées x de la colonne à droite dans le tableau possibilité
                    break;
                case 14:            //Si x est égale à 14 un déplacement vers la droite n'est pas possible
                    possibilite[1]= 90;          //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[0]= xCord - 1;          //Et on stock la coordonées x de la colonne à gauche dans le tableau possibilité
                    break;
                default:
                    possibilite[1]= xCord + 1;          //On stock la coordonées x de la colonne à droite dans le tableau possibilité
                    possibilite[0]= xCord - 1;          //On stock la coordonées x de la colonne à gauche dans le tableau possibilité
                    break;
            }
        }
        
        /*Vérifier que le navire va pas chevocher un autre navire**************/
        if (flotte.get(pListe).direction==0){               //Si le navire est à l'horizontale
            if (possibilite[0]!= 90){           //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[0], flotte.get(pListe).coordonnees[0][1], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){          //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[0]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[1]!= 90){
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[1]- flotte.get(pListe).taille +1 , flotte.get(pListe).coordonnees[0][1], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){            //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[1]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[2]!= 90){
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[2], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[2]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[3]!= 90){
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[3], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[3]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
        }
        
        if (flotte.get(pListe).direction==1){           //Si le navire est à la vertical
            if (possibilite[0] != 90){          //On vérifie que la possibilité est possible
                if(plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[0], flotte.get(pListe).coordonnees[0][1], 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){            //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[0]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[1] != 90){          //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[1], flotte.get(pListe).coordonnees[0][1], 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[1]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            
            if (possibilite[2] != 90){          //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[2], 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[2]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[3] != 90){          //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[3]-  flotte.get(pListe).taille +1 , 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[3]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
        }
        
        if (possibilite[0]==90 && possibilite[1]==90 && possibilite[2]==90 && possibilite[3]==90){          //On vérifie qu'il y un moyen de déplacer le navire, pour éviter que le joueur ne trouve pas un moyen de quitter la boucle
            System.out.println("Erreur! Ce navire ne peut pas être déplacé. \n");           //Si c'est le cas on affiche un message d'erreur
        }
        
        possibilite[0]+=65;
        possibilite[1]+=65;
        
        if (flotte.get(pListe).direction==0){           //Si le navire est à l'horizontale

            if (yBouge==possibilite[3]){         //possibilité de desecendre
                for (int i=0; i<flotte.get(pListe).taille;i++){         //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0] , flotte.get(pListe).coordonnees[i][1]+ 1, Plateau.numeroEtage(numeroJoueur, 0));         //On déplace le navire
                    flotte.get(pListe).coordonnees[i][1] ++;            //On change les coordonnées 
                }
                AffichageJeuGraphique affichageJoueur = new AffichageJeuGraphique();
                affichageJoueur.deplacementEffectueNavire();
            }
            else if (yBouge==possibilite[2]){            //possibilité de monter
                for (int i=0; i<flotte.get(pListe).taille;i++){         //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1] - 1, Plateau.numeroEtage(numeroJoueur, 0));          //On déplace le navire
                    flotte.get(pListe).coordonnees[i][1] --;            //On change les coordonnées 
                }
                AffichageJeuGraphique affichageJoueur = new AffichageJeuGraphique();
                affichageJoueur.deplacementEffectueNavire();
            }
                
            else if (xBouge +65 == possibilite[1] ){              //Possibilité d'aller à gauche 
                for (int i=flotte.get(pListe).taille - 1; i>=0;i--){            //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0] + 1, flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));          //On déplace le navire
                    flotte.get(pListe).coordonnees[i][0] ++;            //On change les coordonnées 
                }
                AffichageJeuGraphique affichageJoueur = new AffichageJeuGraphique();
                affichageJoueur.deplacementEffectueNavire();
            }
            else if (xBouge +65 ==  possibilite[0]){          //Possibilité d'aller à droite
                for (int i=0; i<flotte.get(pListe).taille;i++){         //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0] - 1, flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));          //On déplace le navire
                    flotte.get(pListe).coordonnees[i][0] --;            //On change les coordonnées 
                }
                AffichageJeuGraphique affichageJoueur = new AffichageJeuGraphique();
                affichageJoueur.deplacementEffectueNavire();
            }
        }
        
    
        if (flotte.get(pListe).direction==1){           //Si le navire est à l'horizontal
            
            if (yBouge==possibilite[3]){         //possibilité de desecendre
                for (int i=flotte.get(pListe).taille - 1; i>=0;i--){            //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1] , flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1]+1, Plateau.numeroEtage(numeroJoueur, 0));          //On déplace le navire
                    flotte.get(pListe).coordonnees[i][1] ++;            //On change les coordonnées 
                }
                AffichageJeuGraphique affichageJoueura = new AffichageJeuGraphique();
                affichageJoueura.deplacementEffectueNavire();
            }
            else if (yBouge==possibilite[2]){            //possibilité de monter
                for (int i=0; i<flotte.get(pListe).taille;i++){         //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1]- 1, Plateau.numeroEtage(numeroJoueur, 0));          //On déplace le navire
                    flotte.get(pListe).coordonnees[i][1] --;            //On change les coordonnées
                }
                AffichageJeuGraphique affichageJoueura = new AffichageJeuGraphique();
                affichageJoueura.deplacementEffectueNavire();
            }

            else if (xBouge +65 == possibilite[1]){          //Possibilité d'aller à gauche
                for (int i=0; i<flotte.get(pListe).taille;i++){         //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0]+1 , flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));          //On déplace le navire
                    flotte.get(pListe).coordonnees[i][0] ++;            //On change les coordonnées
                }
                AffichageJeuGraphique affichageJoueura = new AffichageJeuGraphique();
                affichageJoueura.deplacementEffectueNavire();
            }
            else if (xBouge +65  == possibilite[0]){          //Possibilité d'aller à droite
                for (int i=0; i<flotte.get(pListe).taille;i++){         //On parcourt la taille du navire
                    plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0]-1, flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));           //On déplace le navire
                    flotte.get(pListe).coordonnees[i][0] --;            //On change les coordonnées
                }
                AffichageJeuGraphique affichageJoueura = new AffichageJeuGraphique();
                affichageJoueura.deplacementEffectueNavire();
            }
        } 
    }


    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message si un des joueurs à gagné.
     * @return true si un joueur à gagné, false, sinon
     */
    public static boolean victoire(){

        boolean etat0;          //On initialise un booléen
        boolean etat0SousMarin=false;           //On initialise un booléen à false pour le sous-marin 
        for (int i=0; i<10; i++){           //On parcourt la flotte du joueur 
            etat0 = flotteJoueur0.get(i).etat;          //Pour chaque navire, on regarde si il a coulé ou pas
            if (etat0==true) i=10;         //Si il est toujours à flot
            if (i==9 && etat0!=true){           //Si c'est le dernier navire et qu'il vient d'etre coulé
                JeuGraphique.chronometre.stop();        //On arrête le chronomètre
                JeuGraphique.victoire=true ;            //On arrête la boucle de Jeu
                AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique(); 
                affichageJeuGraphique.victoireJoueur();         //On affiche les informations de la victoire
                return true;
            } 
        }
        
        for (int i=6; i<10;i++){            //On parcourt les sous-marin
            if (etat0SousMarin==false) etat0SousMarin=flotteJoueur1.get(i).etat;            //Si le sous marin n'a pas coulé, on prend l'état du sous-marin
        }

        if (etat0SousMarin==false){         //Si il y a plus aucun sous-marin
            JeuGraphique.chronometre.stop();        //On arrête le chronomètre
            JeuGraphique.victoire=true ;            //On arrête la boucle de Jeu
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
            affichageJeuGraphique.victoireJoueurFofait();              //On affiche les informations de la victoire
        }

        boolean etat1SousMarin=false;           //On initialise un booléen à false pour le sous-marin
        boolean etat1;      //On initialise un booléen
        for (int i=0; i<10; i++){           //On parcourt la flotte de l'IA
            etat1 = flotteJoueur1.get(i).etat;          //Pour chaque navire, on regarde si il a coulé ou pas
            if (etat1==true) i=10;         //Si il est toujours à flot
            if (i==9 && etat1!=true){           //Si c'est le dernier navire et qu'il vient d'etre coulé
                JeuGraphique.chronometre.stop();        //On arrête le chronomètre
                JeuGraphique.victoire=true ;            //On arrête la boucle de Jeu
                AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
                affichageJeuGraphique.victoireIA();              //On affiche les informations de la victoire
                return true;
            }
        }
        
        for (int i=6; i<10;i++){            //On parcourt les sous-marins
            if (etat1SousMarin==false) etat1SousMarin=flotteJoueur1.get(i).etat;            //Si le sous marin n'a pas coulé, on prend l'état du sous-marin
            return true;
        }

        if (etat1SousMarin==false){         //Si il y a plus aucun sous-marin
            JeuGraphique.chronometre.stop();        //On arrête le chronomètre
            JeuGraphique.victoire=true ;            //On arrête la boucle de Jeu
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
            affichageJeuGraphique.victoireIAFofait();           //On affiche les informations de la victoire
            return true;
        }
        return false;
    }
}
