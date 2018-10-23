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

    private String staffID, staffName, blockday, blockStart, blockDuration;
    private ArrayList<Class> classList;

    public Staff() {

    }

    public Staff(String staffID, String staffName, String blockday, String blockStart, String blockDuration) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.blockday = blockday;
        this.blockStart = blockStart;
        this.blockDuration = blockDuration;
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

    public String getBlockday() {
        return blockday;
    }

    public void setBlockday(String blockday) {
        this.blockday = blockday;
    }

    public String getBlockStart() {
        return blockStart;
    }

    public void setBlockStart(String blockStart) {
        this.blockStart = blockStart;
    }

    public String getBlockDuration() {
        return blockDuration;
    }

    public void setBlockDuration(String blockDuration) {
        this.blockDuration = blockDuration;
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }

}
