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
    String couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";
    String couleurSurvol = "-fx-background-color: rgba(82,127,143,0.80);";
    String couleurText = "-fx-text-fill: BLACK ;";
    
    
    public CaseBouton(int y, int x){
        this.x=x;
        this.y=y;
        bouton.setPrefSize(75,75);
        
        
        
        bouton.setStyle(couleurDefaut + couleurText);
        bouton.setOnMouseEntered (e -> bouton.setStyle (couleurSurvol + couleurText));
        bouton.setOnMouseExited (e -> bouton.setStyle (couleurDefaut + couleurText));

        bouton.setOnAction((ActionEvent eventChargementPartie) -> {
            System.out.println("Tqt voici mes références x : "+x +" et y : "+y);
        });

    }
    
    public Button getButton(){
        return bouton;
    }

    public void setTitle(String titre){
        bouton.setText(titre);
    }
    
    public void setColor(char lRef){
        if (lRef =='U'){
            couleurDefaut="-fx-background-color: rgba(221,13,13,0.50);";
            couleurSurvol="-fx-background-color: rgba(210,0,0,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (lRef =='C'){
            couleurDefaut="-fx-background-color: rgba(203,25,228,0.50);";
            couleurSurvol="-fx-background-color: rgba(181,25,203,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (lRef =='D'){
            couleurDefaut="-fx-background-color: rgba(15,141,214,0.50);";
            couleurSurvol="-fx-background-color: rgba(12,122,182,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (lRef =='S'){
            couleurDefaut="-fx-background-color: rgba(27,213,41,0.50);";
            couleurSurvol="-fx-background-color: rgba(15,185,30,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (lRef =='_'){
            couleurDefaut="-fx-background-color: rgba(120,160,175,0.50);";
            couleurSurvol="-fx-background-color: rgba(82,127,143,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
    }
    
    
    public void setColor(String numeroPlateau){
        if (numeroPlateau.equals("0")){
            couleurDefaut = "-fx-background-color: rgba(120,160,175,0.50);";
            couleurSurvol = "-fx-background-color: rgba(82,127,143,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("1")){
            couleurDefaut="-fx-background-color: rgba(225,225,225,0.80);";
            couleurSurvol="-fx-background-color: rgba(233,233,233,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("2")){
            couleurDefaut="-fx-background-color: rgba(255,16,16,0.80);";
            couleurSurvol="-fx-background-color: rgba(220,15,15,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("3")){
            couleurDefaut="-fx-background-color: rgba(254,178,107,0.50);";
            couleurSurvol="-fx-background-color: rgba(255,163,77,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("4")){
            couleurDefaut="-fx-background-color: rgba(255,150,53,0.80);";
            couleurSurvol="-fx-background-color: rgba(234,132,37,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
        if (numeroPlateau.equals("5")){
            couleurDefaut="-fx-background-color: rgba(224,249,117,0.80);";
            couleurSurvol="-fx-background-color: rgba(197,221,93,0.80);";
            bouton.setStyle(couleurDefaut + couleurText);
        }
    }
    
}
