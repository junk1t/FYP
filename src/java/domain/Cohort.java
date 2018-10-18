/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Teck Siong
 */
@ManagedBean(name="cohort")
@SessionScoped
public class Cohort implements Serializable {
    String cohortID;
    String years;
    String month;
    
    
    public Cohort(){
    
    }

    public Cohort(String cohortID, String years, String month) {
        this.cohortID = cohortID;
        this.years = years;
        this.month = month;
    }

    public String getCohortID() {
        return cohortID;
    }

    public void setCohortID(String cohortID) {
        this.cohortID = cohortID;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }



    
    
    

   
    
    
}

