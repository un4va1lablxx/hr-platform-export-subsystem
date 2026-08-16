package export.user.exporters;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;

import java.lang.reflect.Field;
import java.util.List;

public class NaturalOrderPositionStrategy<T> extends ColumnPositionMappingStrategy<T> {

    @Override
    public String[] generateHeader(T bean) {
        List<Field> fields = List.of(bean.getClass().getDeclaredFields());
        String[] header = new String[fields.size()];
        String[] columnMapping = new String[fields.size()];

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            CsvBindByName annotation = field.getAnnotation(CsvBindByName.class);
            header[i] = (annotation != null) ? annotation.column() : field.getName();
            columnMapping[i] = field.getName();
        }
        setColumnMapping(columnMapping);

        return header;
    }

}
