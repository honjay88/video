package honjay.common.dagger.module;

import com.example.honjaychen.dagger.ui.login.view.ILoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by honjayChen on 2017/3/26.
 */

@Module
public class MainModule {
    private final ILoginView view ;
    public MainModule(ILoginView view){
        this.view = view ;
    }
    @Provides
    @Singleton
    ILoginView provideILogView(){
        return view ;
    }
}