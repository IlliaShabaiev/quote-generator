package com.ilyadeveloper.quotegenerator;

import com.ilyadeveloper.quotegenerator.config.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UI {

    @Autowired
    Config config;

    public void prepareDriver(){

        String pathToMyProject = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToMyProject);
        WebDriver driver = new ChromeDriver();

        driver.get(config.getBaseurl()+config.getInspiration()+config.getSlash());
        Document doc = Jsoup.parse(driver.getPageSource());

        List<String> urls = findAllPages(driver, doc);

        List<String> allQuotesAsStrings = new ArrayList<>();

        for (String url: urls){
            driver.get(url);
            Document currentDoc = Jsoup.parse(driver.getPageSource());
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

        if (driver!=null){
            driver.close();
        }
        //String quote = driver.findElement(By.xpath("//*[@class='quote']/p[2]/b[1]")).getText();
        //Elements quotesAll = doc.getElementsByClass("quote");
    }

    public String getRandomQuote(List<String> allquotes){
        Random rand = new Random();
        int  n = rand.nextInt(allquotes.size()-1) + 0;
        //50 is the maximum and the 1 is our minimum
        String quote = allquotes.get(n);
        return quote;
    }

    public List<String> findAllPages(WebDriver driver, Document document){
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