package bataillenavalgraphique;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import static javafx.stage.StageStyle.UNDECORATED;

/**
 *
 * @author Théric PHERON
 */
public class MenuGraphique{

    public MenuGraphique(){
        
    }
    
    public void menuPrincipalGraphique(){
        
        //Déclaration de la fenetre et dimension
        Stage lancement = new Stage();
        lancement.setTitle("Bataille Navale");
        lancement.setWidth(1067);
        lancement.setHeight(600);
        lancement.centerOnScreen();
        lancement.setResizable(false);
        lancement.getIcons().add(new Image("/images/iconNaval.png")); 
        lancement.show();           //Ouverturede de la fênetre

        lancement.setScene(sceneLancementMenuPrincipal(lancement));

    }
    

    public Scene sceneLancementMenuPrincipal(Stage stage){
        
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
            choixNiveauIAGraphique();
            stage.close();
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
            stage.setScene(menuAide(stage));
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
            stage.close();
        });
        
        
        VBox rootMenuGraphique = new VBox(10);
        rootMenuGraphique.setPadding(new Insets(280,10, 10,80));
        rootMenuGraphique.getChildren().addAll( boutonLancementJeu, boutonChargementPartie,boutonAide, quitter);
        
        
        Scene scene = new Scene(rootMenuGraphique, 300, 250);
        rootMenuGraphique.setBackground(new Background(fondEcranMenuPrincipal));
        return scene;
    }
    

    public void choixNiveauIAGraphique(){
        //Déclaration de la fenetre et dimension
        Stage lancementChoixNiveauIA = new Stage();
        lancementChoixNiveauIA.setTitle("Bataille Navale - Niveau IA");
        lancementChoixNiveauIA.setWidth(600);
        lancementChoixNiveauIA.setHeight(150);
        lancementChoixNiveauIA.setResizable(false);
        lancementChoixNiveauIA.centerOnScreen();
        lancementChoixNiveauIA.initStyle(UNDECORATED);
        lancementChoixNiveauIA.setOnCloseRequest(event -> event.consume());
        lancementChoixNiveauIA.getIcons().add(new Image("/images/iconNaval.png")); 
        BackgroundImage imageBackground = new BackgroundImage(new Image("/images/fondChoixNiveauIA.png"), 
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        lancementChoixNiveauIA.show();
        
        
        
        Button boutonFacile = new Button("Facile");
        boutonFacile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonFacile.setOnMouseEntered (e-> boutonFacile.setStyle ("-fx-background-color: rgba(82,127,143,0.50)"));
        boutonFacile.setOnMouseExited (e-> boutonFacile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)"));
        boutonFacile.setOnAction((ActionEvent eventLancementJeu) -> {
            try {
                lancementChoixNiveauIA.close();
                JeuGraphique jeuGraphique = new JeuGraphique(1);
                jeuGraphique.lancementJeuGraphique();
            } catch (InterruptedException | ClassNotFoundException ex) {
                Logger.getLogger(MenuGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button boutonMoyen = new Button("Moyen");
        boutonMoyen.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonMoyen.setOnMouseEntered (e-> boutonMoyen.setStyle ("-fx-background-color: rgba(82,127,143,0.50)"));
        boutonMoyen.setOnMouseExited (e-> boutonMoyen.setStyle ("-fx-background-color: rgba(120,160,175,0.50)"));
        boutonMoyen.setOnAction((ActionEvent eventLancementJeu) -> {
            try {
                lancementChoixNiveauIA.close();
                JeuGraphique jeuGraphique = new JeuGraphique(2);
                jeuGraphique.lancementJeuGraphique();
            } catch (InterruptedException | ClassNotFoundException ex) {
                Logger.getLogger(MenuGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button boutonDificile = new Button("Dificile");
        boutonDificile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonDificile.setOnMouseEntered (e-> boutonDificile.setStyle ("-fx-background-color: rgba(82,127,143,0.50)"));
        boutonDificile.setOnMouseExited (e-> boutonDificile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)"));
        boutonFacile.setOnAction((ActionEvent eventLancementJeu) -> {
            try {
                lancementChoixNiveauIA.close();
                JeuGraphique jeuGraphique = new JeuGraphique(3);
                jeuGraphique.lancementJeuGraphique();
            } catch (InterruptedException | ClassNotFoundException ex) {
                Logger.getLogger(MenuGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HBox rootBoutonChoixIA = new HBox(10);
        rootBoutonChoixIA.getChildren().addAll(boutonFacile, boutonMoyen, boutonDificile);
        
        GridPane rootChoixNiveauIA = new GridPane();
        rootChoixNiveauIA.setPadding(new Insets(20));
        rootChoixNiveauIA.setVgap(20);
        
        Label titreNiveauIA = new Label("Veuillez chosir le niveau de l'IA :");
        titreNiveauIA.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 15pt; "
                + "-fx-text-fill: WHITE; "
                + "-fx-font-weight: bold; ");
        rootChoixNiveauIA.add(titreNiveauIA,0,0);
        
        
        rootChoixNiveauIA.add(rootBoutonChoixIA, 0,1);
        rootBoutonChoixIA.setAlignment(CENTER);
        rootChoixNiveauIA.setAlignment(CENTER);
        
        Scene sceneNiveauIA = new Scene(rootChoixNiveauIA);
        rootChoixNiveauIA.setBackground(new Background(imageBackground));
        lancementChoixNiveauIA.setScene(sceneNiveauIA);
    }
    
    
    
    public Scene  menuAide (Stage stage) {

        ImageView retourImage = new ImageView ("/images/retourImage.png");
        ImageView retourImageHover = new ImageView ("/images/retourImageHover.png");
        BackgroundImage fondEcranMenuPrincipal = new BackgroundImage(new Image("/images/fondAide.png"), 
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Button boutonPourCommencer = new Button ("Pour commencer");
        Button boutonFase2Tir = new Button ("La phase de tir");
        Button boutonFlotteFct = new Button ("Flotte et fonctionnalité");
        Button boutonLegendSymb = new Button ("Légende des symboles");
        Button boutonGagnerAstuce = new Button ("Pour gagner et astuces");
        Button boutonRetour = new Button ();

        VBox boutonAide = new VBox();

        boutonPourCommencer.setPrefWidth(1000);
        boutonPourCommencer.setPrefHeight(50);
        boutonPourCommencer.setAlignment(Pos.CENTER_LEFT);
        boutonPourCommencer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonPourCommencer.setOnMouseEntered (e-> 
            boutonPourCommencer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonPourCommencer.setOnMouseExited (e-> 
            boutonPourCommencer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonPourCommencer.setOnAction((ActionEvent eventLancementJeu) -> {});
        
        
        boutonFase2Tir.setPrefWidth(1000);
        boutonFase2Tir.setPrefHeight(50);
        boutonFase2Tir.setAlignment(Pos.CENTER_LEFT);
        boutonFase2Tir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonFase2Tir.setOnMouseEntered (e-> 
            boutonFase2Tir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonFase2Tir.setOnMouseExited (e-> 
            boutonFase2Tir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;"));
        //boutonFase2Tir.setOnAction((ActionEvent eventLancementJeu) -> {});
        
        
        boutonFlotteFct.setPrefWidth(1000);
        boutonFlotteFct.setPrefHeight(50);
        boutonFlotteFct.setAlignment(Pos.CENTER_LEFT);
        boutonFlotteFct.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonFlotteFct.setOnMouseEntered (e-> 
            boutonFlotteFct.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonFlotteFct.setOnMouseExited (e-> 
            boutonFlotteFct.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50); "
                    +                "-fx-font-weight: bold;"));
        boutonFlotteFct.setOnAction((ActionEvent eventLancementJeu) -> {
 
        });
        
        
        boutonLegendSymb.setPrefWidth(1000);
        boutonLegendSymb.setPrefHeight(50);
        boutonLegendSymb.setAlignment(Pos.CENTER_LEFT);
        boutonLegendSymb.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonLegendSymb.setOnMouseEntered (e-> 
            boutonLegendSymb.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonLegendSymb.setOnMouseExited (e-> 
            boutonLegendSymb.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonLegendSymb.setOnAction((ActionEvent eventLancementJeu) -> {});     

        
        boutonGagnerAstuce.setPrefWidth(1000);
        boutonGagnerAstuce.setPrefHeight(50);
        boutonGagnerAstuce.setAlignment(Pos.CENTER_LEFT);
        boutonGagnerAstuce.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonGagnerAstuce.setOnMouseEntered (e-> 
            boutonGagnerAstuce.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonGagnerAstuce.setOnMouseExited (e-> 
            boutonGagnerAstuce.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;"));
        boutonRetour.setOnAction((ActionEvent eventLancementJeu) -> {

        }); 
        //boutonGagnerAstuce.setOnAction((ActionEvent eventLancementJeu) -> {});
        
        
        boutonRetour.setPrefWidth(100);
        boutonRetour.setGraphic(retourImage);
        boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonRetour.setOnMouseEntered (e-> {
            boutonRetour.setGraphic (retourImageHover);
            boutonRetour.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");
                });
        boutonRetour.setOnMouseExited (e-> {
            boutonRetour.setGraphic (retourImage);
            boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
                });
        boutonRetour.setOnAction((ActionEvent eventLancementJeu) -> {
            stage.setScene(sceneLancementMenuPrincipal(stage));
        }); 
        

        
        boutonAide.getChildren().addAll(boutonPourCommencer,boutonFase2Tir,boutonFlotteFct,boutonLegendSymb,boutonGagnerAstuce);

        Label titre = new Label ("Aide");
        titre.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 35pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        GridPane rootMenuAide = new GridPane();
        rootMenuAide.setBackground( new Background(fondEcranMenuPrincipal));
        rootMenuAide.setPadding(new javafx.geometry.Insets(20));
        rootMenuAide.add(titre,0, 0);
        rootMenuAide.add(boutonRetour,0,1);
        rootMenuAide.add(boutonAide,0, 2, 2, 6);
        

        Scene scene = new Scene (rootMenuAide);

        return scene;
    }
    
}
