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
public class GunningFogIndex {
    
    public static double calculateGunningFogIndex(Text sample){
        
        double gunningFogIndex = 0.4 * ((sample.getAmountWords() / sample.getAmountSentence()) + 100 * (sample.getAmountComplexSyllablesWords() / sample.getAmountWords()));
        return Math.round(100.0 * gunningFogIndex) / 100.0;
    }
}
