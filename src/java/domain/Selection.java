/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import client.ViewAll;
import client.generateXML;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Selection {

    ViewAll va = new ViewAll();
    private Map<String, Boolean> checked = new HashMap<String, Boolean>();
    private List<String> checkedListGroup;
    private List<String> checkedListStaff;
    private List<String> checkedListVenue;

    public List<String> getCheckedListGroup() {
        return checkedListGroup;
    }

    public void setCheckedListGroup(List<String> checkedListGroup) {
        this.checkedListGroup = checkedListGroup;
    }

    public List<String> getCheckedListStaff() {
        return checkedListStaff;
    }

    public void setCheckedListStaff(List<String> checkedListStaff) {
        this.checkedListStaff = checkedListStaff;
    }

    public List<String> getCheckedListVenue() {
        return checkedListVenue;
    }

    public void setCheckedListVenue(List<String> checkedListVenue) {
        this.checkedListVenue = checkedListVenue;
    }

    public Map<String, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<String, Boolean> checked) {
        this.checked = checked;
    }

    public List<String> submitGroups() throws ClassNotFoundException, SQLException, IOException {
        List<TutorialGroup> tgList = new ArrayList<TutorialGroup>();
        tgList = va.getAllTutorialGroup();

        checkedListGroup = new ArrayList<String>();
        for (TutorialGroup item : tgList) {
            if (checked.get(item.getGroupID()).booleanValue()) {
                checkedListGroup.add(item.getGroupID());
                checked.remove(item.getGroupID());
            }
        }
        //here logic for selected apps
        if (!checkedListGroup.isEmpty()) {
            for (int i = 0; i < checkedListGroup.size(); i++) {
                System.out.println(checkedListGroup.get(i));
            }
        } else {
            System.out.println("Empty Groups");
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("step4Staff.xhtml");
        return checkedListGroup;
    }

    public List<String> submitStaff() throws ClassNotFoundException, SQLException, IOException {
        List<Staff> staffList = new ArrayList<Staff>();
        staffList = va.getAllStaff();

        checkedListStaff = new ArrayList<String>();
        for (Staff item : staffList) {
            if (checked.get(item.getStaffID()).booleanValue()) {
                checkedListStaff.add(item.getStaffID());
                checked.remove(item.getStaffID());
            }
        }
        //here logic for selected apps
        if (!checkedListStaff.isEmpty()) {
            for (int i = 0; i < checkedListStaff.size(); i++) {
                System.out.println(checkedListStaff.get(i));
            }
        } else {
            System.out.println("Empty Staff");
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("step5Venue.xhtml");
        return checkedListStaff;
    }

    public List<String> submitVenue() throws ClassNotFoundException, SQLException, IOException {
        List<Venue> venueList = new ArrayList<Venue>();
        venueList = va.getAllVenue();

        checkedListVenue = new ArrayList<String>();
        for (Venue item : venueList) {
            if (checked.get(item.getVenueID()).booleanValue()) {
                checkedListVenue.add(item.getVenueID());
                checked.remove(item.getVenueID());
            }
        }
        //here logic for selected apps
        if (!checkedListVenue.isEmpty()) {
            for (int i = 0; i < checkedListVenue.size(); i++) {
                System.out.println(checkedListVenue.get(i));
            }

        } else {
            System.out.println("Empty Venue");
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("step6Programme.xhtml");
        return checkedListVenue;
    }

    public void generateAll() {

        generateXML xml = new generateXML();

        if (!checkedListGroup.isEmpty()) {
            xml.generateTutorialGroupXML(checkedListGroup);
        } else {
            System.out.println("Empty Group");
        }

        if (!checkedListStaff.isEmpty()) {
            xml.generateStaffXML(checkedListStaff);
        } else {
            System.out.println("Empty Staff");
        }

        if (!checkedListVenue.isEmpty()) {
            xml.generateVenueXML(checkedListVenue);
        } else {
            System.out.println("Empty Venue");
        }
    }
}
