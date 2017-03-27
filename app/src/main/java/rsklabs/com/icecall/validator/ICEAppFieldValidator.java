package rsklabs.com.icecall.validator;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import rsklabs.com.icecall.bean.IceAppBean;

/**
 * Created by Kumar on 2/13/2016.
 */
public class ICEAppFieldValidator {

    private static final String PHONE_REGEX = "\\d{10}";

    public boolean validateFields(IceAppBean iceAppBean, Context context){

        boolean hasError= false;
        if("".equals(iceAppBean.getOwnerName())){
            Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show();
            hasError=true;
        }else if("".equals(iceAppBean.getEmrgncyCtPrsn())){
            Toast.makeText(context, "Please enter Emergency Contact name", Toast.LENGTH_SHORT).show();
            hasError=true;
        }else if ("".equals(iceAppBean.getEmrgncyCtNo())) {
            Toast.makeText(context, "Please enter contact no", Toast.LENGTH_SHORT).show();
            hasError=true;
        }else if("".equals(iceAppBean.getRelation())){
            Toast.makeText(context, "Please enter relation", Toast.LENGTH_SHORT).show();
            hasError=true;
        }else if("".equals(iceAppBean.getBloodGroup())){
            Toast.makeText(context, "Please enter blood group", Toast.LENGTH_SHORT).show();
            hasError=true;
        }else  if (!Pattern.matches(PHONE_REGEX, iceAppBean.getEmrgncyCtNo())) {
            Toast.makeText(context, "Please enter contact no in 10 digits", Toast.LENGTH_SHORT).show();
            hasError=true;
        } else if(iceAppBean.getEmrgncyCtPrsn().length()>16 ||iceAppBean.getOwnerName().length()>16){
            Toast.makeText(context, "Max Character allowed for name is 16", Toast.LENGTH_SHORT).show();
            hasError=true;
        }else if(iceAppBean.getRelation().length()>8){
            Toast.makeText(context, "Max Character allowed for relation is 8", Toast.LENGTH_SHORT).show();
            hasError=true;
        }else if(iceAppBean.getBloodGroup().length()>5){
            Toast.makeText(context, "Max Character allowed for blood group is 5", Toast.LENGTH_SHORT).show();
            hasError=true;
        }
        return hasError;
    }

}
