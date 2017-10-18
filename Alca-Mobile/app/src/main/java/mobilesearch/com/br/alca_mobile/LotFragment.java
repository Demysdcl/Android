package mobilesearch.com.br.alca_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import mobilesearch.com.br.alca_mobile.http.BaseHttpUrl;
import mobilesearch.com.br.alca_mobile.model.SystemWrapper;
import mobilesearch.com.br.alca_mobile.model.activites.LotActivitySaveState;
import mobilesearch.com.br.alca_mobile.model.response.ListProdResponse;
import mobilesearch.com.br.alca_mobile.model.response.LotResponse;
import mobilesearch.com.br.alca_mobile.task.ListRequestTask;
import mobilesearch.com.br.alca_mobile.task.LotRequestTask;
import mobilesearch.com.br.alca_mobile.task.ProductionResquestTask;
import mobilesearch.com.br.alca_mobile.util.BarcodeResult;
import mobilesearch.com.br.alca_mobile.util.SpinnerBuilder;


/**
 * A simple {@link Fragment} subclass.
 */
public class LotFragment extends Fragment implements View.OnClickListener, Spinner.OnItemSelectedListener {

    Spinner opSp;
    Spinner skuSp;
    Spinner lineSp;
    TextView tvMsg;
    TextView tvLot;
    TextView tvQtyOp;
    TextView tvProdOp;
    TextView tvQtyLot;
    TextView tvLastGrp;
    TextView tvProdLot;
    EditText etBarcode;
    ImageButton scanButton;
    LinearLayout lotLinearLayout;

    LotActivitySaveState saveState = new LotActivitySaveState();

