/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;


import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Alex
 */
@ManagedBean(name="courseType")
@SessionScoped
public class CourseType implements Serializable{

    private String courseID, courseType, courseDuration, courseCode;
    private String courseName;
    private int creditHour;



    public CourseType() {

    }

    public CourseType(String courseID, String courseType, String courseDuration, String courseCode) {
        this.courseID = courseID;
        this.courseType = courseType;
        this.courseDuration = courseDuration;
        this.courseCode = courseCode;
    }

    public CourseType(String courseID, String courseType, String courseDuration, String courseCode, String courseName, int creditHour) {
        this.courseID = courseID;
        this.courseType = courseType;
        this.courseDuration = courseDuration;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHour = creditHour;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }
    
    
    

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    
    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
