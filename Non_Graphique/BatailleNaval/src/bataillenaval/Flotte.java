package bataillenaval;


public class Flotte {
    
    public boolean etat = false;
    public int [][] coordonnees;
    public int direction;//La direction du bateau. 0 s'il est à la horizontale 1 s'il est à la vertical.
    public int taille = 0;
    public int puissance = 0;
    public char l_ref = 'A';
    public int n_ref = 0;
    
    public Flotte(){
        
    }
    
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
    
    
    public static int n_ref_liste(char l_ref, int n_ref){
        
        if (l_ref=='U') return n_ref-1;
        if (l_ref=='C') return n_ref;
        if (l_ref=='D') return n_ref+2;
        if (l_ref=='S') return n_ref+5;
        return -1;
    }
}
