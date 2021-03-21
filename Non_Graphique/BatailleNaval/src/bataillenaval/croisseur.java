package bataillenaval;


public class croisseur extends Flotte{


    
    public croisseur(){
        etat =true;
        coordonnees = new int [taille][3];
        taille = 5;
        puissance = 4;
        l_ref = 'C';
        n_ref =9;
    }
    
    public void ref_bateau (int nombre_ref){
        n_ref += nombre_ref;
    }
    
    @Override
    public int tir(){
        return 0;
    }
    
    public void impact(int colonne, int ligne){
        
    }
    
    public void deplacement(char direction){
        
    }
}
