package nl.rooftopenergy.bionic.dao.company;


import nl.rooftopenergy.bionic.dao.Dao;
import nl.rooftopenergy.bionic.entity.Company;

import java.util.List;

public interface CompanyDao extends Dao<Company, Integer> {

    /**
     * Gets entity of the company by its identifier number.
     * @param id identifier number of the company.
     * @return entity of the company
     */
    public Company findById(Integer id);

    /**
     * Gets the list of companies with public status
     * so they allowed to use their data
     * @return
     */
    public List<Company> findAllPublic();
}
