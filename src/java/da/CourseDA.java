/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import domain.CourseType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDA { 
    
    

    public List<Course> getAllRecords() throws SQLException {

        Connection connect = null;

        List<Course> output = new ArrayList<>();
        try {
            connect = DBConnection.getConnection();
            PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM course");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Course C = new Course();
                C.setCourseCode(rs.getString(1));
                C.setCourseName(rs.getString(2));
                C.setCreditHour(rs.getInt(3));
            

                output.add(C);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        
        DBConnection.close(connect);
        return output;

    }

    public List<CourseType> getSelectedRecords(String courseID) throws SQLException {

        Connection connect = null;
        List<CourseType> output = new ArrayList<CourseType>();

        try {
            connect = DBConnection.getConnection();
            PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM coursetype WHERE courseID=? ");
            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CourseType CT = new CourseType();
                CT.setCourseID(rs.getString(1));
                CT.setCourseType(rs.getString(2));
                CT.setCourseDuration(rs.getString(3));
                CT.setCourseCode(rs.getString(4));

                output.add(CT);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        DBConnection.close(connect);
        return output;

    }

}
