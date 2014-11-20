package nl.rooftopenergy.bionic.dao.consumption;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.Consumption;



public class ConsumptionJpaDao extends JpaDao<Consumption,Long> implements ConsumptionDao {
    public ConsumptionJpaDao() {
        super(Consumption.class);
    }
}
