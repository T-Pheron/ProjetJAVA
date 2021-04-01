package bataillenavalgraphique.bataillenaval.model;

import bataillenavalgraphique.AffichageJeuGraphique;
import bataillenavalgraphique.JeuGraphique;
import bataillenavalgraphique.bataillenaval.controller.JeuNGraphique;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Croiseur extends Flotte {


    //**************************************************************************
    private static final long serialVersionUID = 6887469948251489653L;          //Runtine définie par le logiciel
    private final Scanner scCroiseur = new Scanner(System.in);          //On initialise une varible de type scanner pour récupérer les saisis de l'utilisateur

    
    //**************************************************************************
    /**
     * Constructeur de la classe Croisseur.
     * Il permet de créer un un navire de type croiseur 
     */
    public Croiseur(){
        etat =true;         //On met l'état du navire à true
        nom = "croiseur";           //On donne au navire son nom
        taille = 5;         //On stock la taille du navire
        coordonnees = new int [taille][3];          //Variable qui stocke les coordonnées du navire. 0 pour une coordonnées qui a été touché, 1 pour une coordonnées non touché.
        puissance = 4;          //On stocke la puissance du navire 
        lRef = 'C';             //On stocke la lettre de référence du navire
        nRef =9;            //On donne un numéro de référence par défaut au navire
    }
    

    //**************************************************************************
    /**
     * Méthode qui permet de donner un numéro de référence unique à un croiseur.
     * D'après la position dans la liste Croisseur à un numéro de référence unique.
     * @param pListe La position du navire dans la liste de la flotte
     */
    @Override
    public void nRef (int pListe){
        nRef += pListe;         //On ajoute la position de la liste au 
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
    public void tir(int xTire, int yTire) throws InterruptedException{
        
        if(JeuGraphique.plateauDeJeu.get(xTire,yTire,1,0).equals("1")){
            Alert confirmationTir = new Alert(AlertType.CONFIRMATION);
            confirmationTir.setTitle("Bataille Navale - Confirmation de tir");
            confirmationTir.setHeaderText("La case que vous avez sélectionné à déjà été bombardée.");
            confirmationTir.setContentText("Etes-vous sur de vouloir la bombarder à nouveau cette case ?");
            
            ButtonType boutonOui = new ButtonType("Oui");
            ButtonType boutonNon = new ButtonType("Non");
            confirmationTir.getButtonTypes().setAll(boutonOui, boutonNon);
            
            Optional<ButtonType> choix = confirmationTir.showAndWait();

            if (choix.get() == boutonNon){
                AffichageJeuGraphique affichage = new AffichageJeuGraphique();
                affichage.selectionCaseTir(Flotte.nRefToPListe(lRef, nRef));
            }
        }
        
        
        VBox rootTexte = new VBox(25);
        Label informationNavire = new Label("Vous avez décidez d'utilisaer un destroyer");
        Label informationCalcul = new Label("Avec les informations fournis, on a calculé comme coordonnées de tir");
        Label informationCoordones = new Label("16°02'58."+ yTire +"\"S ; 60°53'40."+ xTire+"\"E");

        rootTexte.getChildren().addAll(informationNavire, informationCalcul, informationCoordones);
        Scene sceneTir = new Scene(rootTexte);
        JeuGraphique.fenetreJeu.setScene(sceneTir);
        
        
        /*Vérification qu'il y a un impacte********************************************/
        if (JeuNGraphique.plateauDeJeu.get(xTire,yTire,2,0) == (Object) 'S'){
            if (!JeuNGraphique.plateauDeJeu.get(xTire,yTire,1,0).equals("2")) JeuNGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"5");

            int nPlateauAdv;            //Le numéro du plateau du navire adverse
            int pListeAdv;              //La position dans la liste des navires de l'adversaire      

            nPlateauAdv = (int) JeuNGraphique.plateauDeJeu.get(xTire,yTire,2,1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
            pListeAdv=nPlateauToPListe('S', nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

            JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[0][2]=2;           //On met la coordonées sur 2 pour signifie, ce qui signifie que le sous-marin a été touché sans être coulé (il ne peux plus être déplacé)

            System.out.println("Nous avons détecté une structure mais n'avons pas pu la détruire");TimeUnit.SECONDS.sleep(4);           //Affichage d'un message disant qu'on est tombé sur un sous-marin
            //return 1;
        }
        else if (JeuNGraphique.plateauDeJeu.get(xTire,yTire,2,0) != (Object) '_'){             //Si la case contient un navire
            int retourImpacte = impact(xTire, yTire,0);          //On retourne la valeur d'impact
            
            if (retourImpacte==0){
                System.out.println("On est la");
            }
            if (retourImpacte==0){
                System.out.println("On est la1");
            }
            if (retourImpacte==0){
                System.out.println("On est la2");
            }
            if (retourImpacte==0){
                System.out.println("On est la3");
            }
            else{
                System.out.println("On est la4");
            }
        }
        else{
            AffichageJeuGraphique affichage = new AffichageJeuGraphique();
            affichage.tirEchec();
            System.out.println("Nous n'avons rien touché");          //On affiche un message comme quoi il n'a rien touché
            JeuNGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"1");         //On modifie le plateau et on met qu'on a tire ici
            //return 1;           //On a la justification que tout c'est bien passe
        }  
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de donner les dégats sur le navire adverse.
     * La méthode permet de mettre le tir sur la grille de tir du joueur, et de mettre les dégats sur le navire adverse.
     * Le croiseur ne peut pas couler un sous-marin. Il fait néanmoins plus de dégats que le destroyer.
     * @param xTire Variable qui stock la colonne de la position du tire
     * @param yTire Variable qui stock la ligne de la position du tire
     * @param numeroJoueur Variable qui stock le numero du joueur, soit le joueur humain, soit l'IA
     * @return Retourne 1 si tout c'est bien passé, 0 en cas d'erreur
     * @throws InterruptedException 
     */
    @Override
    public int impact(int xTire, int yTire, int numeroJoueur) throws InterruptedException {
        JeuNGraphique.plateauDeJeu.modification(xTire,yTire,Plateau.numeroEtage(numeroJoueur,1),0,"2");           //On modifie le plateau et on met qu'on a tire ici et que le joueur a touché un navire

        int numeroJoueurAdv = -1;           //Le numéro du joueur mit à -1 par defaut
        char lRefAdv = 'A';         //La lettre de référence du navire mit à A par defaut
        int nPlateauAdv;            //Le numéro du plateau du navire adverse
        int pListeAdv;              //La position dans la liste des navires de l'adversaire
        
        if (numeroJoueur==0) numeroJoueurAdv=1;         //On déduit le numéro du joueur adverse en fonction du joueur
        if (numeroJoueur==1) numeroJoueurAdv=0;         

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
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire+1,yTire,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                }
                if(JeuNGraphique.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Yes ! J'ai coulé un "+JeuNGraphique.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
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
                    if(JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire+1,3,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                }
                if(JeuNGraphique.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    System.out.println("Yes ! J'ai coulé un "+JeuNGraphique.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
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
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire+1,yTire,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }  
                }

                if(JeuNGraphique.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    return 2;
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
                    if(JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire+1){          //Si la coordonnée de la case du navire correspond à la colonne+1 de coordonnee du tire 
                        JeuNGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                        JeuNGraphique.plateauDeJeu.modification(xTire,yTire+1,1,0,"2");           //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
                    }
                }

                if(JeuNGraphique.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    return pListeAdv;
                }
                return 20;           //On retourne 1, ce qui signifie que tout c'est bien passé
            }
        
        }
        return 0;           //On retourne 0 si il y a un problème 
    }
}

