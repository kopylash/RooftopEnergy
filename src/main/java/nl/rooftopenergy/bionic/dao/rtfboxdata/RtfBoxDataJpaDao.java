package nl.rooftopenergy.bionic.dao.rtfboxdata;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
public class RtfBoxDataJpaDao extends JpaDao<RtfBoxData,Integer> implements RtfBoxDataDao {
    public RtfBoxDataJpaDao() {

        super(RtfBoxData.class);
    }
    @Transactional
    public List<RtfBoxData> findByRtfBoxId(RtfBox rtfBox){
        String q = "SELECT r FROM RtfBoxData r WHERE r.rtfBox = :rtfBox";
        Query query = getEntityManager().createQuery(q);
        query.setParameter("rtfBox", rtfBox);
        List<RtfBoxData> list = query.getResultList();
        return list;
    }

}
