/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soxaddnoise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author majhamza
 */
public class SoxAddNoise {

   //path 1 est le path vers le fichier audio auquel on veut ajouter du bruit
   // path2 est le path du fichier contenant le bruit
   public static void sox_add_noise(String path1, String path2) throws IOException, InterruptedException{
       //System.out.println("sox -m "+path1+" "+path2+" "+path1.substring(0, path1.lastIndexOf('.'))+"c.wav");
       Process p;
	    p = Runtime.getRuntime().exec("sox -m "+path1+" "+path2+" "+path1.substring(0, path1.lastIndexOf('.'))+"c.wav");
	    p.waitFor();
        }
   
   // la commande sox enleve une partie d'un fichier audio, dont la durée est donnée en paramètre
   // les paramètres sub et ind, sont utilisé dans le renomage des fichiers créés
   public static void trim(String path1, String sub, int ind) throws IOException, InterruptedException{
      //System.out.println("sox "+path1.substring(0, path1.lastIndexOf('.'))+"c.wav"+" "+path1.substring(0, path1.lastIndexOf('.'))+"_"+sub+ind+".wav"+" trim 0 "+length(path1));
       Process p;
	    p = Runtime.getRuntime().exec("sox "+path1.substring(0, path1.lastIndexOf('.'))+"c.wav"+" "+path1.substring(0, path1.lastIndexOf('.'))+"_"+sub+ind+".wav"+" trim 0 "+length(path1));
	    p.waitFor();
   }
 //retourne la longeur d'un fichier audio
 public static String length(String path1) throws IOException, InterruptedException{
     //System.out.println("soxi -D "+path1);
     Process p;
       p = Runtime.getRuntime().exec("soxi -D "+path1);
	    p.waitFor();
            BufferedReader stdInput = new BufferedReader(new 
     InputStreamReader(p.getInputStream()));

     BufferedReader stdError = new BufferedReader(new 
     InputStreamReader(p.getErrorStream()));

// read the output from the command
//System.out.println("Here is the standard output of the command:\n");
String s = "";
while ((s = stdInput.readLine()) != null) {
   // System.out.println(s);
    return s;
}
return s;

// read any errors from the attempted command
//System.out.println("Here is the standard error of the command (if any):\n");
//while ((s = stdError.readLine()) != null) {
    //System.out.println(s);
   //}
 }
 
 //supprimer les fichiers intermédiaires
 public static void remove_files(String path1) throws IOException, InterruptedException{
 Process p;
 p=Runtime.getRuntime().exec("rm "+path1+"*cc.wav");
 p.waitFor();
 }
    public static void main(String[] args) throws IOException, InterruptedException {
    String[] loc={"ismael_002_M_21/","ismael_005_M_23/","ismael_008_M_22/","ismael_012_F_19/","ismael_003_M_25/","ismael_006_F_23/","ismael_009_F_23/","ismael_004_M_24/","ismael_007_F_27/","ismael_011_F_26/"};
    String path1="/home/majhamza/Téléchargements/Reco_locuteurs/Ismael/bruits/";
    String Path2="/home/majhamza/Téléchargements/Reco_locuteurs/Ismael/bruits/Cafe/";
        String[] noise={"BGD_150203_010_CAF.CH1c.wav","BGD_150204_030_CAF.CH1c.wav","BGD_150204_020_CAF.CH1c.wav","BGD_150205_040_CAF.CH1c.wav"};
    //for(int k=0; k<loc.length; k++){
        String Path1=path1+loc[0];
        System.out.println(loc[0]);
     for(int i=0; i<noise.length; i++)
    {
        for(int j=116; j<136; j++){
        sox_add_noise(Path1+j+"c.wav",Path2+noise[i]);
        trim(Path1+j+"c.wav","cafe",i);
        }
    }
    remove_files(Path1);}
    //}
}
