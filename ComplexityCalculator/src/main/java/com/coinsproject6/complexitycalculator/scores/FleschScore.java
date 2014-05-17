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
public class FleschScore {
    
    private static double fleschScore;    
    private static double fleschGrade;
    
    public static double calculateFleschScore(Text textStructure){
        fleschScore = 206.835 - (1.015 * textStructure.getAvgSentenceLength()) - (84.6 * textStructure.getAvgHypen());
        return fleschScore;
    }
    
    public static double calculateFleschGrade(Text textStructure){
        fleschGrade = 0.39 * textStructure.getAvgSentenceLength() + 11.8 * textStructure.getAvgHypen() - 15.59;
        return fleschGrade;
    }
    
}
