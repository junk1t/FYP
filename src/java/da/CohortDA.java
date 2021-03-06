/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Cohort;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DEREK
 */
@ManagedBean(name = "cohortDA")
@SessionScoped
public class CohortDA {

    String years = "";
    String month = "";

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

    public void insertCohort(Cohort c) throws SQLException {
        String cohortID = getMaxID();
        Connection connect;

        try {
            connect = DBConnection.getConnection();
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

            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO COHORT VALUES(?,?,?)");

            pstmt.setString(1, cohortID);
            pstmt.setString(2, c.getYears());
            pstmt.setString(3, c.getMonth());

            pstmt.executeUpdate();
            this.success = true;

        } catch (SQLException ex) {
            this.message = true;
            System.out.println(ex.getMessage());
        }

    }

    public String deleteCohort() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TCohortID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("delete from COHORT where COHORTID = ?");
            ps.setString(1, TCohortID);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/selectProgrammeCohort.xhtml?faces-redirect=true";
    }

    public Cohort get(String cohortID) {
        Connection connect;
        Cohort c = new Cohort();
        try {
            connect = DBConnection.getConnection();
            PreparedStatement pstmt = connect.prepareStatement("select * from COHORT where COHORTID = ?");
            pstmt.setString(1, cohortID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                c = new Cohort(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;

    }

    public Cohort editCohort(String cohortID) {
        Connection connect;
        Cohort c = new Cohort();
        try {
            connect = DBConnection.getConnection();
            PreparedStatement pstmt = connect.prepareStatement("select * from COHORT where COHORTID = ?");
            pstmt.setString(1, cohortID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                c = new Cohort(rs.getString(1), rs.getString(2), rs.getString(3));
            }
//            sessionMap.put("editProgramme", obj_Programme);

        } catch (Exception e) {
            System.out.println(e);
        }
        return c;

    }

    public void updateCohort(Cohort c) {
        Connection connect;
        
        try {
            connect = DBConnection.getConnection();
            PreparedStatement ps = connect.prepareStatement("update Cohort set years=?, month=? where cohortID=?");
            
            ps.setString(1, c.getYears());
            ps.setString(2, c.getMonth());
            ps.setString(3, c.getCohortID());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getMaxID() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        Connection connect = null;

        String url = "jdbc:derby://localhost:1527/schedule";
        String username = "schedule";
        String password = "schedule";
        String cohortID = "";

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

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(cohortID);
        return cohortID;
    }
}
