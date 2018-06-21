package com.fmi.cleancode;

import com.fmi.cleancode.webcrawler.WebCrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.youtube.com");
        String needle = "kanye west";
        WebCrawler webCrawler = new WebCrawler(url, needle);
        try {
            webCrawler.crawl();
            webCrawler.printDuration();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
