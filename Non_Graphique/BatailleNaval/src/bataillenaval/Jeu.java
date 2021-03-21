package bataillenaval;

import java.util.*;
import java.util.concurrent.TimeUnit;




public class Jeu {
    
    public static Plateau plateauDeJeu = new Plateau();             //Variable qui stocke l'ensemble du plateau de jeu 
    public static List <Flotte> flotteJoueur0 ;               //List qui stocke l'ensemble de la flotte du joueur 0
    public static List <Flotte> flotteJoueur1 ;               //List qui stocke l'ensemble de la flotte du joueur 1
    
    //**************************************************************************
    /**
     * Constructeur du jeu.
     * Permet la création d'un type jeu. Ce type permet de lancer une partie.
     */
    public Jeu(){
        
    }
    
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs du texte*/
    /*Elles doivent être placées devant le texte qui doit être mis en couleur avec un +*/
    
    public final String RESET = "\u001B[0m";               //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public final String NOIR = "\u001B[30m";
    public final String ROUGE = "\u001B[31m";
    public final String VERT = "\u001B[32m";
    public final String JAUNE = "\u001B[33m";
    public final String BLEU = "\u001B[34m";
    public final String VOILET = "\u001B[35m";
    public final String CYAN = "\u001B[36m";
    public final String BLANC = "\u001B[37m";
    
    public final String B_NOIR = "\u001B[30;1m";            
    public final String B_ROUGE = "\u001B[31;1m";
    public final String B_VERT = "\u001B[32;1m";
    public final String B_JAUNE = "\u001B[33;1m";
    public final String B_BLEU = "\u001B[34;1m";
    public final String B_VOILET = "\u001B[35;1m";
    public final String B_CYAN = "\u001B[36;1m";
    public final String B_BLANC = "\u001B[37;1m";
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs de l'arriere plan/
    /*Elles doivent être placées devant le texte qui doit avoir une arrière plan en couleur avec un +*/
    
    public final String RESETAR = "\u001B[40m";             //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public final String ROUGEAR = "\u001B[41m";
    public final String VERTAR = "\u001B[42m";
    public final String JAUNEAR = "\u001B[43m";
    public final String BLEUAR = "\u001B[44m";
    public final String VOILETAR = "\u001B[45m";
    public final String CYANAR = "\u001B[46m";
    public final String GRISAR = "\u001B[47m";
    private Scanner scJeu = new Scanner(System.in);
    private Scanner scJeuLine = new Scanner(System.in);
    
    
     
