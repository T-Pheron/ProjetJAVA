package bataillenavalgraphique.model;

import javafx.scene.layout.*;

/**
 * Classe JeuGraphique.
 * Toutes les informations et méthodes principales du jeu.
 * @author Théric PHERON and Joé LHUERRE
 */
public class GrilleNavire{

    //**************************************************************************
    CaseBouton[] grilleBoutons;         //Variable qui stocke les boutons qui représente un navire 
    int taille;         //Variable qui stock la taille du navire
    //**************************************************************************


    //**************************************************************************
    /**
     * Construteur de la classe GrilleNavire.
     * Il permet de créer un object qui va contenir une ligne de bouton ou une collonne de bouton en fonction de la direction et la taille du navire.
     * @param taille La taille du navire.
     */
    public GrilleNavire(int taille){
         grilleBoutons= new CaseBouton[taille];         //On déclare une case de bouton ?
         
         this.taille =taille;           //La taille passé en paramètre devient la taille de ctte méthode
    }
    

    //**************************************************************************
    /**
     * Méthode qui renvoie le root qui réprésente le navire.
     * Elle permet de renvoyer un root avec tous les éléments qui réprésente le navire.
     * @param direction La direction du navire
     * @param lRef La lettre de référence 
     * @param nPlateau Le numéro du navire sur le plateau
     * @param nTir Le numéro sur la grille de tir
     * @return Retourne un root contenant le navire
     */
    public Pane getRoot(int direction, char lRef,int nPlateau, String nTir){
        
        if (direction ==0){         //Si le navire est horizontal
            HBox rootNavireHorizontale = new HBox();            //On déclare un stockage horizontal
            for (int i=0; i<taille;i++){            //On parcourt la taille du navire
                grilleBoutons[i]= new CaseBouton('D');          //On déclare un bouton avec ecrit 'D'
                grilleBoutons[i].setTitle((String)(lRef + String.valueOf(nPlateau)));           //On écrit dans ce bouton la lettre de référence et la valeur du numéro de plateau
                grilleBoutons[i].setColor(lRef,nPlateau,nTir);          //On modifie la couleur de la case suivant sa situation
                rootNavireHorizontale.getChildren().add(grilleBoutons[i].getButton());          //On rajoute au root ces éléments
            }
            AnchorPane rootNavire = new AnchorPane();           //On déclare et paramètre un root de type AnchorPane
            AnchorPane.setTopAnchor(rootNavireHorizontale, 300.0); 
            AnchorPane.setLeftAnchor(rootNavireHorizontale, 250.0);
            rootNavire.getChildren().add(rootNavireHorizontale);
            return rootNavire;          //On return le root
        }
        else{
            VBox rootNavireVertical = new VBox();           //On déclare un affichage vertical
            for (int i=0; i<taille; i++){           //On parcourt la taille du navire
                grilleBoutons[i]= new CaseBouton('D');          //On déclare un bouton avec ecrit 'D'
                grilleBoutons[i].setTitle((String)(lRef + String.valueOf(nPlateau)));           //On écrit dans ce bouton la lettre de référence et la valeur du numéro de plateau
                grilleBoutons[i].setColor(lRef,nPlateau,nTir);          //On modifie la couleur de la case suivant sa situation
                rootNavireVertical.getChildren().add(grilleBoutons[i].getButton());          //On rajoute au root ces éléments
            }
            AnchorPane rootNavire = new AnchorPane();           //On déclare et paramètre un root de type AnchorPane
            AnchorPane.setTopAnchor(rootNavireVertical, 250.0); 
            AnchorPane.setLeftAnchor(rootNavireVertical, 300.0);
            rootNavire.getChildren().add(rootNavireVertical);
            return rootNavire;         //On return le root
        }
    }
}
