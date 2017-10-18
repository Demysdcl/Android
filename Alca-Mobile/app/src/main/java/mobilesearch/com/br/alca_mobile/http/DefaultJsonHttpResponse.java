package mobilesearch.com.br.alca_mobile.http;

import com.loopj.android.http.BaseJsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by LimaD01 on 01/02/2017.
 */

public class DefaultJsonHttpResponse<T> extends BaseJsonHttpResponseHandler<T> {


    @Override
    public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, T response) {

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, T errorResponse) {

    }

    @Override
    protected T parseResponse(String rawJsonData, boolean isFailure) throws Throwable {

        return null;
    }
}
