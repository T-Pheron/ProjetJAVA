package bataillenavalgraphique;

import javafx.scene.layout.*;

/**
 *
 * @author Théric PHERON
 */
public class GrilleNavire{
    
    CaseBouton[] grilleBoutons;
    int taille;
    
    public GrilleNavire(int taille){
         grilleBoutons= new CaseBouton[taille];
         
         this.taille =taille;
    }
    
    public Pane getRoot(int direction, char lRef, int nPlateau){
        
        if (direction ==0){
            HBox rootNavireHorizontale = new HBox();
            for (int i=0; i<taille;i++){
                grilleBoutons[i]= new CaseBouton('D');
                grilleBoutons[i].setTitle((String)(lRef + String.valueOf(nPlateau)));
                grilleBoutons[i].setColor(lRef);
                rootNavireHorizontale.getChildren().add(grilleBoutons[i].getButton());
            }
            AnchorPane rootNavire = new AnchorPane();
            AnchorPane.setTopAnchor(rootNavireHorizontale, 300.0); 
            AnchorPane.setLeftAnchor(rootNavireHorizontale, 250.0);
            rootNavire.getChildren().add(rootNavireHorizontale);
            return rootNavire;
        }
        else{
            VBox rootNavireVertical = new VBox();
            for (int i=0; i<taille; i++){
                grilleBoutons[i]= new CaseBouton('D');
                grilleBoutons[i].setTitle((String)(lRef + String.valueOf(nPlateau)));
                grilleBoutons[i].setColor(lRef);
                rootNavireVertical.getChildren().add(grilleBoutons[i].getButton());
            }
            AnchorPane rootNavire = new AnchorPane();
            AnchorPane.setTopAnchor(rootNavireVertical, 250.0); 
            AnchorPane.setLeftAnchor(rootNavireVertical, 300.0);
            rootNavire.getChildren().add(rootNavireVertical);
            return rootNavire;
        }
    }
}
