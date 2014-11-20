package nl.rooftopenergy.bionic.dao.company;


import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.Company;


public class CompanyJpaDao extends JpaDao<Company,Long> implements CompanyDao {
    public CompanyJpaDao() {
        super(Company.class);
    }
}
