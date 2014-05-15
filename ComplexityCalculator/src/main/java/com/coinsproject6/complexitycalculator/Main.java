/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator;

import com.coinsproject6.complexitycalculator.scores.FleschScore;
import com.coinsproject6.complexitycalculator.text.Text;
import java.io.IOException;

/**
 *
 * @author Georg
 */
public class Main {
    
    public static void main(String args[]) {
        
        try{    
            Text beispiel = new Text("Paste your sample text in the field below. A longer text provides a more accurate measurement. Select measurement method and click 'calculate score' to see the score for your text. The result is displayed below the form.");
        
            System.out.println(FleschScore.calculateFleschScore(beispiel)); 
            System.out.println(beispiel.getAmountWords());
            System.out.println(beispiel.getAmountHypen());
            System.out.println(beispiel.getAmountSentence());
            System.out.println(beispiel.getAvgLengthSentence());
            System.out.println(beispiel.getAvgHypen());
        } catch (IOException e){
            System.err.println("Fehler im Analysieren");
        }
        
    }
    
}
