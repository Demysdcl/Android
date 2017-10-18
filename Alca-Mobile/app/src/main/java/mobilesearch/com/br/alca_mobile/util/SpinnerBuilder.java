package mobilesearch.com.br.alca_mobile.util;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import mobilesearch.com.br.alca_mobile.R;

/**
 * Created by LimaD01 on 16/05/2017.
 */

public class SpinnerBuilder {

    public static void buildAdapter(Activity activity, List<String> list, int id){
        Spinner spinner = (Spinner) activity.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                activity,
                R.layout.support_simple_spinner_dropdown_item,
                list
        );
        spinner.setAdapter(adapter);
    }


}
