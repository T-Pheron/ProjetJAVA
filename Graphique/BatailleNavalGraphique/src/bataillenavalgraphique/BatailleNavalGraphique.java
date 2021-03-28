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
        
        MenuGraphique menu = new MenuGraphique();
        menu.menuPrincipal();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
