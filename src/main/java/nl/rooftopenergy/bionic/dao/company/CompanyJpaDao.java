package nl.rooftopenergy.bionic.dao.company;


import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.Company;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CompanyJpaDao extends JpaDao<Company, Integer> implements CompanyDao {

    private static final Logger logger = Logger.getLogger(CompanyJpaDao.class.getName());

    public CompanyJpaDao() {

        super(Company.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAllPublic() {
        return getEntityManager().createQuery("SELECT c FROM Company c WHERE c.publicStatus = true").getResultList();

    }

    @Override
    @Transactional(readOnly = true)
    public Company findByName(String companyName) {
        return (Company) getEntityManager().createQuery("SELECT c from Company c WHERE c.companyName=:name")
                .setParameter("name", companyName).getSingleResult();
    }
}
