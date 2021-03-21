package bataillenaval;


import java.util.*;


public class Menu {
    
    //**************************************************************************
    /**
     * Constructeur des menus.
     * Permet de créer des variables Menu
     */
    public Menu(){
        
    }
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs du texte
    /*Elles doivent être placées devant le texte qui doit être mis en couleur avec un +*/
    
    public static final String RESET = "\u001B[0m";                //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public static final String NOIR = "\u001B[30m";
    public static final String ROUGE = "\u001B[31m";
    public static final String VERT = "\u001B[32m";
    public static final String JAUNE = "\u001B[33m";
    public static final String BLEU = "\u001B[34m";
    public static final String VOILET = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANC = "\u001B[37m";
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs de l'arriere plan*/
    /*Elles doivent être placées devant le texte qui doit avoir une arrière plan en couleur avec un +*/
    
    public static final String RESET_AR = "\u001B[40m";            //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public static final String ROUGE_AR = "\u001B[41m";
    public static final String VERT_AR = "\u001B[42m";
    public static final String JAUNE_AR = "\u001B[43m";
    public static final String BLEU_AR = "\u001B[44m";
    public static final String VOILET_AR = "\u001B[45m";
    public static final String CYAN_AR = "\u001B[46m";
    public static final String GRIS_AR = "\u001B[47m";
     
    
    
