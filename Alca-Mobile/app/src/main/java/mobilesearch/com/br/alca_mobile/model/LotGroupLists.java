package mobilesearch.com.br.alca_mobile.model;

import java.util.List;

/**
 * Created by LimaD01 on 12/05/2017.
 */

public class LotGroupLists {

    private List<String> skus;
    private List<String> lines;
    private List<String> ops;

    public LotGroupLists(List<String> skus, List<String> lines, List<String> ops) {
        this.skus = skus;
        this.lines = lines;
        this.ops = ops;
    }

    public List<String> getSkus() {
        return skus;
    }

    public void setSkus(List<String> skus) {
        this.skus = skus;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getOps() {
        return ops;
    }

    public void setOps(List<String> ops) {
        this.ops = ops;
    }
}
