package bataillenavalgraphique;

import javafx.scene.layout.HBox;


public class LignePlateau {
    
    
    HBox rootAffichagePlateau = new HBox();
    
    public LignePlateau(){
        
    }
    
    public HBox lignePlateau(int y,CaseBouton[][] pableauBoutonNavireJoueur){
        for (int x=0; x<15;x++){
            pableauBoutonNavireJoueur[x][y]= new CaseBouton(x, y);
            rootAffichagePlateau.getChildren().add(pableauBoutonNavireJoueur[x][y].getButton());
        }
        return rootAffichagePlateau;
    }
}
