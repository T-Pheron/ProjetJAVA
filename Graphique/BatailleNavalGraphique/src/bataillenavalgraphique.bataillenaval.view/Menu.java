package bataillenavalgraphique.bataillenaval.view;



import bataillenavalgraphique.bataillenaval.controller.JeuNGraphique;
import bataillenavalgraphique.bataillenaval.controller.SauvegardeNGraphique;
import java.util.*;
import java.util.concurrent.TimeUnit;


import java.io.*;


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
    private Scanner scMenu = new Scanner(System.in);
    private Scanner scMenuLine = new Scanner(System.in);
     
    
    
    //**************************************************************************
    /**
     * Menu de lancement du programme. 
     * Permet le lancement du jeu, le chargement d'une 
     * partie sauvegardée, le lancement de l'aide, quitter le jeu.
     * @throws java.lang.InterruptedException
     * @throws ClassNotFoundException
    */
    public void menuPrincipal() throws InterruptedException, ClassNotFoundException{
        int choix=0;              //Variable qui permet de stocker le choix du joueur
        JeuNGraphique jeu = new JeuNGraphique();                //On initialise une variable de type Jeu 

        
        System.out.println(GRIS_AR +NOIR+ "                  MENU PRINCIPAL                "+RESET+ RESET_AR);           //On afficher le titre du menu
        System.out.println("1. Jouer une partie \n"
                +          "2. Charger une partie\n"
                +          "3. Aide \n"
                +          "4. Quitter");           //On affiche les choix possibles
        
        System.out.println("Veuillez saisir votre choix :");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>4)){           //On blinde en vérifiant que la saisie fait partie des choix possibles
            System.out.println(ROUGE +"Erreur! "+ RESET+ "Veuillez saisir à nouveau votre choix :");           //Sinon, on affiche un message d'erreur et demande à nouveau la saisie
            try{
                choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        
        switch (choix){
            case 1:            //Si le choix est 1
                System.out.println(); 
                System.out.println("Bienvenue dans le jeu de la "+ CYAN +"Bataille Navale"+ RESET+"\n");           //On affiche un message de bienvenue
                jeu.lancementJeu();             //On appel la méthode qui permet de lancer une nouvelle partie
                
                break;
            case 2: 
                menuChargement();
                break;
            case 3: menuAide();         //On va dans le menu aide 
                    menuPrincipal();
                    break;
            case 4: break;
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
     * @throws ClassNotFoundException
     */
    public int menuJoueur() throws ClassNotFoundException{
        int choix=0;           //Variable qui permet de stocker le choix du joueur
        System.out.println();
        System.out.println(GRIS_AR +NOIR+ "               MENU JOUEUR              "+RESET+ RESET_AR);           //On afficher le titre du menu
        System.out.println("Que voulez vous faire ?");
        System.out.println("1. Tirer ! "
                +        "\n2. Deplacer le navire !"
                +        "\n3. Quitter");           //On affiche les choix possibles
        
        System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>3)){               //On blinde en vérifiant que la saisie fait partie des choix
            System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
            try{
                choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        switch (choix){
            case 1: return 1;                //Si le choix est 1 alors on retourne 1 (Cela signifie que le joueur souhaite effectuer un tir)
            case 2: return 5;               //Si le choix est 2 alors on retourne 2 (Cela signifie que le joueur souhaite déplacer son navire)
            case 3: 
                int choix2=0;
                System.out.println("Voulez vous sauvegarder la partie ?");
                System.out.println("1.OUI \n2.NON");
                System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
                try{
                    choix2 = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
                while ((choix2<1)||(choix2>2)){               //On blinde en vérifiant que la saisie fait partie des choix
                    System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                    try{
                        choix2 = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                    }
                    catch(InputMismatchException e){            //Si ce n'est pas un entier
                        System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                        scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                    }
                }

                switch(choix2){
                    case 1:
                        return menuSauvegarde();

                    case 2: 
                        int choix3=0;
                        System.out.println("Etes vous sûr de vouloir quitter sans sauvegarder ?");
                        System.out.println("1.OUI \n2.NON");
                        System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
                        try{
                            choix3 = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                        }
                        catch(InputMismatchException e){            //Si ce n'est pas un entier
                            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                        }
                        while ((choix3<1)||(choix3>2)){               //On blinde en vérifiant que la saisie fait partie des choix
                            System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                            try{
                                choix3 = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                            }
                            catch(InputMismatchException e){            //Si ce n'est pas un entier
                                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                            }
                        }

                        switch(choix3){
                            case 1: return 4;
                            case 2: return 2;
                        }
                }

                    
            
            default: System.out.println("Erreur_menuJoueur!"); return 0;           //En cas d'erreur, on affiche un message
        }
    }
    
    
    //**************************************************************************
    /**
     * Menu qui permet de sauvegarder une partie de jeu.
     * 
     * @return 
     * @throws ClassNotFoundException
     */
    public int menuSauvegarde() throws ClassNotFoundException{
        int choix=0;           //Variable qui permet de stocker le choix du joueur
        boolean selectionEmplacement=true;
        String saisie;

        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [3];
        for (int i=0; i<3; i++){
            String nomSauvegarde="A";
            if (i==0) nomSauvegarde="saveFiles/save1";
            if (i==1) nomSauvegarde="saveFiles/save2";
            if (i==2) nomSauvegarde="saveFiles/save3";

            try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {
                    nomPartie[i]= (String) in.readObject();
            }
            catch(IOException e){
                System.out.println("Le fichier save1 n'a pas pu être ouvert");
            }
            if (nomPartie[i]==null ) nomPartie[i]="[VIDE]";
        }
        System.out.println(GRIS_AR +NOIR+ "                    MENU SAUVEGARDE                "+RESET+ RESET_AR);           //On afficher le titre du menu

        /*Sélection de l'emplacement par le joueur*****************************/
        do {
                        System.out.println("A quel emplacement voulez vous sauvegarder votre partie ?" 
                +               "\n1. " + nomPartie[0]
                +               "\n2. " + nomPartie[1]
                +               "\n3. " + nomPartie[2]
                +               "\n\n4. Retour");           //On affiche les choix possibles
            System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
            
            try{
                choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
            
            while ((choix<1)||(choix>4)){           //On blinde en vérifiant que la saisie fait partie des choix
                System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                try{
                    choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            }

            selectionEmplacement = false ;

            if (choix<4 && !nomPartie[choix-1].equals("[VIDE]")){
                System.out.println("Cette emplacement contient déjà la sauvegarde "+nomPartie[choix-1]+".");
                System.out.println("Etes vous sûr de vouloir écrasé cette sauvegarde ?");
                System.out.println("1. OUI \n2. NON");
                int choix2=0;
                try{
                    choix2 = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
                while ((choix2<1)||(choix2>2)){               //On blinde en vérifiant que la saisie fait partie des choix
                    System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                    try{
                        choix2 = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                    }
                    catch(InputMismatchException e){            //Si ce n'est pas un entier
                        System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                        scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                    }
                }

                switch(choix2){
                    case 1: 
                        selectionEmplacement = false;
                        break;
                    case 2:
                        selectionEmplacement = true;
                        break;
                }
            }

            if (choix<4 && selectionEmplacement==false){
                System.out.println("\nVeuillez entrez le nom de la partie :");
                saisie = scMenuLine.nextLine();
                nomPartie[choix-1]=saisie;
            }
        } while (selectionEmplacement == true);
        
        SauvegardeNGraphique sauvegarde = new SauvegardeNGraphique();

        switch (choix){
            case 1:  return sauvegarde.savePartie(1, nomPartie[0]);
                        
            case 2:  return sauvegarde.savePartie(2, nomPartie[1]);

            case 3:  return sauvegarde.savePartie(3, nomPartie[2]);

            case 4:  return 1;

            default : System.out.println(ROUGE +"Erreur_menuSauvegarde! "+RESET);         //Message renvoyé en cas de probleme majeur
                break;
        }

        return 2;
    }
    
    //**************************************************************************
    /**
     * Menu qui permet de charger une partie de jeu.
     * @return 
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * 
     */
    public int menuChargement() throws ClassNotFoundException, InterruptedException{
        int choix=0;           //Variable qui permet de stocker le choix du joueur
        boolean selectionEmplacement;
        
        /*Récupération des informations de sauvegarde**************************/
        String [] nomPartie = new String [3];
        for (int i=0; i<3; i++){
            String nomSauvegarde="A";
            if (i==0) nomSauvegarde="saveFiles/save1";
            if (i==1) nomSauvegarde="saveFiles/save2";
            if (i==2) nomSauvegarde="saveFiles/save3";

            try (FileInputStream fichier = new FileInputStream(nomSauvegarde); ObjectInputStream in = new ObjectInputStream(fichier)) {
                    nomPartie[i]= (String) in.readObject();
            }
            catch(IOException e){
                System.out.println("Le fichier save n'a pas pu être ouvert");
            }
            if (nomPartie[i]==null) nomPartie[i]="[VIDE]";
        }
        
        System.out.println(GRIS_AR +NOIR+ "               MENU CHARGEMENT              "+RESET+ RESET_AR);           //On afficher le titre du menu

        do{
                        System.out.println("A quel emplacement voulez vous sauvegarder votre partie ?" 
                    +               "\n1. " + nomPartie[0]
                    +               "\n2. " + nomPartie[1]
                    +               "\n3. " + nomPartie[2]
                    +               "\n4. Retour");           //On affiche les choix possibles
            System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix

            try{
                choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
            
            while ((choix<1)||(choix>4)){           //On blinde en vérifiant que la saisie fait partie des choix
                System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                try{
                    choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            }
            
            selectionEmplacement = false ;

            if (choix<4 && nomPartie[choix-1].equals("[VIDE]")){
                System.out.println(ROUGE+"Erreur !"+RESET +" Cette emplacement ne contient pas de sauvegarde!");
                System.out.println("Veuillez sélectionnez un autre emplacement ou quitter le menu de chargement\n");
                selectionEmplacement =true;
            }
        }while(selectionEmplacement==true);


        SauvegardeNGraphique sauvegarde = new SauvegardeNGraphique();

        switch (choix){
            case 1: 
                sauvegarde.chargementPartie(1);
                break;     
            case 2:
                sauvegarde.chargementPartie(2); 
                break;
            case 3:  
                sauvegarde.chargementPartie(3);
                break;

            case 4:  menuPrincipal();
                break;

            default : System.out.println(ROUGE +"Erreur_menuChargement! "+RESET);         //Message renvoyé en cas de probleme majeur
                break;
        }
        return 0;
    }
    
    
    
    //**************************************************************************
    /**
     * Menu qui permet de naviguer dans l'aide du jeu.
     * Il y a plusieur catégorie dans laide. Ce menu permet de séparer les informations
     * et de ne pas toutes les affichées en même temps.
     * @return 
     */
    public int menuAide(){
        int choix=0;           //Variable qui permet de stocker le choix du joueur
        System.out.println(GRIS_AR +NOIR+ "               MENU AIDE              "+RESET+ RESET_AR);           //On afficher le titre du menu
        System.out.println("1. Pour débuter \n2. La phase de tir \n3. Flotte et fonctionnalité \n4. Légende des symboles  \n5. Pour gagner et quelques astuces \n6. Retour");           //On affiche les choix possibles
        System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>6)){           //On blinde en vérifiant que la saisie fait partie des choix
            System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
            try{
                choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        
        switch (choix){
            case 1:   try(InputStream iS = new FileInputStream("dossier_aide/fichierDebut.txt");            //On lui demande d'aller dans le fichier fichierDebut qui se trouve dans le dossier dossier_aide
            InputStreamReader iSR = new InputStreamReader(iS);          //On déclare une variable de type InputStreamReader
            BufferedReader bR = new BufferedReader (iSR)){          //On déclare une variable de type BufferedReader
                String line;            //On initialise une chaine de caractere
                while((line = bR.readLine())!=null){            //Tant que la ligne n'est pas vide
                    System.out.println(line);           //On affiche la ligne
                }   
            }catch(IOException e){          //Si il n'arrive pas a ouvrir le fichier
                    System.out.println(ROUGE +"Erreur_menuAide! "+RESET+"Impossible d'ouvrir le fichier");            //On affiche un message d'erreur
            }
            break;
                        
            case 2:     try(InputStream iS = new FileInputStream("dossier_aide/fichierFaseTir.txt");            //On lui demande d'aller dans le fichier fichierFaseTir qui se trouve dans le dossier dossier_aide
            InputStreamReader iSR = new InputStreamReader(iS);          //On déclare une variable de type InputStreamReader
            BufferedReader bR = new BufferedReader (iSR)){          //On déclare une variable de type BufferedReader
                String line;            //On initialise une chaine de caractere
                while((line = bR.readLine())!=null){            //Tant que la ligne n'est pas vide
                    System.out.println(line);           //On affiche la ligne
                }   
            }catch(IOException e){          //Si il n'arrive pas a ouvrir le fichier
                System.out.println(ROUGE +"Erreur_menuAide! "+RESET+"Impossible d'ouvrir le fichier");           //On affiche un message d'erreur
            }
            break;

            case 3:     try(InputStream iS = new FileInputStream("dossier_aide/fichierNavFct.txt");         //On lui demande d'aller dans le fichier fichierNavFct qui se trouve dans le dossier dossier_aide
            InputStreamReader iSR = new InputStreamReader(iS);          //On déclare une variable de type InputStreamReader
            BufferedReader bR = new BufferedReader (iSR)){          //On déclare une variable de type BufferedReader
                String line;            //On initialise une chaine de caractere
                while((line = bR.readLine())!=null){            //Tant que la ligne n'est pas vide
                    System.out.println(line);           //On affiche la ligne
                }   
            }catch(IOException e){          //Si il n'arrive pas a ouvrir le fichier
                System.out.println(ROUGE +"Erreur_menuAide! "+RESET+"Impossible d'ouvrir le fichier");           //On affiche un message d'erreur
            }
            break;

           case 4:   try(InputStream iS = new FileInputStream("dossier_aide/fichierSyblPlato.txt");         //On lui demande d'aller dans le fichier fichierSyblPlato qui se trouve dans le dossier dossier_aide
            InputStreamReader iSR = new InputStreamReader(iS);          //On déclare une variable de type InputStreamReader
            BufferedReader bR = new BufferedReader (iSR)){          //On déclare une variable de type BufferedReader
                String line;            //On initialise une chaine de caractere
                while((line = bR.readLine())!=null){            //Tant que la ligne n'est pas vide
                    System.out.println(line);           //On affiche la ligne
                }   
            }catch(IOException e){          //Si il n'arrive pas a ouvrir le fichier
                System.out.println(ROUGE +"Erreur_menuAide! "+RESET+"Impossible d'ouvrir le fichier");           //On affiche un message d'erreur
            }
           break;
                        
            case 5:     try(InputStream iS = new FileInputStream("dossier_aide/fichierGagne.txt");          //On lui demande d'aller dans le fichier fichierGagne qui se trouve dans le dossier dossier_aide
            InputStreamReader iSR = new InputStreamReader(iS);          //On déclare une variable de type InputStreamReader
            BufferedReader bR = new BufferedReader (iSR)){          //On déclare une variable de type BufferedReader
                String line;            //On initialise une chaine de caractere
                while((line = bR.readLine())!=null){            //Tant que la ligne n'est pas vide
                    System.out.println(line);           //On affiche la ligne
                }   
            }catch(IOException e){          //Si il n'arrive pas a ouvrir le fichier
                System.out.println(ROUGE +"Erreur_menuAide! "+RESET+"Impossible d'ouvrir le fichier");           //On affiche un message d'erreur
            }
            break;

            case 6:   return 1;
            default : System.out.println(ROUGE +"Erreur_menuAide! "+RESET);         //Message renvoyé en cas de probleme majeur
                break;
        }


        
        /*Sortie du MenuAide*********************************************/
        choix=0;           //Variable qui permet de stocker le choix du joueur
        System.out.println("\nVoulez vous d'autre rubrique du menu aide ?\n1. Oui \n2. Non");           //On affiche les choix possibles
        
        System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>2)){           //On blinde en vérifiant que la saisie fait partie des choix
            System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
            try{
                choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        if(choix==1) menuAide();            //On retourne dans le menu aide
            return 1;           //On retourne dans le menu principal
    }
    
    


    
    //**************************************************************************
    /**
     * Menu pour le choix du navire qui va tirer.
     * Ce menu est utilisé pour savoir quel navire la joueur souhaite utiliser pour
     * effectuer son tire..
     * Il retourne les références du navire rentré.
     * @return Les instrcutions pour la boucle du jeu
     * @throws java.lang.InterruptedException
     */
    public Object[] menuTirer () throws InterruptedException{
        
        System.out.println(GRIS_AR +NOIR+ "               MENU TIRE              "+RESET+ RESET_AR);           //On afficher le titre du menu
        char lRef ='A';
        int nPlateau =0;
        int maxNavire=0;           //Variable qui permet de stocker le maximun de navire d'une catégorie de navire que contient la flotte

        System.out.println("Veuillez entrer la lettre du navire avec lequel vous voulez tirer :");           //On demande à l'utilisateur de saisir la lettre du navire
        try{
            lRef= scMenu.next().charAt(0);          //On stock la saisie de l'utilisateur
            lRef=JeuNGraphique.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule
        }
        catch(InputMismatchException e){            //Si ce n'est pas un caractère
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }   
        lRef=JeuNGraphique.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule

        while (lRef!='U'&&lRef!='C'&&lRef!='D'&&lRef!='S'){           //On blinde, en vérifiant que sa saisie fait partie des choix possible
            System.out.println(ROUGE + "Erreur !"+RESET);           //Sinon on affiche un message d'erreur
            System.out.println("Veuillez entrer la lettre du navire avec lequel vous voulez tirer :");           //On lui demande de ressaisir
            try{
                lRef= scMenu.next().charAt(0);          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un caractère
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                lRef='A';
            }
            lRef=JeuNGraphique.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule
        }

        if (lRef=='U') nPlateau=1;           //Si le choix du navire est cuirasse, le choix du numéro du navire n'est pas nécésaire
        else {
            System.out.println("Veuillez entrer le numero du navire avec lequel vous voulez tirer :");           //Sinon, on demande à l'utilisateur de rentrer le numéro affiché sur le plateau du navire qu'il souhaite utiliser pour tirer
            try{
                nPlateau = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
            if (lRef=='C') maxNavire=2;            //On affecte à la variable maxNavire le numéro maximal de navire de ce type que contient la liste en fonction de la lettre de référence du navire
            if (lRef=='D') maxNavire=3;
            if (lRef=='S') maxNavire=4;


            while (nPlateau<1||nPlateau>maxNavire){           //On blinde, en vérifiant que la saisie fait partie des choix
                System.out.println(ROUGE + "Erreur! "+RESET +"Ce numéro ne fait pas parti des choix.");           //Sinon, on affiche un message d'erreur
                System.out.println("Veuillez entrer le numero du navire avec lequel vous voulez tirer : :");           //Et on demande la ressaisie
                try{
                    nPlateau = scMenu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            }
        }
        Object[] referencesNavire = new Object[2];
        referencesNavire[0]=lRef;
        referencesNavire[1]=nPlateau;
        return referencesNavire;
    }




    public int choixNiveauIA() throws InterruptedException{
        int niveauIA=0;

        System.out.println("Veuillez choisir le niveau de l'ordinateur que vous souhaitez :"
                        +"\n1.Facile"
                        +"\n2.Moyen"
                        +"\n3.Difficile");           //Sinon, on demande à l'utilisateur de rentrer le numéro affiché sur le plateau du navire qu'il souhaite utiliser pour tirer
        
        System.out.println("Saisisez votre choix :");
        
        try{
            niveauIA = scMenu.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }


        while (niveauIA<1||niveauIA>3){           //On blinde, en vérifiant que la saisie fait partie des choix
            System.out.println(ROUGE + "Erreur! "+RESET +"Ce numéro ne fait pas parti des choix.");           //Sinon, on affiche un message d'erreur
            System.out.println("Veuillez entrer le niveau de l'ordinateur que vous souhaitez :");           //Et on demande la ressaisie
            try{
                niveauIA = scMenu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
        }
        
        System.out.println();

        switch(niveauIA){
            case 1:
                System.out.println("Vous avez choisie le niveau Facile.");TimeUnit.SECONDS.sleep(2);            //On affiche un message annonçant le niveau choisi
                System.out.println("Tu as peur ?");TimeUnit.SECONDS.sleep(1);
                System.out.println("On peut commencer. Bonne chance !");TimeUnit.SECONDS.sleep(3);
                break;
            case 2:
                System.out.println("Vous avez choisie le niveau Moyen.");TimeUnit.SECONDS.sleep(2);
                System.out.println("Ok ok");TimeUnit.SECONDS.sleep(1);
                System.out.println("On peut commencer. Bonne chance !");TimeUnit.SECONDS.sleep(3);
                break;
            case 3:
                System.out.println("Vous avez choisie le niveau Difficile.");TimeUnit.SECONDS.sleep(2);
                System.out.println("Bienn! On est joueur");TimeUnit.SECONDS.sleep(1);
                System.out.println("On peut commencer. Bonne chance !");TimeUnit.SECONDS.sleep(3);
                break;
            default:
        }
        
        return niveauIA;            //On retourne le niveau de l'IA

    }
}

