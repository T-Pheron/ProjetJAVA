package bataillenavalgraphique;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class AffichageIA {
    
    public AffichageIA(){   
    }
    
    
    public void manoeuvreSucces(){
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {           //On met un temps d'attente de 7s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            Label information = new Label ("La manoeuvre a été réalisés avec succès");          //On informe l'utilisateur que tout c'est bien passé
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"         //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("\n\nC'est à votre tour");
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
    
    public void tirRandomIA(int xTire, int yTire){
        
        VBox rootText = new VBox(25);
        Label information = new Label ("Tour IA");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 30pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        Label information0 = new Label ("\n\nJe vais effectuer un tir sur les coordonnées :");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");
        Label information1 = new Label ("18°05'57."+ yTire +"\"S ; 53°43'30."+ xTire+"\"E");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 18pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information,information0, information1);
        rootText.setAlignment(Pos.CENTER);
        Scene sceneNiveau1TirRandom = new Scene(rootText);
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
    }
    
    public void tirDestroyer(){
        
        VBox rootText = new VBox(25);
        Label information = new Label ("Tour IA");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 30pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        Label information0 = new Label ("\n\nJe décide de tirer une fussée avec mon destroyer");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 18pt;"
        +                "-fx-font-weight: bold;");
        Label information1 = new Label ("Veuillez patienter...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information,information0, information1);
        rootText.setAlignment(Pos.CENTER);
        Scene sceneNiveau1TirRandom = new Scene(rootText);
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
    }
    
    
    public void tirEffectueDestroyer(){
        Timeline timeNiveau1TirRandom = new Timeline();
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {
            VBox rootText = new VBox(25);
            Label information0 = new Label ("Mes avions m'ont rapportés des informations intéréssants");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");
            
            Label information1 = new Label ("Je prends note");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");
            
            Label information2 = new Label ("\n\nA votre tour");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1,information2);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneNiveau1TirRandom = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
            
            tourJoueur();
        }));
        timeNiveau1TirRandom.play();
    }
    
    
    
    
    
    public void toucherNavire(){
        Timeline timeNiveau1TirRandom = new Timeline();
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {
            VBox rootText = new VBox(25);
            Label information0 = new Label ("Et c'est touché !!");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 30pt;"
            +                "-fx-font-weight: bold;");
            
            Label information1 = new Label ("\n\nA votre tour");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneNiveau1TirRandom = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
            
            tourJoueur();
        }));
        timeNiveau1TirRandom.play();
    }
    
    
    public void toucherSousMarin(){
        Timeline timeNiveau1TirRandom = new Timeline();
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {
            VBox rootText = new VBox(25);
            Label information0 = new Label ("On a touché une structure mais n'avons pas pu la coulé");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 25pt;"
            +                "-fx-font-weight: bold;");
            
            Label information1 = new Label ("Intéréssant...");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");
            
            
            Label information2 = new Label ("\n\nA votre tour");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 18pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1, information2);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneNiveau1TirRandom = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
            
            tourJoueur();
        }));
        timeNiveau1TirRandom.play();
    }
    
    
    
    public void deplacemmentNavire(){
        VBox rootText = new VBox(25);
        Label information = new Label ("Tour IA");
        information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 30pt;"
        +                "-fx-background-color: rgba(120,160,175,0.50);"
        +                "-fx-font-weight: bold;");
        Label information0 = new Label ("\n\nJe décide de déplacer mon navire");
        information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 15pt;"
        +                "-fx-font-weight: bold;");
        Label information1 = new Label ("Patientez...");
        information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
        +                "-fx-font-size: 18pt;"
        +                "-fx-font-weight: bold;");

        rootText.getChildren().addAll(information,information0, information1);
        rootText.setAlignment(Pos.CENTER);
        Scene sceneNiveau1TirRandom = new Scene(rootText);
        JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
    }
    
    
    
    public void coulerNavire(String nomNavireCoule){
        Timeline timeNiveau1TirRandom = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),action -> {           //On met un temps d'attente de 8s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            
            Label information0 = new Label ("\n\n Yes!! J'ai coulé un "+nomNavireCoule);
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");
            
            Label information1 = new Label ("\n\nA votre tour");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-font-weight: bold;");


            rootText.getChildren().addAll(information0, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre 
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène qui est dans la fenêtre
            
            
            tourJoueur();           //On lance le tour du joueur
        }));
        timeNiveau1TirRandom.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
    

    
    public void tirRate(){
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {           //On met un temps d'attente de 8s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            Label information = new Label ("J'ai rien touché aux coordonées de tir");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 25pt;"
            +                "-fx-font-weight: bold;");
            Label information0 = new Label ("Quel échec\n\n");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 13pt;"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("A votre tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            

            rootText.getChildren().addAll(information,information0, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre 
            Scene sceneNiveau1TirRandom = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);         //On affiche la scène qui est dans la fenêtre
            
            tourJoueur();           //On lance le tour du joueur
            
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit 
        
    }
    
    
    
    
    public void tourJoueur(){
        Timeline timeTourJoueur = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(5000),action -> {           //On met un temps d'attente de 5s
            AffichageJeuGraphique affichage = new AffichageJeuGraphique();          //On déclare un objet de type AffichageJeuGraphique
            affichage.affichageJoueur();            //On affiche le menu principal du joueur 
        }));
        timeTourJoueur.play();            //On démarre le temps de décalage dès que le programme le lit 
    }

    
    public void erreurIA(String erreur){
        Timeline time = new Timeline();         //Variable qui permet de déclencher une action en décaler par rapport au programme
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(3000),action -> {           //On met un temps d'attente de 8s
            VBox rootText = new VBox(25);           //On déclare un affichage vertical avec des éléments espacés de 25 pixels
            Label information = new Label ("Erreur IA. "+ erreur);
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 12pt;"
            +                "-fx-background-color: RED;"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("\n\nC'est à votre tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"            //On change les caractéristiques d'écriture
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);          //On rajoute les deux label au root
            rootText.setAlignment(Pos.CENTER);          //On positionne les éléments du root au centre 
            Scene sceneManoeuvreSucces = new Scene(rootText);           //On met le root dans une scène
            JeuGraphique.fenetreJeu.setScene(sceneManoeuvreSucces);         //On affiche la scène qui est dans la fenêtre
            
            tourJoueur();           //On lance le tour du joueur
            
        }));
        time.play();            //On démarre le temps de décalage dès que le programme le lit 
    }
}
