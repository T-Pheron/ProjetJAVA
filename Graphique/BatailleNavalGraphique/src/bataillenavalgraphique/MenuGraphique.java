package bataillenavalgraphique;

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
        rootChoixNiveauIA.setVgap(25);
        
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
    
    ImageView retourImage = new ImageView ("/images/retourImage.png");
    ImageView retourImageHover = new ImageView ("/images/retourImageHover.png");
    ImageView retourHome = new ImageView ("/images/retourHome.png");
    ImageView retourHomeHover = new ImageView ("/images/retourHomeHover.png");
    BackgroundImage fondEcranMenuPrincipal = new BackgroundImage(new Image("/images/fondAide.png"), 
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    

    public Button boutonRetour (Stage stage, Scene sceneDeRetour){

        Button boutonRetour = new Button ();
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
            stage.setScene(sceneDeRetour);
        }); 

        return boutonRetour;
    }



    public Scene  menuAide (Stage stage) {

        

        Button boutonPourCommencer = new Button ("Pour commencer");
        Button boutonFase2Tir = new Button ("La phase de tir");
        Button boutonFlotteFct = new Button ("Flotte et fonctionnalité");
        Button boutonLegendSymb = new Button ("Légende des symboles");
        Button boutonGagnerAstuce = new Button ("Pour gagner et astuces");

        

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
        boutonPourCommencer.setOnAction((ActionEvent eventLancementJeu) -> {
            stage.setScene(scenePourCommencer(stage));
        });
        
        
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
        boutonGagnerAstuce.setOnAction((ActionEvent eventLancementJeu) -> {

        }); 
        //boutonGagnerAstuce.setOnAction((ActionEvent eventLancementJeu) -> {});

        

        
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
        rootMenuAide.add(boutonRetour(stage, sceneLancementMenuPrincipal(stage)),0,1);
        rootMenuAide.add(boutonAide,0, 2, 2, 6);
        

        Scene scene = new Scene (rootMenuAide);

        return scene;
    }

    public Scene scenePourCommencer (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e->
        boutonRetourMenuPrincipal.setGraphic(retourHome)); 
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        boutonRetourMenuPrincipal.setOnMouseExited (e-> 
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover));
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {
            stage.setScene(sceneLancementMenuPrincipal(stage));
        }); 

        Label labelTitrePourCommencer = new Label ("Pour Commencer");
        labelTitrePourCommencer.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        Label labelPourCommencer = new Label ("COMMENT COMMENCER UNE PARTIE DE BATAILLE NAVALE ?\n Au début du jeu, tout les navires sont placés de manière aléatoire sur la grille.\n"
            +   "Le joueur pourra par la suite les deplacer a sa guise vers le haut ou vers le bas d'une seule case par tour. "
            +   "Le but est de détruire tous les navires de l'adversaire.\n"
            +   "Bien entendu, le joueur ne voit pas la grille de son adversaire.\n"
            +   "Un à un, les joueurs se tire dessus pour détruire les navires ennemis.\n"
            +   "Vous pouvez pas jouer deux fois de suite et doivez attendre le tour du joueur adverse.\n");
            labelPourCommencer.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootPourCommencer = new GridPane();
        rootPourCommencer.setPadding(new javafx.geometry.Insets(20));
        rootPourCommencer.setVgap(20);
        rootPourCommencer.add(labelTitrePourCommencer,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);
        rootPourCommencer.add(rootRetour,0,1);
        rootPourCommencer.add(labelPourCommencer,0,2,3,1);
        rootPourCommencer.setBackground( new Background(fondEcranMenuPrincipal));
        Scene scenePourCommencer = new Scene(rootPourCommencer);
        return scenePourCommencer;
    }

    public Scene sceneFaz2Tir (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e->
        boutonRetourMenuPrincipal.setGraphic(retourHome)); 
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 13pt;"
            +                "-fx-background-color: rgba(82,127,143,0.50);"
            +                "-fx-font-weight: bold;");
        boutonRetourMenuPrincipal.setOnMouseExited (e-> 
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover));
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 13pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {
            stage.setScene(sceneLancementMenuPrincipal(stage));
        }); 

        Label labelTitreFaz2tir = new Label ("Pour tirer");
        labelTitreFaz2tir.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        Label labelFaz2Tir = new Label ("LA PHASE DE TIRE:\n"
            + "le joueur indique la case où il souhaite tirer, par exemple: H7 qui correspond à la case au\n"
            + "croisement de la colonne H et de la ligne 7 sur les côtés des grilles.\n"
            + "Si vous tirez sur un navire ennemi, le jeu vous indiquera si vous l'avez touché.\n"
            + "Si vous ne touchez pas de navire, le jeu vous indiquera si vous l'avez raté.\n"
            + "Si un des navire est entièrement touché, le jeu vous indiquera que vous avez coulé un navire.");
            labelFaz2Tir.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootFaz2Tir = new GridPane();
        rootFaz2Tir.setPadding(new javafx.geometry.Insets(20));
        rootFaz2Tir.setVgap(20);
        rootFaz2Tir.add(labelTitreFaz2tir,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);
        rootFaz2Tir.add(rootRetour,0,1);
        rootFaz2Tir.add(labelFaz2Tir,0,2,3,1);
        rootFaz2Tir.setBackground( new Background(fondEcranMenuPrincipal));
        Scene sceneFaz2Tir = new Scene(rootFaz2Tir);
        return sceneFaz2Tir;
    }




    public Scene sceneFlotteFct (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e-> 
        boutonRetourMenuPrincipal.setGraphic(retourHome));
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;");
        boutonRetourMenuPrincipal.setOnMouseExited (e-> 
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover));
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {
            stage.setScene(sceneLancementMenuPrincipal(stage));
        }); 

        Label labelTitreFlotteFct = new Label ("Pour les navires");
        labelTitreFlotteFct.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        Label labelFlotteFct = new Label ("LES DIFFERENTS NAVIRES ET LEURS FONCTIONNALITEES:"


        +"Il y a 4 types de navires:\n"
        +"\n"
        +"    -Le cuirassé : le plus grand navire de votre flotte (7 cases), il est également le plus puissant.\n" 
        +"                   Lors de ces tires, il touche 2 cases de part et d'autre du point d'impact que vous avez decidé.\n"
        +"                 Il ne peut pas couler les sous-marins.\n"
        +"\n"
        +"    -Le croiseur : Il est un peu plus petit que le cuirassé (5 cases) mais garde les mêmes fonctionnalitées.\n"
        +"                  Lors de ces tires, il touche 1 cases de part et d'autre du point d'impact que vous avez decidé.\n"
        +"                   Il ne peut pas couler les sous-marins.\n"
        +"\n"
        +"    -Le destroyer : Il est petit (3 cases) et ne fait des dégats uniquement au point d'impact que vous avez décidé.\n"
        +"                    Il a cependant une capacité spéciale : Son premier tir est en fait une fusée éclairante.\n"
        +"                    Couvrant une surface de 4*4 cases, vous serez capable de voir si il y a des navires dans cette zone.\n"
        +"                    Le point d'impact de la fusée est tout en haut à gauche de la surface. Puis reprend des tirs normaux \n"
        +"                    la où vous le décidez. Il ne peut pas couler les sous-marins.\n"
        +"\n"                    
        +"    -Le sous-marin : Il est tout petit (1 case) mais est capable de couler tout les navires sur son chemin.\n"
        +"                     Son tir fait également 1 case à l'endroit que vous aurez decidé.\n"
        +"\n"
        +"Dans votre flotte, vous avez 10 navires: 1 cuirassé, 2 croiseurs, 3 destroyers et 4 sous-marins.\n");

            labelFlotteFct.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootFlotteFct = new GridPane();
        rootFlotteFct.setPadding(new javafx.geometry.Insets(20));
        rootFlotteFct.setVgap(20);
        rootFlotteFct.add(labelTitreFlotteFct ,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);
        rootFlotteFct.add(rootRetour,0,1);
        rootFlotteFct.add(labelFlotteFct,0,2,3,1);
        rootFlotteFct.setBackground( new Background(fondEcranMenuPrincipal));
        Scene sceneFlotteFct = new Scene(rootFlotteFct);
        return sceneFlotteFct;
    }



    public Scene sceneLegendSymb (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e-> 
        boutonRetourMenuPrincipal.setGraphic(retourHome));
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        boutonRetourMenuPrincipal.setOnMouseExited (e-> 
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover));
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {
            stage.setScene(sceneLancementMenuPrincipal(stage));
        }); 

        Label labelTitreLegendSymb = new Label ("Pour les symboles");
        labelTitreLegendSymb.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        Label labelLegendSymb = new Label ("LES DIFFERENTS SYMBOLES QUI PEUVENT ETRE PRESENT SUR LA GRILLE:\n"
        +"Légende grille de tir\n"
        + "Il y a deux grilles qui vont vous etres affichées, la grille des navires et la grille des tirs.\n"
        + "Couleur des grilles :\n"
        +"\n"
        + "     -Grille des navires : Jaune\n"
        + "     -Grille des tirs : Bleu marine\n" 
        + "\n"
        + "Les navires ont différentes couleurs suivant leur catégorie.\n"
        + "Couleur des navires :\n"
        + "\n"
        + "     -Cuirassé : Rouge\n"
        + "     -Croiseur : Violet\n"
        + "    -Destroyer : Bleu marine\n"
        + "     -Un sous-marin : Vert\n"
        + "\n"
        
        + "Sur la grille des navires :\n"
        
        + "     -La casse d’un bateau toucher devient rouge foncé avec ses identifiants en rouge.\n"
        + "     -Un sous-marin touché mais non coulé devient cyan avec ses coordonnées en rouge.\n"
        + "     -Un emplacement devient bleu vert avec des croix rouge si un adversaire a tiré dessus.\n"
        + "\n"
        
        + "Sur la grille des tires :\n"
        + "     -Un emplacement cyan avec des croix blanche, signifie que le joueur a tiré dessus mais n’a rien touché\n"
        + "     -Un emplacement cyan, avec des croix rouges, signifie qu’un joueur à tire dessus et à toucher un navire\n"
        + "     -Un emplacement gris avec des tirets, signifie que le joueur a toucher un sous-marin sans utilisé un sous-marin\n"
        + "\n"
        + "Lors du tir de la fusée éclairante :\n"
        + "    -Un emplacement jaune signifie qu’il n’y a rien dessus\n"
        + "     -Un emplacement jaune avec 2 tirets signifie qu’il y a un navire à cet emplacement");
        labelLegendSymb.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootLegendSymb = new GridPane();
        rootLegendSymb.setPadding(new javafx.geometry.Insets(20));
        rootLegendSymb.setVgap(20);
        rootLegendSymb.add(labelTitreLegendSymb,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);
        rootLegendSymb.add(rootRetour,0,1);
        rootLegendSymb.add(labelLegendSymb,0,2,3,1);
        rootLegendSymb.setBackground( new Background(fondEcranMenuPrincipal));
        Scene sceneLegendSymb = new Scene(rootLegendSymb);
        return sceneLegendSymb;
    }

    public Scene sceneGagnerAstuce (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e-> 
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"));
        boutonRetourMenuPrincipal.setOnMouseExited (e->
        boutonRetourMenuPrincipal.setGraphic(retourHome));
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover); 
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {
            stage.setScene(sceneLancementMenuPrincipal(stage));
        }); 

        Label labelTitreGagnerAstuce = new Label ("Pour gagner");
        labelTitreGagnerAstuce.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        Label labelGagnerAstuce = new Label ("COMMENT GAGNER:\n"
        + "Une partie de bataille navale se termine lorsque l’un des joueurs n’a plus de navires ou quand il ne lui reste plus de sous-marin.\n"
        + "\n"
        
        + "PETITE ASTUCE:\n"
        + "Pour gagner plus rapidement, vous pouvez jouer vos tirs en croix,\n"
        + "étant donné que le plus petit navire fait une case alors vous ne\n" 
        + "pourrez éviter aucun autre bateau sur votre chemin.\n"
        + "Cette méthode est infaillible car elle est purement logique.\n");
            labelGagnerAstuce.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootGagnerAstuce = new GridPane();
        rootGagnerAstuce.setPadding(new javafx.geometry.Insets(20));
        rootGagnerAstuce.setVgap(15);
        rootGagnerAstuce.add(labelTitreGagnerAstuce,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);
        rootGagnerAstuce.add(rootRetour,0,1);
        rootGagnerAstuce.add(labelGagnerAstuce,0,2,3,1);
        rootGagnerAstuce.setBackground( new Background(fondEcranMenuPrincipal));
        Scene sceneGagnerAstuce = new Scene(rootGagnerAstuce);
        return sceneGagnerAstuce;
    }
    
}
