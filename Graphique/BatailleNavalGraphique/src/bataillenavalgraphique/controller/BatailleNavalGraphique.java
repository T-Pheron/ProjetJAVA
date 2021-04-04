package bataillenavalgraphique.controller;

import bataillenavalgraphique.view.MenuGraphique;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Classe main.
 * Utilisé pour le lancement du jeu.
 * @author Théric PHERON and Joé LHUERRE
 */
public class BatailleNavalGraphique extends Application {
    
    /**
     * Méthode qui lance la fenêtre principal.
     */
    @Override
    public void start(Stage primaryStage) {
        
        MenuGraphique menu = new MenuGraphique();           //On déclare un objet de type MenuGraphique
        menu.menuPrincipalGraphique();          //On appelle le menu principal
    }

    /**
     * La main du programme.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
