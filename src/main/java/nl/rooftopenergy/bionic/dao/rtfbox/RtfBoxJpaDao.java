package nl.rooftopenergy.bionic.dao.rtfbox;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.RtfBox;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RtfBoxJpaDao extends JpaDao<RtfBox,Integer> implements RtfBoxDao {

    public RtfBoxJpaDao() {

        super(RtfBox.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RtfBox findByCompanyId(Company company){
        String q = "SELECT r FROM RtfBox r WHERE r.company = :company";
        Query query = getEntityManager().createQuery(q);
        query.setParameter("company", company);
        RtfBox box = (RtfBox)query.getSingleResult();
        return box;
    }
}
