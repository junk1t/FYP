/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alex
 */
package domain;

import da.DB_connection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//
@ManagedBean(name = "venue")
@SessionScoped
public class Venue implements Serializable{

    Connection connect = null;
    public String venueID;
    public String block;
    public String venueType;
    public String remark, courseCodeList;
    public int capacity;
    private ArrayList<Class> classList;

    public Venue() {

    }

    public Venue(String venueID, String block, String venueType, int capacity, String remark) {
        this.venueID = venueID;
        this.block = block;
        this.venueType = venueType;
        this.capacity = capacity;
        this.remark = remark;
        this.classList = new ArrayList();
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }

    public String getCourseCodeList() {
        return courseCodeList;
    }

    public void setCourseCodeList(String courseCodeList) {
        this.courseCodeList = courseCodeList;
    }
    

 
    
   
}
