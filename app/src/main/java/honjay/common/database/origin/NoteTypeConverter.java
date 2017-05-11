package honjay.common.database.origin;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by honjayChen on 2017/4/4.
 */

public class NoteTypeConverter implements PropertyConverter<NoteType, String> {
    @Override
    public NoteType convertToEntityProperty(String databaseValue) {
        return NoteType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(NoteType entityProperty) {
        return entityProperty.name();
    }
}