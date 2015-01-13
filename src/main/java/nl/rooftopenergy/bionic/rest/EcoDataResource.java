package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Владислав on 24.12.2014.
 */
@RestController
@Path("eco")
public class EcoDataResource {

    //trees saved for 1 Kwh
    private static final double TREE_SAVED_FOR_ONE_KILOWATT = 0.002742;
    //CO2 offset in kilograms for 1 Kwh (for dutch region)
    private static final double CARBON_OFFSET_FOR_ONE_KILOWATT=0.46;

    @Inject
    private PrincipalInformation principalInformation;
    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @POST
    @Path("trees")
    @Produces(MediaType.APPLICATION_JSON)
    public Double getTreesSaved() {
        Company company = principalInformation.getCompany();
        Integer totalProduction = rtfBoxDataDao.findTotalProduction(company.getRtfBox());
        Double treesSaved = 0.0;
        treesSaved=(totalProduction.doubleValue()/1000.0)*TREE_SAVED_FOR_ONE_KILOWATT;
        return treesSaved;
    }

    @POST
    @Path("carbonOffset")
    @Produces(MediaType.APPLICATION_JSON)
    public Double getCarbonOffset() {
        Company company = principalInformation.getCompany();
        Integer totalProduction = rtfBoxDataDao.findTotalProduction(company.getRtfBox());
        Double carbonOffset=0.0;
        carbonOffset=(totalProduction.doubleValue()/1000)*CARBON_OFFSET_FOR_ONE_KILOWATT;
        return carbonOffset;
    }

}
