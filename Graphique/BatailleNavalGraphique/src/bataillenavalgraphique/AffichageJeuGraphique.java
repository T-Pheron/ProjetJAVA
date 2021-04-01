package bataillenavalgraphique;

import bataillenavalgraphique.bataillenaval.model.Flotte;
import static bataillenavalgraphique.JeuGraphique.*;
import bataillenavalgraphique.bataillenaval.view.Affichage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
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

        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('N');
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('T');
        compteurTourHumain++; 
        
        GridPane rootJeu = new GridPane();
        rootJeu.setPadding(new Insets(20));
        rootJeu.setHgap(30);
        rootJeu.setVgap(20);
        
        grilleBoutonNavire.miseAJourAffichageNavire(plateauDeJeu);
        grilleBoutonTirs.miseAJourAffichageTirs(plateauDeJeu);
        
        rootJeu.add(menu(),0,0,3,5);
        
        rootJeu.add(grilleBoutonNavire.getRoot(),0,6,3,5);
        rootJeu.add(grilleBoutonTirs.getRoot(),3,6,3,5);
        
        Label instructionsJoueur = new Label("Veuillez sélectionner un navire");
        rootJeu.add(instructionsJoueur,2,12,3,1);
        Label titreGrilleNavire = new Label("Voici la grille de vos bateaux :");
        rootJeu.add(titreGrilleNavire,0,5,3,1);
        Label titreGrilleTirs = new Label("Voici la grille de vos tirs :");
        rootJeu.add(titreGrilleTirs,3,5,3,1);
        
        Scene sceneJeu = new Scene(rootJeu);
        JeuGraphique.fenetreJeu.setScene(sceneJeu);
        JeuGraphique.fenetreJeu.show();
        
        Affichage.afficher(0, 0, plateauDeJeu);
    }
    
    public void selectionNavire(int xPlateau, int yPlateau){
        
        char lRef = (char) plateauDeJeu.get(xPlateau, yPlateau, 0,0);
        int nPlateau = (int) plateauDeJeu.get(xPlateau, yPlateau, 0,1);
        int pListe = Flotte.nPlateauToPListe(lRef, nPlateau);
        int tailleNavire= flotteJoueur0.get(pListe).taille;
        int directionNavire = flotteJoueur0.get(pListe).direction;
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D');
        grilleBoutonNavire.miseAJourAffichageNavire(plateauDeJeu);
        
        ImageView imageTir =new ImageView(getClass().getResource("/images/tir.png").toString());
        ImageView imageTirHover =new ImageView(getClass().getResource("/images/tirHover.png").toString());
        ImageView imageBouger =new ImageView(getClass().getResource("/images/bouger.png").toString());
        ImageView imageBougerHover =new ImageView(getClass().getResource("/images/bougerHover.png").toString());
        
        VBox rootselectionNavire = new VBox(40);
        
        rootselectionNavire.setPadding(new Insets(90,100,20,90));

        
        GrilleNavire affichageNavire = new GrilleNavire(tailleNavire);
        
        GridPane rootAffichagePlateau = new GridPane();
        ColumnConstraints  colonneContrainte = new ColumnConstraints ();
        colonneContrainte.setPercentWidth(50);
        colonneContrainte.setHalignment(HPos.CENTER);
        rootAffichagePlateau.getColumnConstraints().add(colonneContrainte);
        
        
        rootAffichagePlateau.add(grilleBoutonNavire.getRoot(),0,0);
        rootAffichagePlateau.add(affichageNavire.getRoot(directionNavire, lRef, nPlateau),1,0);
        
        Label instruction = new Label ("Que souhaiter vous faire ?");
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                + "-fx-font-weight: bold;");
        
        Button boutonTire = new Button("Tirer");
        boutonTire.setGraphic(imageTir);
        boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonTire.setOnMouseEntered ((MouseEvent event) -> {
            boutonTire.setGraphic(imageTirHover);
            boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonTire.setOnMouseExited ((MouseEvent event) -> {
            boutonTire.setGraphic(imageTir);
            boutonTire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonTire.setOnAction((ActionEvent eventChargementPartie) -> {
            selectionCaseTir(pListe);
        });
        
        
        Button boutonBouger = new Button("Bouger Navire");
        boutonBouger.setGraphic(imageBouger);
        boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonBouger.setOnMouseEntered ((MouseEvent event) -> {
            boutonBouger.setGraphic(imageBougerHover);
            boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonBouger.setOnMouseExited ((MouseEvent event) -> {
            boutonBouger.setGraphic(imageBouger);
            boutonBouger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonBouger.setOnAction((ActionEvent eventChargementPartie) -> {
            //ajouter
        });
        
        HBox boutonSelectionNavire = new HBox(80);
        boutonSelectionNavire.getChildren().addAll(boutonTire,boutonBouger);
        
        rootselectionNavire.setAlignment(CENTER);
        rootselectionNavire.getChildren().addAll(rootAffichagePlateau, instruction, boutonSelectionNavire);
        instruction.setAlignment(CENTER);
        boutonSelectionNavire.setAlignment(CENTER);
        
        
        Scene sceneSelectionNavire = new Scene(rootselectionNavire);
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
    }
    
    public void selectionCaseTir(int pListe){
        
        int tailleNavire= flotteJoueur0.get(pListe).taille;
        int directionNavire = flotteJoueur0.get(pListe).direction;
        List listeInformations = new ArrayList ();
        listeInformations.add(0, pListe);
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('B', listeInformations);
        grilleBoutonTirs.miseAJourAffichageTirs(plateauDeJeu);
        
        VBox rootselectionCaseTir = new VBox(40);
        rootselectionCaseTir.setPadding(new Insets(90,300,20,300));

        Label instruction = new Label ("Que souhaiter vous faire ?");
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                + "-fx-font-weight: bold;");
        
        rootselectionCaseTir.setAlignment(CENTER);
        rootselectionCaseTir.getChildren().addAll(instruction, grilleBoutonTirs.getRoot());
        instruction.setAlignment(CENTER);

        Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
    }

    public void tirEchec() throws InterruptedException{
        
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(2000),action -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("Nous avons rien touché à ses coordonées");
            Label information1 = new Label ("C'est au tour de l'IA");

            rootText.getChildren().addAll(information, information1);
            Scene sceneTirEchec = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneTirEchec);
        }));
        time.play();
        
        tourIA();
    }
    
    public void tourIA(){
        
    }

    public void erreurCaseVide(){
        
    }
    
    public MenuBar menu(){
        MenuBar menuBar = new MenuBar();
        BorderPane rootMenuBar = new BorderPane();
        menuBar.setUseSystemMenuBar(true);
        rootMenuBar.setTop(menuBar);

        Menu menuNouvellePartie = new Menu("Nouvelle Partie");
        Menu menuSauvegarderPartie = new Menu("Sauvegarder Partie");
        Menu menuChargerPartie = new Menu("Charger Partie");
        Menu menuAide = new Menu("Aide");
        Menu menuQuitterPartie = new Menu("Quitter");

        MenuItem charger1 = new MenuItem("Charger la sauvegarde 1");
        MenuItem charger2 = new MenuItem("Charger la sauvegarde 2");
        MenuItem charger3 = new MenuItem("Charger la sauvegarde 3");

        menuBar.getMenus().addAll(menuNouvellePartie,menuSauvegarderPartie,menuChargerPartie,menuAide,menuQuitterPartie);

        menuChargerPartie.getItems().addAll(charger1,charger2,charger3);

        menuNouvellePartie.setOnAction((ActionEvent e)-> {
            System.out.println("on esr");
            Alert boiteDialogue = new Alert(AlertType.CONFIRMATION);
            boiteDialogue.setTitle("Attention !");
            boiteDialogue.setHeaderText("Vous vous appretez à quitter cette partie ! ");
            boiteDialogue.setContentText("Que voulez vous faire ? ");
            ButtonType boutonSauvQuit = new ButtonType("Sauvegarder et quitter");
            ButtonType boutonQuitSansSav = new ButtonType("Quitter sans sauvegarder");
            ButtonType boutonAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
            boiteDialogue.getButtonTypes().setAll(boutonSauvQuit, boutonQuitSansSav, boutonAnnuler);
            Optional<ButtonType> choix = boiteDialogue.showAndWait();
            if (choix.get() == boutonSauvQuit) {
                System.out.println("sauv quit");//Programme pour sauvegarder et quitter
            }
            else if (choix.get() == boutonQuitSansSav) {
                System.out.println("quit 100 sauv");//Programme pour quitter sans sauvegarder
            }
            else {
                System.out.println("go back bitch");//Je crois qu'on continue comme si de rien n'était
            } 
        });
        menuSauvegarderPartie.setOnAction((ActionEvent e)-> {
            //On lui demande si il veut vraiment sauvegarder et on saucvegarde 
        });
        menuAide.setOnAction((ActionEvent e)-> {
            //On ouvre l'aide 
        });
        menuQuitterPartie.setOnAction((ActionEvent e)-> {
            //On lui demande si il veut sauvegarder et on quitte 
        });
        charger1.setOnAction((ActionEvent e)-> {
            //On lui demande si il veut sauvegarder et quitter la partie en cours et on charge la partie 1 
        });
        charger2.setOnAction((ActionEvent e)-> {
            //On lui demande si il veut sauvegarder et quitter la partie en cours et on charge la partie 2 
        });
        charger3.setOnAction((ActionEvent e)-> {
            //On lui demande si il veut sauvegarder et quitter la partie en cours et on charge la partie 3 
        });


        return menuBar;

    }
}
