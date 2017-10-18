package mobilesearch.com.br.alca_mobile.task;

import com.google.android.gms.drive.query.Filters;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by LimaD01 on 18/05/2017.
 */

public class FormatedRestTemplate {

    public static RestTemplate getTemplate(){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate
                .getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

}
