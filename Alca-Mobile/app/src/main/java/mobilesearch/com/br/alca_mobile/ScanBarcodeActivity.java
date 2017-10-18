package mobilesearch.com.br.alca_mobile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;


public class ScanBarcodeActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    SurfaceView cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
        cameraPreview = (SurfaceView) findViewById(R.id.camera_preview);
        createCameraSource();
    }

    private void createCameraSource() {
        BarcodeDetector detector = new BarcodeDetector.Builder(this).build();

        final CameraSource source = new CameraSource.Builder(this, detector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                checkAndSolicitePermission();

                if (ContextCompat.checkSelfPermission(ScanBarcodeActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                try {
                    source.start(cameraPreview.getHolder());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                source.stop();
            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if(barcodes.size() > 0){
                    Intent intent = new Intent();
                    intent.putExtra("barcode", barcodes.valueAt(0));
                    setResult(CommonStatusCodes.SUCCESS,intent);
                    finish();
                }
            }
        });
    }

    private void checkAndSolicitePermission(){
        if (ContextCompat.checkSelfPermission(ScanBarcodeActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ScanBarcodeActivity.this,
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(ScanBarcodeActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }
}
