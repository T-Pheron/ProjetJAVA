package bataillenaval;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Théric PHERON
 */
public class IA {
    
    int nombreDeTir = 0;
    
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
     * @throws InterruptedException 
     */
    public void jouer (int niveauIA) throws InterruptedException{
        
        System.out.println("\n\n"+GRIS_AR+BLANC+"                    Tour de l'IA                    "+RESET+RESET_AR);
        System.out.println("Veuillez patienter le temps que j'effectue mon tir");
        System.out.print("Je choisis les coordonnées, 4 sec!");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".\n");
        
        switch(niveauIA){
            case 1://Niveau 1 de l'IA
                int xTir;           //Variable utilisée pour stocker la valeur de l'axe x lors du tir
                int yTir;           //Variable utilisée pour stocker la valeur de l'axe y lors du tir
                int nPlateau;       //Variable utilisée pour stocker le numéro plateau du navire qui tir
                char lRef='A';          //Variable utilisée pour stocker la lettre de référence du navire qui tir
                boolean choixCoordonneesTir=true;           //Variable utilisé pour savoir si on peut sortir de la boucle de choix des coordonées
                
                /*Génération des coordonées de tir et choix navire*************/
                do{ 
                    xTir = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans x
                    yTir = (int) (Math.random() * 15);          //On stock un nombre aléatoire compris entre 0 et 14 dans y

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
                    
                    Object retourPlateau = Jeu.plateauDeJeu.get(xTir, yTir, 0, 0);          //On récupère les informations du point ou on veut tirer
                    if(retourPlateau == (Object) "0") choixCoordonneesTir = true;           //Si le point n'a jamais été touché on autorise la sortie de la boucle de choix de coordonnées de tir
                    else if (retourPlateau == (Object) "1" || retourPlateau == (Object) '2') choixCoordonneesTir = false;           //Si le point à déja été touché on ne quitte pas la boucle pour avoir de nouvelle coordonnées
                    else if (retourPlateau == (Object) "5") {           //Si les coordonnées son celle d'un sous marin qui a été touché sans être coulé
                        lRef = 'S';         //On choix un sous marin pour tirer
                        int tourSousMarin =0;           //On initialise un compteur de tour
                        while (Jeu.flotteJoueur1.get(Flotte.nPlateauToPListe(lRef, nPlateau)).etat == false && tourSousMarin!=5){           //Tant que l'état du navire n'est pas true on cherche un autre sous marin. On vérifie aussi qu'on a pas parcourue tous les sous-marins
                            nPlateau ++;            //On prend le sous-marin suivant
                            tourSousMarin++;            //On ajoute 1 au compteur
                            if (nPlateau==5) nPlateau=0;            //Si le numéros de plateau du navire arrive à 5, on le remet à 0 pour être sûr de parcourir tous les sous-marins
                        }
                        if (tourSousMarin==5){          //Si tous les sous-marins de l'IA a été coulé et qu'il reste un sous-marin à l'autre joueur, il a gagné
                            System.out.println("Vous avez GAGNÉ");          //On affiche un message de victoire
                            System.out.println("Je ne peux plus couler vos sous-marin car vous avez détruit tous mes sous marin");            //On affiche la raison de cette victoire prématurée
                            
                            //Lancer menu de sauvegarde et trouver une solution pour sortir
                        }
                        choixCoordonneesTir = true;         //On autorise la sortie de la boucle, si on a trouvé un sous marin
                    }
                    
                    if (Jeu.flotteJoueur1.get(Flotte.nPlateauToPListe(lRef, nPlateau)).etat == false) choixCoordonneesTir=false;             //on vérifie que le navire choisie n'est pas coulé
                    
                }while(choixCoordonneesTir == false);           //On vérifie que la condition de sortie de la boucle est validée
                
                if (nombreDeTir == (15*15+4)){            //Si on n'a parcouru tout le plateau
                    nombreDeTir = reinitialiserGrilleTrir();          //On remet les cases non touché à 0 et on stock dans nombre de tir le nombre de case déjà touché
                }
                
                System.out.println("C'est bon. Je choisis un " + Jeu.flotteJoueur1.get(Flotte.nPlateauToPListe(lRef, nPlateau)).nom + " pour tirer sur les coordonnées :");         //On affiche qui dit qu'on a trouvé des coordonées de tir
                System.out.println( B_BLEU_AR +BLANC+(char) (xTir + 65) + " " + (yTir+1) +RESET+RESET_AR +"\n");TimeUnit.SECONDS.sleep(2);          //On affiche de message avec les coordonnées de tir
                System.out.print(ROUGE +"Attention ! \nJe TIRE!!"+RESET); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.print("."); TimeUnit.SECONDS.sleep(1);System.out.println("."); TimeUnit.SECONDS.sleep(1);
                
                
                /*Effectuer le tir*********************************************/
                Object resultatEmplacement = Jeu.plateauDeJeu.get(xTir, yTir, 0, 0);            //On récupère les informations de la case qui est sur les coordonées de tir
                if(resultatEmplacement == (Object) "_"|| resultatEmplacement == (Object) "1" ){            //Si la case ne contient pas de navire
                    Jeu.plateauDeJeu.modification(xTir, yTir, 3, 0, "1");           //On met sur la grille de tire de l'IA le chiffre 1 (ce qui signifie qu'on a tiré sur cette case sans rien touché)
                    nombreDeTir ++;         //On rajoute 1 au nombre de tir
                    System.out.println("\n\nC'est raté :(\nQuel échec!");           //On affiche un message qui dit que que l'IA n'a pas touché de navire 
                    System.out.println("Bonne chance!"); TimeUnit.SECONDS.sleep(3);
                }
                else {          //Si la case contient un navire
                    Jeu.flotteJoueur1.get(Flotte.nPlateauToPListe(lRef, nPlateau)).impact(xTir, yTir, 1);           //On appel la méthode qui permet de rentrer les différentes informations lors d'un impacte
                    nombreDeTir ++;         //On rajoute 1 au nombre de tir
                    System.out.println("Et  c'est touché"); TimeUnit.SECONDS.sleep(10);
                }
        }
            
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
                
                if (Jeu.plateauDeJeu.get(i, j, 3, 0)== (Object) "1") Jeu.plateauDeJeu.modification(i, j, 3, 0, "0");            //Pour les cases les chiffre 1 (touché sans avoir touché de navire) on les remets à zéro
                if (Jeu.plateauDeJeu.get(i, j, 3, 0)== (Object) "2") nombreDeCaseRestante++;            //Si on a déjà touché un navire à cette emplacement on ajoute 1 au nombre de case restante
            }
        }
        
        return nombreDeCaseRestante;            //On renvoie le nombre de case restanste
    }
    
}
