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
public class AutomatedReadabilityIndex {
    
    public static double calculateARI(Text sample){
        double ari = 4.71 * (sample.getAmountLetters()/ sample.getAmountWords()) + 0.5 * (sample.getAmountWords() / sample.getAmountSentence()) - 21.43;
        return Math.round(100.0 * ari) / 100.0;
    }
}
