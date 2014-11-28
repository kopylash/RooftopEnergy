package nl.rooftopenergy.bionic.dao.rtfbox;

import nl.rooftopenergy.bionic.dao.Dao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.RtfBox;

import java.util.List;

public interface RtfBoxDao extends Dao<RtfBox, Integer> {

    /**
     * Returns list of boxes which belong to company.
     * @returns list of boxes;
     */
    public List<RtfBox> findByCompanyId(Company company);

    /**
     * Returns box which belongs to company.
     * @returns  box;
     */
    public RtfBox findByRtfBoxId(Integer rtfBoxID);
}
