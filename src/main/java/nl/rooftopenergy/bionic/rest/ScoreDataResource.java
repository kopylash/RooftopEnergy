package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.transfer.GraphDataTransfer;
import nl.rooftopenergy.bionic.transfer.ScoreDataTransfer;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    @POST
    @Path("ratings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScoreDataTransfer> showScore(@FormParam("id") String id){

        return Collections.emptyList();
    }

}
