/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavalgraphique;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author Théric PHERON
 */
public class CaseBouton {
    
    int x;
    int y;
    Button bouton = new Button();
    
    
    
    public CaseBouton(int x, int y){
        this.x=x;
        this.y=y;
        bouton.setPrefSize(50,50);
        
        
        bouton.setStyle ("-fx-background-color: rgba(120,160,175,0.50)");
        bouton.setOnMouseEntered (e -> bouton.setStyle ("-fx-background-color: rgba(82,127,143,0.50)"));
        bouton.setOnMouseExited (e -> bouton.setStyle ("-fx-background-color: rgba(120,160,175,0.50)"));

        bouton.setOnAction((ActionEvent eventChargementPartie) -> {
            System.out.println("Tqt oici mes références x : "+x +" et y : "+y);
        });
        
        
    }
    
    public Button getButton(){
        return bouton;
    }
}
