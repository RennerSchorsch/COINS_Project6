/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator.scores;

import com.coinsproject6.complexitycalculator.text.Text;

/**
 *
 * @author Georg
 */
public class CLIScore {
    
    private double cliScore;
    private double avgLetters;
    private double avgSentence;
    
    public CLIScore(Text textStucture){
        
        this.avgLetters = textStucture.getAvgLetters();
        this.avgSentence = textStucture.getAvgSentence();
        setCliScore();
    }
    
    private void setCliScore(){
        cliScore = 0.0588 * avgLetters - 0.296 * avgSentence - 15.8;
    }
    
    public double getCLIScore(){
        return cliScore;
    }
}
