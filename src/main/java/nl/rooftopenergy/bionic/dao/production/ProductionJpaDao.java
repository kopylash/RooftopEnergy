package nl.rooftopenergy.bionic.dao.production;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.Production;


public class ProductionJpaDao extends JpaDao<Production,Long> implements ProductionDao {
    public ProductionJpaDao() {
        super(Production.class);
    }
}
