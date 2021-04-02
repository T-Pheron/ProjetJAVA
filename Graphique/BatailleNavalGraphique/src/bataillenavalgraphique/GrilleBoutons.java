package bataillenavalgraphique;

import bataillenavalgraphique.bataillenaval.model.Plateau;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Théric PHERON
 */
public final class GrilleBoutons {
    
    
    CaseBouton[][] grilleBoutons = new CaseBouton[15][15];
    VBox rootGrilleBoutons = new VBox();
    
    public GrilleBoutons(char plateauRef, List<Integer>  listeInformations){
        for (int y=0; y<15; y++){
            rootGrilleBoutons.getChildren().add(lignePlateau(y, grilleBoutons, plateauRef, listeInformations));
        }
    }
    
    public GrilleBoutons(char plateauRef){
        for (int y=0; y<15; y++){
            rootGrilleBoutons.getChildren().add(lignePlateau(y, grilleBoutons, plateauRef));
        }
    }
    
    
    public HBox lignePlateau(int y,CaseBouton[][] pableauBoutonNavireJoueur, char plateauRef, List<Integer>  listeInformations){
        HBox rootAffichagePlateau = new HBox();
        
        for (int x=0; x<15;x++){
            pableauBoutonNavireJoueur[y][x]= new CaseBouton(y, x, plateauRef, listeInformations);
            rootAffichagePlateau.getChildren().add(pableauBoutonNavireJoueur[y][x].getButton());
        }
        return rootAffichagePlateau;
    }
    
    public HBox lignePlateau(int y,CaseBouton[][] pableauBoutonNavireJoueur, char plateauRef){
        HBox rootAffichagePlateau = new HBox();
        
        for (int x=0; x<15;x++){
            pableauBoutonNavireJoueur[y][x]= new CaseBouton(y, x, plateauRef);
            rootAffichagePlateau.getChildren().add(pableauBoutonNavireJoueur[y][x].getButton());
        }
        return rootAffichagePlateau;
    }
    
    
    
    public VBox getRoot(){
        return rootGrilleBoutons;
    }

    public void miseAJourAffichageNavire(Plateau plateau){
        
        for (int j=0; j<15; j++){
            for (int i=0;i<15;i++){
                if (plateau.get(i,j,0,0)!=(Object) '_'){
                    grilleBoutons[j][i].setTitle((String) (plateau.get(i,j,0,0).toString() + plateau.get(i,j,0,1).toString()));
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));
                }
                else{
                    grilleBoutons[j][i].setTitle("");
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));
                }
            }
        }
    }
    
    public void miseAJourAffichageTirs(Plateau plateau){
        
        for (int j=0; j<15; j++){
            for (int i=0;i<15;i++){
                grilleBoutons[j][i].setColor((String) plateau.get(i,j,1,0));
                if (plateau.get(i,j,1,0).equals("0")) grilleBoutons[j][i].setTitle("");
                if (plateau.get(i,j,1,0).equals("1")) grilleBoutons[j][i].setTitle("");
                if (plateau.get(i,j,1,0).equals("2")) grilleBoutons[j][i].setTitle("XX");
                if (plateau.get(i,j,1,0).equals("3")) grilleBoutons[j][i].setTitle("");
                if (plateau.get(i,j,1,0).equals("4")) grilleBoutons[j][i].setTitle("--");
                if (plateau.get(i,j,1,0).equals("5")) grilleBoutons[j][i].setTitle("--");
            }
        }
    }
    
    public void miseAJourAffichageNavire(Plateau plateau, int pListe){
        
        for (int j=0; j<15; j++){
            for (int i=0;i<15;i++){
                if (plateau.get(i,j,0,0)!=(Object) '_'){
                    grilleBoutons[j][i].setTitle((String) (plateau.get(i,j,0,0).toString() + plateau.get(i,j,0,1).toString()));
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));
                }
                else{
                    grilleBoutons[j][i].setTitle("");
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));
                }
            }
        }
    }
    
    
    public void miseAJourAffichageBouger(Plateau plateau, int pListe, char lRef){
        
        for (int j=0; j<15; j++){
            for (int i=0;i<15;i++){
                if (plateau.get(i,j,0,0)!=(Object) '_'){
                    grilleBoutons[j][i].setTitle((String) (plateau.get(i,j,0,0).toString() + plateau.get(i,j,0,1).toString()));
                    grilleBoutons[j][i].setColor(pListe, (String) plateau.get(i, j, 3, 0), lRef);
                }
                else{
                    grilleBoutons[j][i].setTitle("");
                    grilleBoutons[j][i].setColor(pListe, (String) plateau.get(i, j, 3, 0), lRef);
                }
            }
        }
    }
}
