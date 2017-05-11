package honjay.common.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import honjay.common.database.SqlBrite.DbModule;

/**
 * Created by honjayChen on 2017/4/2.
 */

@Module(
        includes = {
                DbModule.class,
        }
)
public final class TodoModule {
    private final Application application;

    public TodoModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}
