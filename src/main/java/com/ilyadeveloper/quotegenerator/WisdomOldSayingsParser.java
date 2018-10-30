package com.ilyadeveloper.quotegenerator;

import com.ilyadeveloper.quotegenerator.config.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class WisdomOldSayingsParser {

    @Autowired
    Config config;

    public void scanPages(){

        String content = null;
        try {
            content = Jsoup.connect(config.getBaseurl()+config.getInspiration()+config.getSlash()).userAgent("Mozilla").get().html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(content);

        List<String> urls = findAllPages(doc);

        List<String> allQuotesAsStrings = new ArrayList<>();

        for (String url: urls){
            //driver.get(url);
            String thisPage = null;
            try {
                thisPage = Jsoup.connect(url).userAgent("Mozilla").get().html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Document currentDoc = Jsoup.parse(thisPage);
            Elements exp = currentDoc.select(".quote > p:eq(1) > b");
            for (int i = 0; i<exp.size(); i++){

                String currentQuote = exp.get(i).text();
                allQuotesAsStrings.add(currentQuote);
            }
        }
        for (String quote : allQuotesAsStrings){
            System.out.println(quote);
        }

        System.out.println();
        System.out.println();
        System.out.println("Random quote for today is: ");
        System.out.println();
        System.out.println(getRandomQuote(allQuotesAsStrings));
        System.out.println();
        System.out.println();

    }

    public String getRandomQuote(List<String> allquotes){
        Random rand = new Random();
        int  n = rand.nextInt(allquotes.size()-1) + 0;
        //50 is the maximum and the 1 is our minimum
        String quote = allquotes.get(n);
        return quote;
    }

    public List<String> findAllPages(Document document){
        Elements allLinks = document.getElementsByClass("pagies");
        List<String> allLinksWithoutNextAndPrev = new ArrayList<>();
        String link = config.getBaseurl() + config.getInspiration() + config.getSlash() + "page-";
        int size = allLinks.size()-2;
        for (int i = 1; i<=size; i++){
            String linkCurrent = link + i + "/";
            allLinksWithoutNextAndPrev.add(linkCurrent);
        }
        return allLinksWithoutNextAndPrev;
    }
}
