package export.user.exporters;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvExporter<T> {

    private static final byte[] UTF8_BOM = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    private static final char DEFAULT_SEPARATOR = ';';

    private final Class<T> beanClass;

    public CsvExporter(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public byte[] export(List<T> exportData) {
        return export(exportData, DEFAULT_SEPARATOR);
    }

    public byte[] export(List<T> exportData, char separator) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {

            out.write(UTF8_BOM);

            var strategy = new NaturalOrderPositionStrategy<T>();
            strategy.setType(beanClass);

            var builder = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(strategy)
                    .withSeparator(separator)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            builder.write(exportData);
            writer.flush();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при экспорте в CSV", e);
        }
    }
}
