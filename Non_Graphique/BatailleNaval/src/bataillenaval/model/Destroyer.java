package bataillenaval.model;

import bataillenaval.controller.Jeu;
import bataillenaval.view.Affichage;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class Destroyer extends Flotte{
    
    //**************************************************************************
    private static final long serialVersionUID = -2188496570477313059L;         //Runtine définie par le logiciel
    private final Scanner scDestroyer = new Scanner(System.in);         //On initialise une varible de type scanner pour récupérer les saisis de l'utilisateur


    //**************************************************************************
    /**
     * Constructeur de la classe Destroyer.
     * Il permet de créer un un navire de type destroyer. 
     */
    public Destroyer(){
        etat = true;            //On met l'état du navire à true
        nom = "destroyer";          //On donne au navire son nom
        taille = 3;         //On stock la taille du navire
        coordonnees = new int [taille][3];           //Variable qui stocke les coordonnées du navire. 0 pour une coordonnées qui a été touché, 1 pour une coordonnées non touché.
        puissance = 1;          //On stocke la puissance du navire 
        lRef = 'D';         //On stocke la lettre de référence du navire
        nRef =17;           //On donne un numéro de référence par défaut au navire
    }
    

    /**
     * Méthode qui permet de donner un numéro de référence unique à un destroyer.
     * D'après la position dans la liste Destroyer à un numéro de référence unique.
     * @param pListe 
     */
    @Override
    public void nRef (int pListe){
        nRef += pListe;
    }
    
    
    /**
     * Méthode à un navire d'effectuer un tir.
     * La méthode permet de demander des coordonnées de tir et d'effectuer un tir.
     * @return 1 Si tout c'est bien déroulé, 0 en cas de problème
     * @throws InterruptedException 
     */
    @Override
    public int tir()throws InterruptedException{
        
        if (premierTire==true){
            char xTireFuseeChar='@';         //On declare la variable par defaut avec un @
            int yTireFusee;
            yTireFusee= -1;          //On declare la variable par defaut avec un -1
            System.out.println("Il s'agit du premier tir de ce destroyer");
            System.out.println("La munition tirée est donc une fusée éclairante");
            /*Récupération des coordonées***********************************************/
            //Coordonées x
            System.out.println("Où voulez vous la vous tirer ?");           //On demande à l'utilisateur de saisir les coordonnées du tire
            System.out.println("Veuillez rentrer la colonne :");           //On demande à l'utilisateur de rentrer la colonne
            try{
                xTireFuseeChar= scDestroyer.next().charAt(0);          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un caractère
                System.out.println(ROUGE +"Erreur! "+ RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                xTireFuseeChar='@';          //On remet la variable par defaut 
            }
            xTireFuseeChar=Jeu.convertirMinuscules(xTireFuseeChar);           //On convertir sa saisie en majuscule
            while ((xTireFuseeChar<'A')||(xTireFuseeChar>'O')){           //On blinde, en vérifiant que sa saisie fait partie des choix possible
                System.out.println(ROUGE + "Erreur!"+RESET);           //Sinon on affiche un message d'erreur
                System.out.println("Veuillez entrer la lettre de la colonne d'où vous voulez tirer :");           //On lui demande de ressaisir
                try{
                    xTireFuseeChar= scDestroyer.next().charAt(0);          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un caractère
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                    scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                    xTireFuseeChar='@';          //On remet la variable par defaut
                }
                xTireFuseeChar=Jeu.convertirMinuscules(xTireFuseeChar);           //On convertir sa saisie en majuscule
            }
            //Coordonées y
            System.out.println("Veuillez rentrer la ligne :");         //On lui demande de saisir
            try{
                yTireFusee = scDestroyer.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                yTireFusee=-1;           //On remet la variable par defaut 
            }
            while ((yTireFusee<0)||(yTireFusee>16)){          //On blinde, en vérifiant que la saisie fait partie des choix
                System.out.println(ROUGE + "Erreur!"+ RESET +"\nCe numéro ne fait pas partie des choix.");         //Sinon, on affiche un message d'erreur
                System.out.println("Veuillez entrer le numero de la ligne où vous voulez tirer : :");           //Et on demande la ressaisie
                try{
                    yTireFusee = scDestroyer.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                    scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                    yTireFusee=-1;           //On remet la variable par defaut
                }

            }
            
            yTireFusee--;            //On retire 1 au yTire
            int xTireFusee = (int) (xTireFuseeChar - 65);             //On convertie la saisie en un entier
            
            System.out.print("Envoi de la fusée éclairante en cours");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.println(".");TimeUnit.SECONDS.sleep(1);
            /*Tire de la fusée éclairante***********************************************/
            int surplusX=0;
            int surplusY=0;
            
            if (xTireFusee>10) surplusX=xTireFusee-11;          //On calcule le surplus en cas de dépassement sur la coordonnee X
            if(yTireFusee>10) surplusY=yTireFusee-11;           //On calcule le surplus en cas de dépassement sur la coordonnee Y


            //On parcourt le carré d'une surface de 4*4 en affichant s'il y a des navires ou si y'a rien
            for (int i=0; i<4-surplusX ; i++){
                for (int j=0; j<4-surplusY; j++){
                    if (Jeu.plateauDeJeu.get(xTireFusee+i,yTireFusee+j,2,0)!=(Object)'_' && !Jeu.plateauDeJeu.get(xTireFusee+i,yTireFusee+j,1,0).equals("2") && !Jeu.plateauDeJeu.get(xTireFusee+i,yTireFusee+j,1,0).equals("5")) {
                        Jeu.plateauDeJeu.modification(xTireFusee+i,yTireFusee+j,1,0,"4");         //Affiche sur la grille de tir du joueur les navires qui ont été touché par la fusée éclairante
                    }
                    else Jeu.plateauDeJeu.modification(xTireFusee+i,yTireFusee+j,1,0,"3");          //Affiche sur la grille de tir du joueur là ou la fusée éclairante a touché
                }
            }
            
            System.out.println(VERT+"La fusée à bien été envoyée."+ RESET+ "\nNous envoyons un avion pour la prise d'informations");
            System.out.println("Vous aurez ces informations dans quelques secondes");TimeUnit.SECONDS.sleep(5);

            System.out.println("\n\n    "+GRIS_AR+ "     CARTE ACTUALISÉE    "+RESET_AR);
            Affichage.afficher(0, 1, Jeu.plateauDeJeu);;TimeUnit.SECONDS.sleep(10);

            for (int i=0; i<4-surplusX ; i++){
                for (int j=0; j<4-surplusY; j++){
                    Jeu.plateauDeJeu.modification(xTireFusee+i,yTireFusee+j,1,0,"0");         //Affiche sur la grille de tir du joueur les navires qui ont été touché par la fusée éclairante
                }
            }
            //On met le premier tire sur false pour ne plus rentrer dans l'option du premier tire
            premierTire=false;
        }

        /*Si c'est pas le premier tir du navire***********************************************/
        else {
            char xTireChar='@';         //On declare la variable par defaut avec un @
            int yTire=-1;           //On declare la variable par defaut avec un -1
            System.out.println("Où voulez vous tirer ?");           //On demande à l'utilisateur de saisir les coordonnées du tire
            System.out.println("Veuillez rentrer la colonne :");           //On demande à l'utilisateur de rentrer la colonne
            try{
                xTireChar= scDestroyer.next().charAt(0);          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un caractère
                System.out.println(ROUGE +"Erreur! "+ RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                xTireChar='@';          //On remet la variable par defaut 
            }
            xTireChar=Jeu.convertirMinuscules(xTireChar);           //On convertir sa saisie en majuscule
                        
            while ((xTireChar<'A')||(xTireChar>'O')){           //On blinde, en vérifiant que sa saisie fait partie des choix possible
                System.out.println(ROUGE + "Erreur !"+RESET);           //Sinon on affiche un message d'erreur
                System.out.println("Veuillez entrer la lettre de la colonne d'où vous voulez tirer :");           //On lui demande de ressaisir
                try{
                    xTireChar= scDestroyer.next().charAt(0);          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un caractère
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                    scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                    xTireChar='@';          //On remet la variable par defaut 
                }
                xTireChar=Jeu.convertirMinuscules(xTireChar);           //On convertir sa saisie en majuscule
            }

            System.out.println("Veuillez rentrer la ligne :");         //On lui demande de saisir 
            try{
                yTire = scDestroyer.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                yTire=-1;           //On remet la variable par defaut 
            }


            while ((yTire<0)||(yTire>16)){          //On blinde, en vérifiant que la saisie fait partie des choix
                System.out.println(ROUGE + "Erreur!"+ RESET +"\nCe numéro ne fait pas partie des choix.");         //Sinon, on affiche un message d'erreur
                System.out.println("Veuillez entrer le numero de la ligne où vous voulez tirer :");           //Et on demande la ressaisie
                try{
                    yTire = scDestroyer.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                    scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                    yTire=-1;           //On remet la variable par defaut 
                }
                
            }
            yTire--;            //On retire 1 au yTire

            /*Confirmation du tir sur une case qui a déjà été touchée****************************/
            int xTire = (int) (xTireChar - 65);             //On convertie la saisie en un entier
            if ( Jeu.plateauDeJeu.get(xTire,yTire,1,0).equals("1")){            //Si la case choisie a deja ete touche
                int choix=0;            //On declare une variable qui stock le choix du joueur 
                System.out.println("Voulez vous vraiment tirer sur cette case ? Elle à deja été bombardée");            //On lui demande si il veut vraiment tirer sur cette case
                System.out.println("1.OUI \n2.NON");           //On affiche les choix du joueur 
                try{
                    choix = scDestroyer.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                    scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                }
                
                while ((choix<1)||(choix>2)){           //On blinde en vérifiant que la saisie fait partie des choix
                    System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix :");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                    try{
                        choix = scDestroyer.nextInt();          //On stock la saisie de l'utilisateur
                    }
                    catch(InputMismatchException e){            //Si ce n'est pas un entier
                        System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                        scDestroyer.next();            //On met à la poubelle la saisie de l'utilisateur
                    }
                }

                switch (choix){
                    case 1: break;           //On continue l'attaque
                    case 2: return 2;           //On relance le tour
                }
            }

            //Ici nous affichons un semblant de chargement des données pour le tir ainsi que le résultat, c'est a dire si celui-ci a touché un navire ou pas
            System.out.print("Ajustement des coordonnées");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.println(".");TimeUnit.SECONDS.sleep(1);System.out.println(VERT+"OK"+RESET);
            System.out.print("Armement de l'obus");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.println(".");TimeUnit.SECONDS.sleep(1);System.out.println(VERT+"OK"+RESET);
            TimeUnit.SECONDS.sleep(2);System.out.println(ROUGE_AR+BLANC+"FEU!!"+RESET+RESET_AR);TimeUnit.SECONDS.sleep(2);
            

            /*Vérification qu'il y a un impacte********************************************/
            if (Jeu.plateauDeJeu.get(xTire,yTire,2,0) == (Object) 'S'){
                if (!Jeu.plateauDeJeu.get(xTire,yTire,1,0).equals("2")) Jeu.plateauDeJeu.modification(xTire,yTire,1,0,"5");

                int nPlateauAdv;            //Le numéro du plateau du navire adverse
                int pListeAdv;              //La position dans la liste des navires de l'adversaire      

                nPlateauAdv = (int) Jeu.plateauDeJeu.get(xTire,yTire,2,1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
                pListeAdv=nPlateauToPListe('S', nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

                Jeu.flotteJoueur1.get(pListeAdv).coordonnees[0][2]=2;           //On met la coordonées sur 2 pour signifie, ce qui signifie que le sous-marin a été touché sans être coulé (il ne peux plus être déplacé)

                System.out.println("Nous avons détecté une structure mais nous n'avons pas pu la détruire");TimeUnit.SECONDS.sleep(4);           //Affichage d'un message disant qu'on est tombé sur un sous-marin
           
                return 1;
            }
            else if (Jeu.plateauDeJeu.get(xTire,yTire,2,0) != (Object) '_'){             //Si la case contient un navire
                return impact(xTire, yTire,0);          //On retourne la valeur d'impact
            }
            else{
                System.out.println("Nous n'avons rien touché");TimeUnit.SECONDS.sleep(4);           //On affiche un message comme quoi il n'a rien touché
                Jeu.plateauDeJeu.modification(xTire,yTire,1,0,"1");         //On modifie le plateau et on met qu'on a tire ici
                return 1;           //On a la justification que tout c'est bien passe
            }
        }
        return 0;        
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de donner les dégats sur le navire adverse.
     * La méthode permet de mettre le tir sur la grille de tir du joueur, et de mettre les dégats sur le navire adverse.
     * @param xTire Variable qui stock la colonne de la position du tire
     * @param yTire Variable qui stock la ligne de la position du tire
     * @param numeroJoueur Variable qui stock le numero du joueur, soit le joueur humain, soit l'IA
     * @return Retourne 1 si tout c'est bien passé, 0 en cas d'erreur
     * @throws InterruptedException 
     */
    @Override
    public int impact(int xTire, int yTire, int numeroJoueur) throws InterruptedException{
        Jeu.plateauDeJeu.modification(xTire,yTire,Plateau.numeroEtage(numeroJoueur,1),0,"2");           //On modifie le plateau et on met qu'on a tire ici et que le joueur a touché un navire

        int numeroJoueurAdv = -1;           //Le numéro du joueur mit à -1 par defaut
        char lRefAdv = 'A';         //La lettre de référence du navire mit à A par defaut
        int nPlateauAdv;            //Le numéro du plateau du navire adverse
        int pListeAdv;              //La position dans la liste des navires de l'adversaire
        
        if (numeroJoueur==0) numeroJoueurAdv=1;         //On déduit le numéro du joueur adverse en fonction du joueur
        if (numeroJoueur==1) numeroJoueurAdv=0;         

        lRefAdv= (char) Jeu.plateauDeJeu.get(xTire,yTire,Plateau.numeroEtage(numeroJoueurAdv,0),0);         //On récupère la lettre de référence du navire de l'adversaire aux coordonnées où le joueur veut tirer 
        nPlateauAdv = (int) Jeu.plateauDeJeu.get(xTire,yTire,Plateau.numeroEtage(numeroJoueurAdv,0),1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
        pListeAdv=nPlateauToPListe(lRefAdv, nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

        if (numeroJoueurAdv==0){            //Si c'est l'IA qui joue
            
            Jeu.plateauDeJeu.modification(xTire,yTire,3,0,"2");         //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
            if (Jeu.flotteJoueur0.get(pListeAdv).direction==0){         //Si le navire est à l'horizontale
                
                for (int i=0; i<Jeu.flotteJoueur0.get(pListeAdv).taille; i++){          //On parcourt tout le navire
                    if(Jeu.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        Jeu.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touchée
                    }
                }
                if(Jeu.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Yes! J'ai coulé un "+Jeu.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
            else{           //Si le navire est à la vertical
                
                for (int i=0; i<Jeu.flotteJoueur0.get(pListeAdv).taille; i++){           //On parcourt tout le navire
                    if(Jeu.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire){           //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        Jeu.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touché
                    }
                }
                if(Jeu.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Yes ! J'ai coulé un "+Jeu.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
        }

        if (numeroJoueurAdv==1){           //Si c'est le joueur humain qui joue 
            
            Jeu.plateauDeJeu.modification(xTire,yTire,1,0,"2");//On modifie le plateau et on met qu'on a tire et touche quelqu'un a ces coordonnes la
            if (Jeu.flotteJoueur1.get(pListeAdv).direction==0){         //Si le navire est à l'horizontale
                
                for (int i=0; i<Jeu.flotteJoueur1.get(pListeAdv).taille; i++){          //On parcourt tout le navire

                    if(Jeu.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        Jeu.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        System.out.println("\n"+VERT+"TOUCHE ! \n!"+RESET);           //On affiche un message disant que le joueur a bien touché un navire
                        TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer   
                        
                    }
                }
                if(Jeu.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Bravo ! T'as coulé un "+Jeu.flotteJoueur1.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
            else{           //Si le navire est à la vertical
                
                for (int i=0; i<Jeu.flotteJoueur1.get(pListeAdv).taille; i++){          //On parcourt tout le navire

                    if(Jeu.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        Jeu.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        System.out.println("\n"+VERT+"TOUCHE !"+RESET);           //On affiche un message disant que le joueur a bien touché un navire
                        TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer   
                       
                    }
                }
                if(Jeu.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Bien joué ! T'as coulé un"+Jeu.flotteJoueur1.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
        }
        return 0;           //On retourne 0 si il y a un problème 
    }
}
