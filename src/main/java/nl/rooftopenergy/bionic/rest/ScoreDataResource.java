package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.transfer.ScoreDataTransfer;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Владислав on 05.12.2014.
 */
@RestController
@Path("score")
public class ScoreDataResource {

    @Inject
    private CompanyDao companyDao;

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    /**
     * Compare 2 companies by production.
     * cmp1 is the primary object which is compared with cmp2
     * @param cmp1
     * @param cmp2
     * @return
     */
    private Integer compareByProduction(Company cmp1, Company cmp2) {
        if (rtfBoxDataDao.findTotalProduction(cmp1.getRtfBoxList().get(0))>rtfBoxDataDao.findTotalProduction(cmp2.getRtfBoxList().get(0))) {
            return -1;
        }
        if (rtfBoxDataDao.findTotalProduction(cmp1.getRtfBoxList().get(0))<rtfBoxDataDao.findTotalProduction(cmp2.getRtfBoxList().get(0))) {
            return 1;
        }
        return 0;
    }

    @POST
    @Path("production")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScoreDataTransfer> showProductionScore(@FormParam("id") String id){
        Integer paramId = Integer.parseInt(id);
        companyDao.findById(paramId);
        Company company = companyDao.findById(paramId);
        List<Company> companyList = companyDao.findAllPublic();
        companyList.remove(company);
        List<ScoreDataTransfer> resultList= new ArrayList<ScoreDataTransfer>();
        for (Company c : companyList) {
            resultList.add(new ScoreDataTransfer(c.getCompanyName(),compareByProduction(company,c)));
        }
        return resultList;
    }

    /**
     * Compares 2 companies by consumption.
     * cmp1 is compared with cmp2
     * @param cmp1
     * @param cmp2
     * @return
     */
    private Integer compareByConsumption(Company cmp1, Company cmp2) {
        if (rtfBoxDataDao.findTotalConsumption(cmp1.getRtfBoxList().get(0))>rtfBoxDataDao.findTotalConsumption(cmp2.getRtfBoxList().get(0))) {
            return -1;
        }
        if (rtfBoxDataDao.findTotalConsumption(cmp1.getRtfBoxList().get(0))<rtfBoxDataDao.findTotalConsumption(cmp2.getRtfBoxList().get(0))) {
            return 1;
        }
        return 0;
    }

    @POST
    @Path("consumption")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScoreDataTransfer> showConsumptionScore(@FormParam("id") String id){
        Integer paramId = Integer.parseInt(id);
        companyDao.findById(paramId);
        Company company = companyDao.findById(paramId);
        List<Company> companyList = companyDao.findAllPublic();
        companyList.remove(company);
        List<ScoreDataTransfer> resultList= new ArrayList<ScoreDataTransfer>();
        for (Company c : companyList) {
            resultList.add(new ScoreDataTransfer(c.getCompanyName(),compareByConsumption(company, c)));
        }
        return resultList;
    }

}
