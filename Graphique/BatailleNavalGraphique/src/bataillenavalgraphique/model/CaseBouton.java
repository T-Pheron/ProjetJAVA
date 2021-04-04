package bataillenavalgraphique.model;

import bataillenavalgraphique.view.AffichageJeuGraphique;
import bataillenavalgraphique.controller.JeuGraphique;
import bataillenavalgraphique.bataillenaval.model.Flotte;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


/**
 * Classe CaseBouton.
 * Type utilisé pour créer une case du plateau de Jeu
 * @author Théric PHERON and Joé LHUERRE
 */
public class CaseBouton {

    //**************************************************************************
    protected int x;           //Variable utilisée pour stocker la valeur de l'axe x 
    protected int y;           //Variable utilisée pour stocker la valeur de l'axe y
    private char plateauRef = 'A';          //Variable utilisée pour stocker la référence du plateau qu'on a initialisé à A par défaut
    private Button bouton = new Button();           //On déclare un bouton
    private String couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";         //On change la couleur
    private String couleurSurvol = "-fx-background-color: rgba(82,127,143,0.80);";
    private String couleurText = "-fx-text-fill: BLACK ;";
    protected List<Integer>   listeInformations;          //On déclare un liste d'information
    //**************************************************************************


    //**************************************************************************
    /**
     * Constructeur de la classe CaseBouton.
     * Il permet de créer un bouton qui contient différente informations
     * @param plateauref Le numéro de référence du plateau ou est placé ce bouton
     */
    public CaseBouton(char plateauref){
        x=0;            //On initialise à 0 la coordonnée de x par défaut
        y=0;            //On initialise à 0 la coordonnée de y par défaut
        this.plateauRef= plateauref;            //La lettre de référence du plateau passé en paramètre devient celle de cette méthode
    }
    
    
    //**************************************************************************
    /**
     * Constructeur de la classe CaseBouton.
     * Il permet de créer un bouton avec des informations nécésaire à des actions
     * @param y Sa position y dans la grille de bouton
     * @param x Sa position x dans le grille de bouton
     * @param plateauRef La lettre de référence du plateau
     * @param listeInformations Une liste contenant des informations nécésaire pour effectuer des action
     */
    public CaseBouton(int y, int x, char plateauRef, List<Integer> listeInformations ){
        this.x=x;            //La coordonnée de x passé en paramètre devient la coordonnée de x de cette méthode
        this.y=y;            //La coordonnée de y passé en paramètre devient la coordonnée de y de cette méthode
        this.plateauRef = plateauRef;            //La lettre de référence du plateau passé en paramètre devient celle de cette méthode
        bouton.setPrefSize(75,75);          //On définit la taille du bouton
        this.listeInformations = listeInformations;         //La liste d'information passé en paramètre devient la liste d'information de cette méthode
        
        if (this.plateauRef=='D') couleurSurvol=couleurDefaut;          //Si la lettre référence du plateau est D, la couleur de survol prend la couleur par défaut
        
        bouton.setStyle(couleurDefaut + couleurText);           //On met une couleur par défaut et on donne un couleur au text
        bouton.setOnMouseEntered (e -> bouton.setStyle (couleurSurvol + couleurText));          //On modifie la couleur du bouton et du text si l'utlilisateur a le curseur sur le bouton
        bouton.setOnMouseExited (e -> bouton.setStyle (couleurDefaut + couleurText));          //On modifie la couleur du bouton et du text si l'utlilisateur enlève le curseur du bouton

        bouton.setOnAction((ActionEvent event) -> {         //Si l'utilisateur clique on exécute le prgramme 
            
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();          //On déclare un objet de type AffichageJeuGraphique
            
            if (plateauRef=='S'){           //Si la lettre référence du plateau est égale à S
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est différent de '_'
                    int pListe = Flotte.nPlateauToPListe((char) JeuGraphique.plateauDeJeu.get(x,y,0,0), (int) JeuGraphique.plateauDeJeu.get(x,y,0,1));          //On déduit la position du bateau dans la liste
                    if (JeuGraphique.flotteJoueur0.get(pListe).navireVivant()==true){           //On vérifie que le navire n'est pas coulé
                        JeuGraphique.partieSauvegarde=false;
                        try {
                            JeuGraphique.compteurTourHumain++;          //On rajoute 1 au nombre de tour du joueur 
                            JeuGraphique.flotteJoueur0.get(pListe).tir(listeInformations.get(1), listeInformations.get(2));
                        } catch (InterruptedException e1) {
                            System.err.println("Erreur! .Le tir n'a pas pu être effectué");
                        }
                    }
                    else{
                        affichageJeuGraphique.erreurNavireCoule();          //On affiche un message d'erreur en disant que le navire est coulé
                    }
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est égal à '_'
                    affichageJeuGraphique.erreurCaseVideS();
                }
            }
            
            if (plateauRef=='B'){           //Si la lettre référence du plateau est égale à S
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('0')){            //Si le bouton qui se trouve au coordonnée x est différent de '0'
                JeuGraphique.partieSauvegarde=false;    
                try {
                        JeuGraphique.compteurTourHumain++;          //On rajoute 1 au nombre de tour du joueur 
                        JeuGraphique.flotteJoueur0.get(listeInformations.get(0)).tir(x, y);         //On tire et on rajoute dans la liste qu'on a tiré a cet emplacement
                    } catch (InterruptedException ex) {
                        System.err.println("Erreur! La sélection du navire à échoué");
                    }
                }
            }
            
            if (plateauRef=='C' && nouvellePositionPossible(x,y,listeInformations.get(0))==true){         //Si la lettre référence du plateau est C et qu'il qu'il y a une nouvelle position possible
                JeuGraphique.compteurTourHumain++;          //On rajoute 1 au nombre de tour du joueur     
                JeuGraphique.partieSauvegarde=false;    
                affichageJeuGraphique.bougerNavire(x,y, listeInformations.get(0));          //On bouge le navire et on rajoute ces informations dans la lsite
            }
            
        });

    }
    
    public CaseBouton(int y, int x, char plateauRef ){
        this.x=x;            //La coordonnée de x passé en paramètre devient la coordonnée de x de cette méthode
        this.y=y;            //La coordonnée de y passé en paramètre devient la coordonnée de x de cette méthode
        this.plateauRef = plateauRef;            //La lettre de référence du plateau passé en paramètre devient celle de cette méthode
        bouton.setPrefSize(75,75);          //On définit la taille du bouton
        
        if (this.plateauRef=='D') couleurSurvol=couleurDefaut;          //Si la lettre référence du plateau est D, la couleur de survol prend la couleur par défaut
        
        bouton.setStyle(couleurDefaut + couleurText);           //On met une couleur par défaut et on donne un couleur au text
        bouton.setOnMouseEntered (e -> bouton.setStyle (couleurSurvol + couleurText));          //On modifie la couleur du bouton et du text si l'utlilisateur a le curseur sur le bouton
        bouton.setOnMouseExited (e -> bouton.setStyle (couleurDefaut + couleurText));          //On modifie la couleur du bouton et du text si l'utlilisateur enlève le curseur du bouton

        bouton.setOnAction((ActionEvent eventChargementPartie) -> {         //Si l'utilisateur clique on exécute le prgramme 
            
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();          //On déclare un objet de type AffichageJeuGraphique
            
            
            if (plateauRef=='N'){           //Si la lettre référence du plateau est égale à N
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est différent de '_'
                    int pListe = Flotte.nPlateauToPListe((char) JeuGraphique.plateauDeJeu.get(x,y,0,0), (int) JeuGraphique.plateauDeJeu.get(x,y,0,1));          //On déduit la position du bateau dans la liste
                    if (JeuGraphique.flotteJoueur0.get(pListe).navireVivant()==true){           //On vérifie que le navire n'est pas coulé
                        affichageJeuGraphique.selectionNavire(x,y);         //On appelle la méthode pour la sélection de la case de tire
                    }
                    else{
                        affichageJeuGraphique.erreurNavireCoule();          //On affiche un message d'erreur en disant que le navire est coulé
                    }
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est égal à '_'
                    affichageJeuGraphique.erreurCaseVideN();
                }
            }
            
            if (plateauRef=='T'){           //Si la lettre référence du plateau est égale à T
                affichageJeuGraphique.selectionTir(x, y);
            }
            
        });

    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de renvoyer le bouton
     * @return Retourn un bouton
     */
    public Button getButton(){
        return bouton;          //On return le bouton
    }


    //**************************************************************************
    /**
     * La méthode qui permet de mettre une texte dans le bouton
     * @param titre Le titre à mettre dans le bouton
     */
    public void setTitle(String titre){
        bouton.setText(titre);          //On return le bouton avec le text renté en paramètre
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de donner une couleur au bouton
     * @param lRef La lettre de référence d'un navire
     * @param nPlateau Le numéro du navire sur le plateau 
     * @param nTir Le numéro présent sur la grille de tir de l'adversaire
     */
    public void setColor(char lRef, int nPlateau, String nTir){
        switch (plateauRef) {           //Suivant ce que la lettre de référence du plateau est
            case 'D':           //Si la lettre de référence est le D
                if (nTir.equals("1") && lRef!='U'&& lRef!='C' && lRef!='D' && lRef!='S'){               //Si le joueur adversaire à tirer à cette emplacement mais n'a rien touché. On exclu aussi le faite que le joueur est bien pu déplacer son navire sur cette case à un tour n+1
                    bouton.setText("XX");
                    couleurDefaut="-fx-background-color: rgba(29,80,70,0.50);";          //On change les couleurs de la case
                    couleurText="-fx-text-fill: rgba(65,193,168,0.80);";
                    couleurSurvol=couleurDefaut;
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (nTir.equals("2")){               //Si le joueur adversaire à tirer à cette emplacement et a touché un navire
                    bouton.setText(lRef+ String.valueOf(nPlateau));
                    couleurDefaut="-fx-background-color: rgba(136,0,0,0.50);";          //On change les couleurs de la case
                    couleurText="-fx-text-fill: rgba(253,197,197,0.80);";
                    couleurSurvol=couleurDefaut;
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (nTir.equals("5")){               //Si le joueur adversaire à tirer sur un sous marin mais ne la pas coulé parce-que le navire qu'il a tiré n'était pas un sous marin
                    bouton.setText(lRef+ String.valueOf(nPlateau));
                    couleurDefaut="-fx-background-color: rgba(0,176,3,0.50);";          //On change les couleurs de la case
                    couleurSurvol=couleurDefaut;
                    couleurText="-fx-text-fill: rgba(191,255,192,0.80);";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                
                else if (lRef =='U'){           //Si la lettre de référence du navire est 'U'
                    couleurDefaut="-fx-background-color: rgba(221,13,13,0.50);";          //On change les couleurs de la case
                    couleurText = "-fx-text-fill: BLACK ;";
                    couleurSurvol=couleurDefaut;
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='C'){           //Si la lettre de référence du navire est 'C'
                    couleurDefaut="-fx-background-color: rgba(203,25,228,0.50);";          //On change les couleurs de la case
                    couleurText = "-fx-text-fill: BLACK ;";
                    couleurSurvol=couleurDefaut;
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='D'){           //Si la lettre de référence du navire est 'D'
                    couleurDefaut="-fx-background-color: rgba(15,141,214,0.50);";          //On change les couleurs de la case
                    couleurText = "-fx-text-fill: BLACK ;";
                    couleurSurvol=couleurDefaut;
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='S'){           //Si la lettre de référence du navire est 'S'
                    couleurDefaut="-fx-background-color: rgba(27,213,41,0.50);";          //On change les couleurs de la case
                    couleurText = "-fx-text-fill: BLACK ;";
                    couleurSurvol=couleurDefaut;
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='_'){           //Si la lettre de référence du navire est '_'
                    couleurDefaut="-fx-background-color: rgba(120,160,175,0.50);";          //On change les couleurs de la case
                    couleurText = "-fx-text-fill: BLACK ;";
                    couleurSurvol=couleurDefaut;
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }   break;
            
            default:
                if (nTir.equals("1") && lRef!='U'&& lRef!='C' && lRef!='D' && lRef!='S'){               //Si le joueur adversaire à tirer à cette emplacement mais n'a rien touché. On exclu aussi le faite que le joueur est bien pu déplacer son navire sur cette case à un tour n+1
                    bouton.setText("XX");           //On met comme text XX pour montrer qu'il y a eu un tir ici
                    couleurDefaut="-fx-background-color: rgba(29,80,70,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(21,59,51,0.80);";
                    couleurText="-fx-text-fill: rgba(65,193,168,0.80);";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (nTir.equals("2")){               //Si le joueur adversaire à tirer à cette emplacement et a touché un navire
                    bouton.setText(lRef+ String.valueOf(nPlateau));         //On écrit la lettre de référence du navire et le numero présent sur le numero du plateau
                    couleurDefaut="-fx-background-color: rgba(136,0,0,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(93,0,0,0.80);";
                    couleurText="-fx-text-fill: rgba(253,197,197,0.80);";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (nTir.equals("5")){               //Si le joueur adversaire à tirer sur un sous marin mais ne la pas coulé parce-que le navire qu'il a tiré n'était pas un sous marin
                    bouton.setText(lRef+ String.valueOf(nPlateau));         //On écrit la lettre de référence du navire et le numero présent sur le numero du plateau
                    couleurDefaut="-fx-background-color: rgba(0,176,3,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(82,135,2,0.80);";
                    couleurText="-fx-text-fill: rgba(191,255,192,0.80);";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='U'){           //Si la lettre référence du navire est 'U'
                    couleurDefaut="-fx-background-color: rgba(221,13,13,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(210,0,0,0.80);";
                    couleurText = "-fx-text-fill: BLACK ;";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='C'){           //Si la lettre référence du navire est 'C'
                    couleurDefaut="-fx-background-color: rgba(203,25,228,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(181,25,203,0.80);";
                    couleurText = "-fx-text-fill: BLACK ;";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='D'){           //Si la lettre référence du navire est 'D'
                    couleurDefaut="-fx-background-color: rgba(15,141,214,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(12,122,182,0.80);";
                    couleurText = "-fx-text-fill: BLACK ;";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
                else if (lRef =='S'){           //Si la lettre référence du navire est 'S'
                    couleurDefaut="-fx-background-color: rgba(27,213,41,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(15,185,30,0.80);";
                    couleurText = "-fx-text-fill: BLACK ;";
                    bouton.setStyle(couleurDefaut + couleurText);         //On modifie la couleur du bouton et du text du bouton  
                }
                else if (lRef =='_'){           //Si la lettre référence du navire est '_'
                    couleurDefaut="-fx-background-color: rgba(120,160,175,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(82,127,143,0.80);";
                    couleurText = "-fx-text-fill: BLACK ;";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }   break;
        }
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de donner une couleur au bouton.
     * @param pListe La position dans la liste du navire
     * @param nTirAdverse Le numéro compris dans la grille de tir de l'adversaire
     * @param lRef La lettre de référence du navire
     */
    public void setColor(int pListe, String nTirAdverse, char lRef ){
        if (plateauRef=='C'){           //Si le plateau est utilisé pour bouger des navires
                
                if(JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals(lRef) && JeuGraphique.plateauDeJeu.get(x, y, 0, 1)==(Object) Flotte.pListeToNPlateau(lRef, pListe)){            //Si c'est un navire qui est à cette coordonnée du plateau
                    switch (lRef) {         
                        case 'U':           //Si la lettre de référence des navires est 'U'
                            couleurDefaut="-fx-background-color: rgba(221,13,13,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                            break;
                        case 'C':           //Si la lettre de référence des navires est 'C'
                            couleurDefaut="-fx-background-color: rgba(203,25,228,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                            break;
                        case 'D':           //Si la lettre de référence des navires est 'D'
                            couleurDefaut="-fx-background-color: rgba(15,141,214,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                            break;
                        case 'S':           //Si la lettre de référence des navires est 'S'
                            couleurDefaut="-fx-background-color: rgba(27,213,41,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                            break;
                        default:            //Si on rentre dans aucun des cas précédent
                            break;
                    }
                }
                else if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){           //Si ce n'est pas un navire a cette coordonnée
                    switch ((char) JeuGraphique.plateauDeJeu.get(x, y, 0, 0)) {
                        case 'U':
                            couleurDefaut="-fx-background-color: rgba(175,77,77,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);            //On modifie la couleur du bouton et du text du bouton
                            break;
                        case 'C':
                            couleurDefaut="-fx-background-color: rgba(107,11,120,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                            break;
                        case 'D':
                            couleurDefaut="-fx-background-color: rgba(6,68,104,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                            break;
                        case 'S':
                            couleurDefaut="-fx-background-color: rgba(15,99,22,0.50);";          //On change les couleurs de la case
                            couleurSurvol=couleurDefaut;
                            couleurText = "-fx-text-fill: BLACK ;";
                            bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                            break;
                        default:            //Si on rentre dans aucun des cas précédent
                            break;
                    }
                }
                else if  (nouvellePositionPossible(x, y, pListe)==true){            //Si il y a une nouvelle position posible
                    if (nTirAdverse.equals("1") && lRef!='U'&& lRef!='C' && lRef!='D' && lRef!='S'){               //Si le joueur adversaire à tirer à cette emplacement mais n'a rien touché. On exclu aussi le faite que le joueur est bien pu déplacer son navire sur cette case à un tour n+1
                        bouton.setText("XX");           //On met comme text XX pour montrer qu'il y a eu un tir ici
                        couleurDefaut="-fx-background-color: rgba(29,234,0,0.70);";          //On change les couleurs de la case
                        couleurSurvol="-fx-background-color: rgba(20,163,0,0.80);";
                        couleurText="-fx-text-fill: rgba(255,255,255);";
                        bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                    }
                    else {
                        couleurDefaut="-fx-background-color: rgba(29,234,0,0.70);";          //On change les couleurs de la case
                        couleurSurvol="-fx-background-color: rgba(20,163,0,0.80);";
                        couleurText="-fx-text-fill: rgba(255,255,255);";
                        bouton.setStyle(couleurDefaut + couleurText);          //On modifie la couleur du bouton et du text du bouton     
                    }
                }
                else{
                    couleurDefaut="-fx-background-color: rgba(154,0,0,0.50);";          //On change les couleurs de la case
                    couleurSurvol="-fx-background-color: rgba(154,0,0,0.50);";
                    couleurText="-fx-text-fill: rgba(255,0,0);";
                    bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
                }
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de paramétrer la couleur du bouton
     * @param numeroPlateau Le numéro présent sur la grille de tir de l'adversaire
     */
    public void setColor(String numeroPlateau){
        if (plateauRef=='D'){           //Si la lettre de référence du plateau est 'D'
            if (numeroPlateau.equals("0")){         //Si il n'y a jamais eu d'impact sur cette case
                couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";          //On change les couleurs de la case
                couleurSurvol = couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("1")){         //Si il y a eu un impact mais sans toucher de navire
                couleurDefaut="-fx-background-color: rgba(225,225,225,0.80);";          //On change les couleurs de la case
                couleurSurvol= couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("2")){         //Si un navire a été touché
                couleurDefaut="-fx-background-color: rgba(255,16,16,0.80);";          //On change les couleurs de la case
                couleurSurvol= couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("3")){         //Si la fusée éclairante a touché cette case
                couleurDefaut="-fx-background-color: rgba(254,178,107,0.50);";          //On change les couleurs de la case
                couleurSurvol= couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("4")){         //Si un bateau a été touché par la fusée éclairante
                couleurDefaut="-fx-background-color: rgba(255,150,53,0.80);";          //On change les couleurs de la case
                couleurSurvol= couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("5")){         //Si un sous-marin adverse a été touché avec un navire autre qu'un sous-marin
                couleurDefaut="-fx-background-color: rgba(224,249,117,0.80);";          //On change les couleurs de la case
                couleurSurvol= couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
        }
        else{
            if (numeroPlateau.equals("0")){         //Si il n'y a jamais eu d'impact sur cette case
                couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";          //On change les couleurs de la case
                couleurSurvol = "-fx-background-color: rgba(82,127,143,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("1")){         //Si il y a eu un impact mais sans toucher de navire
                couleurDefaut="-fx-background-color: rgba(225,225,225,0.80);";          //On change les couleurs de la case
                couleurSurvol="-fx-background-color: rgba(233,233,233,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("2")){         //Si un navire a été touché
                couleurDefaut="-fx-background-color: rgba(255,16,16,0.80);";          //On change les couleurs de la case
                couleurSurvol="-fx-background-color: rgba(220,15,15,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("3")){         //Si la fusée éclairante a touché cette case
                couleurDefaut="-fx-background-color: rgba(254,178,107,0.50);";          //On change les couleurs de la case
                couleurSurvol="-fx-background-color: rgba(255,163,77,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("4")){         //Si un bateau a été touché par la fusée éclairante
                couleurDefaut="-fx-background-color: rgba(255,150,53,0.80);";          //On change les couleurs de la case
                couleurSurvol="-fx-background-color: rgba(234,132,37,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
            if (numeroPlateau.equals("5")){         //Si un sous-marin adverse a été touché avec un navire autre qu'un sous-marin
                couleurDefaut="-fx-background-color: rgba(224,249,117,0.80);";          //On change les couleurs de la case
                couleurSurvol="-fx-background-color: rgba(197,221,93,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);           //On modifie la couleur du bouton et du text du bouton
            }
        }
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de savoir si l'emplacement du bouton est une nouvelle position possible pour un navire.
     * @param x La position x du bouton
     * @param y La position y du bouton
     * @param pListe La position du navire dans la flotte
     * @return True si cette emplacement est possible, false sinon
     */
    public boolean nouvellePositionPossible(int x, int y, int pListe){
        for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){            //Pour une case donner on vérifier si le navire peut etre déplacer dessus
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]+1==x &&            //Si la case les a cotés d'une case du navire et qu'elle est vide on rentre dans la boucle 
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){         
                return verificationColonne( x+1, y, pListe,'D')==true;          //On vérifie, si c'est nécésaire, que toute la colonne où est situé cette case contient assez de case vide pour déplacer le navire
            }
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]-1==x &&            //Si la case les a cotés d'une case du navire et qu'elle est vide on rentre dans la boucle
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){
                return verificationColonne( x-1, y, pListe,'G')==true;          //On vérifie, si c'est nécésaire, que toute la colonne où est situé cette case contient assez de case vide pour déplacer le navire
            }
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]==x &&            //Si la case les a cotés d'une case du navire et qu'elle est vide on rentre dans la boucle
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]+1==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){
                return verificationColonne( x, y+1, pListe,'B')==true;          //On vérifie, si c'est nécésaire, que toute la colonne où est situé cette case contient assez de case vide pour déplacer le navire
            }
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]==x &&            //Si la case les a cotés d'une case du navire et qu'elle est vide on rentre dans la boucle
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]-1==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){
                return verificationColonne( x, y-1, pListe,'H')==true;          //On vérifie, si c'est nécésaire, que toute la colonne où est situé cette case contient assez de case vide pour déplacer le navire
            }
        }
        return false;
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de savoir si la placement du navire est possible sur toute une collonne
     * @param x La position x du bouton
     * @param y La position y du bouton
     * @param pListe La position du navire dans la flotte
     * @param emplacement La position de la colone par rapport au navire
     * @return True si le positionnement est possible, false sinon
     */
    public boolean verificationColonne(int x, int y, int pListe, char emplacement){
        
        if (JeuGraphique.flotteJoueur0.get(pListe).direction==0){           //Si le navire est à l'horizontal
            if (emplacement == 'D'){            //Si l'emplacement est situé à droite
                return true;            //On return true
            }
            if (emplacement == 'G'){            //Si l'emplacement est situé à gauche
                return true;            //On return true
            }
            if (emplacement == 'B'){            //Si l'emplacement est situé en bas
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];          //On déclare une variable qui stocke la coordonnée de x
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]+1;            //On déclare une variable qui stocke la coordonnée de y+1
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){         //On parcourt la taille du bateau
                    if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){          //Si la case est différent de vide
                        return false;           //On return false
                    }
                }
                return true;            //On renvoie true sinon
            }
            if (emplacement == 'H'){            //Si l'emplacment est situé en haut
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];          //On déclare une variable qui stocke la coordonnée de x
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]-1;            //On déclare une variable qui stocke la coordonnée de y-1
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){         //On parcourt la taille du bateau
                    if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){          //Si la case est différent de vide
                        return false;           //On return false
                    }
                }
                return true;            //On renvoie true sinon
            }
        }
        else{           //Si le navire est à la vertical
            if (emplacement == 'D'){            //Si l'emplacement est situé à droite
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]+1;          //On déclare une variable qui stocke la coordonnée de x+1
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];          //On déclare une variable qui stocke la coordonnée de y
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){         //On parcourt la taille du bateau
                    if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){          //Si la case est différente
                        return false;           //On return false
                    }
                }
                return true;            //On renvoie true sinon
            }
            if (emplacement == 'G'){            //Si l'emplacement est situé à gauche
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]-1;          //On déclare une variable qui stocke la coordonnée de x-1
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];          //On déclare une variable qui stocke la coordonnée de y
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){         //On parcourt la taille du bateau
                    if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){          //Si la case est différente
                        return false;           //On return false
                    }
                }
                return true;            //On renvoie true sinon
            }
            if (emplacement == 'B'){            //Si l'emplacement est situé en bas
                return true;            //On return true
            }
            if (emplacement == 'H'){            //Si l'emplacement est situé en haut
                return true;            //On return true
            }
        }
        return false;
    }
    
}
