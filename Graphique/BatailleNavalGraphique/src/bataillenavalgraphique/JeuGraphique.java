package bataillenavalgraphique;

import bataillenaval.controller.*;
import bataillenaval.view.*;
import bataillenaval.model.Flotte;
import bataillenaval.model.Plateau;
import java.util.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
        
public class JeuGraphique{
    
    public static GrilleBoutons grilleBoutonNavire = new GrilleBoutons();
    public static GrilleBoutons grilleBoutonTirs = new GrilleBoutons();
    
    public static Plateau plateauDeJeu = new Plateau();             //Variable qui stocke l'ensemble du plateau de jeu 
    public static List <Flotte> flotteJoueur0 ;               //List qui stocke l'ensemble de la flotte du joueur 0
    public static List <Flotte> flotteJoueur1 ;               //List qui stocke l'ensemble de la flotte du joueur 1
    
    public static int numeroJoueur;                 //Variable qui permet de connaître à quelle joueur c'est la tour
    public static int niveauIA=0;           //Variable qui permet de stoker le niveau de l'IA
    public static IA ia = new IA();           //On initialise une variable qui permet de faire fonctionner l'IA
    public static boolean premierTour = true;        //Variable utilisé pour savoir si c'est le premier tir
    public static int compteurTourIA;               //Variable qui compte le nombre de tour de l'IA
    public static int compteurTourHumain;           //variable qui compte le nombre de tour du joueur humain
    
    public JeuGraphique(){

    }
    
