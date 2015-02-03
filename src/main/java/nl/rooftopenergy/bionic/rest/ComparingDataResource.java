package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import nl.rooftopenergy.bionic.transfer.ComparingInfoTransfer;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Владислав on 15.01.2015.
 */
@RestController
@Path("comparing")
public class ComparingDataResource {
    private static final int TWENTY_FOUR_HOURS = 24;
    private static final int HOUR = 3600000;
    private static final int MONTHS = 12;
    //trees saved for 1 Kwh
    private static final double TREE_SAVED_FOR_ONE_KILOWATT = 0.002742;
    //CO2 offset in kilograms for 1 Kwh (for dutch region)
    private static final double CARBON_OFFSET_FOR_ONE_KILOWATT=0.46;

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    private RtfBoxDao rtfBoxDao;

    @Inject
    private CompanyDao companyDao;

    @Inject
    private PrincipalInformation principalInformation;

    /**
     * Gets total number of production whole working period.
     *
     * @param companyName name of the company calculation is made for
     * @return total number of producing energy.
     */
    @POST
    @Path("production/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalProduction(@FormParam("companyName") String companyName) {

        RtfBox rtfBox = companyDao.findByName(companyName).getRtfBox();

        Integer result = rtfBoxDataDao.findTotalProduction(rtfBox);
        return result;
    }

    /**
     * Gets list notes that describe production during current day.
     *
     * @param companyName name of the company calculation is made for.
     * @param dateStart   the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period is started.
     * @param dateEnd     the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period should be finished.
     * @return list notes describing production.
     */
    @POST
    @Path("production/period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showProduction(@FormParam("companyName") String companyName,
                                                  @FormParam("dateStart") String dateStart,
                                                  @FormParam("dateEnd") String dateEnd) {

        Date paramDateStart = new Timestamp(Long.parseLong(dateStart));
        Date paramDateEnd = new Timestamp(Long.parseLong(dateEnd));

        RtfBox rtfBox = companyDao.findByName(companyName).getRtfBox();

        List<RtfBoxData> dataList = rtfBoxDataDao.findByPeriod(rtfBox, paramDateStart, paramDateEnd);
        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        GraphDataTransfer graphData;
        for (int i = 1; i < dataList.size(); i++) {
            int firstProduction = dataList.get(i - 1).getProduction();
            int secondProduction = dataList.get(i).getProduction();
            Integer value = secondProduction - firstProduction;
            Date date = dataList.get(i).getDate();
            graphData = new GraphDataTransfer(date, value);
            resultList.add(graphData);
        }
        return resultList;
    }

    /**
     * Gets list notes that describe production during current day.
     * There are values of production per hour.
     *
     * @param companyName name of the company calculation is made for.
     * @param thoseDate   the date (expressed in milliseconds) points on day.
     * @return list notes describing production.
     */
    @POST
    @Path("production/daily")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> dailyProduction(@FormParam("companyName") String companyName,
                                                   @FormParam("date") String thoseDate) {
        long longDate = Long.parseLong(thoseDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        DateTime thisDate = new DateTime(longDate);
        String day = thisDate.getYear() + "-" + thisDate.getMonthOfYear() + "-" +
                thisDate.getDayOfMonth() + " ";
        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();

        for (int i = 0; i < TWENTY_FOUR_HOURS; i++) {
            Timestamp start = Timestamp.valueOf(day + i + ":00:00");
            Timestamp finish = Timestamp.valueOf(day + i + ":59:59");
            Integer value = rtfBoxDataDao.findTotalProductionByPeriod(box, start, finish);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finish, value));
        }

        Date dateBefore = new Date(resultList.get(0).getDate().getTime() - HOUR);
        resultList = transformToDifferences(box, resultList, dateBefore, "production");


