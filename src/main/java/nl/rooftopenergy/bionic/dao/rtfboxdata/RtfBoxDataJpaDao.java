package nl.rooftopenergy.bionic.dao.rtfboxdata;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class RtfBoxDataJpaDao extends JpaDao<RtfBoxData,Integer> implements RtfBoxDataDao {
    private static final Logger logger = Logger.getLogger(RtfBoxDataJpaDao.class.getName());

    public RtfBoxDataJpaDao() {

        super(RtfBoxData.class);
    }

    @Override
    @Transactional
    public List<RtfBoxData> findByRtfBoxId(RtfBox rtfBox){
        List<RtfBoxData> list = null;
        try {
            String q = "SELECT r FROM RtfBoxData r WHERE r.rtfBox = :rtfBox";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            list = query.getResultList();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Integer findTotalProduction(RtfBox rtfBox) {
        Integer result = null;
        try {
            String q = "SELECT max(r.production) FROM RtfBoxData r WHERE r.rtfBox = :rtfBox";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            result = (Integer) query.getSingleResult();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Integer findTotalConsumption(RtfBox rtfBox) {
        Integer result = null;
        try {
            String q = "SELECT max(r.consumption) FROM RtfBoxData r WHERE r.rtfBox = :rtfBox";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            result = (Integer) query.getSingleResult();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<RtfBoxData> findByPeriod(RtfBox rtfBox, Date startPeriod, Date endPeriod) {
        List<RtfBoxData> list = null;
        try {
            String q = "SELECT r FROM RtfBoxData r WHERE " +
                    "r.rtfBox = :rtfBox AND r.date > :startPeriod AND r.date < :endPeriod";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            query.setParameter("startPeriod", startPeriod);
            query.setParameter("endPeriod", endPeriod);
            list = query.getResultList();

        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Integer findTotalProductionByPeriod(RtfBox rtfBox, Date startPeriod, Date endPeriod) {
        Integer result = null;
        try {
            String q = "SELECT MAX(r.production) FROM RtfBoxData r WHERE r.rtfBox = :rtfBox AND" +
                    " r.date >= :startPeriod AND r.date <= :endPeriod ";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            query.setParameter("startPeriod", startPeriod);
            query.setParameter("endPeriod", endPeriod);
            result = (Integer) query.getSingleResult();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Integer findTotalConsumptionByPeriod(RtfBox rtfBox, Date startPeriod, Date endPeriod) {
        Integer result = null;
        try {
            String q = "SELECT MAX(r.consumption) FROM RtfBoxData r WHERE r.rtfBox = :rtfBox AND" +
                    " r.date >= :startPeriod AND r.date <= :endPeriod ";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            query.setParameter("startPeriod", startPeriod);
            query.setParameter("endPeriod", endPeriod);
            result = (Integer) query.getSingleResult();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Integer findTotalProductionBefore(RtfBox rtfBox, Date endPeriod) {
        Integer result = null;
        try {
            String q = "SELECT MAX(r.production) FROM RtfBoxData r WHERE r.rtfBox = :rtfBox AND" +
                    " r.date < :endPeriod ";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            query.setParameter("endPeriod", endPeriod);
            result = (Integer) query.getSingleResult();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public Integer findTotalConsumptionBefore(RtfBox rtfBox, Date endPeriod) {
        Integer result = null;
        try {
            String q = "SELECT MAX(r.consumption) FROM RtfBoxData r WHERE r.rtfBox = :rtfBox AND" +
                    " r.date < :endPeriod ";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            query.setParameter("endPeriod", endPeriod);
            result = (Integer) query.getSingleResult();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public RtfBoxData findFirstNote(RtfBox rtfBox) {
        RtfBoxData result = null;
        try {
            String q = "SELECT r FROM RtfBoxData r WHERE" +
                    " r.readingId = (SELECT MIN(r.readingId) FROM RtfBoxData r WHERE  r.rtfBox = :rtfBox) ";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("rtfBox", rtfBox);
            result = (RtfBoxData)query.getSingleResult();
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

}
