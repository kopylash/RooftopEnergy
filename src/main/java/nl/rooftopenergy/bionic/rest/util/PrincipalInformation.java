package nl.rooftopenergy.bionic.rest.util;


import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.user.UserDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * Provides methods to find out the information about user, company
 * boxes using data from {@code SecurityContextHolder}.
 *
 * Created by alex on 12/7/14.
 */
@Service
public class PrincipalInformation {
    private static final Logger logger = Logger.getLogger(PrincipalInformation.class);

    @Inject
    private CompanyDao companyDao;

    @Inject
    private UserDao userDao;


    /**
     * Gets user name from {@code SecurityContextHolder} this user has been logged in.
     * @return user name
     */
    public String getPrincipalName(){
        String principalName;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                principalName = ((UserDetails) principal).getUsername();
            } else {
                principalName = principal.toString();
            }
            if (principalName.equals("anonymousUser")){
                throw new WebApplicationException(401);
            }
        } catch (NullPointerException e){
            logger.info("User has not been authorized!");
            throw new WebApplicationException(401);
        }
        return principalName;

    }

    /**
     * Gets instance of {@code nl.rooftopenergy.entity.Company} this user belongs to.
     * @return company.
     */
    public Company getCompany(){
        Company company;
        try {
            String principalName = getPrincipalName();
            User user = userDao.findByName(principalName);
            company = user.getCompany();
        } catch (NullPointerException e){
            logger.info("User has not been authorized!");
            throw new WebApplicationException(401);
        }
        return company;
    }

}
