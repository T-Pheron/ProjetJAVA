package bataillenavalgraphique;


import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Théric PHERON
 */
public class BatailleNavalGraphique extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        MenuGraphique menu = new MenuGraphique();           //On déclare un objet de type MenuGraphique
        menu.menuPrincipalGraphique();          //On appelle le menu principal
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);           //On lance le jeu
    }
    
}
