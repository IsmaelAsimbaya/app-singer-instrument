package com.distribuida.repository;

import com.distribuida.db.SingerInstrument;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class SingerInstrumentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<SingerInstrument> getAllSingersInstruments() {
        return em.createQuery("SELECT si FROM SingerInstrument si", SingerInstrument.class)
                .getResultList();
    }

    public SingerInstrument getSingerInstrumentById(Integer id) {
        return em.find(SingerInstrument.class, id);
    }

    public SingerInstrument createSingerInstrument(SingerInstrument si) {
        em.persist(si);
        return si;
    }

    public SingerInstrument updateSingerInstrument(SingerInstrument si) {
        em.merge(si);
        return si;
    }

    public void deleteSingerInstrument(Integer id) {
        SingerInstrument si = em.find(SingerInstrument.class, id);
        if (si != null) {
            em.remove(si);
        }
    }

    public List<SingerInstrument> findBySingerId(Integer id) {
        return em.createQuery("SELECT s FROM SingerInstrument s WHERE s.singer_id = :id", SingerInstrument.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<SingerInstrument> findByInstrumentId(Integer id) {
        return em.createQuery("SELECT s FROM SingerInstrument s WHERE s.instrument_id = :id", SingerInstrument.class)
                .setParameter("id", id)
                .getResultList();
    }

    /*public SingerInstrument findByIds(Integer ids, Integer idi) {
        return em.createQuery("SELECT s FROM SingerInstrument s WHERE s.singer_id = :ids AND s.instrument_id = :idi", SingerInstrument.class)
                .setParameter("ids", ids)
                .setParameter("idi", idi)
                .getSingleResult();
    }*/

}
