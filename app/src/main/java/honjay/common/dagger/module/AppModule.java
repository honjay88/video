package honjay.common.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.example.honjaychen.dagger.initial.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import honjay.common.dagger.scope.ContextLife;
import honjay.common.dagger.scope.PerApp;

/**
 * Created by honjayChen on 2017/2/25.
 */
/*
@Module(
        includes = {
                DbModule.class,
        }
)*/
@Module
public class AppModule {
    private App mApplication;
    public AppModule(@NonNull App mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @NonNull
    @Singleton     // 标明该方法只产生一个实例
    public App provideApplication() {
        return mApplication;
    }

    @Provides
    @PerApp
  //  @Singleton
    //@ContextLife("Application")
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApplication.getResources();
    }
}