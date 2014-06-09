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

    public static double calculateTextComplexity(Text sample) {

        logger.debug("Starting the calculating of the complexity score.");

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

        return (ari + cli + fs + gfi) / 4;
    }

    public static double calculateTextComplexity(List<Text> samples) {

        double tmp = 0.00;

        for (Text sample : samples) {
            if (!sample.getTextContent().isEmpty()) {
                tmp = tmp + calculateTextComplexity(sample);
            }
        }

        return tmp / samples.size();
    }
}
