package webcrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

public class ThreadCrawler implements Runnable {
    private URL url;
    private String needle;
    public static int counter=0;
    ThreadCrawler(URL url, String needle) {
        this.url = url;
        this.needle = needle;
        counter++;
    }


    @Override
    public void run() {
            try {
                WebCrawler.webCrawler(url, needle);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
