package honjay.common.database.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

/**
 * Created by honjayChen on 2017/2/28.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
   /* @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新
        MigrationHelper.migrate(db,UserBeanDao.class);
    }*/
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        if (oldVersion == newVersion) {
            Log.d("onUpgrade", "数据库是最新版本" + oldVersion + "，不需要升级");
            return;
        }
        Log.d("onUpgrade", "数据库从版本" + oldVersion + "升级到版本" + newVersion);
        switch (oldVersion) {
            case 1:
                String sql = "";
                db.execSQL(sql);
            case 2:
            default:
                break;
        }
    }

}