    //**************************************************************************
    /**
     * Méthode qui permet le lancement d'une partie.
     * La méthode créé de nouvelle flotte pour chaque joueur ainsi que le plateau de jeu.
     * Cette méthode est celle qui possède la boucle de jeu qui permet de faire les différents tour de chaque joueur.
     * @throws InterruptedException 
     */
    public void lancementJeu() throws InterruptedException{
        //Initialisation des flottes************************
        flotteJoueur0 = createFlotte();            //On créé une flotte pour le joueur 0
        flotteJoueur1 = createFlotte();            //On créé une flotte pour le joueur 1
                    
        placementFlotte(flotteJoueur0, 0);            //On place aléatoirement la flotte du joueur 0 sur sa grille de navire
        placementFlotte(flotteJoueur1, 1);            //On place aléatoirement la flotte du joueur 1 sur sa grille de navire
        
        /*Initialisation des variables*****************************************/
        Menu menu = new Menu();             //On initialise une variable pour lancer les différents menus
        IA ia = new IA();           //On initialise une varaible pour lancer l'IA
        boolean tourSuivant = true;             //Variable qui permet de savoir si on passe au tour suivant
        int numeroJoueur=0;                 //Variable qui permet de connaître à quelle joueur c'est la tour
        
        /*Boucle de jeu********************************************************/
        while (tourSuivant==true){            //On vérifie qu'on doit passer au tour suivant
            
            int retourMenu = 0;
            
            if (numeroJoueur==0){            //Si le joueur est le joueur humain on affiche sa grille et son
                System.out.println();
                plateauDeJeu.afficher(numeroJoueur, 1);            //On affiche la grille des tirs du joueur
                System.out.println();
                plateauDeJeu.afficher(numeroJoueur, 0);            //On affiche la grille des navires du joueur
            
                retourMenu = menu.menuJoueur();            //On lance le menu joueur et stocke ce qu'il retourne
            
                switch (retourMenu) {
                    case 1:            //Si le retour du menu est 1
                        if (numeroJoueur==0) retourMenu = menu.menuTirer (flotteJoueur0,numeroJoueur);            //On lance le menu pour tirer
                        break;
                    case 2:
                        if (numeroJoueur==0) retourMenu = bougerNavire (flotteJoueur0,numeroJoueur);            //On appel la méthode qui permet de bouger un navire
                        break;
                    default:
                        System.out.println(ROUGE + "Erreur!"+RESET+ "Problème d'appelle du menuJoueur");            //En cas d'erreur on affiche un message d'erreur
                        retourMenu=2;            //On relance le tour du joueur
                        break;
                }
            }
            
            if(numeroJoueur==1){
                plateauDeJeu.afficher(numeroJoueur, 0);         //TEST
                System.out.println();
                plateauDeJeu.afficher(numeroJoueur, 1);
                
                ia.jouer(1);            //On lance la méthode qui permet à l'IA de jouer
            }
            
            if (retourMenu==2) {            //Si le menu retourne 2 à la fin de la boucle
                System.out.println("On relance votre tour");            //On affiche le message de relance de tour
                System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");TimeUnit.SECONDS.sleep(1);
                numeroJoueur--;             //On retire 1 au numéro du joueur pour recommencer le tour
            }
            if (retourMenu==3) numeroJoueur--;              //Si le menu retourne 3 à la fin de la boucle, ça signifie qu'on doit relancer le tour sans message
            if (retourMenu==4) tourSuivant=false;               //Si le menu retourne 4 à la fin de la boucle; ça signifie qu'on doit quitter le jeu
            if (victoire()==true) tourSuivant=false;                //On test à chaque tour la victoire d'un des 2 joueurs
            if (numeroJoueur == 1) numeroJoueur=0;              //Si le numéro du joueur est 1 on le remet à 0
            else numeroJoueur ++;            //Sinon on lui rajoute 1
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode création d'une nouvelle flotte de navire.
     * La méthode créé une liste contenant tous les navires présent dans une flotte.
     * @return La liste remplie avec tous les navires d'une flotte
     */
    public List<Flotte> createFlotte(){
        
        List <Flotte> flotte = new ArrayList<Flotte>();             //On créé une liste qui contient des object de type flotte
        
        flotte.add(new Cuirasse());             //On ajoute un cuirasse à la liste
        
        flotte.add(new Croiseur());            //On ajoute 2 croisseur à la liste
        flotte.add(new Croiseur());
        
        for (int i=3; i<6; i++) flotte.add(new Destroyer());                //On ajoute 3 destroyer à la liste
        
        for (int i=6; i<10; i++) flotte.add(new SousMarin());               //On ajoute 4 sous-marin à la liste
        
        return flotte;            //On retourne la flotte
    }

    
    //**************************************************************************
    /**
     * Méthode de placement aléatoire d'une flotte de navire.
     * Cette méthode permet de placer aléatoirement une flotte sur la grille de jeu
     * @param flotte La flotte du joueur
     * @param numeroJoueur La numéro du joueur
     */
    public void placementFlotte(List<Flotte> flotte, int numeroJoueur){
        
        for (int pListe=0; pListe<10; pListe++){            //On fait une boucle pour parcourir toute la liste contenant la flotte
            int x, y, direction;            //Variable qui permet de stocke les informations générées aléatoirement
            
            x= (int) (Math.random()*15);            //On stocke une valeur aléatoire comprise entre 0 et 14 dans x
            y= (int) (Math.random()*15);            //On stocke une valeur aléatoire comprise entre 0 et 14 dans y
            direction= (int) (Math.random()*2);            //On stocke une valeur aléatoire 0 ou 1 dans direction (0: Horizontal, 1: Vertical)
            
            if (plateauDeJeu.verifEmplacementVide(numeroJoueur, x, y, direction, flotte.get(pListe).taille, flotte.get(pListe).nRef, flotte.get(pListe).lRef)==true){            //Si l'emplacement est possible
                placementNavire(flotte, x, y, pListe, numeroJoueur, direction);            //On place le navire sur la grille du joueur
            }
            else{            //Sinon
                pListe--;            //On refait le tour
            }
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de placer le navire sur la grille du joueur.
     * Elle permet de rentrer les informations dans le plateau de jeu
     * et dans les coordonnées du navire
     * @param flotte La liste qui contient la flotte du joueur
     * @param x La valeur de l'axe x dans la grille du joueur
     * @param y La valeur de l'axe y dans la grille du joueur
     * @param pListe La position du navire dans la liste flotte
     * @param numeroJoueur
     * @param direction 
     */
    public void placementNavire(List<Flotte> flotte,int x, int y,int pListe, int numeroJoueur, int direction){
        
        int numeroEtage = Plateau.numeroEtage(numeroJoueur,0);              //On déduir le numéro de l'étage dans le plateau de jeu avec le numéro du joueur
        int [][] coordonnees = new int [flotte.get(pListe).taille][3];          //On créé un tableau de coordonnée qui permet de stocker les informations des coordonneés du navire
        char lRef = flotte.get(pListe).lRef;               //On récupère la lettre de référence du navire
        int nPlateau = Flotte.pListeToNPlateau(lRef, pListe);
        
        if (direction ==0){             //Si la direction est horizontale
            for (int j=0; j<flotte.get(pListe).taille ; j++){           //On fait une boucle qui fait la taille du navire pour stocker toutes les informations nécésaires
                plateauDeJeu.modification(x+j, y, numeroEtage, 0, lRef);            //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock la lettre du navire
                plateauDeJeu.modification(x+j, y, numeroEtage, 1, nPlateau);            //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock le numéro plateau du navire
                coordonnees[j][0]=x+j;              //On stocke la valeur x dans le tableau coordonnés
                coordonnees[j][1]=y;                //On stocke la valeur y dans le tableau coordonnés
                coordonnees[j][2]=1;                //On stocke la valeur par défaut 1 qui signifie cette casse du navire n'a pas été touchée
            }
        }
        
        if (direction ==1){         //Si la direction est vertical
            for (int j=0; j<flotte.get(pListe).taille ; j++){               //On fait une boucle qui fait la taille du navire pour stocker toutes les informations nécésaires
                plateauDeJeu.modification(x, y+j, numeroEtage, 0, lRef);                //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock la lettre du navire
                plateauDeJeu.modification(x, y+j, numeroEtage, 1, nPlateau);                //On appel la méthode qui permet de modifier les informations dans le plateau de jeu et on stock le numéro plateau du navire
                coordonnees[j][0]=x;                //On stocke la valeur x dans le tableau coordonnés
                coordonnees[j][1]=y+j;              //On stocke la valeur y dans le tableau coordonnés
                coordonnees[j][2]=1;                //On stocke la valeur par défaut 1 qui signifie cette casse du navire n'a pas été touchée
            } 
        }
        
        flotte.get(pListe).coordonnees=coordonnees;         //On stocke le tableau coordonnées dans le tableau coordonnées du navire
        flotte.get(pListe).direction=direction;             //On stocke la direction du navire dans les informations du navire
    }
    
    
    //**************************************************************************
    
    public int bougerNavire(List<Flotte> flotte,int numeroJoueur) throws InterruptedException{
        
        int nPlateau = 0;           //Variable qui stocke le numéro plateau du navire
        int pListe;         //Variable qui stocke la position du navire dans la liste
        int nMaxNavire=0;            //Variable qui stocke le nombre maximal de navire
        int choix=0;            //Variable qui stocke le choix de l'utilisateur
        String saisie;          //Variable qui stocke la saisie de l'utilisateur
        char lRef='A';              //Varieble qui stocke la lettre de référence du navire
        boolean sortieChoixNavire = false;          //Variable qui permet de savoir si on doit sorti de la boucle de choix du navire
        /*Sélection du navire à bouger*****************************************/
        do{
            System.out.println("Veuillez entrer la lettre du navire que vous voulez bouger :");      //On demande à l'utilisateur de choisir la lettre du navire
            try{
                    lRef= scJeu.next().charAt(0);          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un caractère
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                    scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            lRef=Menu.convertirMinuscules(lRef);            //On convertir la saisie en majuscule
        
            while (lRef!='U'&&lRef!='C'&&lRef!='D'&&lRef!='S'){         //On blinde, en vérifiant que la saisie fait bien parti des choix possibles
                System.out.println(ROUGE +"Erreur!"+RESET +"\nCette lettre ne fait pas parti des choix.");          //Sinon, on affiche un message d'erreur
                System.out.println("Veuillez entrer la lette du navire que vous voulez bouger :");          //On demande à l'utilisateur de resaisir
                try{
                    lRef= scJeu.next().charAt(0);          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un caractère
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                    scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
                lRef=Menu.convertirMinuscules(lRef);            //On convertie la saisie en majuscule
            }
            
            if (lRef=='U') nPlateau=1;          //Si le bateau choisie est un cuirassé, il n'est pas nécésaire de demander le numéro plateau car il existe qu'un seule
            
            else{           //Si c'est pas la cas
                System.out.println("Veuillez entrer le numero du navire que vous voulez bouger :");         //On demande le numéro plateau du navire
                try{
                        nPlateau = scJeu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
                
                if (lRef=='C') nMaxNavire=2;            //On déduit le nombre maximal de navire en fonction de la letrre de référence
                if (lRef=='D') nMaxNavire=3;
                if (lRef=='S') nMaxNavire=4;


                while (nPlateau<1||nPlateau>nMaxNavire){            //On blinde, en vérifiant que le numéro plateau est non nulle et ne dépasse pas le numéro maximal de navire
                    System.out.println(ROUGE + "Erreur!"+RESET+"\nCe numéro ne fait pas parti des choix.");         //Sinon, on affiche un message d'erreur 
                    System.out.println("Veuillez entrer le numero du navire que vous voulez bouger :");             //On demande à l'utilisateur de ressaisir
                    try{
                        nPlateau = scJeu.nextInt();          //On stock la saisie de l'utilisateur
                    }
                    catch(InputMismatchException e){            //Si ce n'est pas un entier
                        System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                        scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                    }
                }
            }
            
            pListe = Flotte.nPlateauToPListe(lRef, nPlateau);           //On déduir la position du navire dans de la liste avec la lettre de référance et le numéro plateau
            
            if (flotte.get(pListe).etat==false){            //On vérifie que le bateau que l'utilisateur souhaite bouger n'est pas déjà coulé
                System.out.println(B_JAUNE + "Echec!" + RESET + "\nCe navire a déjà été coulé!");          //Sinon on affiche un message d'échec de l'opération
                System.out.println("Voulez vous tentez de déplacer un autre navire ?");         //On demande ce que l'utilisateur se qu'il souhaite faire
                System.out.println("1.OUI   2.NON");
                choix = 0;
                try{
                        choix = scJeu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                        System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                        scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
                
                switch(choix){
                    case 1: continue;           //Si oui, on continue ce qui à pour conséquence de recommencer la boucle
                    case 2: return 2;           //Si non; on retourne 2 qui relance le tour de l'utilisateur 
                    
                    default : System.out.println(ROUGE+"Erreur_bougerNavire! "+ RESET + "\nLe choix rentrer n'est pas disponible."); return 2;           //En cas de mauvaise saisie, on affiche un message d'erreur et on relance le tour de l'utilisateur
                }
            }
            else sortieChoixNavire=true;        //Si le navire n'est pas coulé on continue et on autorise la sortie de la boucle de choix
            
            for (int i=0; i<flotte.get(pListe).taille; i++){                //On fait une boucle pour vérifier toutes les coordonnées du bateau
                if (flotte.get(pListe).coordonnees[i][2]==0 && sortieChoixNavire==true){               //On vérifie pour chaque coordonnées qu'elles ont pas déjà été touchées et que pour l'instant on a les autorisations de quitter la boucle de choix du bateau  
                    
                    System.out.println(B_JAUNE + "Echec!"+RESET+ "Ce navire a déjà été touché!");         //Si ce n'est pas le cas, on affiche un message d'échec de l'opération
                    System.out.println("Voulez vous tentez de déplacer un autre navire ?");         //On demande à l'utilisateur ce qu'il souhaite faire
                    System.out.println("1.OUI   2.NON");
                    try{
                        choix = scJeu.nextInt();          //On stock la saisie de l'utilisateur
                    }
                    catch(InputMismatchException e){            //Si ce n'est pas un entier
                        System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                        scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                    }
                
                    switch(choix){
                        case 1: sortieChoixNavire=false;            //Si oui, on met sortieChoixNavire sur false ce qui à pour conséquence de recommencer le choix du navire
                                break;
                        case 2: return 2;           //Si non, on retourne 2 qui a pour conséquence de relancer le tour du joueur
                    
                        default : System.out.println(ROUGE+"Erreur_bougerNavire! "+ RESET + "\nLe choix rentrer n'est pas disponible."); return 2;          //En cas de mauvaise saisie, on affiche un message d'erreur et on relance le tour de l'utilisateur
                    }
                } 
            }    
        }while(sortieChoixNavire==false);//Si toutes les vérifications on été faites et que le navire peut-être bougé on quitte la boucle

        /*Trouver toutes les posibilités de placement du navire****************/
        int  [] possibilite = new int[4];            //Tableau utilisé pour stocker les 4 posibilitées de placement du navire 
        
        if (flotte.get(pListe).direction == 0){         //Si la direction du navire est horizontale
            
            int xCord = flotte.get(pListe).coordonnees[0][0];           //On récupère la valeur du premier x dans les coordonées du navire
            if (xCord==0) possibilite[0]=90;         //Si x est égale à 0, un déplacement vers la gauche n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[0]= xCord - 1;          //Sinon, on stock la coordonnées x de la case de gauche dans le tableau possibilité
            
            xCord = flotte.get(pListe).coordonnees[flotte.get(pListe).taille - 1][0];           //On récupère la valeur du dernier x dans les coordonées du navire
            if (xCord==14) possibilite[1]=90;           //Si x est égale à 14, un déplacement vers la gauche n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[1]= xCord + 1;             //Sinon, on stock la coordonnées x de la case de droite dans le tableau possibilité
            
            
            int yCord = flotte.get(pListe).coordonnees[0][1];           //On récupère la valeur du premier y dans les coordonées du navire
            switch (yCord) {
                case 0:             //Si y est égale à 0 un déplacement vers le haut n'est pas possible
                    possibilite[2]= 90;         //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[3]= yCord +1;           //Et on stock la coordonées y de la ligne en dessous dans le tableau possibilité
                    break;
                case 14:            //Si y est égale à 14 un déplacement vers le bas n'est pas possible
                    possibilite[3]= 90;         //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[2]= yCord -1;           //Et on stock la coordonées y de la ligne au dessus dans le tableau possibilité
                    break;
                default:            //Sinon aucun des cas est vérifié
                    possibilite[3]= yCord +1;           //On stock la coordonées y de la ligne en dessous dans le tableau possibilité
                    possibilite[2]= yCord -1;           //On stock la coordonées y de la ligne au dessus dans le tableau possibilité
                    break;
            }
        }
        
        if (flotte.get(pListe).direction == 1){         //Si la direction du navire est vertical
            
            int yCord = flotte.get(pListe).coordonnees[0][1];           //On récupère la valeur du premier y dans les coordonées du navire
            if (yCord==0) possibilite[2]= 90;           //Si y est égale à 0, un déplacement vers le haut n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[2]= yCord - 1 ;            //Sinon, on stock la coordonnées y de la case au dessus dans le tableau possibilité
            
            yCord = flotte.get(pListe).coordonnees[flotte.get(pListe).taille - 1][1];
            if (yCord ==14) possibilite[3]= 90;         //Si y est égale à 0, un déplacement vers le bas n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[3]= yCord + 1;             //Sinon, on stock la coordonnées y de la case en dessous dans le tableau possibilité
            
            
            int xCord = flotte.get(pListe).coordonnees[0][0];           //On récupère la valeur du premier x dans les coordonées du navire
            switch (xCord) {
                case 0:             //Si x est égale à 0 un déplacement vers la gauche n'est pas possible
                    possibilite[0]= 90;          //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[1]= xCord + 1;          //Et on stock la coordonées x de la colonne à droite dans le tableau possibilité
                    break;
                case 14:            //Si x est égale à 14 un déplacement vers la droite n'est pas possible
                    possibilite[1]= 90;          //On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
                    possibilite[0]= xCord - 1;          //Et on stock la coordonées x de la colonne à gauche dans le tableau possibilité
                    break;
                default:
                    possibilite[1]= xCord + 1;          //On stock la coordonées x de la colonne à droite dans le tableau possibilité
                    possibilite[0]= xCord - 1;          //On stock la coordonées x de la colonne à gauche dans le tableau possibilité
                    break;
            }
        }
        
        /*Vérifier que le navire va pas chevocher un autre navire**************/
        if (flotte.get(pListe).direction==0){               //Si le navire est à l'horizontale
            if (possibilite[0]!= 90){           //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[0], flotte.get(pListe).coordonnees[0][1], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){          //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[0]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[1]!= 90){
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[1]- flotte.get(pListe).taille +1 , flotte.get(pListe).coordonnees[0][1], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){            //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[1]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[2]!= 90){
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[2], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[2]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[3]!= 90){
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[3], 0, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[3]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
        }
        
        if (flotte.get(pListe).direction==1){           //Si le navire est à la vertical
            if (possibilite[0] != 90){          //On vérifie que la possibilité est possible
                if(plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[0], flotte.get(pListe).coordonnees[0][1], 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){            //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[0]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[1] != 90){          //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[1], flotte.get(pListe).coordonnees[0][1], 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[1]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            
            if (possibilite[2] != 90){          //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[2], 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[2]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[3] != 90){          //On vérifie que la possibilité est possible
                if (plateauDeJeu.verifEmplacementVide(numeroJoueur, flotte.get(pListe).coordonnees[0][0], possibilite[3]-  flotte.get(pListe).taille +1 , 1, flotte.get(pListe).taille, pListe, flotte.get(pListe).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[3]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
        }
        
        if (possibilite[0]==90 && possibilite[1]==90 && possibilite[2]==90 && possibilite[3]==90){          //On vérifie qu'il y un moyen de déplacer le navire, pour éviter que le joueur ne trouve pas un moyen de quitter la boucle
            System.out.println("Ce navire de ne peut pas être déplacé. \n");           //Si c'est le cas on affiche un message d'erreur
            return 2;           //On retourne 2, ce qui signifie qu'on doit relancer le tour du joueur
        }
        
        
        System.out.println("Entrer la colonne ou la ligne du nouvelle emplacement du navire");          //On demande à l'utilisateur de saisie le nouvelle emplacement
        saisie = scJeuLine.nextLine();            //On récupère ça saisie qui peut etre de n'importe quelle type
        char saisieChar='Z';                //Variable qui permet de stocker la saisie si elle est un charactère
        int saisieInt=0;                    //Variable qui permet de stocker la saisie si elle est un entier
        
        boolean verif=false;                //Variable qui permet de savoir si la vérification de l'exactitude de la siasie
        boolean verifEntier=false;          //Variable qui permet de savoir si la saisie est un entier
        
        if (saisie.equals("A") || saisie.equals("B") || saisie.equals("C") || saisie.equals("D") || saisie.equals("E")|| saisie.equals("F") || saisie.equals("G") || saisie.equals("H") || saisie.equals("I") || saisie.equals("J") || saisie.equals("K") || saisie.equals("L") || saisie.equals("M") || saisie.equals("N") || saisie.equals("O")) {                //On vérif que la saisie est une majuscule qui fait partie des choix
            verif = true;           //Si c'est le cas on met verif sur true
            saisieChar = saisie.charAt(0);          //Et on stock la saisie dans saisieChar en la convertisant en charactère
        }
        if (saisie.equals("a") || saisie.equals("b") || saisie.equals("c") || saisie.equals("d") || saisie.equals("e")|| saisie.equals("f") || saisie.equals("g") || saisie.equals("h") || saisie.equals("i") || saisie.equals("j") || saisie.equals("k") || saisie.equals("l") || saisie.equals("m") || saisie.equals("n") || saisie.equals("o")) {                //On vérif que la saisie est une minuscule qui fait partie des choix
            verif = true;           //Si c'est le cas on met verif sur true
            saisieChar = saisie.charAt(0);          //On stock la saisie dans saisieChar en la convertisant en charactère
            saisieChar = Menu.convertirMinuscules(saisieChar);          //Et on convertie la saisie en majuscule
        }
        if (saisie.equals("1") || saisie.equals("2") || saisie.equals("3") || saisie.equals("4") || saisie.equals("5")|| saisie.equals("6") || saisie.equals("7") || saisie.equals("8") || saisie.equals("9") || saisie.equals("10") || saisie.equals("11") || saisie.equals("12") || saisie.equals("13") || saisie.equals("14") || saisie.equals("15")){           //On vérif que la saisie est un chiffre qui fait partie des choix
            verif = true;           //Si c'est le cas on met verif sur true
            verifEntier=true;           //On dis que la saisie est un entier
            saisieInt = Integer.parseInt(saisie);           //Et on stocke la saisie dans saisieInt, en la convertisant en entier
        }
        
        while(verif==false){            //On blinde, si la saisie ne fait pas partie des choix possibles
            System.out.println(B_JAUNE + "Echec !"+RESET+"\nLa saisie ne fait pas parti des choix possibles");          //On affiche un message d'échec
            System.out.println("Veuillez resaisir la ligne ou la colonne où vous voulez déplacer le navire");           //On à l'utilisateur de ressaisir
            saisie = scJeuLine.nextLine();         //On stocke la saisie de l'utilisateur
            
            if (saisie.equals("A") || saisie.equals("B") || saisie.equals("C") || saisie.equals("D") || saisie.equals("E")|| saisie.equals("F") || saisie.equals("G") || saisie.equals("H") || saisie.equals("I") || saisie.equals("J") || saisie.equals("K") || saisie.equals("L") || saisie.equals("M") || saisie.equals("N") || saisie.equals("O")) {            //On vérif que la saisie est une majuscule qui fait partie des choix
                verif = true;           //Si c'est le cas on met verif sur true
                saisieChar = saisie.charAt(0);          //Et on stock la saisie dans saisieChar en la convertisant en charactère
            }
            if (saisie.equals("a") || saisie.equals("b") || saisie.equals("c") || saisie.equals("d") || saisie.equals("e")|| saisie.equals("f") || saisie.equals("g") || saisie.equals("h") || saisie.equals("i") || saisie.equals("j") || saisie.equals("k") || saisie.equals("l") || saisie.equals("m") || saisie.equals("n") || saisie.equals("o")) {            //On vérif que la saisie est une minuscule qui fait partie des choix
                verif = true;           //Si c'est le cas on met verif sur true
                saisieChar = saisie.charAt(0);          //On stock la saisie dans saisieChar en la convertisant en charactère
                saisieChar = Menu.convertirMinuscules(saisieChar);
            }
            if (saisie.equals("1") || saisie.equals("2") || saisie.equals("3") || saisie.equals("4") || saisie.equals("5")|| saisie.equals("6") || saisie.equals("7") || saisie.equals("8") || saisie.equals("9") || saisie.equals("10") || saisie.equals("11") || saisie.equals("12") || saisie.equals("13") || saisie.equals("14") || saisie.equals("15")){       //On vérif que la saisie est un chiffre qui fait partie des choix
                verif = true;           //Si c'est le cas on met verif sur true
                verifEntier=true;           //On dis que la saisie est un entier
                saisieInt = Integer.parseInt(saisie);           //Et on stocke la saisie dans saisieInt, en la convertisant en entier
            }
        };
        
        if (verifEntier==true && (saisieInt-1) == flotte.get(pListe).coordonnees[0][1]){            //Si l'utilisateur n'a pas saisie un nouvelle emplacement mais la ligne où se situe le navire
            System.out.println("Veuillez saisir la colonne de la case où vous voulez placer le navire");            //On lui demande de saisir la colonne
            try{
                    saisieChar = scJeu.next().charAt(0);          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un caractère
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                    scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            saisieChar = Menu.convertirMinuscules(saisieChar);          //On convertie cette saisie en majuscule
            
            while(saisieChar<'A'||saisieChar>'O'){          //Si la saisie ne fait pas partie des choix
                System.out.println(ROUGE+"Erreur!"+RESET+ "\nLa saisie ne fait pas partie des choix. Veuillez resaisir :");         //On demande à l'utilisateur de ressaisir
                try{
                    saisieChar = scJeu.next().charAt(0);          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un caractère
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                    scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
                saisieChar = Menu.convertirMinuscules(saisieChar);          //On convertie cette saisie en majuscule
            }
            verifEntier=false;          //On met la vérification d'un entier sur false
        }
        
        else if (verifEntier==false && saisieChar == (char) (flotte.get(pListe).coordonnees[0][0] + 65)){           //Si l'utilisateur n'a pas saisie un nouvelle emplacement mais la colonne où se situe le navire
            System.out.println("Veuillez saisir le numéro de la case où vous voulez placer le navire");
            verifEntier=true;
            try{
                saisieInt = scJeu.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
            }
            while(saisieChar<65||saisieChar>79){
                System.out.println(ROUGE+"Erreur!"+RESET+ "\nLa saisie ne fait pas partie des choix. Veuillez resaisir :");
                try{
                    saisieInt = scJeu.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scJeu.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            }
        }

        
        if (flotte.get(pListe).direction==0){
            if (verifEntier==true){
                saisieInt--;
                if (saisieInt==possibilite[3]){
                    for (int i=0; i<flotte.get(pListe).taille;i++){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0] , flotte.get(pListe).coordonnees[i][1]+ 1, Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][1] ++;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else if (saisieInt==possibilite[2]){
                    for (int i=0; i<flotte.get(pListe).taille;i++){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1] - 1, Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][1] --;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                }
            }
            else {
                
                if (saisieChar == (char) (possibilite[1]+65)){
                    for (int i=flotte.get(pListe).taille - 1; i>=0;i--){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0] + 1, flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][0] ++;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else if (saisieChar ==(char) (possibilite[0])+65){
                    for (int i=0; i<flotte.get(pListe).taille;i++){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0] - 1, flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][0] --;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                }
            }
        }
        
        
        if (flotte.get(pListe).direction==1){
            if (verifEntier==true){
                saisieInt--;
                if (saisieInt==possibilite[3]){
                    for (int i=flotte.get(pListe).taille - 1; i>=0;i--){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1] , flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1]+1, Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][1] ++;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else if (saisieInt==possibilite[2]){
                    for (int i=0; i<flotte.get(pListe).taille;i++){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1]- 1, Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][1] --;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                }
            }
            else {
                
                if (saisieChar == (char) (possibilite[1]+65)){
                    for (int i=0; i<flotte.get(pListe).taille;i++){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0]+1 , flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][0] ++;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else if (saisieChar ==(char) (possibilite[0])+65){
                    for (int i=0; i<flotte.get(pListe).taille;i++){
                        plateauDeJeu.deplacement(flotte.get(pListe).coordonnees[i][0], flotte.get(pListe).coordonnees[i][1], flotte.get(pListe).coordonnees[i][0]-1, flotte.get(pListe).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));
                        flotte.get(pListe).coordonnees[i][0] --;
                    }
                    System.out.println(VERT + "Le déplacement a bien été effectué"+RESET+"\n");
                    plateauDeJeu.afficher(numeroJoueur, 0);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                }
            }
        }
        return 2;   
    }


    public boolean victoire(){
        for (int i=0; i<10; i++){
            boolean etat0 = flotteJoueur0.get(i).etat;
            if (etat0==true) break;
            if (i==9 && etat0!=true){
                System.out.println(ROUGE+"VICTOIRE DE L'ORDI !!\nT'ES NUL PD"+RESET);
                return true;
            }
        }
        for (int i=0; i<10; i++){
            boolean etat1 = flotteJoueur1.get(i).etat;
            if (etat1==true) break;
            if (i==9 && etat1!=true){
                System.out.println(ROUGE+"VICTOIRE JOUEUR !!\n BIEN JOUE CA GASSON"+RESET);
                return true;
            }
        }
        return false;
    }
    
}
