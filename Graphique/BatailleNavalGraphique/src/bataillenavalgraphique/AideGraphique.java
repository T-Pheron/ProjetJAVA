/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavalgraphique;

import java.awt.Insets;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Théric PHERON
 */
public class AideGraphique  {

    public AideGraphique(){
        
    }
    
    public void fenetreMenuAide () {
        //Création et parametrage de la fenêtre
        Stage fenetreAide = new Stage();
        fenetreAide.setTitle("Aide");
        fenetreAide.setWidth(1000);
        fenetreAide.setHeight(600); 
        fenetreAide.show();

        StackPane  rootMenuAide = new StackPane();
        
        

        ImageView aideCommencer = new ImageView(getClass().getResource("/images/aideCommencer.png").toString());
        ImageView aideCommencerHover = new ImageView(getClass().getResource("/images/aideCommencerHover.png").toString());
        ImageView aideImage = new ImageView("/images/ampouleAideImage.png");
        aideImage.setX(600);
        aideImage.setY(1100);
        


        Scene scene = new Scene (rootMenuAide);
        fenetreAide.setScene(scene);
        

        DropShadow shadow = new DropShadow();
        FlowPane rootStyleBouton = new FlowPane();
        rootStyleBouton.setPadding(new javafx.geometry.Insets(10));
        rootStyleBouton.setHgap(500);
        rootStyleBouton.setVgap(100);

        
    
        //Bouton pour commencer
        Button boutonPourCommencer = new Button("Pour commencer");
        boutonPourCommencer.setEffect(null);
        boutonPourCommencer.setGraphic(aideCommencerHover);
        boutonPourCommencer.setLayoutX(250);
        boutonPourCommencer.setLayoutY(220);
        
        // Adding the shadow when the mouse cursor is on
        boutonPourCommencer.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            boutonPourCommencer.setEffect(shadow);
            boutonPourCommencer.setGraphic(aideCommencer);
        });
 
        // Removing the shadow when the mouse cursor is off
        boutonPourCommencer.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            boutonPourCommencer.setEffect(null);
            boutonPourCommencer.setGraphic(aideCommencerHover);
        });

       // boutonPourCommencer.setOnAction((ActionEvent eventLancementJeu) -> {
            //AffichageGraphique affichage = new AffichageGraphique();            //Changer ca pour que ca lise dans un fichier text
            //affichage.affichagePlateauGraphique();
            //lancement.close();
        //});
        rootMenuAide.getChildren().addAll(boutonPourCommencer, aideImage);
    }
}
