/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavalgraphique;

import javafx.scene.layout.VBox;

/**
 *
 * @author Théric PHERON
 */
public class GrilleBoutons {
    
    
    CaseBouton[][] grilleBoutons = new CaseBouton[15][15];
    VBox rootGrilleBoutons = new VBox();
    
    public GrilleBoutons(){
        for (int x=0; x<15; x++){
            LignePlateau ligne = new  LignePlateau();
            rootGrilleBoutons.getChildren().add(ligne.lignePlateau(x, grilleBoutons));
        }
    }
    
    public VBox getRoot(){
        return rootGrilleBoutons;
    }
}
