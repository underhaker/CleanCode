package com.fmi.cleancode.webcrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ThreadCrawler implements Runnable {
    public static int counter = 0;
    private URL url;
    private String needle;

    public ThreadCrawler(URL url, String needle) {
        this.url = url;
        this.needle = needle;
        counter++;
    }

    @Override
    public void run() {
        WebCrawler webCrawler = new WebCrawler(url, needle);
        try {
            webCrawler.crawl();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
