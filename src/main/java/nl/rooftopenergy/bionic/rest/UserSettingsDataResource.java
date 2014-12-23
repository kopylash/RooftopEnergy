package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.user.UserDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.User;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import nl.rooftopenergy.bionic.transfer.UserDataTransfer;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Bess on 12/17/14.
 */
@RestController
@Path("boxData")
public class UserSettingsDataResource {
    private final Logger log = Logger.getLogger(RtfBoxDataResource.class.getName());

    @Inject
    private CompanyDao companyDao;

    @Inject
    private UserDao userDao;

    @Inject
    private PrincipalInformation principalInformation;

    /**
     * @param description is the new description of current user account that is about to be saved.
     */
    @POST
    @Path("/saveNewDescription")
    public Response saveNewDescription(@FormParam("description") String description){
        if (description == null) description = "";
        Company company = principalInformation.getCompany();
        company.setDescription(description);
        companyDao.save(company);
        return Response.ok().build();
    }

    /**
     * @return settings information about current user
     */
    @GET
    @Path("/getUserDescription")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDataTransfer getUserDescription(){
        String principalName = principalInformation.getPrincipalName();
        User user = userDao.findByName(principalName);
        Company company = user.getCompany();

        String userName = user.getUsername();
        String country = company.getCountry();
        String province = company.getProvince();
        String street = company.getStreet();
        Integer zipCode = company.getZipcode();
        String city = company.getTown();
        String companyName = company.getCompanyName();
        String panelType = company.getRtfBox().getPanelType();
        String description = company.getDescription();
        String email = user.getEmail();

        UserDataTransfer userDataTransfer = new UserDataTransfer(userName, description, panelType, companyName,
                city, zipCode, street, province, country, email);

        return userDataTransfer;
    }
}
