

package com.coinsproject6.complexitycalculator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.zip.*;
/**
 *
 * @author tobeflow
 * 
 * sends google search request with search term = <input text to analyse>  
 * and reads out the first google autocorrect/autocomplete suggestion for this term
 * 
 **/


public class DidYouMean {
    
    public static String didYouMean(String s){

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

  /*  public static void main(String args[]){
        System.out.println(didYouMean("believeinnewark"));
    }
*/

    public static String executeGet(String targetURL,String host,char ch){
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

