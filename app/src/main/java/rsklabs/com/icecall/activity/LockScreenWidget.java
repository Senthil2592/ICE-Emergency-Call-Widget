package rsklabs.com.icecall.activity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

import rsklabs.com.icecall.R;
import rsklabs.com.icecall.bean.IceAppBean;
import rsklabs.com.icecall.helper.IceAppHelper;

/**
 * Implementation of App Widget functionality.
 */
public class LockScreenWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created


    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        IceAppBean iceAppBean = new IceAppHelper(context).read();
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        if(""==iceAppBean.getEmrgncyCtNo()|| null==iceAppBean.getEmrgncyCtNo()){
            callIntent.setData(Uri.parse(" "));

        }else {
            callIntent.setData(Uri.parse("tel:" + iceAppBean.getEmrgncyCtNo()));
        }

        callIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                callIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.lock_screen_widget);
        views.setTextViewText(R.id.wgtOwnerNameValue, iceAppBean.getOwnerName());
        if(null==iceAppBean.getEmrgncyCtPrsn() && null==iceAppBean.getRelation()){
            views.setTextViewText(R.id.wgtEmergencyContactPersonValue,  "");

        }else {
            views.setTextViewText(R.id.wgtEmergencyContactPersonValue, iceAppBean.getEmrgncyCtPrsn() + "(" + iceAppBean.getRelation() + ")");
        }
        views.setTextViewText(R.id.wgtEmergencyContactNumberValue, iceAppBean.getEmrgncyCtNo());
        views.setTextViewText(R.id.wgtBloodGrpValue, iceAppBean.getBloodGroup());

        if(0!=iceAppBean.getWidgetColor()) {
            views.setTextColor(R.id.wgtOwnerName, iceAppBean.getWidgetColor());
            views.setTextColor(R.id.wgtOwnerNameValue, iceAppBean.getWidgetColor());
            views.setTextColor(R.id.wgtEmergencyContactPerson, iceAppBean.getWidgetColor());
            views.setTextColor(R.id.wgtEmergencyContactPersonValue, iceAppBean.getWidgetColor());
            views.setTextColor(R.id.wgtEmergencyContactNumber, iceAppBean.getWidgetColor());
            views.setTextColor(R.id.wgtEmergencyContactNumberValue, iceAppBean.getWidgetColor());
            views.setTextColor(R.id.wgtBloodGrp, iceAppBean.getWidgetColor());
            views.setTextColor(R.id.wgtBloodGrpValue, iceAppBean.getWidgetColor());
        }
        views.setOnClickPendingIntent(R.id.next_level_button, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

