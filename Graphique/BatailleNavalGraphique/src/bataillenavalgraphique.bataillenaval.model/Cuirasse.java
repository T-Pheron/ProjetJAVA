package bataillenavalgraphique.bataillenaval.model;

import bataillenavalgraphique.bataillenaval.controller.JeuNGraphique;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Cuirasse extends Flotte{
    
    //**************************************************************************
    private static final long serialVersionUID = 2755963685973214786L;      //Runtine définie par le logiciel
    private final Scanner scCuirasse = new Scanner(System.in);          //On initialise une varible de type scanner pour récupérer les saisis de l'utilisateur


    //**************************************************************************
    /**
     * Constructeur de la classe Cuirasse.
     * Il permet de créer un un navire de type cuirasse 
     */
    public Cuirasse(){
        etat = true;            //On met l'état du navire à true
        nom = "cuirassé";           //On donne au navire son nom
        taille = 7;         //On stock la taille du navire
        coordonnees = new int [taille][3];          //Variable qui stocke les coordonnées du navire. 0 pour une coordonnées qui a été touché, 1 pour une coordonnées non touché.
        puissance = 9;          //On stocke la puissance du navire 
        lRef = 'U';             //On stocke la lettre de référence du navire
        nRef =1;            //On donne un numéro de référence par défaut au navire
    }
    
    
    /**
     * Méthode qui permet de donner un numéro de référence unique à un cuirasse.
     * D'après la position dans la liste Cuirasse à un numéro de référence unique
     * qui est 1.
     * @param pListe 
     */
    @Override
    public void nRef (int pListe){
        nRef += pListe;         //Pour cela on addition juste le nRef avec la position dans le liste
    }
    
    
     /**
     * Méthode à un navire d'effectuer un tir.
     * La méthode permet de demander des coordonnées de tir et d'effectuer un tir.
     * @param xTire
     * @param yTire
     * @return 1 Si tout c'est bien déroulé, 0 en cas de problème
     * @throws InterruptedException 
     */
    @Override
    public int tir(int xTire, int yTire) throws InterruptedException{
        
        char xTireChar='@';         //On declare la variable par defaut avec un @

        
        System.out.println("Où voulez vous tirer ?");           //On demande à l'utilisateur de saisir les coordonnées du tire
        System.out.println("Veuillez rentrer la colonne :");           //On demande à l'utilisateur de rentrer la colonne
        try{
            xTireChar= scCuirasse.next().charAt(0);          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un caractère
            System.out.println(ROUGE +"Erreur! "+ RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
            scCuirasse.next();            //On met à la poubelle la saisie de l'utilisateur
            xTireChar='@';          //On remet la variable par defaut 
        }
        xTireChar=JeuNGraphique.convertirMinuscules(xTireChar);           //On convertir sa saisie en majuscule
        while ((xTireChar<'A')||(xTireChar>'O')){           //On blinde, en vérifiant que sa saisie fait partie des choix possible
            System.out.println(ROUGE + "Erreur !"+RESET);           //Sinon on affiche un message d'erreur
            System.out.println("Veuillez entrer la lettre de la colonne d'où vous voulez tirer :");           //On lui demande de ressaisir
            try{
                xTireChar= scCuirasse.next().charAt(0);          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un caractère
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                scCuirasse.next();            //On met à la poubelle la saisie de l'utilisateur
                xTireChar='@';          //On remet la variable par defaut
            }
            xTireChar=JeuNGraphique.convertirMinuscules(xTireChar);           //On convertir sa saisie en majuscule
        }
        System.out.println("Veuillez rentrer la ligne :");         //On lui demande de saisir
        try{
            yTire = scCuirasse.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
            scCuirasse.next();            //On met à la poubelle la saisie de l'utilisateur
            yTire=-1;           //On remet la variable par defaut 
        }
        while ((yTire<0)||(yTire>16)){          //On blinde, en vérifiant que la saisie fait partie des choix
            System.out.println(ROUGE + "Erreur!"+ RESET +"\nCe numéro ne fait pas parti des choix.");         //Sinon, on affiche un message d'erreur
            System.out.println("Veuillez entrer le numero de la ligne a laquelle vous voulez tirer :");           //Et on demande la ressaisie
            try{
                yTire = scCuirasse.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scCuirasse.next();            //On met à la poubelle la saisie de l'utilisateur
                yTire=-1;           //On remet la variable par defaut
            }

        }
        
        yTire--;            //On retire 1 au yTire
        xTire = (int) (xTireChar - 65); //On convertie la saisie en un entier
        
        /*Confirmation du tir sur une case qui a déjà été touchée****************************/
        if ( JeuNGraphique.plateauDeJeu.get(xTire,yTire,1,0).equals("1")){            //Si la case choisie a deja ete touche
            int choix=0;            //On declare une variable qui stock le choix du joueur
            System.out.println("Voulez vous vraiment tirer sur cette case ? Elle à deja été bombardée");            //On lui demande si il veut vraiment tirer sur cette case
            System.out.println("1.OUI \n2.NON");           //On affiche les choix du joueur
            try{
                choix = scCuirasse.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                scCuirasse.next();            //On met à la poubelle la saisie de l'utilisateur
            }

            while ((choix<1)||(choix>2)){           //On blinde en vérifiant que la saisie fait partie des choix
                System.out.println(ROUGE+"Erreur! "+ RESET +"Veuillez saisir à nouveau votre choix");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                try{
                    choix = scCuirasse.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(ROUGE +"Erreur! "+RESET+ "La saisie n'est pas un entier");            //On affiche un message d'erreur
                    scCuirasse.next();            //On met à la poubelle la saisie de l'utilisateur
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
        if (JeuNGraphique.plateauDeJeu.get(xTire,yTire,2,0) == (Object) 'S'){
            if (!JeuNGraphique.plateauDeJeu.get(xTire,yTire,1,0).equals("2")) JeuNGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"5");

            int nPlateauAdv;            //Le numéro du plateau du navire adverse
            int pListeAdv;              //La position dans la liste des navires de l'adversaire      

            nPlateauAdv = (int) JeuNGraphique.plateauDeJeu.get(xTire,yTire,2,1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
            pListeAdv=nPlateauToPListe('S', nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

            JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[0][2]=2;           //On met la coordonées sur 2 pour signifie, ce qui signifie que le sous-marin a été touché sans être coulé (il ne peux plus être déplacé)

            System.out.println("Nous avons détecté une structure mais n'avons pas pu la détruire");TimeUnit.SECONDS.sleep(4);           //Affichage d'un message disant qu'on est tombé sur un sous-marin
            return 1;
        }
        else if (JeuNGraphique.plateauDeJeu.get(xTire,yTire,2,0) != (Object) '_'){             //Si la case contient un navire
            return impact(xTire, yTire,0);          //On retourne la valeur d'impact
        }
        else{
            System.out.println("Nous n'avons rien touché");TimeUnit.SECONDS.sleep(4);           //On affiche un message comme quoi il n'a rien touché
            JeuNGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"1");         //On modifie le plateau et on met qu'on a tire ici
            return 1;           //On a la justification que tout c'est bien passe
        }
                
    }
    
    
    /**
     * Méthode qui permet de donner les dégats sur le navire adverse.
     * La méthode permet de mettre le tir sur la grille de tir du joueur, et de mettre les dégats sur le navire adverse.
     * Le cuirasse ne peut pas couler un sous-marin. Il fait néanmoins plus de dégats que le destroyer et le croiseur.
     * @param xTire Variable qui stock la colonne de la position du tire
     * @param yTire Variable qui stock la ligne de la position du tire
     * @param numeroJoueur Variable qui stock le numero du joueur, soit le joueur humain, soit l'IA
     * @return Retourne 1 si tout c'est bien passé, 0 en cas d'erreur
     * @throws InterruptedException 
     */
    @Override
    public int impact(int xTire, int yTire, int numeroJoueur) throws InterruptedException{
        JeuNGraphique.plateauDeJeu.modification(xTire,yTire,Plateau.numeroEtage(numeroJoueur,1),0,"2");           //On modifie le plateau et on met qu'on a tire ici et que le joueur a touché un navire

        int numeroJoueurAdv = -1;           //Le numéro du joueur mit à -1 par defaut
        char lRefAdv = 'A';         //La lettre de référence du navire mit à A par defaut
        int nPlateauAdv;            //Le numéro du plateau du navire adverse
        int pListeAdv;              //La position dans la liste des navires de l'adversaire
        
        if (numeroJoueur==0) numeroJoueurAdv=1;         //On déduit le numéro du joueur adverse en fonction du joueur
        if (numeroJoueur==1) numeroJoueurAdv=0;         

        System.out.println(Plateau.numeroEtage(numeroJoueurAdv,0));
        lRefAdv= (char) JeuNGraphique.plateauDeJeu.get(xTire,yTire,Plateau.numeroEtage(numeroJoueurAdv,0),0);         //On récupère la lettre de référence du navire de l'adversaire aux coordonnées où le joueur veut tirer 
        nPlateauAdv = (int) JeuNGraphique.plateauDeJeu.get(xTire,yTire,Plateau.numeroEtage(numeroJoueurAdv,0),1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
        pListeAdv=nPlateauToPListe(lRefAdv, nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

        if (numeroJoueurAdv==0){            //Si c'est l'IA qui joue
            
            JeuNGraphique.plateauDeJeu.modification(xTire,yTire,3,0,"2");         //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
            if (JeuNGraphique.flotteJoueur0.get(pListeAdv).direction==0){         //Si le navire est à l'horizontale
                
                for (int i=0; i<JeuNGraphique.flotteJoueur0.get(pListeAdv).taille; i++){          //On parcourt tout le navire
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touchée  
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire-1){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire-1,yTire,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire-2){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire-2,yTire,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire+1,yTire,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire+2){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire+2,yTire,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                }
                if(JeuNGraphique.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("\nYes ! J'ai coulé un "+JeuNGraphique.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
            else{           //Si le navire est à la vertical
                
                for (int i=0; i<JeuNGraphique.flotteJoueur0.get(pListeAdv).taille; i++){          //On parcourt tout le navire
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touchée  
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire-1){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire-1,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire-2){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire-2,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire+1,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire+2){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire+2,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                }
                if(JeuNGraphique.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("\nYes ! J'ai coulé un "+JeuNGraphique.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
                }
            }
        

        if (numeroJoueurAdv==1){           //Si c'est le joueur humain qui joue 
            
            JeuNGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"2");         //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
            if (JeuNGraphique.flotteJoueur1.get(pListeAdv).direction==0){         //Si le navire est à l'horizontale

                for (int i=0; i<JeuNGraphique.flotteJoueur1.get(pListeAdv).taille; i++){          //On parcourt tout le navire
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touchée  
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire-1){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire-1,yTire,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire-2){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire-2,yTire,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire+1,yTire,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire+2){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire+2,yTire,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                }
                System.out.println("\n"+VERT+"TOUCHE ! \n"+RESET);           //On affiche un message disant que le joueur a bien touché un navire
                TimeUnit.SECONDS.sleep(3);

                if(JeuNGraphique.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("\nBien joué ! T'as coulé un "+JeuNGraphique.flotteJoueur1.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
            else{           //Si le navire est à la vertical

                for (int i=0; i<JeuNGraphique.flotteJoueur1.get(pListeAdv).taille; i++){          //On parcourt tout le navire
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touchée  
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire-1){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire-1,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire-2){          //Si la coordonnée de la case du navire correspond à la colonne-1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire-2,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire+1,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire+2){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire+2,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                }
                System.out.println("\n"+VERT+"TOUCHE ! \n"+RESET);           //On affiche un message disant que le joueur a bien touché un navire
                TimeUnit.SECONDS.sleep(3);

                if(JeuNGraphique.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("\nBien joué ! T'as coulé un "+JeuNGraphique.flotteJoueur1.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);   
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
        
        }
        return 0;           //On retourne 0 si il y a un problème 
    }

}
