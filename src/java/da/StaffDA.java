/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.Class;
import domain.Staff;
import domain.StaffDetails;
import java.io.Serializable;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "StaffDA")
@SessionScoped
public class StaffDA implements Serializable {

    public List<Staff> getSelectedRecords(String staffID) throws SQLException {

        Connection connect = null;
        List<Staff> output = new ArrayList<Staff>();

        try {
            connect = DBConnection.getConnection();
            PreparedStatement pstmt = connect.prepareStatement("SELECT s.staffID,s.staffName,sd.startwork,sd.endwork,s.remark "
                    + "FROM staff s, staffdetails sd WHERE s.staffID=? AND s.staffID = sd.staffID");
            pstmt.setString(1, staffID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setStaffID(rs.getString(1));
                s.setStaffName(rs.getString(2));
                s.setBlockday(rs.getInt(3));
                s.setBlockStart(rs.getDouble(4));
                s.setBlockDuration(rs.getInt(4));

                output.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        DBConnection.close(connect);
        return output;

    }

    public ArrayList<Class> getClassList(String staffID) throws SQLException {
        ArrayList<Class> classList = new ArrayList();
        Connection connect = null;
        String url = "jdbc:derby://localhost:1527/schedule";

        String username = "schedule";
        String password = "schedule";

        try {
            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM class WHERE staffID = ?");
        pstmt.setString(1, staffID);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Class c = new Class(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7));
            classList.add(c);
        }
        return classList;
    }
    boolean success, message;

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
//
//    public void reset(){
//      staff= "";
//        this.staffName = null;
//       this.remark = null;
//     this.startWork = null;
//       this.endWork = null;
//   }

    public void insertStaff(Staff s,StaffDetails sd) throws SQLException {
        String staffID = getMaxID();
        Connection connect = null;

        try {
            connect = DBConnection.getConnection();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT STAFFID FROM STAFF");

            ArrayList<Integer> ls = new ArrayList<>();

            while (rs.next()) {
                try {
                    ls.add(Integer.parseInt(rs.getString(1).split("S")[1]));
                } catch (Exception ex) {
                    System.out.println("Invalid Exception");
                }
            }

            if (ls.size() > 0) {
                int max = Collections.max(ls) + 1;
                staffID = "S" + max;
            } else {
                staffID = "S1001";
            }

            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO STAFF VALUES(?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, staffID);
            pstmt.setString(2, s.getStaffName());
            pstmt.setInt(3, s.getBlockday());
            pstmt.setDouble(4, s.getBlockStart());
            pstmt.setInt(5, s.getBlockDuration());
            pstmt.setString(6, s.getCourseCodeList());
            pstmt.setString(7, s.getLecGroupList());
            pstmt.setString(8, s.getTutGroupList());
            pstmt.setString(9, s.getPracGroupList());
            pstmt.executeUpdate();

                    try {

                        PreparedStatement pstmts = connect.prepareStatement("INSERT INTO STAFFDETAILS VALUES(?,?,?)");
                        pstmts.setString(1, staffID);
                        pstmts.setDouble(2, sd.getStartWork());
                        pstmts.setDouble(3, sd.getEndWork());

                        pstmts.executeUpdate();

                        this.success = true;
                        this.message = false;

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String deleteStaff() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String Sstaff = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("delete from STAFF where STAFFID = ?");
            PreparedStatement pss = connection.prepareStatement("delete from STAFFDETAILS where STAFFID = ?");

            ps.setString(1, Sstaff);
            pss.setString(1, Sstaff);
            System.out.println(ps);
            ps.executeUpdate();
            pss.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/selectStaff.xhtml?faces-redirect=true";
    }

    public String editStaff() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        String staffID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from STAFF where STAFFID = '" + staffID + "'");

            Staff obj_Staff = new Staff();
            rs.next();
            obj_Staff.setStaffID(rs.getString("staffID"));
            obj_Staff.setStaffName(rs.getString("staffName"));
            obj_Staff.setBlockday(rs.getInt("blockDay"));
            obj_Staff.setBlockStart(rs.getDouble("blockStart"));
            obj_Staff.setBlockDuration(rs.getInt("blockDuration"));

            try {

                StaffDetails obj_StaffDetails = new StaffDetails();
                ResultSet rss = st.executeQuery("select * from STAFFDETAILS where STAFFID = '" + staffID + "'");
                rss.next();
                obj_StaffDetails.setStartWork(rss.getDouble("startWork"));
                obj_StaffDetails.setEndWork(rss.getDouble("endWork"));

                sessionMap.put("editStaff", obj_Staff);

            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "/editStaff.xhtml?faces-redirect=true";
    }

    public String updateStaff() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String groupID = params.get("groupID");
        String studyYear = params.get("studyYear");
        String groupNumber = params.get("groupNumber");
        String size = params.get("size");
        String programmeID = params.get("programmeID");
        String cohortID = params.get("cohortID");

        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("update TUTORIAL_GROUP set studyYear=?, groupNumber=?, size=?, programmeID=?, cohortID=? where groupID=?");

            ps.setString(1, studyYear);
            ps.setString(2, groupNumber);
            ps.setString(3, size);
            ps.setString(4, programmeID);
            ps.setString(5, cohortID);
            ps.setString(6, groupID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

        return "/selectStaff.xhtml?faces-redirect=true";
    }

    public String getMaxID() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        Connection connect = null;

        String url = "jdbc:derby://localhost:1527/schedule";
        String username = "schedule";
        String password = "schedule";
        String staffID = "";

        try {

            connect = DriverManager.getConnection(url, username, password);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT STAFFID FROM STAFF");

            ArrayList<Integer> ls = new ArrayList<>();

            while (rs.next()) {
                try {
                    ls.add(Integer.parseInt(rs.getString(1).split("S")[1]));
                } catch (Exception ex) {
                    System.out.println("Invalid Exception");
                }
            }

            if (ls.size() > 0) {
                int max = Collections.max(ls) + 1;
                staffID = "S" + max;
            } else {
                staffID = "S";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(staffID);
        return staffID;
    }

}
