package nl.rooftopenergy.bionic.transfer;

/**
 * Created by Владислав on 19.01.2015.
 */
public class ComparingInfoTransfer {
    private Integer totalProduction;
    private Integer totalConsumption;
    private Double treesSaved;
    private Double carbonOffset;
    private Integer solarPanels;
    private String panelType;

    public ComparingInfoTransfer() {

    }
    public ComparingInfoTransfer(Integer totalProduction, Integer totalConsumption, Double treesSaved,Double carbonOffset,Integer solarPanels, String panelType) {
        this.totalProduction=totalProduction;
        this.totalConsumption=totalConsumption;
        this.treesSaved=treesSaved;
        this.carbonOffset=carbonOffset;
        this.solarPanels=solarPanels;
        this.panelType=panelType;
    }

    public Integer getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(Integer totalProduction) {
        this.totalProduction = totalProduction;
    }

    public Integer getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Integer totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public Double getCarbonOffset() {
        return carbonOffset;
    }

    public void setCarbonOffset(Double carbonOffset) {
        this.carbonOffset = carbonOffset;
    }

    public Double getTreesSaved() {
        return treesSaved;
    }

    public void setTreesSaved(Double treesSaved) {
        this.treesSaved = treesSaved;
    }

    public Integer getSolarPanels() {
        return solarPanels;
    }

    public void setSolarPanels(Integer solarPanels) {
        this.solarPanels = solarPanels;
    }

    public String getPanelType() {
        return panelType;
    }

    public void setPanelType(String panelType) {
        this.panelType = panelType;
    }
}
