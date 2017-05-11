package com.example.honjaychen.dagger.initial;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.alipay.euler.andfix.BuildConfig;
import com.alipay.euler.andfix.patch.PatchManager;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;

import honjay.common.dagger.component.AppComponent;
import honjay.common.dagger.component.DaggerAppComponent;
import honjay.common.dagger.module.AppModule;
import honjay.common.dagger.module.DBServiceModule;
import honjay.common.dagger.module.GreenDaoModule;
import honjay.common.dagger.module.NetModule;
import honjay.common.dagger.module.PersistentModule;
import honjay.common.dagger.module.ServiceModule;
import honjay.common.dagger.module.SqlBriteModule;

/**
 * Created by honjayChen on 2017/2/25.
 */
public class  App extends Application {

    private  AppComponent appComponent;

    String apatch_path = "/out.apatch";
    String path_all = "";

    private PatchManager patchManager;


    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    protected AppComponent createComponent() {
        appComponent = DaggerAppComponent.builder()
               .appModule(new AppModule(this))
               .netModule(new NetModule("http://192.168.56.1:39876/"))
               .greenDaoModule(new GreenDaoModule())
           //    .mainModule(new MainModule(ILoginView))
               .persistentModule(new PersistentModule())
                .serviceModule(new ServiceModule())
                .dBServiceModule(new DBServiceModule())
                .sqlBriteModule(new SqlBriteModule())
                //.todoModule(new TodoModule(this))
               .build();
        return appComponent;
    }


    public static AppComponent getAppComponent(Context context) {
        App app = (App) context.getApplicationContext();
        if (app.appComponent == null) {
            app.appComponent = app.createComponent();
        }
        return app.appComponent;
    }

    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
        //androidfix();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        enabledStrictMode();
        LeakCanary.install(this);
        //MultiDex.install(this);
        // Normal app init code...
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
    }

    private void androidfix()
    {
        patchManager = new PatchManager(this);
        patchManager.init(BuildConfig.VERSION_CODE + "");//current version

        patchManager.loadPatch();

        try {
//            path_all = Environment.getExternalStorageDirectory().getAbsolutePath() + apatch_path;

            path_all = "/sdcard" + apatch_path;
            Log.e("path", path_all);

            File file = new File(path_all);
            if (file.exists()) {
                Log.e("path exists" , file.getPath());
                patchManager.addPatch(path_all);
            } else {
                Log.e("path unexists", path_all + "========");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}