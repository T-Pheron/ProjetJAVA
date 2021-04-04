package bataillenavalgraphique.bataillenaval.model;

import java.io.Serializable;


public class Chrono implements Serializable{
    private long tempsDepart=0;
    private long tempsFin=0;
    private long pauseDepart=0;
    private long pauseFin=0;
    private long duree=0;

    public void start()
        {
        tempsDepart=System.currentTimeMillis();
        tempsFin=0;
        pauseDepart=0;
        pauseFin=0;
        duree=0;
        }

    public void pause()
        {
        if(tempsDepart==0) {return;}
        pauseDepart=System.currentTimeMillis();
        }

    public void resume()
        {
        if(tempsDepart==0) {return;}
        if(pauseDepart==0) {return;}
        pauseFin=System.currentTimeMillis();
        tempsDepart=tempsDepart+pauseFin-pauseDepart;
        tempsFin=0;
        pauseDepart=0;
        pauseFin=0;
        duree=0;
        }
        
    public void stop()
        {
        if(tempsDepart==0) {return;}
        tempsFin=System.currentTimeMillis();
        duree=(tempsFin-tempsDepart) - (pauseFin-pauseDepart);
        tempsDepart=0;
        tempsFin=0;
        pauseDepart=0;
        pauseFin=0;
        }        

    public long getDureeSec()
        {
        return duree/1000;
        }
    
     public static String temps(long tempsEnSec) {

        // IN : (long) temps en secondes
        // OUT : (String) temps au format texte : "1 h 26 min 3 s"

        int h  = (int) (tempsEnSec / 3600);
        int m  = (int) ((tempsEnSec % 3600) / 60);
        int s  = (int) (tempsEnSec % 60);

        String r="";

        if(h>0) {r+=h+" h ";}
        if(m>0) {r+=m+" min ";}
        if(s>0) {r+=s+" s";}
        if(h<=0 && m<=0 && s<=0) {r="0 s";}

        return r;
        }
      
    public String getDureeTxt()
        {
        return temps(getDureeSec());
        }
}
