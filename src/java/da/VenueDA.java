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
public class VenueDA implements Serializable{
 private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

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
//    public void reset() {
//        this.venueID = null;
//        this.block = null;
//        this.venueType = null;
//        this.capacity = 0;
//        this.remark = null;
//    }

    public void insertVenue() throws SQLException {
  FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String venueID = params.get("venueID");
        String block = params.get("block");
        String venueType = params.get("venueType");
        String capacity = params.get("capacity");
        String remark = params.get("remark");
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
            PreparedStatement pstmt = connect.prepareStatement("INSERT INTO VENUE VALUES(?,?,?,?,?)");

            pstmt.setString(1, venueID);
            pstmt.setString(2, block);
            pstmt.setString(3, venueType);
            pstmt.setString(4, capacity);
            pstmt.setString(5, remark);

            pstmt.executeUpdate();
            this.success = true;
            this.message = false;
   
        } catch (SQLException ex) {
            this.message = true;
            this.success = false;
            System.out.println(ex.getMessage());
        }

    }
 

    public String editVenue() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String TvenueID = params.get("action");
        try {
            DB_connection obj_DB_connection = new DB_connection();
            Connection connection = obj_DB_connection.connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from Venue where VenueID = '" + TvenueID+ "'");
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

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "/EditInfo.xhtml?faces-redirect=true";
    }
    public String deleteVenue(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String TVenueID= params.get("action");
      try {
         DB_connection obj_DB_connection=new DB_connection();
         Connection connection=obj_DB_connection.connection();
       PreparedStatement ps=connection.prepareStatement("delete from VENUE where VenueID = ?");
         ps.setString(1, TVenueID);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/EditInfo.xhtml?faces-redirect=true";   
}


   
}
