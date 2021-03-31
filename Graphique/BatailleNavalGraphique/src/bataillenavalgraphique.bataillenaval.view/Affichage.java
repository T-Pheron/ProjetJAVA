package bataillenavalgraphique.bataillenaval.view;

import bataillenavalgraphique.bataillenaval.model.Plateau;
import static bataillenavalgraphique.bataillenaval.model.Plateau.numeroEtage;

/**
 *
 * @author Théric PHERON
 */
public class Affichage {
    
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs du texte*/
    /*Elles doivent être placées devant le texte qui doit être mis en couleur avec un +*/
    
    public static final String RESET = "\u001B[0m";                //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public static final String NOIR = "\u001B[30m";
    public static final String ROUGE = "\u001B[31m";
    public static final String VERT = "\u001B[32m";
    public static final String JAUNE = "\u001B[33m";
    public static final String BLEU = "\u001B[34m";
    public static final String VOILET = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANC = "\u001B[37m";
    
    public static final String B_NOIR = "\u001B[30;1m";            
    public static final String B_ROUGE = "\u001B[31;1m";
    public static final String B_VERT = "\u001B[32;1m";
    public static final String B_JAUNE = "\u001B[33;1m";
    public static final String B_BLEU = "\u001B[34;1m";
    public static final String B_VOILET = "\u001B[35;1m";
    public static final String B_CYAN = "\u001B[36;1m";
    public static final String B_BLANC = "\u001B[37;1m";
    
    //**************************************************************************
    /*Variables utilisées pour modifier les couleurs de l'arriere plan*/
    /*Elles doivent être placées devant le texte qui doit avoir une arrière plan en couleur avec un +*/
    
    public static final String RESET_AR = "\u001B[40m";                //Permet de remettre la couleur par défaut de la console. On la met à la fin de chaque changement de couleur pour remetre la valeur par défaut
    public static final String ROUGE_AR = "\u001B[41m";
    public static final String VERT_AR = "\u001B[42m";
    public static final String JAUNE_AR = "\u001B[43m";
    public static final String BLEU_AR = "\u001B[44m";
    public static final String VOILET_AR = "\u001B[45m";
    public static final String CYAN_AR = "\u001B[46m";
    public static final String GRIS_AR = "\u001B[47m";
    
