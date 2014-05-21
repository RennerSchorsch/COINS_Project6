/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator.text;

/**
 * DRAFT - Erster Entwurf, es sind noch viele Anpassungen nötig!! Komplexe
 * Datenstruktur, die den Text mit allen nötigen Informationen repräsentiert.
 *
 * @author Georg
 */
public class Text {

    private String text;
    private String rawText;
    private int amountWords;
    private int amountSentence;
    private int amountLetters;
    private int amountHyphen;
    private double avgLetters;
    private double avgSentenceLength;
    private double avgAmountSentence;
    private double avgHyphen;
    
    private int amountHyperlinks;
    private int amountReferences;
   
      
    public Text(String text) {
         this.rawText = text;
         clearText();
    }

    public void setTextContent(String text) {
        this.rawText = text;
        clearText();
    }
    


    public String getRawTextContent() {
        return rawText;
    }

    public String getTextContent() {      
        return text;      
    }
    
    
    
    public void setAmountWords(int amount) {
        amountWords = amount;
    }

    public int getAmountWords() {
        return amountWords;
    }

    public void setAmountSentence(int amount) {
        amountSentence = amount;
    }

    public int getAmountSentence() {
        return amountSentence;
    }

    public void setAmountLetters(int amount) {
        amountLetters = amount;
    }

    public int getAmountLetters() {
        return amountLetters;
    }

    public void setAmountHyphen(int amount) {
        amountHyphen = amount;
    }

    public int getAmountHyphen() {
        return amountHyphen;
    }

    public void setAvgLetters() {
        avgLetters = (double) amountLetters / ((double) amountWords / 100);
    }

    public Double getAvgLetters() {
        
        setAvgLetters();
        return avgLetters;
    }

    public void setAvgSentenceLength() {
        avgSentenceLength = (double) amountWords / (double) amountSentence;
    }

    public double getAvgSentenceLength() {
        
        setAvgSentenceLength();
        return avgSentenceLength;
    }

    public void setAvgAmountSentence() {
        avgAmountSentence = (double) amountSentence / ((double) amountWords / 100);
    }

    public double getAvgAmountSentence() {
        
        setAvgAmountSentence();
        return avgAmountSentence;
    }

    public void setAvgHyphen() {
        avgHyphen = (double) amountHyphen / (double) amountWords;
    }

    public double getAvgHyphen() {
        
        setAvgHyphen();
        return avgHyphen;
    }
    
 
    private void clearText() {
    
        this.text = rawText;
        clearHyperlinks();
        clearReferences();
        removeHashSymbols();
     
    }
    
    // method for removing hash-signs in twitter (only removes the "#"-character, not the whole string)
    private void removeHashSymbols () {
        
        text = text.replaceAll("#", "");    
    }
    
    
    // method for removing all twitter-references (strings beginning with "@"-character, removes whole string)
    private void clearReferences(){
        
        String clearedText = "";
        amountReferences = 0;
        Boolean ignoreUntilNextSpace = false;
        
        for (int i = 0; i < text.length(); i++) {
               
            if (text.charAt(i) == "@".charAt(0)) {
                ignoreUntilNextSpace = true;
                amountReferences++;    
            }
            
            else if (text.charAt(i) == " ".charAt(0)) {
                ignoreUntilNextSpace = false;
            }
            
            if (!ignoreUntilNextSpace) {
                clearedText += text.charAt(i);
            }
        }
        
        this.text = clearedText;
        
    }
    
    
    
    // method for removing hyperlinks (removes whole string beginning with http until next space-character)
    private void clearHyperlinks() {
        
        String clearedText = "";
        amountHyperlinks = 0;
        Boolean ignoreUntilNextSpace = false;
        
        for (int i = 0; i < text.length()-4; i++) {
               
            if (text.charAt(i) == "h".charAt(0)) {
                if (text.charAt(i+1) == "t".charAt(0)) {
                    if (text.charAt(i+2) == "t".charAt(0)) {
                        if (text.charAt(i+3) == "p".charAt(0)) {
                            ignoreUntilNextSpace = true;
                            amountHyperlinks++;
                        }
                    }
                }
            }
            else if (text.charAt(i) == " ".charAt(0)) {
                ignoreUntilNextSpace = false;
            }
            
             if (!ignoreUntilNextSpace) {
                clearedText += text.charAt(i);
            }
        }
        
        this.text = clearedText;
    }
}
