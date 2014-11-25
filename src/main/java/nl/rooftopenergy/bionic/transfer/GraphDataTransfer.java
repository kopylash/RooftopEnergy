package nl.rooftopenergy.bionic.transfer;

import java.util.Date;

/**
 * Created by alex on 11/25/14.
 */
public class GraphDataTransfer {

    private Date date;
    private Integer value;

    public GraphDataTransfer(){}

    public GraphDataTransfer(Date date, Integer value){
        this.date = date ;
        this.value = value;
    }

    /**
     * Gets date and time when the measuring was done.
     * @returns date and time.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date and time when the measuring was done.
     * @param date and time
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets a value of energy was received as a result of the measuring.
     * @returns value of energy.
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets the value of energy was received as a result of the measuring.
     * @param value of energy.
     */
    public void setValue(Integer value) {
        this.value = value;
    }

}
