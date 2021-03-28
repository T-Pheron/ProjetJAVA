package bataillenavalgraphique;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class AffichageGraphique {
    
    public AffichageGraphique(){

    }

    public int affichagePlateauGraphique() {


        Pane rootAffichagePlateau = new Pane();
        
        rootAffichagePlateau.getChildren().addAll( );

        for (int i=0; i<15;i++){
            for (int j =0; j<15; j++){
                Button boutonLancementJeu = new Button();
                boutonLancementJeu.setOnMouseClicked((MouseEvent event) ->  {return 0;});
                
            }
        }
    }
}
