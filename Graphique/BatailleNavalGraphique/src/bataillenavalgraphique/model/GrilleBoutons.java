package bataillenavalgraphique.model;

import bataillenavalgraphique.bataillenaval.model.Plateau;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Théric PHERON
 */
public final class GrilleBoutons {
    
    
    CaseBouton[][] grilleBoutons = new CaseBouton[15][15];          //On déclare un grille de bouton de 15*15 cases
    VBox rootGrilleBoutons = new VBox();            //On déclare un affichage vertical
    
    
    public GrilleBoutons(char plateauRef, List<Integer>  listeInformations){
        rootGrilleBoutons.getChildren().addAll(ligneCoordonnees(plateauRef, 'A'));
        for (int y=0; y<15; y++){           //On parcourt l'axe des ordonnées
            rootGrilleBoutons.getChildren().add(lignePlateau(y, grilleBoutons, plateauRef, listeInformations));         //On rajoute ces éléments au root de la grille
        }
    }
    
    public GrilleBoutons(char plateauRef, char lRef){
        rootGrilleBoutons.getChildren().addAll(ligneCoordonnees(plateauRef, lRef));
        for (int y=0; y<15; y++){           //On parcourt l'axe des ordonnées
            rootGrilleBoutons.getChildren().add(lignePlateau(y, grilleBoutons, plateauRef, lRef));            //On rajoute ces éléments dans le root de la grille
        }
    }
    
    public HBox ligneCoordonnees(char plateauRef, char lRef){
        HBox ligneCoordones = new HBox();
        String couleurDefaut = couleurBord(plateauRef, lRef);         //On change la couleur
        
        Button boutonVide = new Button();
        boutonVide.setPrefSize(75,75);
        boutonVide.setStyle(couleurDefaut);           //On met une couleur par défaut et on donne un couleur au text
        ligneCoordones.getChildren().add(boutonVide);

        for (int x=0; x<15; x++){
            Button bouton = new Button(Character.toString((char) (x+65)));
            bouton.setPrefSize(75,75);
            bouton.setStyle(couleurDefaut);           //On met une couleur par défaut et on donne un couleur au text
            ligneCoordones.getChildren().add(bouton);
        }
        return ligneCoordones;
    }

    public Button colloneCoordonnees(char plateauRef, int numeroLigne, char lRef){
        String couleurDefaut = couleurBord(plateauRef, lRef);         //On change la couleur

        Button bouton = new Button(String.valueOf(numeroLigne +1 ));
        bouton.setPrefSize(75,75);
        bouton.setStyle(couleurDefaut);           //On met une couleur par défaut et on donne un couleur au text
        
        return bouton;
    }

    
    public HBox lignePlateau(int y,CaseBouton[][] pableauBoutonNavireJoueur, char plateauRef, List<Integer>  listeInformations){
        HBox rootAffichagePlateau = new HBox();         //On déclare un affichage horizontal
        rootAffichagePlateau.getChildren().add(colloneCoordonnees(plateauRef, y, 'A'));
        for (int x=0; x<15;x++){            //On parcourt l'axe des abscisses
            pableauBoutonNavireJoueur[y][x]= new CaseBouton(y, x, plateauRef, listeInformations);           //On déclare des boutons a chaque valeur de x
            rootAffichagePlateau.getChildren().add(pableauBoutonNavireJoueur[y][x].getButton());            //On rajoute les boutons au root
        }
        return rootAffichagePlateau;            //On return le root
    }
    
    public HBox lignePlateau(int y,CaseBouton[][] pableauBoutonNavireJoueur, char plateauRef, char lRef){
        HBox rootAffichagePlateau = new HBox();
        rootAffichagePlateau.getChildren().add(colloneCoordonnees(plateauRef, y, lRef));
        for (int x=0; x<15;x++){            //On parcourt l'axe des abscisses
            pableauBoutonNavireJoueur[y][x]= new CaseBouton(y, x, plateauRef);           //On déclare des boutons a chaque valeur de x
            rootAffichagePlateau.getChildren().add(pableauBoutonNavireJoueur[y][x].getButton());            //On rajoute les boutons au root
        }
        return rootAffichagePlateau;            //On return le root
    }
    
    
    
    public VBox getRoot(){
        return rootGrilleBoutons;           //On return le root
    }

