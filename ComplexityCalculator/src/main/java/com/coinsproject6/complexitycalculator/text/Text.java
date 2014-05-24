/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coinsproject6.complexitycalculator.text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

/**
 * DRAFT - Erster Entwurf, es sind noch viele Anpassungen nötig!! Komplexe
 * Datenstruktur, die den Text mit allen nötigen Informationen repräsentiert.
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
    private double avgLetters;
    private double avgSentenceLength;
    private double avgAmountSentence;
    private double avgHyphen;
    
    private int amountHyperlinks;
    private int amountReferences;
   
      
    public Text(String text) {
         this.rawText = text;
         clearText();
    }

    public void setTextContent(String text) {
        this.rawText = text;
        clearText();
    }
    


    public String getRawTextContent() {
        return rawText;
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

    public void setAmountHyphen(int amount) {
        amountHyphen = amount;
    }

    public int getAmountHyphen() {
        return amountHyphen;
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
    
 
    private void clearText() {
    
        this.text = rawText;
        clearHyperlinks();
        clearReferences();
        replaceHashesWithGoogleSuggest();
     
    }
    
    // method for removing hash-signs in twitter (only removes the "#"-character, not the whole string)
    private void removeHashSymbols () {
        
        text = text.replaceAll("#", "");    
    }
    
    public void replaceHashesWithGoogleSuggest() {
        
       int hashtagStart;
       int hashtagEnd;
       String hashtag;
       String googleSuggest;
       
       while ((hashtagStart=text.indexOf("#")) > -1)
       {
           
           hashtagEnd = hashtagStart + 1;
           
           while (Character.isLetterOrDigit(text.charAt(hashtagEnd))) {
               hashtagEnd++;
           }
           
           hashtag = text.substring(hashtagStart+1, hashtagEnd);

          
           googleSuggest = this.didYouMean(hashtag);
           System.out.println("Google Suggest for #" + hashtag + " -> " + googleSuggest);
           
           text = text.replaceAll("#" + hashtag, googleSuggest);
           
       }
       
    }
    
    
    // method for removing all twitter-references (strings beginning with "@"-character, removes whole string)
    private void clearReferences(){
        
        String clearedText = "";
        amountReferences = 0;
        Boolean ignoreUntilNextSpace = false;
        
        for (int i = 0; i < text.length(); i++) {
               
            if (text.charAt(i) == "@".charAt(0)) {
                ignoreUntilNextSpace = true;
                amountReferences++;    
            }
            
            else if (text.charAt(i) == " ".charAt(0)) {
                ignoreUntilNextSpace = false;
            }
            
            if (!ignoreUntilNextSpace) {
                clearedText += text.charAt(i);
            }
        }
        
        this.text = clearedText;
        
    }
    
    
    
    // method for removing hyperlinks (removes whole string beginning with http until next space-character)
    private void clearHyperlinks() {
        
        String clearedText = "";
        amountHyperlinks = 0;
        Boolean ignoreUntilNextSpace = false;
        
        for (int i = 0; i < text.length()-4; i++) {
               
            if (text.charAt(i) == "h".charAt(0)) {
                if (text.charAt(i+1) == "t".charAt(0)) {
                    if (text.charAt(i+2) == "t".charAt(0)) {
                        if (text.charAt(i+3) == "p".charAt(0)) {
                            ignoreUntilNextSpace = true;
                            amountHyperlinks++;
                        }
                    }
                }
            }
            else if (text.charAt(i) == " ".charAt(0)) {
                ignoreUntilNextSpace = false;
            }
            
             if (!ignoreUntilNextSpace) {
                clearedText += text.charAt(i);
            }
        }
        
        this.text = clearedText;
    }
    
    
  
    private String didYouMean(String s){

        String word = s;
        String url = "http://www.google.co.in/search?hl=en&q="+URLEncoder.encode(s);
        String html = executeGet(url,"www.google.co.in",'i');

        int pq = html.indexOf("\"pq\":");
        if(pq >= 0){
            html = html.substring(pq + 6);
            int endPQ = html.indexOf("\"");
            if(endPQ > 0) {
                html = html.substring(0,endPQ);
            }
            html = html.trim(); 
        }
        else { 
            return "No results";
        }
        return html;
    }


    public String executeGet(String targetURL,String host,char ch){
        URL url;
        HttpURLConnection connection=null;
        try{
        url=new URL(targetURL);
        connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Host",host);
        connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
        connection.setRequestProperty("Accept-Language","en-US,en;q=0.8");
        if(ch=='c') connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.52 Safari/536.5");
        if(ch=='i') connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; Media Center PC 4.0; InfoPath.2; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; ShopperReports 3.1.22.0; SRS_IT_E879047EB0765B5336AF90)");
        connection.setUseCaches (false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        GZIPInputStream gzis=new GZIPInputStream(connection.getInputStream());
        InputStreamReader reader=new InputStreamReader(gzis);
        BufferedReader in=new BufferedReader(reader);
        String line;
        StringBuffer response=new StringBuffer();
        while((line=in.readLine())!=null) {
        response.append(line);
        response.append('\r');
        }
        in.close();
        return response.toString();
        } catch (Exception e) {e.printStackTrace();return null;}
    }
}
