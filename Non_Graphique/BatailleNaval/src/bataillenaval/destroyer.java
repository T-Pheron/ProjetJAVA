package bataillenaval;


public class destroyer extends Flotte {
    
    public Flotte destroyer;
    
    public destroyer(){
        etat = true;
        destroyer = new Flotte();
        coordonnees = new int [taille][3];
        taille = 3;
        puissance = 1;
        l_ref = 'D';
        n_ref =17;
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
