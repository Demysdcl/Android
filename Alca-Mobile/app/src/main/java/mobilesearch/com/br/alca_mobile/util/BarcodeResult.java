package mobilesearch.com.br.alca_mobile.util;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v4.app.Fragment;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.Serializable;

import mobilesearch.com.br.alca_mobile.ScanBarcodeActivity;

/**
 * Created by LimaD01 on 31/01/2017.
 */

public class BarcodeResult implements Serializable{

    public static String requestResult(int resultCode, Intent data){
        if(resultCode == CommonStatusCodes.SUCCESS){
            if (data != null){
                Barcode barcode = data.getParcelableExtra("barcode");
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 150);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,100);
                return barcode.displayValue;
            } else {
                return "No Barcode Found";
            }
        }
        return "";
    }

    public static void callScanner(Fragment fragment){
        Intent intent = new Intent(fragment.getActivity(), ScanBarcodeActivity.class);
        fragment.startActivityForResult(intent, 0);
    }

}
