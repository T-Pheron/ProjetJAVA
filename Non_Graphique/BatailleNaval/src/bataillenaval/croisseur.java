package bataillenaval;


public class croisseur extends Flotte{
    
    public Flotte croisseur;

    
    public croisseur(){
        etat =true;
        coordonnees = new int [taille][3];
        taille = 5;
        puissance = 4;
        l_ref = 'C';
        n_ref =9;
        croisseur = new Flotte();
    }
    
    public void ref_bateau (int nombre_ref){
        n_ref += nombre_ref;
    }
    
    public void tir(){
        
    }
    
    public void impact(int colonne, int ligne){
        
    }
    
    public void deplacement(char direction){
        
    }
}
