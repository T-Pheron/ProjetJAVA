package bataillenaval.model;

import bataillenaval.controller.Jeu;
import bataillenaval.view.Menu;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
    
public class SousMarin extends Flotte{
    
    //**************************************************************************
    private static final long serialVersionUID = 3045793040051941049L;          //Runtine définie par le logiciel
    private final Scanner scSousMarin = new Scanner(System.in);         //Variable pour le stockage les différentes informations du plateau de jeu


    //**************************************************************************
    /**
     * Constructeur de la classe SousMarin.
     * Il permet de créer un un navire de type sous-marin. 
     */
    public SousMarin(){
        etat =true;         //On met l'état du navire à true
        nom = "sous-marin";         //On donne au navire son nom
        taille = 1;         //On stock la taille du navire
        coordonnees = new int [taille][3];      //Variable qui stocke les coordonnées du sous-marin. 0 pour une coordonnées qui a été touché, 1 pour une coordonnées non touché, 2 pour une coordonnées touché par autre chose qu'un sous marin
        puissance = 1;          //On stocke la puissance du navire 
        lRef = 'S';         //On stocke la lettre de référence du navire
        nRef =24;           //On donne un numéro de référence par défaut au navire
    }
    
    /**
     * Méthode qui permet de donner un numéro de référence unique à un sous-marin.
     * D'après la position dans la liste sous-marin à un numéro de référence unique
     * compris entre 30 et 34.
     * @param pListe 
     */
    @Override
    public void nRef (int pListe){
        nRef += pListe;         //Pour cela on addition juste le nRef avec la position dans le liste
    }
    
    
    /**
     * Méthode à un navire d'effectuer un tir.
     * La méthode permet de demander des coordonnées de tir et d'effectuer un tir.
     * @return 1 Si tout c'est bien déroulé, 0 en cas de problème
     * @throws InterruptedException 
     */
    @Override
    public int tir()throws InterruptedException{
        
        char xTireChar='@';         //On declare la variable par defaut avec un @
        int yTire=-1;           //On declare la variable par defaut avec un -1
        System.out.println("Où voulez vous tirer ?");           //On demande à l'utilisateur de saisir les coordonnées du tire
        System.out.println("Rentrer la colonne");           //On demande à l'utilisateur de rentrer la colonne
        try{
            xTireChar= scSousMarin.next().charAt(0);          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un caractère
            System.out.println(Menu.ROUGE +"Erreur! "+ Menu.RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
            scSousMarin.next();            //On met à la poubelle la saisie de l'utilisateur
            xTireChar='@';          //On remet la variable par defaut 
        }
        xTireChar=Jeu.convertirMinuscules(xTireChar);           //On convertir sa saisie en majuscule
                    
        while ((xTireChar<'A')||(xTireChar>'O')){           //On blinde, en vérifiant que sa saisie fait partie des choix possible
            System.out.println(Menu.ROUGE + "Erreur !"+Menu.RESET);           //Sinon on affiche un message d'erreur
            System.out.println("Veuillez entrer la lettre de la colonne d'où vous voulez tirer :");           //On lui demande de ressaisir
            try{
                xTireChar= scSousMarin.next().charAt(0);          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un caractère
                System.out.println(Menu.ROUGE +"Erreur! "+Menu.RESET+ "La saisie n'est pas un caractère");            //On affiche un message d'erreur
                scSousMarin.next();            //On met à la poubelle la saisie de l'utilisateur
                xTireChar='@';          //On remet la variable par defaut 
            }
            xTireChar=Jeu.convertirMinuscules(xTireChar);           //On convertir sa saisie en majuscule
        }

        System.out.println("Rentrer la ligne");         //On lui demande de saisir 
        try{
            yTire = scSousMarin.nextInt();          //On stock la saisie de l'utilisateur
        }
        catch(InputMismatchException e){            //Si ce n'est pas un entier
            System.out.println(Menu.ROUGE +"Erreur! "+Menu.RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
            scSousMarin.next();            //On met à la poubelle la saisie de l'utilisateur
            yTire=-1;           //On remet la variable par defaut 
        }


        while ((yTire<0)||(yTire>16)){          //On blinde, en vérifiant que la saisie fait partie des choix
            System.out.println(Menu.ROUGE + "Erreur_tire_navire!"+ Menu.RESET +"\nCe numéro ne fait pas partie des choix.");         //Sinon, on affiche un message d'erreur
            System.out.println("Veuillez entrer le numero de la ligne où vous voulez tirer : :");           //Et on demande la ressaisie
            try{
                yTire = scSousMarin.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(Menu.ROUGE +"Erreur! "+Menu.RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                scSousMarin.next();            //On met à la poubelle la saisie de l'utilisateur
                yTire=-1;           //On remet la variable par defaut 
            }
            
        }
        yTire--;            //On retire 1 au yTire

        int xTire = (int) (xTireChar - 65);             //On convertie la saisie en un entier
        if ( Jeu.plateauDeJeu.get(xTire,yTire,1,0).equals("1")){            //Si la case choisie a deja ete touche
            int choix=0;            //On declare une variable qui stock le choix du joueur 
            System.out.println("Voulez vous vraiment tirer sur cette case ? Elle a deja été bombardée");            //On lui demande si il veut vraiment tirer sur cette case
            System.out.println("1.OUI \n2.NON");           //On affiche les choix du joueur 
            try{
                choix = scSousMarin.nextInt();          //On stock la saisie de l'utilisateur
            }
            catch(InputMismatchException e){            //Si ce n'est pas un entier
                System.out.println(Menu.ROUGE +"Erreur! "+Menu.RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                scSousMarin.next();            //On met à la poubelle la saisie de l'utilisateur
            }
            
            while ((choix<1)||(choix>2)){           //On blinde en vérifiant que la saisie fait partie des choix
                System.out.println(Menu.ROUGE+"Erreur! "+ Menu.RESET +"Veuillez saisir à nouveau votre choix :");           //Sinon, on affiche un message d'erreur et demande la ressaisie
                try{
                    choix = scSousMarin.nextInt();          //On stock la saisie de l'utilisateur
                }
                catch(InputMismatchException e){            //Si ce n'est pas un entier
                    System.out.println(Menu.ROUGE +"Erreur! "+Menu.RESET+ "La saisie n'est pas un nombre entier");            //On affiche un message d'erreur
                    scSousMarin.next();            //On met à la poubelle la saisie de l'utilisateur
                }
            }

            switch (choix){
                case 1: break;           //On continue l'attaque
                case 2: return 2;           //On relance le tour
            }
        }

        //Ici nous affichons un semblant de chargement des données pour le tir ainsi que le résultat, c'est a dire si celui-ci a touché un navire ou pas
        System.out.print("Ajustement des coordonnées");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.println(".");TimeUnit.SECONDS.sleep(1);System.out.println(Menu.VERT+"OK"+Menu.RESET);
        System.out.print("Armement de l'obus");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.print(".");TimeUnit.SECONDS.sleep(1);System.out.println(".");TimeUnit.SECONDS.sleep(1);System.out.println(Menu.VERT+"OK"+Menu.RESET);
        TimeUnit.SECONDS.sleep(2);System.out.println(Menu.ROUGE_AR+Menu.BLANC+"FEU!!"+Menu.RESET+Menu.RESET_AR);TimeUnit.SECONDS.sleep(2);
        

        if (Jeu.plateauDeJeu.get(xTire,yTire,2,0) != (Object) '_'){             //Si la case contient un navire
            return impact(xTire, yTire,0);          //On retourne la valeur d'impact
        }
        else{
            System.out.println("Nous avons rien touché");TimeUnit.SECONDS.sleep(4);           //On affiche un message comme quoi il n'a rien touché
            Jeu.plateauDeJeu.modification(xTire,yTire,1,0,"1");         //On modifie le plateau et on met qu'on a tire ici  
            return 1;           //On a la justification que tout c'est bien passe
        }
    }


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
                        Jeu.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touché
                    }
                }
                if(Jeu.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Yes ! J'ai coulé un "+Jeu.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer
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
                
                for (int j=0; j<Jeu.flotteJoueur1.get(pListeAdv).taille;){          //On parcourt tout le navire

                    if(Jeu.flotteJoueur1.get(pListeAdv).coordonnees[j][0]==xTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        Jeu.flotteJoueur1.get(pListeAdv).coordonnees[j][2]=0;            //On met que cette case du navire a été touchée
                        System.out.println("\n"+Menu.VERT+"TOUCHE !"+Menu.RESET);           //On affiche un message disant que le joueur a bien touché un navire
                        TimeUnit.SECONDS.sleep(3);
                    }
                    j++;
                }    
                
                if(Jeu.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Bien joué ! T'as coulé un "+Jeu.flotteJoueur1.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);          //On patient pendant 3 seconde avant de continuer
                }
                
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
                
            }
            else{           //Si le navire est à la vertical
                
                for (int i=0; i<Jeu.flotteJoueur1.get(pListeAdv).taille; i++){          //On parcourt tout le navire

                    if(Jeu.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        Jeu.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        System.out.println("\n"+Menu.VERT+"TOUCHE !"+Menu.RESET);           //On affiche un message disant que le joueur a bien touché un navire
                        TimeUnit.SECONDS.sleep(3);          //On patient pendant 3 seconde avant de continuer
                        
                    }
                }
                if(Jeu.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Bravo ! T'as coulé un "+Jeu.flotteJoueur1.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                    TimeUnit.SECONDS.sleep(3);              //On patient pendant 3 seconde avant de continuer
                }
                return 1;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
        }
        return 0;           //On retourne 0 si il y a un problème 
    }
}
