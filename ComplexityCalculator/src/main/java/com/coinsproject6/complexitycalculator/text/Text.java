/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator.text;

/**
 * (Translatation into English) Eine komplexe Datenstruktur, die den auf Kopmlexität zu untersuchenden Text
 * mit allen Information darstellt. 
 * ACHTUNG: Es sind nur Setter und Getter für die Informationen, alle Logik muss
 * durch eine zweite Klasse sichergestellt werden. Es werden somit auch keine
 * Überprüfungen auf Richtikeit des Inhalts getätigt.
 * 
 *
 * @author Georg
 */
public class Text {

    private String text;
    private String rawText;
    private int amountWords;
    private int amountSentence;
    private int amountLetters;
    private int amountHyphen;
    private int amountHyperlinks;
    private int amountReferences;
    private double avgLetters;
    private double avgSentenceLength;
    private double avgAmountSentence;
    private double avgHyphen;

    public Text(String rawText) {
        this.rawText = rawText;
    }

    public void setTextContent(String text) {
        this.text = text;
    }

    public String getTextContent() {
        return text;
    }

    public void setRawTextContent(String rawText) {
        this.rawText = rawText;
    }

    public String getRawTextContent() {
        return rawText;
    }

    public void setAmountWords(int amount) {
        amountWords = amount;
    }

    public int getAmountWords() {
        return amountWords;
    }

    public void setAmountSentence(int amount) {
        amountSentence = amount;
    }

    public int getAmountSentence() {
        return amountSentence;
    }

    public void setAmountLetters(int amount) {
        amountLetters = amount;
    }

    public int getAmountLetters() {
        return amountLetters;
    }

    public void setAmountHyphen(int amount) {
        amountHyphen = amount;
    }

    public int getAmountHyphen() {
        return amountHyphen;
    }

    public void setAmountReferences(int amount){
        amountReferences = amount;
    }
    
    public int getAmountReferences() {
        return amountReferences;
    }

    public void setAmountHyperlinks(int amount){
        amountHyperlinks = amount;
    }
    
    public int getAmountHyperlinks() {
        return amountHyperlinks;
    }

    public void setAvgLetters() {
        avgLetters = (double) amountLetters / ((double) amountWords / 100);
    }

    public Double getAvgLetters() {

        setAvgLetters();
        return avgLetters;
    }

    public void setAvgSentenceLength() {
        avgSentenceLength = (double) amountWords / (double) amountSentence;
    }

    public double getAvgSentenceLength() {

        setAvgSentenceLength();
        return avgSentenceLength;
    }

    public void setAvgAmountSentence() {
        avgAmountSentence = (double) amountSentence / ((double) amountWords / 100);
    }

    public double getAvgAmountSentence() {

        setAvgAmountSentence();
        return avgAmountSentence;
    }

    public void setAvgHyphen() {
        avgHyphen = (double) amountHyphen / (double) amountWords;
    }

    public double getAvgHyphen() {

        setAvgHyphen();
        return avgHyphen;
    }
}
