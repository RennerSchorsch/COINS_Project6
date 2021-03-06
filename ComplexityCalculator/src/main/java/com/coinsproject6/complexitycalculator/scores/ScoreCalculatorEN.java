/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator.scores;

import com.coinsproject6.complexitycalculator.text.Text;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * This class providing the calculated complexity Score for English texts.
 *
 * @author Georg
 */
public class ScoreCalculatorEN {

    private static final Logger logger = Logger.getLogger(ScoreCalculatorEN.class);
    
    /**
     * 
     * @param sample
     * @return
     * @throws IOException 
     */
    public static double calculateTextComplexity(Text sample) throws IOException {

        logger.debug("Starting the calculating of the complexity score.");

        if (!sample.getTextContent().isEmpty()) {
            logger.debug("##################################");
            logger.debug("Textinhalt: " + sample.getTextContent());
            logger.debug("Anzahl an Zeichen: " + sample.getAmountLetters());
            logger.debug("Anzahl an Wörtern: " + sample.getAmountWords());
            logger.debug("Anzahl an Silben: " + sample.getAmountHyphen());
            logger.debug("Anzahl an Sätzen: " + sample.getAmountSentence());
            logger.debug("Anzahl an komplexen Wörtern: " + sample.getAmountComplexSyllablesWords());
            logger.debug("Durchschnittlich Zeichen Anzahl: " + sample.getAvgLetters());
            logger.debug("Durchschnittlich Silben Anzahl: " + sample.getAvgHyphen());
            logger.debug("Durchschnittlich Satz Anzahl: " + sample.getAvgAmountSentence());
            logger.debug("Durchschnittlich Satz Länge: " + sample.getAvgSentenceLength());

            double ari = AutomatedReadabilityIndex.calculateARI(sample);
            double cli = CLIScore.calculateCLIScore(sample);
            double fs = FleschScore.calculateFleschGrade(sample);
            double gfi = GunningFogIndex.calculateGunningFogIndex(sample);

            logger.debug("Ari: " + ari);
            logger.debug("Cli: " + cli);
            logger.debug("FS: " + fs);
            logger.debug("Gfi: " + gfi);
            logger.debug("##################################");

            logger.debug("End of the calculating.");

            //calculating the score for the given text with the weight of
            // ari = 1 ### cli = 1 ### fs = 2 ### gif = 1
            return (ari + cli + (fs * 2) + gfi) / 5;
        } else {
            logger.error("Error with the tweet: " + sample.getTextContent());
            throw new IOException();
        }

    }

    /**
     * 
     * @param samples
     * @return
     * @throws IOException 
     */
    public static double calculateTextComplexity(List<Text> samples) throws IOException {

        double tmp = 0.00;

        for (Text sample : samples) {
            if (!sample.getTextContent().isEmpty()) {
                tmp = tmp + calculateTextComplexity(sample);
            } else {
                samples.remove(sample);
            }
        }

        return tmp / samples.size();
    }
}
