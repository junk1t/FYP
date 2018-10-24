/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import da.StaffDA;
import domain.Staff;
import domain.StaffDetails;
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
public class staffManage {
    public StaffDA sda = new StaffDA();
    public StaffDetails sd = new StaffDetails();
    public Staff s = new Staff();
    
    

    public Staff getS() {
        return s;
    }

    public void setS(Staff s) {
        this.s = s;
    }

    public StaffDetails getSd() {
        return sd;
    }

    public void setSd(StaffDetails sd) {
        this.sd = sd;
    }
    
    
    public void staffInsert() throws SQLException, IOException {
        
  
        sda.insertStaff(s, sd);
        FacesContext.getCurrentInstance().getExternalContext().redirect("step4Staff.xhtml");
    }
    
}
