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

        victoire();
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('N');
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('T');
        JeuGraphique.compteurTourHumain++; 
        
        GridPane rootJeu = new GridPane();
        rootJeu.setPadding(new Insets(20));
        rootJeu.setHgap(30);
        rootJeu.setVgap(20);
        
        grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);
        grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);
        
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
        
        Affichage.afficher(0, 0, JeuGraphique.plateauDeJeu);
        Affichage.afficher(0, 1, JeuGraphique.plateauDeJeu);
    }
    
    public void selectionNavire(int xPlateau, int yPlateau){
        
        char lRef = (char) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 0,0);
        int nPlateau = (int) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 0,1);
        int pListe = Flotte.nPlateauToPListe(lRef, nPlateau);
        int tailleNavire= JeuGraphique.flotteJoueur0.get(pListe).taille;
        int directionNavire = JeuGraphique.flotteJoueur0.get(pListe).direction;
        GrilleBoutons grilleBoutonNavire = new GrilleBoutons('D');
        grilleBoutonNavire.miseAJourAffichageNavire(JeuGraphique.plateauDeJeu);
        
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
        rootAffichagePlateau.add(affichageNavire.getRoot(directionNavire, lRef, nPlateau,(String) JeuGraphique.plateauDeJeu.get(xPlateau, yPlateau, 3, 0)),1,0);
        
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
        
        //int tailleNavire= flotteJoueur0.get(pListe).taille;
        //int directionNavire = flotteJoueur0.get(pListe).direction;
        List<Integer> listeInformations = new ArrayList<Integer> ();
        listeInformations.add(0, pListe);
        GrilleBoutons grilleBoutonTirs = new GrilleBoutons('B', listeInformations);
        grilleBoutonTirs.miseAJourAffichageTirs(JeuGraphique.plateauDeJeu);
        
        VBox rootselectionCaseTir = new VBox(40);
        rootselectionCaseTir.setPadding(new Insets(90,300,20,300));

        Label instruction = new Label ("Que souhaiter vous faire ?");
        instruction.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';-fx-font-size: 20pt;"
                + "-fx-font-weight: bold;");
        
        rootselectionCaseTir.setAlignment(CENTER);
        rootselectionCaseTir.getChildren().addAll(instruction, grilleBoutonTirs.getRoot());
        instruction.setAlignment(CENTER);
        System.out.println("On reviens bien la");
        Scene sceneSelectionNavire = new Scene(rootselectionCaseTir);
        JeuGraphique.fenetreJeu.setScene(sceneSelectionNavire);
    }

    public void tirEchec() throws InterruptedException{
        
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("Nous avons rien touché à ces coordonées");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("C'est au tour de l'IA");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);
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
    
    public void tirSurSousMarin() throws InterruptedException{
        
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("Nous avons détecté une structure mais n'avons pas pu la détruire");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("C'est au tour de l'IA");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);
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
    
    
    public void tirToucherNavire() throws InterruptedException{
        
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("C'est touché, bien joué");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 25pt; "
            +                "-fx-font-color: Color.GREEN;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("\n\n Au tour de l'IA");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);
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
    
    
    public void tirCoulerNavire(String nomNavireCoule) throws InterruptedException{
        
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("C'est touché, bien joué");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 25pt; "
            +                "-fx-font-color: Color.GREEN;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("Vous avez coulé un "+nomNavireCoule);
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 18pt;"
            +                "-fx-font-weight: bold;");
            
            Label information2 = new Label ("\n\n Au tour de l'IA");
            information2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1,information2);
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
    
    
    
    public void tourIA() throws InterruptedException{
        victoire();
        Timeline timeTourJoueur = new Timeline();
        timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(12000),action -> {
            try {
                JeuGraphique.ia.jouer();
            } catch (InterruptedException ex) {
                Logger.getLogger(AffichageJeuGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        timeTourJoueur.play();
        
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
    
    
    public void victoire(){
        ImageView croquette =new ImageView(getClass().getResource("/images/croquette.png").toString());
        ImageView croquetteHover =new ImageView(getClass().getResource("/images/croquetteHover.png").toString());
        
        Button bontonVictoire = new Button ("SUPER !");
        bontonVictoire.setGraphic(croquetteHover);
        bontonVictoire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 17pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);");
        bontonVictoire.setOnMouseEntered (e->
        bontonVictoire.setGraphic(croquette)); 
        bontonVictoire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(82,127,143,0.50);");
        bontonVictoire.setOnMouseExited (e-> 
        bontonVictoire.setGraphic(croquetteHover));
        bontonVictoire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        /*bontonVictoire.setOnAction((ActionEvent eventLancementJeu) -> {
            fenetreJeu.setScene(sceneLancementMenuPrincipal(fenetreJeu));
        });*/

        Label labelAffichageVictoireJoueur = new Label ("VICTOIRE DU JOUEUR !!");
        labelAffichageVictoireJoueur.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        Label labelAffichageVictoireIA = new Label ("VICTOIRE DE L'ORDINATEUR !!");
        labelAffichageVictoireIA.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        Label labelAffichageInfoPartie = new Label ("\nInformation sur la partie : "
            +"\nNombre de tour : "+compteurTourHumain+compteurTourIA
            +"\nNombre de tour du joueur : "+ compteurTourHumain
            +"\nNombre de tour de l'IA : "+compteurTourIA);
        labelAffichageInfoPartie.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");
        Label labelAffichageVictoireSousMarin = new Label ("Vous n'avez plus de sous-marin"
            +"L'ordinateur gagne par fofait de votre part");
        labelAffichageVictoireSousMarin.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);");

        GridPane rootVictoire = new GridPane();
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
            if (etat0SousMarin==false) etat0SousMarin=flotteJoueur0.get(i).etat;            //Si le sous marin n'a pas coulé, on prend l'état du sous-marin
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
            if (etat1SousMarin==false) etat1SousMarin=flotteJoueur0.get(i).etat;            //Si le sous marin n'a pas coulé, on prend l'état du sous-marin
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
