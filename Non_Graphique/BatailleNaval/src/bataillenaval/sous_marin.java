package bataillenaval;


public class sous_marin extends Flotte {

    
    public sous_marin(){
        etat =true;
        coordonnees = new int [taille][3];
        taille = 1;
        puissance = 1;
        l_ref = 'S';
        n_ref =24;
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
