/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import domain.Venue;
import da.CourseDA;
import da.VenueDA;
import domain.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author junki
 */
@ManagedBean
@RequestScoped
public class VenueManage {

    public VenueDA vda = new VenueDA();
    private CourseDA cda = new CourseDA();
    public Venue v = new Venue();
    private List<String> selectedCourseCodeList = new ArrayList();
    private String[] courseCodeStr;

    public String[] getCourseCodeStr() {
        return courseCodeStr;
    }

    public void setCourseCodeStr(String[] courseCodeStr) {
        this.courseCodeStr = courseCodeStr;
    }

    public List<String> getCourseCodeList() throws SQLException {
        List<Course> courseList = cda.getAllRecords();
        for (Course c : courseList) {
            this.selectedCourseCodeList.add(c.getCourseCode());
        }
        return this.selectedCourseCodeList;
    }

    public Venue getV() {
        return v;
    }

    public void setV(Venue v) {
        this.v = v;
    }

    public void venueInsert() throws SQLException, IOException {
        String tempStr = "-";
        if (courseCodeStr != null) {
            for (String s : courseCodeStr) {
                if (tempStr.length() == 0) {
                    tempStr = s;
                } else {
                    tempStr += "," + s;
                }
            }
        }
        v.setCourseCodeList(tempStr);
        vda.insertVenue(v);
        FacesContext.getCurrentInstance().getExternalContext().redirect("step5Venue.xhtml");
    }
}
