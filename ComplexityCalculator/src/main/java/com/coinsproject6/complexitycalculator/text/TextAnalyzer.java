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

    private String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    /**
     * This method is analysing a String and creating a new "Text" Object for
     * the given String. The method is trying to measure the amount of letters,
     * words, sentence, hypens and evertything else what the "Text" object
     * requires.
     *
     *
     *
     * @param rawText A String which is represanting the text to analyse
     * @return Text.class
     */
    public Text analyzeText(String rawText) throws IOException {

        Character currentCharacter;
        String currentWord = "";
        int wordAmountInSentence = 0;
        Text sample = new Text(rawText);
        cleanText(sample);

        for (int i = 0; i < sample.getTextContent().length(); i++) {

            currentCharacter = sample.getTextContent().charAt(i);

            if (Character.isLetter(currentCharacter)) {
                //Berechnung für Buchstaben durchführen
                currentWord += currentCharacter;

                addAmountLetters(sample, 1);

                if (i + 1 >= sample.getTextContent().length()) {
                    wordAmountInSentence++;
                    addAmountWords(sample, wordAmountInSentence);
                    addAmountHyphen(sample, currentWord);
                    addAmountSentence(sample, 1);

                    wordAmountInSentence = 0;
                    currentWord = "";
                }
            } else if (Character.isDigit(currentCharacter)) {
                // Berechnung für Zahlen durchführen
                currentWord += currentCharacter;

                addAmountLetters(sample, 1);
            } else if ((Character.isWhitespace(currentCharacter)) && (!currentWord.isEmpty())) {
                // Berechnung für Leerzeichen durchführen
                wordAmountInSentence++;
                addAmountHyphen(sample, currentWord);

                currentWord = "";
            } else if ((isSentenceEnd(currentCharacter))) {
                // Berechnung für Satzende durchführen
                if (!currentWord.isEmpty()) {
                    wordAmountInSentence++;
                    addAmountWords(sample, wordAmountInSentence);
                    addAmountHyphen(sample, currentWord);
                    addAmountSentence(sample, 1);

                    currentWord = "";
                } else if (currentWord.isEmpty() && (wordAmountInSentence != 0)) {
                    addAmountWords(sample, wordAmountInSentence);
                    addAmountSentence(sample, 1);
                }

                wordAmountInSentence = 0;
            } else {
                //Sonderfall Behandlung
            }
        }

        return sample;
    }

    /**
     * 33 = ! 46 = . 63 = ? 58 = : 59 = ;
     *
     * Checking if the current char is the end of a sentence. Semicolon and
     * doublepoint is for us a end, too.
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

    /**
     * 
     * @param sample
     * @param amount 
     */
    private void addAmountLetters(Text sample, int amount) {
        sample.setAmountLetters(sample.getAmountLetters() + amount);
    }

    /**
     * 
     * @param sample
     * @param amount 
     */
    private void addAmountWords(Text sample, int amount) {
        sample.setAmountWords(sample.getAmountWords() + amount);
    }

    /**
     * 
     * @param sample
     * @param amount 
     */
    private void addAmountSentence(Text sample, int amount) {
        sample.setAmountSentence(sample.getAmountSentence() + amount);
    }

    /**
     * 
     * @param sample
     * @param currentWord
     * @throws IOException 
     */
    private void addAmountHyphen(Text sample, String currentWord) throws IOException {
        sample.setAmountHyphen(sample.getAmountHyphen() + getWordHyphenCount(currentWord));
    }

    /**
     *
     * @param word
     * @return
     * @throws IOException
     */
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

    /**
     *
     * @param rawText
     * @param sample
     */
    private void cleanText(Text sample) {

        String currentVersion = sample.getRawTextContent();

        currentVersion = clearHyperlinks(currentVersion, sample);
        currentVersion = clearReferences(currentVersion, sample);
        currentVersion = removeHashSymbols(currentVersion, sample);
        currentVersion = currentVersion.trim();
        sample.setTextContent(currentVersion);

    }

    /**
     * method for removing hash-signs in twitter (only removes the
     * "#"-character, not the whole string)
     *
     * @param text
     * @param sample
     * @return
     */
    private String removeHashSymbols(String text, Text sample) {

        return text.replaceAll("#", "");
    }

    /**
     * method for removing all twitter-references (strings beginning with
     * "@"-character, removes whole string)
     *
     * @param text
     * @param sample
     * @return
     */
    private String clearReferences(String text, Text sample) {

        String clearedText = "";
        int amountReferences = 0;
        sample.setAmountReferences(amountReferences);
        Boolean ignoreUntilNextSpace = false;

        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == '@') {
                ignoreUntilNextSpace = true;
                amountReferences++;
            } else if (text.charAt(i) == ' ') {
                ignoreUntilNextSpace = false;
            }

            if (!ignoreUntilNextSpace) {
                clearedText += text.charAt(i);
            }
        }

        sample.setAmountReferences(amountReferences);
        return clearedText;

    }

    /**
     * method for removing hyperlinks (removes whole string beginning with http
     * until next space-character)
     *
     * @param text
     * @param sample
     * @return
     */
    private String clearHyperlinks(String text, Text sample) {

        //TODO Es möglich machen, dass hierdurch auch die Anzahl an Regex gezählt wird.
        return text.replaceAll(regex, "");
    }

}
