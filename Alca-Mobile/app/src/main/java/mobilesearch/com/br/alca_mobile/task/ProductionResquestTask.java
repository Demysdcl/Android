package mobilesearch.com.br.alca_mobile.task;

import android.os.AsyncTask;

import org.springframework.web.client.RestTemplate;

import mobilesearch.com.br.alca_mobile.http.BaseHttpUrl;
import mobilesearch.com.br.alca_mobile.model.response.ListProdResponse;


public abstract class ProductionResquestTask extends AsyncTask<Object, Void, ListProdResponse> {

    private String lot;
    private String box;
    public String msg;

    public ProductionResquestTask(String lot, String box) {
        this.lot = lot;
        this.box = box;
    }

    @Override
    protected ListProdResponse doInBackground(Object... params) {
        try {
            RestTemplate restTemplate = FormatedRestTemplate.getTemplate();
            ListProdResponse resp = restTemplate.postForObject(BaseHttpUrl.getProdUrl(lot, box), null, ListProdResponse.class);
            return resp;
        } catch (Exception e){
            msg = "Verifique sua conexão com a Internet " + e;
            return new ListProdResponse();
        }
    }
}
