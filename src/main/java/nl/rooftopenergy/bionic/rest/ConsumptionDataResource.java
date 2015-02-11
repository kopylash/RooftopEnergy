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
import java.util.*;

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
    private static final int TWENTY_FOUR_HOURS = 24;
    private static final int HOUR = 3600000;
    private static final int MONTHS = 12;

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
     * @return total number of consuming energy.
     */
    @POST
    @Path("total_consumption")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalConsumption(){
        Integer paramId = principalInformation.getCompany().getCompanyId();

        RtfBox rtfBox = companyDao.find(paramId).getRtfBox();

        Integer result = rtfBoxDataDao.findTotalConsumption(rtfBox);
        return result;
    }

    /**
     * Gets list notes that describe consumption during current day.
     * @param dateStart the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period is started.
     * @param dateEnd the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period should be finished.
     * @return list notes describing consumption.
     *
     * method is deprecated: Use dailyConsumption(), monthlyConsumption(), yearlyConsumption
     * and totallyConsumption() instead.
     */
    @Deprecated
    @POST
    @Path("consumption_period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showConsumption(@FormParam("dateStart") String dateStart,
                                                   @FormParam("dateEnd") String dateEnd) {
        Integer paramId = principalInformation.getCompany().getCompanyId();
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

    /**
     * Gets list notes that describe consumption during current day.
     * There are values of consumption per hour.
     *
     * @param thoseDate the date (expressed in milliseconds) points on day.
     * @return list notes describing consumption.
     */
    @POST
    @Path("daily")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> dailyConsumption(@FormParam("date") String thoseDate) {
        long longDate = Long.parseLong(thoseDate);
        RtfBox box = principalInformation.getCompany().getRtfBox();
        DateTime thisDate = new DateTime(longDate);
        String day = thisDate.getYear() + "-" + thisDate.getMonthOfYear() + "-" +
                thisDate.getDayOfMonth() + " ";
        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();

        for (int i = 0; i < TWENTY_FOUR_HOURS; i++) {
            Timestamp start = Timestamp.valueOf(day + i + ":00:00");
            Timestamp finish = Timestamp.valueOf(day + i + ":59:59");
            Integer value = rtfBoxDataDao.findTotalConsumptionByPeriod(box, start, finish);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finish, value));
        }

        Date dateBefore = new Date(resultList.get(0).getDate().getTime() - HOUR);
        resultList = transformToDifferences(box, resultList, dateBefore);


        return resultList;
    }

    /**
     * Gets list notes that describe consumption during current month.
     * There are values of consumption per day.
     *
     * @param thoseDate the date (expressed in milliseconds) points on month.
     * @return list notes describing consumption.
     */
    @POST
    @Path("monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> monthlyConsumption(@FormParam("date")String thoseDate) {

        long longDate = Long.parseLong(thoseDate);
        RtfBox box = principalInformation.getCompany().getRtfBox();
        DateTime thisDate = new DateTime(longDate);

        String month = thisDate.getYear() + "-" + thisDate.getMonthOfYear() + "-";
        int daysInMonth = getDaysInMonth(thisDate);

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        for (int i = 1; i <= daysInMonth; i++){
            Timestamp startDay = Timestamp.valueOf(month + i + " 00:00:00");
            Timestamp finishDay = Timestamp.valueOf(month + i + " 23:59:59");
            Integer value = rtfBoxDataDao.findTotalConsumptionByPeriod(box, startDay, finishDay);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(startDay, value));
        }

