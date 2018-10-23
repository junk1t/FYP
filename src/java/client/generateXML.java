//package client;
//
//import da.CourseDA;
//import domain.CourseType;
//import da.StaffDA;
//import domain.Staff;
//import da.VenueDA;
//import domain.Venue;
//import da.TutorialGroupDA;
//import domain.TutorialGroup;
//import java.io.File;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import org.w3c.dom.Attr;
//import org.w3c.dom.Element;
//import org.w3c.dom.Document;
//
//public class generateXML {
//
//    //Course
//    private CourseDA cda = new CourseDA();
//
//    //Staff
//    private StaffDA sda = new StaffDA();
//
//    //Venue
//    private VenueDA vda = new VenueDA();
//
//    //Tutorial Group
//    private TutorialGroupDA tgda = new TutorialGroupDA();
//    private final String filePath = "C:\\Users\\REPUBLIC\\Documents\\NetBeansProjects\\New Folder\\FYP\\src\\java\\xml\\";
//
////    public void generateCourseXML() {
////        List<CourseType> list = new ArrayList<CourseType>();
////        List<CourseType> recordList = new ArrayList<CourseType>();
////        String xmlFilePath = filePath + "Course.xml";
////
////        try {
////            list = cda.getAllRecords();
////            for (CourseType item : list) {
////                CourseType record = new CourseType(item.getCourseID(), item.getCourseType(), item.getCourseDuration(), item.getCourseCode());
////                recordList.add(record);
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(generateXML.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////        try {
////            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
////
////            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
////
////            Document document = documentBuilder.newDocument();
////
////            //root element
////            Element root = document.createElement("courses");
////            document.appendChild(root);
////
////            for (CourseType item : recordList) {
////                // course element
////                Element course = document.createElement("course");
////                root.appendChild(course);
////
////                // set an attribute to course element
////                Attr attr = document.createAttribute("courseID");
////                attr.setValue(item.getCourseID());
////                course.setAttributeNode(attr);
////
////                // courseType element
////                Element courseType = document.createElement("courseType");
////                courseType.appendChild(document.createTextNode(item.getCourseType()));
////                course.appendChild(courseType);
////
////                // courseDuration element
////                Element courseDuration = document.createElement("courseDuration");
////                courseDuration.appendChild(document.createTextNode(item.getCourseDuration()));
////                course.appendChild(courseDuration);
////
////                // courseCode element
////                Element courseCode = document.createElement("courseCode");
////                courseCode.appendChild(document.createTextNode(item.getCourseCode()));
////                course.appendChild(courseCode);
////
////            }
////
////            //create the xml file
////            //transform the DOM Object to an XML File
////            TransformerFactory transformerFactory = TransformerFactory.newInstance();
////            Transformer transformer = transformerFactory.newTransformer();
////            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
////            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
////            DOMSource domSource = new DOMSource(document);
////            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
////            transformer.transform(domSource, streamResult);
////            System.out.println("Done generating Course.xml XML File");
////
////        } catch (ParserConfigurationException pce) {
////            pce.printStackTrace();
////        } catch (TransformerException tfe) {
////            tfe.printStackTrace();
////        }
////
////    }
////    
//    
//    public void generateVenueXML(String venueID) {
//        List<Venue> list = new ArrayList<Venue>();
//        List<Venue> recordList = new ArrayList<Venue>();
//        String xmlFilePath = filePath + "Venue.xml";
//
//        try {
//            list = vda.getSelectedRecords(venueID);
//            for (Venue item : list) {
//                Venue record = new Venue(item.getVenueID(), item.getBlock(), item.getVenueType(), item.getCapacity(), item.getRemark());
//                recordList.add(record);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(generateXML.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//
//            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
//
//            Document document = documentBuilder.newDocument();
//
//            //root element
//            Element root = document.createElement("venues");
//            document.appendChild(root);
//
//            for (Venue item : recordList) {
//                // venue element
//                Element venue = document.createElement("venue");
//                root.appendChild(venue);
//
//                // set an attribute to staff element
//                Attr attr = document.createAttribute("venueID");
//                attr.setValue(item.getVenueID());
//                venue.setAttributeNode(attr);
//
//                // block element
//                Element block = document.createElement("block");
//                block.appendChild(document.createTextNode(item.getBlock()));
//                venue.appendChild(block);
//
//                // venueType element
//                Element venueType = document.createElement("venueType");
//                venueType.appendChild(document.createTextNode(item.getVenueType()));
//                venue.appendChild(venueType);
//
//                // capacity element
//                Element capacity = document.createElement("capacity");
//                capacity.appendChild(document.createTextNode(String.valueOf(item.getCapacity())));
//                venue.appendChild(capacity);
//
//            }
//
//            //create the xml file
//            //transform the DOM Object to an XML File
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
//            transformer.transform(domSource, streamResult);
//            System.out.println("Done generating Venue.xml XML File");
//
//        } catch (ParserConfigurationException pce) {
//            pce.printStackTrace();
//        } catch (TransformerException tfe) {
//            tfe.printStackTrace();
//        }
//
//    }
//
//    public void generateStaffXML(String staffID) {
//        List<Staff> list = new ArrayList<Staff>();
//        List<Staff> recordList = new ArrayList<Staff>();
//        String xmlFilePath = filePath + "Staff.xml";
//
//        try {
//            list = sda.getSelectedRecords(staffID);
//            for (Staff item : list) {
//                Staff record = new Staff(item.getStaffID(), item.getStaffName(), item.getRemark(), item.getStartWork(), item.getEndWork());
//                recordList.add(record);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(generateXML.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//
//            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
//
//            Document document = documentBuilder.newDocument();
//
//            //root element
//            Element root = document.createElement("staffs");
//            document.appendChild(root);
//
//            for (Staff item : recordList) {
//                // staff element
//                Element staff = document.createElement("staff");
//                root.appendChild(staff);
//
//                // set an attribute to staff element
//                Attr attr = document.createAttribute("staffID");
//                attr.setValue(item.getStaffID());
//                staff.setAttributeNode(attr);
//
//                // name element
//                Element staffName = document.createElement("name");
//                staffName.appendChild(document.createTextNode(item.getStaffName()));
//                staff.appendChild(staffName);
//
//                // startWork element
//                Element startWork = document.createElement("startWork");
//                startWork.appendChild(document.createTextNode(item.getStartWork()));
//                staff.appendChild(startWork);
//
//                // endWork element
//                Element endWork = document.createElement("endWork");
//                endWork.appendChild(document.createTextNode(item.getEndWork()));
//                staff.appendChild(endWork);
//
//                // remark element
//                Element remark = document.createElement("remark");
//                remark.appendChild(document.createTextNode(item.getRemark()));
//                staff.appendChild(remark);
//
//            }
//
//            //create the xml file
//            //transform the DOM Object to an XML File
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
//            transformer.transform(domSource, streamResult);
//            System.out.println("Done generating Staff.xml XML File");
//
//        } catch (ParserConfigurationException pce) {
//            pce.printStackTrace();
//        } catch (TransformerException tfe) {
//            tfe.printStackTrace();
//        }
//
//    }
//
//    
//    
//    public void generateTutorialGroupXML(String groupID) {
//        List<TutorialGroup> list = new ArrayList<TutorialGroup>();
//        List<TutorialGroup> recordList = new ArrayList<TutorialGroup>();
//        String xmlFilePath = filePath + "TutorialGroup.xml";
//
//        try {
//            list = tgda.getSelectedRecords(groupID);
//            for (TutorialGroup item : list) {
//                TutorialGroup record = new TutorialGroup(item.getGroupID(), item.getStudyYear(), item.getGroupNumber(),item.getSize(), item.getProgrammeID(), item.getCohortID());
//                recordList.add(record);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(generateXML.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//
//            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
//
//            Document document = documentBuilder.newDocument();
//
//            //root element
//            Element root = document.createElement("tutorialGroups");
//            document.appendChild(root);
//
//            for (TutorialGroup item : recordList) {
//                // tutorialGroup element
//                Element tutorialGroup = document.createElement("tutorialGroup");
//                root.appendChild(tutorialGroup);
//
//                // set an attribute to staff element
//                Attr attr = document.createAttribute("groupID");
//                attr.setValue(item.getGroupID());
//                tutorialGroup.setAttributeNode(attr);
//
//                // studyYear element
//                Element studyYear = document.createElement("studyYear");
//                studyYear.appendChild(document.createTextNode(String.valueOf(item.getStudyYear())));
//                tutorialGroup.appendChild(studyYear);
//
//                // groupNumber element
//                Element groupNumber = document.createElement("groupNumber");
//                groupNumber.appendChild(document.createTextNode(String.valueOf(item.getGroupNumber())));
//                tutorialGroup.appendChild(groupNumber);
//
//                // size element
//                Element size = document.createElement("size");
//                size.appendChild(document.createTextNode(String.valueOf(item.getGroupNumber())));
//                tutorialGroup.appendChild(size);
//
//                // programmeID element
//                Element programmeID = document.createElement("programmeID");
//                programmeID.appendChild(document.createTextNode(item.getProgrammeID()));
//                tutorialGroup.appendChild(programmeID);
//                
//                //cohortID element
//                Element cohortID = document.createElement("cohortID");
//                cohortID.appendChild(document.createTextNode(item.getCohortID()));
//                tutorialGroup.appendChild(cohortID);
//
//            }
//
//            //create the xml file
//            //transform the DOM Object to an XML File
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
//            transformer.transform(domSource, streamResult);
//            System.out.println("Done generating TutorialGroup.xml XML File");
//
//        } catch (ParserConfigurationException pce) {
//            pce.printStackTrace();
//        } catch (TransformerException tfe) {
//            tfe.printStackTrace();
//        }
//
//    }
//}
