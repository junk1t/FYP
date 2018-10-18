/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author DEREK
 */
public class StaffDetails {
    String staffID, starWork, endWork;

    public StaffDetails(String staffID, String starWork, String endWork) {
        this.staffID = staffID;
        this.starWork = starWork;
        this.endWork = endWork;
    }

    public StaffDetails() {
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStarWork() {
        return starWork;
    }

    public void setStarWork(String starWork) {
        this.starWork = starWork;
    }

    public String getEndWork() {
        return endWork;
    }

    public void setEndWork(String endWork) {
        this.endWork = endWork;
    }
    
    
}
