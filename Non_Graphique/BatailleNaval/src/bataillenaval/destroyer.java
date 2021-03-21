package bataillenaval;


public class destroyer extends Flotte {
    
    
    public destroyer(){
        etat = true;
        coordonnees = new int [taille][3];
        taille = 3;
        puissance = 1;
        l_ref = 'D';
        n_ref =17;
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
