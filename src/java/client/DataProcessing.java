/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import domain.Selection;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
    
@ManagedBean
@SessionScoped
public class DataProcessing implements Serializable{
    private List<String> checkedListGroup;
    private List<String> checkedListStaff;
    private List<String> checkedListVenue;

    
    public void run() throws ClassNotFoundException, SQLException, IOException {
        generateXML test = new generateXML();
        Selection testing = new Selection();
        List<String> venueList = new ArrayList<String>();
        venueList=testing.getCheckedListVenue();
        test.generateVenueXML(venueList);

    }
    

}
