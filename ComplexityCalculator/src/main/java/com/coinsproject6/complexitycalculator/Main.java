/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator;

import com.coinsproject6.complexitycalculator.scores.CLIScore;
import com.coinsproject6.complexitycalculator.scores.FleschScore;
import com.coinsproject6.complexitycalculator.text.Text;
import com.coinsproject6.complexitycalculator.text.TextAnalyzer;
import java.io.IOException;

/**
 *
 * @author Georg
 */
public class Main {
    
    public static void main(String args[]) {
           
            TextAnalyzer analyzer = new TextAnalyzer();
            try{
            Text sample = analyzer.analyzeText("Paste your sample text in the field below. A longer text provides a more accurate measurement. Select measurement method and click 'calculate score' to see the score for your text. The result is displayed below the form.");
            
            System.out.println("Anzahl Wörter: "+sample.getAmountWords());
            System.out.println("Anzahl an Silben: "+sample.getAmountHypen());
            System.out.println("Anzahl an Sätzen: "+sample.getAmountSentence());
            System.out.println("Anzahl der durchschnittlichen Satzlänge: "+sample.getAvgSentenceLength());
            System.out.println("Anuahl der durchschnittlichen Anzahl an Silben: "+sample.getAvgHypen());
            System.out.println("Anzahl an Buchstaben: " + sample.getAmountLetters());
            System.out.println("Durchschnittliche Anzahl an Buchstaben pro 100 Wörter: " + sample.getAvgLetters());
            System.out.println("################################################");
            System.out.println("Flesch Score: " + FleschScore.calculateFleschScore(sample)); 
            System.out.println("Flesch Grade: " + FleschScore.calculateFleschGrade(sample));
            System.out.println("CLI Score: " + CLIScore.calculateCLIScore(sample));
            
            } catch (IOException e) {
                System.err.println("Fehler beim analysieren des Textes. " + e.getMessage());
            }
    }
    
}
