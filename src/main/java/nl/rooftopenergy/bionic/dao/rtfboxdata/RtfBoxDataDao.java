package nl.rooftopenergy.bionic.dao.rtfboxdata;

import nl.rooftopenergy.bionic.dao.Dao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;

import java.util.Date;
import java.util.List;

public interface RtfBoxDataDao extends Dao<RtfBoxData, Integer> {

    /**
     * Returns list of boxes using id number;
     *
     * @param rtfBox comparing object
     * @return list of boxes;
     */
    public List<RtfBoxData> findByRtfBoxId(RtfBox rtfBox);

    /**
     * Gets number of whole energy was produced by solar panel.
     *
     * @param rtfBox identifier number of the box.
     * @return value of produced energy.
     */
    public Integer findTotalProduction(RtfBox rtfBox);

    /**
     * Gets number of whole energy was consumed by customer.
     *
     * @param rtfBox identifier number of the box.
     * @return value of consumed energy.
     */
    public Integer findTotalConsumption(RtfBox rtfBox);

    /**
     * Gets list of notes for the period.
     *
     * @param rtfBox identifier of the box.
     * @param start  date when the period is started.
     * @param end    date when the period should be finished
     * @return list of notes for the period.
     */
    public List<RtfBoxData> findByPeriod(RtfBox rtfBox, Date start, Date end);

}
