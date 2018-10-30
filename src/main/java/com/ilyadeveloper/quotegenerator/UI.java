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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UI {

    @Autowired
    Config config;

    WebDriver driver;

    public void prepareDriver(){
        String pathToMyProject = System.getProperty("user.dir");
        String os = System.getProperty("os.name");
        if (os.contains("Windows")){
            String driverPath = pathToMyProject + "/src/main/resources/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
        }else if (os.contains("Mac OS")){
            System.setProperty("webdriver.chrome.driver", pathToMyProject + "/src/main/resources/chromedriver");
            driver = new ChromeDriver();
        }else {
            throw new IllegalStateException("Couldn't detect platform.");
        }
    }
}