    public JeuGraphique(int numeroJoueur, int niveauIA,IA ia, boolean premierTour, Plateau plateauDeJeu, List <Flotte> flotteJoueur0, List <Flotte> flotteJoueur1, int compteurTourIA, int compteurTourHumain){
        
        JeuGraphique.numeroJoueur = numeroJoueur;
        JeuGraphique.niveauIA= niveauIA;
        JeuGraphique.ia = ia;
        JeuGraphique.premierTour= premierTour;
        JeuGraphique.compteurTourHumain =compteurTourHumain;
        JeuGraphique.compteurTourIA = compteurTourIA;
        JeuGraphique.plateauDeJeu = plateauDeJeu;
        JeuGraphique.flotteJoueur0 = flotteJoueur0;
        JeuGraphique.flotteJoueur1 = flotteJoueur1;
    }
    
    
     public JeuGraphique(int niveauIA){

        JeuGraphique.niveauIA= niveauIA;

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
        Menu menu = new Menu();             //On initialise une variable pour lancer les différents menus
        MenuGraphique menuGraphique = new MenuGraphique();
        boolean tourSuivant = true;             //Variable qui permet de savoir si on passe au tour suivant
        
        Stage fenetreJeu = new Stage();
        fenetreJeu.setTitle("Bataille Navale - Jeu");
        fenetreJeu.setWidth(1400);
        fenetreJeu.setHeight(700);
        fenetreJeu.setResizable(false);
        fenetreJeu.getIcons().add(new Image("/images/iconNaval.png")); 

        
        if (premierTour==true){
            //Initialisation des flottes************************
            System.out.println("On est la");
            flotteJoueur0 = Jeu.createFlotte();            //On créé une flotte pour le joueur 0
            flotteJoueur1 = Jeu.createFlotte();            //On créé une flotte pour le joueur 1  
            
            placementFlotteGraphique(flotteJoueur0, 0);            //On place aléatoirement la flotte du joueur 0 sur sa grille de navire
            placementFlotteGraphique(flotteJoueur1, 1);            //On place aléatoirement la flotte du joueur 1 sur sa grille de navire
        
            numeroJoueur=0;         //On met le joueur qui joue par défaut à 0
            premierTour=false;
        }
        
        GridPane rootJeu = new GridPane();
        rootJeu.setPadding(new Insets(20));
        rootJeu.setHgap(30);
        rootJeu.setVgap(20);
        
        grilleBoutonNavire.miseAJourAffichage(plateauDeJeu);
        
        rootJeu.add(grilleBoutonNavire.getRoot(),0,1,3,5);
        rootJeu.add(grilleBoutonTirs.getRoot(),3,1,3,5);
        
        
        Label titreGrilleNavire = new Label("Voici la grille de vos bateaux :");
        rootJeu.add(titreGrilleNavire,0,0,3,1);
        Label titreGrilleTirs = new Label("Voici la grille de vos tirs :");
        rootJeu.add(titreGrilleTirs,3,0,3,1);
        
        Scene sceneJeu = new Scene(rootJeu);
        fenetreJeu.setScene(sceneJeu);
        fenetreJeu.show();
        
        Affichage.afficher(0, 0, plateauDeJeu);
        
        
             
        /*Boucle de jeu********************************************************/
        /*while (tourSuivant==true){            //On vérifie qu'on doit passer au tour suivant

            int retourMenu = 0;

            if (numeroJoueur==0){            //Si le joueur est le joueur humain on affiche sa grille et son
                System.out.println();
                Affichage.afficher(numeroJoueur, 1, plateauDeJeu);            //On affiche la grille des tirs du joueur
                System.out.println();
                Affichage.afficher(numeroJoueur, 0, plateauDeJeu);            //On affiche la grille des navire du joueur

                retourMenu = menu.menuJoueur();            //On lance le menu joueur et stocke ce qu'il retourne

                switch (retourMenu) {
                    case 1:            //Si le joueur souhaite tirer
                        Object[] referencesNavire = new Object [2];         //On créé un tableau pour stocker les références du navire
                        compteurTourHumain++;       //On rajouter 1 au nombre de tour du joueur

                        referencesNavire = menu.menuTirer ();            //On lance le menu pour tirer et on récupère les informations du navire sélectionné

                        int pListe=Flotte.nPlateauToPListe((char) referencesNavire[0], (int) referencesNavire[1]);           //On trouve la position du navire dans la liste à l'aide de son numéro plateau et sa lettre de reférence
                        if (flotteJoueur0.get(pListe).etat==true) retourMenu= flotteJoueur0.get(pListe).tir();          //On vérifie que le navire n'est pas coulé 
                        else {
                            //System.out.println(ROUGE + "Erreur!"+RESET +"\nCe navire à déjà été coulé et ne peut plus effectuer de tire");TimeUnit.SECONDS.sleep(3);           //Sinon, on affiche un message d'erreur
                            retourMenu = 2;           //On relance le tour du joueur
                        }

                        if (retourMenu==2 || retourMenu==3) compteurTourHumain--;           //Si on recommence le tour du joueur on retire 1 au tour du joueur

                        break;
                    case 5:         //Si le joueur souhaite déplacer son navire
                        compteurTourHumain++;

                        //if (numeroJoueur==0) retourMenu = bougerNavire (flotteJoueur0,numeroJoueur);            //On appel la méthode qui permet de bouger un navire

                        if (retourMenu==2 || retourMenu==3) compteurTourHumain--;//Si on recommence le tour du joueur on retire 1 au tour du joueur

                        break;
                    case 2: retourMenu=2;           //On recommence le tour du joueur
                    case 4: System.out.println("\nFermeture du jeu. \nA bientôt"); break;
                    default:
                        //System.out.println(ROUGE + "Erreur!"+RESET+ "Problème d'appelle du menuJoueur");            //En cas d'erreur on affiche un message d'erreur
                        retourMenu=2;            //On relance le tour du joueur
                        break;
                }
            }

            if(numeroJoueur==1){
                Affichage.afficher(numeroJoueur, 0, JeuGraphique.plateauDeJeu);         //Pour les test on affiche les plateaux de l'IA
                System.out.println();
                Affichage.afficher(numeroJoueur, 1, JeuGraphique.plateauDeJeu);

                compteurTourIA++;           //On rajoute 1 au compteur de tour de l'IA

                retourMenu = ia.jouer(niveauIA);            //On lance la méthode qui permet à l'IA de jouer

                if (retourMenu==2) compteurTourIA--;            //Si l'IA recommence son tour on retire 1 au compteur
            }

            if (retourMenu==2) {            //Si le menu retourne 2 à la fin de la boucle
                System.out.println("On relance votre tour");            //On affiche le message de relance de tour
                System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");TimeUnit.SECONDS.sleep(1);
                numeroJoueur--;             //On retire 1 au numéro du joueur pour recommencer le tour
            }
            if (retourMenu==3) numeroJoueur--;              //Si le menu retourne 3 à la fin de la boucle, ça signifie qu'on doit relancer le tour sans message
            if (retourMenu==4) tourSuivant=false;               //Si le menu retourne 4 à la fin de la boucle; ça signifie qu'on doit quitter le jeu
            if (jeu.victoire()==true) tourSuivant=false;                //On test à chaque tour la victoire d'un des 2 joueurs
            if (numeroJoueur == 1) numeroJoueur=0;              //Si le numéro du joueur est 1 on le remet à 0
            else numeroJoueur ++;            //Sinon on lui rajoute 1
        }
        */
    }
    
    
    
    public void affichagePlateauGraphique() {

        
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
     * @param numeroJoueur
     * @param direction 
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
    
    
}
