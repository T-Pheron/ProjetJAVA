package bataillenaval;


import java.util.*;


public class Menu {
    public Menu(){
        
    }
    
    //**************************************************************************
    //Variables utilisées pour modifier les couleurs du texte
    public final String RESET = "\u001B[0m";
    public final String NOIR = "\u001B[30m";
    public final String ROUGE = "\u001B[31m";
    public final String VERT = "\u001B[32m";
    public final String JAUNE = "\u001B[33m";
    public final String BLEU = "\u001B[34m";
    public final String VOILET = "\u001B[35m";
    public final String CYAN = "\u001B[36m";
    public final String BLANC = "\u001B[37m";
    //Variable utilisées pour modifier les couleurs de l'arriere plan
    public final String RESETAR = "\u001B[40m";
    public final String ROUGEAR = "\u001B[41m";
    public final String VERTAR = "\u001B[42m";
    public final String JAUNEAR = "\u001B[43m";
    public final String BLEUAR = "\u001B[44m";
    public final String VOILETAR = "\u001B[45m";
    public final String CYANAR = "\u001B[46m";
    public final String GRISAR = "\u001B[47m";
     
    
    
    //**************************************************************************
    /*Menu de début de jeu. Permet le lancement du jeu, le chargement d'une
    partie sauvegardée, le lancement de l'aide, quitter le jeu.
    */
    public void menu_principal(){
        int choix;
        int i;
        Scanner sc = new Scanner(System.in);
        Jeu jeu = new Jeu();

        System.out.println(GRISAR +NOIR+ "                  MENU PRINCIPAL                "+RESET+ RESETAR);
        System.out.println("1. Jouer une partie \n2. Charger une partie\n3. Aide \n4. Quitter");
        System.out.println("Veuillez saisir votre choix :");
        choix=sc.nextInt();
        while ((choix<1)||(choix>4)){
            System.out.println(ROUGE +"Erreur_menu_principal!"+ RESET+ "\nVeuillez saisir à nouveau votre choix :");
            choix=sc.nextInt();    
        }
        
        
        switch (choix){
            case 1: System.out.println(""); jeu.initialiser_jeu(); //programme a mettre pour le premier choix
            case 2://programme a mettre pour le second choix
            case 3://programme a mettre pour l'aide
            case 4://programme a mettre quitter la partie
        }
    }
    
    //**************************************************************************
    //Menu qui permet au joueur de choisir l'action qui veut réaliser
    public void menu_joueur(){
        int choix;
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println(GRISAR +NOIR+ "               MENU JOUEUR              "+RESET+ RESETAR);
        System.out.println("1. Tirer ! \n2. Deplacer le navire !");
        System.out.println("Veuillez saisir votre choix");
        choix=sc.nextInt();
        while ((choix<1)||(choix>2)){
            System.out.println(ROUGE+"Erreur menu joueur !"+ RESET +"\nVeuillez saisir à nouveau votre choix");
            choix=sc.nextInt();    
        }
        switch (choix){
            case 1://programme a mettre pour que le joueur puisse tirer
            case 2://programme a mettre pour que le joueur puisse deplacer son bateau

        }
    }
    
    
    //**************************************************************************
    //Menu qui pemet de sauvegarder une partie de jeu
    public void menu_sauvegarde(){
        
    }
    
    //**************************************************************************
    //Menu qui pemet de recharger une partie de jeu
    public void menu_chargement(){
        
    }
    
    
    //**************************************************************************
    //Menu qui pemet de naviguer dans l'aide du jeu
    public void menu_aide(){
        int choix;
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println(GRISAR +NOIR+ "               MENU AIDE              "+RESET+ RESETAR);
        System.out.println("1.premiere categorie d'aide \2. deuxieme categorie d'aide \n3. troisieme categorie d'aide ");
        System.out.println("Veuillez saisir votre choix");
        choix=sc.nextInt();
        while ((choix<1)||(choix>2)){
            System.out.println(ROUGE+"Erreur menu aide !"+ RESET +"\nVeuillez saisir à nouveau votre choix");
            choix=sc.nextInt();    
        }
        switch (choix){
            case 1://premiere categorie d'aide
            case 2://deuxieme categorie d'aide
            case 3://troisieme categorie d'aide 

        }    
    }
    
    public static char convertirMinuscules(char lettre){
        if (lettre<65||lettre>90){
            return lettre -= 'a'-'A';
        }
        return lettre;
    }
}
