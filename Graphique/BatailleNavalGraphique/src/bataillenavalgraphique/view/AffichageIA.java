package bataillenavalgraphique.view;

import bataillenavalgraphique.controller.JeuGraphique;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Classe AffichageIA.
 * Toutes les méthodes utilisées pour l'affichage des informations concernant l'IA
 * @author Théric PHERON and Joé LHUERRE
 */
public class AffichageIA {
    
    //**************************************************************************
    /**
     * Constructeur de la classe Affichage IA.
     * Il permet à l'IA d'afficher un message.
     */
    public AffichageIA(){   
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet dafficher un message si un coup de déplacement d'un navire à bien eu lieu.
     */
    public void manoeuvreSucces(){
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information = new Label ("La manoeuvre a été réalisée avec succès");          //On informe l'utilisateur que tout c'est bien passé
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            final Label information1 = new Label ("\n\nC'est à votre tour");          //On informe au joueur que c'est à son tour
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);           //On rajoute les deux label au root        
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre 
            Scene sceneManoeuvreSucces = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneManoeuvreSucces);         //On affiche la scène
            
            tourJoueur();           //On lance le tour du joueur
            
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet dafficher un message quand l'IA tir sur une coordoné.
     * @param xTire La coordonées x de tir
     * @param yTire La coordonées y de tir
     */
    public void tirRandomIA(int xTire, int yTire){
        
        VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
        final Label information = new Label ("Tour IA");          //On informe l'utilisateur que c'est le tour de l'IA
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 30pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        final Label information0 = new Label ("\n\nJe vais effectuer un tir sur les coordonnées :");          //On informe au joueur que l'IA va effectué un tir 
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");
        final Label information1 = new Label ("18°05'57."+ yTire +"\"S ; 53°43'30."+ xTire+"\"E");            //A ces coordonnées la (qui sont factice)
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 18pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information,information0, information1);           //On rajoute les trois label au root
        rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message quand l'IA tir avec un destroyer.
     */
    public void tirDestroyer(){
        
        VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
        final Label information = new Label ("Tour IA");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 30pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        final Label information0 = new Label ("\n\nJe décide de tirer une fusée avec mon destroyer");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 18pt;"
        +                "-fx-font-weight: bold;");
        final Label information1 = new Label ("Veuillez patienter...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information,information0, information1);           //On rajoute les trois label au root
        rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'afficher une message quand le tir avec le destroyer à bien eu lieu.
     */
    public void tirEffectueDestroyer(){
        Timeline timeNiveau1TirRandom = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information0 = new Label ("Mes avions m'ont rapportés des informations intéréssantes");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information1 = new Label ("J'en prends note");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information2 = new Label ("\n\nA votre tour");
            information2.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1,information2);           //On rajoute les trois label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène
            
            tourJoueur();           //On lance le tour du joueur
        }));
        timeNiveau1TirRandom.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message quand l'IA a touché un navire.
     */
    public void toucherNavire(){
        Timeline timeNiveau1TirRandom = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information0 = new Label ("Et c'est touché !!");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 30pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information1 = new Label ("\n\nA votre tour");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);           //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène
            
            tourJoueur();
        }));
        timeNiveau1TirRandom.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message quand l'IA a touché un sous-marin.
     */
    public void toucherSousMarin(){
        Timeline timeNiveau1TirRandom = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information0 = new Label ("On a touché une structure mais nous n'avons pas pu la couler");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 25pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information1 = new Label ("Intéréssant...");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");
            
            
            final Label information2 = new Label ("\n\nA votre tour");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 18pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1, information2);           //On rajoute les trois label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène
            
            tourJoueur();
        }));
        timeNiveau1TirRandom.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'aficher un message quand l'IA d'écide de déplacer un navire
     */
    public void deplacemmentNavire(){
        VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
        final Label information = new Label ("Tour IA");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 30pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        final Label information0 = new Label ("\n\nJe décide de déplacer mon navire");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");
        final Label information1 = new Label ("Patientez...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
        +                "-fx-font-size: 18pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information,information0, information1);           //On rajoute les trois label au root
        rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre
        Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message quand l'IA a coulé un navire.
     * @param nomNavireCoule Le nom du navire coulé
     */
    public void coulerNavire(String nomNavireCoule){
        Timeline timeNiveau1TirRandom = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),action -> {           //On met un temps d'attente de 8s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            
            final Label information0 = new Label ("\n\n Yes !! J'ai coulé un "+nomNavireCoule);
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");
            
            final Label information1 = new Label ("\n\nA votre tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre 
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène qui est dans la fenêtre
            
            
            tourJoueur();           //On lance le tour du joueur
        }));
        timeNiveau1TirRandom.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet d'afficher un message quand l'IA a raté un tir.
     */
    public void tirRate(){
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {           //On met un temps d'attente de 8s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information = new Label ("J'ai rien touché aux coordonées de tir");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 25pt;"
            +                "-fx-font-weight: bold;");
            final Label information0 = new Label ("Quel échec\n\n");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 13pt;"
            +                "-fx-font-weight: bold;");
            final Label information1 = new Label ("A votre tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            

            rootText.getChildren().addAll(information,information0, information1);          //On rajoute les trois label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre 
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène qui est dans la fenêtre
            
            tourJoueur();           //On lance le tour du joueur
            
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit 
        
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'indiquer à l'utilisateur qu'il y a eu un problème lors du déplacement d'un navire.
     * La méthode permet d'afficher une message d'erreur pour indiquer à l'utilisateur que l'IA va effcetuer un autre tire
     * @throws InterruptedException
     */
    public void panneMoteur() throws InterruptedException{
        
        Timeline timeTourJoueur = new Timeline();                    //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(5000),action -> {           //On met un temps d'attente de 5s
            final Label information = new Label("Le navire que j'ai choisi a un problème moteur");           //On informe au joueur que l'IA relance son tour car il ne peut pas déplacer le navire qu'elle a sélectionné
            final Label information2 = new Label("\nJ'effectue un autre coup");

            VBox rootText = new VBox(25);           //On déclare un affichage vertical où les éléments sont espacés de 25 pixels
            rootText.getChildren().addAll(information, information2);           //On ajoute les label au root
            Scene scenePanneMoteur = new Scene(rootText);           //On met le root dans la scène
            JeuGraphique.fenetreJeu.setScene(scenePanneMoteur);         //On modifie la scène  
    

            try {
                
                JeuGraphique.ia.jouer();            //On lance le programme jouer 
            } catch (InterruptedException ex) {
                System.err.println("Erreur! Le lancement du tour du l'IA n'a pas pu être effectuer");         //On affiche un message d'erreur
            }
            JeuGraphique.compteurTourIA--;
        }));
        timeTourJoueur.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de lancer le tour de l'utilisateur.
     */
    public void tourJoueur(){
        Timeline timeTourJoueur = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(5000),action -> {           //On met un temps d'attente de 5s
            AffichageJeuGraphique affichage = new AffichageJeuGraphique();          //On déclare un objet de type AffichageJeuGraphique
            affichage.affichageJoueur();            //On affiche le menu principal du joueur 
        }));
        timeTourJoueur.play();            //On démarre le temps de décalage dès que le programme le lit 
    }

    
    //**************************************************************************
    /**
     * Méthode qui permet d'afficher s'il y a eu une erreur avec l'IA.
     * @param erreur Le message d'erreur
     */
    public void erreurIA(String erreur){
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(3000),action -> {           //On met un temps d'attente de 8s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            final Label information = new Label ("Erreur IA. "+ erreur);
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 12pt;"
            +                "-fx-background-color: RED;"
            +                "-fx-font-weight: bold;");
            final Label information1 = new Label ("\n\nJe recommance mon tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre 
            Scene sceneManoeuvreSucces = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneManoeuvreSucces);         //On affiche la scène qui est dans la fenêtre
            
            try {
                JeuGraphique.ia.jouer();            //Lance jouer 
            } catch (InterruptedException e) {
                System.err.println("Erreur!_ Lancement tour IA");           //Affiche un message d'erreur 
            }           //On lance le tour du joueur
            JeuGraphique.compteurTourIA--;
            
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
}
