/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator;

import com.coinsproject6.complexitycalculator.scores.CLIScore;
import com.coinsproject6.complexitycalculator.scores.FleschScore;
import com.coinsproject6.complexitycalculator.text.Text;
import java.io.IOException;

/**
 *
 * @author Georg
 */
public class Main {
    
    public static void main(String args[]) {
           
            Text beispiel = new Text("Paste your sample text in the field below. A longer text provides a more accurate measurement. Select measurement method and click 'calculate score' to see the score for your text. The result is displayed below the form.");
        
            System.out.println(FleschScore.calculateFleschScore(beispiel)); 
            System.out.println("CLI Score: " + CLIScore.calculateCLIScore(beispiel));
            System.out.println("Anzahl Wörter: "+beispiel.getAmountWords());
            System.out.println("Anzahl an Silben: "+beispiel.getAmountHypen());
            System.out.println("Anzahl an Sätzen: "+beispiel.getAmountSentence());
            System.out.println("Anzahl der durchschnittlichen Satzlänge: "+beispiel.getAvgSentenceLength());
            System.out.println("Anuahl der durchschnittlichen Anzahl an Silben: "+beispiel.getAvgHypen());
            System.out.println("Anzahl an Buchstaben: " + beispiel.getAmountLetters());
            System.out.println("Durchschnittliche Anzahl an Buchstaben pro 100 Wörter: " + beispiel.getAvgLetters());
        
    }
    
}
