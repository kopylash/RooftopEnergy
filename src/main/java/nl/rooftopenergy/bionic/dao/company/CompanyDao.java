package nl.rooftopenergy.bionic.dao.company;


import nl.rooftopenergy.bionic.dao.Dao;
import nl.rooftopenergy.bionic.entity.Company;

public interface CompanyDao extends Dao<Company, Integer> {

    /**
     * Gets entity of the company by its identifier number.
     * @param id identifier number of the company.
     * @return entity of the company
     */
    public Company fundById(Integer id);

}
