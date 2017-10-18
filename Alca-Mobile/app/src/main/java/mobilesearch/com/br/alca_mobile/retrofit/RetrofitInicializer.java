package mobilesearch.com.br.alca_mobile.retrofit;

import mobilesearch.com.br.alca_mobile.service.LotService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by LimaD01 on 23/02/2017.
 */

public class RetrofitInicializer {

    private final Retrofit retrofit;

    public RetrofitInicializer(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client =  new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("172.20.19.193:8089/sys/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();
    }

    public <T> T createServiceByClass(Class<T> cls){
        return retrofit.create(cls);
    }

    public LotService createLotService(){
        return retrofit.create(LotService.class);
    }

}
