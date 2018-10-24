/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Programme;
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
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "programmeDA")
@SessionScoped
/**
 *
 * @author DEREK
 */
public class ProgrammeDA implements Serializable {

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
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

    public void clear() {
        Programme p = new Programme();
        p.setProgrammeCode(null);
        p.setProgrammeName(null);

    }//end clear`

    public void insertProgramme(Programme p) throws SQLException {
        String programmeID = getMaxID();
        Connection connect = null;

        try {
            connect = DBConnection.getConnection();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PROGRAMMEID FROM PROGRAMME");

            ArrayList<Integer> ls = new ArrayList<>();

            while (rs.next()) {
                try {
                    ls.add(Integer.parseInt(rs.getString(1).split("P")[1]));
                } catch (Exception ex) {
                    System.out.println("Invalid Exception");
                }
            }

            if (ls.size() > 0) {
                int max = Collections.max(ls) + 1;
                programmeID = "P" + max;
            } else {
                programmeID = "P1001";
            }

            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO PROGRAMME VALUES(?,?,?)");

            pstmt.setString(1, programmeID);
            pstmt.setString(2, p.getProgrammeCode());
            pstmt.setString(3, p.getProgrammeName());

            pstmt.executeUpdate();
            this.success = true;

        } catch (SQLException ex) {
            this.message = true;
            System.out.println(ex.getMessage());
        }

    }

    public String deleteProgramme() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TProgrammeID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("delete from PROGRAMME where PROGRAMMEID = ?");
            ps.setString(1, TProgrammeID);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/selectProgrammeCohort.xhtml?faces-redirect=true";
    }

    public String editProgramme() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TprogrammeID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from PROGRAMME where PROGRAMMEID = '" + TprogrammeID + "'");
            Programme obj_Programme = new Programme();
            rs.next();
            obj_Programme.setProgrammeID(rs.getString("ProgrammeID"));
            obj_Programme.setProgrammeCode(rs.getString("ProgrammeCode"));
            obj_Programme.setProgrammeName(rs.getString("ProgeammeName"));

            sessionMap.put("editProgramme", obj_Programme);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "/editProgramme.xhtml?faces-redirect=true";
    }

    public String updateProgramme() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String programmeID = params.get("programmeID");
        String programmeCode = params.get("programmeCode");
        String programmeName = params.get("programmeName");

        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("update PROGRAMME set PROGRAMMECODE=?, PROGEAMMENAME=? where PROGRAMMEID=?");
            ps.setString(1, programmeCode);
            ps.setString(2, programmeName);
            ps.setString(3, programmeID);

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
        String programmeID = "";

        try {

            connect = DriverManager.getConnection(url, username, password);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PROGRAMMEID FROM PROGRAMME");

            ArrayList<Integer> ls = new ArrayList<>();

            while (rs.next()) {
                try {
                    ls.add(Integer.parseInt(rs.getString(1).split("P")[1]));
                } catch (Exception ex) {
                    System.out.println("Invalid Exception");
                }
            }

            if (ls.size() > 0) {
                int max = Collections.max(ls) + 1;
                programmeID = "P" + max;
            } else {
                programmeID = "P";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(programmeID);
        return programmeID;
    }
}
