package honjay.common.dagger.component;

import android.content.Context;
import android.support.v4.app.DialogFragment;

import com.example.honjaychen.dagger.ui.Fragment.B1Fragment;
import com.example.honjaychen.dagger.ui.Fragment.CommonDialogFragment;
import com.example.honjaychen.dagger.ui.Fragment.CustomDialogFragment;
import com.example.honjaychen.dagger.ui.login.view.LoginOptimizedActivity;

import honjay.common.dagger.module.AppModule;
import honjay.common.dagger.module.DBServiceModule;
import honjay.common.dagger.module.GreenDaoModule;
import honjay.common.dagger.module.MainModule;
import honjay.common.dagger.module.NetModule;
import honjay.common.dagger.module.PersistentModule;


import javax.inject.Singleton;

import dagger.Component;
import honjay.common.dagger.module.ServiceModule;
import honjay.common.dagger.module.SqlBriteModule;
import honjay.common.dagger.scope.ContextLife;
import honjay.common.dagger.scope.PerApp;

/**
 * Created by honjayChen on 2017/2/25.
 */

@PerApp    //添加@PerApp标明该Component中有Module使用了@PerApp
@Singleton // 标明该Component中有Module使用了@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        GreenDaoModule.class,
        PersistentModule.class,
        MainModule.class,
        ServiceModule.class,
        DBServiceModule.class,
        SqlBriteModule.class
})
public abstract class AppComponent {

    //@ContextLife("Application")
   // public abstract Context getApplication();

    public abstract void inject(LoginOptimizedActivity activity);
    public abstract void inject(B1Fragment Fragment);
    public abstract void inject(CommonDialogFragment dialogFragment);
    //public abstract void inject(uploadIntentService IntentService);
}