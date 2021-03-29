package bataillenavalgraphique;


import javafx.event.*;
import javafx.geometry.Insets;
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
        lancement.setWidth(1067);
        lancement.setHeight(600);
        lancement.getIcons().add(new Image("/images/bateau.png")); 
        lancement.show();           //Ouverturede de la fênetre
        

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


        

        //Variable qui stock l'arrière plan
        BackgroundImage fondEcranMenuPrincipal = new BackgroundImage(imageEcranMenuPrincipal, 
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


        //Bouton pour la nouvelle partie
        Button boutonLancementJeu = new Button("Nouvelle Partie");
        boutonLancementJeu.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonLancementJeu.setGraphic(bateauImage);
        boutonLancementJeu.setOnMouseEntered ((MouseEvent event) -> {
            boutonLancementJeu.setGraphic(bateauImageHover);
            boutonLancementJeu.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");
        } //evenement lorsqu'on survole le bouton
        );
        boutonLancementJeu.setOnMouseExited ((MouseEvent event) -> {
            boutonLancementJeu.setGraphic(bateauImage);
            boutonLancementJeu.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
            
        });
        boutonLancementJeu.setOnAction((ActionEvent eventLancementJeu) -> {
            AffichageGraphique affichage = new AffichageGraphique();
            affichage.affichagePlateauGraphique();
            lancement.close();
        });
        


        Button boutonChargementPartie = new Button("Chargement Partie");
        boutonChargementPartie.setGraphic(chargerImage);
        boutonChargementPartie.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");
        boutonChargementPartie.setOnMouseEntered ((MouseEvent event) -> {
            boutonChargementPartie.setGraphic(chargerImageHover);
            boutonChargementPartie.setStyle ("-fx-background-color: rgba(130,170,182,0.50)");
        });
            boutonChargementPartie.setOnMouseExited ((MouseEvent event) -> {
            boutonChargementPartie.setGraphic(chargerImage);
            boutonChargementPartie.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonChargementPartie.setOnAction((ActionEvent eventChargementPartie) -> {
            System.out.println("On est la c'est la rue");
        });

        
        
        
        Button boutonAide = new Button("Aide");
        boutonAide.setGraphic(helpImage);
        boutonAide.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");
        boutonAide.setOnMouseEntered ((MouseEvent event) -> {
            boutonAide.setGraphic(helpImageHover);
            boutonAide.setStyle ("-fx-background-color: rgba(130,170,182,0.50)");
        });
        boutonAide.setOnMouseExited ((MouseEvent event) -> {
            boutonAide.setGraphic(helpImage);
            boutonAide.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonAide.setOnAction((ActionEvent eventChargementPartie) -> {
            AideGraphique aide = new AideGraphique();
            aide.fenetreMenuAide();
            lancement.close();
        });
        
        
        Button quitter = new Button("Quitter");
        quitter.setGraphic(quitterImage);
        quitter.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");
        quitter.setOnMouseEntered ((MouseEvent event) -> {
            quitter.setGraphic(quitterImageHover);
            quitter.setStyle ("-fx-background-color: rgba(130,170,182,0.50)");
        } //evenement lorsqu'on survole le bouton
        );
        quitter.setOnMouseExited ((MouseEvent event) -> {
            quitter.setGraphic(quitterImage);
            quitter.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");
        });
        quitter.setOnAction((ActionEvent eventQuitter) -> {
            lancement.close();
        });
        
        
        VBox rootMenuGraphique = new VBox(10);
        rootMenuGraphique.setPadding(new Insets(280,10, 10,80));
        rootMenuGraphique.getChildren().addAll( boutonLancementJeu, boutonChargementPartie,boutonAide, quitter);
        
        
        Scene scene = new Scene(rootMenuGraphique, 300, 250);
        rootMenuGraphique.setBackground(new Background(fondEcranMenuPrincipal));
        lancement.setScene(scene);
        
        
        
        

    }
    
}
