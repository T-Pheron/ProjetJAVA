package bataillenavalgraphique;

import bataillenaval.controller.Jeu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

/**
 *
 * @author Théric PHERON
 */
public class MenuGraphique{

    public MenuGraphique(){
        
    }
    
    public void menuPrincipal(){
        
        //Déclaration de la fenetre et dimension
        Stage lancement = new Stage();
        lancement.setTitle("Bataille Naval");
        lancement.setWidth(1000);
        lancement.setHeight(600);
        lancement.getIcons().add(new Image("/images/bateau.png")); 

        Jeu jeu = new Jeu();
        
        //Pour aller cherc
        ImageView bateauImage = new ImageView(getClass().getResource("/images/bateau.png").toString());
        ImageView bateauImageHover = new ImageView(getClass().getResource("/images/bateauHover.png").toString());
        ImageView chargerImage = new ImageView(getClass().getResource("/images/loading.png").toString());
        ImageView chargerImageHover = new ImageView(getClass().getResource("/images/loadingHover.png").toString());
        ImageView helpImage = new ImageView(getClass().getResource("/images/help.png").toString());
        ImageView helpImageHover = new ImageView(getClass().getResource("/images/helpHove.png").toString());
        ImageView quitterImage  =new ImageView(getClass().getResource("/images/quitter.png").toString());
        ImageView quitterImageHover =new ImageView(getClass().getResource("/images/quitterHover.png").toString());
        Image imageEcranMenuPrincipal =new Image("/images/fondMenuPrincipal.png");

        
        
        lancement.show();           //Ouverturede de la fênetre

        //On affiche l'image d'arriere plan du jeu
        BackgroundImage fondEcranMenuPrincipal = new BackgroundImage(imageEcranMenuPrincipal, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


        //Bouton pour la nouvelle partie
        Button boutonLancementJeu = new Button("Nouvelle Partie");
        boutonLancementJeu.setGraphic(bateauImage);
        boutonLancementJeu.setOnMouseEntered ((MouseEvent event) -> {
            boutonLancementJeu.setGraphic(bateauImageHover);
        } //evenement lorsqu'on survole le bouton
        );
        boutonLancementJeu.setOnMouseExited ((MouseEvent event) -> {
            boutonLancementJeu.setGraphic(bateauImage);
        });
        boutonLancementJeu.setOnAction((ActionEvent eventLancementJeu) -> {
            try {
                jeu.lancementJeu();
            } catch (InterruptedException | ClassNotFoundException ex) {
                Logger.getLogger(MenuGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        


        Button boutonChargementPartie = new Button("Chargement Partie");
        boutonChargementPartie.setGraphic(chargerImage);
        boutonChargementPartie.setOnMouseEntered ((MouseEvent event) -> {
            boutonChargementPartie.setGraphic(chargerImageHover);
        } //evenement lorsqu'on survole le bouton
        );
        boutonChargementPartie.setOnMouseExited ((MouseEvent event) -> {
            boutonChargementPartie.setGraphic(chargerImage);
        });
        boutonChargementPartie.setOnAction((ActionEvent eventChargementPartie) -> {
            try {
                jeu.lancementJeu();
            } catch (InterruptedException | ClassNotFoundException ex) {
                Logger.getLogger(MenuGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        
        
        
        Button boutonAide = new Button("Aide");
        boutonAide.setGraphic(helpImage);
        boutonAide.setOnMouseEntered ((MouseEvent event) -> {
            boutonAide.setGraphic(helpImageHover);
        } //evenement lorsqu'on survole le bouton
        );
        boutonAide.setOnMouseExited ((MouseEvent event) -> {
            boutonAide.setGraphic(helpImage);
        });
        boutonAide.setOnAction((ActionEvent eventChargementPartie) -> {
            try {
                jeu.lancementJeu();
            } catch (InterruptedException | ClassNotFoundException ex) {
                Logger.getLogger(MenuGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        Button quitter = new Button("Quitter");
        quitter.setGraphic(quitterImage);
        quitter.setOnMouseEntered ((MouseEvent event) -> {
            quitter.setGraphic(quitterImageHover);
        } //evenement lorsqu'on survole le bouton
        );
        quitter.setOnMouseExited ((MouseEvent event) -> {
            quitter.setGraphic(quitterImage);
        });
        quitter.setOnAction((ActionEvent eventQuitter) -> {
            lancement.close();
        });
        
        
        VBox rootMenuGraphique = new VBox(10);
        
        rootMenuGraphique.getChildren().addAll( boutonLancementJeu, boutonChargementPartie,boutonAide, quitter);
        
        
        Scene scene = new Scene(rootMenuGraphique, 300, 250);
        lancement.setScene(scene);
        
        
        
        

    }
    
}
