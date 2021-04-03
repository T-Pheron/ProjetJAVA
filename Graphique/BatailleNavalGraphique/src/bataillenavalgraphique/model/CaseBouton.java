package bataillenavalgraphique.model;


import bataillenavalgraphique.view.AffichageJeuGraphique;
import bataillenavalgraphique.controller.JeuGraphique;
import bataillenavalgraphique.bataillenaval.model.Flotte;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author Théric PHERON
 */
public class CaseBouton {
    
    int x;           //Variable utilisée pour stocker la valeur de l'axe x 
    int y;           //Variable utilisée pour stocker la valeur de l'axe y
    char plateauRef = 'A';          //Variable utilisée pour stocker la référence du plateau qu'on a initialisé à A par défaut
    Button bouton = new Button();           //On déclare un bouton
    String couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";         //On change la couleur
    String couleurSurvol = "-fx-background-color: rgba(82,127,143,0.80);";
    String couleurText = "-fx-text-fill: BLACK ;";
    List<Integer>   listeInformations;          //On déclare un liste d'information
    
    public CaseBouton(char plateauref){
        x=0;            //On initialise à 0 la coordonnée de x par défaut
        y=0;            //On initialise à 0 la coordonnée de y par défaut
        this.plateauRef= plateauref;            //La lettre de référence du plateau passé en paramètre devient celle de cette méthode
    }
    
    
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

        bouton.setOnAction((ActionEvent eventChargementPartie) -> {         //Si l'utilisateur clique on exécute le prgramme 
            
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();          //On déclare un objet de type AffichageJeuGraphique
            
            if (plateauRef=='S'){           //Si la lettre référence du plateau est égale à S
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est différent de '_'
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est égal à '_'
                }
            }
            
            if (plateauRef=='B'){           //Si la lettre référence du plateau est égale à S
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('0')){            //Si le bouton qui se trouve au coordonnée x est différent de '0'
                    try {
                        JeuGraphique.flotteJoueur0.get(listeInformations.get(0)).tir(x, y);         //On tire et on rajoute dans la liste qu'on a tiré a cet emplacement
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CaseBouton.class.getName()).log(Level.SEVERE, null, ex);           //On informe qu'il y a eu une erreur
                    }
                }
            }
            
            if (plateauRef=='C'&&nouvellePositionPossible(x,y,listeInformations.get(0))==true){         //Si la lettre référence du plateau est C et qu'il qu'il y a une nouvelle position possible
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
                    affichageJeuGraphique.selectionNavire(x,y);
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est égal à '_'
                    affichageJeuGraphique.erreurCaseVide();
                }
            }
            
            if (plateauRef=='T'){           //Si la lettre référence du plateau est égale à T
                if (!JeuGraphique.plateauDeJeu.get(x, y, 1, 0).equals('0')){            //Si le bouton qui se trouve au coordonnée x et y est différent de '0'
                }
            }
            
            
            if (plateauRef=='S'){           //Si la lettre référence du plateau est égale à S
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est différent de '_'
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){            //Si le bouton qui se trouve au coordonnée x et y est égal à '_'
                }
            }
            
        });

    }
    

    
    public Button getButton(){
        return bouton;          //On return le bouton
    }

    public void setTitle(String titre){
        bouton.setText(titre);          //On return le bouton avec le text renté en paramètre
    }
    
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
    
    public void setColor(int pListe, String nTirAdverse, char lRef ){
        if (plateauRef=='C'){
                
                if(JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals(lRef) && JeuGraphique.plateauDeJeu.get(x, y, 0, 1)==(Object) Flotte.pListeToNPlateau(lRef, pListe)){
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
                else if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
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
    
    public boolean nouvellePositionPossible(int x, int y, int pListe){
        for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){            //On parcourt le navire
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]+1==x &&
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){         //Si
                return verificationColonne( x+1, y, pListe,'D')==true;
            }
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]-1==x &&
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){
                return verificationColonne( x-1, y, pListe,'G')==true;
            }
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]==x &&
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]+1==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){
                return verificationColonne( x, y+1, pListe,'B')==true;
            }
            if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][0]==x &&
                    JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][1]-1==y && 
                       JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_')){
                return verificationColonne( x, y-1, pListe,'H')==true;
            }
        }
        return false;
    }
    
    public boolean verificationColonne(int x, int y, int pListe, char emplacement){
        
        if (JeuGraphique.flotteJoueur0.get(pListe).direction==0){
            if (emplacement == 'D'){
                return true;
            }
            if (emplacement == 'G'){
                return true;
            }
            if (emplacement == 'B'){
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]+1;
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){
                        return false;
                    }
                }
                return true;
            }
            if (emplacement == 'H'){
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]-1;
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){
                        return false;
                    }
                }
                return true;
            }
        }
        else{
            if (emplacement == 'D'){
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]+1;
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){
                        return false;
                    }
                }
                return true;
            }
            if (emplacement == 'G'){
                int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]-1;
                int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){
                        return false;
                    }
                }
                return true;
            }
            if (emplacement == 'B'){
                return true;
            }
            if (emplacement == 'H'){
                return true;
            }
        }
        return false;
    }
    
}
