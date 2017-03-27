package rsklabs.com.icecall.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import rsklabs.com.icecall.bean.IceAppBean;
import rsklabs.com.icecall.dao.IceDao;

/**
 * Created by Kumar on 2/8/2016.
 */
public class IceAppHelper extends IceDao {


    public IceAppHelper(Context context) {
        super(context);
    }


    public boolean create(IceAppBean iceAppBean) {

        ContentValues values = new ContentValues();
        values.put("ownerName", iceAppBean.getOwnerName());
        values.put("emrgncyCtPrsn", iceAppBean.getEmrgncyCtPrsn());
        values.put("emrgncyCtNo", iceAppBean.getEmrgncyCtNo());
        values.put("bloodGroup", iceAppBean.getBloodGroup());
        values.put("relation", iceAppBean.getRelation());
        values.put("widgetColor", iceAppBean.getWidgetColor());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("iceCall", null, values) > 0;
        db.close();

        return createSuccessful;
    }


    public IceAppBean read() {

        IceAppBean iceAppBean = new IceAppBean();

        String sql = "SELECT * FROM iceCall";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                String ownerName = cursor.getString(cursor.getColumnIndex("ownerName"));
                String emrgncyCtPrsn = cursor.getString(cursor.getColumnIndex("emrgncyCtPrsn"));
                String emrgncyCtNo = cursor.getString(cursor.getColumnIndex("emrgncyCtNo"));
                String bloodGroup = cursor.getString(cursor.getColumnIndex("bloodGroup"));
                String relation = cursor.getString(cursor.getColumnIndex("relation"));
                int wgtColor = cursor.getInt(cursor.getColumnIndex("widgetColor"));


                iceAppBean.setOwnerName(ownerName);
                iceAppBean.setEmrgncyCtPrsn(emrgncyCtPrsn);
                iceAppBean.setEmrgncyCtNo(emrgncyCtNo);
                iceAppBean.setBloodGroup(bloodGroup);
                iceAppBean.setRelation(relation);
                iceAppBean.setWidgetColor(wgtColor);



            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return iceAppBean;
    }
}
