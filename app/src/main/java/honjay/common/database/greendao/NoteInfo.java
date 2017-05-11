package honjay.common.database.greendao;

import javax.inject.Singleton;

import honjay.common.database.origin.Note;
import honjay.common.greendao.model.gen.DaoSession;

/**
 * Created by honjayChen on 2017/4/5.
 */


@Singleton
public class NoteInfo extends BaseInfo<Note,Long> {


    public NoteInfo(DaoSession daoSession) {
        super(daoSession);
    }
    private static DaoSession _daoSession;

    public static NoteInfo getInstance(DaoSession daoSession){

        _daoSession = daoSession;
        return  SingleLoader.INSTANCE;
    }

    private static class SingleLoader{
        final static NoteInfo INSTANCE = new NoteInfo(_daoSession);
    }

}