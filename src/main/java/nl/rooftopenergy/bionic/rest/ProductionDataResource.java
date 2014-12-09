package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
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
 * Provides REST API to get information about production of energy by
 * solar panels. The company-owner of panels should have identifier number <code>id</code>.
 * It is possible to get information for given time interval. Also it is possible to get
 * information about consumption during whole solar panel's working period.
 *
 *
 * Created by Alexander Iakovenko.
 * 11/27/14.
 */
@RestController
@Path("production")
public class ProductionDataResource {


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
     * @param currentBox identifier number of RTF Box
     * @return total number of producing energy.
     */
    @POST
    @Path("total_production")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer showTotalProduction(@FormParam("currentBox") String currentBox){
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Integer paramCurrentBox = Integer.parseInt(currentBox);

        RtfBox rtfBox = findBox(paramId, paramCurrentBox);

        Integer result = rtfBoxDataDao.findTotalProduction(rtfBox);
        return result;
    }

    /**
     * Gets list notes that describe production during current day.
     * @param currentBox identifier number of RTF Box
     * @param dateStart the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period is started.
     * @param dateEnd the date like {@value 'yyyy-mm-dd hh:MM:ss'} when period should be finished.
     * @return list notes describing production.
     */
    @POST
    @Path("production_period")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphDataTransfer> showProduction(@FormParam("currentBox") String currentBox,
                                                  @FormParam("dateStart") String dateStart,
                                                  @FormParam("dateEnd") String dateEnd){
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Integer paramCurrentBox = Integer.parseInt(currentBox);
        Date paramDateStart = new Timestamp(Long.parseLong(dateStart));
        Date paramDateEnd = new Timestamp(Long.parseLong(dateEnd));

        RtfBox rtfBox = findBox(paramId, paramCurrentBox);
        List<RtfBoxData> dataList = rtfBoxDataDao.findByPeriod(rtfBox, paramDateStart, paramDateEnd);
        List<GraphDataTransfer> resultList = new ArrayList<GraphDataTransfer>();
        GraphDataTransfer graphData;
        for (RtfBoxData data : dataList){
            Integer value = data.getProduction();
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
