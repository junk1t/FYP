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

    private String staffID, staffName;
    private ArrayList<Class> classList;
    private String courseCodeList, lecGroupList, tutGroupList,pracGroupList;
    private double blockStart;
    private int blockday,blockDuration;

    public Staff() {

    }

    public Staff(String staffID, String staffName, int blockday, double blockStart, int blockDuration) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.blockday = blockday;
        this.blockStart = blockStart;
        this.blockDuration = blockDuration;
    }

    public Staff(String staffID, String staffName, int blockday, double blockStart, int blockDuration, ArrayList<Class> classList, String courseCodeList, String lecGroupList, String tutGroupList, String pracGroupList) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.blockday = blockday;
        this.blockStart = blockStart;
        this.blockDuration = blockDuration;
        this.classList = classList;
        this.courseCodeList = courseCodeList;
        this.lecGroupList = lecGroupList;
        this.tutGroupList = tutGroupList;
        this.pracGroupList = pracGroupList;
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

    public int getBlockday() {
        return blockday;
    }

    public void setBlockday(int blockday) {
        this.blockday = blockday;
    }

    public double getBlockStart() {
        return blockStart;
    }

    public void setBlockStart(double blockStart) {
        this.blockStart = blockStart;
    }

    public int getBlockDuration() {
        return blockDuration;
    }

    public void setBlockDuration(int blockDuration) {
        this.blockDuration = blockDuration;
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

    public String getLecGroupList() {
        return lecGroupList;
    }

    public void setLecGroupList(String lecGroupList) {
        this.lecGroupList = lecGroupList;
    }

    public String getTutGroupList() {
        return tutGroupList;
    }

    public void setTutGroupList(String tutGroupList) {
        this.tutGroupList = tutGroupList;
    }

    public String getPracGroupList() {
        return pracGroupList;
    }

    public void setPracGroupList(String pracGroupList) {
        this.pracGroupList = pracGroupList;
    }
    

}
