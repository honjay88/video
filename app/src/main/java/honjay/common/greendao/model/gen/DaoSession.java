package honjay.common.greendao.model.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import honjay.common.database.origin.Note;
import honjay.common.database.origin.UserBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig noteDaoConfig;
    private final DaoConfig userBeanDaoConfig;

    private final NoteDao noteDao;
    private final UserBeanDao userBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        noteDaoConfig = daoConfigMap.get(NoteDao.class).clone();
        noteDaoConfig.initIdentityScope(type);

        userBeanDaoConfig = daoConfigMap.get(UserBeanDao.class).clone();
        userBeanDaoConfig.initIdentityScope(type);

        noteDao = new NoteDao(noteDaoConfig, this);
        userBeanDao = new UserBeanDao(userBeanDaoConfig, this);

        registerDao(Note.class, noteDao);
        registerDao(UserBean.class, userBeanDao);
    }
    
    public void clear() {
        noteDaoConfig.clearIdentityScope();
        userBeanDaoConfig.clearIdentityScope();
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public UserBeanDao getUserBeanDao() {
        return userBeanDao;
    }

}
