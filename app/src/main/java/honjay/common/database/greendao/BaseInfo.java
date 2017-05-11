package honjay.common.database.greendao;


import android.util.Log;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxDao;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Singleton;

import honjay.common.utils.Rxbus.RxBusHelper;
import honjay.common.greendao.model.gen.DaoSession;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by honjayChen on 2017/4/4.
 */


@Singleton
public abstract class BaseInfo<T,K>  {

    private AbstractDao<T, K> mDao;

    public BaseInfo(DaoSession daoSession) {
        try
        {
            mDao = (AbstractDao<T, K>)daoSession.getDao(Class.forName(getGenericClassName()));
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
    }

    private Observer<Object> getObserver() {
        return new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Insert", " onSubscribe : " + d.isDisposed());
            }
            @Override
            public void onNext(Object o) {
                if( o instanceof List<?>)
                {
                }
                else if( o instanceof Object[])
                {}
                else if(o instanceof Iterable<?>)
                {}
                else
                {}
                RxBusHelper.post("这是发送的消息");
                Log.d("Insert", " onNext : size :");
            }
            @Override
            public void onError(Throwable e) {
                Log.d("Insert", " onError : " + e.getMessage());
                RxBusHelper.post(" onError : " + e.getMessage());
            }
            @Override
            public void onComplete() {
                Log.d("Insert", " onComplete");
            }
        };
    }

    public void Rxinsert(final T t) {
        try {
            final RxDao<T, Void> rxDao = (RxDao<T, Void>) mDao.rx();
            RxJavaInterop.toV2Observable(rxDao.insert(t))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    // 当调用订阅操作（即调用Observable.subscribe()方法）的时候，被观察者才真正开始发出事件。
                    // 核心源码见Observable类的create(OnSubscribe<T> f)方法的解释
                    rxDao.insert(t).subscribe(new Subscriber<T>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e != null){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onNext(T t) {

                        }
                    });
                }
            }).start();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Rxinsert(final T... ts) {
        try {
            final RxDao<T, Void> rxDao = (RxDao<T, Void>) mDao.rx();
            RxJavaInterop.toV2Observable(rxDao.insertInTx(ts))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Rxinsert(final List ls) {
        try {
            final RxDao<T, Void> rxDao = (RxDao<T, Void>) mDao.rx();
            RxJavaInterop.toV2Observable(rxDao.insertInTx(ls))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Rxupdate(final T t) {
        try {
            final RxDao<T, Void> rxDao = (RxDao<T, Void>) mDao.rx();
            RxJavaInterop.toV2Observable(rxDao.update(t))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public void Rxdelete(final T t) {
        try {
            final RxDao<T, Void> rxDao = (RxDao<T, Void>) mDao.rx();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rxDao.delete(t).subscribe(new Subscriber<Void>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e != null){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onNext(Void aVoid) {

                        }
                    });
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void loadAll() {
        try {
            final RxDao<T, Void> rxDao = (RxDao<T, Void>) mDao.rx();
            RxJavaInterop.toV2Observable(rxDao.loadAll())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insert(T item) {
        mDao.insert(item);
    }

    public void insert(T... items) {
        mDao.insertInTx(items);
    }

    public void insert(List items) {
        mDao.insertInTx(items);
    }

    public void InsertOrUpdate(T item) {
        mDao.insertOrReplace(item);
    }

    public void InsertOrUpdate(T... items) {
        mDao.insertOrReplaceInTx(items);
    }

    public void InsertOrUpdate(List items) {
        mDao.insertOrReplaceInTx(items);
    }

    public void deleteByID(K key) {
        mDao.deleteByKey(key);
    }

    public void delete(T item) {
        mDao.delete(item);
    }

    public void delete(T... items) {
        mDao.deleteInTx(items);
    }

    public void delete(List items) {
        mDao.deleteInTx(items);
    }

    public void deleteAll() {
        mDao.deleteAll();
    }

    public void update(T item) {
        mDao.update(item);
    }

    public void update(T... items) {
        mDao.updateInTx(items);
    }

    public void update(List items) {
        mDao.updateInTx(items);
    }

    public  T queryByID(K key) {
        return  mDao.load(key);
    }

    public List queryAll() {
        return mDao.loadAll();
    }

    public List query(String where, String... params) {

        return mDao.queryRaw(where, params);
    }

    public QueryBuilder queryBuilder() {

        return mDao.queryBuilder();
    }

    public long count() {
        return mDao.count();
    }

    public void refresh(T item) {
        mDao.refresh(item);

    }

    public void detach(T item) {
        mDao.detach(item);
    }

    public RxDao getRxDao(){
        return  mDao.rx();
    }

    public QueryBuilder getQueryBuilder(){
        return  mDao.queryBuilder();
    }

    public final Type getType() {
        Type superType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superType).getActualTypeArguments()[0];
        return type;
    }


    public final String getGenericClassName() {
        String raw = getType().toString();
        int spaceIndex = raw.lastIndexOf(" ");
        String result = raw.substring(spaceIndex + 1);
        return result;
    }

}