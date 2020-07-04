package com.hen.backend.bussines; 

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  
/**
 *
 * @author Henrry Gómez
 */
public class DnaMutationTest {

    private final static Logger LOGGER = Logger.getLogger("viewResult");
    //String[] dna = {"ABCT","ACTB","ATCB","TBDB","GBDB"};//True Diagonal
    //String[] dna = {"ATCG","GCTA","AGTC","TCGA"};//False
    //String[] dna = {"AAAA","GCTA","AGTC","TCGA"};//True Horizontal
    //String[] dna = {"AACA","ACTA","AGTC","ACGA"};//True Vertical
    //String[] dna = {"AACAAAA","TCTAGTC","AGTCCCT","ACGACCA"};//True Horizontal
    //String[] dna = {"AACAAAA","TCTAGTC","AGTCC","ACGACCA"};//Falla base de adn no son todas del mismo lenght
    //String[] dna = {"GTCG","AACG","AACG","GTAA","ATCA"};//True Diagonal Inversa
   
        
        
    public DnaMutationTest() {
        
     
    }
   
    public boolean hasMutationHVD(String[] dna){
     boolean res = hasMutation(dna);
     LOGGER.log(Level.INFO,  res?"HorizontalMutation true":"HorizontalMutation false");
     String[] dna2 = getArrayDiagonal(dna);
     boolean res2 = hasMutation(dna2);
     LOGGER.log(Level.INFO,  res2?"1DiagonalMutation true":"1erDiagonalMutation false");
     String[] dna3 = flip90GradeArray(dna);
     boolean res3 = hasMutation(dna3);
     LOGGER.log(Level.INFO,  res3?"VerticalMutation true":"VerticalMutation false"); 
     String[] dna4 = getArrayDiagonal(dna3);
     boolean res4 = hasMutation(dna4);
     LOGGER.log(Level.INFO,  res4?"2DiagonalVerticalMutation true":"2DiagonalVerticalMutation false");
     String[] dna5 = flip90GradeArray(dna3);
     String[] dna6 = getArrayDiagonal(dna5);
     boolean res6 = hasMutation(dna6);
     LOGGER.log(Level.INFO,  res6?"3DiagonalVerticalMutation true":"3DiagonalVerticalMutation false");
     String[] dna7 = flip90GradeArray(dna5);
     String[] dna8 = getArrayDiagonal(dna7);
     boolean res7 = hasMutation(dna8);
     LOGGER.log(Level.INFO,  res7?"4DiagonalVerticalMutation true":"4DiagonalVerticalMutation false"); 
     
     if(res || res2 || res3 || res4 || res6 || res7){
         return true;
     }
     
     return false;
    }
    
    public boolean hasMutation(String[] dna) { 
         boolean result = false;
         for(String lineTest : dna){
             Pattern  pattern = Pattern.compile("[A]{4}|[T]{4}|[G]{4}|[C]{4}");
             Matcher  matcher = pattern.matcher(lineTest);
                if(matcher.find()){
                      return true;
                }
         } 
        return result;
    }  
     
    public String[] getArrayDiagonal(String[] dna) { 
         String[] result = new String[dna.length]; 
        for (int z = 0; z < dna.length; z++) {
            int x = z;
            int y = 0;
            while(x>-1  && y<dna[0].length()){
                  if(result[z]==null){
                        result[z] = dna[x].substring(y,y+1); //(y,y+1); 
                  } else {
                        result[z] += dna[x].substring(y,y+1);
                  }
                  x--;
                  y++;
            } 
            LOGGER.log(Level.INFO, result[z]);
        } 
        return result;
    }  
   
    public String[] flip90GradeArray(String[] dna) { 
       String[] result = new String[dna[0].length()];
        int y = 0;
        for (int i = 0; i < dna[0].length(); i++) {
            int x = dna.length-1; 
            while(x>-1 && y<dna[0].length()){
                  if(result[i]==null){
                        result[i] = dna[x].substring(y,y+1);
                  } else {
                        result[i] += dna[x].substring(y,y+1);
                  }
                  x--;
            }   
            y++;
            LOGGER.log(Level.INFO, result[i]);
        }

       return result;
    } 
    
}