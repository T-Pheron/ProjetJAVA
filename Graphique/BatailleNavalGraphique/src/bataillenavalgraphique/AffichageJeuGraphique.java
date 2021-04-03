package bataillenavalgraphique;

import static bataillenavalgraphique.JeuGraphique.compteurTourHumain;
import static bataillenavalgraphique.JeuGraphique.compteurTourIA;
import static bataillenavalgraphique.JeuGraphique.flotteJoueur0;
import static bataillenavalgraphique.JeuGraphique.flotteJoueur1;
import bataillenavalgraphique.bataillenaval.model.Flotte;
import bataillenavalgraphique.bataillenaval.view.Affichage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 *
 * @author Théric PHERON
 */
public class AffichageJeuGraphique {


    public AffichageJeuGraphique(){
        
    }
    
    
    public void affichageJoueur(){

        victoire();         //On appelle le méthode Victoire pour vérifier si il y a un gagnant ou pas
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('N','A');          //On déclare la grille des boutons pour les navires 
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('T', 'A');            //On déclare la grille des boutons pour les tirs
        JeuGraphique.compteurTourHumain++;          //On rajoute 1 au nombre de tour du joueur 
        
        GridPane rootJeu = new GridPane();          //On déclare un root de type GridPane et on le paramètre
        rootJeu.setPadding(new Insets(20));         
        rootJeu.setHgap(30);
        rootJeu.setVgap(20);

        ImageView imageNouvellePartie =new ImageView(getClass().getResource("/images/imageNouvellePartie.png").toString());            //On donne l'emplacement de l'image
        ImageView imageNouvellePartieHover =new ImageView(getClass().getResource("/images/imageNouvellePartieHover.png").toString());
        ImageView imageSauvegarder =new ImageView(getClass().getResource("/images/imageSauvegarde.png").toString());
        ImageView imageSauvegarderHover =new ImageView(getClass().getResource("/images/imageSauvegardeHover.png").toString());
        ImageView imageChargerPartie =new ImageView(getClass().getResource("/images/chargerPetit.png").toString());            
        ImageView imageChargerPartieHover =new ImageView(getClass().getResource("/images/chargerPetitHover.png").toString());
        ImageView imageQuitter =new ImageView(getClass().getResource("/images/imageQuitter.png").toString());
        ImageView imageQuitterHover =new ImageView(getClass().getResource("/images/imageQuitterHover.png").toString());



        HBox boxBarreMenu = new HBox();
        Button boutonNouvellePartie = new Button("Nouvelle Partie");
        boutonNouvellePartie.setGraphic(imageNouvellePartie);            //On l'illustre par une petite image
        boutonNouvellePartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonNouvellePartie.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonNouvellePartie.setGraphic(imageNouvellePartieHover);
            boutonNouvellePartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonNouvellePartie.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonNouvellePartie.setGraphic(imageNouvellePartie);
            boutonNouvellePartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonNouvellePartie.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            if (JeuGraphique.partieSauvegarde==true){
                JeuGraphique.fenetreJeu.close();
                MenuGraphique menuGraphique= new MenuGraphique();
                menuGraphique.choixNiveauIAGraphique();
            }
            else {
                Alert boiAlert = new Alert(AlertType.CONFIRMATION);
                boiAlert.setTitle("Bataille Naval - Confirmation");
                boiAlert.setHeaderText("Cette partie n'est pas sauvegarder !");
                boiAlert.setContentText("Voulez vous lancer une nouvelle partie sans sauvegarder ?");

                ButtonType boutonOui = new ButtonType("Oui");
                ButtonType boutonNon = new ButtonType("Non");
                boiAlert.getButtonTypes().setAll(boutonOui, boutonNon);
                
                Optional<ButtonType> choix = boiAlert.showAndWait();
                if (choix.get() == boutonOui) {
                    JeuGraphique.fenetreJeu.close();
                    MenuGraphique menuGraphique= new MenuGraphique();
                    menuGraphique.choixNiveauIAGraphique();
                }
            }
        });

        Button boutonSauvegarde = new Button("Sauvegarder");
        boutonSauvegarde.setGraphic(imageSauvegarder);            //On l'illustre par une petite image
        boutonSauvegarde.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonSauvegarde.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonSauvegarde.setGraphic(imageSauvegarderHover);
            boutonSauvegarde.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 12pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonSauvegarde.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonSauvegarde.setGraphic(imageSauvegarder);
            boutonSauvegarde.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonSauvegarde.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            AffichageSauvegardeGraphique sauvegarde = new AffichageSauvegardeGraphique();
            try {
                sauvegarde.sauvegarde(false);
            } catch (ClassNotFoundException e) {
                System.out.println("Erreur! Lancement sauvegarde");
            }
        });

        Button boutonCharger = new Button("Charger une partie");
        boutonCharger.setGraphic(imageChargerPartie);            //On l'illustre par une petite image
        boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonCharger.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonCharger.setGraphic(imageChargerPartieHover);
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 12pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonCharger.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonCharger.setGraphic(imageChargerPartie);
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonCharger.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            if (JeuGraphique.partieSauvegarde==true){
                AffichageSauvegardeGraphique affichageSauvegardeGraphique = new AffichageSauvegardeGraphique();
                try {
                    affichageSauvegardeGraphique.lancementChargementMenuJoueur();
                } catch (ClassNotFoundException ex) {
                    System.err.println("Erreu! Le chargement n'as pas pu etre effectué.");
                }
            }
            else {
                Alert boiAlert = new Alert(AlertType.CONFIRMATION);
                boiAlert.setTitle("Bataille Naval - Confirmation");
                boiAlert.setHeaderText("Cette partie n'est pas sauvegarder !");
                boiAlert.setContentText("Voulez vous charger une nouvelle partie sans sauvegarder ?");

                ButtonType boutonOui = new ButtonType("Oui");
                ButtonType boutonNon = new ButtonType("Non");
                boiAlert.getButtonTypes().setAll(boutonOui, boutonNon);
                
                Optional<ButtonType> choix = boiAlert.showAndWait();
                if (choix.get() == boutonOui) {
                    //Lancer une charger partie
                }
            }
        });

        Button boutonQuitter = new Button("Quitter");
        boutonQuitter.setGraphic(imageQuitter);            //On l'illustre par une petite image
        boutonQuitter.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonQuitter.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonQuitter.setGraphic(imageQuitterHover);
            boutonQuitter.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 12pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonQuitter.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonQuitter.setGraphic(imageQuitter);
            boutonQuitter.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 8pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonQuitter.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            quiiterJeu();
        });

        boxBarreMenu.getChildren().addAll(boutonNouvellePartie, boutonSauvegarde, boutonCharger, boutonQuitter);


        
        grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);         //On fait la mise à jour de la grille des navires
        grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);         //On fait la mise à jour de la grille de tirs
        
        
        rootJeu.add(grilleBoutonNavire.getRoot(),0,6,3,5);           //On place la grille des navires à gauche
        rootJeu.add(grilleBoutonTirs.getRoot(),3,6,3,5);            //On place la grille des tirs à droite 
        
        Label instructionsJoueur = new Label("Veuillez sélectionner un bateau ou une case");            //On informe le joueur des instructions à suivre
        instructionsJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 18pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        rootJeu.add(instructionsJoueur,2,12,5,1);           //On place les instructions 
        Label titreGrilleNavire = new Label("Voici la grille de vos bateaux :");            //On informe le joueur que c'est la grille de ses navires
        titreGrilleNavire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;");
        rootJeu.add(titreGrilleNavire,0,5,3,1);         //On place les instructions
        Label titreGrilleTirs = new Label("Voici la grille de vos tirs :");             //On informe le joueur que c'est la grille des tirs
        titreGrilleTirs.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;");
        rootJeu.add(titreGrilleTirs,3,5,3,1);           //On place les instructions
        rootJeu.setAlignment(CENTER);
        
        VBox allElements = new VBox();
        allElements.getChildren().addAll(boxBarreMenu, rootJeu);


        Scene sceneJeu = new Scene(allElements);            //On met le root dans la scène 
        JeuGraphique.fenetreJeu.setScene(sceneJeu);         //On modifie la scène
        JeuGraphique.fenetreJeu.show();         //On affiche la scène 
        
        Affichage.afficher(0, 0, JeuGraphique.plateauDeJeu);            
        Affichage.afficher(0, 1, JeuGraphique.plateauDeJeu);
    }


    public void quiiterJeu(){

        if (JeuGraphique.partieSauvegarde==false){
            ImageView imageOui=new ImageView(getClass().getResource("/images/yes.png").toString());            //On donne l'emplacement de l'image
            ImageView imageOuiHover =new ImageView(getClass().getResource("/images/yesHover.png").toString());
            ImageView imageNon =new ImageView(getClass().getResource("/images/no.png").toString());
            ImageView imageNonHover =new ImageView(getClass().getResource("/images/noHover.png").toString());


            VBox rootText = new VBox(25);
            Label information = new Label ("Voulez vous quitter sans sauvegarder ?");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");

            HBox rootBouton = new HBox(80);
            Button boutonOui = new Button("Oui");
            boutonOui.setGraphic(imageOui);            //On l'illustre par une petite image
            boutonOui.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonOui.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonOui.setGraphic(imageOuiHover);
                boutonOui.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 16pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonOui.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonOui.setGraphic(imageOui);
                boutonOui.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonOui.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                JeuGraphique.fenetreJeu.close();
                MenuGraphique menuGraphique =new MenuGraphique();
                menuGraphique.menuPrincipalGraphique();
            });


            Button boutonNon = new Button("Non");
            boutonNon.setGraphic(imageNon);            //On l'illustre par une petite image
            boutonNon.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            boutonNon.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                boutonNon.setGraphic(imageNonHover);
                boutonNon.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 16pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
            });
            boutonNon.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                boutonNon.setGraphic(imageNon);
                boutonNon.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"          //On change les caractéristiques d'écriture
                        + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
            });
            boutonNon.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
                AffichageSauvegardeGraphique sauvegarde = new AffichageSauvegardeGraphique();
                try {
                    sauvegarde.sauvegarde(true);
                } catch (ClassNotFoundException e) {
                    System.err.println("Erreur! La sauvegarde n'as pas pu être lancée");
                }
            });

            rootBouton.getChildren().addAll(boutonOui, boutonNon);
            rootBouton.setAlignment(Pos.CENTER);


            rootText.getChildren().addAll(information, rootBouton);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneTirEchec = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);
        }
        else{
            JeuGraphique.fenetreJeu.close();
            MenuGraphique menuGraphique =new MenuGraphique();
            menuGraphique.menuPrincipalGraphique();
        }
    }



    
    public void selectionNavire(int xPlateau, int yPlateau){
        
        char lRef = (char) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 0,0);          //On déclare la variable qui stocke la lettre de référence du navire
        int nPlateau = (int) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 0,1);            //On déclare la variable qui stocke le numéro du navire sur la plateau
        int pListe = Flotte.nPlateauToPListe(lRef, nPlateau);           //On déclare la variable qui stocke la position du navire dans la liste
        int tailleNavire= JeuGraphique.flotteJoueur0.get(pListe).taille;            //On déclare la variable qui stocke la taille du navire
        int directionNavire = JeuGraphique.flotteJoueur0.get(pListe).direction;         //On déclare la variable qui stoke la direction du navire
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D', lRef);          //On déclare une grille de bouton pour la grille des navires
        grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);         //On le met à jour
        
        ImageView imageTir =new ImageView(getClass().getResource("/images/tir.png").toString());            //On donne l'emplacement de l'image
        ImageView imageTirHover =new ImageView(getClass().getResource("/images/tirHover.png").toString());
        ImageView imageBouger =new ImageView(getClass().getResource("/images/bouger.png").toString());
        ImageView imageBougerHover =new ImageView(getClass().getResource("/images/bougerHover.png").toString());
        ImageView retourImage = new ImageView ("/images/retourImage.png");
        ImageView retourImageHover = new ImageView ("/images/retourImageHover.png");

        VBox rootselectionNavire = new VBox(40);            //On déclare un affichage vertical avec des éléments espacés de 40 pixels
        
        rootselectionNavire.setPadding(new Insets(90,20,20,70));           //On donne les dimensions du root pour la sélection des navires

        
        GrilleNavire affichageNavire = new GrilleNavire(tailleNavire);          //On déclare une grille de boutons qui fait la taille du navire sélectionné
        
        GridPane rootAffichagePlateau = new GridPane();         //On déclare un root pour placer le navire selectionné par la suite
        ColumnConstraints  colonneContrainte = new ColumnConstraints (); 
        colonneContrainte.setPercentWidth(50);
        colonneContrainte.setHalignment(HPos.CENTER);       
        rootAffichagePlateau.getColumnConstraints().add(colonneContrainte);         
        

        
        Button boutonRetour = new Button("Retour");            //On déclare un bouton tirer
        boutonRetour.setGraphic(retourImage);            //On l'illustre par une petite image
        boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonRetour.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonRetour.setGraphic(retourImageHover);
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonRetour.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonRetour.setGraphic(retourImage);
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonRetour.setOnAction((ActionEvent eventChargementPartie) -> {         
            affichageJoueur();
        });
        rootAffichagePlateau.add(boutonRetour,0,0);

        
        rootAffichagePlateau.add(grilleBoutonNavire.getRoot(),0,1);         //On place la grille des boutons dans le root
        rootAffichagePlateau.add(affichageNavire.getRoot(directionNavire, lRef, nPlateau,(String) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 3, 0)),1,1);            //On place le navire dans le root
        
        Label instruction = new Label ("Que souhaiter vous faire ?");           //On demande au joueur ce qu'il veut faire
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"      //On change les caractéristiques d'écriture   
                + "-fx-font-weight: bold;");
        
        Button boutonTire = new Button("Tirer");            //On déclare un bouton tirer
        boutonTire.setGraphic(imageTir);            //On l'illustre par une petite image
        boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonTire.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonTire.setGraphic(imageTirHover);
            boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonTire.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonTire.setGraphic(imageTir);
            boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonTire.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            JeuGraphique.partieSauvegarde=false;
            selectionCaseTir(pListe);           //On appelle la méthode qui lui demander où tirer
        });
        
        
        Button boutonBouger = new Button("Bouger Navire");          //On déclare un bouton déplacer
        boutonBouger.setGraphic(imageBouger);           //On l'illustre par une petite image
        boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonBouger.setOnMouseEntered ((MouseEvent event) -> {          //Si le joueur met son curseur sur le bouton
            boutonBouger.setGraphic(imageBougerHover);
            boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonBouger.setOnMouseExited ((MouseEvent event) -> {          //Si le joueur enlève son curseur du bouton
            boutonBouger.setGraphic(imageBouger);
            boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonBouger.setOnAction((ActionEvent eventChargementPartie) -> {
            boolean verif = verificationDeplacementPossible(pListe);
            if (verif==true)selectionCaseBouger(pListe, lRef);
        });
        
        HBox boutonSelectionNavire = new HBox(80);          //On déclare un affichage horizontal avec les éléments qui sont espacés de 80 pixels
        boutonSelectionNavire.getChildren().addAll(boutonTire,boutonBouger);            //On rajoute le bouton tirer et le bouton déplacer au root
        
        rootselectionNavire.setAlignment(CENTER);           //On positionne les éléments du root au centre
        rootselectionNavire.getChildren().addAll(rootAffichagePlateau, instruction, boutonSelectionNavire);             //On rajoute des éléments a ce root     
        instruction.setAlignment(CENTER);           //On positionne les éléments du root au centre
        boutonSelectionNavire.setAlignment(CENTER);         //On positionne les éléments du root au centre
        
        
        Scene sceneSelectionNavire = new Scene(rootselectionNavire);            //On met le root dans la scène
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);         //On modifie la scène
    }
    
    public void selectionCaseTir(int pListe){
        if (JeuGraphique.flotteJoueur0.get(pListe).lRef=='D' && JeuGraphique.flotteJoueur0.get(pListe).premierTire==true){
            List<Integer> listeInformations = new ArrayList<Integer> ();
            listeInformations.add(0, pListe);
            GrilleBoutons grilleBoutonTirs = new GrilleBoutons('B', listeInformations);
            grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);

            VBox rootselectionCaseTir = new VBox(20);
            rootselectionCaseTir.setPadding(new Insets(20,300,20,300));

            Label instruction = new Label ("Il s'agit d'u premier du de ce Destroyer !");
            instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                    + "-fx-font-weight: bold;");
            
            Label instruction2 = new Label ("Il s'agit donc d'une fussée éclairante ?");
            instruction2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15pt;"
                    + "-fx-font-weight: bold;");
            
            Label instruction3 = new Label ("Où souhaitez vous la tirer ?");
            instruction3.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 15;"
                    + "-fx-font-weight: bold;");

            rootselectionCaseTir.setAlignment(CENTER);
            rootselectionCaseTir.getChildren().addAll(instruction, instruction2, instruction3, grilleBoutonTirs.getRoot());
            instruction.setAlignment(CENTER);
            Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);
            JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
        }
        else{
            List<Integer> listeInformations = new ArrayList<Integer> ();
            listeInformations.add(0, pListe);
            GrilleBoutons grilleBoutonTirs = new GrilleBoutons('B', listeInformations);
            grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);

            VBox rootselectionCaseTir = new VBox(40);
            rootselectionCaseTir.setPadding(new Insets(90,300,20,300));

            Label instruction = new Label ("Où souhaitez vous tirer ?");
            instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                    + "-fx-font-weight: bold;");

            rootselectionCaseTir.setAlignment(CENTER);
            rootselectionCaseTir.getChildren().addAll(instruction, grilleBoutonTirs.getRoot());
            instruction.setAlignment(CENTER);
            Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);
            JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
        }
    }
    
    
    public void selectionCaseBouger(int pListe, char lRef){
        
        JeuGraphique.partieSauvegarde=false;

        List<Integer> listeInformations = new ArrayList<Integer> ();
        listeInformations.add(0, pListe);
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('C', listeInformations);
        grilleBoutonTirs.miseAJourAffichageBouger(JeuGraphique.plateauDeJeu, pListe, lRef);

        VBox rootselectionCaseTir = new VBox(40);
        rootselectionCaseTir.setPadding(new Insets(90,300,20,300));

        Label instruction = new Label ("Où souhaitez vous bougez votre navire ?");
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                + "-fx-font-weight: bold;");

        rootselectionCaseTir.setAlignment(CENTER);
        rootselectionCaseTir.getChildren().addAll(instruction, grilleBoutonTirs.getRoot());
        instruction.setAlignment(CENTER);
        Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
        
    }
    
    public void bougerNavire(int x, int y, int pListe){
        
        
        VBox rootText = new VBox(25);
        Label information = new Label ("La manoeuvre est en cours");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 20pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        Label information1 = new Label ("Veuillez patientez");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information, information1);
        rootText.setAlignment(Pos.CENTER);
        Scene sceneTirEchec = new Scene(rootText);
        JeuGraphique.fenetreJeu.setScene(sceneTirEchec);
        
        JeuGraphique.bougerNavire(pListe, x, y);
    }
    
    
    public void deplacementEffectueNavire(){
        
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {
            GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D', 'V');
            grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);
            
            VBox rootText = new VBox(25);
            rootText.setPadding(new Insets(5,300,20,300));
            Label information = new Label ("La manoeuvre a bien été effectuée");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, grilleBoutonNavire.getRoot());
            rootText.setAlignment(Pos.CENTER);
            Scene sceneTirEchec = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);
        }));
        time.play();    
        
        try {
            tourIA();
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");
        }
    }
    
    

    public void tirEchec() throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            Label information = new Label ("Nous avons rien touché à ces coordonées");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("C'est au tour de l'IA");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On affiche la scène qui est dans la fenêtre
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie une erreur en cas de problème
        }
    }
    
    public void tirSurSousMarin() throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            Label information = new Label ("Nous avons détecté une structure mais n'avons pas pu la détruire");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("C'est au tour de l'IA");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);            //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On affiche la scène qui est dans la fenêtre
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie une erreur en cas de problème
        }
    }
    
    
    public void tirToucherNavire() throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            Label information = new Label ("C'est touché, bien joué");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 25pt; "
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("\n\n Au tour de l'IA");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On affiche la scène qui est dans la fenêtre
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie une erreur en cas de problème
        }
    }
    
    
    public void tirCoulerNavire(String nomNavireCoule) throws InterruptedException{
        
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            Label information = new Label ("C'est touché, bien joué");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 25pt; "
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("Vous avez coulé un "+nomNavireCoule);
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 18pt;"
            +                "-fx-font-weight: bold;");
            
            Label information2 = new Label ("\n\n Au tour de l'IA");
            information2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1,information2);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneTirEchec = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);         //On affiche la scène qui est dans la fenêtre
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit
        
        try {
            tourIA();           //On lance le tour de l'IA
        } catch (InterruptedException e) {
            System.err.println("Erreur_exceptionIA");           //On renvoie une erreur en cas de problème
        }
    }
    
    
    
    public void tourIA() throws InterruptedException{
    victoire();
        Timeline timeTourJoueur = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(12000),action -> {            //On met un temps d'attente de 12s
            try {
                JeuGraphique.ia.jouer();            //On essaye de lancer le tour de l'IA
            } catch (InterruptedException ex){}
        }));
        timeTourJoueur.play();            //On démarre le temps de décalage dès que le programme le lit
        
    }
    
    public void zoneTirFusee(int xTire, int yTire, int surplusX, int surplusY){
        
        Timeline timeZoneTirFusee = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeZoneTirFusee.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {
            GrilleBoutons grilleBoutonTirs = new GrilleBoutons('D', 'F');
            grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);

            VBox rootselectionCaseTir = new VBox(40);
            rootselectionCaseTir.setPadding(new Insets(90,300,20,300));

            Label instruction2 = new Label ("Voici les information qui ont été rapporté");
            instruction2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                    + "-fx-font-weight: bold;");

            rootselectionCaseTir.setAlignment(CENTER);
            rootselectionCaseTir.getChildren().addAll(instruction2, grilleBoutonTirs.getRoot());
            instruction2.setAlignment(CENTER);
            Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);
            JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
            
            
            Timeline timeTourJoueur = new Timeline();
            timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(12000),e -> {
                
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
            timeTourJoueur.play();
            
        }));
        timeZoneTirFusee.play();
    }
    
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
            for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){          //Si il y a un navire
                    possible=false;
                    i=10;           //On sort de la boucle
                }
            }
            if (possible=true) return true;
            
            possible = true;
            //Cas haut
            xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0];
            yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1]-1;
            for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                if (!JeuGraphique.plateauDeJeu.get(xStart+i, yStart,0,0).equals('_')){
                    possible=false;
                    i=10;
                }
            }
            return possible;
        }
        else{
            int xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]+1;
            int yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
            for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){
                    possible=false;
                    i=10;
                }
            }
            if (possible=true) return true;
            
            possible=true;
            xStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][0]-1;
            yStart = JeuGraphique.flotteJoueur0.get(pListe).coordonnees[0][1];
            for(int i=0; i<JeuGraphique.flotteJoueur0.get(pListe).taille; i++){
                if (!JeuGraphique.plateauDeJeu.get(xStart, yStart+i,0,0).equals('_')){
                    if (possible==false) possible=false;
                    i=10;
                }
            }
            if (possible=true) return true;

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
    
    

    public void erreurCaseVide(){
        
    }
    
    
    public void victoire(){
        ImageView croquette =new ImageView(getClass().getResource("/images/croquette.png").toString());         //On indique la position de l'image 
        ImageView croquetteHover =new ImageView(getClass().getResource("/images/croquetteHover.png").toString());         //On indique la position de l'image 
        
        Button bontonVictoire = new Button ("SUPER !");         //On déclare un bouton 
        bontonVictoire.setGraphic(croquetteHover);          //On l'illustre par une image
        bontonVictoire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
        +                "-fx-font-size: 17pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);");
        bontonVictoire.setOnMouseEntered (e->
        bontonVictoire.setGraphic(croquette));           //On l'illustre par une image
        bontonVictoire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        bontonVictoire.setOnMouseExited (e-> 
        bontonVictoire.setGraphic(croquetteHover));          //On l'illustre par une image
        bontonVictoire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        /*bontonVictoire.setOnAction((ActionEvent eventLancementJeu) -> {
            fenetreJeu.setScene(sceneLancementMenuPrincipal(fenetreJeu));
        });*/

        Label labelAffichageVictoireJoueur = new Label ("VICTOIRE DU JOUEUR !!");           //On informe le joueur qu'il a gagné
        labelAffichageVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        Label labelAffichageVictoireIA = new Label ("VICTOIRE DE L'ORDINATEUR !!");         //On informe le joueur que l'IA a gagné
        labelAffichageVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        Label labelAffichageInfoPartie = new Label ("\nInformation sur la partie : "            //On donne les caractéristiques de la partie
            +"\nNombre de tour : "+compteurTourHumain+compteurTourIA            //Le nombre de tour que l'IA a effecuté
            +"\nNombre de tour du joueur : "+ compteurTourHumain            //Le nombre de tour que le joueur a effectué
            +"\nNombre de tour de l'IA : "+compteurTourIA);
        labelAffichageInfoPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        Label labelAffichageVictoireSousMarin = new Label ("Vous n'avez plus de sous-marin"         //On informe le joueur qu'il a perdu car il a plus de sous-marin
            +"L'ordinateur gagne par fofait de votre part");
        labelAffichageVictoireSousMarin.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");

        GridPane rootVictoire = new GridPane();         //On déclare et paramêtre un root 
        rootVictoire.setPadding(new javafx.geometry.Insets(20));
        rootVictoire.setVgap(20);
        

        boolean etat0;          //On initialise un booléen
        boolean etat0SousMarin=false;           //On initialise un booléen à false pour le sous-marin 
        for (int i=0; i<10; i++){           //On parcourt la flotte du joueur 
            etat0 = flotteJoueur0.get(i).etat;          //Pour chaque navire, on regarde si il a coulé ou pas
            if (etat0==true) break;         //Si il est toujours à flot
            if (i==9 && etat0!=true){           //Si c'est le dernier navire et qu'il vient d'etre coulé

                rootVictoire.add(labelAffichageVictoireIA,0,0);
                rootVictoire.add(labelAffichageInfoPartie,0,1);
                rootVictoire.add(bontonVictoire,1,1);
                /*System.out.println(ROUGE+"VICTOIRE DE L'ORDINATEUR !!"+RESET);            //On affiche un message 
                System.out.println("\nInformations sur la partie : ");
                System.out.println("Nombre de tour : "+(compteurTourHumain+compteurTourIA));
                System.out.println("Nombre de tour du joueur : "+ compteurTourHumain);
                System.out.println("Nombre de tour de l'IA : "+compteurTourIA);*/
                //Scene sceneFlotteFct = new Scene(rootVictoire);
                //return true;            //On renvoie true si l'IA a gagné
            } 
        }
        
        for (int i=6; i<10;i++){            //On parcourt les sous-marin
            if (etat0SousMarin==false) etat0SousMarin=flotteJoueur1.get(i).etat;            //Si le sous marin n'a pas coulé, on prend l'état du sous-marin
        }

        if (etat0SousMarin==false){         //Si il y a plus aucun sous-marin
            //System.out.println(ROUGE+"VICTOIRE DE L'ORDINATEUR !!"+RESET);          //On affiche un message de victoire de l'IA
            rootVictoire.add(labelAffichageVictoireIA,0,0);
            rootVictoire.add(labelAffichageVictoireSousMarin,0,1);
            rootVictoire.add(labelAffichageInfoPartie,0,2);
            rootVictoire.add(bontonVictoire,1,3);
            /*System.out.println("Vous n'avez plus de sous-marin");
            System.out.println("L'ordinateur gagne par fofait de votre part");
            System.out.println("\nInformations sur la partie : ");
            System.out.println("Nombre de tour : "+(compteurTourHumain+compteurTourIA));
            System.out.println("Nombre de tour du joueur : "+ compteurTourHumain);
            System.out.println("Nombre de tour de l'IA : "+compteurTourIA);*/
            //Scene sceneFlotteFct = new Scene(rootVictoire);
            //return true;            //Renvoie true si l'un des deux joueurs a gagné
        }

        boolean etat1SousMarin=false;           //On initialise un booléen à false pour le sous-marin
        boolean etat1;      //On initialise un booléen
        for (int i=0; i<10; i++){           //On parcourt la flotte de l'IA
            etat1 = flotteJoueur1.get(i).etat;          //Pour chaque navire, on regarde si il a coulé ou pas
            if (etat1==true) break;         //Si il est toujours à flot
            if (i==9 && etat1!=true){           //Si c'est le dernier navire et qu'il vient d'etre coulé
                rootVictoire.add(labelAffichageVictoireJoueur,0,0);
                rootVictoire.add(labelAffichageInfoPartie,0,1);
                rootVictoire.add(bontonVictoire,1,1);
                /*System.out.println(ROUGE+"VICTOIRE DU JOUEUR !!"+RESET);           //On affiche un message
                System.out.println("\nInformations sur la partie : ");
                System.out.println("Nombre de tour : "+(compteurTourHumain+compteurTourIA));
                System.out.println("Nombre de tour du joueur : "+ compteurTourHumain);
                System.out.println("Nombre de tour de l'IA : "+compteurTourIA); */
                //Scene sceneFlotteFct = new Scene(rootVictoire);
                //return true;            //On renvoie true si le joueur a gagné
            }
        }
        
        for (int i=6; i<10;i++){            //On parcourt les sous-marins
            if (etat1SousMarin==false) etat1SousMarin=flotteJoueur1.get(i).etat;            //Si le sous marin n'a pas coulé, on prend l'état du sous-marin
        }

        if (etat1SousMarin==false){         //Si il y a plus aucun sous-marin
            rootVictoire.add(labelAffichageVictoireJoueur,0,0);
            rootVictoire.add(labelAffichageVictoireSousMarin,0,1);
            rootVictoire.add(labelAffichageInfoPartie,0,2);
            rootVictoire.add(bontonVictoire,1,3);
            /*System.out.println(ROUGE+"VICTOIRE DU JOUEUR !!"+RESET);            //On affiche un message de victoire du joueur
            System.out.println("Vous avez détruit tout les sous-marin de l'ordinateur");
            System.out.println("Vous gagnez par forfait");
            System.out.println("\nInformations sur la partie : ");
            System.out.println("Nombre de tour : "+(compteurTourHumain+compteurTourIA));
            System.out.println("Nombre de tour du joueur : "+ compteurTourHumain);
            System.out.println("Nombre de tour de l'IA : "+compteurTourIA);*/
            //Scene sceneFlotteFct = new Scene(rootVictoire);
            //return true;            //Renvoie true si l'un des deux joueurs a gagné
        }
    }
}
