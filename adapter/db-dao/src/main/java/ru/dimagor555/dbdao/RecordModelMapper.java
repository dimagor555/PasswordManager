package ru.dimagor555.dbdao;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.exception.RecordValidationException;

public class RecordModelMapper {
    public RecordModel toModel(Record record) {
        if (record == null) {
            throw new RecordValidationException("Record is null");
        }
        return new RecordModel(record.getId(), record.getSite(),
                record.getLogin(), record.getPassword());
    }

    public Record fromModel(RecordModel model) {
        if (model != null) {
            return new Record(model.getId(), model.getSite(),
                    model.getLogin(), model.getPass());
        } else {
            return null;
        }
    }
}