//        Date dateBefore = new Date(resultList.get(0).getDate().getTime() - TWENTY_FOUR_HOURS*HOUR);
        Date dateBefore = new Date(resultList.get(0).getDate().getTime() - 1000*60);
        resultList = transformToDifferences(box, resultList, dateBefore);

        return resultList;
    }

    /**
     * Gets list notes that describe consumption during current year.
     * There are values of consumption per month.
     *
     * @param thoseDate the date (expressed in milliseconds) points on year.
     * @return list notes describing consumption.
     */
    @POST
    @Path("yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> yearlyConsumption(@FormParam("date") String thoseDate) {



        long longDate = Long.parseLong(thoseDate);
        RtfBox box = principalInformation.getCompany().getRtfBox();
        DateTime thisDate = new DateTime(longDate);

        String year = thisDate.getYear() + "-";


        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        for (int i = 1; i <= MONTHS; i++){

            Timestamp startMonth = Timestamp.valueOf(year + i + "-01 00:00:00");
            int lastDay = getDaysInMonth(new DateTime(startMonth));
            Timestamp finishMonth = Timestamp.valueOf(year + i + "-" + lastDay + " 23:59:59");
            Integer value = rtfBoxDataDao.findTotalConsumptionByPeriod(box, startMonth, finishMonth);
            if (value == null) {
                value = 0;
            }
            DateTime graphDate = new DateTime(finishMonth);
            String graphDateString = graphDate.getYear() + "-" + graphDate.getMonthOfYear() + "-01 00:00:00";
            resultList.add(new GraphDataTransfer(Timestamp.valueOf(graphDateString), value));
        }

        Calendar thisYear = Calendar.getInstance();
        thisYear.setTime(resultList.get(0).getDate());
        thisYear.add(Calendar.YEAR, -1);
        DateTime d = new DateTime(thisYear);
        int y  = d.getYear();
        Date dateBefore = Timestamp.valueOf(y + "-12-31 23:59:59") ;
        resultList = transformToDifferences(box, resultList, dateBefore);

        return resultList;
    }

    /**
     * Gets list notes that describe consumption for all working period.
     * There are values of production per month.
     *
     * @param tillDate the date (expressed in milliseconds) points on date.
     * @return list notes describing consumption.
     */
    @POST
    @Path("totally")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> totallyConsumption(@FormParam("date") String tillDate) {

        long longDate = Long.parseLong(tillDate);
        RtfBox box = principalInformation.getCompany().getRtfBox();
        RtfBoxData boxData = rtfBoxDataDao.findFirstNote(box);

        Date dbNoteDate = new Date(boxData.getDate().getTime());
        Date endPeriodDate = new Date(longDate);

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        Calendar calendar = Calendar.getInstance();

        boolean isAvailable = true;
        while (isAvailable) {

            DateTime date = new DateTime(dbNoteDate);
            String yearAndMonth = date.getYear() + "-" + date.getMonthOfYear();
            Timestamp startMonth = Timestamp.valueOf(yearAndMonth + "-01 00:00:00");

            int lastDay = getDaysInMonth(new DateTime(startMonth));
            Timestamp finishMonth;

            if (dbNoteDate.before(endPeriodDate)) {
                finishMonth = Timestamp.valueOf(yearAndMonth + "-" + lastDay + " 23:59:59");
            } else {
                DateTime end = new DateTime(endPeriodDate);
                String endOfMonth = end.getYear() + "-" + end.getMonthOfYear() + "-" +
                        getDaysInMonth(end) + " 23:59:59";
                finishMonth = Timestamp.valueOf(endOfMonth);
                isAvailable = false;
            }
            Integer value = rtfBoxDataDao.findTotalConsumptionByPeriod(box, startMonth, finishMonth);
            if (value == null) {
                value = 0;
            }
            DateTime graphDate = new DateTime(finishMonth);
            String graphDateString = graphDate.getYear() + "-" + graphDate.getMonthOfYear() + "-01 00:00:00";
            resultList.add(new GraphDataTransfer(Timestamp.valueOf(graphDateString), value));
            calendar.setTime(dbNoteDate);
            calendar.add(Calendar.MONTH, 1);
            dbNoteDate = calendar.getTime();
        }

        Calendar thisYear = Calendar.getInstance();
        thisYear.setTime(resultList.get(0).getDate());
        thisYear.add(Calendar.YEAR, -1);
        Date dateBefore = thisYear.getTime();
        resultList = transformToDifferences(box, resultList, dateBefore);

        Date lastNoteDate = resultList.get(resultList.size()-1).getDate();
        Calendar noteDate = Calendar.getInstance();
        noteDate.setTime(lastNoteDate);
        while (resultList.size() < MONTHS){
            noteDate.add(Calendar.MONTH, 1);
            resultList.add(new GraphDataTransfer(new Timestamp(noteDate.getTimeInMillis()), null));
        }
        return resultList;
    }

    /**
     * Gets the value of energy has been consumed this day.
     *
     * @return total daily consumption.
     */
    @POST
    @Path("thisDayTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisDayTotalConsumption() {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();
        int month = nowDate.getMonthOfYear();
        int day = nowDate.getDayOfMonth();
        Timestamp begin = Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00");
        Integer value = getThisConsumption(begin);

        return value;
    }

    /**
     * Gets the value of energy has been consumed this month.
     *
     * @return total monthly consumption.
     */
    @POST
    @Path("thisMonthTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisMonthTotalConsumption() {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();
        int month = nowDate.getMonthOfYear();

        Timestamp begin = Timestamp.valueOf(year + "-" + month + "-" + "01" + " 00:00:00");

        Integer value = getThisConsumption(begin);
        return value;
    }

    /**
     * Gets the value of energy has been consumed this year.
     *
     * @return total yearly consumption.
     */
    @POST
    @Path("thisYearTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisYearTotalConsumption() {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();

        Timestamp begin = Timestamp.valueOf(year + "-" + "01" + "-" + "01" + " 00:00:00");

        Integer value = getThisConsumption(begin);
        return value;
    }


    private List<GraphDataTransfer> transformToDifferences(RtfBox box, List<GraphDataTransfer> list, Date dateBefore) {

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();

        Integer previousValue = rtfBoxDataDao.findTotalConsumptionBefore(box, dateBefore);
        if (previousValue == null){
            previousValue = 0;
        }

        int value;
        int now = list.get(0).getValue();
        int before = previousValue;
        if (now != 0) {
            value = now - before;
        } else  value = 0;
        resultList.add(new GraphDataTransfer(list.get(0).getDate(), value));

        for (int i = 1; i < list.size(); i++) {
            now = list.get(i).getValue();
            before = list.get(i - 1).getValue();
            if (before == 0) {
                before = previousValue;
            } else {
                previousValue = before;
            }
            if (now != 0){
                value = now - before;
            } else {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(list.get(i).getDate(), value));
        }
        return resultList;
    }

    private int getDaysInMonth(DateTime thisDate){

        String month = thisDate.getYear() + "-" + thisDate.getMonthOfYear() + "-";

        String start = month + "01 00:01:01";
        DateTime startDate = new DateTime(Timestamp.valueOf(start));
        DateTime finishDate = startDate;
        int days = 1;
        while (true){
            finishDate = finishDate.plusDays(1);
            if (finishDate.getDayOfMonth() != 1){
                days++;
            } else break;
        }
        return days;
    }

    private Integer getThisConsumption(Timestamp begin){
        RtfBox box = principalInformation.getCompany().getRtfBox();
        Integer valueBefore = rtfBoxDataDao.findTotalConsumptionBefore(box, begin);
        Integer actualValue = rtfBoxDataDao.findTotalConsumption(box);
        return actualValue - valueBefore;
    }

}
