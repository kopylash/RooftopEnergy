package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import nl.rooftopenergy.bionic.transfer.GraphDataTransfer;
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
    private final GraphDataTransfer graphData = new GraphDataTransfer();

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    private RtfBoxDao rtfBoxDao;

    @Inject
    private PrincipalInformation principalInformation;

    /**
     * Returns value of energy has been received from solar panel for month.
     * @param date it is the month to look for data.
     * @return value of energy for month
     */
    @POST
    @Path("/monthTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalMonthNumber(@FormParam("date") String date){
        Integer param = principalInformation.getCompany().getCompanyId();
        Timestamp paramCurrentMonth = Timestamp.valueOf(date);

        RtfBox box = rtfBoxDao.findByRtfBoxId(param);

        List<RtfBoxData> listData = rtfBoxDataDao.findByRtfBoxId(box);
        Integer totalNumber = 0;

        for (RtfBoxData data : listData){
            int result = DateTimeComparator.getInstance(DateTimeFieldType.monthOfYear()).compare(data.getDate(), paramCurrentMonth);
            if (result == 0){
                totalNumber += data.getProduction();
            }
        }
        return totalNumber;
    }

    /**
     * Returns list of measures (list of GraphDataTransfer instances)  has been done by RTFBox for day.
     * @param date it is the month to look for day.
     * @return list of measures for day
     */
    @POST
    @Path("/day")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showDailyMeasures(@FormParam("date") String date){
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Timestamp paramDate =  Timestamp.valueOf(date);

        RtfBox box = rtfBoxDao.findByRtfBoxId(paramId);

        List<RtfBoxData> listAllData = rtfBoxDataDao.findByRtfBoxId(box);
        List<GraphDataTransfer> listDailyData = new ArrayList<GraphDataTransfer>();

        for (RtfBoxData data : listAllData){
            int result = DateTimeComparator.getDateOnlyInstance().compare(data.getDate(), paramDate);
            if (result == 0){
                graphData.setDate(data.getDate());
                graphData.setValue(data.getProduction());
                listDailyData.add(graphData);
            }
        }
        return listDailyData;
    }

    /**
     * Returns list of measures (list of GraphDataTransfer instances)  has been done by RTFBox for month.
     * @param date it is the month to look for month.
     * @return list of measures for month
     */
    @POST
    @Path("/month")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showMonthlyMeasures(@FormParam("date") String date){
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Timestamp paramDate =  Timestamp.valueOf(date);

        RtfBox box = rtfBoxDao.findByRtfBoxId(paramId);

        List<RtfBoxData> listAllData = rtfBoxDataDao.findByRtfBoxId(box);
        List<GraphDataTransfer> listDailyData = new ArrayList<GraphDataTransfer>();

        for (RtfBoxData data : listAllData){
            int result = DateTimeComparator.getInstance(DateTimeFieldType.monthOfYear()).compare(data.getDate(), paramDate);
            if (result == 0){
                graphData.setDate(data.getDate());
                graphData.setValue(data.getProduction());
                listDailyData.add(graphData);
            }
        }
        return listDailyData;
    }

    /**
     * Returns list of measures (list of GraphDataTransfer instances)  has been done by RTFBox for year.
     * @param date it is the month to look for year.
     * @return list of measures for year
     */
    @POST
    @Path("/year")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showYearlyMeasures(@FormParam("date") String date){
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Timestamp paramDate =  Timestamp.valueOf(date);

        RtfBox box = rtfBoxDao.findByRtfBoxId(paramId);

        List<RtfBoxData> listAllData = rtfBoxDataDao.findByRtfBoxId(box);
        List<GraphDataTransfer> listDailyData = new ArrayList<GraphDataTransfer>();

        for (RtfBoxData data : listAllData){
            int result = DateTimeComparator.getInstance(DateTimeFieldType.year()).compare(data.getDate(), paramDate);
            if (result == 0){
                graphData.setDate(data.getDate());
                graphData.setValue(data.getProduction());
                listDailyData.add(graphData);
            }
        }
        return listDailyData;
    }
}
