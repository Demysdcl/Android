package mobilesearch.com.br.alca_mobile.http;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

/**
 * Created by LimaD01 on 12/05/2017.
 */

public class BaseHttpUrl implements Serializable {

    private static final String baseUrl = "http://172.20.19.193:8089/sys/";
//    private static final String baseUrl = "http://TCT-NB-19BGV32:8089/sys/";

    @Contract(pure = true)
    public static final String getSkus() {
        return baseUrl + "sku/name";
    }

    @Contract(pure = true)
    public static final String getLines() {
        return baseUrl + "line/name";
    }

    @Contract(pure = true)
    public static final String getOps(String line, String sku) {
        return baseUrl + "ordem/findByLineAndSku/" + line + "/" + sku;
    }

    public static final String getProdUrl(String lot, String grp){
        return baseUrl + "prod/" + lot +"/" + grp;
    }

    @Contract(pure = true)
    public static final String getLotByOp(String op) {
        return baseUrl + "lot/lotByOp/" + op;
    }

}
