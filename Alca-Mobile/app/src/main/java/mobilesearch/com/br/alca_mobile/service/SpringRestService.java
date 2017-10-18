package mobilesearch.com.br.alca_mobile.service;

import android.util.Log;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * Created by LimaD01 on 12/05/2017.
 */

public class SpringRestService<T> {

    public T response(String url, Class<T> tClass ){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.setInterceptors(Collections.singletonList(getInterceptor()));
        T result = restTemplate.getForObject(url, tClass);
        return result;
    }

    private ClientHttpRequestInterceptor getInterceptor (){
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                Log.i("http request",body.toString());
                return execution.execute(request,body);
            }
        };
    }

}
