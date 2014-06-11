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
import org.apache.log4j.Logger;

/**
 *
 * @author Georg
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    private static String tweetsPath = "/benchmarking";
    private static String defaultFile = tweetsPath + "/Schwer.txt";
    private static boolean zeilenDokumente = false;

    public static void main(String[] args) {

        String filename = "";
        logger.info("Start der Komplexitätsberechnung.");

        if (args.length == 0) {
            filename = Main.class.getResource(defaultFile).getPath();
            logger.info("Keine Parameter. Filename: " + filename);
        } else if (args.length == 1){
            filename = args[0];
            logger.info("Mit Parameter. Filename: " + filename);
        } else if (args.length >= 2){
            filename = args[0];
            logger.info("Mit Parameter. Filename: " + filename);
            if(args[1].equalsIgnoreCase("true")){
                zeilenDokumente = true;
            } else if (args[1].equalsIgnoreCase("false")){
                zeilenDokumente = false;
            } else {
                logger.error("Wrong parameter decleration. The default parameters will be used");
                zeilenDokumente = false;
            }            
        }

        TextAnalyzer analyzer = new TextAnalyzer();
        try {
            Reader reader = new Reader();

            if (zeilenDokumente) {

                List<String> content = reader.txtReader(new File(filename));
                List<Text> tweets = new ArrayList<Text>();
                logger.info("Anzahl der Tweets: " + content.size());

                for (String tmp : content) {
                    tweets.add(analyzer.analyzeText(tmp));
                }

                logger.info("Complexity of the given Tweets: " + ScoreCalculatorEN.calculateTextComplexity(tweets));
            } else {
                
                String content = reader.rowReader(new File(filename));
                
                Text tweets = analyzer.analyzeText(content);
                logger.info("Complexity of the given text: " + ScoreCalculatorEN.calculateTextComplexity(tweets));
            }

        } catch (IOException e) {
            logger.error("Fehler beim analysieren des Textes. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Fehler beim analysieren des Textes. " + e.getMessage());
        }
    }

}
