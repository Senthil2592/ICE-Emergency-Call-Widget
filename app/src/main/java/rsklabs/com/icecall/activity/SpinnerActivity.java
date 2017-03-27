package rsklabs.com.icecall.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import rsklabs.com.icecall.R;
import rsklabs.com.icecall.bean.IceAppBean;

/**
 * Created by Kumar on 2/11/2016.
 */
public class SpinnerActivity extends MainActivity implements AdapterView.OnItemSelectedListener {

    public SpinnerActivity() {

    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        String selected = parent.getItemAtPosition(pos).toString();
        if ("White".equals(selected)) {
            parent.setBackgroundColor(0x7fFFFFFF);
        } else if ("Violet".equals(selected)) {
            parent.setBackgroundColor(0x7f990073);
        } else if ("Indigo".equals(selected)) {
            parent.setBackgroundColor(0x7f4b0082);
        } else if ("Green".equals(selected)) {
            parent.setBackgroundColor(0x7f00FF00);
        } else if ("Red".equals(selected)) {
            parent.setBackgroundColor(0x7fFF0000);
        } else if ("Blue".equals(selected)) {
            parent.setBackgroundColor(0x7f0000FF);
        } else if ("Orange".equals(selected)) {
            parent.setBackgroundColor(0x7fFFA500);
        } else if ("Yellow".equals(selected)) {
            parent.setBackgroundColor(0x7fFFFF00);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void getSpinnerValue(IceAppBean iceAppBean, String selected){


        if ("White".equals(selected)) {
            iceAppBean.setWidgetColor(0xffFFFFFF);
        } else if ("Violet".equals(selected)) {
            iceAppBean.setWidgetColor(0xff990073);
        } else if ("Indigo".equals(selected)) {
            iceAppBean.setWidgetColor(0xff4b0082);
        } else if ("Green".equals(selected)) {
            iceAppBean.setWidgetColor(0xff00FF00);
        } else if ("Red".equals(selected)) {
            iceAppBean.setWidgetColor(0xffFF0000);
        } else if ("Blue".equals(selected)) {
            iceAppBean.setWidgetColor(0xff0000FF);
        } else if ("Orange".equals(selected)) {
            iceAppBean.setWidgetColor(0xffFFA500);
        } else if ("Yellow".equals(selected)) {
            iceAppBean.setWidgetColor(0xffFFFF00);
        }

    }
}
