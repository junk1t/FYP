/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Time;

/**
 *
 * @author Alex
 */
public class Class {

    private String courseID, venueID, groupID, staffID, courseType;
    private int day;
    private double startTime, endTime;

    public Class() {
    }

    public Class(String courseID, String groupID, int day, double startTime, double endTime, String courseType) {
        this.courseID = courseID;
        this.groupID = groupID;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseType = courseType;
    }

    public Class(String courseID, String venueID, String groupID, String staffID, int day, double startTime, double endTime) {
        this.courseID = courseID;
        this.venueID = venueID;
        this.groupID = groupID;
        this.staffID = staffID;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Class(String courseID, String venueID, String groupID, String staffID, int day, double startTime, double endTime, String courseType) {
        this.courseID = courseID;
        this.venueID = venueID;
        this.groupID = groupID;
        this.staffID = staffID;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseType = courseType;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

}
