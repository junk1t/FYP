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
import java.util.ArrayList;
import domain.Class;
import domain.Programme;
import domain.Venue;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "venueDA")
@SessionScoped
public class VenueDA implements Serializable {

    public ArrayList<Class> getClassList(String venueID) throws SQLException {
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

        PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM class WHERE venueID = ?");
        pstmt.setString(1, venueID);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Class c = new domain.Class(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7));
            classList.add(c);
        }
        return classList;
    }

    //Get the alert message
    boolean success, message, update, delete;

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

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;;
    }

    public List<Venue> getSelectedRecords(String venueID) throws SQLException {
        Connection connect = null;
        List<Venue> output = new ArrayList<Venue>();

        try {
            connect = DBConnection.getConnection();
            PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM venue WHERE venueID=? ");
            pstmt.setString(1, venueID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Venue v = new Venue();
                v.setVenueID(rs.getString(1));
                v.setBlock(rs.getString(2));
                v.setVenueType(rs.getString(3));
                v.setCapacity(rs.getInt(4));
                

                output.add(v);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        DBConnection.close(connect);
        return output;

    }

    public void insertVenue(Venue v) throws SQLException {
        Connection connect = null;

        try {
            connect = DBConnection.getConnection();

            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO VENUE(venueID,block,venueType,capacity,courseCodeList) VALUES(?,?,?,?,?)");

            pstmt.setString(1, v.getVenueID());
            pstmt.setString(2, v.getBlock());
            pstmt.setString(3, v.getVenueType());
            pstmt.setInt(4, v.getCapacity());
            pstmt.setString(5, v.getCourseCodeList());
            pstmt.executeUpdate();
            this.success = true;
            this.message = false;

        } catch (SQLException ex) {

            this.success = false;
            this.message = true;
            System.out.println(ex.getMessage());
        }
        DBConnection.close(connect);

    }

    public void editVenue(String venueID) throws IOException {
        Connection connect = null;
        
         
        try {   
            connect = DBConnection.getConnection();

            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select * from Venue where VenueID = '" + venueID + "'");
            
           rs.next();
               Venue v = new Venue();
               v.setVenueID(rs.getString("venueID"));
               v.setBlock(rs.getString("block"));
               v.setVenueType(rs.getString("venueType"));
               v.setCapacity(rs.getInt("capacity"));

        } catch (SQLException e) {
            System.out.println(e);
        }
        DBConnection.close(connect);
        
    FacesContext.getCurrentInstance().getExternalContext().redirect("editVenue.xhtml");
    }

    public String updateVenue() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String venueID = params.get("venueID");
        String block = params.get("block");
        String venueType = params.get("venueType");
        String capacity = params.get("capacity");
        String remark = params.get("remark");

        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("update VENUE set block=?, venueType=?, capacity=?, remark=? where venueID=?");
            ps.setString(1, block);
            ps.setString(2, venueType);
            ps.setString(3, capacity);
            ps.setString(4, remark);
            ps.setString(5, venueID);
            ps.executeUpdate();
            this.update = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return "/selectVenue.xhtml?faces-redirect=true";

    }

    public String deleteVenue() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TVenueID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            PreparedStatement ps = connection.prepareStatement("delete from VENUE where VenueID = ?");
            ps.setString(1, TVenueID);
            System.out.println(ps);
            ps.executeUpdate();
            this.delete = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/selectVenue.xhtml?faces-redirect=true";
    }

}
