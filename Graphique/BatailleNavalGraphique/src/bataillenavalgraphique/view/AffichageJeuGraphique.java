package bataillenavalgraphique.view;

import bataillenavalgraphique.model.GrilleNavire;
import bataillenavalgraphique.controller.JeuGraphique;
import bataillenavalgraphique.model.GrilleBoutons;
import bataillenavalgraphique.bataillenaval.model.Flotte;
import bataillenavalgraphique.bataillenaval.view.Affichage;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.UNDECORATED;
import javafx.util.Duration;


/**
 * Classe Affichage Joueur.
 * Classe utilisé pour l'affichage des informations et l'intéraction avec le joueur.
 * @author Théric PHERON and Joé LHUERRE
 */
public class AffichageJeuGraphique {

    //**************************************************************************
    /**
     * Constructeur de la classe AffichageGraphique.
     * Il permet d'afficher des  élément au joueur.
     */
    public AffichageJeuGraphique(){
        
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui est utiliser pour afficher le menu principal du joueur.
     * Il permet à l'utilisateur de chosir parmis les différentes option qu'il souhaite faire
     */
    public void affichageJoueur(){

        JeuGraphique.victoire();

        if (JeuGraphique.victoire==false){
            
            GrilleBoutons grilleBoutonNavire = new GrilleBoutons('N','A');          //On déclare la grille des boutons pour les navires 
            GrilleBoutons grilleBoutonTirs = new GrilleBoutons('T', 'A');            //On déclare la grille des boutons pour les tirs
            
            
            GridPane rootJeu = new GridPane();          //On déclare un root de type GridPane et on le paramètre
            rootJeu.setPadding(new Insets(20));                    //On donne les dimensions entre les rebords de la fenètre et le root
            rootJeu.setHgap(30);            //On modifie l'écart horizontal
            rootJeu.setVgap(20);            //On modifie l'écart vertical

            final ImageView imageNouvellePartie =new ImageView(getClass().getResource("/images/imageNouvellePartie.png").toString());            //On donne l'emplacement de l'image
            final ImageView imageNouvellePartieHover =new ImageView(getClass().getResource("/images/imageNouvellePartieHover.png").toString());
            final ImageView imageSauvegarder =new ImageView(getClass().getResource("/images/imageSauvegarde.png").toString());
            final ImageView imageSauvegarderHover =new ImageView(getClass().getResource("/images/imageSauvegardeHover.png").toString());
            final ImageView imageChargerPartie =new ImageView(getClass().getResource("/images/chargerPetit.png").toString());            
            final ImageView imageChargerPartieHover =new ImageView(getClass().getResource("/images/chargerPetitHover.png").toString());
            final ImageView imageQuitter =new ImageView(getClass().getResource("/images/imageQuitter.png").toString());
            final ImageView imageQuitterHover =new ImageView(getClass().getResource("/images/imageQuitterHover.png").toString());



            HBox boxBarreMenu = new HBox();         //On déclare un affichage horizontal
            Button boutonNouvellePartie = new Button("Nouvelle Partie");            //On déclare un bouton pour un nouvelle partie
            boutonNouvellePartie.setGraphic(imageNouvellePartie);            //On l'illustre par une petite image
            boutonNouvellePartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonNouvellePartie.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonNouvellePartie.setGraphic(imageNouvellePartieHover);            //On l'illustre par une petite image
                boutonNouvellePartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonNouvellePartie.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonNouvellePartie.setGraphic(imageNouvellePartie);            //On l'illustre par une petite image
                boutonNouvellePartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonNouvellePartie.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                if (JeuGraphique.partieSauvegarde==true){
                    JeuGraphique.fenetreJeu.close();            //On ferme la fenètre
                    MenuGraphique menuGraphique= new MenuGraphique();           //On déclare un objet de type MenuGraphique
                    menuGraphique.choixNiveauIAGraphique();         //On lance le choix du niveau de l'IA
                }
                else {
                    Alert boiAlert = new Alert(AlertType.CONFIRMATION);         //On initialise une boite de dialogue pour une confimation
                    boiAlert.setTitle("Bataille Navale - Confirmation");         //On lui donne un titre
                    boiAlert.setHeaderText("Cette partie n'est pas sauvegardée !");         //On lui donne un message destiné à l'utilisateur
                    boiAlert.setContentText("Voulez vous lancer une nouvelle partie sans sauvegarder ?");           //On lui demande ce qu'il veur faire

                    ButtonType boutonOui = new ButtonType("Oui");           //On créé un bouton pour la réponse oui
                    ButtonType boutonNon = new ButtonType("Non");           //On créé un bouton pour la réponse non
                    boiAlert.getButtonTypes().setAll(boutonOui, boutonNon);         //On ajoute ces deux boutons à la boite de dialogue
                    
                    Optional<ButtonType> choix = boiAlert.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
                    if (choix.get() == boutonOui) {         //Si la réponse est oui 
                        JeuGraphique.fenetreJeu.close();            //On ferme la fenètre
                        MenuGraphique menuGraphique= new MenuGraphique();           //On déclare un objet de type MenuGraphique
                        menuGraphique.choixNiveauIAGraphique();         //On lance le choix du niveau de l'IA   
                    }
                }
            });

            Button boutonSauvegarde = new Button("Sauvegarder");            //On déclare un bouton pour sauvegarder
            boutonSauvegarde.setGraphic(imageSauvegarder);            //On l'illustre par une petite image
            boutonSauvegarde.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonSauvegarde.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonSauvegarde.setGraphic(imageSauvegarderHover);            //On l'illustre par une petite image
                boutonSauvegarde.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 12pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonSauvegarde.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonSauvegarde.setGraphic(imageSauvegarder);            //On l'illustre par une petite image
                boutonSauvegarde.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonSauvegarde.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                AffichageSauvegardeGraphique sauvegarde = new AffichageSauvegardeGraphique();
                try {
                    sauvegarde.sauvegarde(false);
                } catch (ClassNotFoundException e) {
                    System.err.println("Erreur! Lancement sauvegarde");           //On renvoie un message d'erreur en cas de problème
                }
            });

            Button boutonCharger = new Button("Charger une partie");            //On déclare un bouton pour charger une partie
            boutonCharger.setGraphic(imageChargerPartie);            //On l'illustre par une petite image
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonCharger.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonCharger.setGraphic(imageChargerPartieHover);            //On l'illustre par une petite image
                boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 12pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonCharger.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonCharger.setGraphic(imageChargerPartie);            //On l'illustre par une petite image
                boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonCharger.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                if (JeuGraphique.partieSauvegarde==true){
                    AffichageSauvegardeGraphique affichageSauvegardeGraphique = new AffichageSauvegardeGraphique();
                    try {
                        affichageSauvegardeGraphique.lancementChargementMenuJoueur();           //On lance le menu de la sauvegarde
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Erreur! Le chargement n'a pas pu etre effectué.");         //On affiche un message en cas d'erreur
                    }
                }
                else {
                    Alert boiAlert = new Alert(AlertType.CONFIRMATION);         //On initialise une boite de dialogue pour une confimation
                    boiAlert.setTitle("Bataille Navale - Confirmation");         //ON lui donne un titre
                    boiAlert.setHeaderText("Cette partie n'est pas sauvegardée !");         //On lui donne un message destiné à l'utilisateur
                    boiAlert.setContentText("Voulez vous charger une nouvelle partie sans sauvegarder ?");           //On lui demande ce qu'il veur faire

                    ButtonType boutonOui = new ButtonType("Oui");           //On créé un bouton pour la réponse oui
                    ButtonType boutonNon = new ButtonType("Non");           //On créé un bouton pour la réponse non
                    boiAlert.getButtonTypes().setAll(boutonOui, boutonNon);         //On ajoute ces deux boutons à la boite de dialogue
                    
                    Optional<ButtonType> choix = boiAlert.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
                    if (choix.get() == boutonOui) {         //Si la réponse est oui
                        AffichageSauvegardeGraphique affichageSauvegardeGraphique = new AffichageSauvegardeGraphique();
                        try {
                            affichageSauvegardeGraphique.lancementChargementMenuJoueur();           //On lance le menu de sauvegarde
                        } catch (ClassNotFoundException e) {
                            System.err.println("Erreur! Le chargement n'a pas pu etre effectué.");           //On renvoie un message d'erreur en cas de problème
                        }
                    }
                }
            });

            Button boutonQuitter = new Button("Quitter");           //On déclre un bouton pour quitter
            boutonQuitter.setGraphic(imageQuitter);            //On l'illustre par une petite image
            boutonQuitter.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonQuitter.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonQuitter.setGraphic(imageQuitterHover);            //On l'illustre par une petite image
                boutonQuitter.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 12pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonQuitter.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonQuitter.setGraphic(imageQuitter);            //On l'illustre par une petite image
                boutonQuitter.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonQuitter.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                quiiterJeu();
            });

            boxBarreMenu.getChildren().addAll(boutonNouvellePartie, boutonSauvegarde, boutonCharger, boutonQuitter);             //On rajoute des éléments a ce root


            
            grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);         //On fait la mise à jour de la grille des navires
            grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);         //On fait la mise à jour de la grille de tirs
            
            
            rootJeu.add(grilleBoutonNavire.getRoot(),0,6,3,5);           //On place la grille des navires à gauche
            rootJeu.add(grilleBoutonTirs.getRoot(),3,6,3,5);            //On place la grille des tirs à droite 
            
            final Label instructionsJoueur = new Label("Veuillez sélectionner un navire ou une case");            //On informe le joueur des instructions à suivre
            instructionsJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 18pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            final Label titreGrilleNavire = new Label("Voici la grille de vos navires :");            //On informe le joueur que c'est la grille de ses navires
            titreGrilleNavire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;");
            rootJeu.add(titreGrilleNavire,0,5,3,1);         //On place les instructions
            final Label titreGrilleTirs = new Label("Voici la grille de vos tirs :");             //On informe le joueur que c'est la grille des tirs
            titreGrilleTirs.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;");
            rootJeu.add(titreGrilleTirs,3,5,3,1);           //On place les instructions
            rootJeu.setAlignment(CENTER);          //On positionne les éléments du root au centre
            
            VBox allElements = new VBox();         //On déclare un affichage vertical 
            allElements.setPadding(new Insets(0,0,20,0));           //On donne les dimensions entre les rebords de la fenètre et le root
            allElements.getChildren().addAll(boxBarreMenu, rootJeu, instructionsJoueur);
            instructionsJoueur.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre

            Scene sceneJeu = new Scene(allElements);            //On met le root dans la scène 
            JeuGraphique.fenetreJeu.setScene(sceneJeu);         //On modifie et affiche la scène
            JeuGraphique.fenetreJeu.show();         //On ouvre la stage 
            
            Affichage.afficher(0, 0, JeuGraphique.plateauDeJeu);            
            Affichage.afficher(0, 1, JeuGraphique.plateauDeJeu);
        }
    }


    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de quitter le jeu.
     */
    public void quiiterJeu(){

        if (JeuGraphique.partieSauvegarde==false){
            final ImageView imageOui=new ImageView(getClass().getResource("/images/yes.png").toString());            //On donne l'emplacement de l'image
            final ImageView imageOuiHover =new ImageView(getClass().getResource("/images/yesHover.png").toString());
            final ImageView imageNon =new ImageView(getClass().getResource("/images/no.png").toString());
            final ImageView imageNonHover =new ImageView(getClass().getResource("/images/noHover.png").toString());


            VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
            final Label information = new Label ("Voulez vous quitter sans sauvegarder ?");             //On demande au joueur ce qu'il veut faire
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");

            HBox rootBouton = new HBox(80);
            Button boutonOui = new Button("Oui");            //On déclare un bouton pour la réponse oui
            boutonOui.setGraphic(imageOui);            //On l'illustre par une petite image
            boutonOui.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonOui.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonOui.setGraphic(imageOuiHover);            //On l'illustre par une petite image
                boutonOui.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 16pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonOui.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonOui.setGraphic(imageOui);            //On l'illustre par une petite image
                boutonOui.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonOui.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                JeuGraphique.fenetreJeu.close();            //On ferme la fenètre
                MenuGraphique menuGraphique =new MenuGraphique();
                menuGraphique.menuPrincipalGraphique();         //On lance le menu principal
            });


            Button boutonNon = new Button("Non");            //On déclare un bouton pour la réponse non
            boutonNon.setGraphic(imageNon);            //On l'illustre par une petite image
            boutonNon.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonNon.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonNon.setGraphic(imageNonHover);            //On l'illustre par une petite image
                boutonNon.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 16pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonNon.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonNon.setGraphic(imageNon);            //On l'illustre par une petite image
                boutonNon.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonNon.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                AffichageSauvegardeGraphique sauvegarde = new AffichageSauvegardeGraphique();
                try {
                    sauvegarde.sauvegarde(true);
                } catch (ClassNotFoundException e) {
                    System.err.println("Erreur! La sauvegarde n'a pas pu être lancée");           //On renvoie un message d'erreur en cas de problème
                }
            });

            rootBouton.getChildren().addAll(boutonOui, boutonNon);             //On rajoute des éléments a ce root
            rootBouton.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre


            rootText.getChildren().addAll(information, rootBouton);             //On rajoute des éléments a ce root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On modifie et affiche la scène
        }
        else{
            JeuGraphique.fenetreJeu.close();            //On ferme la fenètre
            MenuGraphique menuGraphique =new MenuGraphique();           //On déclare un objet de type MenuGraphique
            menuGraphique.menuPrincipalGraphique();         //On lance le menu principal
        }
    }


    //**************************************************************************
    /**
     * Méthode qui permet à l'uilisateur de sélectionner un navire
     * @param xPlateau Le x du plateau de tir
     * @param yPlateau Le y du plateau de tir
     */
    public void selectionNavire(int xPlateau, int yPlateau){
        
        char lRef = (char) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 0,0);          //On déclare la variable qui stocke la lettre de référence du navire
        int nPlateau = (int) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 0,1);            //On déclare la variable qui stocke le numéro du navire sur la plateau
        int pListe = Flotte.nPlateauToPListe(lRef, nPlateau);           //On déclare la variable qui stocke la position du navire dans la liste
        int tailleNavire= JeuGraphique.flotteJoueur0.get(pListe).taille;            //On déclare la variable qui stocke la taille du navire
        int directionNavire = JeuGraphique.flotteJoueur0.get(pListe).direction;         //On déclare la variable qui stoke la direction du navire
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D', lRef);          //On déclare une grille de bouton pour la grille des navires
        grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);         //On le met à jour
        
        final ImageView imageTir =new ImageView(getClass().getResource("/images/tir.png").toString());            //On donne l'emplacement de l'image
        final ImageView imageTirHover =new ImageView(getClass().getResource("/images/tirHover.png").toString());
        final ImageView imageBouger =new ImageView(getClass().getResource("/images/bouger.png").toString());
        final ImageView imageBougerHover =new ImageView(getClass().getResource("/images/bougerHover.png").toString());
        final ImageView retourImage = new ImageView ("/images/retourImage.png");
        final ImageView retourImageHover = new ImageView ("/images/retourImageHover.png");

        VBox rootselectionNavire = new VBox(40);            //On déclare un affichage vertical avec des éléments espacés de 40 pixels
        
        rootselectionNavire.setPadding(new Insets(10,20,20,70));           //On donne les dimensions entre les rebords de la fenètre et le root pour la sélection des navires

        
        GrilleNavire affichageNavire = new GrilleNavire(tailleNavire);          //On déclare une grille de boutons qui fait la taille du navire sélectionné
        
        GridPane rootAffichagePlateau = new GridPane();         //On déclare un root pour placer le navire selectionné par la suite
        ColumnConstraints  colonneContrainte = new ColumnConstraints (); 
        colonneContrainte.setPercentWidth(50);
        colonneContrainte.setHalignment(HPos.CENTER);          //On positionne les éléments du root au centre    
        rootAffichagePlateau.getColumnConstraints().add(colonneContrainte);             //On rajoute des éléments a ce root
        rootAffichagePlateau.setVgap(20);             //On modifie l'écart vertical       
        

        
        Button boutonRetour = new Button("Retour");            //On déclare un bouton tirer
        boutonRetour.setGraphic(retourImage);            //On l'illustre par une petite image
        boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonRetour.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonRetour.setGraphic(retourImageHover);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonRetour.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonRetour.setGraphic(retourImage);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonRetour.setOnAction((ActionEvent eventChargementPartie) -> {         
            affichageJoueur();
        });
        rootAffichagePlateau.add(boutonRetour,0,0);             //On rajoute un élément a ce root

        
        rootAffichagePlateau.add(grilleBoutonNavire.getRoot(),0,1);         //On place la grille des boutons dans le root
        rootAffichagePlateau.add(affichageNavire.getRoot(directionNavire, lRef, nPlateau,(String) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 3, 0)),1,1);            //On place le navire dans le root
        
        final Label instruction = new Label ("Que souhaitez vous faire ?");           //On demande au joueur ce qu'il veut faire
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"      //On change les caractéristiques d'écriture   
                + "-fx-font-weight: bold;");
        
        Button boutonTire = new Button("Tirer");            //On déclare un bouton tirer
        boutonTire.setGraphic(imageTir);            //On l'illustre par une petite image
        boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonTire.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonTire.setGraphic(imageTirHover);            //On l'illustre par une petite image
            boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonTire.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonTire.setGraphic(imageTir);            //On l'illustre par une petite image
            boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonTire.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            JeuGraphique.partieSauvegarde=false;
            selectionCaseTir(pListe);           //On appelle la méthode qui lui demander où tirer
        });
        
        
        Button boutonBouger = new Button("Bouger Navire");          //On déclare un bouton déplacer
        boutonBouger.setGraphic(imageBouger);           //On l'illustre par une petite image
        if (verificationDeplacementPossible(pListe)==true){
            boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        }
        else {
            boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(14,61,68,0.20)");
        }
        boutonBouger.setOnMouseEntered ((MouseEvent event) -> {          //Si le joueur met son curseur sur le bouton
            if(verificationDeplacementPossible(pListe)==true){
                boutonBouger.setGraphic(imageBougerHover);            //On l'illustre par une petite image
                boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            }
        });
        boutonBouger.setOnMouseExited ((MouseEvent event) -> {          //Si le joueur enlève son curseur du bouton
            if (verificationDeplacementPossible(pListe)==true){
                boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            }
            else {
                boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(14,61,68,0.20)");
            }
        });
        boutonBouger.setOnAction((ActionEvent eventChargementPartie) -> {           //Si on clique sur le bouton bouger
            boolean verif = verificationDeplacementPossible(pListe);            //Renvoie true si il y a un déplacement possible
            if (verif==true) selectionCaseBouger(pListe, lRef);          //Si déplacement est possible alors on appelle la méthode qui lui permettra de déplacer son navire
            else {
                Alert dialogueNePeuxPasBouger = new Alert(AlertType.ERROR);         //On initialise une boite de dialoque pour une erreur
                dialogueNePeuxPasBouger.setTitle("Bataille Navale - Bouger Navire");         //ON lui donne un titre
                dialogueNePeuxPasBouger.setHeaderText("Le navire sélectionné n'a aucune option de déplacement");          //On lui donne un message destiné à l'utilisateur
                dialogueNePeuxPasBouger.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
            }
        });
        
        HBox boutonSelectionNavire = new HBox(80);          //On déclare un affichage horizontal avec les éléments qui sont espacés de 80 pixels
        boutonSelectionNavire.getChildren().addAll(boutonTire,boutonBouger);            //On rajoute le bouton tirer et le bouton déplacer au root
        
        rootselectionNavire.setAlignment(CENTER);           //On positionne les éléments du root au centre
        rootselectionNavire.getChildren().addAll(rootAffichagePlateau, instruction, boutonSelectionNavire);             //On rajoute des éléments a ce root     
        instruction.setAlignment(CENTER);           //On positionne les éléments du root au centre
        boutonSelectionNavire.setAlignment(CENTER);         //On positionne les éléments du root au centre
        

        
        Scene sceneSelectionNavire = new Scene(rootselectionNavire);            //On met le root dans la scène
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);         //On modifie et affiche la scène
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de sélectionne une case de tir.
     * @param pListe La position du navire tireur dans la flotte
     */
    public void selectionCaseTir(int pListe){
        if (JeuGraphique.flotteJoueur0.get(pListe).lRef=='D' && JeuGraphique.flotteJoueur0.get(pListe).premierTire==true){
            List<Integer> listeInformations = new ArrayList<Integer> ();
            listeInformations.add(0, pListe);           //On ajoute une information à la pListe
            GrilleBoutons grilleBoutonTirs = new GrilleBoutons('B', listeInformations);
            grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);

            VBox rootselectionCaseTir = new VBox(20);           //On crée un root de stockage vertival pour stocker les tirs
            rootselectionCaseTir.setPadding(new Insets(20,300,20,300));           //On donne les dimensions entre les rebords de la fenètre et le root

            final Label instruction = new Label ("Il s'agit du premier tir de ce Destroyer !");             //On informe le joueur que c'est le premier tir du destroyer
            instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                    + "-fx-font-weight: bold;");
            
                    final Label instruction2 = new Label ("Il s'agit donc d'une fusée éclairante ?");
            instruction2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"
                    + "-fx-font-weight: bold;");
            
                    final Label instruction3 = new Label ("Où souhaitez vous la tirer ?");
            instruction3.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15;"
                    + "-fx-font-weight: bold;");

            rootselectionCaseTir.setAlignment(CENTER);          //On positionne les éléments du root au centre
            rootselectionCaseTir.getChildren().addAll(instruction, instruction2, instruction3, grilleBoutonTirs.getRoot());             //On rajoute des éléments a ce root
            instruction.setAlignment(CENTER);          //On positionne les éléments du root au centre
            Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);         //On modifie la scène
        }
        else{
            List<Integer> listeInformations = new ArrayList<Integer> ();            //On déclare une liste qui va contenir les informations de tirs
            listeInformations.add(0, pListe);               //On ajoute dedans la possition du navire dans la liste de la flotte
            GrilleBoutons grilleBoutonTirs = new GrilleBoutons('B', listeInformations);         //On initialiser une grille de bouton avec la référence B - Cela signifie que c'est une grille de bouton pour la sélection de l'emplacement de tir
            grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);         //On met a jour les couleur et les texte de la grille 

            VBox rootselectionCaseTir = new VBox(40);           //On crée un root de stockage vertival pour stocker les navires
            rootselectionCaseTir.setPadding(new Insets(90,300,20,300));           //On donne les dimensions entre les rebords de la fenètre et le root

            final Label instruction = new Label ("Où souhaitez vous tirer ?");            //On affiche les instruction
            instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"         //On donner le style au instruction
                    + "-fx-font-weight: bold;");

            rootselectionCaseTir.setAlignment(CENTER);          //On met les élément au centre 
            rootselectionCaseTir.getChildren().addAll(instruction, grilleBoutonTirs.getRoot());         //On ajoute les élément au root
            instruction.setAlignment(CENTER);           //On les possition au centre
            Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);           //On rajoute les root à une scene
            JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);         //On modifie et affiche la scène
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de sélectionner une case pour bouger un navire.
     */
    public void selectionCaseBouger(int pListe, char lRef){
        
        JeuGraphique.partieSauvegarde=false;

        List<Integer> listeInformations = new ArrayList<Integer> ();
        listeInformations.add(0, pListe);           //On ajoute un élément à la pListe
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('C', listeInformations);
        grilleBoutonTirs.miseAJourAffichageBouger(JeuGraphique.plateauDeJeu, pListe, lRef);

        VBox rootselectionCaseTir = new VBox(40);           //On crée un root de stockage vertival pour stocker les tirs
        rootselectionCaseTir.setPadding(new Insets(90,300,20,300));           //On donne les dimensions entre les rebords de la fenètre et le root

        final Label instruction = new Label ("Où souhaitez vous bougez votre navire ?");             //On demande au joueur où il veut bouger son navire
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                + "-fx-font-weight: bold;");

        rootselectionCaseTir.setAlignment(CENTER);          //On positionne les éléments du root au centre
        rootselectionCaseTir.getChildren().addAll(instruction, grilleBoutonTirs.getRoot());             //On rajoute des éléments a ce root
        instruction.setAlignment(CENTER);          //On positionne les éléments du root au centre
        Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);           //On met le root dans la scène
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);         //On modifie et affiche la scène
        
    }
    
    
    //**************************************************************************
     /**
      * Méthode qui permet d'afficher un message à l'utilisateur pour lui indiquer que le déplacement de son navire est en cours.
      * @param x La position x de déplacement
      * @param y La position y de déplacement
      * @param pListe La position du navire dans la flotte
      */
    public void bougerNavire(int x, int y, int pListe){
        
        
        VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        final Label information = new Label ("La manoeuvre est en cours");             //On informe le joueur que le déplacment de son navire a été pris en compte
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 20pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        final Label information1 = new Label ("Veuillez patienter");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information, information1);             //On rajoute des éléments a ce root
        rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
        JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On modifie et affiche la scène
        
        JeuGraphique.bougerNavire(pListe, x, y);
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message à l'utilisateur pour lui indiquer que le déplacement du navire a bien eu lieu.
     */
    public void deplacementEffectueNavire(){
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {            //On met un temps d'attente de 7s
            GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D', 'V');
            grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);
            
            VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
            rootText.setPadding(new Insets(5,300,20,300));           //On donne les dimensions entre les rebords de la fenètre et le root
            final Label information = new Label ("La manoeuvre a bien été effectuée");             //On informe le joueur que la manoeuvre est réussite
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, grilleBoutonNavire.getRoot());             //On rajoute des éléments a ce root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On modifie et affiche la scène
        }));
        time.play();                //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie un message d'erreur en cas de problème
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui pemet d'indiquer à l'utilisateur qui'il a raté son tir.
     * @throws InterruptedException
     */
    public void tirEchec() throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information = new Label ("Nous n'avons rien touché à ces coordonées");             //On informe le joueur qu'il a rien touché
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            final Label information1 = new Label ("C'est au tour de l'IA");             //On informe le joueur que c'est au tour de l'IA de jouer
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On modifie et affiche la scène
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie un message d'erreur en cas de problème
        }
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message
     * @throws InterruptedException
     */
    public void tirSurSousMarin() throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information = new Label ("Nous avons détecté une structure mais nous n'avons pas pu la détruire");             //On informe le joueur qu'il a touché un sous-marin mais qu'il ne la pas coulé
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            final Label information1 = new Label ("C'est au tour de l'IA");             //On informe le joueur que c'est au tour de l'IA
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);            //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On modifie et affiche la scène
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie une erreur en cas de problème
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer à l'utilisateur qu'il a touché un navire.
     */
    public void tirToucherNavire() throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information = new Label ("C'est touché, bien joué");             //On informe le joueur qu'il a touché un navire adverse
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 25pt; "
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            final Label information1 = new Label ("\n\n Au tour de l'IA");             //On informe le joueur que c'est au tour de l'IA
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On modifie et affiche la scène
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie une erreur en cas de problème
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer à l'utilisateur qu'il a coulé un navire.
     * @param nomNavireCoule Le nom du navire qui a été coulé
     */
    public void tirCoulerNavire(String nomNavireCoule) throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information = new Label ("C'est touché, bien joué");             //On informe le joueur qu'il a touché un navire adverse
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 25pt; "
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            final Label information1 = new Label ("Vous avez coulé un "+nomNavireCoule);             //On informe le joueur du nom du navire qu'il a coulé
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 18pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information2 = new Label ("\n\n Au tour de l'IA");
            information2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1,information2);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On modifie et affiche la scène
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie une erreur en cas de problème
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de l'encer le tour de l'IA
     * @throws InterruptedException
     */
    public void tourIA() throws InterruptedException{
        
        JeuGraphique.victoire();

        if (JeuGraphique.victoire==false){
            Timeline timeTourJoueur = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
            timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(12000),action -> {            //On met un temps d'attente de 12s
                try {
                    JeuGraphique.ia.jouer();            //On essaye de lancer le tour de l'IA
                } catch (InterruptedException ex){}
            }));
            timeTourJoueur.play();            //On démarre le temps de décalage dès que le programme le lit
        }
    }
    

    //**************************************************************************
    /**
     * Méthodeq qui permet de d'afficher la zone de tir de la fusée.
     * @param xTire La coordonées x de tir de la fussée
     * @param yTire La coordonées y de tir de la fussée
     * @param surplusX Le surplus en cas de dépassement sur l'axe x
     * @param surplusY La surplus en cas de dépassement sur l'axe y
     */
    public void zoneTirFusee(int xTire, int yTire, int surplusX, int surplusY){
        
        Timeline timeZoneTirFusee = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeZoneTirFusee.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {            //On met un temps d'attente de 7s
            GrilleBoutons grilleBoutonTirs = new GrilleBoutons('D', 'F');
            grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);

            VBox rootselectionCaseTir = new VBox(40);           //On crée un root de stockage vertival pour stocker les navires
            rootselectionCaseTir.setPadding(new Insets(90,300,20,300));           //On donne les dimensions entre les rebords de la fenètre et le root

            final Label instruction2 = new Label ("Voici les informations qui ont été rapportées");
            instruction2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                    + "-fx-font-weight: bold;");

            rootselectionCaseTir.setAlignment(CENTER);          //On positionne les éléments du root au centre
            rootselectionCaseTir.getChildren().addAll(instruction2, grilleBoutonTirs.getRoot());             //On rajoute des éléments a ce root
            instruction2.setAlignment(CENTER);          //On positionne les éléments du root au centre
            Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
            
            
            Timeline timeTourJoueur = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
            timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(12000),e -> {            //On met un temps d'attente de 12s
                
                for (int i=0; i<4-surplusX ; i++){
                    for (int j=0; j<4-surplusY; j++){
                        JeuGraphique.plateauDeJeu.modification(xTire+i,yTire+j,1,0,"0");         //Affiche sur la grille de tir du joueur les navires qui ont été touché par la fusée éclairante
                    }
                }
                
                try {
                    JeuGraphique.ia.jouer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AffichageJeuGraphique.class.getName()).log(Level.SEVERE, null, ex);
                }
            }));
            timeTourJoueur.play();            //On démarre le temps de décalage dès que le programme le lit
            
        }));
        timeZoneTirFusee.play();            //On démarre le temps de décalage dès que le programme le lit
    }
    

    //**************************************************************************
    /**
     * Mathode qui permet de vérifier qu'une déplacement est possible.
     * @param pListe La possition du navire dans la flotte
     * @return True si le déplacement est possible, false sinon
     */
    public boolean verificationDeplacementPossible(int pListe){
        
        for (int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){                //On fait une boucle pour vérifier toutes les coordonnées du navire
                if (JeuGraphique.flotteJoueur0.get(pListe).coordonnees[i][2]==0){
                    return false;
                }
        }
        boolean possible=true;

        
        if (JeuGraphique.flotteJoueur0.get(pListe).direction==0){           //Si le navire est à l'horizontal

            //Cas droit
            int x = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[JeuGraphique.flotteJoueur0.get(pListe).taille-1][0] + 1 ;
            int y = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
            if (x+1<=14){
                if (JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_'))return true;
            }
            
            //Cas gauche
            x = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0] -1 ;
            y = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
            if (x-1>=0){
                if (JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_'))return true;
            }

            
            //Cas bas
            int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];
            int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]+1;
            if (xStart<=14){
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){          //Si il y a un navire
                        possible=false;
                        i=10;           //On sort de la boucle
                    }
                }
            }
            if (possible==true) return true;
            
            possible = true;
            //Cas haut
            xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];
            yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]-1;
            if (xStart>=0){
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){
                        possible=false;
                        i=10;
                    }
                }
            }
            if (possible==true) return true;
        }
        else{
            int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]+1;
            int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
            if (xStart<=14){
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){
                        possible=false;
                        i=10;
                    }
                }
            }
            if (possible==true) return true;
            
            possible=true;
            xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]-1;
            yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
            if (xStart>=0){
                for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                    if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){
                        possible=false;
                        i=10;
                    }
                }
            }
            if (possible==true) return true;

            int x = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];
            int y = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[JeuGraphique.flotteJoueur0.get(pListe).taille-1][1]+1;
            if (y+1<=14){
                if (JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_'))return true;
            }
            
            x = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];
            y = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]-1;
            if (y-1>=0){
                if (JeuGraphique.plateauDeJeu.get(x,y,0,0).equals('_'))return true;
            }
        }
        return false;
        
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de sélectionner uoù il veut tirer.
     * @param xTire La coordonées x du tire.
     * @param yTire La coordonées y du tire
     */
    public void selectionTir(int xTire, int yTire){
        List<Integer> listeInformations = new ArrayList<Integer> ();            //On déclare une liste qui va contenir les informations de tirs
        listeInformations.add(0, 0); 
        listeInformations.add(1, xTire);               //On ajoute dedans la coordonees x de tir dans la liste de la flotte
        listeInformations.add(2, yTire);               //On ajoute dedans la coordonees y de tir dans la liste de la flotte

        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('S', listeInformations);         //On initialiser une grille de bouton avec la référence B - Cela signifie que c'est une grille de bouton pour la sélection de l'emplacement de tir
        grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);         //On met a jour les couleur et les texte de la grille 

        VBox rootselectionTir = new VBox(40);           //On crée un root de stockage vertival pour stocker les navires
        rootselectionTir.setPadding(new Insets(10,300,20,300));           //On donne les dimensions entre les rebords de la fenètre et le root

        final Label instruction = new Label ("Avec quel navire souhaitez vous tirer?");            //On affiche les instruction
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"         //On donner le style au instruction
                + "-fx-font-weight: bold;");


        final ImageView retourImage = new ImageView ("/images/retourImage.png");            //On donne l'emplacement de l'image
        final ImageView retourImageHover = new ImageView ("/images/retourImageHover.png");

        Button boutonRetour = new Button("Retour");            //On déclare un bouton tirer
        boutonRetour.setGraphic(retourImage);            //On l'illustre par une petite image
        boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonRetour.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonRetour.setGraphic(retourImageHover);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonRetour.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonRetour.setGraphic(retourImage);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonRetour.setOnAction((ActionEvent eventChargementPartie) -> {         
            affichageJoueur();
        });

        rootselectionTir.setAlignment(CENTER);          //On positionne les éléments du root au centre 
        rootselectionTir.getChildren().addAll(boutonRetour,instruction, grilleBoutonNavire.getRoot());         //On ajoute les élément au root
        instruction.setAlignment(CENTER);          //On positionne les éléments du root au centre
        Scene sceneSelectionNavire = new Scene(rootselectionTir);           //On rajoute les root à une scene
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);         //On modifie et affiche la scène
    }


    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer qu'une case est vide à l'utilisateur dans le menu joueur.
     */
    public void erreurCaseVideN(){
        Alert alertCaserVide = new Alert(AlertType.INFORMATION);         //On initialise une boite de dialogue pour une information
        alertCaserVide.setTitle("Bataille Navale - Sélection");         //ON lui donne un titre
        alertCaserVide.setHeaderText("La case sélectionnée est vide.");          //On lui donne un message destiné à l'utilisateur
        alertCaserVide.setContentText("Veuillez sélectionner une case contenant un navire ou une case de tir.");          //On lui donne un message destiné à l'utilisateur

        alertCaserVide.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
    }


    //**************************************************************************
    /**
     * Méthode qui permet d'indiiquer qu'une case est vide à l'utilisateur dans la sélection du navire.
     */
    public void erreurCaseVideS(){
        Alert alertCaserVide = new Alert(AlertType.INFORMATION);         //On initialise une boite de dialogue pour une information
        alertCaserVide.setTitle("Bataille Navale - Sélection");         //ON lui donne un titre
        alertCaserVide.setHeaderText("La case sélectionnée est vide.");          //On lui donne un message destiné à l'utilisateur
        alertCaserVide.setContentText("Veuillez sélectionner une case contenant un navire");          //On lui donne un message destiné à l'utilisateur

        alertCaserVide.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet d'afficher une erreur si le joueur sélectionne un navire déja coulé.
     */
    public void erreurNavireCoule(){
        Alert alertNavireCouler = new Alert(AlertType.INFORMATION);         //On initialise une boite de dialogue pour une information
        alertNavireCouler.setTitle("Bataille Navale - Navire coulé");         //ON lui donne un titre
        alertNavireCouler.setHeaderText("Le navire sélectionnée est coulé");          //On lui donne un message destiné à l'utilisateur
        alertNavireCouler.setContentText("Un navire qui est coulé ne peut pas être utilisé. Veuillez sélectionner un autre navire.");          //On lui donne un message destiné à l'utilisateur

        alertNavireCouler.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
    }


    //**************************************************************************
    /**
     * Méthode qui permet de vérifier si un joueur à gagner.
     */
    public void victoireJoueur(){
        Stage fenetreVictoire = new Stage();
        fenetreVictoire.setTitle("Bataille Navale - Niveau IA");
        fenetreVictoire.setWidth(400);
        fenetreVictoire.setHeight(800);
        fenetreVictoire.setResizable(false);
        fenetreVictoire.centerOnScreen();
        fenetreVictoire.initStyle(UNDECORATED);
        fenetreVictoire.setOnCloseRequest(event -> event.consume());
        fenetreVictoire.getIcons().add(new Image("/images/iconNaval.png")); 
        
        
        final ImageView victoireGif =new ImageView(getClass().getResource("/images/victoire.gif").toString());         //On indique la position de l'image 
        
        VBox rootVictoire = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        rootVictoire.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        rootVictoire.setPadding(new Insets (20,30,10,30));           //On donne les dimensions entre les rebords de la fenètre et le root

        final Label labelAffichageVictoireJoueur = new Label ("VICTOIRE DU JOUEUR !!");           //On informe le joueur qu'il a gagné
        labelAffichageVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                           "-fx-font-size: 20pt;"
                    +                           "-fx-background-color: rgba(120,160,175,0.50);");

        final Label labelAffichageInfoPartie = new Label ("\nInformations sur la partie : "            //On donne les caractéristiques de la partie
            +"\nNombre de tours : "+ (JeuGraphique.compteurTourHumain+ JeuGraphique.compteurTourIA)            //Le nombre de tour que l'IA a effecuté
            +"\nNombre de tours du joueur : "+ JeuGraphique.compteurTourHumain            //Le nombre de tour que le joueur a effectué
            +"\nNombre de tours de l'IA : "+ JeuGraphique.compteurTourIA
            +"\nTemps total de la partie : "+ JeuGraphique.chronometre.getDureeTxt());
        labelAffichageInfoPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 15pt;");
                    
        
        Button bontonVictoireJoueur = new Button ("SUPER !");         //On déclare un bouton pour confirmer le fait que le joueur a gagner avant de retourner au menu principal
        bontonVictoireJoueur.setContentDisplay(ContentDisplay.TOP);             //On met l'image au dessus du bouton
        bontonVictoireJoueur.setGraphic(victoireGif);          //On l'illustre par une image
        bontonVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
        +                "-fx-font-size: 17pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);");
        bontonVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        bontonVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
       bontonVictoireJoueur.setOnAction((ActionEvent eventLancementJeu) -> {
            MenuGraphique menuGraphique = new MenuGraphique();           //On déclare un objet de type MenuGraphique
            fenetreVictoire.close();            //On ferme la fenetre
            menuGraphique.menuPrincipalGraphique();         //On lance le menu principal
        });

        rootVictoire.getChildren().addAll(labelAffichageVictoireJoueur,labelAffichageInfoPartie, bontonVictoireJoueur);             //On rajoute des éléments a ce root
        rootVictoire.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneVictoire = new Scene(rootVictoire);           //On met le root dans une scène
        
        
        fenetreVictoire.setScene(sceneVictoire);
        fenetreVictoire.show();
    
        JeuGraphique.fenetreJeu.close();;         //On modifie et affiche la scène
    }


    public void victoireJoueurFofait(){
        
        Stage fenetreVictoire = new Stage();
        fenetreVictoire.setTitle("Bataille Navale - Niveau IA");
        fenetreVictoire.setWidth(400);
        fenetreVictoire.setHeight(800);
        fenetreVictoire.setResizable(false);
        fenetreVictoire.centerOnScreen();
        fenetreVictoire.initStyle(UNDECORATED);
        fenetreVictoire.setOnCloseRequest(event -> event.consume());
        fenetreVictoire.getIcons().add(new Image("/images/iconNaval.png")); 


        final ImageView victoireGif =new ImageView(getClass().getResource("/images/victoire.gif").toString());         //On indique la position de l'image 
        
        VBox rootVictoireForfait = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        rootVictoireForfait.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        rootVictoireForfait.setPadding(new Insets (20,30,10,30));           //On donne les dimensions entre les rebords de la fenètre et le root

        final Label labelAffichageVictoireJoueur = new Label ("VICTOIRE DU JOUEUR !!");           //On informe le joueur qu'il a gagné
        labelAffichageVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                           "-fx-font-size: 20pt;"
                    +                           "-fx-background-color: rgba(120,160,175,0.50);");
        final Label labelForfaitDeLIA = new Label ("Vous avez coulé tous \nles sous-marins de l'ordinateur\n"         //On informe le joueur qu'il a perdu car il a plus de sous-marin
                    +                                       "Vous gagnez par fofait");
        labelForfaitDeLIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                           "-fx-font-size: 15pt;");

        final Label labelAffichageInfoPartie = new Label ("\nInformations sur la partie : "            //On donne les caractéristiques de la partie
            +"\nNombre de tours : "+ (JeuGraphique.compteurTourHumain+ JeuGraphique.compteurTourIA)            //Le nombre de tour que l'IA a effecuté
            +"\nNombre de tours du joueur : "+ JeuGraphique.compteurTourHumain            //Le nombre de tour que le joueur a effectué
            +"\nNombre de tours de l'IA : "+ JeuGraphique.compteurTourIA
            +"\nTemps total de la partie : "+ JeuGraphique.chronometre.getDureeTxt());
        labelAffichageInfoPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 15pt;");

        
        Button bontonVictoireJoueur = new Button ("SUPER !");         //On déclare un bouton pour confirmer le fait que le joueur a gagner avant de retourner au menu principal
        bontonVictoireJoueur.setContentDisplay(ContentDisplay.TOP);              //On met l'image au dessus du bouton
        bontonVictoireJoueur.setGraphic(victoireGif);          //On l'illustre par une image
        bontonVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
        +                "-fx-font-size: 17pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);");
        bontonVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        bontonVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        bontonVictoireJoueur.setOnAction((ActionEvent eventLancementJeu) -> {
            MenuGraphique menuGraphique = new MenuGraphique();           //On déclare un objet de type MenuGraphique
            fenetreVictoire.close();            //On ferme la fenetre
            menuGraphique.menuPrincipalGraphique();         //On lance le menu principal
        });
        rootVictoireForfait.getChildren().addAll(labelAffichageVictoireJoueur,labelForfaitDeLIA ,labelAffichageInfoPartie, bontonVictoireJoueur);
        rootVictoireForfait.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneVictoire = new Scene(rootVictoireForfait);           //On met le root dans une scène
        
        fenetreVictoire.setScene(sceneVictoire);
        fenetreVictoire.show();

        JeuGraphique.fenetreJeu.close();;         //On modifie et affiche la scène
    }
    
    
    public void victoireIA(){
        
        Stage fenetreVictoire = new Stage();
        fenetreVictoire.setTitle("Bataille Navale - Niveau IA");
        fenetreVictoire.setWidth(400);
        fenetreVictoire.setHeight(800);
        fenetreVictoire.setResizable(false);
        fenetreVictoire.centerOnScreen();
        fenetreVictoire.initStyle(UNDECORATED);
        fenetreVictoire.setOnCloseRequest(event -> event.consume());
        fenetreVictoire.getIcons().add(new Image("/images/iconNaval.png")); 

        VBox rootVictoire = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        rootVictoire.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        rootVictoire.setPadding(new Insets (20,30,10,30));           //On donne les dimensions entre les rebords de la fenètre et le root

        final Label labelAffichageVictoireIA = new Label ("VICTOIRE DE L'ORDINATEUR !!");         //On informe le joueur que l'IA a gagné
        labelAffichageVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 20pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");

        final Label labelAffichageInfoPartie = new Label ("\nInformations sur la partie : "            //On donne les caractéristiques de la partie
            +"\nNombre de tours : "+ (JeuGraphique.compteurTourHumain+ JeuGraphique.compteurTourIA)            //Le nombre de tour que l'IA a effecuté
            +"\nNombre de tours du joueur : "+ JeuGraphique.compteurTourHumain            //Le nombre de tour que le joueur a effectué
            +"\nNombre de tours de l'IA : "+ JeuGraphique.compteurTourIA
            +"\nTemps total de la partie : "+ JeuGraphique.chronometre.getDureeTxt());
        labelAffichageInfoPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 15pt;");
        
        
        Button bontonVictoireIA = new Button ("Bien joué !");         //On déclare un bouton pour confirmer le fait que le joueur a perdu face à notre IA
        bontonVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
        +                "-fx-font-size: 17pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);");
        bontonVictoireIA.setOnMouseEntered (e->
        bontonVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"));
        bontonVictoireIA.setOnMouseExited (e-> 
        bontonVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"));
        bontonVictoireIA.setOnAction((ActionEvent eventLancementJeu) -> {
            MenuGraphique menuGraphique = new MenuGraphique();           //On déclare un objet de type MenuGraphique
            fenetreVictoire.close();            //On ferme la fenetre
            menuGraphique.menuPrincipalGraphique();         //On lance le menu principal
        });

        rootVictoire.getChildren().addAll(labelAffichageVictoireIA,labelAffichageInfoPartie, bontonVictoireIA);             //On rajoute des éléments a ce root
        rootVictoire.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneVictoire = new Scene(rootVictoire);           //On met le root dans une scène
        
        fenetreVictoire.setScene(sceneVictoire);
        fenetreVictoire.show();

        JeuGraphique.fenetreJeu.close();;         //On modifie et affiche la scène
    }
    
    public void victoireIAFofait(){
        
        Stage fenetreVictoire = new Stage();
        fenetreVictoire.setTitle("Bataille Navale - Niveau IA");
        fenetreVictoire.setWidth(400);
        fenetreVictoire.setHeight(800);
        fenetreVictoire.setResizable(false);
        fenetreVictoire.centerOnScreen();
        fenetreVictoire.initStyle(UNDECORATED);
        fenetreVictoire.setOnCloseRequest(event -> event.consume());
        fenetreVictoire.getIcons().add(new Image("/images/iconNaval.png")); 

        VBox rootVictoireForfait = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        rootVictoireForfait.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        rootVictoireForfait.setPadding(new Insets (20,30,10,30));           //On donne les dimensions entre les rebords de la fenètre et le root

        final Label labelAffichageVictoireIA = new Label ("VICTOIRE DE L'ORDINATEUR !!");         //On informe le joueur que l'IA a gagné
        labelAffichageVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 20pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        
        final Label labelForfaitDuJoueur = new Label ("Vous n'avez plus de sous-marins\n"         //On informe le joueur qu'il a perdu car il a plus de sous-marin
                    +                           "L'ordinateur gagne par \nfofait de votre part");
        labelForfaitDuJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                        +                "-fx-font-size: 15pt;");


        final Label labelAffichageInfoPartie = new Label ("\nInformations sur la partie : "            //On donne les caractéristiques de la partie
            +"\nNombre de tours : "+ (JeuGraphique.compteurTourHumain+JeuGraphique.compteurTourIA)            //Le nombre de tour que l'IA a effecuté
            +"\nNombre de tours du joueur : "+ JeuGraphique.compteurTourHumain            //Le nombre de tour que le joueur a effectué
            +"\nNombre de tours de l'IA : "+ JeuGraphique.compteurTourIA
            +"\nTemps total de la partie : "+ JeuGraphique.chronometre.getDureeTxt());
        labelAffichageInfoPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 15pt;");
        
        
        Button bontonVictoireIA = new Button ("Bien joué !");         //On déclare un bouton pour confirmer le fait que le joueur a perdu face à notre IA
        bontonVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
        +                "-fx-font-size: 17pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);");
        bontonVictoireIA.setOnMouseEntered (e->
        bontonVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);"));
        bontonVictoireIA.setOnMouseExited (e-> 
        bontonVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 17pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"));
        bontonVictoireIA.setOnAction((ActionEvent eventLancementJeu) -> {
            MenuGraphique menuGraphique = new MenuGraphique();           //On déclare un objet de type MenuGraphique
            fenetreVictoire.close();            //On ferme la fenetre
            menuGraphique.menuPrincipalGraphique();         //On lance le menu principal
        });

        rootVictoireForfait.getChildren().addAll(labelAffichageVictoireIA,labelForfaitDuJoueur ,labelAffichageInfoPartie, bontonVictoireIA);
        rootVictoireForfait.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneVictoire = new Scene(rootVictoireForfait);           //On met le root dans une scène
        
        fenetreVictoire.setScene(sceneVictoire);
        fenetreVictoire.show();

        JeuGraphique.fenetreJeu.close();;         //On modifie et affiche la scène
    }
}
