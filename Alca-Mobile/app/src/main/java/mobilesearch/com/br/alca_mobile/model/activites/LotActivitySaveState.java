package mobilesearch.com.br.alca_mobile.model.activites;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LimaD01 on 16/05/2017.
 */

public class LotActivitySaveState implements Serializable {

    public static String KEY = "lotFromBundle";

    public List<String> lines;

    public List<String> skus;

    public String selectedSku;

    public String selectedLine;

    public String selectedOp;

    public String scannedCode;

    public String selectedLot;

    public LotActivitySaveState() {
    }

    public LotActivitySaveState(List<String> lines, List<String> skus, String selectedSku,
                                String selectedLine, String selectedOp) {
        this.lines = lines;
        this.skus = skus;
        this.selectedSku = selectedSku;
        this.selectedLine = selectedLine;
        this.selectedOp = selectedOp;
    }
}
