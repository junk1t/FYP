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
import domain.Venue;
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
                v.setRemark(rs.getString(5));

                output.add(v);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        DBConnection.close(connect);
        return output;

    }

    public void insertVenue() throws SQLException {
        Connection connect = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String venueID = params.get("venueID");
        String block = params.get("block");
        String venueType = params.get("venueType");
        String capacity = params.get("capacity");
        String remark = params.get("remark");

        String sql = "INSERT INTO VENUE(venueID,block,venueType,capacity,remark) VALUES(?,?,?,?,?)";

        try {
            connect = DBConnection.getConnection();
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
               
                pstmt.setString(1, venueID);
                pstmt.setString(2, block);
                pstmt.setString(3, venueType);
                pstmt.setString(4, capacity);
                pstmt.setString(5, remark);

                pstmt.executeUpdate();
                this.success = true;
                this.message = false;
            } catch (SQLException ex) {

                this.success = false;
                this.message = true;
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {

            this.success = false;
            this.message = true;
            System.out.println(ex.getMessage());
        }
        DBConnection.close(connect);

    }

    public String editVenue() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TvenueID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from Venue where VenueID = '" + TvenueID + "'");
            Venue obj_Venue = new Venue();
            rs.next();
            obj_Venue.setVenueID(rs.getString("venueID"));
            obj_Venue.setBlock(rs.getString("block"));
            obj_Venue.setVenueType(rs.getString("venueType"));
            obj_Venue.setCapacity(rs.getInt("capacity"));
            obj_Venue.setRemark(rs.getString("remark"));
            sessionMap.put("editVenue", obj_Venue);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "/editVenue.xhtml?faces-redirect=true";
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
