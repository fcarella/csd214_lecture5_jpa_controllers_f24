package csd214_publications.entities;


import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author fcarella
 */
@Entity
public class Magazine extends Publication {

    @Basic
    private int orderQty;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date currIssue;

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrIssue() {
        return currIssue;
    }

    public void setCurrIssue(Date currIssue) {
        this.currIssue = currIssue;
    }

}