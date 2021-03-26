package bataillenaval.controller;

import bataillenaval.view.Menu;


public class BatailleNaval {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {            //On lève les exceptions
       
        Menu menu = new Menu();         //On déclare une variable de type Menu
        menu.menuPrincipal();           //On lance le menu principal
    }
}
