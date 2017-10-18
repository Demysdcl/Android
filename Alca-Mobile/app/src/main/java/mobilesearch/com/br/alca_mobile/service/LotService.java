package mobilesearch.com.br.alca_mobile.service;

import mobilesearch.com.br.alca_mobile.model.SystemWrapper;
import mobilesearch.com.br.alca_mobile.model.response.LotResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LimaD01 on 18/05/2017.
 */

public interface LotService {

    @GET("/lot/lotByOp/{op}")
    Call<SystemWrapper<LotResponse>> findByOp(@Path("op") String op);

}
