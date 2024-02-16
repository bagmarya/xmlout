package org.example;


import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class Dao {
//    public void save(Object object) {
//        if (object == null) {
//            return;
//        }
//        sessionFactory.getCurrentSession().saveOrUpdate(object);
//    }
    private static final Logger logger = LoggerFactory.getLogger(Dao.class);

    private SessionFactory sessionFactory;

    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    @Transactional
//    public List<Lpu> getLpuEntityList() {
//        return sessionFactory.getCurrentSession().createQuery("select l from Lpu l  where l.dateEnd is null and l.mkod!=450000 order by l.mkod", Lpu.class)
//                .getResultList();
//    }

}
