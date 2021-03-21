package bataillenaval;


public class Plateau {
    
    public Object [][][][] plateaudejeu = new Object[15][15][4][2];
    
    
    /**Constructeur du plateau de jeu.
    Il y a 4 étages pour le plateau de jeu de 15x15.<br>
    Les étages d'indices 0 et 1 sont réservées au joueur humain (joueur 0)<br>
        - Etage 0 : Elle est utilisée pour stocker l'emplacement de ses navires:<br>
            [y][x][numeroEtage][0] est utilisé pour la lettre qui identifie le navire (Legende : lettre dans la classe Flottes)<br>
            [y][x][numeroEtage][1] est utilisé pour stocker le numéro du navire afficher sur le plateau<br><br>
        
        - Etage 1 : Elle sert à stocker les endroit où on a tiré:<br>
            [y][x][numeroEtage][0] est utilisé pour stocker un numéro qui correspond au tir du joueur<br>
                0 On a jamais tiré sur cette case<br>
                1 On a déjà tiré sur cette case mais on a rien touché<br>
                2 On a déjà tiré sur cette case et on a touché un bateau<br><br>
    
    Les étages d'indice 2 et 3 sont réservés à l'IA (joueur 1)<br>
        - Etage 2 : Correspond à l'étage 0 pour l'IA<br>
        - Etage 3 : Correspond à l'étage 1 pour l'IA
    */
    public Plateau(){
        for (int i=0; i<15; i++){//On fait des boucles pour parcourir tous les étages du plateau de jeu
           for (int j=0; j<15; j++){
               for (int k=0; k<4; k++){
                    if (k==0 || k==2){//On différencie les étages où sont les navires et ceux avec les endroits où on lieu les tirs
                        for (int l=0; l<2; l++){
                            plateaudejeu[i][j][k][l]="_";//On met toutes les cases où on place les navires à la valeur par defaut
                        }
                    }
                    else {//On différencie les étages où sont les navires et ceux avec les endroits où on lieu les tirs
                        for (int l=0; l<2; l++){
                            plateaudejeu[i][j][k][l]="0";//On met les cases pour visualiser les cases touchées à la valeur par défaut
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
     * @param x La valeur de l'axe en x du plateau de jeu
     * @param y La valeur de l'axe en y du plateau de jeu
     * @param numeroEtage Le numéro de l'étage dans le plateau de jeu
     * @param numeroCase Le numéro de la case dans le plateau de jeu
     * @return Retourne la valeur présent à la position donnée
     */
    public Object get(int x, int y, int numeroEtage, int numeroCase){
        return plateaudejeu[y][x][numeroEtage][numeroCase];//On retourne la valeur à la position donné
    }
    
    
    //**************************************************************************
    /**Méthode qui modifie l'élément qui est à la position rentrer du plateau de jeu avec l'élement donné.
     * Permet de modifie un élément à une position du plateau de jeu avec un autre élément
     * @param x La valeur de l'axe x du plateau de jeu
     * @param y La valeur de l'axe y du plateau de jeu
     * @param numeroEtage Le numéro de l'étage dans la plateau de jeu
     * @param numeroCase Le numéro de la case dans le plateau de jeu
     * @param object L'élement qu'on soit metrre à la place
     */
    public void modification (int x, int y, int numeroEtage, int numeroCase, Object object){
        plateaudejeu[y][x][numeroEtage][numeroCase] = object;//On donne à la position donnée la valeur donnée en paramètre
    }
    
    
    //**************************************************************************
    /**Méthode qui permet d'échanger 2 positions dans le plateau de jeu
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param numeroEtage 
     */
    public void echange (int x1, int y1, int x2, int y2, int numeroEtage){

            Object stock1 = plateaudejeu[y1][x1][numeroEtage][0];
            Object stock2 = plateaudejeu[y1][x1][numeroEtage][1];
            
            if (numeroEtage == 0 || numeroEtage == 2) {
                plateaudejeu[y1][x1][numeroEtage][0]="_";
                plateaudejeu[y1][x1][numeroEtage][1]="_";
            }
            else{
                plateaudejeu[y1][x1][numeroEtage][0]="0";
                plateaudejeu[y1][x1][numeroEtage][1]="0";
            }
            
            plateaudejeu[y2][x2][numeroEtage][0]=stock1;
            plateaudejeu[y2][x2][numeroEtage][1]=stock2;
    }
    
    
    
    public static int numero_etage(int numero_joueur, int numero_grille){
        if (numero_joueur==0 && numero_grille==0) return 0;
        if (numero_joueur==0 && numero_grille==1) return 1;
        if (numero_joueur==1 && numero_grille==0) return 2;
        if (numero_joueur==1 && numero_grille==1) return 3;
        else {
            System.out.println("Erreur_num_etage!");
            return -1;
        }
    }
    
    //**************************************************************************
    //Variables utilisées pour modifier les couleurs du texte
    public final String RESET = "\u001B[0m";
    public final String NOIR = "\u001B[30m";
    public final String ROUGE = "\u001B[31m";
    public final String VERT = "\u001B[32m";
    public final String JAUNE = "\u001B[33m";
    public final String BLEU = "\u001B[34m";
    public final String VOILET = "\u001B[35m";
    public final String CYAN = "\u001B[36m";
    public final String BLANC = "\u001B[37m";
    //Variable utilisées pour modifier les couleurs de l'arriere plan
    public final String RESETAR = "\u001B[40m";
    public final String ROUGEAR = "\u001B[41m";
    public final String VERTAR = "\u001B[42m";
    public final String JAUNEAR = "\u001B[43m";
    public final String BLEUAR = "\u001B[44m";
    public final String VOILETAR = "\u001B[45m";
    public final String CYANAR = "\u001B[46m";
    public final String GRISAR = "\u001B[47m";
    
    
    //**************************************************************************
    //Méthode qui permet d'afficher une grille du plateau de jeu
    public void afficher(int numero_joueur, int numero_grille){
        
        int numero_etage= numero_etage(numero_joueur,numero_grille);
        
        
        if (numero_etage == 0 || numero_etage == 2){
            System.out.println(GRISAR +BLANC +"          Voici la grille de vos bateaux        "+RESETAR +RESET);
            System.out.println(JAUNEAR +BLANC +"  | A| B| C| D| E| F| G| H| I| J| K| L| M| N| O|"+RESETAR +RESET);
        
            for (int i=0; i<15; i++){
            
                if (i<9) System.out.print(JAUNEAR +BLANC +" "+(i+1)+"|"+RESETAR +RESET);
                else System.out.print(JAUNEAR +BLANC +(i+1)+"|"+RESETAR +RESET);
            
                for (int j=0; j<15; j++){
               
                    if (numero_joueur==0){
                        if (plateaudejeu[i][j][3][0]=="1"){
                            System.out.print(CYANAR + ROUGE + "XX" + RESETAR + RESET +"|");
                            continue;
                        }
                        if (plateaudejeu[i][j][3][0]=="2"){
                            System.out.print(ROUGEAR + BLANC + "XX" + RESETAR + RESET +"|");
                            continue;
                        }
                    }
                    else {
                        if (plateaudejeu[i][j][1][0]=="1"){
                            System.out.print(CYANAR + ROUGE + "XX" + RESETAR + RESET +"|");
                            continue;
                        }
                        if (plateaudejeu[i][j][1][0]=="2"){
                            System.out.print(ROUGEAR + BLANC + "XX" + RESETAR + RESET +"|");
                            continue;
                        }
                    }
                    
                    if (plateaudejeu[i][j][numero_etage][0]=="_"){
                        System.out.print("__|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]==(Object) 'U'){
                        System.out.print(ROUGEAR + BLANC + plateaudejeu[i][j][numero_etage][0] +""+ plateaudejeu[i][j][numero_etage][1]+RESETAR +RESET+"|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]==(Object) 'C'){
                        System.out.print(VOILETAR + BLANC + plateaudejeu[i][j][numero_etage][0] +""+ plateaudejeu[i][j][numero_etage][1]+RESETAR +RESET+"|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]==(Object) 'D'){
                        System.out.print(BLEUAR + BLANC + plateaudejeu[i][j][numero_etage][0] +""+ plateaudejeu[i][j][numero_etage][1]+RESETAR +RESET+"|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]==(Object) 'S'){
                        System.out.print(VERTAR + BLANC + plateaudejeu[i][j][numero_etage][0] +""+ plateaudejeu[i][j][numero_etage][1]+RESETAR +RESET+"|");
                    }
                } 
                System.out.println();
            }
        }
        
        
        
        if (numero_etage == 1 || numero_etage == 3){
            System.out.println(GRISAR+ BLANC +"       Voici la grille de où vous avez tiré     ");
            System.out.println(BLEUAR + BLANC +"  | A| B| C| D| E| F| G| H| I| J| K| L| M| N| O|"+RESETAR +RESET);
        
            for (int i=0; i<15; i++){
            
                if (i<9) System.out.print(BLEUAR+ BLANC +" "+(i+1)+"|"+RESETAR +RESET);
                else System.out.print(BLEUAR+ BLANC +(i+1)+"|"+RESETAR +RESET);
            
                for (int j=0; j<15; j++){
               
                    if (plateaudejeu[i][j][numero_etage][0]=="0"){
                        System.out.print("__|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]=="1"){
                        System.out.print(CYANAR + "XX" + RESETAR+ "|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]=="2"){
                        System.out.print(ROUGEAR + "XX" + RESETAR +"|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]=="3"){
                        System.out.print(JAUNEAR + "__" + RESETAR +"|");
                    }
                    if (plateaudejeu[i][j][numero_etage][0]=="4"){
                        System.out.print(JAUNEAR + "--" + RESETAR +"|");
                    }
                } 
                System.out.println();
            }
        }
    }
    
    public boolean verif_placement(int numero_joueur, int x, int y, int direction, int taille,int n_ref, char l_ref){
        
        int numero_etage= numero_etage(numero_joueur,0);
        
        if (direction==0 && x+taille-1>=15)return false;
        if (direction==1 && y+taille-1>=15)return false;
        
        
        if (direction == 0){//Cas vérification horizontale
            for (int i=0; i<taille; i++){
                if (plateaudejeu[y][x+i][numero_etage][0]!="_" && (plateaudejeu[y][x+i][numero_etage][1]!=(Object) Flotte.n_ref_plateau(l_ref, n_ref) || plateaudejeu[y][x+i][numero_etage][0]!=(Object) l_ref)){
                    return false;
                }
            }
        }
        else {//Cas vérification vertical
            for (int i=0; i<taille; i++){
                if (plateaudejeu[y+i][x][numero_etage][0]!="_" && (plateaudejeu[y+i][x][numero_etage][1]!=(Object) Flotte.n_ref_plateau(l_ref, n_ref) || plateaudejeu[y+i][x][numero_etage][0]!=(Object) l_ref)){
                    return false;
                }
            }
        }
        return true;
    }
}
