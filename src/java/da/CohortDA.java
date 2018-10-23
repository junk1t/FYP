/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Cohort;
import java.io.Serializable;
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
public class CohortDA implements Serializable {

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

    public void reset() {

    }

    public void insertCohort() throws SQLException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String cohortID = getMaxID();
        years = params.get("years");
        month = params.get("month");

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
            
         
        
            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO COHORT VALUES(?,?,?)");

            pstmt.setString(1, cohortID);
            pstmt.setString(2, years);
            pstmt.setString(3, month);

            pstmt.executeUpdate();
            this.success = true;
          
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
             this.message = false;
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

    public String editCohort() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TCohortID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from Cohort where CohortID = '" + TCohortID + "'");
            Cohort obj_Cohort = new Cohort();
            rs.next();
            obj_Cohort.setCohortID(rs.getString("cohortID"));
            obj_Cohort.setYears(rs.getString("years"));
            obj_Cohort.setMonth(rs.getString("month"));

            sessionMap.put("editCohort", obj_Cohort);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "/editCohort.xhtml?faces-redirect=true";
    }

    public String updateCohort() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String cohortID = params.get("cohortID");
        String years = params.get("years");
        String month = params.get("month");

        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("update Cohort set years=?, month=? where cohortID=?");
            ps.setString(1, years);
            ps.setString(2, month);
            ps.setString(3, cohortID);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

        return "/selectProgrammeCohort.xhtml?faces-redirect=true";
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
