package nl.rooftopenergy.bionic.dao.rtfbox;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.RtfBox;


public class RtfBoxJpaDao extends JpaDao<RtfBox,Long> implements RtfBoxDao {
    public RtfBoxJpaDao() {
        super(RtfBox.class);
    }
}
