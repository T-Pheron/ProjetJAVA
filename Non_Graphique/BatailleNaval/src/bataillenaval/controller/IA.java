package bataillenaval.controller;

import bataillenaval.model.Flotte;
import bataillenaval.model.Plateau;
import bataillenaval.view.Affichage;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Théric PHERON
 */
public class IA implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = -8187468013304872508L;
    private int nombreDeTir = 0;            //Variable utilisée pour stocker le nombre de tir
    private boolean premierTire = true;         //Variable utilisée pour stocker 
    private  boolean[] saveCoord = new boolean [20];            //Variable utilisée pour stocker
    private int [][] stockSaveCoord = new int[20][3];           //Variable utilisée pour stocker

    private int xTire=0;           //Variable utilisée pour stocker la valeur de l'axe x lors du tir
    private int yTire=0;           //Variable utilisée pour stocker la valeur de l'axe y lors du tir
    private int nPlateau=1;       //Variable utilisée pour stocker le numéro plateau du navire qui tir
    private char lRef='U';          //Variable utilisée pour stocker la lettre de référence du navire qui tir
    private int pListe=0;           //Variable utilisée pour stocker la position 
    private boolean choixCoordonneesTir=true;           //Variable utilisé pour savoir si on peut sortir de la boucle de choix des coordonées
    private int nombreDeTirDestroyer=0;

    public IA (){
        
    }
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs du texte*/
    /*Elles doivent être placées devant le texte qui doit être mis en couleur avec un +*/
    
    public final String RESET = "\u001B[0m";                //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
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
    /*Variables utilisées pour modifier les couleurs de l'arriere plan*/
    /*Elles doivent être placées devant le texte qui doit avoir une arrière plan en couleur avec un +*/
    
    public final String RESET_AR = "\u001B[40m";                //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public final String ROUGE_AR = "\u001B[41m";
    public final String VERT_AR = "\u001B[42m";
    public final String JAUNE_AR = "\u001B[43m";
    public final String BLEU_AR = "\u001B[44m";
    public final String VOILET_AR = "\u001B[45m";
    public final String CYAN_AR = "\u001B[46m";
    public final String GRIS_AR = "\u001B[47m";
    
    public final String B_ROUGE_AR = "\u001B[41;1m";
    public final String B_VERT_AR = "\u001B[42;1m";
    public final String B_JAUNE_AR = "\u001B[43;1m";
    public final String B_BLEU_AR = "\u001B[44;1m";
    public final String B_VOILET_AR = "\u001B[45;1m";
    public final String B_CYAN_AR = "\u001B[46;1m";
    public final String B_GRIS_AR = "\u001B[47;1m";
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de faire jouer l'IA.
     * La méthode affectue le tour de l'IA. D'après le niveau de dificulté choisie par l'utilisateur,
     * elle va mettre en place différent stratégie pour toucher et couler les navires du joueur humain
     * @param niveauIA Le niveau de l'IA choisie
     * @return 1 Si tout c'est bien passé
     * @throws InterruptedException 
     */
    public int jouer (int niveauIA) throws InterruptedException{
        
        System.out.println("\n\n"+GRIS_AR+BLANC+"                    Tour de l'IA                    "+RESET+RESET_AR);         //On affiche que c'est le tour de l'IA

        if (premierTire==true) {            //Si c'est le pemier tir 
            initialiseStockage();           //Toutes les variables sont mis par défaut
            premierTire=false;              //Le premier tir devient faux
        }
        
        switch(niveauIA){
            
            /*Niveau 1 de l'IA*************************************************/
            case 1:
                return niveau1IA();         //On renvoie la méthode du premier niveau de l'IA

            /*Niveau 2 de l'IA*************************************************/
            case 2:
                return niveau2IA();         //On renvoie la méthode du deuxième niveau de l'IA

            /*Niveau 3 de l'IA*************************************************/
            case 3:
                return niveau3IA();         //On renvoie la méthode du troisième niveau de l'IA
        }
        return 1;           //On retourne 1 si tout c'est bien passé
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de remettre à 0 la grille de tir de l'IA.
     * La méthode permet de remettre à zéro les casse qui sont suseptible d'avoir des navires
     * @return Le nombre de case non vide dans la grille de jeu
     */
    public int reinitialiserGrilleTrir(){
        
        int nombreDeCaseRestante = 0;           //Variable qui permet de stocker le nombre de case restante sur la grille de tir après passage de la méthode
        for (int i=0; i<15; i++){           //On créé des boucles pour parcourir toute la grille de tir
            for (int j=0; j<15; j++){
                
                if (Jeu.plateauDeJeu.get(i, j, 3, 0).equals("1")) Jeu.plateauDeJeu.modification(i, j, 3, 0, "0");            //Pour les cases les chiffre 1 (touché sans avoir touché de navire) on les remets à zéro
                if (Jeu.plateauDeJeu.get(i, j, 3, 0).equals("2")) nombreDeCaseRestante++;            //Si on a déjà touché un navire à cette emplacement on ajoute 1 au nombre de case restante
            }
        }
        
        return nombreDeCaseRestante;            //On renvoie le nombre de case restanste
    }

    //**************************************************************************
    public void initialiseStockage(){
        for (int i=0; i<20; i++){
            saveCoord[i]=false;             //On met les états des sauvegardes à false
            stockSaveCoord[i][0]=0;         //On met toutes les zones de stockage à 0
            stockSaveCoord[i][1]=0;
            stockSaveCoord[i][2]=0;
        }
    }
    
    
    
    
    
    
    
    //**************************************************************************
    public int bougerNavireIA() throws InterruptedException{
        
        int pListeBougerNavire;         //Variable qui stocke la position du navire dans la liste
        int numeroJoueur=1;         //Le numéro joueur de l'IA est 1
        
        /*Sélection du navire à bouger*****************************************/
        boolean sortieChoix=true;
        do {
           pListeBougerNavire = (int) (Math.random()*10);          //On prend un nombre aléatoire entre 0 et 9
           sortieChoix=true;
           for (int i=0; i<Jeu.flotteJoueur1.get(pListeBougerNavire).taille; i++){                //On fait une boucle pour vérifier toutes les coordonnées du navire
               if (Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][2]==0 || Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][2]==2){            //On vérifie que le bateau n'est pas touché
                   sortieChoix=false;
               }
           }
       }while (Jeu.flotteJoueur1.get(pListeBougerNavire).etat==false && sortieChoix==true);         //Tant que l'IA n'a pas trouvé un navire qui n'a pas coulé

        /*Trouver toutes les posibilités de placement du navire****************/
        int  [] possibilite = new int[4];            //Tableau utilisé pour stocker les 4 posibilitées de placement du navire 
        
        if (Jeu.flotteJoueur1.get(pListeBougerNavire).direction == 0){         //Si la direction du navire est horizontale
            
            int xCord = Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][0];           //On récupère la valeur du premier x dans les coordonées du navire
            if (xCord==0) possibilite[0]=90;         //Si x est égale à 0, un déplacement vers la gauche n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[0]= xCord - 1;          //Sinon, on stock la coordonnées x de la case de gauche dans le tableau possibilité
            
            xCord = Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[Jeu.flotteJoueur1.get(pListeBougerNavire).taille - 1][0];           //On récupère la valeur du dernier x dans les coordonées du navire
            if (xCord==14) possibilite[1]=90;           //Si x est égale à 14, un déplacement vers la gauche n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[1]= xCord + 1;             //Sinon, on stock la coordonnées x de la case de droite dans le tableau possibilité
            
            
            int yCord = Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][1];           //On récupère la valeur du premier y dans les coordonées du navire
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
        
        if (Jeu.flotteJoueur1.get(pListeBougerNavire).direction == 1){         //Si la direction du navire est vertical
            
            int yCord = Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][1];           //On récupère la valeur du premier y dans les coordonées du navire
            if (yCord==0) possibilite[2]= 90;           //Si y est égale à 0, un déplacement vers le haut n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[2]= yCord - 1 ;            //Sinon, on stock la coordonnées y de la case au dessus dans le tableau possibilité
            
            yCord = Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[Jeu.flotteJoueur1.get(pListeBougerNavire).taille - 1][1];
            if (yCord ==14) possibilite[3]= 90;         //Si y est égale à 0, un déplacement vers le bas n'est pas possible. On met donc cette possibilité à 90 (ce qui siginifie qu'elle n'est pas possible)
            else possibilite[3]= yCord + 1;             //Sinon, on stock la coordonnées y de la case en dessous dans le tableau possibilité
            
            
            int xCord = Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][0];           //On récupère la valeur du premier x dans les coordonées du navire
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
        if (Jeu.flotteJoueur1.get(pListeBougerNavire).direction==0){               //Si le navire est à l'horizontale
            if (possibilite[0]!= 90){           //On vérifie que la possibilité est possible
                if (Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][1], 0, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){          //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[0]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[1]!= 90){
                if (Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, ((possibilite[1] - Jeu.flotteJoueur1.get(pListeBougerNavire).taille) + 1 ), Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][1], 0, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){            //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[1]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[2]!= 90){
                if (Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][0], possibilite[2], 0, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[2]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[3]!= 90){
                if (Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][0], possibilite[3], 0, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[3]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
        }
        
        if (Jeu.flotteJoueur1.get(pListeBougerNavire).direction==1){           //Si le navire est à la vertical
            if (possibilite[0] != 90){          //On vérifie que la possibilité est possible
                if(Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][1], 1, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){            //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[0]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[1] != 90){          //On vérifie que la possibilité est possible
                if (Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, possibilite[1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][1], 1, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[1]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            
            if (possibilite[2] != 90){          //On vérifie que la possibilité est possible
                if (Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][0], possibilite[2], 1, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[2]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
            if (possibilite[3] != 90){          //On vérifie que la possibilité est possible
                if (Jeu.plateauDeJeu.verifEmplacementVide(numeroJoueur, Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[0][0],(( possibilite[3] -  Jeu.flotteJoueur1.get(pListeBougerNavire).taille) + 1) , 1, Jeu.flotteJoueur1.get(pListeBougerNavire).taille, pListeBougerNavire, Jeu.flotteJoueur1.get(pListeBougerNavire).lRef)==false){           //On vérifie que le placement ne chevoche pas un autre navire
                    possibilite[3]=90;          //Si c'est la cas on met la possibilité à 90 (ce qui siginifie qu'elle n'est pas possible
                }
            }
        }
        
        if (possibilite[0]==90 && possibilite[1]==90 && possibilite[2]==90 && possibilite[3]==90){          //On vérifie qu'il y un moyen de déplacer le navire
            System.out.println("\nLe navire que j'ai choisi a une panne moteur.");           //Si c'est le cas on affiche un message d'erreur
            System.out.println("Le temps de la réparation j'effectue une autre action.");TimeUnit.SECONDS.sleep(3);
            return 3;           //On retourne 2, ce qui signifie qu'on doit relancer le tour du joueur
        }
        

        int selectionPossibilite=0;
        do{
            selectionPossibilite= (int) (Math.random()*4);          //On prend un nombre aléatoire entre 0 et 3
        }while(possibilite[selectionPossibilite]==90);          //Tant que l'IA n'a pas trouvé d'emplacement possible
        

        
        if (Jeu.flotteJoueur1.get(pListeBougerNavire).direction==0){            //Si le navire est à l'horizontale
                
            switch (selectionPossibilite) {
                case 3:
                    //Possibilité de descendre
                    for (int i=0; i<Jeu.flotteJoueur1.get(pListeBougerNavire).taille;i++){          //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0] , Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1]+ 1, Plateau.numeroEtage(numeroJoueur, 0));         //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1] ++;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                case 2:
                    //Possibilité de monter
                    for (int i=0; i<Jeu.flotteJoueur1.get(pListeBougerNavire).taille;i++){          //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1] - 1, Plateau.numeroEtage(numeroJoueur, 0));         //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1] --;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                case 1:
                    //Posibilité d'aller à gauche
                    for (int i=Jeu.flotteJoueur1.get(pListeBougerNavire).taille - 1; i>=0;i--){         //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0] + 1, Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));         //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0] ++;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                case 0:
                    //Possibilité d'aller à droite
                    for (int i=0; i<Jeu.flotteJoueur1.get(pListeBougerNavire).taille;i++){          //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0] - 1, Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));         //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0] --;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;       //On retourne 1 si tout c'est bien passé
                default:
                    System.out.println(ROUGE +"Erreur_bougerNavireIA !"+RESET +"\nErreur de choix de déplacement");         //En cas d'erreur on renvoie un message d'erreur
                    return 3;           //Recommence le tour de l'IA sans afficher de message 
            } 
        }
        
        
        if (Jeu.flotteJoueur1.get(pListeBougerNavire).direction==1){            //Si le navire est à la vertical
            switch (selectionPossibilite) {
                case 3:
                    //Possibilité de descendre
                    for (int i=Jeu.flotteJoueur1.get(pListeBougerNavire).taille - 1; i>=0;i--){         //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1] , Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1]+1, Plateau.numeroEtage(numeroJoueur, 0));          //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1] ++;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                case 2:
                    //Possibilité de monter
                    for (int i=0; i<Jeu.flotteJoueur1.get(pListeBougerNavire).taille;i++){          //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1]- 1, Plateau.numeroEtage(numeroJoueur, 0));          //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1] --;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                case 1:
                    //Possibilité d'aller à gauche
                    for (int i=0; i<Jeu.flotteJoueur1.get(pListeBougerNavire).taille;i++){          //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0]+1 , Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));          //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0] ++;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                case 0:
                    //Possibilité d'aller à droite
                    for (int i=0; i<Jeu.flotteJoueur1.get(pListeBougerNavire).taille;i++){          //On parcour toutes les cases du navire
                        Jeu.plateauDeJeu.deplacement(Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0]-1, Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][1], Plateau.numeroEtage(numeroJoueur, 0));           //Il déplace le navire
                        Jeu.flotteJoueur1.get(pListeBougerNavire).coordonnees[i][0] --;         //L'IA corrige les nouvelles coordonnées du navire
                    }
                    System.out.println(VERT + "Manoeuvre réalisée avec succès"+RESET+"\n");         //L'IA affiche un message qui confirme le déplacement
                    Affichage.afficher(numeroJoueur, 0, Jeu.plateauDeJeu);
                    TimeUnit.SECONDS.sleep(5);
                    return 1;
                default:
                    System.out.println(ROUGE +"Erreur_bougerNavireIA !"+RESET +"\nErreur de choix de déplacement");         //En cas d'erreur on renvoie un message d'erreur
                    break;
            }
        }
        return 3;  //Recommence le tour de l'IA sans afficher de message  
    }
 
    


    public int niveau1IA() throws InterruptedException{
        
        System.out.println("Veuillez patienter le temps que j'effectue mon tir");           
        System.out.print("Je choisis les coordonnées, 4 sec!");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");


        /*Génération des coordonées de tir et choix navire*************/
        do{ 
            xTire = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans x
            yTire = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans y

            int alea = (int) (Math.random() * 4);           //On génère un nombre aléatoir pour savoir quel navire qui va tirer
            if (alea==0) lRef='U';          //D'après le nombre aléatoire, on choix un type de navire
            if (alea==1) lRef='C';
            if (alea==2) lRef='D';
            if (alea==3) lRef='S';

            if (lRef=='U') nPlateau = 1;
            else{
                int nMaxNavire=0;           //Variable utilisé pour stocker le nombre maximal de navire dans une catégorie de navire
                if (lRef=='C') nMaxNavire=2;            //On déduit le nombre maximal de navire en fonction de la letrre de référence
                if (lRef=='D') nMaxNavire=3;
                if (lRef=='S') nMaxNavire=4;

                nPlateau = 1+ (int) (Math.random() * (nMaxNavire));         //On génère aléatoirement un numéros de navire
            }
            
            Object retourPlateau = Jeu.plateauDeJeu.get(xTire, yTire, 3, 0);          //On récupère les informations du point ou on veut tirer
            if(retourPlateau.equals("0")) choixCoordonneesTir = true;           //Si le point n'a jamais été touché on autorise la sortie de la boucle de choix de coordonnées de tir
            else if (retourPlateau.equals("1") || retourPlateau.equals("2")) choixCoordonneesTir = false;           //Si le point à déja été touché on ne quitte pas la boucle pour avoir de nouvelle coordonnées
            else if (retourPlateau.equals("5")) {           //Si les coordonnées son celle d'un sous marin qui a été touché sans être coulé
                lRef = 'S';         //On choix un sous marin pour tirer
                int tourSousMarin =0;           //On initialise un compteur de tour
                while (Jeu.flotteJoueur1.get(Flotte.nPlateauToPListe(lRef, nPlateau)).etat == false && tourSousMarin!=5){           //Tant que l'état du navire n'est pas true on cherche un autre sous marin. On vérifie aussi qu'on a pas parcourue tous les sous-marins
                    nPlateau ++;            //On prend le sous-marin suivant
                    tourSousMarin++;            //On ajoute 1 au compteur
                    if (nPlateau==5) nPlateau=0;            //Si le numéros de plateau du navire arrive à 5, on le remet à 0 pour être sûr de parcourir tous les sous-marins
                }
                if (tourSousMarin==5){          //Si tous les sous-marins de l'IA a été coulé et qu'il reste un sous-marin à l'autre joueur, il a gagné
                    System.out.println("Je ne peux plus couler vos sous-marins car vous avez détruit tous mes sous-marins");TimeUnit.SECONDS.sleep(3);            //On affiche la raison de cette victoire prématurée
                    return 1;
                }
                
                choixCoordonneesTir = true;         //On autorise la sortie de la boucle, si on a trouvé un sous marin
            }
            
            if (Jeu.flotteJoueur1.get(Flotte.nPlateauToPListe(lRef, nPlateau)).etat == false) choixCoordonneesTir=false;             //on vérifie que le navire choisie n'est pas coulé
            
        }while(choixCoordonneesTir == false);           //On vérifie que la condition de sortie de la boucle est validée
        
        if (nombreDeTir == (15*15+4)){            //Si on n'a parcouru tout le plateau
            nombreDeTir = reinitialiserGrilleTrir();          //On remet les cases non touché à 0 et on stock dans nombre de tir le nombre de case déjà touché
        }

        pListe=Flotte.nPlateauToPListe(lRef, nPlateau);
        
        System.out.println("C'est bon. Je choisis un " + Jeu.flotteJoueur1.get(pListe).nom + " pour tirer sur les coordonnées :");         //On affiche qui dit qu'on a trouvé des coordonées de tir
        System.out.println( B_BLEU_AR +BLANC+(char) (xTire + 65) + " " + (yTire+1) +RESET+RESET_AR +"\n");TimeUnit.SECONDS.sleep(2);          //On affiche de message avec les coordonnées de tir
        System.out.print(ROUGE +"Attention ! \nJe TIRE!!"+RESET); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.println("."); TimeUnit.SECONDS.sleep(1);
        
        
        /*Effectuer le tir*********************************************/
        Object resultatEmplacement = Jeu.plateauDeJeu.get(xTire, yTire, 0, 0);            //On récupère les informations de la case qui est sur les coordonées de tir
        if(resultatEmplacement == (Object) '_'){            //Si la case ne contient pas de navire
            Jeu.plateauDeJeu.modification(xTire, yTire, 3, 0, "1");           //On met sur la grille de tire de l'IA le chiffre 1 (ce qui signifie qu'on a tiré sur cette case sans rien touché)
            nombreDeTir ++;         //On rajoute 1 au nombre de tir
            System.out.println("\n\nC'est raté :(\nQuel échec!");           //On affiche un message qui dit que que l'IA n'a pas touché de navire 
            System.out.println("Bonne chance!"); TimeUnit.SECONDS.sleep(3);
        }
        else {          //Si la case contient un navire
            Jeu.flotteJoueur1.get(pListe).impact(xTire, yTire, 1);           //On appel la méthode qui permet de rentrer les différentes informations lors d'un impacte
            nombreDeTir ++;         //On rajoute 1 au nombre de tir
            System.out.println("Et c'est touché"); TimeUnit.SECONDS.sleep(10);
        }
        return 1;
    }



    public int niveau2IA() throws InterruptedException{
        boolean bougerNavire2=false;
        if (((int) (Math.random()*15))>12) bougerNavire2=true;

        /*Si l'IA tir**************************************************/
        if ((bougerNavire2==false) && (saveCoord[0] ==false) && (saveCoord[1] ==false) && (saveCoord[2] == false) && (saveCoord[3] == false) ){
            
            
            System.out.println("Veuillez patienter le temps que j'effectue mon tir");
            System.out.print("Je choisis les coordonnées, 4 sec!");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");

            /*Génération des coordonées de tir et choix navire*********/
            if ( (saveCoord[0] ==false) && (saveCoord[1] ==false) && (saveCoord[2] ==false) && (saveCoord[3] ==false) ){//On vérifie qu'il y a pas des emplacements de tir déjà enregistré
                do{ 
                    xTire = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans x
                    yTire = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans y

                    for (int i=0; i<10;i++){
                        if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                            pListe=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                            i=10;           //Pour sortir de la boucle
                        }
                    }
                    
                    Object retourPlateau = Jeu.plateauDeJeu.get(xTire, yTire, 3, 0);          //On récupère les informations du point ou on veut tirer
                    if(retourPlateau.equals("0")) choixCoordonneesTir = true;           //Si le point n'a jamais été touché on autorise la sortie de la boucle de choix de coordonnées de tir
                    else if (retourPlateau.equals("1") || retourPlateau.equals("2")) choixCoordonneesTir = false;           //Si le point à déja été touché on ne quitte pas la boucle pour avoir de nouvelle coordonnées
                    else if (retourPlateau.equals("5")) {           //Si les coordonnées son celle d'un sous marin qui a été touché sans être coulé
                        lRef = 'S';         //On choix un sous marin pour tirer
                        int tourSousMarin =0;           //On initialise un compteur de tour
                        while (Jeu.flotteJoueur1.get(pListe).etat == false && tourSousMarin!=5){           //Tant que l'état du navire n'est pas true on cherche un autre sous marin. On vérifie aussi qu'on a pas parcourue tous les sous-marins
                            nPlateau ++;            //On prend le sous-marin suivant
                            tourSousMarin++;            //On ajoute 1 au compteur
                            if (nPlateau==5) nPlateau=0;            //Si le numéros de plateau du navire arrive à 5, on le remet à 0 pour être sûr de parcourir tous les sous-marins
                        }
                        if (tourSousMarin==5){          //Si tous les sous-marins de l'IA a été coulé et qu'il reste un sous-marin à l'autre joueur, il a gagné
                            System.out.println("Je ne peux plus couler vos sous-marins car vous avez détruit tous mes sous-marins");TimeUnit.SECONDS.sleep(3);            //On affiche la raison de cette victoire prématurée
                            return 1;
                        }
                        choixCoordonneesTir = true;         //On autorise la sortie de la boucle, si on a trouvé un sous marin
                    }
                    
                    if (Jeu.flotteJoueur1.get(pListe).etat == false) choixCoordonneesTir=false;             //on vérifie que le navire choisie n'est pas coulé
                    
                }while(choixCoordonneesTir == false);           //On vérifie que la condition de sortie de la boucle est validée
            
            }
            
            

            if (saveCoord[0]==true){            //Si il y a des coordonées de tir sauvegar dans la mémoir 1

                if (Jeu.flotteJoueur1.get(stockSaveCoord[0][2]).etat !=true && Jeu.flotteJoueur1.get(stockSaveCoord[0][2]).lRef!= 'S') {            //On vérifie que le bateau sélecctionner pour le tir est toujours à flot et quelle est différente d'un sous-marin 
                for (int i=0; i<10;i++){
                    if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                        stockSaveCoord[0][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                        i=10;           //Pour sortir de la boucle
                    }
                }
                }
                else if (Jeu.flotteJoueur1.get(stockSaveCoord[0][2]).etat!=true && Jeu.flotteJoueur1.get(stockSaveCoord[0][2]).lRef== 'S'){
                    for (int i=6; i<10;i++){
                        if (Jeu.flotteJoueur1.get(i).navireVivant()==true) {           //Si le navire n'est pas coulé
                                stockSaveCoord[0][2]=i;          //On sélectionne un sous-marin
                                i=10;           //Pour sortir de la boucle
                        }
                    }
                }
                
                xTire=stockSaveCoord[0][0];         //On attribue à chaque élément utilisé pour un tir les éléments sauvegardés
                yTire=stockSaveCoord[0][1];
                pListe=stockSaveCoord[0][2];
                lRef=Jeu.flotteJoueur1.get(pListe).lRef;            
                saveCoord[0]=false;
            }
            else if (saveCoord[1]==true){
                
                if (Jeu.flotteJoueur1.get(stockSaveCoord[1][2]).etat!=true){            //On vérifie que le bateau sélectionner pour le tir est toujours à flot
                    for (int i=0; i<10;i++){
                        if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                            stockSaveCoord[1][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                            i=10;           //Pour sortir de la boucle
                        }
                    }
                }
                
                xTire=stockSaveCoord[1][0];         //On attribue à chaque élément utilisé pour un tir les éléments sauvegardés
                yTire=stockSaveCoord[1][1];
                pListe=stockSaveCoord[1][2];
                lRef=Jeu.flotteJoueur1.get(pListe).lRef;
                saveCoord[1]=false;
            }
            else if (saveCoord[2]==true){
                
                if (Jeu.flotteJoueur1.get(stockSaveCoord[2][2]).etat!=true){            //On vérifie que le bateau sélectionner pour le tir est toujours à flot
                    for (int i=0; i<10;i++){
                        if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                            stockSaveCoord[2][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                            i=10;           //Pour sortir de la boucle
                        }
                    }
                }

                xTire=stockSaveCoord[2][0];         //On attribue à chaque élément utilisé pour un tir les éléments sauvegardés
                yTire=stockSaveCoord[2][1];
                pListe=stockSaveCoord[2][2];
                lRef=Jeu.flotteJoueur1.get(pListe).lRef;
                saveCoord[2]=false;
            }
            else if (saveCoord[3]==true){
                
                if (Jeu.flotteJoueur1.get(stockSaveCoord[3][2]).etat!=true){            //On vérifie que le bateau sélectionner pour le tir est toujours à flot
                    for (int i=0; i<10;i++){
                        if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                            stockSaveCoord[3][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                            i=10;           //Pour sortir de la boucle
                        }
                    }
                }
                
                xTire=stockSaveCoord[3][0];         //On attribue à chaque élément utilisé pour un tir les éléments sauvegardés
                yTire=stockSaveCoord[3][1];
                pListe=stockSaveCoord[3][2];
                lRef=Jeu.flotteJoueur1.get(pListe).lRef;
                saveCoord[3]=false;
            }

            if (nombreDeTir == (15*15+4)){            //Si on n'a parcouru tout le plateau
                nombreDeTir = reinitialiserGrilleTrir();          //On remet les cases non touché à 0 et on stock dans nombre de tir le nombre de case déjà touché
            }

            System.out.println("C'est bon. Je choisis un " + Jeu.flotteJoueur1.get(pListe).nom + " pour tirer sur les coordonnées :");         //On affiche qui dit qu'on a trouvé des coordonées de tir
            System.out.println( B_BLEU_AR +BLANC+(char) (xTire + 65) + " " + (yTire+1) +RESET+RESET_AR +"\n");TimeUnit.SECONDS.sleep(2);          //On affiche de message avec les coordonnées de tir
            System.out.print(ROUGE +"Attention ! \nJe TIRE!!"+RESET); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.println("."); TimeUnit.SECONDS.sleep(1);
            
            /*Effectuer le tir*********************************************/
            if(Jeu.plateauDeJeu.get(xTire, yTire, 0, 0) == (Object) '_'){            //Si la case ne contient pas de navire
                Jeu.plateauDeJeu.modification(xTire, yTire, 3, 0, "1");           //On met sur la grille de tire de l'IA le chiffre 1 (ce qui signifie qu'on a tiré sur cette case sans rien touché)
                nombreDeTir ++;         //On rajoute 1 au nombre de tir
                System.out.println("\n\nC'est raté :(\nQuel échec!");           //On affiche un message qui dit que que l'IA n'a pas touché de navire 
                System.out.println("Bonne chance!"); TimeUnit.SECONDS.sleep(3);
            }
            else if (Jeu.plateauDeJeu.get(xTire, yTire, 0, 0) ==  (Object) 'S' && lRef!='S'){           //Si le navire touché est un sous-marin et que ce n'est pas un sous-marin qui a tiré
                if (!Jeu.plateauDeJeu.get(xTire,yTire,3,0).equals("2")) Jeu.plateauDeJeu.modification(xTire,yTire,3,0,"5");

                int nPlateauAdv;            //Le numéro du plateau du bateau adverse
                int pListeAdv;              //La position dans la liste des navires de l'adversaire      

                nPlateauAdv = (int) Jeu.plateauDeJeu.get(xTire,yTire,1,1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
                pListeAdv=Flotte.nPlateauToPListe('S', nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

                Jeu.flotteJoueur1.get(pListeAdv).coordonnees[0][2]=2;           //On met la coordonées sur 2 pour signifie, ce qui signifie que le sous-marin a été touché sans être coulé (il ne peux plus être déplacé)

                saveCoord[0]=true;          //On retient une coordonnées de tire pour le prochain tour
                stockSaveCoord[0][0]=xTire;         //On stock les différents informations de tire
                stockSaveCoord[0][1]=yTire;
                stockSaveCoord[0][2]=0;
                for (int i=6; i<10;i++){
                    if (Jeu.flotteJoueur1.get(i).navireVivant()==true) {
                            stockSaveCoord[0][2]=i;          //On sélectionne un sous-marin
                            i=10;           //Pour sortir de la boucle
                    }
                }
                if (stockSaveCoord[0][2]==0){           //Si l'IA n'a plus de sous-marin le joueur adverse à gagner
                    System.out.println("Je ne peux plus couler vos sous-marins car vous avez détruit tous mes sous-marins");TimeUnit.SECONDS.sleep(3);            //On affiche la raison de cette victoire prématurée
                    return 1;           //On retourne 1 pour que le programme confirme la victoire du joueur
                }
                nombreDeTir ++;         //On rajoute 1 au nombre de tir
                System.out.println("J'ai touché un objet non identifié");
                System.out.println("Je retiens...");TimeUnit.SECONDS.sleep(5);

            }

            /*Impacte sur un navire adverse************************************************/
            else {          //Si les coordonées de tir coorespondent à un navire
                System.out.println("Et c'est touché!!");TimeUnit.SECONDS.sleep(2);
                Jeu.flotteJoueur1.get(pListe).impact(xTire, yTire, 1);           //On appel la méthode qui permet de rentrer les différentes informations lors d'un impacte
                TimeUnit.SECONDS.sleep(3);
                nombreDeTir ++;         //On rajoute 1 au nombre de tir


                /*Sauvegarde de coordonée pour les prochains tirs**************************/
                if (xTire-2>=0){            //Si le tir 2 case à gauche est possible
                    if (!Jeu.plateauDeJeu.get(xTire-2, yTire, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire-2, yTire, 3, 0).equals("2")){
                        saveCoord[0]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[0][0]=xTire-2;           //On stock les informations de tir
                        stockSaveCoord[0][1]=yTire;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                                stockSaveCoord[0][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //Pour sortir de la boucle
                            }
                        }
                    }
                }
                if (xTire+2<15){        //Si le tir 2 case à doite est possible
                    if (!Jeu.plateauDeJeu.get(xTire+2, yTire, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire+2, yTire, 3, 0).equals("2")){
                        saveCoord[1]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[1][0]=xTire+2;           //On stock les informations de tir
                        stockSaveCoord[1][1]=yTire;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                                stockSaveCoord[1][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //Pour sortir de la boucle
                            }
                        }
                    }
                }
                if (yTire-2>=0){        //Si le tir 2 case en bas est possible
                    if (!Jeu.plateauDeJeu.get(xTire, yTire-2, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire, yTire-2, 3, 0).equals("2")){
                        saveCoord[2]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[2][0]=xTire;         //On stock les informations de tir
                        stockSaveCoord[2][1]=yTire-2;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                                stockSaveCoord[2][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //Pour sortir de la boucle
                            }
                        }
                    }
                }
                if (yTire+2<15){        //Si le tir 2 case en haut est possible
                    if (!Jeu.plateauDeJeu.get(xTire, yTire+2, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire, yTire+2, 3, 0).equals("2")){
                        saveCoord[3]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[3][0]=xTire;         //On stock les informations de tir
                        stockSaveCoord[3][1]=yTire+2;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire n'est pas coulé
                                stockSaveCoord[3][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //Pour sortir de la boucle
                            }
                        }
                    }
                }
            }
        }
        else {              //Si l'IA déplace un navire
            System.out.println("Je décide de déplacer un de mes navires.");         //On affiche un message
            System.out.println("Veuillez patienter le temps que j'effectue la manoeuvre");
            TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");
            bougerNavireIA();           //On appel la méthode qui déplace un bateau aléatoirement
            TimeUnit.SECONDS.sleep(5);
        }
        return 1;
    }

    

    public int niveau3IA() throws InterruptedException{
        boolean bougerNavire3=false;
        if (((int) (Math.random()*8))>6) bougerNavire3=true;            //Si le nombre aléatoire est supérieur a 6, on bouge le navire 


        /*Vérification emplacement de sauvegarde***********************/
        boolean emplacementSave = false;        //Variable utilisé pour savoir s'il y a des coordonées de tir déjà enregistré
        
        for (int i=0; i<20; i++){           //On parcour toutes les coordonées
            if (saveCoord[i]==true) {
                emplacementSave=true;           //Si un emplacment est actif on met la variable sur true
                i=20;          //Et on quitte la boucle 
            }
        }

        /*Tirer avec les destoyers*************************************/
        if (emplacementSave==false && nombreDeTirDestroyer <3){     //S'il a pas encore tiré sauvegarde sur cette case et qu'on a pas dépassé le nombre de destroyer maximum
            for (int i=3; i<6;i++)
            {
                if (Jeu.flotteJoueur1.get(i).premierTire==true && Jeu.flotteJoueur1.get(i).etat==true){         //Si c'est le premier tire du destroyer et qu'il n'a pas été coulé   
                    
                    System.out.println("J'effetue un tir avec mon destroyer");          //On affiche un message un message disant qu'il tire avec son destroyer
                    System.out.println("Veuillez patienter le temps du recueil des données");
                    TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");
                    
                    int xTireFusee = 0;         //Coordonnée de tir de la fusée par défaut
                    int yTireFusee = 0;

                    for (int j=0 ;j<242;j++){
                        xTireFusee = (int) (Math.random()* 12);         //On prend un nombre aléatoire entre 0 et 11
                        yTireFusee = (int) (Math.random()* 12);         //On prend un nombre aléatoire entre 0 et 11
                        if (verifDejaExplore(xTireFusee, yTireFusee) == false){         //Si c'est case on déjà été exploré
                            j=242;          //On sort de la boucle
                        }
                    }

                    int pSaveCoord = 0;         //Emplacement de la position des coordonnées par défaut

                    for (int k=0; k<4 ; k++){           //On parcourt un coté d'un carré de 4 cases sur l'axe des abcsisses
                        for (int l=0; l<4; l++){            //On parcourt un coté d'un carré de 4 cases sur l'axe des ordonnées 
                            if (Jeu.plateauDeJeu.get(xTireFusee+k,yTireFusee+l,0,0)!=(Object)'_') {         //Si la case contient 
                                if (!Jeu.plateauDeJeu.get(xTireFusee+k,yTireFusee+l,3,0).equals("2")) Jeu.plateauDeJeu.modification(xTireFusee+k,yTireFusee+l,3,0,"4");         //Affiche sur la grille de tir du joueur les navires qui ont été touché par la fusée éclairante
                                
                                do{
                                    pSaveCoord=(int) (Math.random()*16);            //On prend aléatoirement un nombre entre 0 et 15
                                } while (saveCoord[pSaveCoord]==true);          //Tant qu'il a pas trouvé un emplacement de stockage
                                
                                saveCoord[pSaveCoord]=true;         //On met cet emplacement contient une sauvegarde
                                stockSaveCoord[pSaveCoord][0]=xTireFusee+k;         //On stock les nouvelles coordonnées a cet emplacement
                                stockSaveCoord[pSaveCoord][1]=yTireFusee+l;
                                for (int m=0; m<10;m++){
                                    if (Jeu.flotteJoueur1.get(m).etat==true) {          //Si le navire n'a pas coulé
                                        stockSaveCoord[pSaveCoord][2]=m;            //On prend les références du bateau le plus puissant non coulé
                                        m=10;           //On sort de la boucle
                                    }
                                }
                                
                            }
                            else Jeu.plateauDeJeu.modification(xTireFusee+k,yTireFusee+l,3,0,"3");          //Affiche sur la grille de tir du joueur là ou la fusée éclairante a touché
                        }
                    }

                    System.out.println("C'est intéréssant ce que mes avions m'ont rapporté du terrain");           //On affiche un message disant que l'IA a bien effectué le tir de la fusée éclairante
                    System.out.println("Je prends note");TimeUnit.SECONDS.sleep(5);
                    Jeu.flotteJoueur1.get(i).premierTire=false;         //le premier tir du destroyer choisi est mis à false
                    nombreDeTirDestroyer++;         //On rajoute 1 au nombre de tir du destroyer
                    return 1;           //On retourne 1 si tout c'est bien passé
                }
            }
        }

        /*Si l'IA tir**************************************************/
        if (bougerNavire3==false){
            
            
            System.out.println("Veuillez patienter le temps que j'effectue mon tir");
            System.out.print("Je choisis les coordonnées, 4 sec!");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");
            
            /*Récupération des coordonées enregistrée*****************/
            if (emplacementSave==true){
                
                for (int i=0; i<20;i++){
                    if (saveCoord[i]==true){            //Si il y a des coordonées de tir sauvegar dans la mémoir 1

                        if (Jeu.flotteJoueur1.get(stockSaveCoord[i][2]).etat !=true && Jeu.flotteJoueur1.get(stockSaveCoord[i][2]).lRef!= 'S') {            //On vérifie que le navire sélecctionner pour le tir est toujours à flot et quelle est différente d'un sous-marin 
                            for (int j=0; j<10;j++){            //Si le navire est coulé on sélectionner un nouveau navire qui n'est pas coulé
                                if (Jeu.flotteJoueur1.get(j).etat==true) {
                                    stockSaveCoord[i][2]=j;            //On prend les coordonnées du navire le plus puissant non coulé
                                    j =10;          //On sort de la boucle
                                }
                            }
                        }
                        else if (Jeu.flotteJoueur1.get(stockSaveCoord[i][2]).etat!=true && Jeu.flotteJoueur1.get(stockSaveCoord[i][2]).lRef== 'S'){         //Si le navire sélectionné est coulé et que c'est un sous-marin
                            for (int k=6; k<10;k++){
                                if (Jeu.flotteJoueur1.get(k).navireVivant()==true) {            //Si le navire n'a pas coulé
                                    stockSaveCoord[0][2]=k;          //On sélectionne un sous-marin
                                    k =10;          //On sort de la boucle
                                }
                            }      //Si le navire sélectionné pour le tir été un sous-marin on prend le suivant si c'est possible
                        }
                        
                        xTire=stockSaveCoord[i][0];         //On attribue à chaque élément utilisé pour un tir les éléments sauvegardés
                        yTire=stockSaveCoord[i][1];
                        pListe=stockSaveCoord[i][2];
                        lRef=Jeu.flotteJoueur1.get(pListe).lRef;
                        saveCoord[i]=false;
                        

                        if (!Jeu.plateauDeJeu.get(xTire, yTire, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire, yTire, 3, 0).equals("2") && !Jeu.plateauDeJeu.get(xTire, yTire, 3, 0).equals("3")){           //Si l'emplacement n'a pas été 
                            emplacementSave=true;           //On met l'emplacement de sauvegarde sur true pour qu'il effectue le tir avec ces coordonées
                            i=20;           //On sort de la boucle
                        }
                        else{           //Sinon
                            emplacementSave=false;          //On met l'emplacement de sauvegarde sur false pour qu'il calcul des coordonées alléatoire s'il ne reste plus d'emplacement de sauvegarde
                        }
                    }
                }
            }


            /*Génération des coordonées de tir et choix navire*********/
            if (emplacementSave==false ){          //On vérifie qu'il y a pas des emplacements de tir déjà enregistré
                do{ 
                    xTire = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans x
                    yTire = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans y

                    for (int i=0; i<10;i++){
                        if (Jeu.flotteJoueur1.get(i).etat==true) {          //Si le navire de l'IA est encore à flot 
                            pListe=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                            i=10;           //On sort de la boucle
                        }
                    }
                    
                    Object retourPlateau = Jeu.plateauDeJeu.get(xTire, yTire, 3, 0);          //On récupère les informations du point où on veut tirer
                    if(retourPlateau.equals("0")) choixCoordonneesTir = true;           //Si le point n'a jamais été touché on autorise la sortie de la boucle de choix de coordonnées de tir
                    else if (retourPlateau.equals("1") || retourPlateau.equals("2") || retourPlateau.equals("3")) choixCoordonneesTir = false;           //Si le point à déja été touché on ne quitte pas la boucle pour avoir de nouvelle coordonnées
                    else if (retourPlateau.equals("5")) {           //Si les coordonnées son celle d'un sous marin qui a été touché sans être coulé
                        lRef = 'S';         //On choix un sous marin pour tirer
                        int tourSousMarin =0;           //On initialise un compteur de tour
                        while (Jeu.flotteJoueur1.get(pListe).etat == false && tourSousMarin!=5){           //Tant que l'état du navire n'est pas true on cherche un autre sous marin. On vérifie aussi qu'on a pas parcourue tous les sous-marins
                            nPlateau ++;            //On prend le sous-marin suivant
                            tourSousMarin++;            //On ajoute 1 au compteur
                            if (nPlateau==5) nPlateau=0;            //Si le numéros de plateau du navire arrive à 5, on le remet à 0 pour être sûr de parcourir tous les sous-marins
                        }
                        if (tourSousMarin==5){          //Si tous les sous-marins de l'IA a été coulé et qu'il reste un sous-marin à l'autre joueur, il a gagné
                            System.out.println("Je ne peux plus couler vos sous-marins car vous avez détruit tous mes sous-marins");TimeUnit.SECONDS.sleep(3);            //On affiche la raison de cette victoire prématurée
                            return 1;
                        }
                        choixCoordonneesTir = true;         //On autorise la sortie de la boucle, si on a trouvé un sous marin
                    }
                    
                    if (Jeu.flotteJoueur1.get(pListe).etat == false) choixCoordonneesTir=false;             //on vérifie que le navire choisie n'est pas coulé
                    
                }while(choixCoordonneesTir == false);           //On vérifie que la condition de sortie de la boucle est validée
            
            }


            if (nombreDeTir == (15*15+4)){            //Si on n'a parcouru tout le plateau
                nombreDeTir = reinitialiserGrilleTrir();          //On remet les cases non touché à 0 et on stock dans nombre de tir le nombre de case déjà touché
            }

            System.out.println("C'est bon. Je choisis un " + Jeu.flotteJoueur1.get(pListe).nom + " pour tirer sur les coordonnées :");         //On affiche qui dit qu'on a trouvé des coordonées de tir
            System.out.println( B_BLEU_AR +BLANC+(char) (xTire + 65) + " " + (yTire+1) +RESET+RESET_AR +"\n");TimeUnit.SECONDS.sleep(2);          //On affiche de message avec les coordonnées de tir
            System.out.print(ROUGE +"Attention ! \nJe TIRE!!"+RESET); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.println("."); TimeUnit.SECONDS.sleep(1);
            

            /*Effectuer le tir*********************************************/
            if(Jeu.plateauDeJeu.get(xTire, yTire, 0, 0) == (Object) '_'){            //Si la case ne contient pas de navire
                Jeu.plateauDeJeu.modification(xTire, yTire, 3, 0, "1");           //On met sur la grille de tire de l'IA le chiffre 1 (ce qui signifie qu'on a tiré sur cette case sans rien touché)
                nombreDeTir ++;         //On rajoute 1 au nombre de tir
                System.out.println("\n\nC'est raté :(\nQuel échec!");           //On affiche un message qui dit que que l'IA n'a pas touché de navire 
                System.out.println("Bonne chance!"); TimeUnit.SECONDS.sleep(3);
            }
            else if (Jeu.plateauDeJeu.get(xTire, yTire, 0, 0) ==  (Object) 'S' && lRef!='S'){           //Si le navire touché est un sous-marin et que ce n'est pas un sous-marin qui a tiré
                if (!Jeu.plateauDeJeu.get(xTire,yTire,3,0).equals("2")) Jeu.plateauDeJeu.modification(xTire,yTire,3,0,"5");

                int nPlateauAdv;            //Le numéro du plateau du bateau adverse
                int pListeAdv;              //La position dans la liste des navires de l'adversaire      

                nPlateauAdv = (int) Jeu.plateauDeJeu.get(xTire,yTire,0,1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
                pListeAdv=Flotte.nPlateauToPListe('S', nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

                Jeu.flotteJoueur1.get(pListeAdv).coordonnees[0][2]=2;           //On met la coordonées sur 2 pour signifie, ce qui signifie que le sous-marin a été touché sans être coulé (il ne peux plus être déplacé)

                saveCoord[0]=true;          //On retient une coordonnées de tire pour le prochain tour
                stockSaveCoord[0][0]=xTire;         //On stock les différents informations de tire
                stockSaveCoord[0][1]=yTire;
                stockSaveCoord[0][2]=0;
                for (int i=6; i<10;i++){
                    if (Jeu.flotteJoueur1.get(i).navireVivant()==true) {
                        stockSaveCoord[0][2]=6;          //On sélectionne un sous-marin
                        i=10;           //On sort de la boucle
                    }
                }
                if (stockSaveCoord[0][2]==0){           //Si l'IA n'a plus de sous-marin le joueur adverse à gagner
                    System.out.println("Je ne peux plus couler vos sous-marins car vous avez détruit tous mes sous-marins");TimeUnit.SECONDS.sleep(3);            //On affiche la raison de cette victoire prématurée
                    return 1;           //On retourne 1 pour que le programme confirme la victoire du joueur
                }
                nombreDeTir ++;         //On rajoute 1 au nombre de tir
                System.out.println("J'ai touché un objet non identifié.");
                System.out.println("Je retiens...");TimeUnit.SECONDS.sleep(5);

            }

            /*Impacte sur un navire adverse************************************************/
            else {          //Si les coordonées de tir coorespondent à un navire
                System.out.println("Et c'est touché!!");TimeUnit.SECONDS.sleep(2);
                Jeu.flotteJoueur1.get(pListe).impact(xTire, yTire, 1);           //On appel la méthode qui permet de rentrer les différentes informations lors d'un impacte
                TimeUnit.SECONDS.sleep(3);
                nombreDeTir ++;         //On rajoute 1 au nombre de tir


                /*Sauvegarde de coordonée pour les prochains tirs**************************/
                if (xTire-2>=0){            //Si le tir 2 case à gauche est possible
                    if (!Jeu.plateauDeJeu.get(xTire-2, yTire, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire-2, yTire, 3, 0).equals("2")){
                        saveCoord[16]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[16][0]=xTire-2;           //On stock les informations de tir
                        stockSaveCoord[16][1]=yTire;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {
                                stockSaveCoord[16][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //On sort de la boucle
                            }
                        }
                    }
                }
                if (xTire+2<15){        //Si le tir 2 case à doite est possible
                    if (!Jeu.plateauDeJeu.get(xTire+2, yTire, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire+2, yTire, 3, 0).equals("2")){
                        saveCoord[17]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[17][0]=xTire+2;           //On stock les informations de tir
                        stockSaveCoord[17][1]=yTire;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {
                                stockSaveCoord[17][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //On sort de la boucle
                            }
                        }
                    }
                }
                if (yTire-2>=0){        //Si le tir 2 case en bas est possible
                    if (!Jeu.plateauDeJeu.get(xTire, yTire-2, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire, yTire-2, 3, 0).equals("2")){
                        saveCoord[18]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[18][0]=xTire;         //On stock les informations de tir
                        stockSaveCoord[18][1]=yTire-2;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {
                                stockSaveCoord[18][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //On sort de la boucle
                            }
                        }
                    }
                    
                }
                if (yTire+2<15){        //Si le tir 2 case en haut est possible
                    if (!Jeu.plateauDeJeu.get(xTire, yTire+2, 3, 0).equals("1") && !Jeu.plateauDeJeu.get(xTire, yTire+2, 3, 0).equals("2")){
                        saveCoord[19]=true;          //On est met l'emplacement sauvegarde sur true
                        stockSaveCoord[19][0]=xTire;         //On stock les informations de tir
                        stockSaveCoord[19][1]=yTire+2;
                        for (int i=0; i<10;i++){
                            if (Jeu.flotteJoueur1.get(i).etat==true) {
                                stockSaveCoord[19][2]=i;            //On prend les coordonnées du bateau le plus puissant non coulé
                                i=10;           //On sort de la boucle
                            }
                        }
                    }
                    
                }
            }
        }
        else {              //Si l'IA déplace un navire
            System.out.println("Je décide de déplacer un de mes navires.");         //On affiche un message
            System.out.println("Veuillez patienter le temps que j'effectue la manoeuvre");
            TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");
            bougerNavireIA();           //On appel la méthode qui déplace un bateau aléatoirement
            TimeUnit.SECONDS.sleep(5);
        }
        return 1;
    }

    public boolean verifDejaExplore (int xTireFusee, int yTireFusee){
        
        for (int i=0; i<4 ; i++){           //On parcourt le carré de coté 4 cases
            for (int j=0; j<4; j++){
                if (!Jeu.plateauDeJeu.get(xTireFusee+i,yTireFusee+j,3,0).equals("0")) {
                    return true;       //Inqique que ces  cases on déjà été exploré
                }
            }
        }
        return false;
    }
}

