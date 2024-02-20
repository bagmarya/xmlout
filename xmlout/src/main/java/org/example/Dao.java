package org.example;


import org.example.entity.People;
import org.example.entity.Sluch;
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

    public List<Sluch> getListSluch(Integer year, Integer month) {
        return sessionFactory.getCurrentSession().createQuery("select s from Sluch s where s.year =:year and s.month =:month order by s.slId", Sluch.class)
                .setParameter("year", year)
                .setParameter("month", month).getResultList();
    }
public List<People> gePeople() {
        return sessionFactory.getCurrentSession().createQuery("select p from People p", People.class)
                .getResultList();
    }
//    @Transactional
//    public List<Lpu> getLpuEntityList() {
//        return sessionFactory.getCurrentSession().createQuery("select l from Lpu l  where l.dateEnd is null and l.mkod!=450000 order by l.mkod", Lpu.class)
//                .getResultList();
//    }

}
