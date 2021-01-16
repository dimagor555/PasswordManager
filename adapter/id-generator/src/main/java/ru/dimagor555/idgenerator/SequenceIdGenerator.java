package ru.dimagor555.idgenerator;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.IdGenerator;
import ru.dimagor555.domain.port.RecordRepository;

import java.util.Collection;

public class SequenceIdGenerator implements IdGenerator {
    private final RecordRepository recordRepository;

    public SequenceIdGenerator(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public long generate() {
        Collection<Record> allRecords = recordRepository.getAll();
        long maxId = Long.MIN_VALUE;
        for (Record record : allRecords) {
            long currId = record.getId();
            if (currId > maxId) {
                maxId = currId;
            }
        }
        return maxId + 1;
    }
}
