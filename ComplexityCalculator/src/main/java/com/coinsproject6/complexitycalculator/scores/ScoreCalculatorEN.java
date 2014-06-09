/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator.scores;

import com.coinsproject6.complexitycalculator.text.Text;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * This class providing the calculated complexity Score for English texts.
 * 
 * @author Georg
 */
public class ScoreCalculatorEN {
    
    private static final Logger logger = Logger.getLogger(ScoreCalculatorEN.class);
    
    public static double calculateTextComplexity(Text sample){
        
        logger.debug("Starting the calculating of the complexity score.");
        
        logger.error("Text: " + sample.getTextContent());
        logger.error("AmountWords: " + sample.getAmountWords());
        logger.error("AmountSentence: " + sample.getAmountSentence());
        
        double ari = AutomatedReadabilityIndex.calculateARI(sample);
        double cli = CLIScore.calculateCLIScore(sample);
        double fs = FleschScore.calculateFleschGrade(sample);
        double gfi = GunningFogIndex.calculateGunningFogIndex(sample);
        
        logger.error("Ari: " + ari);
        logger.error("Cli: " + cli);
        logger.error("FS: " + fs);
        logger.error("Gfi: " + gfi);
        
        logger.debug("End of the calculating.");
        
        return (ari+cli+fs+gfi)/4;
    }
    
    public static double calculateTextComplexity(List<Text> samples){
        
        double tmp = 0.00;
        
        for(Text sample : samples){
            tmp = tmp + calculateTextComplexity(sample);
        }
        
        return tmp/samples.size();
    }
}
