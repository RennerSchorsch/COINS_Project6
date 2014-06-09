/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator;

import com.coinsproject6.complexitycalculator.scores.CLIScore;
import com.coinsproject6.complexitycalculator.scores.FleschScore;
import com.coinsproject6.complexitycalculator.text.Text;
import com.coinsproject6.complexitycalculator.text.TextAnalyzer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.coinsproject6.complexitycalculator.readingCSV.Reader;
import com.coinsproject6.complexitycalculator.scores.ScoreCalculatorEN;
import org.apache.log4j.Logger;

/**
 *
 * @author Georg
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    private static String defaultFile = System.getProperty("user.dir") + "/src/main/resources/com/coinsproject6/complexitycalculator/tweets/tweetsRasBaraka";
    private static boolean analyse = true;

    public static void main(String[] args) {

        String filename = "";

        if (args.length == 0) {
            filename = defaultFile;
        } else {
            filename = args[0];
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

            ScoreCalculatorEN.calculateTextComplexity(tweets);

        } catch (IOException e) {
            logger.error("Fehler beim analysieren des Textes. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Fehler beim analysieren des Textes. " + e.getMessage());
        }
    }

}
