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

    /**
     * Gets total production for the period.
     * @param startPeriod when period is started.
     * @param endPeriod when period id finished.
     * @return production of energy for the period.
     */
    public Integer findTotalProductionByPeriod(RtfBox rtfBox, Date startPeriod, Date endPeriod);

    /**
     * Gets total consumption for the period.
     * @param startPeriod when period is started.
     * @param endPeriod when period id finished.
     * @return consumption of energy for the period.
     */
    public Integer findTotalConsumptionByPeriod(RtfBox rtfBox, Date startPeriod, Date endPeriod);

    /**
     * Gets total production till requested date.
     * It looks for the greatest value of production till date (the date not inclusive).
     * @param endPeriod the latest limit of date.
     * @return production of energy till date.
     */
    public Integer findTotalProductionBefore(RtfBox rtfBox, Date endPeriod);

    /**
     * Gets total consumption till requested date.
     * It looks for the greatest value of consumption till date (the date not inclusive).
     * @param endPeriod the latest limit of date.
     * @return consumption of energy till date.
     */
    public Integer findTotalConsumptionBefore(RtfBox rtfBox, Date endPeriod);

    /**
     * Gets first note from table.
     * @param rtfBox the box to find note for.
     * @return first note for this box.
     */
    public RtfBoxData findFirstNote(RtfBox rtfBox);

}
