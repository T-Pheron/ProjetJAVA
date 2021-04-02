package bataillenavalgraphique.bataillenaval.model;

import bataillenavalgraphique.*;
import bataillenavalgraphique.bataillenaval.view.Affichage;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;



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
     * @param xTire
     * @param yTire
     * @throws InterruptedException 
     */
    @Override
    public void tir(int xTire, int yTire)throws InterruptedException{
        
        if (premierTire==true){
            /*Tire de la fusée éclairante***********************************************/
            int surplusX=0;
            int surplusY=0;
            
            if (xTire>10) surplusX=xTire-11;          //On calcule le surplus en cas de dépassement sur la coordonnee X
            if(yTire>10) surplusY=yTire-11;           //On calcule le surplus en cas de dépassement sur la coordonnee Y

            //On parcourt le carré d'une surface de 4*4 en affichant s'il y a des navires ou si y'a rien
            for (int i=0; i<4-surplusX ; i++){
                for (int j=0; j<4-surplusY; j++){
                    if (JeuGraphique.plateauDeJeu.get(xTire+i,yTire+j,2,0)!=(Object)'_' && !JeuGraphique.plateauDeJeu.get(xTire+i,yTire+j,1,0).equals("2") && !JeuGraphique.plateauDeJeu.get(xTire+i,yTire+j,1,0).equals("5")) {
                        JeuGraphique.plateauDeJeu.modification(xTire+i,yTire+j,1,0,"4");         //Affiche sur la grille de tir du joueur les navires qui ont été touché par la fusée éclairante
                    }
                    else JeuGraphique.plateauDeJeu.modification(xTire+i,yTire+j,1,0,"3");          //Affiche sur la grille de tir du joueur là ou la fusée éclairante a touché
                }
            }
            
            VBox rootTexte = new VBox(25);

            Label information = new Label("Envoie de la fusée effectué");
            information.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 25pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");
            
            Label informationAvion = new Label("\n\nNous envoyons un avion pour récupérer les informations");
            informationAvion.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-font-weight: bold;");

            Label informationTir = new Label("Patienter ...");
            informationTir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-font-weight: bold;");

            rootTexte.getChildren().addAll(information, informationAvion, informationTir);
            rootTexte.setAlignment(Pos.CENTER);
            Scene sceneTir = new Scene(rootTexte);
            JeuGraphique.fenetreJeu.setScene(sceneTir);
            
            AffichageJeuGraphique affichage = new AffichageJeuGraphique();
            affichage.zoneTirFusee(xTire, yTire, surplusX, surplusY);
            //On met le premier tire sur false pour ne plus rentrer dans l'option du premier tire
            premierTire=false;
        }

        /*Si c'est pas le premier tir du navire***********************************************/
        else {
            if(JeuGraphique.plateauDeJeu.get(xTire,yTire,1,0).equals("1")){
                Alert confirmationTir = new Alert(Alert.AlertType.CONFIRMATION);
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
            Label informationNavire = new Label("Vous avez décidez d'utiliser un "+nom);
            informationNavire.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 15pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");

            Label informationCalcul = new Label("Avec les informations fournis, on a calculé comme coordonnées de tir");
            informationCalcul.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-font-weight: bold;");
            Label informationCoordones = new Label("16°02'58."+ yTire +"\"S ; 60°53'40."+ xTire+"\"E");
            informationCoordones.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 25pt;"
                    +                "-fx-background-color: rgba(120,160,175,0.50);"
                    +                "-fx-font-weight: bold;");

            Label informationTir = new Label("\n\nOn effectu le tir, patienter...");
            informationTir.setStyle ("-fx-font-police: 'Tw Cen MT Condensed';"
                    +                "-fx-font-size: 13pt;"
                    +                "-fx-font-weight: bold;");

            rootTexte.getChildren().addAll(informationNavire, informationCalcul, informationCoordones, informationTir);
            rootTexte.setAlignment(Pos.CENTER);
            Scene sceneTir = new Scene(rootTexte);
            JeuGraphique.fenetreJeu.setScene(sceneTir);


            /*Vérification qu'il y a un impacte********************************************/
            if (JeuGraphique.plateauDeJeu.get(xTire,yTire,2,0) == (Object) 'S'){
                if (!JeuGraphique.plateauDeJeu.get(xTire,yTire,1,0).equals("2")) JeuGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"5");

                int nPlateauAdv;            //Le numéro du plateau du navire adverse
                int pListeAdv;              //La position dans la liste des navires de l'adversaire      

                nPlateauAdv = (int) JeuGraphique.plateauDeJeu.get(xTire,yTire,2,1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
                pListeAdv=nPlateauToPListe('S', nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

                JeuGraphique.flotteJoueur1.get(pListeAdv).coordonnees[0][2]=2;           //On met la coordonées sur 2 pour signifie, ce qui signifie que le sous-marin a été touché sans être coulé (il ne peux plus être déplacé)

                AffichageJeuGraphique affichageEchec = new AffichageJeuGraphique();
                affichageEchec.tirSurSousMarin();
            }
            else if (JeuGraphique.plateauDeJeu.get(xTire,yTire,2,0) != (Object) '_'){             //Si la case contient un navire
                impact(xTire, yTire,0);
            }
            else{
                AffichageJeuGraphique affichageEchec = new AffichageJeuGraphique();
                affichageEchec.tirEchec();
                JeuGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"1");         //On modifie le plateau et on met qu'on a tire ici
            }
        }   
    }
    
    
    //**************************************************************************
    /**
     * Méthode qui permet de donner les dégats sur le navire adverse.
     * La méthode permet de mettre le tir sur la grille de tir du joueur, et de mettre les dégats sur le navire adverse.
     * @param xTire Variable qui stock la colonne de la position du tire
     * @param yTire Variable qui stock la ligne de la position du tire
     * @param numeroJoueur Variable qui stock le numero du joueur, soit le joueur humain, soit l'IA
     * @throws InterruptedException 
     */
    @Override
    public void impact(int xTire, int yTire, int numeroJoueur) throws InterruptedException{
        JeuGraphique.plateauDeJeu.modification(xTire,yTire,Plateau.numeroEtage(numeroJoueur,1),0,"2");           //On modifie le plateau et on met qu'on a tire ici et que le joueur a touché un navire

        int numeroJoueurAdv = -1;           //Le numéro du joueur mit à -1 par defaut
        char lRefAdv = 'A';         //La lettre de référence du navire mit à A par defaut
        int nPlateauAdv;            //Le numéro du plateau du navire adverse
        int pListeAdv;              //La position dans la liste des navires de l'adversaire
        
        if (numeroJoueur==0) numeroJoueurAdv=1;         //On déduit le numéro du joueur adverse en fonction du joueur
        if (numeroJoueur==1) numeroJoueurAdv=0;         

        lRefAdv= (char) JeuGraphique.plateauDeJeu.get(xTire,yTire,Plateau.numeroEtage(numeroJoueurAdv,0),0);         //On récupère la lettre de référence du navire de l'adversaire aux coordonnées où le joueur veut tirer 
        nPlateauAdv = (int) JeuGraphique.plateauDeJeu.get(xTire,yTire,Plateau.numeroEtage(numeroJoueurAdv,0),1);         //On récupère le numéro de plateau de l'adversaire aux coordonnées où le joueur veut tirer
        pListeAdv=nPlateauToPListe(lRefAdv, nPlateauAdv);           //On en deduit la position dans la liste du navire de l'adversaire

        if (numeroJoueurAdv==0){            //Si c'est l'IA qui joue
            
            JeuGraphique.plateauDeJeu.modification(xTire,yTire,3,0,"2");         //On met 2, ça signifie que le joueur a tiré et qu'il a touché un navire adverse
            if (JeuGraphique.flotteJoueur0.get(pListeAdv).direction==0){         //Si le navire est à l'horizontale
                
                for (int i=0; i<JeuGraphique.flotteJoueur0.get(pListeAdv).taille; i++){          //On parcourt tout le navire
                    if(JeuGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][0]==xTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touchée
                    }
                }
                if(JeuGraphique.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    AffichageIA affichageIA = new AffichageIA();
                    affichageIA.coulerNavire(JeuGraphique.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                }
                else{
                    AffichageIA affichageIA = new AffichageIA();
                    affichageIA.toucherNavire();
                }
            }
            else{           //Si le navire est à la vertical
                
                for (int i=0; i<JeuGraphique.flotteJoueur0.get(pListeAdv).taille; i++){           //On parcourt tout le navire
                    if(JeuGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][1]==yTire){           //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuGraphique.flotteJoueur0.get(pListeAdv).coordonnees[i][2]=0;           //On met que cette case du navire a été touché
                    }
                }
                if(JeuGraphique.flotteJoueur0.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    AffichageIA affichageIA = new AffichageIA();
                    affichageIA.coulerNavire(JeuGraphique.flotteJoueur0.get(pListeAdv).nom);           //On affiche un message disant qu'il a coulé le navire adverse avec le nom du navire qu'il a coulé
                }
                else{
                    AffichageIA affichageIA = new AffichageIA();
                    affichageIA.toucherNavire();
                }
            }
        }

        if (numeroJoueurAdv==1){           //Si c'est le joueur humain qui joue 
            
            JeuGraphique.plateauDeJeu.modification(xTire,yTire,1,0,"2");//On modifie le plateau et on met qu'on a tire et touche quelqu'un a ces coordonnes la
            if (JeuGraphique.flotteJoueur1.get(pListeAdv).direction==0){         //Si le navire est à l'horizontale
                
                for (int i=0; i<JeuGraphique.flotteJoueur1.get(pListeAdv).taille; i++){          //On parcourt tout le navire

                    if(JeuGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][0]==xTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                    }
                }
                if(JeuGraphique.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    AffichageJeuGraphique affichageTouche = new AffichageJeuGraphique();
                    affichageTouche.tirCoulerNavire(JeuGraphique.flotteJoueur1.get(pListeAdv).nom);   
                }
                else {
                    AffichageIA affichageIA = new AffichageIA();
                    affichageIA.toucherNavire();
                }
            }
            else{           //Si le navire est à la vertical
                for (int i=0; i<JeuGraphique.flotteJoueur1.get(pListeAdv).taille; i++){          //On parcourt tout le navire

                    if(JeuGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][1]==yTire){          //Si la coordonnée de la case du navire correspond à la colonne de coordonnee du tire 
                        JeuGraphique.flotteJoueur1.get(pListeAdv).coordonnees[i][2]=0;            //On met que cette case du navire a été touchée
                    }
                }
                if(JeuGraphique.flotteJoueur1.get(pListeAdv).navireVivant()==false){         //On verifie si le navire est encore vivant ou pas à l'aide de la méthode navireVivant
                    AffichageJeuGraphique affichageTouche = new AffichageJeuGraphique();
                    affichageTouche.tirCoulerNavire(JeuGraphique.flotteJoueur1.get(pListeAdv).nom);            //On patient pendant 3 seconde avant de continuer   
                }
                else{
                    AffichageIA affichageIA = new AffichageIA();
                    affichageIA.toucherNavire();
                }
            }
        } 
    }
}