    public void miseAJourAffichageNavire(Plateau plateau){
        
        for (int j=0; j<15; j++){           //On parcourt l'axe des ordonnées
            for (int i=0;i<15;i++){            //On parcourt l'axe des abscisses
                if (plateau.get(i,j,0,0)!=(Object) '_'){            //Si la case du plateau est different de '_'
                    grilleBoutons[j][i].setTitle((String) (plateau.get(i,j,0,0).toString() + plateau.get(i,j,0,1).toString()));         //?
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));            //Affiche un couleur sur le bouton suivant ce qui est dedans ?
                }
                else{
                    grilleBoutons[j][i].setTitle("");           //?
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));            //Affiche un couleur sur le bouton suivant ce qui est dedans ?
                }
            }
        }
    }
    
    public void miseAJourAffichageTirs(Plateau plateau){
        
        for (int j=0; j<15; j++){           //On parcourt l'axe des ordonnées
            for (int i=0;i<15;i++){            //On parcourt l'axe des abscisses
                grilleBoutons[j][i].setColor((String) plateau.get(i,j,1,0));            //Affiche un couleur sur le bouton suivant ce qui est dedans ?
                if (plateau.get(i,j,1,0).equals("0")) grilleBoutons[j][i].setTitle("");         //Affiche des symboles suivants la situation qui se trouve dans la case
                if (plateau.get(i,j,1,0).equals("1")) grilleBoutons[j][i].setTitle("");
                if (plateau.get(i,j,1,0).equals("2")) grilleBoutons[j][i].setTitle("XX");
                if (plateau.get(i,j,1,0).equals("3")) grilleBoutons[j][i].setTitle("");
                if (plateau.get(i,j,1,0).equals("4")) grilleBoutons[j][i].setTitle("--");
                if (plateau.get(i,j,1,0).equals("5")) grilleBoutons[j][i].setTitle("--");
            }
        }
    }
    
    public void miseAJourAffichageNavire(Plateau plateau, int pListe){
        
        for (int j=0; j<15; j++){           //On parcourt l'axe des ordonnées
            for (int i=0;i<15;i++){            //On parcourt l'axe des abscisses
                if (plateau.get(i,j,0,0)!=(Object) '_'){            //Si la case du plateau est different de '_'
                    grilleBoutons[j][i].setTitle((String) (plateau.get(i,j,0,0).toString() + plateau.get(i,j,0,1).toString()));         //On paramètre le texte du bouton en fonction des élément présent dans le tableau
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));         //On paramètre la couleur en fonction de ce qui est présent dans le plateau
                }
                else{
                    grilleBoutons[j][i].setTitle("");
                    grilleBoutons[j][i].setColor((char) plateau.get(i,j,0,0),(int) plateau.get(i,j,0,1), (String) plateau.get(i, j, 3, 0));
                }
            }
        }
    }
    
    
    public void miseAJourAffichageBouger(Plateau plateau, int pListe, char lRef){
        
        for (int j=0; j<15; j++){           //On parcourt l'axe des ordonnées
            for (int i=0;i<15;i++){            //On parcourt l'axe des abscisses
                if (plateau.get(i,j,0,0)!=(Object) '_'){            //Si la case du plateau est different de '_'
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

    public String couleurBord(char plateauRef, char lRef){
        
        if (lRef=='A'){         //Si la lettre de référence est 'A' par défaut
            switch (plateauRef) {           //On va retourner une couleur pour le bord du plateau suivant la situation
                case'N':            //Le plateau de sélection des navire
                    return "-fx-background-color: rgba(234,213,0,0.80);";
                
                case'T':            //Le plateau de sélection d'une case de tir
                    return "-fx-background-color: rgba(0,220,230,0.80);";
                
                case'S':            //Le plateau de sélection d'un navire après avoir sélectionner une case de tir
                    return "-fx-background-color: rgba(245,17,17,0.80);";
    
                case'B':            //Le plateau de sélection d'une case de tir après sélection du navire
                    return "-fx-background-color: rgba(245,17,17,0.80);";
    
                case'C':            //Le plateau de sélection d'une case pour bouger un navire
                    return "-fx-background-color: rgba(200,0,0,0.80);";                    
    
                default: return "-fx-background-color: rgba(120,160,175,0.80);";
            }
        }
        else{
            switch (lRef) {         //La lettre de référence du navire
                case'U':            //Cas où le bateau selctionné est un cuirassé
                    return "-fx-background-color: rgba(221,13,13,0.80);";
                
                case'C':            //Cas où le bateau sélectionner est un croisseur
                    return "-fx-background-color: rgba(203,25,228,0.80);";
                
                case'D':            //Cas où le bateau sélectionner est un destroyer
                    return "-fx-background-color: rgba(15,141,214,0.80);";
    
                case'S':            //Cas où le bateau sélectionner est un sous-marin
                    return "-fx-background-color: rgba(27,213,41,0.80);"; 
                
                case'V':            //La plateau quand une tache à bien été exécuté
                    return "-fx-background-color: rgba(19,222,19,0.80);";
    
                case'F':            //Le plateau pour le tir d'une fussée éclairante
                    return "-fx-background-color: rgba(255,195,0,0.80);";              
    
                default: return "-fx-background-color: rgba(120,160,175,0.80);";
            }
        }
    }
}
