package br.edu.ifpb.domain;

import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/01/2019, 10:23:11
 */
@Converter//(autoApply = true)
public class ConvertLocalDateToLong implements
        AttributeConverter<LocalDate, Long> {

    @Override
    public Long convertToDatabaseColumn(LocalDate attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toEpochDay();
    }

    @Override
    public LocalDate convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        return LocalDate.ofEpochDay(dbData);
    }
}
