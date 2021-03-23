package bataillenaval.view;


import bataillenaval.controller.Jeu;
import java.util.*;

import javax.swing.table.DefaultTableCellRenderer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


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
     
    
    
    //**************************************************************************
    /**
     * Menu de lancement du programme. 
     * Permet le lancement du jeu, le chargement d'une 
     * partie sauvegardée, le lancement de l'aide, quitter le jeu.
     * @throws java.lang.InterruptedException
    */
    public void menuPrincipal() throws InterruptedException{
        int choix=0;              //Variable qui permet de stocker le choix du joueur
        Jeu jeu = new Jeu();                //On initialise une variable de type Jeu 

        
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
                System.out.println(""); 
                System.out.println("Bienvenue dans le jeu de la "+ CYAN +"Bataille Navale"+ RESET);           //On affiche un message de bienvenue
                System.out.println("Voici vos grilles au début de la partie");
                jeu.lancementJeu();             //On appel la méthode qui permet de lancer une nouvelle partie
                
                break;
            case 2: break;
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
     */
    public int menuJoueur(){
        int choix=0;           //Variable qui permet de stocker le choix du joueur
        System.out.println("");
        System.out.println(GRIS_AR +NOIR+ "               MENU JOUEUR              "+RESET+ RESET_AR);           //On afficher le titre du menu
        System.out.println("Que voulez vous faire ?");
        System.out.println("1. Tirer ! "
                +        "\n2. Deplacer le navire !");           //On affiche les choix possibles
        
        System.out.println("Veuillez saisir votre choix");           //On demande à l'utilisateur de saisir son choix
        try{
            choix = scMenu.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }
        
        while ((choix<1)||(choix>2)){               //On blinde en vérifiant que la saisie fait partie des choix
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
            case 2: return 2;               //Si le choix est 2 alors on retounr 2 (Cela signifie que le joueur souhaite déplacer son navire)
            
            default: System.out.println("Erreur_menuJoueur!"); return 0;           //En cas d'erreur, on affiche un message
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
        int maxNavire=0;           //Variable qui permet de stocker le maximun de bateau d'une catégorie de navire que contient la flotte

        System.out.println("Veuillez entrer la lettre du navire avec lequel vous voulez tirer :");           //On demande à l'utilisateur de saisir la lettre du navire
        try{
            lRef= scMenu.next().charAt(0);          //On stock la saisie de l'utilisateur
            lRef=Jeu.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule
        }
        catch(InputMismatchException e){            //Si ce n'est pas un caractère
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
            scMenu.next();            //On met à la poubelle la saisie de l'utilisateur
        }   
        lRef=Jeu.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule

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
            lRef=Jeu.convertirMinuscules(lRef);           //On convertir sa saisie en majuscule
        }

        if (lRef=='U') nPlateau=1;           //Si le choix du bateaue est cuirasse, le choix du numéro du bateau n'est pas nécésaire
        else {
            System.out.println("Veuillez entrer le numero du navire avec lequel vous voulez tirer :");           //Sinon, on demande à l'utilisateur de rentrer le numéro affiché sur le plateau du bateau qu'il souhaite utiliser pour tirer
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
}