    //**************************************************************************
    /**
     * Menu de lancement du programme. 
     * Permet le lancement du jeu, le chargement d'une 
     * partie sauvegardée, le lancement de l'aide, quitter le jeu.
     * @throws java.lang.InterruptedException
    */
    public void menuPrincipal() throws InterruptedException{
        int choix=0;              //Variable qui permet de stocker le choix du joueur
        Scanner sc = new Scanner(System.in);                //Variable qui permet de récuperer la saisie du clavier
        Jeu jeu = new Jeu();                //On initialise une variable de type Jeu 

        
        System.out.println(GRIS_AR +NOIR+ "                  MENU PRINCIPAL                "+RESET+ RESET_AR);           //On afficher le titre du menu
        System.out.println("1. Jouer une partie \n"
                +          "2. Charger une partie\n"
                +          "3. Aide \n"
                +          "4. Quitter");           //On affiche les choix possibles
        
        System.out.println("Veuillez saisir votre choix :");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = sc.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            sc.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>4)){           //On blinde en vérifiant que la saisie fait partie des choix possibles
            System.out.println(ROUGE +"Erreur! "+ RESET+ "Veuillez saisir à nouveau votre choix :");           //Sinon, on affiche un message d'erreur et demande à nouveau la saisie
            try{
                choix = sc.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                sc.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        
        switch (choix){
            case 1:            //Si le choix est 1
                System.out.println(""); 
                System.out.println("Bienvenue dans le jeu de la "+ CYAN +"Bataille Navale"+ RESET);           //On affiche un message de bienvenue
                System.out.println("Voici vos grilles au début de la partie");
                jeu.lancementJeu();             //On appel la méthode qui permet de lancer une nouvelle partie
                break;
            case 2: sc.close(); break;
            case 3: sc.close(); break;
            case 4: sc.close(); break;
            default : System.out.println(ROUGE + "Erreur_menuPrincipal!"+ RESET); break;            //En cas d'erreur, on affiche un message
        }
    }
    
    //**************************************************************************
    /**
     * Menu qui permet au joueur de choisir l'action qui veut réaliser.
     * Ce menu retourne le choix du joueur.<br>
     * 1 s'il veut tirer.<br>
     * 2 s'il veut déplacer un navire.<br>
     * 0 En cas d'erreur.
     * @return Le choix du joueur
     */
    public int menuJoueur(){
        int choix=0;           //Variable qui permet de stocker le choix du joueur
        Scanner sc = new Scanner(System.in);           //Variable qui permet de récuperer la saisie du clavier
        
        
        System.out.println("");
        System.out.println(GRIS_AR +NOIR+ "               MENU JOUEUR              "+RESET+ RESET_AR);           //On afficher le titre du menu
        System.out.println("Que voulez vous faire ?");
        System.out.println("1. Tirer ! "
                +        "\n2. Deplacer le navire !");           //On affiche les choix possibles
        
        System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = sc.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            sc.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>2)){               //On blinde en vérifiant que la saisie fait partie des choix
            System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
            try{
                choix = sc.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                sc.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        switch (choix){
            case 1: sc.close(); return 1;                //Si le choix est 1 alors on retourne 1 (Cela signifie que le joueur souhaite effectuer un tir)
            case 2: sc.close(); return 2;               //Si le choix est 2 alors on retounr 2 (Cela signifie que le joueur souhaite déplacer son navire)
            
            default: System.out.println("Erreur_menuJoueur!"); sc.close(); return 0;           //En cas d'erreur, on affiche un message
        }
    }
    
    
    //**************************************************************************
    /**
     * Menu qui permet de sauvegarder une partie de jeu.
     * 
     */
    public void menuSauvegarde(){
        
    }
    
    //**************************************************************************
    /**
     * Menu qui permet de charger une partie de jeu.
     * 
     */
    public void menuChargement(){
        
    }
    
    
    //**************************************************************************
    /**
     * Menu qui permet de naviguer dans l'aide du jeu.
     * Il y a plusieur catégorie dans laide. Ce menu permet de séparer les informations
     * et de ne pas toutes les affichées en même temps.
     */
    public void menuAide(){
        int choix=0;           //Variable qui permet de stocker le choix du joueur
        Scanner sc = new Scanner(System.in);           //Variable qui permet de récuperer la saisie du clavier
        
        
        System.out.println(GRIS_AR +NOIR+ "               MENU AIDE              "+RESET+ RESET_AR);           //On afficher le titre du menu
        System.out.println("1.premiere categorie d'aide \2. deuxieme categorie d'aide \n3. troisieme categorie d'aide ");           //On affiche les choix possibles
        
        System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = sc.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            sc.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>2)){           //On blinde en vérifiant que la saisie fait partie des choix
            System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
            try{
                choix = sc.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                sc.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        
        switch (choix){
            case 1: sc.close();           //premiere categorie d'aide
            case 2: sc.close();          //deuxieme categorie d'aide
            case 3: sc.close();          //troisieme categorie d'aide 

        }    
    }
    
    
    //**************************************************************************
    /**
     * Converti les minuscules en majuscule.
     * Cette méthode recois une lettre en majuscule ou en misnucule et la transforme
     * si nécésaire en lettre majuscule.
     * @param lettre La lettre à convertir
     * @return La lettre en majuscule
     */
    public static char convertirMinuscules(char lettre){
        if (lettre<65||lettre>90){                  //On vérifie que la lettre est une lettre minuscule
            return lettre -= 'a'-'A';                   //Si c'est le cas on la convertie en majuscule en lui retirant la différence qu'il y a entre les minuscules et les majucules
        }
        return lettre;              //On retourne la lettre en majuscule
    }

    
    //**************************************************************************
    /**
     * Menu pour le choix du navire qui va tirer.
     * Ce menu est utilisé pour savoir quel navire la joueur souhaite utiliser pour
     * effectuer son tire. Il vérifie que le navire est dans la capacité de tirer.
     * Il retourne : <br>
     * 1 si le tir a bien eu lieu<br>
     * 2 en cas d'une opération qui nécésite la relance du tour du joueur<br>
     * 0 en cas d'erreur majeur
     * @param flotte La flotte du joueur qui tire
     * @param numero_joueur Le numéro du joueur qui tire
     * @return Les instrcutions pour la boucle du jeu
     * @throws java.lang.InterruptedException
     */
    public int menuTirer (List<Flotte> flotte, int numero_joueur) throws InterruptedException{
        Scanner sc = new Scanner(System.in);           //Variable qui permet de récuperer la saisie du clavier
        
        
        System.out.println(GRIS_AR +NOIR+ "               MENU TIRE              "+RESET+ RESET_AR);           //On afficher le titre du menu
        
        char lRef='A';            //On iniatilise une variable de type char qui permet de stocker la lettre de référence du navire choix
        int nPlateau=0;            //Variable qui permet de stocker la numéro de plateau du navire choisie par l'utilisateur
        int maxNavire=0;           //Variable qui permet de stocker le maximun de bateau d'une catégorie de navire que contient la flotte

        System.out.println("Veuillez entrer la lettre du navire avec lequel vous voulez tirer :");           //On demande à l'utilisateur de saisir la lettre du navire
        try{
            lRef= sc.next().charAt(0);          //On stock la saisie de l'utilisateur
            lRef=Menu.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule
        }
        catch(InputMismatchException e){            //Si ce n'est pas un caractère
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
            sc.next();            //On met à la poubelle la saisie de l'utilisateur
        }   
        lRef=Menu.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule

        while (lRef!='U'&&lRef!='C'&&lRef!='D'&&lRef!='S'){           //On blinde, en vérifiant que sa saisie fait partie des choix possible
            System.out.println(ROUGE + "Erreur !"+RESET);           //Sinon on affiche un message d'erreur
            System.out.println("Veuillez entrer la lettre du navire avec lequel vous voulez tirer :");           //On lui demande de ressaisir
            try{
                lRef= sc.next().charAt(0);          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un caractère
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                sc.next();            //On met à la poubelle la saisie de l'utilisateur
                lRef='A';
            }
            lRef=Menu.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule
        }

        if (lRef=='U') nPlateau=1;           //Si le choix du bateaue est cuirasse, le choix du numéro du bateau n'est pas nécésaire
        else {
            System.out.println("Veuillez entrer le numero du navire avec lequel vous voulez tirer :");           //Sinon, on demande à l'utilisateur de rentrer le numéro affiché sur le plateau du bateau qu'il souhaite utiliser pour tirer
            try{
                nPlateau = sc.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                sc.next();            //On met à la poubelle la saisie de l'utilisateur
            }
            if (lRef=='C') maxNavire=2;            //On affecte à la variable maxNavire le numéro maximal de navire de ce type que contient la liste en fonction de la lettre de référence du navire
            if (lRef=='D') maxNavire=3;
            if (lRef=='S') maxNavire=4;


            while (nPlateau<1||nPlateau>maxNavire){           //On blinde, en vérifiant que la saisie fait partie des choix
                System.out.println(ROUGE + "Erreur! "+RESET +"Ce numéro ne fait pas parti des choix.");           //Sinon, on affiche un message d'erreur
                System.out.println("Veuillez entrer le numero du navire avec lequel vous voulez tirer : :");           //Et on demande la ressaisie
                try{
                    nPlateau = sc.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    sc.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            }
        }

        int pListe=Flotte.nPlateauToPListe(lRef, nPlateau);           //On trouve la position du navire dans la liste à l'aide de son numéro plateau et sa lettre de reférence 
        if (flotte.get(pListe).etat==true){           //On vérifie que le bateau n'est pas coulé 
            sc.close();
            return flotte.get(pListe).tir();            //On appelle la methode tir coresspondant au navire selectionné
        }
        else {
            System.out.println(ROUGE + "Erreur!"+RESET +"\nCe navire à déjà été coulé et ne peut plus effectuer de tire");           //Sinon, on affiche un message d'erreur
            sc.close();
            return 2;           //On relance le tour du joueur
        }
    }
}

