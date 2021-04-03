package bataillenavalgraphique;

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
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Théric PHERON
 */
public class AffichageSauvegardeGraphique {
    
    String emplacementVide =null;
    
    public AffichageSauvegardeGraphique(){
        
    }
    
    public void sauvegarde(boolean sauvegardeEtQuitter) throws ClassNotFoundException{
        
        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [10];
        VBox vBoxBouton = new VBox(2);

        Label informationEmplacmentSauvegarde = new Label ("Sauvegardes déjà présentes :");
        informationEmplacmentSauvegarde.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 16pt; ");
        vBoxBouton.getChildren().add(informationEmplacmentSauvegarde);

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
                    TextInputDialog boiteSaisie = new TextInputDialog("Nom Partie");
                    boiteSaisie.setTitle("Bataille Navale - Nom Partie");
                    boiteSaisie.setHeaderText("Veuillez saisir le nom de la partie :");
                    boiteSaisie.setContentText("Nom de la partie :");

                    Optional<String> nomPartieSaisie = boiteSaisie.showAndWait();
                    if (nomPartieSaisie.isPresent()){
                        Sauvegarde sauvegarde = new Sauvegarde();
                        sauvegarde.savePartie(nomSauvegarde, nomPartieSaisie.get(), sauvegardeEtQuitter);
                    }
                    
                });
                
                vBoxBouton.getChildren().add(boutonLancementPartie);
            }
            else
            {
                emplacementVide=nomSauvegarde;
            }
        }
        
        ScrollPane scrollBarBouton = new ScrollPane();
        scrollBarBouton.setStyle("-fx-background-color:transparent;");
        scrollBarBouton.setContent(vBoxBouton);


        VBox vBoxScrollBarBouton = new VBox(scrollBarBouton);
        vBoxScrollBarBouton.setAlignment(Pos.BOTTOM_LEFT);
        
        Label titre = new Label ("Sauvegarde");
        titre.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold;"
                + "-fx-background-color: rgba(120,160,175,0.50);");
        
        
        ImageView imageRetour = new ImageView ("/images/retourImage.png");
        ImageView imageRetourHover = new ImageView ("/images/retourImageHover.png");
        ImageView imageNouveau = new ImageView ("/images/new.png");
        ImageView imageNouveauHover = new ImageView ("/images/newHover.png"); 
        
        Button boutonRetour = new Button ();
        boutonRetour.setGraphic(imageRetour);
        boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonRetour.setOnMouseEntered (e-> {
            boutonRetour.setGraphic (imageRetourHover);
            boutonRetour.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");
                });
        boutonRetour.setOnMouseExited (e-> {
            boutonRetour.setGraphic (imageRetour);
            boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
                });
        boutonRetour.setOnAction((ActionEvent eventLancementJeu) -> {
            AffichageJeuGraphique affichageJeuGraphique= new AffichageJeuGraphique();
            affichageJeuGraphique.affichageJoueur();
        }); 


        Button boutonNouveau = new Button ("Nouvelle Emplacement");
        boutonNouveau.setGraphic(imageNouveau);
        boutonNouveau.setStyle ("-fx-background-color: rgba(120,160,175,0.50);"
                + "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 20pt; ");
        boutonNouveau.setOnMouseEntered (e-> {
            boutonNouveau.setGraphic (imageNouveauHover);
            boutonNouveau.setStyle ("-fx-background-color: rgba(82,127,143,0.50);"
                + "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 20pt; ");
        });
        boutonNouveau.setOnMouseExited (e-> {
            boutonNouveau.setGraphic (imageNouveau);
            boutonNouveau.setStyle ("-fx-background-color: rgba(120,160,175,0.50);"
                + "-fx-font-police: 'Tw Cen MT Condensed';"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 20pt; ");
        });
        boutonNouveau.setOnAction((ActionEvent eventLancementJeu) -> {
            if (emplacementVide==null){
                Alert boitAlertePlusDemplacement = new Alert(AlertType.ERROR);
                boitAlertePlusDemplacement.setTitle("Bataille Navale - Erreur");
                boitAlertePlusDemplacement.setHeaderText("Emplacement de sauvergarde plein");
                boitAlertePlusDemplacement.setContentText("Il n'y a plus d'emplacement de sauvegarde disponible. Veuillez supprimer  des emplacements pour continuer.");
                boitAlertePlusDemplacement.showAndWait();
            }
            else{
                TextInputDialog boiteSaisie = new TextInputDialog("Nom Partie");
                boiteSaisie.setTitle("Bataille Navale - Nom Partie");
                boiteSaisie.setHeaderText("Veuillez saisir le nom de la partie :");
                boiteSaisie.setContentText("Nom de la partie :");

                Optional<String> nomPartieSaisie = boiteSaisie.showAndWait();
                if (nomPartieSaisie.isPresent()){
                    Sauvegarde sauvegarde = new Sauvegarde();
                    sauvegarde.savePartie(emplacementVide, nomPartieSaisie.get(), sauvegardeEtQuitter);
                }
            }
        }); 
        

        VBox rootLancementPartie = new VBox(10);
        rootLancementPartie.setPadding(new Insets(10,10,30,80));

        GridPane rootEmplacementSauvegarde = new GridPane();
        rootEmplacementSauvegarde.setHgap(250);
        rootEmplacementSauvegarde.setPadding(new Insets(300,10,10,10));
        rootEmplacementSauvegarde.add(vBoxScrollBarBouton,0,0);
        rootEmplacementSauvegarde.add(boutonNouveau,1,0);
        
        ColumnConstraints  colonneContrainte = new ColumnConstraints ();
        colonneContrainte.setPercentWidth(50);
        colonneContrainte.setHalignment(HPos.CENTER);           //On positionne les éléments du root au centre
        rootEmplacementSauvegarde.getColumnConstraints().add(colonneContrainte);

        
        rootLancementPartie.getChildren().add(titre);
        rootLancementPartie.getChildren().add(boutonRetour);
        rootLancementPartie.getChildren().add(rootEmplacementSauvegarde);
        rootLancementPartie.setAlignment(Pos.CENTER);

        VBox.setVgrow(rootEmplacementSauvegarde, Priority.ALWAYS);
        

        Scene scene = new Scene(rootLancementPartie);
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

    public void sauvegardeEffectueeSauvegardeSimple(){
        Timeline timeSauvegardeEffectuee= new Timeline();
        timeSauvegardeEffectuee.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),e -> {
            VBox rootText = new VBox(25);
            Label information0 = new Label ("La sauvegarder a bien eu lieu");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +               " -fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-size: 30pt;"
            +                "-fx-font-weight: bold;");
            
            Label information1 = new Label ("\n\nRetour au plateau de Jeu");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneNiveau1TirRandom = new Scene(rootText);
            
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
            
            Timeline timeLancementMenu = new Timeline();
            timeLancementMenu.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),k -> {
                AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
                affichageJeuGraphique.affichageJoueur();
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
        Scene sceneSuppressionEffectuer= new Scene(rootText);

        stage.setScene(sceneSuppressionEffectuer);

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

    public void lancementChargementMenuJoueur() throws ClassNotFoundException{
        
        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [10];
        VBox vBoxBouton = new VBox(2);
        
        Label informationEmplacmentSauvegarde = new Label ("Sauvegardes présentes :");
        informationEmplacmentSauvegarde.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + " -fx-font-size: 16pt; ");
        vBoxBouton.getChildren().add(informationEmplacmentSauvegarde);


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
                        preViewPartieMenuJoueur(nomSauvegarde, JeuGraphique.fenetreJeu);
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Erreur! Le chargement de la partie n'a pas eu lieu.");
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
                + "-fx-font-weight: bold; "
                + "-fx-background-color: rgba(120,160,175,0.50);");
        
        
        ImageView retourImage = new ImageView ("/images/retourImage.png");
        ImageView retourImageHover = new ImageView ("/images/retourImageHover.png"); 
        
        Button boutonHome = new Button ();
        boutonHome.setGraphic(retourImage);
        boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        boutonHome.setOnMouseEntered (e-> {
            boutonHome.setGraphic (retourImageHover);
            boutonHome.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");
                });
        boutonHome.setOnMouseExited (e-> {
            boutonHome.setGraphic (retourImage);
            boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
                });
        boutonHome.setOnAction((ActionEvent eventLancementJeu) -> {
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
            affichageJeuGraphique.affichageJoueur();
        }); 
        
        
        VBox rootLancementPartie = new VBox(10);
        rootLancementPartie.setPadding(new Insets(10,10,30,80));
        
        rootLancementPartie.getChildren().add(titre);
        rootLancementPartie.getChildren().add(boutonHome);
        rootLancementPartie.getChildren().add(vBoxScrollBarBouton);
        rootLancementPartie.setAlignment(Pos.CENTER);
        VBox.setVgrow(vBoxScrollBarBouton, Priority.ALWAYS);
        
        
        
        Scene scene = new Scene(rootLancementPartie, 300, 250);
        JeuGraphique.fenetreJeu.setScene(scene);
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

    public void preViewPartieMenuJoueur(String nomSauvegarde ,Stage stage) throws ClassNotFoundException{
        
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
        boutonRetour.setOnAction((ActionEvent eventChargementPartie) -> {         
            try {         //Action si le joueur click sur le bouton
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
        
        VBox rootAllElement = new VBox(25);
        rootAllElement.setPadding(new Insets(25,25,25,20));   
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
