package mobilesearch.com.br.alca_mobile.task;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import mobilesearch.com.br.alca_mobile.R;
import mobilesearch.com.br.alca_mobile.model.SystemWrapper;

/**
 * Created by LimaD01 on 16/05/2017.
 */

public abstract class ListRequestTask extends AsyncTask<Object, Void, SystemWrapper<List<String>>> {

    private final String url;

    public String msg;

    private List<String> list;

    public ListRequestTask(String url) {
        this.url = url;
    }

    @Override
    protected SystemWrapper<List<String>> doInBackground(Object... params) {
        try {
            RestTemplate restTemplate = FormatedRestTemplate.getTemplate();
            SystemWrapper<List<String>> wrapper = restTemplate.getForObject(url, SystemWrapper.class);
            if(wrapper.getValue() != null){
                msg = "Não foi encontrado registro para estes parâmetros";
            }
            return wrapper;
        } catch (Exception e) {
            msg = "Verifique sua conexão com a Internet " + e;
            return new SystemWrapper<>();
        }
    }

}
