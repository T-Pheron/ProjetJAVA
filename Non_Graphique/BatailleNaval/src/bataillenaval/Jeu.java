package bataillenaval;

import java.util.*;



public class Jeu {
    
    static Plateau plateau_de_jeu = new Plateau();
    public List <Flotte> flotte_joueur0 ;
    public List <Flotte> flotte_joueur1 ;
    
    public Jeu(){
        
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
    
    
    
    
    public void initialiser_jeu(){
        flotte_joueur0 = nouvelle_flotte();
        flotte_joueur1 = nouvelle_flotte();
        
        placement_flotte(flotte_joueur0, 0);
        placement_flotte(flotte_joueur1, 1);
        
        plateau_de_jeu.afficher(1, 0);
        
        int ok= bouger_navire(flotte_joueur1,1 );
        plateau_de_jeu.afficher(1, 0);
    }
    
    public List<Flotte> nouvelle_flotte(){
        
        List <Flotte> flotte = new ArrayList<Flotte>();
        
        flotte.add(new cuirasse());
        
        flotte.add(new croisseur());
        flotte.add(new croisseur());
        
        for (int i=3; i<6; i++) flotte.add(new destroyer());
        
        for (int i=6; i<10; i++) flotte.add(new sous_marin());
        
        return flotte;
    }

    
    public void placement_flotte(List<Flotte> flotte, int numero_joueur){
        
        for (int i=0; i<10; i++){
            int x, y, direction;
            
            x= (int) (Math.random()*14);
            y= (int) (Math.random()*14);
            direction= (int) (Math.random()*2);
            
            if (plateau_de_jeu.verif_placement(numero_joueur, x, y, direction, flotte.get(i).taille, flotte.get(i).n_ref, flotte.get(i).l_ref)==true){
                placement_bateau(flotte, x, y, i, numero_joueur, direction);
            }
            else{
                i--;
            }
        }
    }
    
    public void placement_bateau(List<Flotte> flotte,int x, int y,int i, int numero_joueur, int direction){
        
        int numero_etage = Plateau.numero_etage(numero_joueur,0);
        int [][] coordonnees = new int [flotte.get(i).taille][3];
        
        int n_ref = flotte.get(i).n_ref;
        char l_ref = flotte.get(i).l_ref;
        
        if (direction ==0){
            for (int j=0; j<flotte.get(i).taille ; j++){
                plateau_de_jeu.modification(x+j, y, numero_etage, 0, l_ref);
                plateau_de_jeu.modification(x+j, y, numero_etage, 1, Flotte.n_ref_plateau(l_ref, i));
                coordonnees[j][0]=x+j;
                coordonnees[j][1]=y;
                coordonnees[j][2]=1;
            }
        }
        
        if (direction ==1){
            for (int j=0; j<flotte.get(i).taille ; j++){
                plateau_de_jeu.modification(x, y+j, numero_etage, 0, l_ref);
                plateau_de_jeu.modification(x, y+j, numero_etage, 1, Flotte.n_ref_plateau(l_ref, i));
                coordonnees[j][0]=x;
                coordonnees[j][1]=y+j;
                coordonnees[j][2]=1;
            } 
        }
        flotte.get(i).coordonnees=coordonnees;
        flotte.get(i).direction=direction;
    }
    
    public int bouger_navire(List<Flotte> flotte,int numero_joueur){
        
        int n_ref, max_bateau=0;
        Object choix=0;
        String saisie;
        char l_ref;
        boolean continuer=false;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("Veuillez entrer la lette du bateau que vous voulez bouger :");
            l_ref= sc.next().charAt(0);
            l_ref=Menu.convertirMinuscules(l_ref);
        
            while (l_ref!='U'&&l_ref!='C'&&l_ref!='D'&&l_ref!='S'){
                System.out.println("Erreur_bouger_navire! Cette lettre ne fait pas parti des choix.");
                System.out.println("Veuillez entrer la lette du bateau que vous voulez bouger :");
                l_ref= sc.next().charAt(0);
                l_ref=Menu.convertirMinuscules(l_ref);
            }
            
            
            System.out.println("Veuillez entrer le numero du bateau que vous voulez bouger :");
            n_ref= sc.nextInt();
        
            if (l_ref=='U') max_bateau=1;
            if (l_ref=='C') max_bateau=2;
            if (l_ref=='D') max_bateau=3;
            if (l_ref=='S') max_bateau=4;
        
        
            while (n_ref<1||n_ref>max_bateau){
                System.out.println("Erreur_bouger_navire! Ce numéro ne fait pas parti des choix.");
                System.out.println("Veuillez entrer le numero du bateau que vous voulez bouger :");
                n_ref= sc.nextInt();
                }
            
            n_ref=Flotte.n_ref_liste(l_ref, n_ref);
            
            if (flotte.get(n_ref).etat==false){
                System.out.println("Ce bateau a déjà été coulé!");
                System.out.println("Voulez vous tentez de déplacer un autre bateau ?");
                System.out.println("1.OUI   2.NON");
                choix = sc.nextInt();
                
                switch((int)choix){
                    case 1: continue;
                    case 2: return 2;
                    
                    default : System.out.println(ROUGE+"Erreur_placement_bateau! "+ RESET + "Le choix rentrer n'est pas disponible. \nOn relance votre tour."); return 2;
                }
            }
            else continuer=true;
            
            for (int i=0; i<flotte.get(n_ref).taille; i++){
                if (flotte.get(n_ref).coordonnees[i][2]==0){
                    
                    System.out.println("Ce bateau a déjà été touché!");
                    System.out.println("Voulez vous tentez de déplacer un autre bateau ?");
                    System.out.println("1.OUI   2.NON");
                    choix = sc.nextInt();
                
                    switch((int)choix){
                        case 1: continue;
                        case 2: return 2;
                    
                        default : System.out.println(ROUGE+"Erreur_placement_bateau! "+ RESET + "Le choix rentrer n'est pas disponible. \nOn relance votre tour."); return 2;
                    }
                } 
            }    
        }while(continuer==false);

        
        int  [] posibilite = new int[4];
        
        if (flotte.get(n_ref).direction == 0){
            int transfere;
            
            transfere = flotte.get(n_ref).coordonnees[0][0];
            if (transfere==0 || transfere ==14) posibilite[0]=90;
            else posibilite[0]= transfere - 1;
            
            transfere = flotte.get(n_ref).coordonnees[flotte.get(n_ref).taille - 1][0];
            if (transfere==0 || transfere ==14) posibilite[1]=90;
            else posibilite[1]= transfere + 1;
            
            
            transfere = flotte.get(n_ref).coordonnees[0][1];
            
            switch (transfere) {
                case 0:
                    posibilite[2]= 90;
                    posibilite[3]= transfere +1;
                    break;
                case 14:
                    posibilite[3]= 90;
                    posibilite[2]= transfere -1;
                    break;
                default:
                    posibilite[3]= transfere +1;
                    posibilite[2]= transfere -1;
                    break;
            }
        }
        
        if (flotte.get(n_ref).direction == 1){
            int transfere;
            
            transfere = flotte.get(n_ref).coordonnees[0][1];
            if (transfere==0 || transfere ==14) posibilite[2]= 90;
            else posibilite[2]= transfere - 1 ;
            
            transfere = flotte.get(n_ref).coordonnees[flotte.get(n_ref).taille - 1][1];
            if (transfere==0 || transfere ==14) posibilite[3]= 90;
            else posibilite[3]= transfere + 1;
            
            
            transfere = flotte.get(n_ref).coordonnees[0][0];
            
            switch (transfere) {
                case 0:
                    posibilite[0]= 90;
                    posibilite[1]= transfere + 1;
                    break;
                case 14:
                    posibilite[1]= 90;
                    posibilite[0]= transfere - 1;
                    break;
                default:
                    posibilite[1]= transfere + 1;
                    posibilite[0]= transfere - 1;
                    break;
            }
        }
        
        
        if (flotte.get(n_ref).direction==0){
            if (posibilite[0]!= 90){
                if (plateau_de_jeu.verif_placement(numero_joueur, posibilite[0], flotte.get(n_ref).coordonnees[0][1], 0, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[0]=90;
                }
            }
            if (posibilite[1]!= 90){
                if (plateau_de_jeu.verif_placement(numero_joueur, posibilite[1]- flotte.get(n_ref).taille +1 , flotte.get(n_ref).coordonnees[0][1], 0, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[1]=90;
                }
            }
            if (posibilite[2]!= 90){
                if (plateau_de_jeu.verif_placement(numero_joueur, flotte.get(n_ref).coordonnees[0][0], posibilite[2], 0, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[2]=90;
                }
            }
            if (posibilite[3]!= 90){
                if (plateau_de_jeu.verif_placement(numero_joueur, flotte.get(n_ref).coordonnees[0][0], posibilite[3], 0, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[3]=90;
                }
            }
        }
        
        if (flotte.get(n_ref).direction==1){
            if (posibilite[0] != 90){
                if(plateau_de_jeu.verif_placement(numero_joueur, posibilite[0], flotte.get(n_ref).coordonnees[0][1], 1, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[0]=90;
                }
            }
            if (posibilite[1] != 90){
                if (plateau_de_jeu.verif_placement(numero_joueur, posibilite[1], flotte.get(n_ref).coordonnees[0][1], 1, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[1]=90;
                }
            }
            
            if (posibilite[2] != 90){
                if (plateau_de_jeu.verif_placement(numero_joueur, flotte.get(n_ref).coordonnees[0][0], posibilite[2], 1, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[2]=90;
                }
            }
            if (posibilite[3] != 90){
                if (plateau_de_jeu.verif_placement(numero_joueur, flotte.get(n_ref).coordonnees[0][0], posibilite[3]-  flotte.get(n_ref).taille +1 , 1, flotte.get(n_ref).taille, n_ref, flotte.get(n_ref).l_ref)==false){
                    posibilite[3]=90;
                }
            }
        }
        
        if (posibilite[0]==90 && posibilite[1]==90 && posibilite[2]==90 && posibilite[3]==90){
            System.out.println("Ce bateau de ne peut pas être déplacé. \nOn relance votre tour");
            return 2;
        }
        
        Scanner sc1 = new Scanner(System.in);
        
        System.out.println("Entrer la lettre de la colonne ou le numéro de ligne du nouvelle emplacement du bateau");
        saisie = sc1.nextLine();
        char saisieChar='Z';
        int saisieInt=0;
        
        boolean verif=false, verifEntier=false;
        
        if (saisie.equals("A") || saisie.equals("B") || saisie.equals("C") || saisie.equals("D") || saisie.equals("E")|| saisie.equals("F") || saisie.equals("G") || saisie.equals("H") || saisie.equals("I") || saisie.equals("J") || saisie.equals("K") || saisie.equals("L") || saisie.equals("M") || saisie.equals("N") || saisie.equals("O")) {
            verif = true;
            saisieChar = saisie.charAt(0);
        }
        if (saisie.equals("a") || saisie.equals("b") || saisie.equals("c") || saisie.equals("d") || saisie.equals("e")|| saisie.equals("f") || saisie.equals("g") || saisie.equals("h") || saisie.equals("i") || saisie.equals("j") || saisie.equals("k") || saisie.equals("l") || saisie.equals("m") || saisie.equals("n") || saisie.equals("o")) {
            verif = true;
            saisieChar = saisie.charAt(0);
            saisieChar = Menu.convertirMinuscules(saisieChar);
        }
        if (saisie.equals("1") || saisie.equals("2") || saisie.equals("3") || saisie.equals("4") || saisie.equals("5")|| saisie.equals("6") || saisie.equals("7") || saisie.equals("8") || saisie.equals("9") || saisie.equals("10") || saisie.equals("11") || saisie.equals("12") || saisie.equals("13") || saisie.equals("14") || saisie.equals("15")){
            verif = true; verifEntier=true;
            saisieInt = Integer.parseInt(saisie);
        }
        
        while(verif==false){
            System.out.println(ROUGE + "Erreur !"+RESET+"\nLa saisie ne fait pas partie des choix possible");
            System.out.println("Veuillez resaisir le chiffre de la ligne ou la lettre de la colonne où vous voulez déplacer le bateau");
            saisie = sc.nextLine();
            
            if (saisie.equals("A") || saisie.equals("B") || saisie.equals("C") || saisie.equals("D") || saisie.equals("E")|| saisie.equals("F") || saisie.equals("G") || saisie.equals("H") || saisie.equals("I") || saisie.equals("J") || saisie.equals("K") || saisie.equals("L") || saisie.equals("M") || saisie.equals("N") || saisie.equals("O")) {
                verif = true;
                saisieChar = saisie.charAt(0);
            }
            if (saisie.equals("a") || saisie.equals("b") || saisie.equals("c") || saisie.equals("d") || saisie.equals("e")|| saisie.equals("f") || saisie.equals("g") || saisie.equals("h") || saisie.equals("i") || saisie.equals("j") || saisie.equals("k") || saisie.equals("l") || saisie.equals("m") || saisie.equals("n") || saisie.equals("o")) {
                verif = true;
                saisieChar = saisie.charAt(0);
                saisieChar = Menu.convertirMinuscules(saisieChar);
            }
            if (saisie.equals("1") || saisie.equals("2") || saisie.equals("3") || saisie.equals("4") || saisie.equals("5")|| saisie.equals("6") || saisie.equals("7") || saisie.equals("8") || saisie.equals("9") || saisie.equals("10") || saisie.equals("11") || saisie.equals("12") || saisie.equals("13") || saisie.equals("14") || saisie.equals("15")){
                verif = true; verifEntier=true;
                saisieInt = Integer.parseInt(saisie);
            }
        }
        
        
        if (flotte.get(n_ref).direction==0){
            if (verifEntier==true){
                saisieInt--;
                if (saisieInt==posibilite[3]){
                    for (int i=0; i<flotte.get(n_ref).taille;i++){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1], flotte.get(n_ref).coordonnees[i][0] , flotte.get(n_ref).coordonnees[i][1]+ 1, Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][0] ++;
                    }
                }
                else if (saisieInt==posibilite[2]){
                    for (int i=0; i<flotte.get(n_ref).taille;i++){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1], flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1] - 1, Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][2] --;
                    }
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                    System.out.println("On relance votre tour");
                }
            }
            else {
                
                if (saisieChar == (char) (posibilite[1]+65)){
                    for (int i=flotte.get(n_ref).taille - 1; i>=0;i--){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1], flotte.get(n_ref).coordonnees[i][0] + 1, flotte.get(n_ref).coordonnees[i][1], Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][0] ++;
                    }
                }
                else if (saisieChar ==(char) (posibilite[0])+65){
                    for (int i=0; i<flotte.get(n_ref).taille;i++){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1], flotte.get(n_ref).coordonnees[i][0] - 1, flotte.get(n_ref).coordonnees[i][1], Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][0] --;
                    }
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                    System.out.println("On relance votre tour");
                }
            }
        }
        
        
        if (flotte.get(n_ref).direction==1){
            if (verifEntier==true){
                saisieInt--;
                if (saisieInt==posibilite[3]){
                    for (int i=flotte.get(n_ref).taille - 1; i>=0;i--){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1] , flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1]+1, Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][0] ++;
                    }
                }
                else if (saisieInt==posibilite[2]){
                    for (int i=0; i<flotte.get(n_ref).taille;i++){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1], flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1]- 1, Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][2] --;
                    }
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                    System.out.println("On relance votre tour");
                }
            }
            else {
                
                if (saisieChar == (char) (posibilite[1]+65)){
                    for (int i=0; i<flotte.get(n_ref).taille;i++){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1], flotte.get(n_ref).coordonnees[i][0]+1 , flotte.get(n_ref).coordonnees[i][1], Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][0] ++;
                    }
                }
                else if (saisieChar ==(char) (posibilite[0])+65){
                    for (int i=0; i<flotte.get(n_ref).taille;i++){
                        plateau_de_jeu.echange(flotte.get(n_ref).coordonnees[i][0], flotte.get(n_ref).coordonnees[i][1], flotte.get(n_ref).coordonnees[i][0]-1, flotte.get(n_ref).coordonnees[i][1], Plateau.numero_etage(numero_joueur, 0));
                        flotte.get(n_ref).coordonnees[i][0] --;
                    }
                }
                else {
                    System.out.println(ROUGE +"Erreur!"+RESET +"\nCe déplacement n'est pas possible");
                    System.out.println("On relance votre tour");
                }
            }
        }
        return 2;   
    }


    public void victoire(){
        for (int i=0; i<10; i++){
            boolean etat0 = flotte_joueur0.get(i).etat;
            if (etat0=true){
                break;
            }
            if (i==9){
                System.out.println(ROUGE+"VICTOIRE DE L'ORDI !!\nT'ES NUL PD"+RESET);

            }
        }
        for (int i=0; i<10; i++){
            boolean etat1 = flotte_joueur1.get(i).etat;
            if (etat1=true){
                break;
            }
            if (i==9){
                System.out.println(ROUGE+"VICTOIRE JOUEUR !!\n BIEN JOUE CA GASSON"+RESET);

            }
        }
        
    }
}
