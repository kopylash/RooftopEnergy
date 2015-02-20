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
 * Provides REST API to get information about consumption and production of energy produced by
 * solar panels to use data to show by regions.
 * It is possible to get information about production and consumption which was produced or
 * consumed by each region. Also it is able to get information about ratio between production
 * and consumption.
 *
 * Created by Alexander Iakovenko on 2/19/15.
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

    /**
     * Gets list of regions and their production by current month.
     * @return list of regions.
     */
    @POST
    @Path("production/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> productionMonthly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> companiesMap = new HashMap<String, Double>();

        for(Company c : companiesList){
            String companyName = c.getCompanyName();
            Integer value = comparingDataResource.thisMonthTotalProduction(companyName);
            double castedValue = value == null ? 0d : (double)value;
            companiesMap.put(companyName, castedValue);
        }

        List<MapEntity> result = getList(companiesMap, companiesList);
        return result;
    }

    /**
     * Gets list of regions and their production by current year.
     * @return list of regions.
     */
    @POST
    @Path("production/yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> productionYearly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> companiesMap = new HashMap<String, Double>();

        for(Company c : companiesList){
            String companyName = c.getCompanyName();
            Integer value = comparingDataResource.thisYearTotalProduction(companyName);
            double castedValue = value == null ? 0d : (double)value;
            companiesMap.put(companyName, castedValue);
        }

        List<MapEntity> result = getList(companiesMap, companiesList);
        return result;
    }

    /**
     * Gets list of regions and their consumption by current month.
     * @return list of regions.
     */
    @POST
    @Path("consumption/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> consumptionMonthly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> companiesMap = new HashMap<String, Double>();

        for(Company c : companiesList){
            String companyName = c.getCompanyName();
            Integer value = comparingDataResource.thisMonthTotalConsumption(companyName);
            double castedValue = value == null ? 0d : (double)value;
            companiesMap.put(companyName, castedValue);
        }

        List<MapEntity> result = getList(companiesMap, companiesList);
        return result;
    }

    /**
     * Gets list of regions and their consumption by current year.
     * @return list of regions.
     */
    @POST
    @Path("consumption/yearly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> consumptionYearly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> companiesMap = new HashMap<String, Double>();

        for(Company c : companiesList){
            String companyName = c.getCompanyName();
            Integer value = comparingDataResource.thisYearTotalProduction(companyName);
            double castedValue = value == null ? 0d : (double)value;
            companiesMap.put(companyName, castedValue);
        }

        List<MapEntity> result = getList(companiesMap, companiesList);
        return result;
    }

    /**
     * Gets list of regions and their ratio between production and consumption
     * by current month.
     * @return list of regions.
     */
    @POST
    @Path("ratio/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> ratioMonthly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> companiesMap = new HashMap<String, Double>();

        for(Company c : companiesList){
            String companyName = c.getCompanyName();
            Integer production = comparingDataResource.thisMonthTotalProduction(companyName);
            Integer consumption = comparingDataResource.thisMonthTotalConsumption(companyName);
            double castedProduction = (production == null) || (production == 0) ? 1.0 : (double)production;
            double castedConsumption = (consumption == null) || (consumption == 0) ? 1.0 : (double)consumption;
            double ratio = castedProduction/castedConsumption;
            companiesMap.put(companyName, ratio);
        }

        List<MapEntity> result = getList(companiesMap, companiesList);
        return result;
    }

    /**
     * Gets list of regions and their ratio between production and consumption
     * by current month.
     * @return list of regions.
     */
    @POST
    @Path("ratio/monthly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapEntity> ratioYearly(){

        List<Company> companiesList = companyDao.findAll();
        HashMap<String, Double> companiesMap = new HashMap<String, Double>();

        for(Company c : companiesList){
            String companyName = c.getCompanyName();
            Integer production = comparingDataResource.thisYearTotalProduction(companyName);
            Integer consumption = comparingDataResource.thisYearTotalConsumption(companyName);
            double castedProduction = (production == null) || (production == 0) ? 1.0 : (double)production;
            double castedConsumption = (consumption == null) || (consumption == 0) ? 1.0 : (double)consumption;
            double ratio = castedProduction/castedConsumption;
            companiesMap.put(companyName, ratio);
        }

        List<MapEntity> result = getList(companiesMap, companiesList);
        return result;
    }

    private List<MapEntity> getList(HashMap<String, Double> companiesData, List<Company> companiesList) {

        HashMap<String, Double> values = new HashMap<String, Double>();
        for (Company e : companiesList) {
            String key = e.getProvince();
            String companyName = e.getCompanyName();
            Double month = companiesData.get(companyName);
            Double oldValue = dutchProvinceList.getEntity(key).getValue();
            Double newValue;
            if (oldValue == null){
                oldValue = 0d;
            }
            if (values.containsKey(key)) {
                newValue = values.getOrDefault(key, 0d) + oldValue + month;
                values.replace(key, newValue);
            } else {
                newValue = oldValue + month;
                values.put(key, newValue);
            }
        }
        List<MapEntity> provinces = dutchProvinceList.getList();
        for (MapEntity e : provinces) {
            e.setValue(values.get(e.getName()));
        }
        return provinces;
    }

}
