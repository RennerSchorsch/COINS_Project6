/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator.text;

import java.util.HashMap;
import java.util.Map;

/**
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
        setTextInfo(text);
        setAmountWords(text);
        
    }
    
    public void setTextInfo(String text){        
        textInfo = null;
    }
    
    public Map<String, Integer> getTextInfo(){
    
    return textInfo;
    }
    
    public void setAmountWords(String text){   
        amountWords = 0;
    }
    
    public Integer getAmountWords(){
        
        return amountWords;
    }
    
    public void setAmountSentence(String text){
        amountSentence = 0;
    }
    
    public Integer getAmountSentence(){
        
        return amountSentence;
    }
    
    public void setAmountLetters(String text){
        amountLetters = 0;
    }
    
    public Integer getAmountLetters(){      
        return amountLetters;
    }
    
    public void setAvgLetters(String text){    
        avgLetters = getAmountLetters() / (getAmountWords() / 100);
    }
    
    public Double getAvgLetters(){       
        return avgLetters;
    }
    
    public void setAvgSentence(String text){
        avgSentence = 0;
    }
    
    public double getAvgSentence(){       
        return avgSentence;
    }
}
