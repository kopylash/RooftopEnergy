package nl.rooftopenergy.bionic.dao.rtfboxdata;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.RtfBoxData;


public class RtfBoxDataJpaDao extends JpaDao<RtfBoxData,Long> implements RtfBoxDataDao {
    public RtfBoxDataJpaDao() {
        super(RtfBoxData.class);
    }
}
