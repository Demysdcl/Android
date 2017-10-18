package mobilesearch.com.br.alca_mobile.model.response;

import java.io.Serializable;

/**
 * Created by LimaD01 on 19/05/2017.
 */

public class LotResponse implements Serializable {

    private String lote;

    private Integer qtyOp;

    private Integer prodOp;

    private Integer qtyLot;

    private Integer prodLot;

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getQtyOp() {
        return qtyOp;
    }

    public void setQtyOp(Integer qtyOp) {
        this.qtyOp = qtyOp;
    }

    public Integer getProdOp() {
        return prodOp;
    }

    public void setProdOp(Integer prodOp) {
        this.prodOp = prodOp;
    }

    public Integer getQtyLot() {
        return qtyLot;
    }

    public void setQtyLot(Integer qtyLot) {
        this.qtyLot = qtyLot;
    }

    public Integer getProdLot() {
        return prodLot;
    }

    public void setProdLot(Integer prodLot) {
        this.prodLot = prodLot;
    }

}
