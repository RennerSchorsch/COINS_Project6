/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator.text;

/**
 * DRAFT - Erster Entwurf, es sind noch viele Anpassungen nötig!! Komplexe
 * Datenstruktur, die den Text mit allen nötigen Informationen repräsentiert.
 *
 * @author Georg
 */
public class Text {

    private String text;
    private int amountWords;
    private int amountSentence;
    private int amountLetters;
    private int amountHypen;
    private double avgLetters;
    private double avgSentenceLength;
    private double avgAmountSentence;
    private double avgHypen;

    public Text(String text) {
        this.text = text;
    }

    public void setTextContent(String text) {
        this.text = text;
    }

    public String getTextContent() {
        return text;
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

    public void setAmountHypen(int amount) {
        amountHypen = amount;
    }

    public int getAmountHypen() {
        return amountHypen;
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

    public void setAvgHypen() {
        avgHypen = (double) amountHypen / (double) amountWords;
    }

    public double getAvgHypen() {
        
        setAvgHypen();
        return avgHypen;
    }
}
