package mobilesearch.com.br.alca_mobile.model.response;

import java.io.Serializable;

/**
 * Created by LimaD01 on 19/05/2017.
 */

public class ListProdResponse implements Serializable {

    private boolean finishedPO;

    private boolean finishedLot;

    private boolean validBox;

    private boolean proccessed;

    private LotResponse response;

    private String msg;

    public boolean isFinishedPO() {
        return finishedPO;
    }

    public void setFinishedPO(boolean finishedPO) {
        this.finishedPO = finishedPO;
    }

    public boolean isFinishedLot() {
        return finishedLot;
    }

    public void setFinishedLot(boolean finishedLot) {
        this.finishedLot = finishedLot;
    }

    public boolean isValidBox() {
        return validBox;
    }

    public void setValidBox(boolean validBox) {
        this.validBox = validBox;
    }

    public boolean isProccessed() {
        return proccessed;
    }

    public void setProccessed(boolean proccessed) {
        this.proccessed = proccessed;
    }

    public LotResponse getResponse() {
        return response;
    }

    public void setResponse(LotResponse response) {
        this.response = response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
