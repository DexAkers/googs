package com.journaldev.jsoup;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class googs {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static void main(String[] args) throws IOException {
        //Get the search term and number of search results desired from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search:");
        String searchTerm = scanner.nextLine();
        System.out.println("How many results do you want?:");
        int num = scanner.nextInt();
        scanner.close();

        //Search on google using the url code
        //without proper User-Agent, we will get 403 error
        String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+num;
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        //BELOW IS THE RESULTS IN HTML.  NEED TO CONVERT TO TEXT.
        System.out.println(doc.html());

        Elements results = doc.select("h3.r > a");

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
            
        }
    }

}
