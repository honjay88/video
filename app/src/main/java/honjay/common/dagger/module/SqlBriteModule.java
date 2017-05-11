package honjay.common.dagger.module;


import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.honjaychen.dagger.initial.App;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import honjay.common.database.SqlBrite.DbOpenHelper;
import rx.schedulers.Schedulers;


/**
 * Created by honjayChen on 2017/4/1.
 */

@Module
public final class SqlBriteModule {
    @Provides
    @Singleton
    SQLiteOpenHelper provideOpenHelper(App application) {
        return new DbOpenHelper(application);
    }

    @Provides
    @Singleton
    SqlBrite provideSqlBrite() {
        return new SqlBrite.Builder()
                .logger(new SqlBrite.Logger() {
                    @Override public void log(String message) {
                       // Timber.tag("Database").v(message);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
        db.setLoggingEnabled(true);
        return db;
    }
}
