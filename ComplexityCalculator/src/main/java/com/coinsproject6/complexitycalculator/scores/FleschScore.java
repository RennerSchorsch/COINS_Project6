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
    
    public static double calculateFleschScore(Text textStructure){
        double fleschScore = 206.835 - (1.015 * textStructure.getAvgSentenceLength()) - (84.6 * textStructure.getAvgHyphen());
        return Math.round(100.0 * fleschScore) / 100.0;
    }
    
    public static double calculateFleschGrade(Text textStructure){
        double fleschGrade = 0.39 * textStructure.getAvgSentenceLength() + 11.8 * textStructure.getAvgHyphen() - 15.59;
        return Math.round(100.0 * fleschGrade) / 100.0;
    }
    
}
