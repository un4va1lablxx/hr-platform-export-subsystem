package export.user.exporters;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import export.user.annotations.ExcelColumn;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelExporter<T> {

    private final Class<T> beanClass;

    public ExcelExporter(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public byte[] export(List<T> exportData) {
        try (SXSSFWorkbook wb = new SXSSFWorkbook(100);
             ByteArrayOutputStream out = new ByteArrayOutputStream())
        {
            Sheet sheet = wb.createSheet("Лист 1");
            List<Field> fields = List.of(beanClass.getDeclaredFields());

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                String header = (annotation != null) ? annotation.name() : field.getName();
                headerRow.createCell(i).setCellValue(header);
            }

            for (int r = 0; r < exportData.size(); r++)
            {
                T item = exportData.get(r);
                Row row = sheet.createRow(r + 1);
                for (int c = 0; c < fields.size(); c++)
                {
                    Field field = fields.get(c);
                    field.setAccessible(true);
                    Object value = field.get(item);
                    row.createCell(c).setCellValue(value != null ? value.toString() : "");
                }
            }
            wb.write(out);
            wb.dispose();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при экспорте в Excel", e);
        }
    }
}
