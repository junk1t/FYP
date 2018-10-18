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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "tutorialGroup")
public class TutorialGroup implements Serializable{

    private String groupID, programmeID, cohortID, programmeCode, month, year;
    private int studyYear, groupNumber, size;
    private ArrayList<Class> classList;

    public TutorialGroup() {
    }

    public TutorialGroup(String groupID, int studyYear, int groupNumber, int size, String programmeID, String cohortID) {
        this.groupID = groupID;
        this.programmeID = programmeID;
        this.cohortID = cohortID;
        this.studyYear = studyYear;
        this.groupNumber = groupNumber;
        this.size = size;
        this.classList = new ArrayList();
    }

    public TutorialGroup(String groupID, int studyYear, int groupNumber, int size, String programmeID, String cohortID, String programmeCode, String month, String year) {
        this.groupID = groupID;
        this.programmeID = programmeID;
        this.cohortID = cohortID;
        this.studyYear = studyYear;
        this.groupNumber = groupNumber;
        this.size = size;
        this.programmeCode = programmeCode;
        this.month = month;
        this.year = year;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(String programmeID) {
        this.programmeID = programmeID;
    }

    public String getCohortID() {
        return cohortID;
    }

    public void setCohortID(String cohortID) {
        this.cohortID = cohortID;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
