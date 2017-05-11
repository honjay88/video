package honjay.common.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by honjayChen on 2017/2/28.
 */
@Module
public class PersistentModule {
    /*Context context;

    public PersistentModule(Context context) {
        this.context = context;
    }
*/
    @Singleton
    @Provides
    @Named("DEF")
    public SharedPreferences sharedPreferencesDef(Context context) {
        return context.getSharedPreferences("DEF", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    @Named("NEW")
    public SharedPreferences sharedPreferencesNew(Context context) {
        return context.getSharedPreferences("NEW", Context.MODE_PRIVATE);
    }
}
