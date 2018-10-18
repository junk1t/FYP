/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.TutorialGroup;
import domain.Venue;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author DEREK
 */
@ManagedBean(name = "TutorialGroupDA")
@SessionScoped
public class TutorialGroupDA implements Serializable {

    //Get the alert message
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

//    public void reset(){
//        this.groupID = "";
//        this.studyYear = 0;
//        this.groupNumber = 0;
//        this.size = 0 ;
//    }
    public List<TutorialGroup> getSelectedRecords(String groupID) throws SQLException {

        Connection connect = null;
        List<TutorialGroup> output = new ArrayList<TutorialGroup>();

        try {
            connect = DBConnection.getConnection();
            PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM tutorial_group WHERE groupID=? ");
            pstmt.setString(1, groupID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TutorialGroup TG = new TutorialGroup();
                TG.setGroupID(rs.getString(1));
                TG.setStudyYear(rs.getInt(2));
                TG.setGroupNumber(rs.getInt(3));
                TG.setSize(rs.getInt(4));
                TG.setProgrammeID(rs.getString(5));
                TG.setCohortID(rs.getString(6));

                output.add(TG);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        DBConnection.close(connect);
        return output;

    }

    public void insertTutorialGroup() throws SQLException {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String groupID = getMaxID();
        String studyYear = params.get("studyYear");
        String groupNumber = params.get("groupNumber");
        String size = params.get("size");
        String programmeID = params.get("programmeID");
        String cohortID = params.get("cohortID");
        Connection connect = null;

        String url = "jdbc:derby://localhost:1527/schedule";
        String username = "schedule";
        String password = "schedule";

        try {
            connect = DriverManager.getConnection(url, username, password);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COHORTID FROM COHORT");

            ArrayList<Integer> ls = new ArrayList<>();

            while (rs.next()) {
                try {
                    ls.add(Integer.parseInt(rs.getString(1).split("CH")[1]));
                } catch (Exception ex) {
                    System.out.println("Invalid Exception");
                }
            }

            if (ls.size() > 0) {
                int max = Collections.max(ls) + 1;
                cohortID = "CH" + max;
            } else {
                cohortID = "CH1001";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO TUTORIAL_GROUP VALUES(?,?,?,?,?,?)");

            pstmt.setString(1, groupID);
            pstmt.setString(2, studyYear);
            pstmt.setString(3, groupNumber);
            pstmt.setString(4, size);
            pstmt.setString(5, programmeID);
            pstmt.setString(6, cohortID);
            pstmt.executeUpdate();

            this.success = true;
            this.message = false;

        } catch (SQLException ex) {
            this.message = true;
            this.success = false;
            System.out.println(ex.getMessage());
        }
    }

    public String deleteTutorialGroup() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TtutorialGroup = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("delete from Tutorial_Group where GroupID = ?");
            ps.setString(1, TtutorialGroup);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/step3Groups.xhtml?faces-redirect=true";
    }

    public String editTutorialGroup() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String groupID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from TUTORIAL_GROUP where GROUPID = '" + groupID + "'");
            TutorialGroup obj_Group = new TutorialGroup();
            rs.next();
            obj_Group.setGroupID(rs.getString("groupID"));
            obj_Group.setStudyYear(rs.getInt("studyYear"));
            obj_Group.setGroupNumber(rs.getInt("groupNumber"));
            obj_Group.setSize(rs.getInt("size"));
            obj_Group.setProgrammeID(rs.getString("programmeID"));
            obj_Group.setCohortID(rs.getString("cohortID"));

            sessionMap.put("editGroup", obj_Group);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "/editGroup.xhtml?faces-redirect=true";
    }

    public String updateTutorialGroup() {

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

        return "/EditInfo.xhtml?faces-redirect=true";
    }

    public String getMaxID() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        Connection connect = null;

        String url = "jdbc:derby://localhost:1527/schedule";
        String username = "schedule";
        String password = "schedule";
        String groupID = "";

        try {

            connect = DriverManager.getConnection(url, username, password);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT GROUPID FROM TUTORIAL_GROUP");

            ArrayList<Integer> ls = new ArrayList<>();

            while (rs.next()) {
                try {
                    ls.add(Integer.parseInt(rs.getString(1).split("G")[1]));
                } catch (Exception ex) {
                    System.out.println("Invalid Exception");
                }
            }

            if (ls.size() > 0) {
                int max = Collections.max(ls) + 1;
                groupID = "G" + max;
            } else {
                groupID = "G1001";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return groupID;
    }
}
