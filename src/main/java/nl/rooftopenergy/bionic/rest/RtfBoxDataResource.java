package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by alex on 11/21/14.
 */
/*@Component*/
@RestController
@Path("total")
public class RtfBoxDataResource {
    private final Logger log = Logger.getLogger(RtfBoxDataResource.class.getName());

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    private RtfBoxDao rtfBoxDao;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showTotalNumber(@PathParam("id") String id){

        Integer param = Integer.parseInt(id);
        RtfBox box = rtfBoxDao.findByRtfBoxId(param);
        List<RtfBoxData> listData = rtfBoxDataDao.findByRtfBoxId(box.getRtfBoxId());
        int totalNumber = 0;
        for (RtfBoxData data : listData){
            totalNumber += data.getReading1();
        }
        return Response.ok(totalNumber).build();
    }


}
