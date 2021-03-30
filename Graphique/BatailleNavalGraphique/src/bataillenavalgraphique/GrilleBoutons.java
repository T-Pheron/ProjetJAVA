/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavalgraphique;

import bataillenaval.model.Plateau;
import javafx.scene.layout.VBox;

/**
 *
 * @author Théric PHERON
 */
public class GrilleBoutons {
    
    
    CaseBouton[][] grilleBoutons = new CaseBouton[15][15];
    VBox rootGrilleBoutons = new VBox();
    
    public GrilleBoutons(){
        for (int y=0; y<15; y++){
            LignePlateau ligne = new  LignePlateau();
            rootGrilleBoutons.getChildren().add(ligne.lignePlateau(y, grilleBoutons));
        }
    }
    
    public VBox getRoot(){
        return rootGrilleBoutons;
    }

    public void miseAJourAffichage(Plateau plateau){
        
        for (int j=0; j<15; j++){
            for (int i=0;i<15;i++){
                System.out.println(plateau.get(i,j,0,0));
                if (plateau.get(i,j,0,0)!=(Object) '_'){
                    grilleBoutons[j][i].setTitle((String) (plateau.get(i,j,0,0).toString() + plateau.get(i,j,0,1).toString()));
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0));
                }
                else{
                    grilleBoutons[j][i].setTitle("");
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0));
                }
            }
        }
    }
    
}
