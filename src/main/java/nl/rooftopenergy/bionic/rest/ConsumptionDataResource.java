package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import nl.rooftopenergy.bionic.transfer.GraphDataTransfer;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Provides REST API to get information about consumption of energy produced by
 * solar panels. The company-owner of panels should have identifier number <code>id</code>.
 * It is possible to get information for given time interval. Also it is possible to get
 * information about consumption during whole solar panel's working period.
 *
 *
 * Created by Alexander Iakovenko.
 * 11/27/14.
 */
@RestController
@Path("consumption")
public class ConsumptionDataResource {

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    private RtfBoxDao rtfBoxDao;

    @Inject
    private CompanyDao companyDao;

    @Inject
    private PrincipalInformation principalInformation;

    /**
     * Gets total number of consumption whole working period.
     * @param currentBox identifier number of RTF Box
     * @return total number of consuming energy.
     */
    @POST
    @Path("total_consumption")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalConsumption(@FormParam("currentBox") String currentBox){
        Integer paramId = principalInformation.getCompany().getCompanyId();
        // why we need currentBox param???
        Integer paramCurrentBox = Integer.parseInt(currentBox);

        RtfBox rtfBox = companyDao.find(paramId).getRtfBox();

        Integer result = rtfBoxDataDao.findTotalConsumption(rtfBox);
        return result;
    }

    /**
     * Gets list notes that describe consumption during current day.
     * @param currentBox identifier number of RTF Box
     * @param dateStart the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period is started.
     * @param dateEnd the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period should be finished.
     * @return list notes describing consumption.
     */
    @POST
    @Path("consumption_period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showConsumption(@FormParam("currentBox") String currentBox,
                                                   @FormParam("dateStart") String dateStart,
                                                   @FormParam("dateEnd") String dateEnd) {
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Integer paramCurrentBox = Integer.parseInt(currentBox);
        Date paramDateStart = new Timestamp(Long.parseLong(dateStart));
        Date paramDateEnd = new Timestamp(Long.parseLong(dateEnd));

        RtfBox rtfBox = companyDao.find(paramId).getRtfBox();

        List<RtfBoxData> dataList = rtfBoxDataDao.findByPeriod(rtfBox, paramDateStart, paramDateEnd);
        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        GraphDataTransfer graphData;
        for (int i = 1; i < dataList.size(); i++) {
            int firstConsumption = dataList.get(i - 1).getConsumption();
            int secondConsumption = dataList.get(i).getConsumption();
            Integer value = secondConsumption - firstConsumption;
            Date date = dataList.get(i).getDate();
            graphData = new GraphDataTransfer(date, value);
            resultList.add(graphData);
        }
        return resultList;
    }

    @POST
    @Path("daily")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> dailyConsumption(@FormParam("date")String thisDay) {

        //MOCK Implementation!!!
        long thoseDay = Long.parseLong(thisDay);
        Random rnd = new Random();
        DateTime date = new DateTime(thoseDay);
        String dateStr = date.getYear() + "-" + date.getMonthOfYear() + "-" + date.getDayOfMonth() + " 00:59:59";
        DateTime day = new DateTime(Timestamp.valueOf(dateStr));
        List<GraphDataTransfer> list = new ArrayList<GraphDataTransfer>();
        for (int i = 0; i < 23; i++){
            Integer value = 0;
            int hour = day.getHourOfDay();
            if (hour < 18 &&  hour > 8) {
                value = rnd.nextInt(10) + 5;
            }
            GraphDataTransfer graphDataTransfer = new GraphDataTransfer(day.toDate(), value);
            day = day.plusHours(1);
            list.add(graphDataTransfer);
        }
        return list;
    }

    @POST
    @Path("monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> monthlyConsumption(@FormParam("date") String thisMonth) {

        //MOCK Implementation!!!
        long thoseMonth = Long.parseLong(thisMonth);
        Random rnd = new Random();
        DateTime date = new DateTime(thoseMonth);
        String dateStr = date.getYear() + "-" + date.getMonthOfYear() + "-" + 1 + " 00:59:59";
        DateTime day = new DateTime(Timestamp.valueOf(dateStr));
        List<GraphDataTransfer> list = new ArrayList<GraphDataTransfer>();
        do{
            Integer value = rnd.nextInt(200) + 50;
            GraphDataTransfer graphDataTransfer = new GraphDataTransfer(day.toDate(), value);
            day = day.plusDays(1);
            list.add(graphDataTransfer);
        } while (day.getDayOfMonth() != 1);
        return list;
    }

    @POST
    @Path("yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> yearlyConsumption(@FormParam("date")String thisYear) {

        //MOCK Implementation!!!
        long thoseYear = Long.parseLong(thisYear);
        Random rnd = new Random(23);
        DateTime date = new DateTime(thoseYear);
        String dateStr = date.getYear() + "-" + 1 + "-" + 1 + " 00:59:59";
        DateTime day = new DateTime(Timestamp.valueOf(dateStr));
        List<GraphDataTransfer> list = new ArrayList<GraphDataTransfer>();
        do{
            Integer value = rnd.nextInt(500) + 200;
            GraphDataTransfer graphDataTransfer = new GraphDataTransfer(day.toDate(), value);
            day = day.plusMonths(1);
            list.add(graphDataTransfer);
        } while (day.getMonthOfYear() != 1);
        return list;
    }

}
