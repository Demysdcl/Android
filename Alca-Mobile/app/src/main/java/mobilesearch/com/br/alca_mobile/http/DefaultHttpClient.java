package mobilesearch.com.br.alca_mobile.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by LimaD01 on 01/02/2017.
 */

public class DefaultHttpClient {

    private static String BASE_URL = "http://172.20.19.165:8080/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler handler){
        client.get(getAbsoluteUrl(url),params, handler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler handler){
        client.post(getAbsoluteUrl(url),params, handler);
    }
    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler handler){
        client.delete(getAbsoluteUrl(url),params, handler);
    }
    public static void put(String url, RequestParams params, AsyncHttpResponseHandler handler){
        client.put(getAbsoluteUrl(url),params, handler);
    }

    private static String getAbsoluteUrl(String url) {
        return BASE_URL + url;
    }

}
