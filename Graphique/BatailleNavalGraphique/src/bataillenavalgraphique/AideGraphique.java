/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavalgraphique;

import java.io.*;


/**
 *
 * @author Théric PHERON
 */
public class AideGraphique  {

    public AideGraphique(){

        try(InputStream iS = new FileInputStream("fichiers_text/fichierGagne.txt");          //On lui demande d'aller dans le fichier fichierGagne qui se trouve dans le dossier dossier_aide
            InputStreamReader iSR = new InputStreamReader(iS);          //On déclare une variable de type InputStreamReader
            BufferedReader bR = new BufferedReader (iSR)){          //On déclare une variable de type BufferedReader
                String line;            //On initialise une chaine de caractere
                while((line = bR.readLine())!=null){            //Tant que la ligne n'est pas vide
                    System.out.println(line);           //On affiche la ligne
                }   
            }catch(IOException e){          //Si il n'arrive pas a ouvrir le fichier
                System.out.println("Erreur_menuAide! Impossible d'ouvrir le fichier");           //On affiche un message d'erreur
            }
        
    }
    
}
