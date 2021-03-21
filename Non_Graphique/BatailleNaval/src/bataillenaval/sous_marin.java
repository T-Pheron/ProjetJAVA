package bataillenaval;


public class sous_marin extends Flotte {
    
    public Flotte sous_marin;
    
    public sous_marin(){
        etat =true;
        coordonnees = new int [taille][3];
        taille = 1;
        puissance = 1;
        l_ref = 'S';
        n_ref =24;
        sous_marin = new Flotte();
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
