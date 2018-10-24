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

    String staffID;
    double startWork, endWork;

    public StaffDetails(String staffID, double startWork, double endWork) {
        this.staffID = staffID;
        this.startWork = startWork;
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

    public double getStartWork() {
        return startWork;
    }

    public void setStartWork(double startWork) {
        this.startWork = startWork;
    }

    public double getEndWork() {
        return endWork;
    }

    public void setEndWork(double endWork) {
        this.endWork = endWork;
    }

   
}
