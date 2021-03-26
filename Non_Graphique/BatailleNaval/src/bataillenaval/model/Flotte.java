package bataillenaval.model;

import java.io.Serializable;



public abstract class Flotte implements Serializable{
    
    

    /**
     *
     */
    private static final long serialVersionUID = 7786772327657687295L;
    public boolean etat = false;        //Variable qui permet de connître si le navire est coulé. True si le navire n'est pas coulé, false sinon
    public String nom;              //Variable qui permet de stocker le nom du navire
    public int [][] coordonnees;        //Variable qui stock les coordonnées du navire
    public int direction;               //Variable qui stock la direction du bateau. 0 s'il est à la horizontale 1 s'il est à la vertical.
    public int taille;                  //Variable qui stock la taille du bateau
    public int puissance;           //Variable qui stock la puissance de tir du navire
    public char lRef;               //Variable qui stock la lettre de référence du bateau
    public int nRef;                //Variable qui stock le numéro de référence du bateau
    public boolean premierTire = true;      //Variable utilisé pour savoir si c'est le premier tir d'un bateau
    
    /**
     * Constructeur des variable de type stock.
     */
    public Flotte(){
        
    }
    
    public abstract int tir() throws InterruptedException;
    public abstract int impact(int xTire, int yTire, int numeroJoueur) throws InterruptedException;
    public abstract void nRef (int pListe);
    
    //**************************************************************************
    /**
     * Méthode qui permet de renvoyer la position dans la liste.
     * Elle permet avec le numéro présent sur le plateau de renvoyer la position du bateau dans la liste avec les navires.
     * @param lRef La lettre de référence du bateau
     * @param pListe La position dans la liste
     * @return Retourne la position dans la liste du navire
     */
    public static int pListeToNPlateau(char lRef, int pListe){
        
        if (lRef=='U') return pListe+1;//On déduit la position dans la liste
        if (lRef=='C') return pListe;
        if (lRef=='D') return pListe-2;
        if (lRef=='S') return pListe-5;
        return 0;
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de renvoyer le numéro du plateau.
     * Elle permet avec le numéro présent sur le plateau de renvoyer le numéro de référence du navire.
     * @param lRef La lettre de référence
     * @param nPlateau Le numéro présent sur le plateau
     * @return On retoune le numéro de référence
     */
    public static int nPlateauToNRef(char lRef, int nPlateau){
        
        if (lRef=='U') return nPlateau;//On déduit le numéro de référence du bateau
        if (lRef=='C') return nPlateau+9;
        if (lRef=='D') return nPlateau+19;
        if (lRef=='S') return nPlateau+29;
        return 0;
    }
    
    
   //***************************************************************************
    /**
     * Méthode qui permet de renvoyer la position dans la liste du navire.
     * Elle permet avec le numéro présent sur le plateau de renvoyer le numéro de plateau.
     * @param lRef La lettre de référence.
     * @param nPlateau Le numéro de plateau
     * @return On retourne la position dans la liste
     */
    public static int nPlateauToPListe(char lRef, int nPlateau){
        
        if (lRef=='U') return nPlateau-1;           //Déduit la position dans la liste
        if (lRef=='C') return nPlateau;
        if (lRef=='D') return nPlateau+2;
        if (lRef=='S') return nPlateau+5;
        return -1;
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de savoir si un navire est coulé.
     * Vérifie si le navire a été coulé où pas
     * @return True si le bateau n'est pas coulé, false sinon
     */
    public boolean navireVivant(){
        for (int j=0; j<taille; j++){           //On parcour tous les états des coordonées
            if (coordonnees[j][2] == 1) return true;            //S'il y a au moins un état qui est à 1 on renvoie true
        }
        etat=false;
        return false;           //Sinon on renvoit false
    }
    
    
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs du texte*/
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
    
    public static final String B_NOIR = "\u001B[30;1m";            
    public static final String B_ROUGE = "\u001B[31;1m";
    public static final String B_VERT = "\u001B[32;1m";
    public static final String B_JAUNE = "\u001B[33;1m";
    public static final String B_BLEU = "\u001B[34;1m";
    public static final String B_VOILET = "\u001B[35;1m";
    public static final String B_CYAN = "\u001B[36;1m";
    public static final String B_BLANC = "\u001B[37;1m";
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs de l'arriere plan*/
    /*Elles doivent être placées devant le texte qui doit avoir une arrière plan en couleur avec un +*/
    
    public static final String RESET_AR = "\u001B[40m";                //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public static final String ROUGE_AR = "\u001B[41m";
    public static final String VERT_AR = "\u001B[42m";
    public static final String JAUNE_AR = "\u001B[43m";
    public static final String BLEU_AR = "\u001B[44m";
    public static final String VOILET_AR = "\u001B[45m";
    public static final String CYAN_AR = "\u001B[46m";
    public static final String GRIS_AR = "\u001B[47m";
    
    public static final String B_ROUGE_AR = "\u001B[41;1m";
    public static final String B_VERT_AR = "\u001B[42;1m";
    public static final String B_JAUNE_AR = "\u001B[43;1m";
    public static final String B_BLEU_AR = "\u001B[44;1m";
    public static final String B_VOILET_AR = "\u001B[45;1m";
    public static final String B_CYAN_AR = "\u001B[46;1m";
    public static final String B_GRIS_AR = "\u001B[47;1m";
}
