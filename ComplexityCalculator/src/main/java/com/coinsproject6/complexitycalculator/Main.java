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
import readingCSV.Reader;

/**
 *
 * @author Georg
 */
public class Main {

    public static void main(String args[]) {

        TextAnalyzer analyzer = new TextAnalyzer();
        try {
            Reader reader = new Reader();
            List<String> content = reader.txtReader(new File("/Users/Georg/Documents/Uni/SNA_Seminar_2014/COINS_Project6/ComplexityCalculator/src/main/resources/tweetsNewark"));
            //List<String> content = reader.txtReader(new File("/Users/Georg/Documents/Uni/SNA_Seminar_2014/COINS_Project6/ComplexityCalculator/src/main/resources/tweetsRasBaraka"));
            List<Text> tweets = new ArrayList<Text>();
            double gesamtFleschScore = 0;
            double gesamtFleschGrade = 0;
            double gesamtCLIScore = 0;

            System.out.println("Anzahl der Tweets: " + content.size());

            for (String tmp : content) {
                tweets.add(analyzer.analyzeText(tmp));
            }

            /*Text sample = analyzer.analyzeText("RT @errollouis: Hope this all works out");
            //Election is called for newark election. Waiting for the victory speech.");
            // + "Paste your sample text in the field below. A longer text provides a more accurate measurement. Select measurement method and click 'calculate score' to see the score for your text. The result is displayed below the form.");
            System.out.println("Anzahl Wörter: " + sample.getAmountWords());
            System.out.println("Anzahl an Silben: " + sample.getAmountHypen());
            System.out.println("Anzahl an Sätzen: " + sample.getAmountSentence());
            System.out.println("Anzahl der durchschnittlichen Satzlänge: " + sample.getAvgSentenceLength());
            System.out.println("Anuahl der durchschnittlichen Anzahl an Silben: " + sample.getAvgHypen());
            System.out.println("Anzahl an Buchstaben: " + sample.getAmountLetters());
            System.out.println("Durchschnittliche Anzahl an Buchstaben pro 100 Wörter: " + sample.getAvgLetters());
            System.out.println("################################################");
            System.out.println("Flesch Score: " + FleschScore.calculateFleschScore(sample));
            System.out.println("Flesch Grade: " + FleschScore.calculateFleschGrade(sample));
            System.out.println("CLI Score: " + CLIScore.calculateCLIScore(sample));*/

            for (Text sample : tweets) {
                gesamtFleschScore = gesamtFleschScore + FleschScore.calculateFleschScore(sample);
                gesamtFleschGrade = gesamtFleschGrade + FleschScore.calculateFleschGrade(sample);
                gesamtCLIScore = gesamtCLIScore + CLIScore.calculateCLIScore(sample);
            }

            System.out.println("Durchschnitt Flesch Score: " + gesamtFleschScore/content.size());
            System.out.println("Durchschnitt Flesch Grade: " + gesamtFleschGrade/content.size());
            System.out.println("Durchschnitt CLI Score: " + gesamtCLIScore/content.size());

        } catch (IOException e) {
            System.err.println("Fehler beim analysieren des Textes. " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Fehler beim analysieren des Textes. " + e.getMessage());
        }
    }

}
