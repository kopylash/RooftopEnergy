package nl.rooftopenergy.bionic.dao.company;


import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.Company;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
public class CompanyJpaDao extends JpaDao<Company,Integer> implements CompanyDao {

    private static final Logger logger = Logger.getLogger(CompanyJpaDao.class.getName());

    public CompanyJpaDao() {

        super(Company.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Company fundById(Integer id) {
        Company company = null;
        try {
            String q = "SELECT c FROM Company c WHERE c.companyId = :id";
            Query query = getEntityManager().createQuery(q);
            query.setParameter("id", id);
            company = (Company) query.getSingleResult();
        }catch(RuntimeException e){
            logger.error(e.getMessage(), e);
        }
        return company;
    }

}
