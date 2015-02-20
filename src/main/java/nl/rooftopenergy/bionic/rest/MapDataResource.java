package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.company.CompanyDao;
import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.rest.util.MapData;
import nl.rooftopenergy.bionic.rest.util.MapEntity;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alex on 2/19/15.
 */
@RestController
@Path("map")
public class MapDataResource {
    private final static Logger logger = Logger.getLogger(MapDataResource.class);

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    private RtfBoxDao rtfBoxDao;

    @Inject
    private CompanyDao companyDao;

    @Inject
    private ComparingDataResource comparingDataResource;

    @Inject
    private MapData dutchProvinceList;

    @POST
    @Path("production/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> productionMonthly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> values= new HashMap<String, Double>();

        for (Company e : companiesList){
            String key = e.getProvince();
            Integer month = comparingDataResource.thisMonthTotalProduction(e.getCompanyName());
            Double oldValue = dutchProvinceList.getEntity(key).getValue();
            if (oldValue == null){
                oldValue = 0d;
            }
            Double newValue;
            if (values.containsKey(key)){
                newValue = values.getOrDefault(key, 0d) + oldValue + month;
                values.replace(key, newValue);
            } else {
                newValue = oldValue + month;
                values.put(key, newValue);
            }
        }
        List<MapEntity> provinces = dutchProvinceList.getList();
        for (MapEntity e : provinces){
            e.setValue(values.get(e.getName()));
        }
        return provinces;
    }

    @POST
    @Path("production/yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> productionYearly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> values= new HashMap<String, Double>();

        for (Company e : companiesList){
            String key = e.getProvince();
            Integer year = comparingDataResource.thisYearTotalProduction(e.getCompanyName());
            Double oldValue = dutchProvinceList.getEntity(key).getValue();
            if (oldValue == null){
                oldValue = 0d;
            }
            Double newValue;
            if (values.containsKey(key)){
                newValue = values.getOrDefault(key, 0d) + oldValue + year;
                values.replace(key, newValue);
            } else {
                newValue = oldValue + year;
                values.put(key, newValue);
            }
        }
        List<MapEntity> provinces = dutchProvinceList.getList();
        for (MapEntity e : provinces){
            e.setValue(values.get(e.getName()));
        }
        return provinces;
    }

    @POST
    @Path("consumption/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> consumptionMonthly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> values= new HashMap<String, Double>();

        for (Company e : companiesList){
            String key = e.getProvince();
            Integer month = comparingDataResource.thisMonthTotalConsumption(e.getCompanyName());
            Double oldValue = dutchProvinceList.getEntity(key).getValue();
            if (oldValue == null){
                oldValue = 0d;
            }
            Double newValue;
            if (values.containsKey(key)){
                newValue = values.getOrDefault(key, 0d) + oldValue + month;
                values.replace(key, newValue);
            } else {
                newValue = oldValue + month;
                values.put(key, newValue);
            }
        }
        List<MapEntity> provinces = dutchProvinceList.getList();
        for (MapEntity e : provinces){
            e.setValue(values.get(e.getName()));
        }
        return provinces;
    }

    @POST
    @Path("consumption/yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> consumptionYearly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> values= new HashMap<String, Double>();

        for (Company e : companiesList){
            String key = e.getProvince();
            Integer year = comparingDataResource.thisYearTotalConsumption(e.getCompanyName());
            Double oldValue = dutchProvinceList.getEntity(key).getValue();
            if (oldValue == null){
                oldValue = 0d;
            }
            Double newValue;
            if (values.containsKey(key)){
                newValue = values.getOrDefault(key, 0d) + oldValue + year;
                values.replace(key, newValue);
            } else {
                newValue = oldValue + year;
                values.put(key, newValue);
            }
        }
        List<MapEntity> provinces = dutchProvinceList.getList();
        for (MapEntity e : provinces){
            e.setValue(values.get(e.getName()));
        }
        return provinces;
    }

}
