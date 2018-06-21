package webcrawler;

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

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WebCrawler {
    private final static int THREAD_COUNT = 250;
    private static List<String> used = Collections.synchronizedList(new ArrayList<String>());
    private static BlockingQueue<URL> bq = new ArrayBlockingQueue<URL>((100 * THREAD_COUNT));
    private static URL urlHome;
    private static boolean isUrlHome;
    private static long startTime;

    private static String getContent(URI uri) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        return responseString;
    }

    private static List<String> getAllLinks(String content) {
        ArrayList<String> resultList = new ArrayList<>();
        String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            resultList.add(matcher.group(1));
        }
        return resultList;
    }

    public static void webCrawler(URL url, String needle) throws ClientProtocolException, IOException,
            URISyntaxException {
        bq.offer(url);
        List<String> links = null;

        while (bq.size() != 0) {
            url = bq.poll();
            System.out.println(Thread.currentThread().getName() + " " + url + " active threads: "
                    + Thread.activeCount() + " queue size:" + bq.size());

            if (isUrlHome == false) {
                urlHome = url;
                isUrlHome = true;
            }

            String urlContent = "";
            urlContent = new StringBuilder(getContent(url.toURI())).toString();

            if (used.contains(url.toString()) == false)
                used.add(url.toString());

            if (urlContent.contains(needle) == true) {
                synchronized (urlContent) {
                    System.out.println("the selected needle was found in: " + url);
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    System.out.println("duration: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
                    System.exit(0);
                }
            }

            links = getAllLinks(urlContent);
            URL urlLink = null;
            for (int i = 0; i < links.size(); i++) {
                urlLink = new URL(url, links.get(i));
                if (used.contains(urlLink.toString()) == false
                        && urlLink.toString().contains(urlHome.getHost()) == true 
                        && bq.contains(urlLink) == false) {

                    bq.offer(urlLink);
                    if (Thread.activeCount() < THREAD_COUNT)
                        new Thread(new ThreadCrawler(urlLink, needle)).start();
                }
            }
        }

    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.youtube.com");
        String needle = "kanye west";
        startTime = System.currentTimeMillis();
        try {
            webCrawler(url, needle);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("duration: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
    }
}
