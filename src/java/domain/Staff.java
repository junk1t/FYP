/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import da.DB_connection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "staff")
public class Staff implements Serializable{

    private String staffID, staffName, remark, startWork, endWork;
    private ArrayList<Class> classList;

    public Staff() {

    }

    public Staff(String staffID, String staffName, String remark, String startWork, String endWork) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.remark = remark;
        this.startWork = startWork;
        this.endWork = endWork;
        this.classList = new ArrayList();
    }
    

    public Staff(String staffID, String staffName, String remark) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.remark = remark;
    }
    
   
    

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public String getEndWork() {
        return endWork;
    }

    public void setEndWork(String endWork) {
        this.endWork = endWork;
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }

}
