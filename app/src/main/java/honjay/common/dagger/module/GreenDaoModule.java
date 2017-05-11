package honjay.common.dagger.module;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;


import com.example.honjaychen.dagger.initial.App;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import honjay.common.greendao.model.gen.DaoMaster;
import honjay.common.greendao.model.gen.DaoSession;

/**
 * Created by honjayChen on 2017/2/28.
 */

@Module
public class GreenDaoModule {

 //   public GreenDaoModule(Application application){
   //   this.application = application;
   //}

    @Provides
  //  @UserScope
    @Singleton
    SQLiteDatabase provideSQLiteDatabase(App baseApp){
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(baseApp, "zlot-db", null);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        return sqlDB;
    }

    @Provides
 //  @UserScope
    @Singleton
    DaoMaster provideDaoMaster(SQLiteDatabase sqLiteDatabase){
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        return daoMaster;
    }
    @Provides
    @Named("DaoSession")
  //@UserScope
    @Singleton
    DaoSession provideDaoSession(DaoMaster daoMaster){
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }
}