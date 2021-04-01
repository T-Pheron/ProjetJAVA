package bataillenavalgraphique;


import bataillenavalgraphique.bataillenaval.view.Affichage;
import bataillenavalgraphique.bataillenaval.controller.JeuNGraphique;
import bataillenavalgraphique.bataillenaval.model.Flotte;
import bataillenavalgraphique.bataillenaval.model.Plateau;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
        
public class JeuGraphique{

    
    public static Plateau plateauDeJeu = new Plateau();             //Variable qui stocke l'ensemble du plateau de jeu 
    public static List <Flotte> flotteJoueur0 ;               //List qui stocke l'ensemble de la flotte du joueur 0
    public static List <Flotte> flotteJoueur1 ;               //List qui stocke l'ensemble de la flotte du joueur 1
    
    public static int numeroJoueur;                 //Variable qui permet de connaître à quelle joueur c'est la tour
    public static int niveauIA=0;           //Variable qui permet de stoker le niveau de l'IA
    public static IA ia;           //On initialise une variable qui permet de faire fonctionner l'IA
    public static boolean premierTour = true;        //Variable utilisé pour savoir si c'est le premier tir
    public static int compteurTourIA;               //Variable qui compte le nombre de tour de l'IA
    public static int compteurTourHumain;           //variable qui compte le nombre de tour du joueur humain
    
    public static Stage fenetreJeu = new Stage();
    public static boolean selectionJoueur=false;

        
    
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
        ia = new IA(niveauIA);

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
        }

        JeuGraphique.fenetreJeu.setTitle("Bataille Navale - Jeu");
        JeuGraphique.fenetreJeu.setWidth(1400);
        JeuGraphique.fenetreJeu.setHeight(900);
        JeuGraphique.fenetreJeu.setResizable(false);
        JeuGraphique.fenetreJeu.getIcons().add(new Image("/images/iconNaval.png")); 
        
        
        AffichageJeuGraphique choixJoueur = new AffichageJeuGraphique();
        choixJoueur.affichageJoueur();
    }
        
    public void tourIA() throws InterruptedException{
        
        Affichage.afficher(numeroJoueur, 0, JeuGraphique.plateauDeJeu);         //Pour les test on affiche les plateaux de l'IA
        System.out.println();
        Affichage.afficher(numeroJoueur, 1, JeuGraphique.plateauDeJeu);

        compteurTourIA++;           //On rajoute 1 au compteur de tour de l'IA

        ia.jouer();            //On lance la méthode qui permet à l'IA de jouer

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
    
























    ////////////////////////////////A RETIRER///////////////////////////////////
    //**************************************************************************
    /**
     * Converti les minuscules en majuscule.
     * Cette méthode recois une lettre en majuscule ou en misnucule et la transforme
     * si nécésaire en lettre majuscule.
     * @param lettre La lettre à convertir
     * @return La lettre en majuscule
     */
    public static char convertirMinuscules(char lettre){
        if (lettre<65||lettre>90){                  //On vérifie que la lettre est une lettre minuscule
            return lettre -= 'a'-'A';                   //Si c'est le cas on la convertie en majuscule en lui retirant la différence qu'il y a entre les minuscules et les majucules
        }
        return lettre;              //On retourne la lettre en majuscule
    }

    
}
