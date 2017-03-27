package rsklabs.com.icecall.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kumar on 2/8/2016.
 */
public class IceDao extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "iceCall";

    public IceDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlDropTable = "DROP TABLE IF EXISTS iceCall";
        db.execSQL(sqlDropTable);
        String sqlCreate = "CREATE TABLE iceCall (ownerName TEXT, emrgncyCtPrsn TEXT," +
                "emrgncyCtNo TEXT, bloodGroup TEXT, relation TEXT, widgetColor int)";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
