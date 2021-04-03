package bataillenavalgraphique;

import bataillenavalgraphique.bataillenaval.controller.IANGraphique;
import bataillenavalgraphique.bataillenaval.model.Plateau;
import bataillenavalgraphique.bataillenaval.view.Affichage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Théric PHERON
 */
public class AffichageSauvegardeGraphique {
    
    int emplacementVide =10;
    
    public AffichageSauvegardeGraphique(){
        
    }
    
    public void sauvegarde() throws ClassNotFoundException{
        
        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [10];
        VBox vBoxBouton = new VBox(2);
        
        //List<Button> boutonPartie = new ArrayList<Button>();
        for (int i=0; i<10; i++){
            
            String nomSauvegarde = "saveFiles/save"+String.valueOf(i); 

            
            try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {
                    nomPartie[i]= (String) in.readObject();
            }
            catch(IOException e){
                System.out.println(i);
                System.out.println("Le fichier save n'a pas pu être ouvert");
            }
            
            if (!nomPartie[i].equals("[VIDE]")){
                Button boutonLancementPartie = new Button(nomPartie[i]);
                boutonLancementPartie.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
                boutonLancementPartie.setOnMouseEntered ((MouseEvent event) -> {
                    boutonLancementPartie.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");
                });
                boutonLancementPartie.setOnMouseExited ((MouseEvent event) -> {
                    boutonLancementPartie.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
                });
                boutonLancementPartie.setOnAction((ActionEvent eventLancementJeu) -> {
                    
                });
                vBoxBouton.getChildren().add(boutonLancementPartie);
            }
            else {
                emplacementVide=i;
            }
        }
        
        ScrollPane scrollBarBouton = new ScrollPane();
        scrollBarBouton.setContent(vBoxBouton);

        
        HBox rootScrollSauvegarde= new HBox(10);
        rootScrollSauvegarde.setPadding(new Insets(280,10, 10,80));
        rootScrollSauvegarde.getChildren().addAll(scrollBarBouton);
        
        
        ImageView imageNouveau =new ImageView(getClass().getResource("/images/tir.png").toString());            //On donne l'emplacement de l'image
        ImageView imageNouveauHover =new ImageView(getClass().getResource("/images/tirHover.png").toString());
        
        Button nouveau = new Button("Nouvelle Sauvegarde");
        nouveau.setGraphic(imageNouveau);            //On l'illustre par une petite image
        nouveau.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        nouveau.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            nouveau.setGraphic(imageNouveauHover);
            nouveau.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        nouveau.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            nouveau.setGraphic(imageNouveau);
            nouveau.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        nouveau.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            if (emplacementVide!=10){
                String nomPartieS ="A";
                
                Sauvegarde lancementSauvegarde = new Sauvegarde();
                lancementSauvegarde.savePartie(2, nomPartieS);
            }
            
        });
        
        VBox rootSauvegarde = new VBox(25);
        rootSauvegarde.getChildren().addAll(rootScrollSauvegarde,nouveau);
        
        Scene scene = new Scene(rootSauvegarde, 300, 250);
        JeuGraphique.fenetreJeu.setScene(scene);
    }
    
    
    public void sauvegardeEnCours(){

        VBox rootText = new VBox(25);
        Label information0 = new Label ("La sauvegarde est en cours");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +               " -fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-size: 30pt;"
        +                "-fx-font-weight: bold;");

        Label information1 = new Label ("\n\nPatienter...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 20pt;"
        +                "-fx-font-weight: bold;");


        rootText.getChildren().addAll(information0, information1);
        rootText.setAlignment(Pos.CENTER);
        Scene sceneNiveau1TirRandom = new Scene(rootText);
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);

    }
    
    
    
    
    public void sauvegardeEffectuee(){
        Timeline timeSauvegardeEffectuee= new Timeline();
        timeSauvegardeEffectuee.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),e -> {
            VBox rootText = new VBox(25);
            Label information0 = new Label ("La sauvegarder a bien eu lieu");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +               " -fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-size: 30pt;"
            +                "-fx-font-weight: bold;");
            
            Label information1 = new Label ("\n\nRetour au Menu Principal");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneNiveau1TirRandom = new Scene(rootText);
            
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
            
            Timeline timeLancementMenu = new Timeline();
            timeLancementMenu.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),k -> {
                
                JeuGraphique.fenetreJeu.close();
                
                MenuGraphique menuPrincipal = new MenuGraphique();
                menuPrincipal.menuPrincipalGraphique();
            }));
            timeLancementMenu.play();
        }));
        timeSauvegardeEffectuee.play();
    }
    
    
    public void suppressionEffectue(Stage stage){

        VBox rootText = new VBox(25);
        Label information0 = new Label ("La suppresion de la partie a bien eu lieu");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +               " -fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-size: 20pt;"
        +                "-fx-font-weight: bold;");

        Label information1 = new Label ("\n\nRetour au Menu de Chargement");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");


        rootText.getChildren().addAll(information0, information1);
        rootText.setAlignment(Pos.CENTER);
        Scene sceneNiveau1TirRandom = new Scene(rootText);

        stage.setScene(sceneNiveau1TirRandom);

        Timeline timeLancementMenu = new Timeline();
        timeLancementMenu.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),k -> {

            AffichageSauvegardeGraphique affichageSauvegarde = new AffichageSauvegardeGraphique();
            try {
                affichageSauvegarde.lancementChargement(stage);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AffichageSauvegardeGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        timeLancementMenu.play();
    }
    
    
    
    public void lancementChargement(Stage stage) throws ClassNotFoundException{
        
        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [10];
        VBox vBoxBouton = new VBox(2);
        //List<Button> boutonPartie = new ArrayList<Button>();
        for (int i=0; i<10; i++){
            
            String nomSauvegarde = "saveFiles/save"+String.valueOf(i); 

            try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {
                    nomPartie[i]= (String) in.readObject();
            }
            catch(IOException e){
                System.out.println("Le fichier save n'a pas pu être ouvert");
            }
            
            if (nomPartie[i]!=null){
                Button boutonLancementPartie = new Button(nomPartie[i]);
                boutonLancementPartie.setPrefSize(500, 30);
                boutonLancementPartie.setAlignment(Pos.CENTER_LEFT);
                boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                
                boutonLancementPartie.setOnMouseEntered ((MouseEvent event) -> {
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                                    +                "-fx-font-size: 15pt;"
                                    +                "-fx-font-weight: bold;"
                                    +                   "-fx-background-color: rgba(82,127,143,0.50);");
                });
                
                boutonLancementPartie.setOnMouseExited ((MouseEvent event) -> {
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                });
                boutonLancementPartie.setOnAction((ActionEvent eventLancementJeu) -> {
                    try {
                        preViewPartie(nomSauvegarde, stage);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AffichageSauvegardeGraphique.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                vBoxBouton.getChildren().add(boutonLancementPartie);
            }
        }
        
        ScrollPane scrollBarBouton = new ScrollPane();
        scrollBarBouton.setStyle("-fx-background-color:transparent;");
        scrollBarBouton.setContent(vBoxBouton);
        VBox vBoxScrollBarBouton = new VBox(scrollBarBouton);
        vBoxScrollBarBouton.setAlignment(Pos.BOTTOM_LEFT);
        
        
        Label titre = new Label ("Chargement");
        titre.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 35pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");
        
        
        ImageView retourHome = new ImageView ("/images/retourHome.png");
        ImageView retourHomeHover = new ImageView ("/images/retourHomeHover.png"); 
        
        Button boutonHome = new Button ("Menu Principal");
        boutonHome.setGraphic(retourHome);
        boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonHome.setOnMouseEntered (e-> {
            boutonHome.setGraphic (retourHomeHover);
            boutonHome.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");
                });
        boutonHome.setOnMouseExited (e-> {
            boutonHome.setGraphic (retourHome);
            boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
                });
        boutonHome.setOnAction((ActionEvent eventLancementJeu) -> {
            MenuGraphique menuPrincipal = new MenuGraphique();
            stage.setScene(menuPrincipal.sceneLancementMenuPrincipal(stage));
        }); 
        
        
        VBox rootLancementPartie = new VBox(10);
        rootLancementPartie.setPadding(new Insets(10,10,30,80));
        
        rootLancementPartie.getChildren().add(titre);
        rootLancementPartie.getChildren().add(boutonHome);
        rootLancementPartie.getChildren().add(vBoxScrollBarBouton);
        VBox.setVgrow(vBoxScrollBarBouton, Priority.ALWAYS);
        
        
        
        Scene scene = new Scene(rootLancementPartie, 300, 250);
        stage.setScene(scene);
    }
    
    public void preViewPartie(String nomSauvegarde ,Stage stage) throws ClassNotFoundException{
        
        Plateau plateauDeJeuCopy = new Plateau();
        try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {
            
            in.readObject();            //On jette les information qui nous non utile
            in.readInt();
            in.readInt();
            in.readObject();
            in.readBoolean();
            in.readInt();
            in.readInt();

            //Onrécupère les informations du plateau
            for(int x=0; x<15 ; x++){           
                for (int y=0; y<15 ;y++){
                    plateauDeJeuCopy.modification(x, y, 0, 0, in.readChar());
                    plateauDeJeuCopy.modification(x, y, 0, 1, in.readInt());
                    plateauDeJeuCopy.modification(x, y, 1, 0, (String) in.readObject());
                    plateauDeJeuCopy.modification(x, y, 1, 1, (String) in.readObject());

                    plateauDeJeuCopy.modification(x, y, 2, 0, in.readChar());
                    plateauDeJeuCopy.modification(x, y, 2, 1, in.readInt());
                    plateauDeJeuCopy.modification(x, y, 3, 0, (String) in.readObject());
                    plateauDeJeuCopy.modification(x, y, 3, 1, (String) in.readObject());
                }
            }
            
        } catch (IOException e) {
            System.out.println("Erreur_chargementPartie! "+"Le fichier n'a pas pu être ouvert.");           //On affiche un message d'erreur
        }
        
        Affichage.afficher(0,0,plateauDeJeuCopy);
        
        ImageView imageSupprimer=new ImageView(getClass().getResource("/images/poubelle.png").toString());            //On donne l'emplacement de l'image
        ImageView imageSupprimerHover =new ImageView(getClass().getResource("/images/poubelleHover.png").toString());
        ImageView imageCharger =new ImageView(getClass().getResource("/images/charger.png").toString());
        ImageView imageChargerHover =new ImageView(getClass().getResource("/images/chargerHover.png").toString());
        ImageView imageRetour = new ImageView ("/images/retourImage.png");
        ImageView imageRetourHover = new ImageView ("/images/retourImageHover.png");
        
        
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D');          //On déclare la grille des boutons pour les navires 
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('D');            //On déclare la grille des boutons pour les tirs
        
        grilleBoutonNavire.miseAJourAffichageNavire(plateauDeJeuCopy);         //On fait la mise à jour de la grille des navires
        grilleBoutonTirs.miseAJourAffichageTirs(plateauDeJeuCopy);         //On fait la mise à jour de la grille de tirs
        
        GridPane rootNavire = new GridPane();          //On déclare un root de type GridPane et on le paramètre        
        rootNavire.setHgap(5);
        ColumnConstraints  colonneContrainte = new ColumnConstraints ();            //On déclare et paramètre une colonne 
        colonneContrainte.setPercentWidth(60);
        colonneContrainte.setHalignment(HPos.CENTER);           //On positionne les éléments du root au centre
        rootNavire.getColumnConstraints().add(colonneContrainte);         //On ajoute cette colonne au root
        
        
        rootNavire.add(grilleBoutonNavire.getRoot(),0,0);           //On place la grille des navires à gauche
        rootNavire.add(grilleBoutonTirs.getRoot(),2,0);            //On place la grille des tirs à droite 
        
        Label instructionsJoueur = new Label("Que souhaitez vous faire ?");            //On informe le joueur des instructions à suivre
        instructionsJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 14pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        
        
        Button boutonRetour = new Button("Retour");            //On déclare un bouton tirer
        boutonRetour.setGraphic(imageRetour);            //On l'illustre par une petite image
        boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonRetour.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonRetour.setGraphic(imageRetourHover);
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonRetour.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonRetour.setGraphic(imageRetour);
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonRetour.setOnAction((ActionEvent eventChargementPartie) -> {         try {         //Action si le joueur click sur le bouton
            lancementChargement(stage);
            } catch (ClassNotFoundException ex) {
                System.err.println("Erreur! Problème sur le retour");
            }
        });
        Button transparent = new Button();            //On déclare un bouton tirer
        transparent.setPrefSize(75,10);
        transparent.setStyle ("-fx-background-color: transparent;");          //On change les caractéristiques d'écriture
        
        
        
        Button boutonCharger = new Button("Charger");            //On déclare un bouton tirer
        boutonCharger.setGraphic(imageCharger);            //On l'illustre par une petite image
        boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonCharger.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonCharger.setGraphic(imageChargerHover);
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonCharger.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonCharger.setGraphic(imageCharger);
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonCharger.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur click sur le bouton
            ecranChargement(nomSauvegarde, stage);
        });
        
        
        Button boutonSupprimer = new Button("Supprimer");          //On déclare un bouton déplacer
        boutonSupprimer.setGraphic(imageSupprimer);           //On l'illustre par une petite image
        boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonSupprimer.setOnMouseEntered ((MouseEvent event) -> {          //Si le joueur met son curseur sur le bouton
            boutonSupprimer.setGraphic(imageSupprimerHover);
            boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonSupprimer.setOnMouseExited ((MouseEvent event) -> {          //Si le joueur enlève son curseur du bouton
            boutonSupprimer.setGraphic(imageSupprimer);
            boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonSupprimer.setOnAction((ActionEvent eventChargementPartie) -> {
            Alert alertBox = new Alert(AlertType.CONFIRMATION);
            alertBox.setTitle("Bataille Navale - Confirmation supression");
            alertBox.setHeaderText("La suppresion de cette partie est définitif.");
            alertBox.setContentText("Êtes vous sûr de vouloir supprimer cette partie ?");
            
            ButtonType boutonOui = new ButtonType("Oui");
            ButtonType boutonNon = new ButtonType("Non");
            
            alertBox.getButtonTypes().setAll(boutonOui,boutonNon);
            
            Optional<ButtonType> choix = alertBox.showAndWait();
            if (choix.get() == boutonOui) {
                Sauvegarde suppression = new Sauvegarde();
                suppression.supprimerPartie(nomSauvegarde, stage);
            }
        });
        
        HBox boutonSelectionOption = new HBox(80);          //On déclare un affichage horizontal avec les éléments qui sont espacés de 80 pixels
        boutonSelectionOption.getChildren().addAll(boutonRetour,transparent, instructionsJoueur, boutonCharger,boutonSupprimer);
        boutonSelectionOption.setAlignment(Pos.CENTER);
        
        VBox rootAllElement = new VBox(15);
        rootAllElement.setPadding(new Insets(15,15,15,10));   
        rootAllElement.getChildren().addAll(rootNavire, boutonSelectionOption);
        
        Scene sceneJeu = new Scene(rootAllElement);            //On met le root dans la scène 
        stage.setScene(sceneJeu);
    }
    
    public void ecranChargement(String nomSauvegarde, Stage stage){
        VBox rootText = new VBox(25);
        Label information = new Label ("Nous chargons la partie sélectionnée");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 20pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        Label information1 = new Label ("\n\nVeuillez patientez...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information, information1);
        rootText.setAlignment(Pos.CENTER);
        Scene sceneEcranChargement = new Scene(rootText);
        stage.setScene(sceneEcranChargement);
        
        
        Timeline timeChargement = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeChargement.getKeyFrames().addAll(new KeyFrame(Duration.millis(3000),action -> {            //On met un temps d'attente de 12s
            Sauvegarde chargementSauvegarde = new Sauvegarde();
            try {
                chargementSauvegarde.chargementPartie(nomSauvegarde);
                stage.close();
            } catch (ClassNotFoundException | InterruptedException ex) {
                System.out.println("Erreur! Le chargement n'as pas pu être effectué");
            }
        }));
        timeChargement.play();  
        
        
    }
    
    
}
