package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.user.UserDao;
import nl.rooftopenergy.bionic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * @Depricated as Spring security was implemented.
 */
@Deprecated
@Component
@Path("/user")
public class UserResource
{

	@Autowired
	private UserDao userDao;

	@Path("/authenticate")
	@POST
	public Response authenticate(@FormParam("username") String username, @FormParam("password") String password)
	{
        User user = userDao.findByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            return Response.status(400).entity("Wrong authorization data").build();

        }
        return Response.status(307).entity("loggedPage.html")
                .build();
	}
}