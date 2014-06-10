/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator;

import com.coinsproject6.complexitycalculator.text.Text;
import com.coinsproject6.complexitycalculator.text.TextAnalyzer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.coinsproject6.complexitycalculator.readingCSV.Reader;
import com.coinsproject6.complexitycalculator.scores.ScoreCalculatorEN;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 *
 * @author Georg
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    private static String tweetsPath = "/tweets";
    private static String defaultFile =  tweetsPath + "/tweetsRasBaraka";
    
    public static void main(String[] args) {

        String filename = "";
        logger.info("Start der Komplexitätsberechnung.");

        if (args.length == 0) {
            filename = Main.class.getResource(defaultFile).getPath();
            logger.info("Keine Parameter. Filename: " + filename);
        } else {
            filename = args[0];
            logger.info("Mit Parameter. Filename: " + filename);
        }

        TextAnalyzer analyzer = new TextAnalyzer();
        try {
            Reader reader = new Reader();
            List<String> content = reader.txtReader(new File(filename));
            List<Text> tweets = new ArrayList<Text>();

            logger.info("Anzahl der Tweets: " + content.size());

            for (String tmp : content) {
                tweets.add(analyzer.analyzeText(tmp));
            }

            logger.info(ScoreCalculatorEN.calculateTextComplexity(tweets));

        } catch (IOException e) {
            logger.error("Fehler beim analysieren des Textes. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Fehler beim analysieren des Textes. " + e.getMessage());
        }
    }

}
