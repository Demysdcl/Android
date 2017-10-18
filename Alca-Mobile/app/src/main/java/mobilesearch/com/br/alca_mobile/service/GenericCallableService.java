package mobilesearch.com.br.alca_mobile.service;

import java.util.List;

import mobilesearch.com.br.alca_mobile.model.SystemWrapper;
import retrofit2.Call;

/**
 * Created by LimaD01 on 23/02/2017.
 */
public interface GenericCallableService<T> {

    Call<SystemWrapper<T>> insert();

    Call<SystemWrapper<T>> update();

    Call<SystemWrapper<T>> delete();

    Call<SystemWrapper<List<T>>> listAll();

    Call<SystemWrapper<T>> findById();

}
