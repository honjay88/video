package honjay.common.database.SqlBrite;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by honjayChen on 2017/4/2.
 */

@AutoValue
public abstract class TodoList implements Parcelable {
    public static final String TABLE = "todo_list";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String ARCHIVED = "archived";

    public abstract long id();
    public abstract String name();
    public abstract boolean archived();

    public static Func1<Cursor, List<TodoList>> MAP = new Func1<Cursor, List<TodoList>>() {
        @Override public List<TodoList> call(final Cursor cursor) {
            try {
                List<TodoList> values = new ArrayList<>(cursor.getCount());

                while (cursor.moveToNext()) {
                    long id = Db.getLong(cursor, ID);
                    String name = Db.getString(cursor, NAME);
                    boolean archived = Db.getBoolean(cursor, ARCHIVED);
                    values.add(new AutoValue_TodoList(id, name, archived));
                }
                return values;
            } finally {
                cursor.close();
            }
        }
    };

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(ID, id);
            return this;
        }

        public Builder name(String name) {
            values.put(NAME, name);
            return this;
        }

        public Builder archived(boolean archived) {
            values.put(ARCHIVED, archived);
            return this;
        }

        public ContentValues build() {
            return values; // TODO defensive copy?
        }
    }
}