    public static final String B_ROUGE_AR = "\u001B[41;1m";
    public static final String B_VERT_AR = "\u001B[42;1m";
    public static final String B_JAUNE_AR = "\u001B[43;1m";
    public static final String B_BLEU_AR = "\u001B[44;1m";
    public static final String B_VOILET_AR = "\u001B[45;1m";
    public static final String B_CYAN_AR = "\u001B[46;1m";
    public static final String B_GRIS_AR = "\u001B[47;1m";
    
    
    //**************************************************************************
    /**
     * Méthode qui permet d'afficher une l'étage du plateau de jeu.
     * La méthode affiche l'étage du plateau de jeu demandé en fonction du numéro du joueur et le numéro de grille demandé.
     * @param numeroJoueur Le numéro du joueur
     * @param numeroGrille Le numéro de la grille
     * @param plateauDeJeu
     */
    public static void afficher(int numeroJoueur, int numeroGrille, Plateau plateauDeJeu){
        
        int numeroEtage= numeroEtage(numeroJoueur,numeroGrille);                //On calcul le numéro de l'étage demandé
        
        /*En fonction du numéro d'étage on affiche différente chose*/
        if (numeroEtage == 0 || numeroEtage == 2){              //Si on est sur la grille des navire du joueur on affiche le titre
            System.out.println(GRIS_AR + BLANC +"          Voici la grille de vos navires        "+RESET_AR +RESET);
            System.out.println(B_JAUNE_AR + BLANC +"  | A| B| C| D| E| F| G| H| I| J| K| L| M| N| O|"+RESET_AR +RESET);             //On affiche les repères pour l'utilisateur
        
            for (int j=0; j<15; j++){           //On fait une boucle pour parcourir toutes les lignes da la grille
            
                if (j<9) System.out.print(B_JAUNE_AR +BLANC +" "+(j+1)+"|"+RESET_AR +RESET);            //A chaque début de ligne on affiche le repère pour l'utilisateur
                else System.out.print(B_JAUNE_AR +BLANC +(j+1)+"|"+RESET_AR +RESET);            //Si le nombre est supérieur à 9 on retire un espace pour évité de décaler
            
                for (int i=0; i<15; i++){               //On fait une boucle pour parcourir toutes les colonnes de la grille
                    
                    //***************************
                    /*On utilise cette partie pour pouvoir afficher les tires que l'adversaire a effectué sur le plateau de jeu du joueur*/
                    
                    if (numeroJoueur==0){               //On vérifie le numéro du joueur pour pouvoir différencier l'étage dans la plateau de jeu
                        if (plateauDeJeu.get(i, j, 3, 0).equals("1") && !plateauDeJeu.get(i, j, numeroEtage, 0).equals('U')&& !plateauDeJeu.get(i, j, numeroEtage, 0).equals('C') && !plateauDeJeu.get(i, j, numeroEtage, 0).equals('D') && !plateauDeJeu.get(i, j, numeroEtage, 0).equals('S')){               //Si le joueur adversaire à tirer à cette emplacement mais n'a rien touché. On exclu aussi le faite que le joueur est bien pu déplacer son navire sur cette case à un tour n+1
                            System.out.print(CYAN_AR + B_ROUGE + "XX" + RESET_AR + RESET +"|");               //On affiche l'information sur la grille de jeu avec des croix rouge brillant et un fond cyan
                            continue;               //On passe au tour suivant de la boucle sans effectuer le reste de la boucle
                        }
                        if (plateauDeJeu.get(i, j, 3, 0).equals("2")){               //Si le joueur adversaire à tirer à cette emplacement et a touché un navire 
                            System.out.print(ROUGE_AR + B_ROUGE + plateauDeJeu.get(i, j, numeroEtage, 0) +""+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche les informations du navire en rouge foncé
                            continue;               //On passe au tour suivant de la boucle sans effectuer le reste de la boucle
                        }
                        if (plateauDeJeu.get(i, j, 3, 0).equals("5")){               //Si le joueur adversaire à tirer sur un sous marin mais ne la pas coulé parce-que le navire qu'il a tiré n'était pas un sous marin
                            System.out.print(CYAN_AR + ROUGE + 'S'+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche le sous marin avec la couleur cyan et rouge
                            continue;               //On passe au tour suivant de la boucle sans effectuer le reste de la boucle
                        }
                    }
                    else {               //Si c'est l'autre joueur (IA)
                        if (plateauDeJeu.get(i, j, 1, 0).equals("1") && !plateauDeJeu.get(i, j, numeroEtage, 0).equals('U')&& !plateauDeJeu.get(i, j, numeroEtage, 0).equals('C') && !plateauDeJeu.get(i, j, numeroEtage, 0).equals('D') && !plateauDeJeu.get(i, j, numeroEtage, 0).equals('S')){               //Si le joueur adversaire à tirer à cette emplacement mais n'a rien touché. On exclu aussi le faite que le joueur est bien pu déplacer son navire sur cette case à un tour n+1
                            System.out.print(CYAN_AR + B_ROUGE + "XX" + RESET_AR + RESET +"|");               //On affiche l'information sur la grille de jeu avec des croix rouge brillant et un fond cyan
                            continue;               //On passe au tour suivant de la boucle sans effectuer le reste de la boucle
                        }
                        if (plateauDeJeu.get(i, j, 1, 0).equals("2")){               //Si le joueur adversaire à tirer à cette emplacement et a touché un navire 
                            System.out.print(ROUGE_AR + B_ROUGE + plateauDeJeu.get(i, j, numeroEtage, 0) +""+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche les informations du navire en rouge foncé
                            continue;               //On passe au tour suivant de la boucle sans effectuer le reste de la boucle
                        }
                        if (plateauDeJeu.get(i, j, 1, 0).equals("5")){               //Si le joueur adversaire à tirer sur un sous marin mais ne la pas coulé parce-que le navire qu'il a tiré n'était pas un sous marin
                            System.out.print(CYAN_AR + ROUGE +'S'+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche le sous marin avec la couleur cyan et rouge
                            continue;               //On passe au tour suivant de la boucle sans effectuer le reste de la boucle
                        }
                    }
                    
                    //**************************
                    /*Sinon on affiche les autres emplacements du plateau de jeu normalement*/
                    
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals('_')){               //Si l'emplacement est vide
                        System.out.print("__|");               //On affiche le emplacement vide
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals('U')){               //Si l'emplacement contient un cuirasse
                        System.out.print(B_ROUGE_AR + BLANC + plateauDeJeu.get(i, j, numeroEtage, 0) +""+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche la letrre et la numero du navire avec la couleur rouge en fond
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals('C')){               //Si l'emplacement contient un croisseur
                        System.out.print(VOILET_AR + BLANC + plateauDeJeu.get(i, j, numeroEtage, 0) +""+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche la letrre et la numero du navire avec la couleur violet en fond
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals('D')){               //Si l'emplacement contient un destroyer
                        System.out.print(BLEU_AR + BLANC + plateauDeJeu.get(i, j, numeroEtage, 0) +""+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche la letrre et la numero du navire avec la couleur bleu en fond
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals('S')){               //Si l'emplacement contient un sous_marin
                        System.out.print(VERT_AR + BLANC + plateauDeJeu.get(i, j, numeroEtage, 0) +""+ plateauDeJeu.get(i, j, numeroEtage, 1)+RESET_AR +RESET+"|");               //On affiche la letrre et la numero du navire avec la couleur vert en fond
                    }
                } 
                System.out.println();               //On saute une ligne à la fin de l'affichage
            }
        }
        
        if (numeroEtage == 1 || numeroEtage == 3){               //Si on est sur la grille des tirs du joueur 
            System.out.println(GRIS_AR+ BLANC +"       Voici la grille de où vous avez tiré     ");               //On affiche le titre de la grille
            System.out.println(BLEU_AR + BLANC +"  | A| B| C| D| E| F| G| H| I| J| K| L| M| N| O|"+RESET_AR +RESET);               //On affiche les repères pour l'utilisateur
        
            for (int j=0; j<15; j++){               //On fait une boucle pour parcourir toute les lignes de la grille
            
                if (j<9) System.out.print(BLEU_AR+ BLANC +" "+(j+1)+"|"+RESET_AR +RESET);               //On affiche à chaque ligne un repère pour l'uilisateur
                else System.out.print(BLEU_AR+ BLANC +(j+1)+"|"+RESET_AR +RESET);               //Si le chiffre est supérieur à 9 on retire un espace pour eviter un décalage
            
                for (int i=0; i<15; i++){               //On fait une boucle pour parcourir toute les colonnes de la grille
               
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals("0")){               //Si le joueur a jamais tiré sur cette emplacement 
                        System.out.print("__|");               //On affiche une case vide
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals("1")){               //Si le joueur a déjà tiré sur cette case mais n'a rien touché
                        System.out.print(CYAN_AR +BLANC+ "XX" + RESET +RESET_AR+ "|");               //On affiche des croix avec blanche sur un fond cyan
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals("2")){               //Si le joueur a déjà tiré sur cette case et a touché un navire
                        System.out.print(ROUGE_AR+BLANC + "XX" + RESET+ RESET_AR +"|");               //On affiche des croix blanche sur un fonc rouge
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals("3")){               //Si le joueur a tiré avec sa fusée éclairante mais n'a rien repéré
                        System.out.print(JAUNE_AR + "__" + RESET_AR +"|");               //On affiche une case jaune sans rien dedans
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals("4")){               //Si le joueur a tiré avec sa fusée éclairante et a repéré un navire
                        System.out.print(JAUNE_AR + "--" + RESET_AR +"|");               //On affiche une case jaune avec des tirets dedans
                    }
                    if (plateauDeJeu.get(i, j, numeroEtage, 0).equals("5")){               //Si le joueur a toucher un sous-marin mais n'a pas tiré avec un sous marin
                        System.out.print(GRIS_AR + "--" + RESET_AR +"|");               //On affiche une case verte avec des tirets dedans
                    }
                } 
                System.out.println();               //On saute une ligne à la fin de l'affichage
            }
        }
    }
}
