package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import nl.rooftopenergy.bionic.transfer.ScoreDataTransfer;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
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

    @Inject
    private PrincipalInformation principalInformation;

    /**
     * Gets list of companies according to their energy production.
     * An {@code ScoreDataTransfer} contains company name and arrow status fields.
     * Arrow status is a value that was getting as result of comparing between current
     * company and other one.
     * Arrow status is {@value 0} if production of couple companies is equal.
     * Arrow status is {@value -1} if production of current company greater than other one.
     * Arrow status is {@value 1} if production of current company less than other one.
     *
     * @return list of {@code ScoreDataTransfer} objects.
     */
    @POST
    @Path("production")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScoreDataTransfer> showProductionScore() {
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Company company = companyDao.find(paramId);
        List<Company> companyList = companyDao.findAllPublic();
        companyList.remove(company);
        List<ScoreDataTransfer> resultList = new ArrayList<ScoreDataTransfer>();
        for (Company c : companyList) {
            resultList.add(new ScoreDataTransfer(c.getCompanyName(), compareByProduction(company, c)));
        }
        Collections.sort(resultList);
        return resultList;
    }

    /**
     * Gets list of companies according to their energy consumption.
     * An {@code ScoreDataTransfer} contains company name and arrow status fields.
     * Arrow status is a value that was getting as result of comparing between current
     * company and other one.
     * Arrow status is {@value 0} if production of couple companies is equal.
     * Arrow status is {@value -1} if production of current company greater than other one.
     * Arrow status is {@value 1} if production of current company less than other one.
     *
     * @return list of {@code ScoreDataTransfer} objects.
     */
    @POST
    @Path("consumption")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScoreDataTransfer> showConsumptionScore() {
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Company company = companyDao.find(paramId);
        List<Company> companyList = companyDao.findAllPublic();
        companyList.remove(company);
        List<ScoreDataTransfer> resultList = new ArrayList<ScoreDataTransfer>();
        for (Company c : companyList) {
            resultList.add(new ScoreDataTransfer(c.getCompanyName(), compareByConsumption(company, c)));
        }
        Collections.sort(resultList);
        return resultList;
    }

    /**
     * Gets list of companies according to the division production by consumption
     * An {@code ScoreDataTransfer} contains company name and arrow status fields.
     * Arrow status is a value that was getting as result of comparing between current
     * company and other one.
     * Arrow status is {@value 0} if production of couple companies is equal.
     * Arrow status is {@value -1} if production of current company greater than other one.
     * Arrow status is {@value 1} if production of current company less than other one.
     *
     * @return list of {@code ScoreDataTransfer} objects.
     */
    @POST
    @Path("overall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScoreDataTransfer> showOverallScore() {
        Integer paramId = principalInformation.getCompany().getCompanyId();
        Company company = companyDao.find(paramId);
        List<Company> companyList = companyDao.findAllPublic();
        companyList.remove(company);
        List<ScoreDataTransfer> resultList = new ArrayList<ScoreDataTransfer>();
        for (Company c : companyList) {
            resultList.add(new ScoreDataTransfer(c.getCompanyName(), compareByDivision(company, c)));
        }
        Collections.sort(resultList);
        return resultList;
    }

    /*
     * Compare 2 companies by production.
     * cmp1 is the primary object which is compared with cmp2
     */
    private Integer compareByProduction(Company cmp1, Company cmp2) {
        if (rtfBoxDataDao.findTotalProduction(cmp1.getRtfBox()) > rtfBoxDataDao.findTotalProduction(cmp2.getRtfBox())) {
            return -1;
        }
        if (rtfBoxDataDao.findTotalProduction(cmp1.getRtfBox()) < rtfBoxDataDao.findTotalProduction(cmp2.getRtfBox())) {
            return 1;
        }
        return 0;
    }

    /*
     * Compares 2 companies by consumption.
     * cmp1 is compared with cmp2
     */
    private Integer compareByConsumption(Company cmp1, Company cmp2) {
        if (rtfBoxDataDao.findTotalConsumption(cmp1.getRtfBox()) > rtfBoxDataDao.findTotalConsumption(cmp2.getRtfBox())) {
            return -1;
        }
        if (rtfBoxDataDao.findTotalConsumption(cmp1.getRtfBox()) < rtfBoxDataDao.findTotalConsumption(cmp2.getRtfBox())) {
            return 1;
        }
        return 0;
    }

    /*
     * Compares 2 companies by division production by consumption.
     * cmp1 is compared with cmp2
     */
    private Integer compareByDivision(Company cmp1, Company cmp2) {
        Double div1;
        Double div2;
        Integer production1 = rtfBoxDataDao.findTotalProduction(cmp1.getRtfBox());
        Integer consumption1 = rtfBoxDataDao.findTotalConsumption(cmp1.getRtfBox());
        if (consumption1 == 0) {
            div1 = production1.doubleValue();
        } else {
            div1 = production1.doubleValue() / consumption1.doubleValue();
        }
        Integer production2 = rtfBoxDataDao.findTotalProduction(cmp2.getRtfBox());
        Integer consumption2 = rtfBoxDataDao.findTotalConsumption(cmp2.getRtfBox());
        if (consumption2 == 0) {
            div2 = production1.doubleValue();
        } else {
            div2 = production2.doubleValue() / consumption2.doubleValue();
        }
        if (div1 > div2) {
            return 1;
        }
        if (div1 < div2) {
            return -1;
        }
        return 0;
    }

}
