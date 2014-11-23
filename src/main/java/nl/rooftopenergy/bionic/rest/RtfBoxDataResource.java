package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import org.apache.log4j.Logger;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/21/14.
 */
@RestController
@Path("boxData")
public class RtfBoxDataResource {
    private final Logger log = Logger.getLogger(RtfBoxDataResource.class.getName());

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    private RtfBoxDao rtfBoxDao;

    /**
     * Returns value of energy has been received from solar panel for month.
     * @param id Id of a company which uses this RTFBox.
     * @param date it is the month to look for data.
     * @return value of energy for month
     */
    @POST
    @Path("/monthTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalMonthNumber(@FormParam("id") String id, @FormParam("date") String date){

        Integer param = Integer.parseInt(id);
        Timestamp paramCurrentMonth = Timestamp.valueOf(date);
        RtfBox box = rtfBoxDao.findByRtfBoxId(param);

        List<RtfBoxData> listData = rtfBoxDataDao.findByRtfBoxId(box);
        Integer totalNumber = 0;
        for (RtfBoxData data : listData){
            int result = DateTimeComparator.getInstance(DateTimeFieldType.monthOfYear()).compare(data.getCreated(), paramCurrentMonth);
            if (result == 0){
                totalNumber += data.getReading1();
            }

        }

        return totalNumber;
    }

    /**
     * Returns list of measures (list of RTFBoxData instances)  has been done by RTFBox for day.
     * @param id Id of a company which uses this RTFBox.
     * @param date it is the month to look for day.
     * @return list of measures for day
     */
    @POST
    @Path("/day")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RtfBoxData> showDailyMeasures(@FormParam("id") String id, @FormParam("date") String date){
        Integer paramId = Integer.parseInt(id);
        Timestamp paramDate =  Timestamp.valueOf(date);
        RtfBox box = rtfBoxDao.findByRtfBoxId(paramId);

        List<RtfBoxData> listAllData = rtfBoxDataDao.findByRtfBoxId(box);
        List<RtfBoxData> listDailyData = new ArrayList<RtfBoxData>();
        for (RtfBoxData data : listAllData){
            int result = DateTimeComparator.getDateOnlyInstance().compare(data.getCreated(), paramDate);
            if (result == 0){
                listDailyData.add(data);
            }
        }
        return listDailyData;
    }

    /**
     * Returns list of measures (list of RTFBoxData instances)  has been done by RTFBox for month.
     * @param id Id of a company which uses this RTFBox.
     * @param date it is the month to look for month.
     * @return list of measures for month
     */
    @POST
    @Path("/month")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RtfBoxData> showMonthlyMeasures(@FormParam("id") String id, @FormParam("date") String date){
        Integer paramId = Integer.parseInt(id);
        Timestamp paramDate =  Timestamp.valueOf(date);
        RtfBox box = rtfBoxDao.findByRtfBoxId(paramId);

        List<RtfBoxData> listAllData = rtfBoxDataDao.findByRtfBoxId(box);
        List<RtfBoxData> listDailyData = new ArrayList<RtfBoxData>();
        for (RtfBoxData data : listAllData){
            int result = DateTimeComparator.getInstance(DateTimeFieldType.monthOfYear()).compare(data.getCreated(), paramDate);
            if (result == 0){
                listDailyData.add(data);
            }
        }
        return listDailyData;
    }

    /**
     * Returns list of measures (list of RTFBoxData instances)  has been done by RTFBox for year.
     * @param id Id of a company which uses this RTFBox.
     * @param date it is the month to look for year.
     * @return list of measures for year
     */
    @POST
    @Path("/year")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RtfBoxData> showYearlyMeasures(@FormParam("id") String id, @FormParam("date") String date){
        Integer paramId = Integer.parseInt(id);
        Timestamp paramDate =  Timestamp.valueOf(date);
        RtfBox box = rtfBoxDao.findByRtfBoxId(paramId);

        List<RtfBoxData> listAllData = rtfBoxDataDao.findByRtfBoxId(box);
        List<RtfBoxData> listDailyData = new ArrayList<RtfBoxData>();
        for (RtfBoxData data : listAllData){
            int result = DateTimeComparator.getInstance(DateTimeFieldType.year()).compare(data.getCreated(), paramDate);
            if (result == 0){
                listDailyData.add(data);
            }
        }
        return listDailyData;
    }

}
