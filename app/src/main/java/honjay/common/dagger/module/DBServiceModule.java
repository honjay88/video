package honjay.common.dagger.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import honjay.common.greendao.model.gen.DaoSession;
import honjay.common.database.greendao.NoteInfo;
//import honjay.common.service.NoteService;


/**
 * Created by honjayChen on 2017/4/5.
 */



@Module(includes = GreenDaoModule.class)
public class DBServiceModule {
    @Singleton
    @Provides
    protected NoteInfo provideDBService(@Named("DaoSession") DaoSession daoSession) {

        return NoteInfo.getInstance(daoSession);

    }
}