/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator.text;

import java.util.HashMap;
import java.util.Map;

/**
 * DRAFT - Erster Entwurf, es sind noch viele Anpassungen nötig!!
 * Komplexe Datenstruktur, die den Text mit allen nötigen Informationen repräsentiert.
 * 
 * @author Georg
 */
public class Text {
    
    private String text;
    private Map<String, Integer> textInfo;
    private Integer amountWords;
    private Integer amountSentence;
    private Integer amountLetters;
    private double avgLetters;
    private double avgSentence;
    
    public Text(String text){
        this.text = text;
        anilyzeText(text);
    }
    
    //TODO muss in eine extra Klasse ausgelagert werden, da komplexe Datenstruktur eigentlich nur Getter und Setter
    private void anilyzeText(String text){
        
        String currentWord = "";
        Integer wordsInSentence = 0;
        
        for (int i = 0; i < text.length();i++) {    
            
            //TODO Was passiert eigetnlich mit Nummern, die können doch eigentlich auch zur komplexität beitragen.
            if(Character.isLetter(text.charAt(i))){
                setAmountLetters(getAmountLetters()+1);
                currentWord += text.charAt(i);
                
            } else {
              
                if(!currentWord.isEmpty()){  
                    //hyphenCount += getWordHyphenCount(currentWord);
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
    
    /*public int getWordHyphenCount(String word) {
    
        String hyphenatedWord = hp.hyphenate(word, 2, 3);
        int hCount = 1;
      
            for (int i=0; i < hyphenatedWord.length(); i++) {
                if (hyphenatedWord.charAt(i) == 173) {
                        hCount++;
                }
            }
        return hCount;
        
    }*/
    
    private void setTextInfo(String text){        
        textInfo = null;
    }
    
    public Map<String, Integer> getTextInfo(){
    
    return textInfo;
    }
    
    private void setAmountWords(Integer amount){   
        amountWords = amount;
    }
    
    public Integer getAmountWords(){
        
        return amountWords;
    }
    
    private void setAmountSentence(Integer amount){
        amountSentence = amount;
    }
    
    public Integer getAmountSentence(){
        
        return amountSentence;
    }
    
    private void setAmountLetters(Integer amount){
        amountLetters = amount;
    }
    
    public Integer getAmountLetters(){      
        return amountLetters;
    }
    
    private void setAvgLetters(){    
        avgLetters = getAmountLetters() / (getAmountWords() / 100);
    }
    
    public Double getAvgLetters(){       
        return avgLetters;
    }
    
    private void setAvgSentence(){
        avgSentence = 0;
    }
    
    public double getAvgSentence(){       
        return avgSentence;
    }
}
