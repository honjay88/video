package honjay.common.dagger.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import honjay.common.service.ApiService;
import retrofit2.Retrofit;

/**
 * Created by honjayChen on 2017/4/5.
 */


@Module(includes = NetModule.class)
public class ServiceModule {

    @Singleton
    @Provides
    protected ApiService provideWebApiService(@Named("Retrofit") Retrofit retrofit) {

        return retrofit.create(ApiService.class);
    }
}