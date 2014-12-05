package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import nl.rooftopenergy.bionic.transfer.GraphDataTransfer;
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

    /**
     * Gets total number of consumption whole working period.
     * @param id identifier number of the company that uses this panels;
     * @return total number of consuming energy.
     */
    @POST
    @Path("total_consumption")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalConsumption(@FormParam("id") String id,
                                        @FormParam("currentBox") String currentBox){
        Integer paramId = Integer.parseInt(id);
        Integer paramCurrentBox = Integer.parseInt(currentBox);

        RtfBox rtfBox = findBox(paramId, paramCurrentBox);

        Integer result = rtfBoxDataDao.findTotalConsumption(rtfBox);
        return result;
    }

    /**
     * Gets list notes that describe consumption during current day.
     * @param id identifier number of the company that uses this panels;
     * @return list notes describing consumption.
     */
    @POST
    @Path("consumption_period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showConsumption(@FormParam("id") String id,
                                                   @FormParam("currentBox") String currentBox,
                                                   @FormParam("dateStart") String dateStart,
                                                   @FormParam("dateEnd") String dateEnd){
        Integer paramId = Integer.parseInt(id);
        Integer paramCurrentBox = Integer.parseInt(currentBox);
        Date paramDateStart = Timestamp.valueOf(dateStart);
        Date paramDateEnd = Timestamp.valueOf(dateEnd);

        RtfBox rtfBox = findBox(paramId, paramCurrentBox);

        List<RtfBoxData> dataList = rtfBoxDataDao.findByPeriod(rtfBox, paramDateStart, paramDateEnd);
        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        GraphDataTransfer graphData;
        for (RtfBoxData data : dataList){
            Integer value = data.getConsumption();
            Date date = data.getDate();
            graphData = new GraphDataTransfer(date, value);
            resultList.add(graphData);
        }
        return resultList;
    }

    private RtfBox findBox(Integer companyId, Integer boxId ){

        Company company = companyDao.findById(companyId);
        List<RtfBox> rtfBoxList = rtfBoxDao.findByCompanyId(company);

        RtfBox rtfbox = null;
        for (RtfBox box : rtfBoxList){
            if (box.getRtfBoxId().equals(boxId)){
                rtfbox = box;
                break;
            }
        }
        return rtfbox;
    }

}
