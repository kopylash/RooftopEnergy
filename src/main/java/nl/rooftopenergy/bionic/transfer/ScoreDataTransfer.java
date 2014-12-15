package nl.rooftopenergy.bionic.transfer;

/**
 * Created by Владислав on 05.12.2014.
 */
public class ScoreDataTransfer implements Comparable<ScoreDataTransfer> {
    private String company;
    private Integer arrow;

    public ScoreDataTransfer(){

    }

    /**
     *
     * @param company
     * @param arrow
     */
    public ScoreDataTransfer(String company, Integer arrow) {
        this.company = company;
        this.arrow = arrow;
    }

    /**
     * Gets company name
     * @return
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the name of the company
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Gets comparison status
     * 1 - more
     * 0 - equal
     * -1 - less
     * @return
     */
    public Integer getArrow() {
        return arrow;
    }

    /**
     * Sets the comparison status
     * 1 - more
     * 0 - equal
     * -1 - less
     * @param arrow
     */
    public void setArrow(Integer arrow) {
        this.arrow = arrow;
    }

    @Override
    public int compareTo(ScoreDataTransfer o) {
        if (this.arrow > o.arrow) {
            return 1;
        }
        if (this.arrow < o.arrow) {
            return -1;
        }
        return 0;
    }
}
