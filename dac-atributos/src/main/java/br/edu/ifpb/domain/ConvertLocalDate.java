package br.edu.ifpb.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/01/2019, 10:23:11
 */
@Converter//(autoApply = true)
public class ConvertLocalDate implements
        AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute == null) {
            return null;
        }
        return Date.valueOf(attribute);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.toLocalDate();
//        return LocalDate.parse(dbData, DateTimeFormatter.ISO_DATE);
    }
}
