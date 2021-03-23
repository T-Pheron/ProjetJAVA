package bataillenaval.model;

import bataillenaval.view.Affichage;


public class Plateau {
    
    public Object [][][][] plateauDeJeu = new Object[15][15][4][2];             //Variable pour le stockage les différentes informations du plateau de jeu
    
    
    //**************************************************************************
    /**
     * Constructeur du plateau de jeu.
     * Il y a 4 étages pour le plateau de jeu de 15x15.<br>
     * Les étages d'indices 0 et 1 sont réservées au joueur humain (joueur 0)<br>
     *     - Etage 0 : Elle est utilisée pour stocker l'emplacement de ses navires:<br>
     *         [y][x][numeroEtage][0] est utilisé pour la lettre qui identifie le navire (Legende : lettre dans la classe Flottes)<br>
     *         [y][x][numeroEtage][1] est utilisé pour stocker le numéro du navire afficher sur le plateau<br><br>
     *     
     *     - Etage 1 : Elle sert à stocker les endroit où on a tiré:<br>
     *         [y][x][numeroEtage][0] est utilisé pour stocker un numéro qui correspond au tir du joueur<br>
     *             0 On a jamais tiré sur cette case<br>
     *             1 On a déjà tiré sur cette case mais on a rien touché<br>
     *             2 On a déjà tiré sur cette case et on a touché un bateau<br><br>
     * 
     * Les étages d'indice 2 et 3 sont réservés à l'IA (joueur 1)<br>
     *     - Etage 2 : Correspond à l'étage 0 pour l'IA<br>
     *     - Etage 3 : Correspond à l'étage 1 pour l'IA
     */
    public Plateau(){
        for (int i=0; i<15; i++){               //On fait des boucles pour parcourir tous les étages du plateau de jeu
           for (int j=0; j<15; j++){
               for (int k=0; k<4; k++){
                    if (k==0 || k==2){              //On différencie les étages où sont les navires et ceux avec les endroits où on lieu les tirs
                        for (int l=0; l<2; l++){
                            plateauDeJeu[i][j][k][l]="_";               //On met toutes les cases où on place les navires à la valeur par defaut
                        }
                    }
                    else {               //On différencie les étages où sont les navires et ceux avec les endroits où on lieu les tirs
                        for (int l=0; l<2; l++){
                            plateauDeJeu[i][j][k][l]="0";               //On met les cases pour visualiser les cases touchées à la valeur par défaut
                        }
                    }
                }
            } 
        }
    }
    
    
    //**************************************************************************
    /**
     * Méthode pour connaître la valeur d'un élément dans le tableau.
     * Cela permet renvoyer la valeur d'une position donnée dans le plateau de jeu sans la modifier.
     * @param x La valeur de l'axe en x de la grille de jeu
     * @param y La valeur de l'axe en y de la grille de jeu
     * @param numeroEtage Le numéro de l'étage dans le plateau de jeu
     * @param numeroCase Le numéro de la case dans le plateau de jeu
     * @return Retourne la valeur présent à la position donnée
     */
    public Object get(int x, int y, int numeroEtage, int numeroCase){
        return plateauDeJeu[y][x][numeroEtage][numeroCase];               //On retourne la valeur à la position donné
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui modifie l'élément qui est à la position rentrer du plateau de jeu avec l'élement donné.
     * Permet de modifie un élément à une position du plateau de jeu avec un autre élément.
     * @param x La valeur de l'axe x de la grille de jeu
     * @param y La valeur de l'axe y de la grille de jeu
     * @param numeroEtage Le numéro de l'étage dans la plateau de jeu
     * @param numeroCase Le numéro de la case dans le plateau de jeu
     * @param object L'élement qu'on soit metrre à la place
     */
    public void modification (int x, int y, int numeroEtage, int numeroCase, Object object){
        plateauDeJeu[y][x][numeroEtage][numeroCase] = object;               //On donne à la position donnée la valeur donnée en paramètre
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'échanger 2 positions dans le plateau de jeu.
     * La méthode remet les valeurs par défauts à la position 1 et donne à la position 2 les valeurs de la position 1.
     * @param x1 La valeur de l'axe x de la position 1 de la grille de jeu
     * @param y1 La valeur de l'axe y de la position 1 de la grille de jeu
     * @param x2 La valeur de l'axe x de la position 2 de la grille de jeu
     * @param y2 La valeur de l'axe y de la position 2 de la grille de jeu
     * @param numeroEtage Le numéro de l'étage qu'on souhaite modifier
     */
    public void deplacement (int x1, int y1, int x2, int y2, int numeroEtage){

            Object stocke1 = plateauDeJeu[y1][x1][numeroEtage][0];               //On stocke les 2 cases du plateau de jeu
            Object stocke2 = plateauDeJeu[y1][x1][numeroEtage][1];
            
            if (numeroEtage == 0 || numeroEtage == 2) {                 //On vérifie sur quelle type de grille on est.
                plateauDeJeu[y1][x1][numeroEtage][0]="_";               //On remet les valeurs par défaut pour ce type de grille
                plateauDeJeu[y1][x1][numeroEtage][1]="_";
            }
            else{
                plateauDeJeu[y1][x1][numeroEtage][0]="0";               //On remet les valeurs par défaut pour ce type de grille
                plateauDeJeu[y1][x1][numeroEtage][1]="0";
            }
            
            plateauDeJeu[y2][x2][numeroEtage][0]=stocke1;                //On donne à la position 2 les valeurs de la position 1
            plateauDeJeu[y2][x2][numeroEtage][1]=stocke2;
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui renvoie le numéro de l'étage qu'on souhaite utiliser en fonction du numéro du joueur et le numéro de la grille.
     * On donne à cette méthode le numéro de joueur ainsi que le numéro de la grille et la méthode 
     * nous renvoie le numéro d'étage corespondant dans le plateau de jeu.
     * @param numeroJoueur Le numéro du joueur
     * @param numeroGrille Le numéro de la grille
     * @return Le numéro d'étage dans le plateau de jeu
     */
    public static int numeroEtage(int numeroJoueur, int numeroGrille){
        if (numeroJoueur==0 && numeroGrille==0) return 0;               //On déduit le numéro d'étage en fonction di numéro de joueur et le numéro de la grille souhaité
        else if (numeroJoueur==0 && numeroGrille==1) return 1;
        else if (numeroJoueur==1 && numeroGrille==0) return 2;
        else if (numeroJoueur==1 && numeroGrille==1) return 3;
        else {
            System.out.println("Erreur_num_etage!");                //En cas d'erreur d'entrer du numéro joueur ou du numéro de grille, on renvoie une erreur
            return 4;                   //On renvoi 4 en cas d'erreur, ça permet d'arrêter le programme ou ça créé une erreur d'index dans le plateau de jeu  
        }
    }
    
    
    
    //**************************************************************************
    /**
     * Méthode qui vérif qu'un navire peut-être mis à un emplacement.
     * On rentrant les coordonnées d'un navire et sa taille, la méthode renvoie true si le placement est possible false sinon.
     * @param numeroJoueur Le numéro du joueur
     * @param x La valeur de l'axe x dans la grille du plateau de jeu 
     * @param y La valeur de l'axe y dans la grille du plateau de jeu 
     * @param direction La direction dans le plateaud de jeu (0; Horizontal, 1: Vertical)
     * @param taille La taille du navire
     * @param nPlateau La numéro du navire stocké dans le plateau
     * @param lRef La lettre de réfrence du navire
     * @return Retourne true si le placement est possible, false sinon
     */
    public boolean verifEmplacementVide(int numeroJoueur, int x, int y, int direction, int taille,int nPlateau, char lRef){
        
        int numeroEtage= numeroEtage(numeroJoueur,0);               //On calcul l'étage dans le plateau de jeu en fonction de numéro du joueur
        
        if (direction==0 && x+taille-1>=15)return false;               //Si le bateau dépasse la grille de jeu, on renvoie false
        if (direction==1 && y+taille-1>=15)return false;
        
        
        if (direction == 0){               //Si la direction est horizontale
            for (int i=0; i<taille; i++){               //On fait une boucle pour vérifier chaque emplacment où doit se placer le navire
                /*On vérifie que l'emplacment est vide et que la lettre de référence  et le numéro de référence du navire est différent*/
                /*C'est pour évité que la méthode renvoir false dans le cas où on souhaite bouger le navire */
                if (plateauDeJeu[y][x+i][numeroEtage][0]!="_" && (plateauDeJeu[y][x+i][numeroEtage][1]!=(Object) Flotte.pListeToNPlateau(lRef, nPlateau) || plateauDeJeu[y][x+i][numeroEtage][0]!=(Object) lRef)){
                    return false;               //Si c'est pas le cas, on renvoie false
                }
            }
        }
        else {               //Si la direction est vertical
            for (int i=0; i<taille; i++){           //On fait une boucle pour vérifier chaque emplacment où doit se placer le navire
                /*On vérifie que l'emplacment est vide et que la lettre de référence  et le numéro de référence du navire est différent*/
                /*C'est pour évité que la méthode renvoir false dans le cas où on souhaite bouger le navire */
                if (plateauDeJeu[y+i][x][numeroEtage][0]!="_" && (plateauDeJeu[y+i][x][numeroEtage][1]!=(Object) Flotte.pListeToNPlateau(lRef, nPlateau) || plateauDeJeu[y+i][x][numeroEtage][0]!=(Object) lRef)){
                    return false;               //Si c'est pas le cas, on renvoie false
                }
            }
        }
        return true;               //Si l'emplacement a passer tous les tests on renvoie true
    }
}