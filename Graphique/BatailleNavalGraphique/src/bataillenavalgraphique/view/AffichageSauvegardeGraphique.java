package bataillenavalgraphique.view;

import bataillenavalgraphique.controller.JeuGraphique;
import bataillenavalgraphique.controller.Sauvegarde;
import bataillenavalgraphique.model.GrilleBoutons;
import bataillenavalgraphique.bataillenaval.model.Plateau;
import bataillenavalgraphique.bataillenaval.view.Affichage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;
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
 * Classe Affichage Sauvegarde.
 * Toutes les méthodes utilisées pour l'affichage des informations et l'intéraction avec le joueur lors de la sauvegarde.
 * @author Théric PHERON and Joé LHUERRE
 */
public class AffichageSauvegardeGraphique {
    
    //**************************************************************************
    String emplacementVide =null;
    //**************************************************************************


    //**************************************************************************
    /**
     * Constructeur de la classe AffichageSauvegardeGraphique.
     * Il permet à la sauvegarder d'afficher des message à l'tilisateur.
     */
    public AffichageSauvegardeGraphique(){
        
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet à l'uilisateur de créer une sauvegarde ou sélection une déjà présente
     * @param sauvegardeEtQuitter True s'il faut quitter après avoir sauvegardé, false sinon
     * @throws ClassNotFoundException
     */
    public void sauvegarde(boolean sauvegardeEtQuitter) throws ClassNotFoundException{
        JeuGraphique.chronometre.pause();           //On met le chronomètre sur pause

        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [10];          //Variable qui est utilser pour stocker les différents nom de partie
        VBox vBoxBouton = new VBox(2);          //Variable qui stoke les éléments pour l'affichage

        final Label informationEmplacmentSauvegarde = new Label ("Sauvegardes déjà présentes :");         //On informe le joueur du contenu des sauvegardes présentes
        informationEmplacmentSauvegarde.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 16pt; ");
        vBoxBouton.getChildren().add(informationEmplacmentSauvegarde);             //On rajoute des éléments a ce root

        for (int i=0; i<10; i++){           //On parcourt les 10 sauvegardes
            
            String nomSauvegarde = "saveFiles/save"+String.valueOf(i);          //Le nom du fichier de la sauvegarde

            try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {         //On ouvre le fichier
                    nomPartie[i]= (String) in.readObject();         //On récupère le nom de la partie
            }
            catch(IOException e){
                System.err.println("Le fichier save n'a pas pu être ouvert");           //On affiche un message en cas d'erreur
            }
            
            if (nomPartie[i]!=null){
                Button boutonLancementPartie = new Button(nomPartie[i]);            //On déclare le bouton pour chaque fichier de sauvegarde
                boutonLancementPartie.setPrefSize(500, 30);         //On définit la taille du bouton
                boutonLancementPartie.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
                boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                
                boutonLancementPartie.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                    +                "-fx-font-size: 15pt;"
                                    +                "-fx-font-weight: bold;"
                                    +                   "-fx-background-color: rgba(82,127,143,0.50);");
                });
                
                boutonLancementPartie.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                });
                boutonLancementPartie.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
                    
                    Alert confirmation = new Alert(AlertType.CONFIRMATION);         //On initialise une boite de dialogue pour une confirmation
                    confirmation.setTitle("Bataille Navale - Confiramation");            //On lui donne un titre
                    confirmation.setHeaderText("Vous allez supprimer la partie déjà présente.");         //On lui donne un message destiné à l'utilisateur
                    confirmation.setContentText("Etes vous sur de vouloir supprimer la partie déjà présente ?");            //On lui demande ce qu'il veut faire

                    ButtonType boutonOui = new ButtonType("Oui");           //On déclare un bouton pour la réponse oui
                    ButtonType boutonNon = new ButtonType("Non");           //On déclare un bouton pour la réponse non
                    confirmation.getButtonTypes().setAll(boutonOui, boutonNon);

                    Optional<ButtonType> choix = confirmation.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
                    if (choix.get() == boutonOui) {         //Si c'est oui
                        TextInputDialog boiteSaisie = new TextInputDialog("Nom Partie");        //Le texte par défaut dans la zone de tir est Nom Partie
                        boiteSaisie.setTitle("Bataille Navale - Nom Partie");            //On lui donne un titre
                        boiteSaisie.setHeaderText("Veuillez saisir le nom de la partie :");         //On lui donne un message destiné à l'utilisateur
                        boiteSaisie.setContentText("Nom de la partie :");

                        Optional<String> nomPartieSaisie = boiteSaisie.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
                        if (nomPartieSaisie.isPresent()){           //Si il y a un nom de partie 
                            Sauvegarde sauvegarde = new Sauvegarde();           //On déclare un variable de type Sauvegarde
                            sauvegarde.savePartie(nomSauvegarde, nomPartieSaisie.get(), sauvegardeEtQuitter);           //On sauvegarde avec le nom que l'utilisateur a rentré
                        }
                    }
                });
                
                vBoxBouton.getChildren().add(boutonLancementPartie);             //On rajoute un élément a ce root
            }
            else
            {
                emplacementVide=nomSauvegarde;          //On attribut à un emplacement vide un nom de partie par défaut
            }
        }
        
        ScrollPane scrollBarBouton = new ScrollPane();          //On déclare une barre où on peut monter et descendre comme une page web
        scrollBarBouton.setStyle("-fx-background-color:transparent;");          //On change les caractéristiques d'écriture
        scrollBarBouton.setContent(vBoxBouton);         //On met dans la scrollBar la VBox contenant les boutons


        VBox vBoxScrollBarBouton = new VBox(scrollBarBouton);           //On met la scrollBar dans une affichage vertical
        vBoxScrollBarBouton.setAlignment(Pos.BOTTOM_LEFT);            //On positionne la scroolBar en bas à gauche de la scène
        
        final Label titre = new Label ("Sauvegarde");         //On indique qu'on se trouve dans la sauvegarde
        titre.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 30pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold;"
                + "-fx-background-color: rgba(120,160,175,0.50);");
        
        
        final ImageView imageRetour = new ImageView ("/images/retourImage.png");          //On donne l'endroit où sont stocker les images
        final ImageView imageRetourHover = new ImageView ("/images/retourImageHover.png");
        final ImageView imageNouveau = new ImageView ("/images/new.png");
        final ImageView imageNouveauHover = new ImageView ("/images/newHover.png"); 
        
        Button boutonRetour = new Button ("Retour");           //On déclare un bouton pour le retour
        boutonRetour.setGraphic(imageRetour);            //On l'illustre par une petite image
        boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonRetour.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
            boutonRetour.setGraphic (imageRetourHover);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");          //On change les caractéristiques d'écriture
                });
        boutonRetour.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonRetour.setGraphic (imageRetour);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
                });
        boutonRetour.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            JeuGraphique.chronometre.resume();          //On relance le chronomètre
            AffichageJeuGraphique affichageJeuGraphique= new AffichageJeuGraphique();           //On déclare une variable de type affichageJeuGraphique
            affichageJeuGraphique.affichageJoueur();            //On affiche le menu du joueur
        }); 


        Button boutonNouveau = new Button ("Nouvel Emplacement");           //On déclare un bouton pour un nouvelle emplacement
        boutonNouveau.setGraphic(imageNouveau);            //On l'illustre par une petite image
        boutonNouveau.setStyle ("-fx-background-color: rgba(120,160,175,0.50);"          //On change les caractéristiques d'écriture
                + "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 20pt; ");
        boutonNouveau.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
            boutonNouveau.setGraphic (imageNouveauHover);            //On l'illustre par une petite image
            boutonNouveau.setStyle ("-fx-background-color: rgba(82,127,143,0.50);"          //On change les caractéristiques d'écriture
                + "-fx-font-police: 'Tw Cen MT Condensed' ;"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 20pt; ");
        });
        boutonNouveau.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonNouveau.setGraphic (imageNouveau);            //On l'illustre par une petite image
            boutonNouveau.setStyle ("-fx-background-color: rgba(120,160,175,0.50);"          //On change les caractéristiques d'écriture
                + "-fx-font-police: 'Tw Cen MT Condensed';"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 20pt; ");
        });
        boutonNouveau.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            if (emplacementVide==null){
                Alert boitAlertePlusDemplacement = new Alert(AlertType.ERROR);         //On initialise une boite de dialogue pour une erreur
                boitAlertePlusDemplacement.setTitle("Bataille Navale - Erreur");            //On lui donne un titre
                boitAlertePlusDemplacement.setHeaderText("Emplacement de sauvergarde plein");         //On lui donne un message destiné à l'utilisateur
                boitAlertePlusDemplacement.setContentText("Il n'y a plus d'emplacement de sauvegarde disponible. Veuillez supprimer des emplacements pour continuer.");         //On lui donne un message destiné à l'utilisateur
                boitAlertePlusDemplacement.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
            }
            else{
                TextInputDialog boiteSaisie = new TextInputDialog("Nom Partie");        //Le texte par défaut dans la zone de tir est Nom Partie
                boiteSaisie.setTitle("Bataille Navale - Nom Partie");            //On lui donne un titre
                boiteSaisie.setHeaderText("Veuillez saisir le nom de la partie :");         //On lui donne un message destiné à l'utilisateur
                boiteSaisie.setContentText("Nom de la partie :");

                Optional<String> nomPartieSaisie = boiteSaisie.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
                if (nomPartieSaisie.isPresent()){
                    Sauvegarde sauvegarde = new Sauvegarde();           //On déclare une variable de type Sauvegarde
                    sauvegarde.savePartie(emplacementVide, nomPartieSaisie.get(), sauvegardeEtQuitter);           //On sauvegarde avec le nom que l'utilisateur a rentré
                }
            }
        }); 
        

        VBox rootLancementPartie = new VBox(10);         //On déclare un affichage vertical où les éléments sont espacé de 10 pixels
        rootLancementPartie.setPadding(new Insets(10,10,30,80));          //On donne les dimensions entre les rebords de la fenètre et le root

        GridPane rootEmplacementSauvegarde = new GridPane();            //On déclare un grille où on peut placer les éléments qu'on souhaite
        rootEmplacementSauvegarde.setHgap(250);         //On met un espacement de 250 pixels horizontalement entre les éléments
        rootEmplacementSauvegarde.setPadding(new Insets(300,10,10,10));           //On donne les dimensions entre les rebords de la fenètre et le root 
        rootEmplacementSauvegarde.add(vBoxScrollBarBouton,0,0);         //On ajoute un élément au root
        rootEmplacementSauvegarde.add(boutonNouveau,1,0);
        
        ColumnConstraints  colonneContrainte = new ColumnConstraints ();
        colonneContrainte.setPercentWidth(50);
        colonneContrainte.setHalignment(HPos.CENTER);           //On positionne les éléments du root au centre
        rootEmplacementSauvegarde.getColumnConstraints().add(colonneContrainte);         //On ajoute un élément au root

        
        rootLancementPartie.getChildren().add(titre);             //On rajoute des éléments a ce root
        rootLancementPartie.getChildren().add(boutonRetour);
        rootLancementPartie.getChildren().add(rootEmplacementSauvegarde);
        rootLancementPartie.setAlignment(Pos.CENTER);            //On positionne l'élément au centre

        VBox.setVgrow(rootEmplacementSauvegarde, Priority.ALWAYS);
        

        Scene scene = new Scene(rootLancementPartie);           //On ajoute ce root à la scène
        JeuGraphique.fenetreJeu.setScene(scene);           //On modifie et affiche la scène
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer à l'utilisateur que la sauvegarde est en cours.
     */
    public void sauvegardeEnCours(){

        VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        final Label information0 = new Label ("La sauvegarde est en cours");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +               " -fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-size: 30pt;"
        +                "-fx-font-weight: bold;");

        final Label information1 = new Label ("\n\nPatientez...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +                "-fx-font-size: 20pt;"
        +                "-fx-font-weight: bold;");


        rootText.getChildren().addAll(information0, information1);             //On rajoute des éléments a ce root
        rootText.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
        Scene sceneNiveau1TirRandom = new Scene(rootText);           //On ajoute ce root à la scène
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);           //On modifie et affiche la scène

    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer à l'utilisateur que la sauvegarder à été effcetué.
     */
    public void sauvegardeEffectuee(){
        Timeline timeSauvegardeEffectuee= new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeSauvegardeEffectuee.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),e -> {            //On met un temps d'attente de 4s
            VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
            final Label information0 = new Label ("La sauvegarde a bien eu lieu");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
            +               " -fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-size: 30pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information1 = new Label ("\n\nRetour au Menu Principal");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);             //On rajoute des éléments a ce root
            rootText.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On ajoute ce root à la scène
            
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);           //On modifie et affiche la scène
            
            Timeline timeLancementMenu = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
            timeLancementMenu.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),k -> {            //On met un temps d'attente de 4s
                
                JeuGraphique.fenetreJeu.close();            //On ferme la fenètre
                
                MenuGraphique menuPrincipal = new MenuGraphique();          //On déclare une variable de type menuGraphique
                menuPrincipal.menuPrincipalGraphique();         //On lance le menu principal
            }));
            timeLancementMenu.play();                //On démarre le temps de décalage dès que le programme le lit
        }));
        timeSauvegardeEffectuee.play();                //On démarre le temps de décalage dès que le programme le lit
    }

    public void sauvegardeEffectueeSauvegardeSimple(){
        Timeline timeSauvegardeEffectuee= new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeSauvegardeEffectuee.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),e -> {            //On met un temps d'attente de 4s
            VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
            final Label information0 = new Label ("La sauvegarde a bien eu lieu");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
            +               " -fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-size: 30pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information1 = new Label ("\n\nRetour au plateau de Jeu");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);             //On rajoute des éléments a ce root
            rootText.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On ajoute ce root à la scène
            
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);           //On modifie et affiche la scène
            
            Timeline timeLancementMenu = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
            timeLancementMenu.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),k -> {            //On met un temps d'attente de 4s
                JeuGraphique.chronometre.resume();          //On reprend le chronometre
                AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();          //On déclare une variable de type affichageJeuGraphique
                affichageJeuGraphique.affichageJoueur();            //On lance le menu joueur
            }));
            timeLancementMenu.play();                //On démarre le temps de décalage dès que le programme le lit
        }));
        timeSauvegardeEffectuee.play();                //On démarre le temps de décalage dès que le programme le lit
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer à l'utilisateur que la suppresion d'une sauvegarde à bien eu lieu.
     * @param stage La fenêtre d'affichage
     */
    public void suppressionEffectue(Stage stage){

        VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        final Label information0 = new Label ("La suppresion de la partie a bien eu lieu");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +               " -fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-size: 20pt;"
        +                "-fx-font-weight: bold;");

        final Label information1 = new Label ("\n\nRetour au Menu de Chargement");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");


        rootText.getChildren().addAll(information0, information1);             //On rajoute des éléments a ce root
        rootText.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
        Scene sceneSuppressionEffectuer= new Scene(rootText);           //On ajoute ce root à la scène

        stage.setScene(sceneSuppressionEffectuer);           //On modifie et affiche la scène

        Timeline timeLancementMenu = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeLancementMenu.getKeyFrames().addAll(new KeyFrame(Duration.millis(4000),k -> {            //On met un temps d'attente de 4s

            AffichageSauvegardeGraphique affichageSauvegarde = new AffichageSauvegardeGraphique();
            try {
                affichageSauvegarde.lancementChargement(stage);         //On lance l'affichage du chargement 
            } catch (ClassNotFoundException ex) {
                System.err.println("Erreur! Le lancement du chargement a échoué");          //On affiche une erreur dans la consol en cas d'erreur
            }
        }));
        timeLancementMenu.play();                //On démarre le temps de décalage dès que le programme le lit
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de lancer une partie.
     * @param stage La fenêtre d'affichage
     * @throws ClassNotFoundException
     */
    public void lancementChargement(Stage stage) throws ClassNotFoundException{
        
        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [10];          //On déclare un tableau de String
        VBox vBoxBouton = new VBox(2);         //On déclare un affichage vertical où les éléments sont espacé de 2 pixels
        //List<Button> boutonPartie = new ArrayList<Button>();
        for (int i=0; i<10; i++){
            
            String nomSauvegarde = "saveFiles/save"+String.valueOf(i);          //Le nom du fichier de la sauvegarde

            try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {         //On ouvre le fichier
                    nomPartie[i]= (String) in.readObject();         //On récupère le nom de la partie
            }
            catch(IOException e){
                System.err.println("Le fichier save n'a pas pu être ouvert");           //On affiche un message en cas d'erreur
            }
            
            if (nomPartie[i]!=null){            //Si la sauvegarde n'est pas nul
                Button boutonLancementPartie = new Button(nomPartie[i]);           //On déclare un bouton pour chaque sauvegarde
                boutonLancementPartie.setPrefSize(500, 30);         //On définit la taille du bouton
                boutonLancementPartie.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
                boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                
                boutonLancementPartie.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                    +                "-fx-font-size: 15pt;"
                                    +                "-fx-font-weight: bold;"
                                    +                   "-fx-background-color: rgba(82,127,143,0.50);");
                });
                
                boutonLancementPartie.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                });
                boutonLancementPartie.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
                    try {
                        preViewPartie(nomSauvegarde, stage);            //On visualise la partie sauvegardée
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Le fichier save n'a pas pu être ouvert");           //On affiche un message en cas d'erreur
                    }
                });
                
                vBoxBouton.getChildren().add(boutonLancementPartie);             //On rajoute des éléments a ce root
            }
        }
        
        ScrollPane scrollBarBouton = new ScrollPane();
        scrollBarBouton.setStyle("-fx-background-color:transparent;");          //On change les caractéristiques d'écriture
        scrollBarBouton.setContent(vBoxBouton);
        VBox vBoxScrollBarBouton = new VBox(scrollBarBouton);
        vBoxScrollBarBouton.setAlignment(Pos.BOTTOM_LEFT);            //On positionne l'élément en bas à gauche
        
        
        final Label titre = new Label ("Chargement");
        titre.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 35pt; "
                + "-fx-text-fill: BLACK; "
                + "-fx-font-weight: bold; ");
        
        
        final ImageView retourHome = new ImageView ("/images/retourHome.png");            //On donne l'emplacement de l'image
        final ImageView retourHomeHover = new ImageView ("/images/retourHomeHover.png"); 
        
        Button boutonHome = new Button ("Menu Principal");           //On déclare un bouton pour aller dans le menu principal
        boutonHome.setGraphic(retourHome);            //On l'illustre par une petite image
        boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonHome.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
            boutonHome.setGraphic (retourHomeHover);            //On l'illustre par une petite image
            boutonHome.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");          //On change les caractéristiques d'écriture
                });
        boutonHome.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonHome.setGraphic (retourHome);            //On l'illustre par une petite image
            boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
                });
        boutonHome.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            MenuGraphique menuPrincipal = new MenuGraphique();
            stage.setScene(menuPrincipal.sceneLancementMenuPrincipal(stage));           //On modifie et affiche la scène
        }); 
        
        
        VBox rootLancementPartie = new VBox(10);         //On déclare un affichage vertical où les éléments sont espacé de 10 pixels
        rootLancementPartie.setPadding(new Insets(10,10,30,80));           //On donne les dimensions entre les rebords de la fenètre et le root 
        
        rootLancementPartie.getChildren().add(titre);             //On rajoute des éléments a ce root
        rootLancementPartie.getChildren().add(boutonHome);
        rootLancementPartie.getChildren().add(vBoxScrollBarBouton);
        VBox.setVgrow(vBoxScrollBarBouton, Priority.ALWAYS);
        
        
        
        Scene scene = new Scene(rootLancementPartie, 300, 250);           //On ajoute ce root à la scène
        stage.setScene(scene);           //On modifie et affiche la scène
    }


    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de lancer une nouvelle partie depuis la menu joueur.
     * @throws ClassNotFoundException
     */
    public void lancementChargementMenuJoueur() throws ClassNotFoundException{
        
        JeuGraphique.chronometre.pause();
        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [10];
        VBox vBoxBouton = new VBox(2);         //On déclare un affichage vertical où les éléments sont espacé de 2 pixels
        
        final Label informationEmplacmentSauvegarde = new Label ("Sauvegardes présentes :");
        informationEmplacmentSauvegarde.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 16pt; ");
        vBoxBouton.getChildren().add(informationEmplacmentSauvegarde);             //On rajoute un élément à ce root


        for (int i=0; i<10; i++){
            
            String nomSauvegarde = "saveFiles/save"+String.valueOf(i);           //Le nom du fichier de la sauvegarde

            try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {         //On ouvre le fichier
                    nomPartie[i]= (String) in.readObject();         //On récupère le nom de la partie
            }
            catch(IOException e){
                System.err.println("Le fichier save n'a pas pu être ouvert");           //On affiche un message en cas d'erreur
            }
            
            if (nomPartie[i]!=null){
                Button boutonLancementPartie = new Button(nomPartie[i]);           //On déclare un bouton pour chaque sauvegarde
                boutonLancementPartie.setPrefSize(500, 30);         //On définit la taille du bouton
                boutonLancementPartie.setAlignment(Pos.CENTER_LEFT);            //On positionne l'élément au milieu à gauche
                boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                
                boutonLancementPartie.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                    +                "-fx-font-size: 15pt;"
                                    +                "-fx-font-weight: bold;"
                                    +                   "-fx-background-color: rgba(82,127,143,0.50);");
                });
                
                boutonLancementPartie.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
                    boutonLancementPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
                                +                "-fx-font-size: 13pt;"
                                +                "-fx-font-weight: bold;"
                                +                "-fx-background-color: rgba(120,160,175,0.50);");
                });
                boutonLancementPartie.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
                    try {
                        preViewPartieMenuJoueur(nomSauvegarde, JeuGraphique.fenetreJeu);
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Erreur! Le chargement de la partie n'a pas eu lieu.");           //On affiche un message en cas d'erreur
                    }
                });
                
                vBoxBouton.getChildren().add(boutonLancementPartie);             //On rajoute un élément à ce root
            }
        }
        
        ScrollPane scrollBarBouton = new ScrollPane();
        scrollBarBouton.setStyle("-fx-background-color:transparent;");          //On change les caractéristiques d'écriture
        scrollBarBouton.setContent(vBoxBouton);
        VBox vBoxScrollBarBouton = new VBox(scrollBarBouton);
        vBoxScrollBarBouton.setAlignment(Pos.BOTTOM_LEFT);            //On positionne l'élément en bas à gauche
        
        
        final Label titre = new Label ("Chargement");         //On informe dans quel endroit on se trouve
        titre.setStyle (
                  "-fx-font-police: 'Tw Cen MT Condensed' ;"          //On change les caractéristiques d'écriture
                + " -fx-font-size: 35pt; "
                + "-fx-font-weight: bold; "
                + "-fx-background-color: rgba(120,160,175,0.50);");
        
        
        final ImageView retourImage = new ImageView ("/images/retourImage.png");            //On donne l'emplacement de l'image
        final ImageView retourImageHover = new ImageView ("/images/retourImageHover.png"); 
        
        Button boutonHome = new Button ();           //On déclare un bouton pour retourner au menu principal
        boutonHome.setGraphic(retourImage);            //On l'illustre par une petite image
        boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
        boutonHome.setOnMouseEntered (e-> {           //Si le joueur met son curseur sur le bouton
            boutonHome.setGraphic (retourImageHover);            //On l'illustre par une petite image
            boutonHome.setStyle ("-fx-background-color: rgba(82,127,143,0.50)");          //On change les caractéristiques d'écriture
                });
        boutonHome.setOnMouseExited (e-> {            //Si le joueur enlève son curseur du bouton
            boutonHome.setGraphic (retourImage);            //On l'illustre par une petite image
            boutonHome.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");          //On change les caractéristiques d'écriture
                });
        boutonHome.setOnAction((ActionEvent eventLancementJeu) -> {         //Action si le joueur clique sur le bouton
            JeuGraphique.chronometre.resume();
            AffichageJeuGraphique affichageJeuGraphique = new AffichageJeuGraphique();
            affichageJeuGraphique.affichageJoueur();            //On affiche le menu du joueur 
        }); 
        
        
        VBox rootLancementPartie = new VBox(10);         //On déclare un affichage vertical où les éléments sont espacé de 10 pixels
        rootLancementPartie.setPadding(new Insets(10,10,30,80));           //On donne les dimensions entre les rebords de la fenètre et le root 
        
        rootLancementPartie.getChildren().add(titre);             //On rajoute des éléments à ce root
        rootLancementPartie.getChildren().add(boutonHome);
        rootLancementPartie.getChildren().add(vBoxScrollBarBouton);
        rootLancementPartie.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
        VBox.setVgrow(vBoxScrollBarBouton, Priority.ALWAYS);
        
        
        
        Scene scene = new Scene(rootLancementPartie, 300, 250);           //On ajoute ce root à la scène
        JeuGraphique.fenetreJeu.setScene(scene);           //On modifie et affiche la scène
    }


    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de prévisualiser une partie.
     * La méthode affiche la partie avant que l'utilisateur la charge pour savoir lui demander s'il veut vraiment la charger.
     * @param nomSauvegarde Le nom du fichier de sauvegarde
     * @param stage La fenêtre d'affichage
     * @throws ClassNotFoundException
     */    
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
            in.readObject();

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
            System.err.println("Erreur_chargementPartie! "+"Le fichier n'a pas pu être ouvert.");           //On affiche un message d'erreur
        }
        
        Affichage.afficher(0,0,plateauDeJeuCopy);
        
        final ImageView imageSupprimer=new ImageView(getClass().getResource("/images/poubelle.png").toString());            //On donne l'emplacement de l'image
        final ImageView imageSupprimerHover =new ImageView(getClass().getResource("/images/poubelleHover.png").toString());
        final ImageView imageCharger =new ImageView(getClass().getResource("/images/charger.png").toString());
        final ImageView imageChargerHover =new ImageView(getClass().getResource("/images/chargerHover.png").toString());
        final ImageView imageRetour = new ImageView ("/images/retourImage.png");
        final ImageView imageRetourHover = new ImageView ("/images/retourImageHover.png");
        
        
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D', 'A');          //On déclare la grille des boutons pour les navires 
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('D', 'A');            //On déclare la grille des boutons pour les tirs
        
        grilleBoutonNavire.miseAJourAffichageNavire(plateauDeJeuCopy);         //On fait la mise à jour de la grille des navires
        grilleBoutonTirs.miseAJourAffichageTirs(plateauDeJeuCopy);         //On fait la mise à jour de la grille de tirs
        
        GridPane rootNavire = new GridPane();            //On déclare un grille où on peut placer les éléments qu'on souhaite        
        rootNavire.setHgap(5);         //On met un espacement de 5 pixels horizontalement entre les éléments
        ColumnConstraints  colonneContrainte = new ColumnConstraints ();            //On déclare et paramètre une colonne 
        colonneContrainte.setPercentWidth(60);
        colonneContrainte.setHalignment(HPos.CENTER);           //On positionne les éléments du root au centre
        rootNavire.getColumnConstraints().add(colonneContrainte);         //On ajoute cette colonne au root
        
        
        rootNavire.add(grilleBoutonNavire.getRoot(),0,0);           //On place la grille des navires à gauche
        rootNavire.add(grilleBoutonTirs.getRoot(),1,0);            //On place la grille des tirs à droite 
        
        final Label instructionsJoueur = new Label("Que souhaitez vous faire ?");            //On informe le joueur des instructions à suivre
        instructionsJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +                "-fx-font-size: 14pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        
        
        Button boutonRetour = new Button("Retour");            //On déclare un bouton pour retourner au menu précedent
        boutonRetour.setGraphic(imageRetour);            //On l'illustre par une petite image
        boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonRetour.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonRetour.setGraphic(imageRetourHover);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonRetour.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonRetour.setGraphic(imageRetour);            //On l'illustre par une petite image
            boutonRetour.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonRetour.setOnAction((ActionEvent eventChargementPartie) -> {            //Action si le joueur clique sur le bouton
            try {         //Action si le joueur click sur le bouton
                lancementChargement(stage);
            } catch (ClassNotFoundException ex) {
                System.err.println("Erreur! Problème sur le retour");           //On affiche un message en cas d'erreur
            }
        });
        Button transparent = new Button();
        transparent.setPrefSize(75,5);
        transparent.setStyle ("-fx-background-color: transparent;");
        
        
        
        Button boutonCharger = new Button("Charger");            //On déclare un bouton pour charger
        boutonCharger.setGraphic(imageCharger);            //On l'illustre par une petite image
        boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonCharger.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonCharger.setGraphic(imageChargerHover);            //On l'illustre par une petite image
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonCharger.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonCharger.setGraphic(imageCharger);            //On l'illustre par une petite image
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonCharger.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur clique sur le bouton
            ecranChargement(nomSauvegarde, stage);
        });
        
        
        Button boutonSupprimer = new Button("Supprimer");          //On déclare un bouton pour supprimer une sauvegarde déjà existante
        boutonSupprimer.setGraphic(imageSupprimer);           //On l'illustre par une petite image
        boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonSupprimer.setOnMouseEntered ((MouseEvent event) -> {          //Si le joueur met son curseur sur le bouton
            boutonSupprimer.setGraphic(imageSupprimerHover);            //On l'illustre par une petite image
            boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonSupprimer.setOnMouseExited ((MouseEvent event) -> {          //Si le joueur enlève son curseur du bouton
            boutonSupprimer.setGraphic(imageSupprimer);            //On l'illustre par une petite image
            boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonSupprimer.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur clique sur le bouton
            Alert alertBox = new Alert(AlertType.CONFIRMATION);         //On initialise une boite de dialogue pour une confirmation
            alertBox.setTitle("Bataille Navale - Confirmation suppression");            //On lui donne un titre
            alertBox.setHeaderText("La suppression de cette partie est définitive.");         //On lui donne un message destiné à l'utilisateur
            alertBox.setContentText("Êtes vous sûr de vouloir supprimer cette partie ?");           //On lui demande ce qu'il veut faire
            
            ButtonType boutonOui = new ButtonType("Oui");           //On déclare un bouton pour la réponse oui
            ButtonType boutonNon = new ButtonType("Non");           //On déclare un bouton pour la réponse non
            
            alertBox.getButtonTypes().setAll(boutonOui,boutonNon);          //On ajoute les boutons à la boite de dialogue
            
            Optional<ButtonType> choix = alertBox.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
            if (choix.get() == boutonOui) {         //Si c'est oui
                Sauvegarde suppression = new Sauvegarde();          //on déclare un variable de tuype Sauvegarde
                suppression.supprimerPartie(nomSauvegarde, stage);          //On supprime la partie
            }
        });
        
        HBox boutonSelectionOption = new HBox(80);          //On déclare un affichage horizontal avec les éléments qui sont espacés de 80 pixels
        boutonSelectionOption.getChildren().addAll(boutonRetour,transparent, instructionsJoueur, boutonCharger,boutonSupprimer);             //On rajoute des éléments à ce root
        boutonSelectionOption.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
        
        VBox rootAllElement = new VBox(8);         //On déclare un affichage vertical où les éléments sont espacé de 8 pixels
        rootAllElement.setPadding(new Insets(0,4,10,4));          //On donne les dimensions entre les rebords de la fenètre et le root 
        rootAllElement.getChildren().addAll(rootNavire, boutonSelectionOption);             //On rajoute des éléments à ce root
        
        Scene sceneJeu = new Scene(rootAllElement);            //On met le root dans la scène 
        stage.setScene(sceneJeu);           //On modifie et affiche la scène
    }


    //**************************************************************************
    /**
     * Méthode qui permet à l'utilisateur de prévisualiser une partie.
     * La méthode affiche la partie avant que l'utilisateur la charge pour savoir lui demander s'il veut vraiment la charger.
     * @param nomSauvegarde Le nom du fichier de sauvegarde
     * @param stage La fenêtre d'affichage
     * @throws ClassNotFoundException
     */
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
            in.readObject();

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
            System.err.println("Erreur_chargementPartie! "+"Le fichier n'a pas pu être ouvert.");           //On affiche un message d'erreur
        }
        
        Affichage.afficher(0,0,plateauDeJeuCopy);
        
        final ImageView imageSupprimer=new ImageView(getClass().getResource("/images/poubelle.png").toString());            //On donne l'emplacement de l'image
        final ImageView imageSupprimerHover =new ImageView(getClass().getResource("/images/poubelleHover.png").toString());
        final ImageView imageCharger =new ImageView(getClass().getResource("/images/charger.png").toString());
        final ImageView imageChargerHover =new ImageView(getClass().getResource("/images/chargerHover.png").toString());
        final ImageView imageRetour = new ImageView ("/images/retourImage.png");
        final ImageView imageRetourHover = new ImageView ("/images/retourImageHover.png");
        
        
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D', 'A');          //On déclare la grille des boutons pour les navires 
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('D', 'A');            //On déclare la grille des boutons pour les tirs
        
        grilleBoutonNavire.miseAJourAffichageNavire(plateauDeJeuCopy);         //On fait la mise à jour de la grille des navires
        grilleBoutonTirs.miseAJourAffichageTirs(plateauDeJeuCopy);         //On fait la mise à jour de la grille de tirs
        
        GridPane rootNavire = new GridPane();            //On déclare un grille où on peut placer les éléments qu'on souhaite        
        rootNavire.setHgap(5);         //On met un espacement de 5 pixels horizontalement entre les éléments
        ColumnConstraints  colonneContrainte = new ColumnConstraints ();            //On déclare et paramètre une colonne 
        colonneContrainte.setPercentWidth(60);
        colonneContrainte.setHalignment(HPos.CENTER);           //On positionne les éléments du root au centre
        rootNavire.getColumnConstraints().add(colonneContrainte);         //On ajoute cette colonne au root
        
        
        rootNavire.add(grilleBoutonNavire.getRoot(),0,0);           //On place la grille des navires à gauche
        rootNavire.add(grilleBoutonTirs.getRoot(),2,0);            //On place la grille des tirs à droite 
        
        final Label instructionsJoueur = new Label("Que souhaitez vous faire ?");            //On informe le joueur des instructions à suivre
        instructionsJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 14pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        
        
        Button boutonRetour = new Button("Retour");            //On déclare un bouton pour retourner au menu précédent
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
        boutonRetour.setOnAction((ActionEvent eventChargementPartie) -> {             //Action si le joueur clique sur le bouton     
            try {         //Action si le joueur click sur le bouton
                lancementChargement(stage);         //On lance l'affichage du chargement
            } catch (ClassNotFoundException ex) {
                System.err.println("Erreur! Problème sur le retour");           //On affiche un message d'erreur en cas de problème
            }
        });

        
        Button transparent = new Button();
        transparent.setPrefSize(75,10);
        transparent.setStyle ("-fx-background-color: transparent;");
        
        
        
        Button boutonCharger = new Button("Charger");            //On déclare un bouton pour charger
        boutonCharger.setGraphic(imageCharger);            //On l'illustre par une petite image
        boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonCharger.setOnMouseEntered ((MouseEvent event) -> {           //Si le joueur met son curseur sur le bouton
            boutonCharger.setGraphic(imageChargerHover);            //On l'illustre par une petite image
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonCharger.setOnMouseExited ((MouseEvent event) -> {            //Si le joueur enlève son curseur du bouton
            boutonCharger.setGraphic(imageCharger);            //On l'illustre par une petite image
            boutonCharger.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"          //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonCharger.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur clique sur le bouton
            ecranChargement(nomSauvegarde, stage);
        });
        
        
        Button boutonSupprimer = new Button("Supprimer");          //On déclare un bouton pour supprimer une sauvegarde existante
        boutonSupprimer.setGraphic(imageSupprimer);           //On l'illustre par une petite image
        boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        boutonSupprimer.setOnMouseEntered ((MouseEvent event) -> {          //Si le joueur met son curseur sur le bouton
            boutonSupprimer.setGraphic(imageSupprimerHover);            //On l'illustre par une petite image
            boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                    + "-fx-font-weight: bold;-fx-background-color: rgba(90,170,182,0.80)");
        });
        boutonSupprimer.setOnMouseExited ((MouseEvent event) -> {          //Si le joueur enlève son curseur du bouton
            boutonSupprimer.setGraphic(imageSupprimer);            //On l'illustre par une petite image
            boutonSupprimer.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 13pt;"            //On change les caractéristiques d'écriture
                + "-fx-font-weight: bold;-fx-background-color: rgba(163,198,211,0.50)");
        });
        boutonSupprimer.setOnAction((ActionEvent eventChargementPartie) -> {         //Action si le joueur clique sur le bouton
            Alert alertBox = new Alert(AlertType.CONFIRMATION);         //On initialise une boite de dialogue pour confirmation
            alertBox.setTitle("Bataille Navale - Confirmation suppression");            //On lui donne un titre
            alertBox.setHeaderText("La suppression de cette partie est définitive.");         //On lui donne un message destiné à l'utilisateur
            alertBox.setContentText("Êtes vous sûr de vouloir supprimer cette partie ?");           //On lui demande ce qu'il veut faire
            
            ButtonType boutonOui = new ButtonType("Oui");           //On déclare un bouton pour la réponse oui
            ButtonType boutonNon = new ButtonType("Non");           //On déclare un bouton pour la réponse non
            
            alertBox.getButtonTypes().setAll(boutonOui,boutonNon);
            
            Optional<ButtonType> choix = alertBox.showAndWait();            //On affiche la boite de dialogue et on attent la réponse de l'utilisateur
            if (choix.get() == boutonOui) {         //Si c'est oui
                Sauvegarde suppression = new Sauvegarde();          //On déclare une variable de type Sauvegarde
                suppression.supprimerPartie(nomSauvegarde, stage);          //On supprime la sauvegarde 
            }
        });
        
        HBox boutonSelectionOption = new HBox(80);          //On déclare un affichage horizontal avec les éléments qui sont espacés de 80 pixels
        boutonSelectionOption.getChildren().addAll(boutonRetour,transparent, instructionsJoueur, boutonCharger,boutonSupprimer);             //On rajoute des éléments à ce root
        boutonSelectionOption.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
        
        VBox rootAllElement = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        rootAllElement.setPadding(new Insets(25,25,25,20));             //On donne les dimensions entre les rebords de la fenètre et le root
        rootAllElement.getChildren().addAll(rootNavire, boutonSelectionOption);             //On rajoute des éléments à ce root
        
        Scene sceneJeu = new Scene(rootAllElement);            //On met le root dans la scène 
        stage.setScene(sceneJeu);           //On modifie et affiche la scène
    }


    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer à l'utilisateur que le chargement de la partie est en cours.
     * @param nomSauvegarde Le nom du fichier de sauvegarde
     * @param stage La fenêtre d'affichage
     */
    public void ecranChargement(String nomSauvegarde, Stage stage){
        VBox rootText = new VBox(25);         //On déclare un affichage vertical où les éléments sont espacé de 25 pixels
        final Label information = new Label ("Nous chargons la partie sélectionnée");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +                "-fx-font-size: 20pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        final Label information1 = new Label ("\n\nVeuillez patientez...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"          //On change les caractéristiques d'écriture
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information, information1);             //On rajoute des éléments à ce root
        rootText.setAlignment(Pos.CENTER);            //On positionne l'élément au centre
        Scene sceneEcranChargement = new Scene(rootText);           //On ajoute ce root à la scène
        stage.setScene(sceneEcranChargement);           //On modifie et affiche la scène
        
        
        Timeline timeChargement = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeChargement.getKeyFrames().addAll(new KeyFrame(Duration.millis(3000),action -> {            //On met un temps d'attente de 12s
            Sauvegarde chargementSauvegarde = new Sauvegarde();         //On déclare une variable de type Sauvegarde
            try {
                chargementSauvegarde.chargementPartie(nomSauvegarde);           //On charge la partie 
                stage.close();            //On ferme la fenètre
            } catch (ClassNotFoundException | InterruptedException ex) {
                System.err.println("Erreur! Le chargement n'as pas pu être effectué");          //On affiche un message en cas d'erreur
            }
        }));
        timeChargement.play();           //On démarre le temps de décalage dès que le programme le lit
    }   
}
