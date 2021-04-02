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
    
    public void entrerIA(){
        
    }
    
    public void manoeuvreSucces(){
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(7000),action -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("La manoeuvre a été réalisés avec succès");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("\n\nC'est à votre tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneManoeuvreSucces = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneManoeuvreSucces);
            
            tourJoueur();
            
        }));
        time.play();
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
        Timeline timeNiveau1TirRandom = new Timeline();
        timeNiveau1TirRandom.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),action -> {
            VBox rootText = new VBox(25);
            
            Label information0 = new Label ("\n\n Yes!! J'ai coulé un "+nomNavireCoule);
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
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
    

    
    public void tirRate(){
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(8000),e -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("J'ai rien touché aux coordonées de tir");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 25pt;"
            +                "-fx-font-weight: bold;");
            Label information0 = new Label ("Quel échec\n\n");
            information0.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 13pt;"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("A votre tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 20pt;"
            +                "-fx-background-color: rgba(120,160,175,0.50);"
            +                "-fx-font-weight: bold;");
            

            rootText.getChildren().addAll(information,information0, information1);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneNiveau1TirRandom = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneNiveau1TirRandom);
            
            tourJoueur();
            
        }));
        time.play();
        
    }
    
    
    
    
    
    
    public void tourJoueur(){
        Timeline timeTourJoueur = new Timeline();
        timeTourJoueur.getKeyFrames().addAll(new KeyFrame(Duration.millis(5000),action -> {
            AffichageJeuGraphique affichage = new AffichageJeuGraphique();
            affichage.affichageJoueur();
        }));
        timeTourJoueur.play();
    }

    
    public void erreurIA(String erreur){
        Timeline time = new Timeline();
        time.getKeyFrames().addAll(new KeyFrame(Duration.millis(3000),action -> {
            VBox rootText = new VBox(25);
            Label information = new Label ("Erreur IA. "+ erreur);
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 12pt;"
            +                "-fx-background-color: RED;"
            +                "-fx-font-weight: bold;");
            Label information1 = new Label ("\n\nC'est à votre tour");
            information1.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
            +                "-fx-font-size: 15pt;"
            +                "-fx-font-weight: bold;");

            rootText.getChildren().addAll(information, information1);
            rootText.setAlignment(Pos.CENTER);
            Scene sceneManoeuvreSucces = new Scene(rootText);
            JeuGraphique.fenetreJeu.setScene(sceneManoeuvreSucces);
            
            tourJoueur();
            
        }));
        time.play();
    }
}
