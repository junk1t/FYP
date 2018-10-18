/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.CourseType;
import domain.TutorialGroup;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DEREK
 */
@ManagedBean(name = "course")
@SessionScoped
public class CourseTypeDA implements Serializable {

    private CourseType c;
    boolean success, message;

    public void ini() {
        c = new CourseType();
    }

    public CourseType getCourse() {
        return c;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public void insertCourse() throws SQLException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String courseCode = params.get("courseCode");
        String courseName = params.get("courseName");
        String creditHour = params.get("creditHour");
        String courseID = params.get("courseID");
        String courseType = params.get("courseType");
        String courseDuration = params.get("courseDuration");

        Connection connect = null;
        String url = "jdbc:derby://localhost:1527/schedule";
        String username = "schedule";
        String password = "schedule";

        try {
            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO COURSE VALUES(?,?,?)");

            pstmt.setString(1, courseCode);
            pstmt.setString(2, courseName);
            pstmt.setString(3, creditHour);

            pstmt.executeUpdate();
            try {
                
                PreparedStatement pstmtt = connect.prepareStatement("INSERT INTO COURSETYPE VALUES(?,?,?,?)");

                pstmt.setString(1, courseID);
                pstmt.setString(2, courseType);
                pstmt.setString(3, courseDuration);
                pstmtt.setString(4, courseCode);

                this.success = true;
                this.message = false;

            } catch (SQLException ex) {
                this.message = true;
                this.success = false;
                System.out.println(ex.getMessage());
            }

        } catch (SQLException ex) {
            this.message = true;
            this.success = false;
            System.out.println(ex.getMessage());
        }

    }

    public String deleteCourse() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String CcourseCode = params.get("action");

        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("delete from Course where COURSECODE = ?");
            ps.setString(1, CcourseCode);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/step2Course.xhtml?faces-redirect=true";
    }

    public String editCourse() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String CcourseCode = params.get("action");

        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from COURSE where COURSECODE = '" + CcourseCode + "'");
            CourseType obj_Course = new CourseType();

            rs.next();
            obj_Course.setCourseCode(rs.getString("courseCode"));
            obj_Course.setCourseName(rs.getString("courseName"));
            obj_Course.setCreditHour(rs.getInt("creditHour"));

            sessionMap.put("editCourse", obj_Course);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "/editCourse.xhtml?faces-redirect=true";
    }

    public List<CourseType> getMaxCourseType() throws ClassNotFoundException, SQLException {

        DB_connection dc = new DB_connection();
        Connection connect = dc.connection();

        List<CourseType> CourseType = new ArrayList<CourseType>();

        PreparedStatement pstmt = connect
                .prepareStatement("SELECT MAX(COURSEID) AS MAXID FROM COURSETYPE;");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {

            CourseType ct = new CourseType();
            ct.setCourseID(rs.getString("courseID"));

            CourseType.add(ct);
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        return CourseType;
    }

    public String updateCourse() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String CcourseCode = params.get("editCourseCode");
        String CcourseName = params.get("editCourseName");
        String CcreditHour = params.get("editCreditHour");

        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("UPDATE COURSE SET COURSENAME=?, CREDITHOUR=? where COURSECODE=?");

            ps.setString(1, CcourseName);
            ps.setString(2, CcreditHour);
            ps.setString(3, CcourseCode);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

        return "/EditInfo.xhtml?faces-redirect=true";
    }

    public void addCourseInfo() throws SQLException, ClassNotFoundException {
        int id2 = -1;
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String courseCode = params.get("courseCode");
        String courseType = params.get("courseType");
        String courseDuration = params.get("courseDuration");
        String courseID = Integer.toString(id2) + 1;

        Connection connect = null;
        String url = "jdbc:derby://localhost:1527/schedule";
        String username = "schedule";
        String password = "schedule";

        Statement st2 = connect.createStatement();
        ResultSet idMax = st2.executeQuery("select (max(COURSECODE),0) maxID from COURSETYPE");

        if (idMax.next()) {
            id2 = idMax.getInt("maxID");
        }

        try {
            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO COURSETYPE VALUES(?,?,?,?)");

            pstmt.setString(1, courseID);
            pstmt.setString(2, courseType);
            pstmt.setString(3, courseDuration);
            pstmt.setString(3, courseCode);

            pstmt.executeUpdate();
            this.success = true;
            this.message = false;

        } catch (SQLException ex) {
            this.message = true;
            this.success = false;
            System.out.println(ex.getMessage());
        }

    }

}