        return resultList;
    }

    /**
     * Gets list notes that describe production during current month.
     * There are values of production per day.
     *
     * @param companyName name of the company calculation is made for.
     * @param thoseDate   the date (expressed in milliseconds) points on month.
     * @return list notes describing production.
     */
    @POST
    @Path("production/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> monthlyProduction(@FormParam("companyName") String companyName,
                                                     @FormParam("date") String thoseDate) {

        long longDate = Long.parseLong(thoseDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        DateTime thisDate = new DateTime(longDate);

        String month = thisDate.getYear() + "-" + thisDate.getMonthOfYear() + "-";
        int daysInMonth = getDaysInMonth(thisDate);

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        for (int i = 1; i <= daysInMonth; i++) {
            Timestamp startDay = Timestamp.valueOf(month + i + " 00:00:00");
            Timestamp finishDay = Timestamp.valueOf(month + i + " 23:59:59");
            Integer value = rtfBoxDataDao.findTotalProductionByPeriod(box, startDay, finishDay);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finishDay, value));
        }

        Date dateBefore = new Date(resultList.get(0).getDate().getTime() - TWENTY_FOUR_HOURS * HOUR);
        resultList = transformToDifferences(box, resultList, dateBefore, "production");

        return resultList;
    }

    /**
     * Gets list notes that describe production during current year.
     * There are values of production per month.
     *
     * @param companyName name of the company calculation is made for.
     * @param thoseDate   the date (expressed in milliseconds) points on year.
     * @return list notes describing production.
     */
    @POST
    @Path("production/yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> yearlyProduction(@FormParam("companyName") String companyName,
                                                    @FormParam("date") String thoseDate) {

        long longDate = Long.parseLong(thoseDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        DateTime thisDate = new DateTime(longDate);

        String year = thisDate.getYear() + "-";


        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        for (int i = 1; i <= MONTHS; i++) {

            Timestamp startMonth = Timestamp.valueOf(year + i + "-01 00:00:00");
            int lastDay = getDaysInMonth(new DateTime(startMonth));
            Timestamp finishMonth = Timestamp.valueOf(year + i + "-" + lastDay + " 23:59:59");
            Integer value = rtfBoxDataDao.findTotalProductionByPeriod(box, startMonth, finishMonth);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finishMonth, value));
        }

        Calendar thisYear = Calendar.getInstance();
        thisYear.setTime(resultList.get(0).getDate());
        thisYear.add(Calendar.YEAR, -1);
        DateTime d = new DateTime(thisYear);
        int y  = d.getYear();
        Date dateBefore = Timestamp.valueOf(y + "-12-31 23:59:59") ;
        resultList = transformToDifferences(box, resultList, dateBefore, "production");

        return resultList;
    }

    /**
     * Gets list notes that describe production for all working period.
     * There are values of production per month.
     *
     * @param companyName name of the company calculation is made for.
     * @param tillDate    the date (expressed in milliseconds) points on date.
     * @return list notes describing production.
     */
    @POST
    @Path("production/totally")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> totallyProduction(@FormParam("companyName") String companyName,
                                                     @FormParam("date") String tillDate) {

        long longDate = Long.parseLong(tillDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        RtfBoxData boxData = rtfBoxDataDao.findFirstNote(box);

        Calendar firstDate = Calendar.getInstance();
        firstDate.setTime(boxData.getDate());

        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(new Date(longDate));

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        boolean isAvailable = true;
        while (isAvailable) {
            DateTime date = new DateTime(firstDate);
            String year = date.getYear() + "-" + date.getMonthOfYear();
            Timestamp startMonth = Timestamp.valueOf(year + "-01 00:00:00");
            int lastDay = getDaysInMonth(new DateTime(startMonth));
            Timestamp finishMonth;
            if (firstDate.compareTo(lastDate) > 0) {
                finishMonth = new Timestamp(lastDate.getTimeInMillis());
                isAvailable = false;
            } else {
                finishMonth = Timestamp.valueOf(year + "-" + lastDay + " 23:59:59");
            }
            Integer value = rtfBoxDataDao.findTotalProductionByPeriod(box, startMonth, finishMonth);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finishMonth, value));
            firstDate.add(Calendar.MONTH, 1);
        }

        Calendar thisYear = Calendar.getInstance();
        thisYear.setTime(resultList.get(0).getDate());
        thisYear.add(Calendar.YEAR, -1);
        Date dateBefore = thisYear.getTime();
        resultList = transformToDifferences(box, resultList, dateBefore, "production");

        return resultList;
    }

    /**
     * Gets the value of energy has produced this day.
     *
     * @param companyName name of the company calculation is made for.
     * @return total daily production.
     */
    @POST
    @Path("production/thisDayTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisDayTotalProduction(@FormParam("companyName") String companyName) {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();
        int month = nowDate.getMonthOfYear();
        int day = nowDate.getDayOfMonth();
        Timestamp begin = Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00");
        Integer value = getThisProduction(companyName, begin);

        return value;
    }

    /**
     * Gets the value of energy has produced this month.
     *
     * @param companyName name of the company calculation is made for.
     * @return total monthly production.
     */
    @POST
    @Path("production/thisMonthTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisMonthTotalProduction(@FormParam("companyName") String companyName) {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();
        int month = nowDate.getMonthOfYear();

        Timestamp begin = Timestamp.valueOf(year + "-" + month + "-" + "01" + " 00:00:00");

        Integer value = getThisProduction(companyName, begin);
        return value;
    }

    /**
     * Gets the value of energy has produced this year.
     *
     * @param companyName name of the company calculation is made for.
     * @return total yearly production.
     */
    @POST
    @Path("production/thisYearTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisYearTotalProduction(@FormParam("companyName") String companyName) {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();

        Timestamp begin = Timestamp.valueOf(year + "-" + "01" + "-" + "01" + " 00:00:00");

        Integer value = getThisProduction(companyName, begin);
        return value;
    }

    /**
     * Gets total number of consumption whole working period.
     *
     * @return total number of consuming energy.
     */
    @POST
    @Path("consumption/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalConsumption(@FormParam("companyName") String companyName) {

        RtfBox rtfBox = companyDao.findByName(companyName).getRtfBox();

        Integer result = rtfBoxDataDao.findTotalConsumption(rtfBox);
        return result;
    }

    /**
     * Gets list notes that describe consumption during current day.
     *
     * @param dateStart the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period is started.
     * @param dateEnd   the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period should be finished.
     * @return list notes describing consumption.
     * <p/>
     * method is deprecated: Use dailyConsumption(), monthlyConsumption(), yearlyConsumption
     * and totallyConsumption() instead.
     */
    @Deprecated
    @POST
    @Path("consumption/period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showConsumption(@FormParam("companyName") String companyName,
                                                   @FormParam("dateStart") String dateStart,
                                                   @FormParam("dateEnd") String dateEnd) {

        Date paramDateStart = new Timestamp(Long.parseLong(dateStart));
        Date paramDateEnd = new Timestamp(Long.parseLong(dateEnd));

        RtfBox rtfBox = companyDao.findByName(companyName).getRtfBox();

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
    @Path("consumption/daily")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> dailyConsumption(@FormParam("companyName") String companyName,
                                                    @FormParam("date") String thoseDate) {
        long longDate = Long.parseLong(thoseDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
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
        resultList = transformToDifferences(box, resultList, dateBefore, "consumption");


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
    @Path("consumption/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> monthlyConsumption(@FormParam("companyName") String companyName,
                                                      @FormParam("date") String thoseDate) {

        long longDate = Long.parseLong(thoseDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        DateTime thisDate = new DateTime(longDate);

        String month = thisDate.getYear() + "-" + thisDate.getMonthOfYear() + "-";
        int daysInMonth = getDaysInMonth(thisDate);

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        for (int i = 1; i <= daysInMonth; i++) {
            Timestamp startDay = Timestamp.valueOf(month + i + " 00:00:00");
            Timestamp finishDay = Timestamp.valueOf(month + i + " 23:59:59");
            Integer value = rtfBoxDataDao.findTotalConsumptionByPeriod(box, startDay, finishDay);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finishDay, value));
        }

        Date dateBefore = new Date(resultList.get(0).getDate().getTime() - TWENTY_FOUR_HOURS * HOUR);
        resultList = transformToDifferences(box, resultList, dateBefore, "consumption");

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
    @Path("consumption/yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> yearlyConsumption(@FormParam("companyName") String companyName,
                                                     @FormParam("date") String thoseDate) {

        long longDate = Long.parseLong(thoseDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        DateTime thisDate = new DateTime(longDate);

        String year = thisDate.getYear() + "-";


        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        for (int i = 1; i <= MONTHS; i++) {

            Timestamp startMonth = Timestamp.valueOf(year + i + "-01 00:00:00");
            int lastDay = getDaysInMonth(new DateTime(startMonth));
            Timestamp finishMonth = Timestamp.valueOf(year + i + "-" + lastDay + " 23:59:59");
            Integer value = rtfBoxDataDao.findTotalConsumptionByPeriod(box, startMonth, finishMonth);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finishMonth, value));
        }

        Calendar thisYear = Calendar.getInstance();
        thisYear.setTime(resultList.get(0).getDate());
        thisYear.add(Calendar.YEAR, -1);
        DateTime d = new DateTime(thisYear);
        int y  = d.getYear();
        Date dateBefore = Timestamp.valueOf(y + "-12-31 23:59:59") ;
        resultList = transformToDifferences(box, resultList, dateBefore, "consumption");

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
    @Path("consumption/totally")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> totallyConsumption(@FormParam("companyName") String companyName,
                                                      @FormParam("date") String tillDate) {

        long longDate = Long.parseLong(tillDate);
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        RtfBoxData boxData = rtfBoxDataDao.findFirstNote(box);

        Calendar firstDate = Calendar.getInstance();
        firstDate.setTime(boxData.getDate());

        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(new Date(longDate));

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        boolean isAvailable = true;
        while (isAvailable) {
            DateTime date = new DateTime(firstDate);
            String year = date.getYear() + "-" + date.getMonthOfYear();
            Timestamp startMonth = Timestamp.valueOf(year + "-01 00:00:00");
            int lastDay = getDaysInMonth(new DateTime(startMonth));
            Timestamp finishMonth;
            if (firstDate.compareTo(lastDate) > 0) {
                finishMonth = new Timestamp(lastDate.getTimeInMillis());
                isAvailable = false;
            } else {
                finishMonth = Timestamp.valueOf(year + "-" + lastDay + " 23:59:59");
            }
            Integer value = rtfBoxDataDao.findTotalConsumptionByPeriod(box, startMonth, finishMonth);
            if (value == null) {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(finishMonth, value));
            firstDate.add(Calendar.MONTH, 1);
        }

        Calendar thisYear = Calendar.getInstance();
        thisYear.setTime(resultList.get(0).getDate());
        thisYear.add(Calendar.YEAR, -1);
        Date dateBefore = thisYear.getTime();
        resultList = transformToDifferences(box, resultList, dateBefore, "consumption");

        return resultList;
    }

    /**
     * Gets the value of energy has been consumed this day.
     *
     * @return total daily consumption.
     */
    @POST
    @Path("consumption/thisDayTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisDayTotalConsumption(@FormParam("companyName") String companyName) {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();
        int month = nowDate.getMonthOfYear();
        int day = nowDate.getDayOfMonth();
        Timestamp begin = Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00");
        Integer value = getThisConsumption(companyName, begin);

        return value;
    }

    /**
     * Gets the value of energy has been consumed this month.
     *
     * @return total monthly consumption.
     */
    @POST
    @Path("consumption/thisMonthTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisMonthTotalConsumption(@FormParam("companyName") String companyName) {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();
        int month = nowDate.getMonthOfYear();

        Timestamp begin = Timestamp.valueOf(year + "-" + month + "-" + "01" + " 00:00:00");

        Integer value = getThisConsumption(companyName, begin);
        return value;
    }

    /**
     * Gets the value of energy has been consumed this year.
     *
     * @return total yearly consumption.
     */
    @POST
    @Path("consumption/thisYearTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer thisYearTotalConsumption(@FormParam("companyName") String companyName) {
        DateTime nowDate = new DateTime(System.currentTimeMillis());
        int year = nowDate.getYear();

        Timestamp begin = Timestamp.valueOf(year + "-" + "01" + "-" + "01" + " 00:00:00");

        Integer value = getThisConsumption(companyName, begin);
        return value;
    }

    @POST
    @Path("comparingInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComparingInfoTransfer> getComparingInfo(@FormParam("comparingCompany") String comparingCompanyName) {
        List<ComparingInfoTransfer> resultList = new ArrayList<ComparingInfoTransfer>();
        //for user company
        RtfBox rtfBox = principalInformation.getCompany().getRtfBox();
        Integer totalProduction = rtfBoxDataDao.findTotalProduction(rtfBox);
        Integer totalConsumption = rtfBoxDataDao.findTotalConsumption(rtfBox);
        Double treesSaved=(totalProduction.doubleValue()/1000.0)*TREE_SAVED_FOR_ONE_KILOWATT;
        Double carbonOffset=(totalProduction.doubleValue()/1000)*CARBON_OFFSET_FOR_ONE_KILOWATT;
        resultList.add(new ComparingInfoTransfer(totalProduction,totalConsumption,treesSaved, carbonOffset, rtfBox.getSolarPanels(), rtfBox.getPanelType()));
        //for comparing company
        rtfBox = companyDao.findByName(comparingCompanyName).getRtfBox();
        totalProduction = rtfBoxDataDao.findTotalProduction(rtfBox);
        totalConsumption = rtfBoxDataDao.findTotalConsumption(rtfBox);
        treesSaved=(totalProduction.doubleValue()/1000.0)*TREE_SAVED_FOR_ONE_KILOWATT;
        carbonOffset=(totalProduction.doubleValue()/1000)*CARBON_OFFSET_FOR_ONE_KILOWATT;
        resultList.add(new ComparingInfoTransfer(totalProduction,totalConsumption,treesSaved, carbonOffset, rtfBox.getSolarPanels(), rtfBox.getPanelType()));
        return resultList;
    }


    private List<GraphDataTransfer> transformToDifferences(RtfBox box, List<GraphDataTransfer> list, Date dateBefore, String type) {

        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        Integer previousValue = 0;

        if (type.equals("production")) {
            previousValue = rtfBoxDataDao.findTotalProductionBefore(box, dateBefore);
        }
        if (type.equals("consumption")) {
            previousValue = rtfBoxDataDao.findTotalConsumptionBefore(box, dateBefore);
        }
        if (previousValue == null) {
            previousValue = 0;
        }

        int value;
        int now = list.get(0).getValue();
        int before = previousValue;
        if (now != 0) {
            value = now - before;
        } else value = 0;
        resultList.add(new GraphDataTransfer(list.get(0).getDate(), value));

        for (int i = 1; i < list.size(); i++) {
            now = list.get(i).getValue();
            before = list.get(i - 1).getValue();
            if (before == 0) {
                before = previousValue;
            } else {
                previousValue = before;
            }
            if (now != 0) {
                value = now - before;
            } else {
                value = 0;
            }
            resultList.add(new GraphDataTransfer(list.get(i).getDate(), value));
        }
        return resultList;
    }

    private int getDaysInMonth(DateTime thisDate) {

        String month = thisDate.getYear() + "-" + thisDate.getMonthOfYear() + "-";

        String start = month + "01 00:01:01";
        DateTime startDate = new DateTime(Timestamp.valueOf(start));
        DateTime finishDate = startDate;
        int days = 1;
        while (true) {
            finishDate = finishDate.plusDays(1);
            if (finishDate.getDayOfMonth() != 1) {
                days++;
            } else break;
        }
        return days;
    }

    private Integer getThisProduction(String companyName, Timestamp begin) {
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        Integer valueBefore = rtfBoxDataDao.findTotalProductionBefore(box, begin);
        Integer actualValue = rtfBoxDataDao.findTotalProduction(box);
        return actualValue - valueBefore;
    }

    private Integer getThisConsumption(String companyName, Timestamp begin) {
        RtfBox box = companyDao.findByName(companyName).getRtfBox();
        Integer valueBefore = rtfBoxDataDao.findTotalConsumptionBefore(box, begin);
        Integer actualValue = rtfBoxDataDao.findTotalConsumption(box);
        return actualValue - valueBefore;
    }

}

