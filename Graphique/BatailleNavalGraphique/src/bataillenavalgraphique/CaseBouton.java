package bataillenavalgraphique;


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
    
    int x;
    int y;
    char plateauRef = 'A';
    Button bouton = new Button();
    String couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";
    String couleurSurvol = "-fx-background-color: rgba(82,127,143,0.80);";
    String couleurText = "-fx-text-fill: BLACK ;";
    List<Integer>   listeInformations;
    
    public CaseBouton(char plateauref){
        x=0;
        y=0;
        this.plateauRef= plateauref;
    }
    
    
    public CaseBouton(int y, int x, char plateauRef, List<Integer> listeInformations ){
        this.x=x;
        this.y=y;
        this.plateauRef = plateauRef;
        bouton.setPrefSize(75,75);
        this.listeInformations = listeInformations;
        
        if (this.plateauRef=='D') couleurSurvol=couleurDefaut;
        
        bouton.setStyle(couleurDefaut + couleurText);
        bouton.setOnMouseEntered (e -> bouton.setStyle (couleurSurvol + couleurText));
        bouton.setOnMouseExited (e -> bouton.setStyle (couleurDefaut + couleurText));

        bouton.setOnAction((ActionEvent eventChargementPartie) -> {
            
            //AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
            
            if (plateauRef=='S'){
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                }
            }
            
            if (plateauRef=='B'){
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('0')){
                    try {
                        JeuGraphique.flotteJoueur0.get((int) listeInformations.get(0)).tir(x, y);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CaseBouton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if (plateauRef=='C'){
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                }
            }
            
        });

    }
    
    public CaseBouton(int y, int x, char plateauRef ){
        this.x=x;
        this.y=y;
        this.plateauRef = plateauRef;
        bouton.setPrefSize(75,75);
        
        if (this.plateauRef=='D') couleurSurvol=couleurDefaut;
        
        bouton.setStyle(couleurDefaut + couleurText);
        bouton.setOnMouseEntered (e -> bouton.setStyle (couleurSurvol + couleurText));
        bouton.setOnMouseExited (e -> bouton.setStyle (couleurDefaut + couleurText));

        bouton.setOnAction((ActionEvent eventChargementPartie) -> {
            
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
            
            
            if (plateauRef=='N'){
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                    affichageJeuGraphique.selectionNavire(x,y);
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                    affichageJeuGraphique.erreurCaseVide();
                }
            }
            
            if (plateauRef=='T'){
                if (!JeuGraphique.plateauDeJeu.get(x, y, 1, 0).equals('0')){
                }
            }
            
            
            if (plateauRef=='S'){
                if (!JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                }
                if (JeuGraphique.plateauDeJeu.get(x, y, 0, 0).equals('_')){
                }
            }
            
        });

    }
    

    
    public Button getButton(){
        return bouton;
    }

    public void setTitle(String titre){
        bouton.setText(titre);
    }
    
    public void setColor(char lRef, int nPlateau, String nTir){
        if (plateauRef=='D'){
            if (nTir.equals("1") && lRef!='U'&& lRef!='C' && lRef!='D' && lRef!='S'){               //Si le joueur adversaire à tirer à cette emplacement mais n'a rien touché. On exclu aussi le faite que le joueur est bien pu déplacer son navire sur cette case à un tour n+1
                bouton.setText("XX");              
                couleurDefaut="-fx-background-color: rgba(50,255,215,0.50);";
                couleurText="-fx-text-fill: rgba(160,206,197,0.80);";
                couleurSurvol=couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (nTir.equals("2")){               //Si le joueur adversaire à tirer à cette emplacement et a touché un navire 
                bouton.setText(lRef+ String.valueOf(nPlateau));              
                couleurDefaut="-fx-background-color: rgba(136,0,0,0.50);";
                couleurText="-fx-text-fill: rgba(253,197,197,0.80);";
                couleurSurvol=couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (nTir.equals("5")){               //Si le joueur adversaire à tirer sur un sous marin mais ne la pas coulé parce-que le navire qu'il a tiré n'était pas un sous marin
                bouton.setText(lRef+ String.valueOf(nPlateau));              
                couleurDefaut="-fx-background-color: rgba(0,176,3,0.50);";
                couleurSurvol=couleurDefaut;
                couleurText="-fx-text-fill: rgba(191,255,192,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            
            else if (lRef =='U'){
                couleurDefaut="-fx-background-color: rgba(221,13,13,0.50);";
                couleurText = "-fx-text-fill: BLACK ;";
                couleurSurvol=couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='C'){
                couleurDefaut="-fx-background-color: rgba(203,25,228,0.50);";
                couleurText = "-fx-text-fill: BLACK ;";
                couleurSurvol=couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='D'){
                couleurDefaut="-fx-background-color: rgba(15,141,214,0.50);";
                couleurText = "-fx-text-fill: BLACK ;";
                couleurSurvol=couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='S'){
                couleurDefaut="-fx-background-color: rgba(27,213,41,0.50);";
                couleurText = "-fx-text-fill: BLACK ;";
                couleurSurvol=couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='_'){
                couleurDefaut="-fx-background-color: rgba(120,160,175,0.50);";
                couleurText = "-fx-text-fill: BLACK ;";
                couleurSurvol=couleurDefaut;
                bouton.setStyle(couleurDefaut + couleurText);
            }
        }
        else{
            System.out.println("char"+ lRef+ "int"+ nPlateau+ "String"+ nTir);
            if (nTir.equals("1") && lRef!='U'&& lRef!='C' && lRef!='D' && lRef!='S'){               //Si le joueur adversaire à tirer à cette emplacement mais n'a rien touché. On exclu aussi le faite que le joueur est bien pu déplacer son navire sur cette case à un tour n+1
                bouton.setText("XX");              
                couleurDefaut="-fx-background-color: rgba(50,255,215,0.50);";
                couleurSurvol="-fx-background-color: rgba(50,226,191,0.80);";
                couleurText="-fx-text-fill: rgba(160,206,197,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (nTir.equals("2")){               //Si le joueur adversaire à tirer à cette emplacement et a touché un navire 
                bouton.setText(lRef+ String.valueOf(nPlateau));              
                couleurDefaut="-fx-background-color: rgba(136,0,0,0.50);";
                couleurSurvol="-fx-background-color: rgba(93,0,0,0.80);";
                couleurText="-fx-text-fill: rgba(253,197,197,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (nTir.equals("5")){               //Si le joueur adversaire à tirer sur un sous marin mais ne la pas coulé parce-que le navire qu'il a tiré n'était pas un sous marin
                bouton.setText(lRef+ String.valueOf(nPlateau));              
                couleurDefaut="-fx-background-color: rgba(0,176,3,0.50);";
                couleurSurvol="-fx-background-color: rgba(82,135,2,0.80);";
                couleurText="-fx-text-fill: rgba(191,255,192,0.80);";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='U'){
                couleurDefaut="-fx-background-color: rgba(221,13,13,0.50);";
                couleurSurvol="-fx-background-color: rgba(210,0,0,0.80);";
                couleurText = "-fx-text-fill: BLACK ;";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='C'){
                couleurDefaut="-fx-background-color: rgba(203,25,228,0.50);";
                couleurSurvol="-fx-background-color: rgba(181,25,203,0.80);";
                couleurText = "-fx-text-fill: BLACK ;";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='D'){
                couleurDefaut="-fx-background-color: rgba(15,141,214,0.50);";
                couleurSurvol="-fx-background-color: rgba(12,122,182,0.80);";
                couleurText = "-fx-text-fill: BLACK ;";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            else if (lRef =='S'){
                couleurDefaut="-fx-background-color: rgba(27,213,41,0.50);";
                couleurSurvol="-fx-background-color: rgba(15,185,30,0.80);";
                couleurText = "-fx-text-fill: BLACK ;";
                bouton.setStyle(couleurDefaut + couleurText);
            }
            if (lRef =='_'){
                couleurDefaut="-fx-background-color: rgba(120,160,175,0.50);";
                couleurSurvol="-fx-background-color: rgba(82,127,143,0.80);";
                couleurText = "-fx-text-fill: BLACK ;";
                bouton.setStyle(couleurDefaut + couleurText);
            }
        }
    }
    
    
    public void setColor(String numeroPlateau){
        if (numeroPlateau.equals("0")){
            couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";
            couleurSurvol = "-fx-background-color: rgba(82,127,143,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("1")){
            couleurDefaut="-fx-background-color: rgba(225,225,225,0.80);";
            couleurSurvol="-fx-background-color: rgba(233,233,233,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("2")){
            couleurDefaut="-fx-background-color: rgba(255,16,16,0.80);";
            couleurSurvol="-fx-background-color: rgba(220,15,15,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("3")){
            couleurDefaut="-fx-background-color: rgba(254,178,107,0.50);";
            couleurSurvol="-fx-background-color: rgba(255,163,77,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("4")){
            couleurDefaut="-fx-background-color: rgba(255,150,53,0.80);";
            couleurSurvol="-fx-background-color: rgba(234,132,37,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("5")){
            couleurDefaut="-fx-background-color: rgba(224,249,117,0.80);";
            couleurSurvol="-fx-background-color: rgba(197,221,93,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
    }
    
}
