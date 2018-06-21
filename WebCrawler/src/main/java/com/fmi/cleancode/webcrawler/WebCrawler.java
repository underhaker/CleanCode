package com.fmi.cleancode.webcrawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
    private static int THREAD_COUNT = 250;
    private List<String> usedLinks;
    private BlockingQueue<URL> blockingQueue;
    private URL homeUrl;
    private boolean isHomeUrl;
    private long startTime;
    private URL searchUrl;
    private String needle;

    public WebCrawler(URL searchUrl, String needle) {
        this.searchUrl = searchUrl;
        this.needle = needle;
        this.usedLinks = Collections.synchronizedList(new ArrayList<String>());
        this.blockingQueue = new ArrayBlockingQueue<>((100 * THREAD_COUNT));
    }

    public void crawl() throws IOException,
            URISyntaxException {
        startTime = System.currentTimeMillis();
        blockingQueue.offer(searchUrl);
        URL url;
        while (blockingQueue.size() != 0) {
            url = blockingQueue.poll();
            System.out.println(Thread.currentThread().getName() + " " + url + " active threads: "
                    + Thread.activeCount() + " queue size:" + blockingQueue.size());

            if (!isHomeUrl) {
                homeUrl = url;
                isHomeUrl = true;
            }

            String urlContent = getHtmlContent(url.toURI());

            if (!usedLinks.contains(url.toString())) {
                usedLinks.add(url.toString());
            }

            if (urlContent.contains(needle)) {
                synchronized (urlContent) {
                    System.out.println("the selected needle was found in: " + url);
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    System.out.println("duration: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
                    return;
                }
            }
            crawlWithNewLink(url, urlContent);
        }
    }

    public void printDuration() {
        System.out.println("duration: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
    }


    private void crawlWithNewLink(URL url, String urlContent) throws MalformedURLException {
        List<String> linkList = getAllLinks(urlContent);
        URL urlLink;
        for (String link : linkList) {
            urlLink = new URL(url, link);
            if (urlLink.toString().contains(homeUrl.getHost())
                    && !usedLinks.contains(urlLink.toString())
                    && !blockingQueue.contains(urlLink)) {
                blockingQueue.offer(urlLink);
                if (Thread.activeCount() < THREAD_COUNT) {
                    new Thread(new ThreadCrawler(urlLink, needle)).start();
                }
            }
        }
    }

    private String getHtmlContent(URI uri) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, "UTF-8");
    }

    private List<String> getAllLinks(String content) {
        ArrayList<String> resultList = new ArrayList<>();
        String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            resultList.add(matcher.group(1));
        }
        return resultList;
    }

}
