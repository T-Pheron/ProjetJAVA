package bataillenaval;


public class cuirasse extends Flotte{
    
    public Flotte cuirrasse;
    
    public cuirasse(){
        etat = true;
        cuirrasse = new Flotte();
        coordonnees = new int [taille][3];
        taille = 7;
        puissance = 9;
        l_ref = 'U';
        n_ref =1;
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
