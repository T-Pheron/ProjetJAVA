package bataillenaval;


public abstract class Flotte {
    
    public boolean etat = false;
    public int [][] coordonnees;
    public int direction;//La direction du bateau. 0 s'il est à la horizontale 1 s'il est à la vertical.
    public int taille = 0;
    public int puissance = 0;
    public char l_ref = 'A';
    public int n_ref = 0;
    
    public Flotte(){
        
    }
    
    public abstract int tir();
    
    public static int n_ref_plateau(char l_ref, int i){
        
        if (l_ref=='U') return i+1;
        if (l_ref=='C') return i;
        if (l_ref=='D') return i-2;
        if (l_ref=='S') return i-5;
        return 0;
    }
    
    public static int n_ref_flotte(char l_ref, int n_ref){
        
        if (l_ref=='U') return n_ref;
        if (l_ref=='C') return n_ref+9;
        if (l_ref=='D') return n_ref+19;
        if (l_ref=='S') return n_ref+29;
        return 0;
    }
    
    
    public static int nPlateauToPListe(char lRef, int nPlateau){
        
        if (lRef=='U') return nPlateau-1;
        if (lRef=='C') return nPlateau;
        if (lRef=='D') return nPlateau+2;
        if (lRef=='S') return nPlateau+5;
        return -1;
    }
    
    public boolean bateau_vivant(){
        for (int j=0; j<taille; j++){
            if (coordonnees[j][3] == 1) return true;
        }
        return false;
    }
}
