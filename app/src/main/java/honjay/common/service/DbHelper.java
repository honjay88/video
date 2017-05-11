package honjay.common.service;

import honjay.common.database.origin.Note;

/**
 * Created by honjayChen on 2017/4/4.
 */

public interface DbHelper {

    void insert(final Note entity);
    void update(final Note entity);
    void delete(final Note entity);

    /*Observable<List<Note>> getAllData();

    Observable<Note> getData();

    Observable<Boolean> isEmpty();

    Observable<Boolean> saveData(Note entity);//T entity

    Observable<Boolean> saveDataList(List<Note> lsentity);

    Observable<Boolean> DeleteData();*/

}
