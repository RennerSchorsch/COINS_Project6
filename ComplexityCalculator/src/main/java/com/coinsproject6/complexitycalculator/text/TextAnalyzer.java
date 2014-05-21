/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator.text;

import java.io.IOException;
import net.davidashen.text.Hyphenator;
import net.davidashen.util.ErrorHandler;

/**
 *
 * @author Georg
 */
public class TextAnalyzer {

    public Text analyzeText(String text) throws IOException {

        Text sample = new Text(text);

        String currentWord = "";
        int wordsInSentence = 0;

        for (int i = 0; i < sample.getTextContent().length(); i++) {

            //TODO Was passiert eigetnlich mit Nummern, die können doch eigentlich auch zur komplexität beitragen.
            if (Character.isLetter(sample.getTextContent().charAt(i))) {
                sample.setAmountLetters(sample.getAmountLetters() + 1);
                currentWord += sample.getTextContent().charAt(i);

                if (i + 1 >= sample.getTextContent().length()) {
                    sample.setAmountSentence(sample.getAmountSentence() + 1);
                    sample.setAmountWords(sample.getAmountWords() + wordsInSentence);
                    wordsInSentence = 0;

                    sample.setAmountSentence(sample.getAmountSentence() + 1);
                    sample.setAmountWords(sample.getAmountWords() + wordsInSentence);
                    wordsInSentence = 0;
                }

            } else {

                if (!currentWord.isEmpty()) {
                    sample.setAmountHypen(sample.getAmountHypen() + getWordHyphenCount(currentWord));
                    wordsInSentence++;
                    currentWord = "";
                }

                if (isSentenceEnd(sample.getTextContent().charAt(i))) {
                    sample.setAmountSentence(sample.getAmountSentence() + 1);
                    sample.setAmountWords(sample.getAmountWords() + wordsInSentence);
                    wordsInSentence = 0;
                }
            }
        }

        return sample;
    }
    
    /**
     * This method is analysing a String and creating a new "Text" Object for the given String.
     * The method is trying to measure the amount of letters, words, sentence, hypens and evertything else
     * what the "Text" object requires. 
     * 
     * 
     * 
     * @param text A String which is represanting the text to analyse
     * @return Text.class
     */
    public Text newAnalyzer(String text){
        
        Text sample = new Text(text);
        
        
        
        return sample;
    }

    /**
     * 33 = !
     * 46 = .
     * 63 = ?
     * 58 = :
     * 59 = ;
     * 
     * Checking if the current char is the end of a sentence. Semicolon and doublepoint
     * is for us a end, too.
     * 
     * @param letter
     * @return 
     */
    private boolean isSentenceEnd(char letter) {

        if ((33 == letter) || (46 == letter) || (63 == letter) || (58 == letter) || (59 == letter)) {
            return true;
        } else {
            return false;
        }

    }

    private int getWordHyphenCount(String word) throws IOException {

        Hyphenator hp = new net.davidashen.text.Hyphenator();
        hp.setErrorHandler(new ErrorHandler() {
            public void debug(String guard, String s) {
            }

            public void info(String s) {
                System.err.println(s);
            }

            public void warning(String s) {
                System.err.println("WARNING: " + s);
            }

            public void error(String s) {
                System.err.println("ERROR: " + s);
            }

            public void exception(String s, Exception e) {
                System.err.println("ERROR: " + s);
                e.printStackTrace();
            }

            public boolean isDebugged(String guard) {
                return false;
            }
        });

        hp.loadTable(new java.io.BufferedInputStream(new java.io.FileInputStream("hyphen.tex")));

        String hyphenatedWord = hp.hyphenate(word, 2, 3);
        int hCount = 1;

        for (int i = 0; i < hyphenatedWord.length(); i++) {
            if (hyphenatedWord.charAt(i) == 173) {
                hCount++;
            }
        }
        return hCount;

    }

}
