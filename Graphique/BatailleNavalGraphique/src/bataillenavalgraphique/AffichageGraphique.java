package bataillenavalgraphique;

import bataillenaval.model.Plateau;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
        
public class AffichageGraphique{
    
    int tailleCase=1;
    static CaseBouton[][] pableauBoutonNavireJoueur = new CaseBouton[15][15];
    
    public AffichageGraphique(){

    }

    public void affichagePlateauGraphique() {

        Stage fenetreJeu = new Stage();
        fenetreJeu.setTitle("Bataille Naval");
        fenetreJeu.setWidth(1000);
        fenetreJeu.setHeight(600);
        fenetreJeu.setResizable(false);
        fenetreJeu.getIcons().add(new Image("/images/iconNaval.png")); 
        fenetreJeu.show(); 
        
        GrilleBoutons grilleBoutonBateaux = new GrilleBoutons();
        //Scene sceneBoutonBateaux
        //fenetreJeu.getScene(grilleBoutonBateaux.get)
        
    }

    
    
    public void miseAJourAffichage(Plateau plateau){
        
        for (int i=0; i<15; i++){
            for (int j=0;j<15;j++){
                //plateau[i][j][][]
            }
        }
    }
}
