package bataillenavalgraphique.view;

import bataillenavalgraphique.controller.JeuGraphique;
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
 * Classe MenuGrapphique.
 * Classe utiliser pour l'affichage du menu de lancement du jeu et l'intereaction avec le joueur. 
 * @author Théric PHERON and Joé LHUERRE
 */
public class MenuGraphique{

    //**************************************************************************
    /**
     * Constructeur de la classe MenuGraphique
     */
    public MenuGraphique(){
        
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de créer une fenêtre pour le menu.
     */
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

        lancement.setScene(sceneLancementMenuPrincipal(lancement));           //On modifie et affiche la scène

    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de créer la scène de lancement du menu.
     * @param stage La fenêtre d'affichage
     * @return La scène du menu principal
     */
    public Scene sceneLancementMenuPrincipal(Stage stage){
        
        //On donne l'emplacement de l'image
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
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);           //On met en arriere plan une image


        //Bouton pour la nouvelle partie
        Button boutonLancementJeu = new Button("Nouvelle Partie");
        boutonLancementJeu.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonLancementJeu.setGraphic(bateauImage);            //On l'illustre par une petite image
        boutonLancementJeu.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonLancementJeu.setGraphic(bateauImageHover);            //On l'illustre par une petite image
            boutonLancementJeu.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");          //On change les caractéristiques d'écriture
        });
        boutonLancementJeu.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonLancementJeu.setGraphic(bateauImage);            //On l'illustre par une petite image
            boutonLancementJeu.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
            
        });
        boutonLancementJeu.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            choixNiveauIAGraphique();           //On lance le choix du niveau de l'IA
            stage.close();          //On ferme la fenètre
        });
        


        Button boutonChargementPartie = new Button("Chargement Partie");            //On déclare le bouton pour charger la partie
        boutonChargementPartie.setGraphic(chargerImage);            //On l'illustre par une petite image
        boutonChargementPartie.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");          //On change les caractéristiques d'écriture
        boutonChargementPartie.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonChargementPartie.setGraphic(chargerImageHover);            //On l'illustre par une petite image
            boutonChargementPartie.setStyle ("-fx-background-color: rgba(130,170,182,0.50)");          //On change les caractéristiques d'écriture
        });
            boutonChargementPartie.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonChargementPartie.setGraphic(chargerImage);            //On l'illustre par une petite image
            boutonChargementPartie.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");          //On change les caractéristiques d'écriture
        });
        boutonChargementPartie.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur clique sur le bouton
            AffichageSauvegardeGraphique affichageSauvegarde = new AffichageSauvegardeGraphique();  
            try {
                affichageSauvegarde.lancementChargement(stage);         //On lance l'affichage du charegement de partie
            } catch (ClassNotFoundException ex) {
                System.err.println("Erreur! Lancement chargement partie échoué");           //On affiche un message d'erreur 
            }
        });

        
        
        
        Button boutonAide = new Button("Aide");            //On déclare le bouton pour l'aide
        boutonAide.setGraphic(helpImage);            //On l'illustre par une petite image
        boutonAide.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");          //On change les caractéristiques d'écriture
        boutonAide.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonAide.setGraphic(helpImageHover);            //On l'illustre par une petite image
            boutonAide.setStyle ("-fx-background-color: rgba(130,170,182,0.50)");          //On change les caractéristiques d'écriture
        });
        boutonAide.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonAide.setGraphic(helpImage);            //On l'illustre par une petite image
            boutonAide.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");          //On change les caractéristiques d'écriture
        });
        boutonAide.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(menuAide(stage));            //On modifie et affiche la scène
        });
        
        
        Button quitter = new Button("Quitter");            //On déclare le bouton pour quitter
        quitter.setGraphic(quitterImage);            //On l'illustre par une petite image
        quitter.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");          //On change les caractéristiques d'écriture
        quitter.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            quitter.setGraphic(quitterImageHover);            //On l'illustre par une petite image
            quitter.setStyle ("-fx-background-color: rgba(130,170,182,0.50)");          //On change les caractéristiques d'écriture
        } 
        );
        quitter.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            quitter.setGraphic(quitterImage);            //On l'illustre par une petite image
            quitter.setStyle ("-fx-background-color: rgba(163,198,211,0.50)");          //On change les caractéristiques d'écriture
        });
        quitter.setOnAction((ActionEvent eventQuitter) -> {         //Action si le joueur clique sur le bouton
            stage.close();              //On ferme la fenètre
        });
        
        
        VBox rootMenuGraphique = new VBox(10);         //On déclare un affichage vertical où les éléments sont espacé de 10 pixels
        rootMenuGraphique.setPadding(new Insets(280,10, 10,80));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootMenuGraphique.getChildren().addAll( boutonLancementJeu, boutonChargementPartie,boutonAide, quitter);             //On rajoute des éléments a ce root
        
        
        Scene scene = new Scene(rootMenuGraphique, 300, 250);           //On ajoute ce root à la scène
        rootMenuGraphique.setBackground(new Background(fondEcranMenuPrincipal));           //On met en arriere plan une image
        return scene;
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de choisir le niveau de l'IA.
     */
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
        lancementChoixNiveauIA.getIcons().add(new Image("/images/iconNaval.png"));          //On rajoute une icone à la fenètre
        BackgroundImage imageBackground = new BackgroundImage(new Image("/images/fondChoixNiveauIA.png"), 
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);           //On met en arriere plan une image
        lancementChoixNiveauIA.show();           //Ouverturede de la fênetre
        
        
        
        Button boutonFacile = new Button("Facile");            //On déclare le bouton pour le niveau facile
        boutonFacile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonFacile.setOnMouseEntered (e-> boutonFacile.setStyle ("-fx-background-color: rgba(82,127,143,0.50)"));          //On change les caractéristiques d'écriture si le joueur met son curseur sur le bouton
        boutonFacile.setOnMouseExited (e-> boutonFacile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)"));          //On change les caractéristiques d'écriture si le joueur enlève son curseur du bouton
        boutonFacile.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            try {
                lancementChoixNiveauIA.close();         //On ferme la fenètre
                JeuGraphique jeuGraphique = new JeuGraphique(1);
                jeuGraphique.lancementJeuGraphique();           //On lance 
            } catch (InterruptedException | ClassNotFoundException ex) {
                System.err.println("Erreur! Lancement de la partie a échoué");           //On affiche un message d'erreur 
            }
        });
        
        Button boutonMoyen = new Button("Moyen");            //On déclare le bouton pour le niveau moyen
        boutonMoyen.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonMoyen.setOnMouseEntered (e-> boutonMoyen.setStyle ("-fx-background-color: rgba(82,127,143,0.50)"));          //On change les caractéristiques d'écriture si le joueur met son curseur sur le bouton
        boutonMoyen.setOnMouseExited (e-> boutonMoyen.setStyle ("-fx-background-color: rgba(120,160,175,0.50)"));          //On change les caractéristiques d'écriture si le joueur enlève son curseur du bouton
        boutonMoyen.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            try {
                lancementChoixNiveauIA.close();         //On ferme la fenètre
                JeuGraphique jeuGraphique = new JeuGraphique(2);
                jeuGraphique.lancementJeuGraphique();           //On lance le jeu graphique
            } catch (InterruptedException | ClassNotFoundException ex) {
                System.err.println("Erreur! Lancement de la partie a échoué");           //On affiche un message d'erreur
            }
        });
        
        Button boutonDificile = new Button("Dificile");            //On déclare le bouton pour le niveau difficile
        boutonDificile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonDificile.setOnMouseEntered (e-> boutonDificile.setStyle ("-fx-background-color: rgba(82,127,143,0.50)"));          //On change les caractéristiques d'écriture si le joueur met son curseur sur le bouton
        boutonDificile.setOnMouseExited (e-> boutonDificile.setStyle ("-fx-background-color: rgba(120,160,175,0.50)"));          //On change les caractéristiques d'écriture si le joueur enlève son curseur du bouton
        boutonDificile.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            try {
                lancementChoixNiveauIA.close();         //On ferme la fenètre
                JeuGraphique jeuGraphique = new JeuGraphique(3);
                jeuGraphique.lancementJeuGraphique();           //On lance le jeu graphique
            } catch (InterruptedException | ClassNotFoundException ex) {
                System.err.println("Erreur! Lancement de la partie a échoué");           //On affiche un message d'erreur
            }
        });
        
        HBox rootBoutonChoixIA = new HBox(10);
        rootBoutonChoixIA.getChildren().addAll(boutonFacile, boutonMoyen, boutonDificile);             //On rajoute des éléments a ce root
        
        GridPane rootChoixNiveauIA = new GridPane();
        rootChoixNiveauIA.setPadding(new Insets(20));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootChoixNiveauIA.setVgap(25);
        
        Label titreNiveauIA = new Label("Veuillez chosir le niveau de l'IA :");         //On demande au joueur de choisir le niveau de difficulté de l'IA
        titreNiveauIA.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 15pt; "
                + "-fx-text-fill: WHITE; "
                + "-fx-font-weight: bold; ");
        rootChoixNiveauIA.add(titreNiveauIA,0,0);
        
        
        rootChoixNiveauIA.add(rootBoutonChoixIA, 0,1);
        rootBoutonChoixIA.setAlignment(CENTER);            //On positionne l'élément au centre
        rootChoixNiveauIA.setAlignment(CENTER);            //On positionne l'élément au centre
        
        Scene sceneNiveauIA = new Scene(rootChoixNiveauIA);           //On ajoute ce root à la scène
        rootChoixNiveauIA.setBackground(new Background(imageBackground));           //On met en arriere plan une image
        lancementChoixNiveauIA.setScene(sceneNiveauIA);           //On modifie et affiche la scène
    }
    
    ImageView retourImage = new ImageView ("/images/retourImage.png");            //On donne l'emplacement de l'image
    ImageView retourImageHover = new ImageView ("/images/retourImageHover.png");
    ImageView retourHome = new ImageView ("/images/retourHome.png");
    ImageView retourHomeHover = new ImageView ("/images/retourHomeHover.png");
    BackgroundImage fondEcranMenuPrincipal = new BackgroundImage(new Image("/images/fondAide.png"), 
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);           //On met en arriere plan une image


    //**************************************************************************   
    /**
     * Méthode qui permet de créer un bouton retour.
     * @param stage Fenêtre d'affichage
     * @param sceneDeRetour La scène qu'il faut afficher en cas de retour
     * @return Le bouton retour
     */
    public Button boutonRetour (Stage stage, Scene sceneDeRetour){

        Button boutonRetour = new Button ();            //On déclare le bouton pour retourner au menu précédent
        boutonRetour.setPrefWidth(100);
        boutonRetour.setGraphic(retourImage);            //On l'illustre par une petite image
        boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonRetour.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
            boutonRetour.setGraphic (retourImageHover);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");          //On change les caractéristiques d'écriture
        });
        boutonRetour.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonRetour.setGraphic (retourImage);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        });
        boutonRetour.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneDeRetour);           //On modifie et affiche la scène
        }); 

        return boutonRetour;
    }


    //**************************************************************************
    /**
     * Méthode qui permet de créer la scène du menu aide.
     * @param stage La fenêtre d'affichage
     * @return La scène à afficher
     */
    public Scene  menuAide (Stage stage) {

        
        //On déclare les bouton pour aller dans les différentes parties de l'aide
        Button boutonPourCommencer = new Button ("Pour commencer");
        Button boutonFase2Tir = new Button ("La phase de tir");
        Button boutonFlotteFct = new Button ("Flotte et fonctionnalité");
        Button boutonLegendSymb = new Button ("Légende des symboles");
        Button boutonGagnerAstuce = new Button ("Pour gagner et astuces");

        

        VBox boutonAide = new VBox();         //On déclare un affichage vertical pour les différentes parties de l'aide

        boutonPourCommencer.setPrefWidth(1000);         //On dimensionne le bouton
        boutonPourCommencer.setPrefHeight(50);
        boutonPourCommencer.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
        boutonPourCommencer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonPourCommencer.setOnMouseEntered (e->  {       //Si le joueur met son curseur sur le bouton
            boutonPourCommencer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonPourCommencer.setOnMouseExited (e->   {          //Si le joueur enlève son curseur du bouton
            boutonPourCommencer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonPourCommencer.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(scenePourCommencer(stage));           //On modifie et affiche la scène
        });
        
        
        boutonFase2Tir.setPrefWidth(1000);         //On dimensionne le bouton
        boutonFase2Tir.setPrefHeight(50);
        boutonFase2Tir.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
        boutonFase2Tir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonFase2Tir.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
            boutonFase2Tir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;");
        });

        boutonFase2Tir.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonFase2Tir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonFase2Tir.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneFaz2Tir(stage));           //On modifie et affiche la scène
        });
        
        
        boutonFlotteFct.setPrefWidth(1000);         //On dimensionne le bouton
        boutonFlotteFct.setPrefHeight(50);
        boutonFlotteFct.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
        boutonFlotteFct.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonFlotteFct.setOnMouseEntered (e->  {         //Si le joueur met son curseur sur le bouton
            boutonFlotteFct.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonFlotteFct.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonFlotteFct.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50); "
                    +                "-fx-font-weight: bold;");
        });
        boutonFlotteFct.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneFlotteFct(stage));           //On modifie et affiche la scène
        });
        
        
        boutonLegendSymb.setPrefWidth(1000);         //On dimensionne le bouton
        boutonLegendSymb.setPrefHeight(50);
        boutonLegendSymb.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
        boutonLegendSymb.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonLegendSymb.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
            boutonLegendSymb.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonLegendSymb.setOnMouseExited (e->  {           //Si le joueur enlève son curseur du bouton
            boutonLegendSymb.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonLegendSymb.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneLegendSymb(stage));           //On modifie et affiche la scène
        });     

        
        boutonGagnerAstuce.setPrefWidth(1000);         //On dimensionne le bouton
        boutonGagnerAstuce.setPrefHeight(50);
        boutonGagnerAstuce.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
        boutonGagnerAstuce.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);"
                +                "-fx-font-weight: bold;");
        boutonGagnerAstuce.setOnMouseEntered (e->  {          //Si le joueur met son curseur sur le bouton
            boutonGagnerAstuce.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonGagnerAstuce.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonGagnerAstuce.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonGagnerAstuce.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneGagnerAstuce(stage));           //On modifie et affiche la scène
        });

        

        
        boutonAide.getChildren().addAll(boutonPourCommencer,boutonFase2Tir,boutonFlotteFct,boutonLegendSymb,boutonGagnerAstuce);             //On rajoute des éléments a ce root

        Label titre = new Label ("Aide");         //On informe dans quel endroit on se trouve
        titre.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 35pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        GridPane rootMenuAide = new GridPane();
        rootMenuAide.setBackground( new Background(fondEcranMenuPrincipal));           //On met en arriere plan une image
        rootMenuAide.setPadding(new javafx.geometry.Insets(20));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootMenuAide.add(titre,0, 0);             //On rajoute des éléments a ce root
        rootMenuAide.add(boutonRetour(stage, sceneLancementMenuPrincipal(stage)),0,1);
        rootMenuAide.add(boutonAide,0, 2, 2, 6);
        

        Scene scene = new Scene (rootMenuAide);           //On ajoute ce root à la scène

        return scene;
    }


    //**************************************************************************
    /**
     * Méthode qui permet de créer la scène du menu aide - Pour commencer
     * @param stage La fenêtre d'affichage
     * @return Le scène à afficher
     */
    public Scene scenePourCommencer (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");            //On déclare le bouton pour aller au menu principal
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e->  {         //Si le joueur met son curseur sur le bouton
        boutonRetourMenuPrincipal.setGraphic(retourHome);             //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        });
        boutonRetourMenuPrincipal.setOnMouseExited (e->   {          //Si le joueur enlève son curseur du bouton
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        });
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneLancementMenuPrincipal(stage));           //On modifie et affiche la scène
        }); 

        Label labelTitrePourCommencer = new Label ("Pour Commencer");         //On informe dans quel endroit on se trouve
        labelTitrePourCommencer.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        Label labelPourCommencer = new Label ("COMMENT COMMENCER UNE PARTIE DE BATAILLE NAVALE ?\n Au début du jeu, tout les navires sont placés de manière aléatoire sur la grille.\n"
            +   "Le joueur pourra par la suite les deplacer a sa guise vers le haut ou vers le bas d'une seule case par tour. "
            +   "Le but est de détruire tous les navires de l'adversaire.\n"
            +   "Bien entendu, le joueur ne voit pas la grille de son adversaire.\n"
            +   "Un à un, les joueurs se tire dessus pour détruire les navires ennemis.\n"
            +   "Vous pouvez pas jouer deux fois de suite et doivez attendre le tour du joueur adverse.\n");            //On affiche les règles du jeu
            labelPourCommencer.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootPourCommencer = new GridPane();
        rootPourCommencer.setPadding(new javafx.geometry.Insets(20));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootPourCommencer.setVgap(20);
        rootPourCommencer.add(labelTitrePourCommencer,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);             //On rajoute des éléments a ce root
        rootPourCommencer.add(rootRetour,0,1);             //On rajoute des éléments a ce root
        rootPourCommencer.add(labelPourCommencer,0,2,3,1);
        rootPourCommencer.setBackground( new Background(fondEcranMenuPrincipal));           //On met en arriere plan une image
        Scene scenePourCommencer = new Scene(rootPourCommencer);           //On ajoute ce root à la scène
        return scenePourCommencer;
    }


    //**************************************************************************
    /**
     * Méthode qui permet de créer la scène du menu aide - Phase de tire
     * @param stage La fenêtre d'affichage
     * @return Le scène à afficher
     */
    public Scene sceneFaz2Tir (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");            //On déclare le bouton pour aller au menu principal
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e->   {        //Si le joueur met son curseur sur le bouton
        boutonRetourMenuPrincipal.setGraphic(retourHome);             //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
            +                "-fx-font-size: 13pt;"
            +                "-fx-background-color: rgba(82,127,143,0.50);"
            +                "-fx-font-weight: bold;");
        });
        boutonRetourMenuPrincipal.setOnMouseExited (e->             //Si le joueur enlève son curseur du bouton
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover));            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +                "-fx-font-size: 13pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneLancementMenuPrincipal(stage));           //On modifie et affiche la scène
        }); 

        Label labelTitreFaz2tir = new Label ("Pour tirer");         //On informe dans quel endroit on se trouve
        labelTitreFaz2tir.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");

        Label labelFaz2Tir = new Label ("LA PHASE DE TIRE:\n"
            + "le joueur indique la case où il souhaite tirer, par exemple: H7 qui correspond à la case au\n"
            + "croisement de la colonne H et de la ligne 7 sur les côtés des grilles.\n"
            + "Si vous tirez sur un navire ennemi, le jeu vous indiquera si vous l'avez touché.\n"
            + "Si vous ne touchez pas de navire, le jeu vous indiquera si vous l'avez raté.\n"
            + "Si un des navire est entièrement touché, le jeu vous indiquera que vous avez coulé un navire.");            //On affiche les règles du jeu
            labelFaz2Tir.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootFaz2Tir = new GridPane();
        rootFaz2Tir.setPadding(new javafx.geometry.Insets(20));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootFaz2Tir.setVgap(20);
        rootFaz2Tir.add(labelTitreFaz2tir,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);             //On rajoute des éléments a ce root
        rootFaz2Tir.add(rootRetour,0,1);
        rootFaz2Tir.add(labelFaz2Tir,0,2,3,1);
        rootFaz2Tir.setBackground( new Background(fondEcranMenuPrincipal));           //On met en arriere plan une image
        Scene sceneFaz2Tir = new Scene(rootFaz2Tir);           //On ajoute ce root à la scène
        return sceneFaz2Tir;
    }


    //**************************************************************************
    /**
     * Méthode qui permet de créer la scène du menu aide - Flotte
     * @param stage La fenêtre d'affichage
     * @return Le scène à afficher
     */
    public Scene sceneFlotteFct (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");            //On déclare le bouton pour aller au menu principal
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
        boutonRetourMenuPrincipal.setGraphic(retourHome);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonRetourMenuPrincipal.setOnMouseExited (e->   {          //Si le joueur enlève son curseur du bouton
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");
        });
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneLancementMenuPrincipal(stage));           //On modifie et affiche la scène
        }); 

        Label labelTitreFlotteFct = new Label ("Pour les navires");         //On informe dans quel endroit on se trouve
        labelTitreFlotteFct.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
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
        +"Dans votre flotte, vous avez 10 navires: 1 cuirassé, 2 croiseurs, 3 destroyers et 4 sous-marins.\n");            //On affiche les règles du jeu

        labelFlotteFct.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK;"
              + "-fx-background-color: transparent; ");
        ScrollPane scrollPaneF = new ScrollPane();
        scrollPaneF.setContent(labelFlotteFct);

        GridPane rootFlotteFct = new GridPane();
        rootFlotteFct.setPadding(new javafx.geometry.Insets(20));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootFlotteFct.setVgap(20);
        rootFlotteFct.add(labelTitreFlotteFct ,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);             //On rajoute des éléments a ce root
        rootFlotteFct.add(rootRetour,0,1);             //On rajoute des éléments a ce root
        rootFlotteFct.add(scrollPaneF,0,2);
        rootFlotteFct.setBackground( new Background(fondEcranMenuPrincipal));           //On met en arriere plan une image
        Scene sceneFlotteFct = new Scene(rootFlotteFct);           //On ajoute ce root à la scène
        return sceneFlotteFct;
    }


    //**************************************************************************
    /**
     * Méthode qui permet de créer la scène du menu aide - Légende symbole
     * @param stage La fenêtre d'affichage
     * @return Le scène à afficher
     */
    public Scene sceneLegendSymb (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");            //On déclare le bouton pour aller au menu principal
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e->   {         //Si le joueur met son curseur sur le bouton
        boutonRetourMenuPrincipal.setGraphic(retourHome);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        });
        boutonRetourMenuPrincipal.setOnMouseExited (e->  {           //Si le joueur enlève son curseur du bouton
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        });
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneLancementMenuPrincipal(stage));           //On modifie et affiche la scène
        }); 

        Label labelTitreLegendSymb = new Label ("Pour les symboles");         //On informe dans quel endroit on se trouve
        labelTitreLegendSymb.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
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
        + "     -Un emplacement jaune avec 2 tirets signifie qu’il y a un navire à cet emplacement");            //On affiche les règles du jeu
        labelLegendSymb.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        ScrollPane scrollPaneF = new ScrollPane();
        scrollPaneF.setContent(labelLegendSymb);

        GridPane rootLegendSymb = new GridPane();
        rootLegendSymb.setPadding(new javafx.geometry.Insets(20));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootLegendSymb.setVgap(20);
        rootLegendSymb.add(labelTitreLegendSymb,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);             //On rajoute des éléments a ce root
        rootLegendSymb.add(rootRetour,0,1);
        rootLegendSymb.add(scrollPaneF,0,2);
        rootLegendSymb.setBackground( new Background(fondEcranMenuPrincipal));           //On met en arriere plan une image
        Scene sceneLegendSymb = new Scene(rootLegendSymb);           //On ajoute ce root à la scène
        return sceneLegendSymb;
    }


    //**************************************************************************
    /**
     * Méthode qui permet de créer la scène du menu aide - Gagner et astuce
     * @param stage La fenêtre d'affichage
     * @return Le scène à afficher
     */
    public Scene sceneGagnerAstuce (Stage stage){
        Button boutonRetourMenuPrincipal = new Button ("Menu Principal");            //On déclare le bouton pour aller au menu principal
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                +                "-fx-font-size: 13pt;"
                +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnMouseEntered (e->    {        //Si le joueur met son curseur sur le bouton
        boutonRetourMenuPrincipal.setGraphic(retourHome);            //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        });
        boutonRetourMenuPrincipal.setOnMouseExited (e->            //Si le joueur enlève son curseur du bouton
        boutonRetourMenuPrincipal.setGraphic(retourHomeHover));             //On l'illustre par une petite image
        boutonRetourMenuPrincipal.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        boutonRetourMenuPrincipal.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            stage.setScene(sceneLancementMenuPrincipal(stage));           //On modifie et affiche la scène
        }); 

        Label labelTitreGagnerAstuce = new Label ("Pour gagner");         //On informe dans quel endroit on se trouve
        labelTitreGagnerAstuce.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
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
        + "Cette méthode est infaillible car elle est purement logique.\n");            //On affiche les règles du jeu
            labelGagnerAstuce.setStyle (
                "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
              + " -fx-font-size: 13pt; "
              + "-fx-text-fill: BLACK; ");

        GridPane rootGagnerAstuce = new GridPane();
        rootGagnerAstuce.setPadding(new javafx.geometry.Insets(20));          //On donne les dimensions entre les rebords de la fenètre et le root
        rootGagnerAstuce.setVgap(15);
        rootGagnerAstuce.add(labelTitreGagnerAstuce,0,0);
        HBox rootRetour = new HBox(5); rootRetour.getChildren().addAll(boutonRetour(stage, menuAide(stage)),boutonRetourMenuPrincipal);             //On rajoute des éléments a ce root
        rootGagnerAstuce.add(rootRetour,0,1);             //On rajoute des éléments a ce root
        rootGagnerAstuce.add(labelGagnerAstuce,0,2,3,1);
        rootGagnerAstuce.setBackground( new Background(fondEcranMenuPrincipal));           //On met en arriere plan une image
        Scene sceneGagnerAstuce = new Scene(rootGagnerAstuce);           //On ajoute ce root à la scène
        return sceneGagnerAstuce;
    }
    
}
