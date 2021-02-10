package ru.dimagor555.dbdao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.repository.RecordDao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RecordHibernateDao implements RecordDao {
    private final RecordModelMapper mapper = new RecordModelMapper();

    public void create(Record record) {
        var session = openSession();
        session.beginTransaction();
        session.save(mapper.toModel(record));
        session.getTransaction().commit();
        session.close();
    }

    public void update(Record record) {
        var session = openSession();
        session.beginTransaction();
        session.update(mapper.toModel(record));
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Record record) {
        var session = openSession();
        session.beginTransaction();
        session.delete(mapper.toModel(record));
        session.getTransaction().commit();
        session.close();
    }

    public List<Record> findAll() {
        var session = openSession();
        var cb = session.getCriteriaBuilder();
        CriteriaQuery<RecordModel> cr = cb.createQuery(RecordModel.class);
        Root<RecordModel> root = cr.from(RecordModel.class);
        cr.select(root);
        Query<RecordModel> query = session.createQuery(cr);

        List<RecordModel> recordModels = query.getResultList();
        session.close();

        List<Record> records = new ArrayList<>(recordModels.size());
        recordModels.forEach(recordModel -> records.add(mapper.fromModel(recordModel)));
        return records;
    }

    private Session openSession() {
        return HibernateSessionFactoryUtil.openSession();
    }
}
