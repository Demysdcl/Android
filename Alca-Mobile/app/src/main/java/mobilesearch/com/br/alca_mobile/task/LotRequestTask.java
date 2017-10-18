package mobilesearch.com.br.alca_mobile.task;

import android.os.AsyncTask;

import org.springframework.web.client.RestTemplate;

import mobilesearch.com.br.alca_mobile.http.BaseHttpUrl;
import mobilesearch.com.br.alca_mobile.model.SystemWrapper;
import mobilesearch.com.br.alca_mobile.model.response.LotResponse;

/**
 * Created by LimaD01 on 16/05/2017.
 */

public abstract class LotRequestTask extends AsyncTask<Object, Void, LotResponse> {

    private String op;

    public String msg;

    public LotRequestTask(String op) {
        this.op = op;
    }

    @Override
    protected LotResponse doInBackground(Object... params) {
        try {
            RestTemplate restTemplate = FormatedRestTemplate.getTemplate();
            LotResponse wrapper = restTemplate.
                    getForObject(BaseHttpUrl.getLotByOp(op), LotResponse.class);
            return wrapper;
        } catch (Exception e) {
            msg = "Verifique sua conexão com a Internet " + e;
            return new LotResponse();
        }
    }

}
