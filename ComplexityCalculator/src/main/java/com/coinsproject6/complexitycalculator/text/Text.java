/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator.text;

import java.io.IOException;
import java.util.Map;
import net.davidashen.text.Hyphenator;
import net.davidashen.util.ErrorHandler;

/**
 * DRAFT - Erster Entwurf, es sind noch viele Anpassungen nötig!!
 * Komplexe Datenstruktur, die den Text mit allen nötigen Informationen repräsentiert.
 * 
 * @author Georg
 */
public class Text {
    
    private String text;
    private int amountWords;
    private int amountSentence;
    private int amountLetters;
    private int amountHypen;
    private double avgLetters;
    private double avgSentenceLength;
    private double avgAmountSentence;
    private double avgHypen;
    
    public Text(String text){
        this.text = text;
        try{
            anilyzeText(text);
        } catch (IOException e){
            System.err.println("Fehler beim Zählen der Sylben.");
        }
        setAvgHypen();
        setAvgSentenceLength();
        setAvgAmountSentence();
        setAvgLetters();
    }
    
    //TODO muss in eine extra Klasse ausgelagert werden, da komplexe Datenstruktur eigentlich nur Getter und Setter
    private void anilyzeText(String text) throws IOException {
        
        String currentWord = "";
        int wordsInSentence = 0;
        
        for (int i = 0; i < text.length();i++) {    
            
            //TODO Was passiert eigetnlich mit Nummern, die können doch eigentlich auch zur komplexität beitragen.
            if(Character.isLetter(text.charAt(i))){
                setAmountLetters(getAmountLetters()+1);
                currentWord += text.charAt(i);
                
            } else {
              
                if(!currentWord.isEmpty()){  
                    amountHypen += getWordHyphenCount(currentWord);
                    wordsInSentence++;
                    currentWord = "";
                }
                
                if(isSentenceEnd(text.charAt(i))){
                    setAmountSentence(getAmountSentence()+1);
                    setAmountWords(getAmountWords()+wordsInSentence);
                    wordsInSentence = 0;                               
                }
            }
        }
    }
    
    private boolean isSentenceEnd(char letter) {
         
        if ((33 == letter) || (46 == letter) || (63 == letter)) {
            return true;
        } else {
            return false;
        }
         
    }
    
    public int getWordHyphenCount(String word) throws IOException{
    
        Hyphenator hp = new net.davidashen.text.Hyphenator();
        hp.setErrorHandler(new ErrorHandler() {
        public void debug(String guard,String s) {}
                public void info(String s) {System.err.println(s);}
                public void warning(String s) {System.err.println("WARNING: "+s);}
                public void error(String s) {System.err.println("ERROR: "+s);}
                public void exception(String s,Exception e) {System.err.println("ERROR: "+s); e.printStackTrace(); }
                public boolean isDebugged(String guard) {return false;}
              });
        
        hp.loadTable(new java.io.BufferedInputStream(new java.io.FileInputStream("hyphen.tex")));
        
        String hyphenatedWord = hp.hyphenate(word, 2, 3);
        int hCount = 1;
      
            for (int i=0; i < hyphenatedWord.length(); i++) {
                if (hyphenatedWord.charAt(i) == 173) {
                        hCount++;
                }
            }
        return hCount;
        
    }
    
    private void setAmountWords(int amount){   
        amountWords = amount;
    }
    
    public int getAmountWords(){
        
        return amountWords;
    }
    
    private void setAmountSentence(int amount){
        amountSentence = amount;
    }
    
    public int getAmountSentence(){
        
        return amountSentence;
    }
    
    private void setAmountLetters(int amount){
        amountLetters = amount;
    }
    
    public int getAmountLetters(){      
        return amountLetters;
    }
    
    private void setAmountHypen(int amount){
        amountLetters = amount;
    }
    
    public int getAmountHypen(){      
        return amountLetters;
    }
    
    private void setAvgLetters(){    
        avgLetters = (double) amountLetters / ((double) amountWords / 100);
    }
    
    public Double getAvgLetters(){       
        return avgLetters;
    }
    
    private void setAvgSentenceLength(){
        avgSentenceLength = (double) amountWords / (double) amountSentence;
    }
    
    public double getAvgSentenceLength(){       
        return avgSentenceLength;
    }
    
    private void setAvgAmountSentence(){
        avgAmountSentence = (double) amountSentence / ((double) amountWords / 100);
    }
    
    public double getAvgAmountSentence(){       
        return avgAmountSentence;
    }
    
    private void setAvgHypen(){
        avgHypen = (double) amountHypen / (double) amountWords;
    }
    
    public double getAvgHypen(){       
        return avgHypen;
    }
}
