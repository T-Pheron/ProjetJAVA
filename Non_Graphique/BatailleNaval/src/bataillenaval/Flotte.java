package bataillenaval;



public abstract class Flotte {
    
    public boolean etat = false;        //Variable qui permet de connître si le navire est coulé. True si le navire n'est pas coulé, false sinon
    public String nom;              //Variable qui permet de stocker le nom du navire
    public int [][] coordonnees;        //Variable qui stock les coordonnées du navire
    public int direction;               //Variable qui stock la direction du bateau. 0 s'il est à la horizontale 1 s'il est à la vertical.
    public int taille;                  //Variable qui stock la taille du bateau
    public int puissance;           //Variable qui stock la puissance de tir du navire
    public char lRef;               //Variable qui stock la lettre de référence du bateau
    public int nRef;                //Variable qui stock le numéro de référence du bateau
    
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
     * @param nPlateau Le numéro sur le plateau
     * @return Retourne la position dans la liste du navire
     */
    public static int pListeToNPlateau(char lRef, int nPlateau){
        
        if (lRef=='U') return nPlateau+1;//On déduit la position dans la liste
        if (lRef=='C') return nPlateau;
        if (lRef=='D') return nPlateau-2;
        if (lRef=='S') return nPlateau-5;
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
        for (int j=0; j<taille-1; j++){           //On parcour tous les états des coordonées
            if (coordonnees[j][2] == 1) return true;            //S'il y a au moins un état qui est à 1 on renvoie true
        }
        return false;           //Sinon on renvoit false
    }
}
