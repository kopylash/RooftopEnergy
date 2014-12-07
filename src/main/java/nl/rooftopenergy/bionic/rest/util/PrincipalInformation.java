package nl.rooftopenergy.bionic.rest.util;


import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.user.UserDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Provides methods to find out the information about user, company
 * boxes using data from {@code SecurityContextHolder}.
 *
 * Created by alex on 12/7/14.
 */
@Service
public class PrincipalInformation {

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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            principalName = ((UserDetails) principal).getUsername();
        } else {
            principalName = principal.toString();
        }
        return principalName;

    }

    /**
     * Gets instance of {@code nl.rooftopenergy.entity.Company} this user belongs to.
     * @return company.
     */
    public Company getCompany(){
        Company company;
        String principalName = getPrincipalName();
        User user = userDao.findByName(principalName);
        company = companyDao.findById(user.getCompany().getCompanyId());
        return company;
    }

}
