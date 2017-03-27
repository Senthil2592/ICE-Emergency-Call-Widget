package rsklabs.com.icecall.activity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rsklabs.com.icecall.R;
import rsklabs.com.icecall.bean.IceAppBean;
import rsklabs.com.icecall.helper.DetectConnection;
import rsklabs.com.icecall.helper.IceAppHelper;
import rsklabs.com.icecall.validator.ICEAppFieldValidator;

public class MainActivity extends AppCompatActivity {


    private static final int START_LEVEL = 1;
    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Create the next level button, which tries to show an interstitial when clicked.
        mNextLevelButton = ((Button) findViewById(R.id.next_level_button));
        mNextLevelButton.setEnabled(true);

        final Spinner spinner = (Spinner) findViewById(R.id.colorDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colorDropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, LockScreenWidget.class));
        new LockScreenWidget().onUpdate(this, appWidgetManager, appWidgetIds);

        IceAppBean iceAppBean = new IceAppHelper(this).read();
        if(iceAppBean!=null){

            EditText ownerNameValue = (EditText) findViewById(R.id.ownerNameValue);
            EditText emergContPersonValue = (EditText) findViewById(R.id.emergContPersonValue);
            EditText emergContNoValue = (EditText) findViewById(R.id.emergContNoValue);
            EditText relationValue = (EditText) findViewById(R.id.relationValue);
            EditText bloodGrpValue = (EditText) findViewById(R.id.bloodGrpValue);
            ownerNameValue.setText(iceAppBean.getOwnerName());
            emergContPersonValue.setText(iceAppBean.getEmrgncyCtPrsn());
            emergContNoValue.setText(iceAppBean.getEmrgncyCtNo());
            relationValue.setText((iceAppBean.getRelation()));
            bloodGrpValue.setText(iceAppBean.getBloodGroup());
        }


        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Context context = view.getContext();
                EditText ownerNameValue = (EditText) findViewById(R.id.ownerNameValue);
                EditText emergContPersonValue = (EditText) findViewById(R.id.emergContPersonValue);
                EditText emergContNoValue = (EditText) findViewById(R.id.emergContNoValue);
                EditText relationValue = (EditText) findViewById(R.id.relationValue);
                EditText bloodGrpValue = (EditText) findViewById(R.id.bloodGrpValue);
                int spinnerValue= spinner.getSelectedItemPosition();
                String selected = spinner.getItemAtPosition(spinnerValue).toString();


                IceAppBean iceAppBean = new IceAppBean();
                SpinnerActivity spinnerActivity = new SpinnerActivity();
                spinnerActivity.getSpinnerValue(iceAppBean, selected);

                iceAppBean.setOwnerName(ownerNameValue.getText().toString());
                iceAppBean.setEmrgncyCtPrsn(emergContPersonValue.getText().toString());
                iceAppBean.setEmrgncyCtNo(emergContNoValue.getText().toString());
                iceAppBean.setRelation(relationValue.getText().toString());
                iceAppBean.setBloodGroup(bloodGrpValue.getText().toString());

                ICEAppFieldValidator iceAppFieldValidator = new ICEAppFieldValidator();
                boolean hasErrors=iceAppFieldValidator.validateFields(iceAppBean,context);
                if(!hasErrors) {
                    boolean createSuccessful = new IceAppHelper(context).create(iceAppBean);

                    if (createSuccessful) {
                        Toast.makeText(view.getContext(), "Details Updated in Widget", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "Unable to save data", Toast.LENGTH_SHORT).show();
                    }
                    showInterstitial();

                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, LockScreenWidget.class));
                    new LockScreenWidget().onUpdate(context, appWidgetManager, appWidgetIds);
                }
            }
        });

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent mi = new Intent(MainActivity.this, StepsToWidget.class);
            startActivity(mi);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        mNextLevelButton.setEnabled(true);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }
}
