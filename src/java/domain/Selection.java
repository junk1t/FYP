/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Selection {

    public ArrayList<String> selectionList= new ArrayList();

    public void initializeList() {
    }

    public void addToList(String str) {
        for(int i=0; i< selectionList.size();i++){
        this.selectionList.add(str);
            //System.out.println(selectionList.get(i));
        }
    }

    public void printList() {
        if(!selectionList.isEmpty()){
        for (String str : selectionList) {
            System.out.println(str);
        }}
        else{
            System.out.println("EMPTY");
        }
    }
}

/**
 * public void selectedCourse(String selectedID) { List<String> list = new
 * ArrayList<String>();
 *
 * if (selected == true) { list.add(selectedID); } }
 *
 * public void redirect() throws IOException{
 * FacesContext.getCurrentInstance().getExternalContext().redirect("step3Groups.xhtml");
 * }*
 */
