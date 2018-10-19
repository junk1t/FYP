/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import da.DB_connection;
import domain.Staff;
import domain.TutorialGroup;
import domain.Venue;
import domain.CourseType;
import domain.Programme;
import domain.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DEREK
 */
@ManagedBean(name = "viewAll")
@SessionScoped
public class ViewAll implements Serializable{
 
    //GET VENUE
    public List<Venue> getAllVenue() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();

        List<Venue> Venue = new ArrayList<Venue>();
        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM venue");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Venue v = new Venue();
            v.setVenueID(rs.getString("venueID"));
            v.setBlock(rs.getString("block"));
            v.setVenueType(rs.getString("venueType"));
            v.setCapacity(rs.getInt("capacity"));
            v.setRemark(rs.getString("remark"));

            Venue.add(v);

        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return Venue;
    }

    //Get COURSE
    public List<CourseType> getAllCourse() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();

        List<CourseType> Course = new ArrayList<CourseType>();
        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM Course");
        PreparedStatement pstmtt = connect
                .prepareStatement("SELECT * FROM CourseType");
        ResultSet rs = pstmt.executeQuery();
        ResultSet rss = pstmtt.executeQuery();

        while (rs.next()) {

            CourseType c = new CourseType();
            c.setCourseCode(rs.getString("courseCode"));
            c.setCourseName(rs.getString("courseName"));
            c.setCreditHour(rs.getInt("creditHour"));

            while (rss.next()) {
                c.setCourseType(rss.getString("courseType"));
                c.setCourseDuration(rss.getString("courseDuration"));
                c.setCourseID(rss.getString("courseID"));
            }
            Course.add(c);
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return Course;
    }
    //Get TutorialGroup

    public List<TutorialGroup> getAllTutorialGroup() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();

        List<TutorialGroup> TutorialGroup = new ArrayList<TutorialGroup>();

        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM Tutorial_Group");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {

            TutorialGroup tg = new TutorialGroup();
            tg.setGroupID(rs.getString("groupID"));
            tg.setStudyYear(rs.getInt("studyYear"));
            tg.setGroupNumber(rs.getInt("groupNumber"));
            tg.setSize(rs.getInt("size"));
            tg.setProgrammeID(rs.getString("programmeID"));
            tg.setCohortID(rs.getString("cohortID"));

            TutorialGroup.add(tg);
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return TutorialGroup;
    }

    public List<Staff> getAllStaff() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();

        List<Staff> Staff = new ArrayList<Staff>();
        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM Staff ORDER BY STAFFID");

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Staff s = new Staff();

            s.setStaffID(rs.getString("staffID"));
            s.setStaffName(rs.getString("staffName"));
            s.setRemark(rs.getString("remark"));

            PreparedStatement pstmts = connect
                    .prepareStatement("SELECT * FROM STAFFDETAILS WHERE STAFFID = '" + s.getStaffID() + "'");
            ResultSet rss = pstmts.executeQuery();

            while (rss.next()) {

                s.setStartWork(rss.getString("startWork"));
                s.setEndWork(rss.getString("endWork"));

            }
            Staff.add(s);
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return Staff;
    }

    public List<Programme> getAllProgrammeID() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();
        List<Programme> programme = new ArrayList<Programme>();
        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM PROGRAMME ORDER BY PROGRAMMEID");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Programme p = new Programme();
            p.setProgrammeID(rs.getString("programmeID"));
            programme.add(p);
        }
        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return programme;
    }

    public List<Cohort> getAllCohortID() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();
        List<Cohort> cohort = new ArrayList<Cohort>();
        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM COHORT ORDER BY COHORTID");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Cohort c = new Cohort();
            c.setCohortID(rs.getString("cohortID"));
            cohort.add(c);
        }
        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return cohort;
    }

    public List<Programme> getAllProgramme() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();

        List<Programme> Programme = new ArrayList<Programme>();
        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM PROGRAMME");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Programme p = new Programme();
            p.setProgrammeID(rs.getString("programmeID"));
            p.setProgrammeCode(rs.getString("programmeCode"));
            p.setProgrammeName(rs.getString("progeammeName"));
            Programme.add(p);

        }
          rs.close();
        pstmt.close();
        connect.close();

        return Programme;
    }
    public List<Cohort> getAllCohort() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();

        List<Cohort> Cohort = new ArrayList<Cohort>();
        PreparedStatement pstmt = connect
                .prepareStatement("SELECT * FROM COHORT");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Cohort c = new Cohort();
            c.setCohortID(rs.getString("cohortID"));
            c.setYears(rs.getString("years"));
            c.setMonth(rs.getString("month"));
        

           Cohort.add(c);

        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return Cohort;
    }
}
