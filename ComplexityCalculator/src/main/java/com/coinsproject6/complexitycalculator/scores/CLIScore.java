/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator.scores;

import com.coinsproject6.complexitycalculator.text.Text;

/**
 * Coleman–Liau index
 * 
 * @author Georg
 */
public class CLIScore {
    
    public static double calculateCLIScore(Text sample){
        double cliScore = 0.0588 * sample.getAvgLetters() - 0.296 * sample.getAvgAmountSentence() - 15.8;
        return Math.round(100.0 * cliScore) / 100.0;
    }
 
}
