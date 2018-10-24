/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import da.CohortDA;
import domain.Cohort;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DEREK
 */
@ManagedBean
@RequestScoped
public class cohortManage {
    public CohortDA cda = new CohortDA();
    public Cohort c = new Cohort();

    public Cohort getC() {
        return c;
    }

    public void setC(Cohort c) {
        this.c = c;
    }
    
     public void insertCohort() throws SQLException, IOException{
     cda.insertCohort(c);
        FacesContext.getCurrentInstance().getExternalContext().redirect("step1Programme.xhtml");
     }
     
}