    public LotFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_lot, container, false);
        etBarcode = (EditText) inflatedView.findViewById(R.id.ed_code);
        scanButton = (ImageButton) inflatedView.findViewById(R.id.scan_button);
        scanButton.setOnClickListener(this);
        skuSp = (Spinner) inflatedView.findViewById(R.id.sp_sku);
        lineSp = (Spinner) inflatedView.findViewById(R.id.sp_line);
        opSp = (Spinner) inflatedView.findViewById(R.id.sp_op);
        tvMsg = (TextView) inflatedView.findViewById(R.id.msg_view);
        tvLot = (TextView) inflatedView.findViewById(R.id.tv_lot);
        tvQtyOp = (TextView) inflatedView.findViewById(R.id.tv_qty_op);
        tvProdOp = (TextView) inflatedView.findViewById(R.id.tv_prod_op);
        tvQtyLot = (TextView) inflatedView.findViewById(R.id.tv_qty_lot);
        tvProdLot = (TextView) inflatedView.findViewById(R.id.tv_prod_lot);
        tvLastGrp = (TextView) inflatedView.findViewById(R.id.tv_last_grp);
        lotLinearLayout = (LinearLayout) inflatedView.findViewById(R.id.lot_linear_layout);
        skuSp.setOnItemSelectedListener(this);
        lineSp.setOnItemSelectedListener(this);
        opSp.setOnItemSelectedListener(this);

        if (savedInstanceState != null) {
            saveState = (LotActivitySaveState) savedInstanceState.getSerializable(LotActivitySaveState.KEY);
            fillSpinner(saveState.skus, null, R.id.sp_sku);
            fillSpinner(saveState.lines, null, R.id.sp_line);
        } else {
            fillSpinnerLineAndSku();
        }
        return inflatedView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LotActivitySaveState.KEY, saveState);
    }

    public void fillSpinnerLineAndSku() {
        new ListRequestTask(BaseHttpUrl.getSkus()) {
            @Override
            protected void onPostExecute(SystemWrapper<List<String>> listSystemWrapper) {
                if (listSystemWrapper != null) {
                    saveState.skus = listSystemWrapper.getValue();
                    fillSpinner(saveState.skus, this.msg, R.id.sp_sku);
                }
            }
        }.execute();

        new ListRequestTask(BaseHttpUrl.getLines()) {
            @Override
            protected void onPostExecute(SystemWrapper<List<String>> listSystemWrapper) {
                if (listSystemWrapper != null) {
                    fillSpinner(listSystemWrapper.getValue(), this.msg, R.id.sp_line);
                    saveState.lines = listSystemWrapper.getValue();
                }
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        String box = etBarcode.getText().toString();
        if (box.isEmpty()) {
            BarcodeResult.callScanner(this);
        } else {
            tvLastGrp.setText(box);
            processBox(box);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            etBarcode.setText(BarcodeResult.requestResult(resultCode, data));
            saveState.scannedCode = etBarcode.getText().toString();
            tvLastGrp.setText(etBarcode.getText().toString());
            etBarcode.setText(null);
            processBox(saveState.scannedCode);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void processBox(String box) {
        final LotFragment lotFragment = this;
        lotLinearLayout.setBackgroundColor(ContextCompat.getColor(lotFragment.getContext(), R.color.colorMain));
        new ProductionResquestTask(saveState.selectedLot, box) {
            @Override
            protected void onPostExecute(ListProdResponse resp) {

                if (resp.getResponse() != null) {

                    if (resp.isFinishedPO()) {
                        updateOp();
                        updateLot();
                        updateBackgroud(R.color.wallet_holo_blue_light);
                    } else if (resp.isFinishedLot()) {
                        updateLot();
                        updateBackgroud(R.color.wallet_holo_blue_light);
                    } else if (!resp.isProccessed() && resp.isValidBox()) {
                        LotResponse wp = resp.getResponse();
                        tvQtyOp.setText(wp.getQtyOp().toString());
                        tvProdOp.setText(wp.getProdOp().toString());
                        tvQtyLot.setText(wp.getQtyLot().toString());
                        tvProdLot.setText(wp.getProdLot().toString());
                    } else {
                        updateBackgroud(R.color.colorRed);
                    }
                    tvMsg.setText(resp.getMsg());
                } else {
                    tvMsg.setText(this.msg);
                }
            }
        }.execute();
    }

    private void updateBackgroud(int color) {
        lotLinearLayout.setBackgroundColor(ContextCompat.getColor(this.getContext(),
                color));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_sku:
                saveState.selectedSku = skuSp.getItemAtPosition(position).toString();
                updateOp();
                updateLot();
                break;
            case R.id.sp_line:
                saveState.selectedLine = lineSp.getItemAtPosition(position).toString();
                updateOp();
                updateLot();
                break;
            case R.id.sp_op:
                Object obj = opSp.getItemAtPosition(position);
                saveState.selectedOp = obj.toString();
                updateLot();
                break;
        }
    }

    private void updateOp() {
        if (saveState.selectedLine != null && !saveState.selectedLine.isEmpty()) {
            new ListRequestTask(BaseHttpUrl.getOps(saveState.selectedLine, saveState.selectedSku)) {
                @Override
                protected void onPostExecute(SystemWrapper<List<String>> listSystemWrapper) {
                    if (listSystemWrapper != null && listSystemWrapper.getValue() != null)
                        fillSpinner(listSystemWrapper.getValue(), this.msg, R.id.sp_op);
                    updateBackgroud(R.color.colorMain);
                    tvMsg.setText(null);
                    if (opSp.getAdapter().isEmpty()) {
                        clearLabels("Não há Ordem de Produção");
                        updateBackgroud(R.color.colorRed);
                        saveState.selectedOp = null;
                    }
                }
            }.execute();
        }
    }

    private void updateLot() {
        if (saveState.selectedOp != null && !saveState.selectedOp.isEmpty()) {
            new LotRequestTask(saveState.selectedOp) {
                @Override
                protected void onPostExecute(LotResponse wp) {
                    if (wp != null) {
                        tvLot.setText(wp.getLote());
                        saveState.selectedLot = wp.getLote();
                        tvQtyOp.setText(wp.getQtyOp().toString());
                        tvProdOp.setText(wp.getProdOp().toString());
                        tvQtyLot.setText(wp.getQtyLot().toString());
                        tvProdLot.setText(wp.getProdLot().toString());
                    } else {
                        clearLabels("Sem Lote");
                    }
                }
            }.execute();
        }
    }

    private void clearLabels(String msg) {
        if (!tvMsg.getText().toString().contains("Finalizad")) {
            tvMsg.setText(msg);
        }
        tvLot.setText("None");
        tvQtyOp.setText("0");
        tvProdOp.setText("0");
        tvQtyLot.setText("0");
        tvProdLot.setText("0");
    }

    private void fillSpinner(List<String> list, String msg, int id) {
        if (list != null)
            SpinnerBuilder.buildAdapter(getActivity(), list, id);
        else if (msg != null)
            tvMsg.setText(msg);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
