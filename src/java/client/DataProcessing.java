/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

public class DataProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        generateXML test = new generateXML();
        test.generateCourseXML();
        test.generateStaffXML("S1001");
        test.generateVenueXML("H303");
        test.generateTutorialGroupXML("G1001");
    }

}
