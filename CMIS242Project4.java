package cmis.pkg242.project.pkg4;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Iterator;
import java.util.Map.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * 
 * @author AArgento
 * @date 5 October 2016
 * @class CMIS 242
 * @purpose 
 * 
 */

public class CMIS242Project4 extends JFrame {
    
    //define HashMap for database
    private HashMap<Integer, Student> database = new HashMap<>();
    
    //define labels
    private final JLabel labelID = new JLabel("ID:");
    private final JLabel labelName = new JLabel("Name:");
    private final JLabel labelMajor = new JLabel("Major:");
    private final JLabel labelSelection = new JLabel("Choose Selection:");
    private final JLabel labelStudents = new JLabel("Database Size:");
    private JLabel labelResults = new JLabel("0");

    //define text fields
    private JTextField textID = new JTextField();
    private JTextField textName = new JTextField();
    private JTextField textMajor = new JTextField();

    //define button
    private final JButton buttonProcess = new JButton("Process Request");
    private final JButton buttonPrint = new JButton("Print Database");

    //define options for all combo boxes
    String [] selectionOptions = new String [] {"","Insert", "Delete", "Find", "Update"};

    //define combo boxes
    private final JComboBox <String> comboSelection = new JComboBox<>(selectionOptions);
    
    Student student = new Student("", "");
  
    //constructor for CMIS242Project4
    public CMIS242Project4() {
 
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelID)
                .addComponent(labelName)
                .addComponent(labelMajor)
                .addComponent(labelSelection)
                .addComponent(buttonProcess)
                .addComponent(labelStudents))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(textID)
                            .addComponent(textName)
                            .addComponent(textMajor)
                            .addComponent(comboSelection)
                            .addComponent(buttonPrint)
                            .addComponent(labelResults))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelID)
                        .addComponent(textID))
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelName)
                        .addComponent(textName))
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelMajor)
                        .addComponent(textMajor))
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelSelection)
                        .addComponent(comboSelection))
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonProcess)
                        .addComponent(buttonPrint))
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelStudents)
                        .addComponent(labelResults))
        );

        //define all required tool tips
        textID.setToolTipText("Student ID Number");
        textName.setToolTipText("Student Name");
        textMajor.setToolTipText("Student Major");
        comboSelection.setToolTipText("Select Process To Execute In Database");
        
        //listener for compute button action
        buttonProcess.addActionListener((ActionEvent e) -> {
            if (comboSelection.getSelectedItem().equals("Insert")) {
                newStudent();
            } else if (comboSelection.getSelectedItem().equals("Delete")) {
                deleteStudent();
            } else if (comboSelection.getSelectedItem().equals("Find")) {
                findStudent();
            } else if (comboSelection.getSelectedItem().equals("Update")) {
                updateStudent();
            }
            
            String databaseSize = String.valueOf(database.size());
            labelResults.setText(databaseSize);    
        }); 
        
        //listener for print button action
        buttonPrint.addActionListener((ActionEvent e) -> {
            writeFile();
        }); 
        
        setTitle("CMIS 242 Project 4");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

}//end constructor

    private int getStudentID(){
        String stringID = textID.getText();
        int studentID = Integer.parseInt(stringID);
        return studentID;
    }//end getStudentID
    
    private void newStudent(){
        int studentID = getStudentID();
       
        String studentName = textName.getText();
        String studentMajor = textMajor.getText();
        
        Student newStudent = new Student(studentName, studentMajor);
        
        if (database.containsKey(studentID)){
            JOptionPane.showMessageDialog(
                    this,
                    "Student ID " + studentID + " is already in the database.",
                    "Add Student", 
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            database.put(studentID, newStudent);
            JOptionPane.showMessageDialog(
                    this,
                    "Student ID " +  studentID + " successfully inserted in the database.",
                    "Add Student", 
                    JOptionPane.PLAIN_MESSAGE);
        }  
    }//end newStudent
    
    private void deleteStudent(){
        int studentID = getStudentID();
        
        if (database.containsKey(studentID)){
            database.remove(studentID);
            JOptionPane.showMessageDialog(
                    this,
                    "Student ID " + studentID + " removed from the database.",
                    "Delete Student", 
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Student ID " +  studentID + " not in database.",
                    "Delete Student", 
                    JOptionPane.PLAIN_MESSAGE);
        }
        clearEntryValues();
    }
    
    private void findStudent(){
        int studentID = getStudentID();
        
        if (database.containsKey(studentID)){
            Student studentDetails = database.get(studentID);
            JOptionPane.showMessageDialog(
                    this,
                    studentDetails.toString(),
                    "Find Student",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    this, 
                    "Student ID " +  studentID + " not in database.", 
                    "Find Student", 
                    JOptionPane.PLAIN_MESSAGE);
        }
        clearEntryValues();  
    }
    
    private void updateStudent(){
        int studentID = getStudentID();
        
        if (database.containsKey(studentID)){
            
            Student record = database.get(studentID);
            
            final String gradeList [] = {"A", "B", "C", "D", "F"};
            final String creditList [] = {"3", "6"};
            
            String updateGrade = (String)JOptionPane.showInputDialog(
                    this, 
                    "Select Class Grade", 
                    "Update Grade", 
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    gradeList, 
                    gradeList[0]);
        
            String updateCredit = (String)JOptionPane.showInputDialog(
                    this, 
                    "Select Class Credits", 
                    "Update Credits", 
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    creditList, 
                    creditList[0]);
            
            int studentCredit = Integer.parseInt(updateCredit);
            
            record.courseCompleted(updateGrade.charAt(0), studentCredit);
            database.replace(studentID, record);
            
            JOptionPane.showMessageDialog(
                    this,
                    "Student ID " + studentID + " successfully updated.",
                    "Update Student", 
                    JOptionPane.PLAIN_MESSAGE);
            } else {

            JOptionPane.showMessageDialog(
                    this, 
                    "Student ID " +  studentID + " not in database.", 
                    "Update Student", 
                    JOptionPane.PLAIN_MESSAGE);
        }   
        
        clearEntryValues();
        
    }//end updateStudent
    
    private void writeFile (){

        int count = 0;
        
        try {
            FileWriter fileWriter = new FileWriter("StudentDatabase.txt");
            BufferedWriter out = new BufferedWriter(fileWriter);
        
            Iterator<Entry<Integer, Student>> iterator = database.entrySet().iterator();
        
            while (iterator.hasNext() && count < database.size()){
                
                Map.Entry<Integer, Student> studentInfo = iterator.next();
                System.out.println(studentInfo.getValue());

                out.write(studentInfo.getValue() + "\n\n");
                count ++;
            } 
            out.close();
        }catch (IOException ex) {
            Logger.getLogger(CMIS242Project4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //clears all text entry fields
    private void clearEntryValues() {
        textID.setText("");
        textName.setText("");
        textMajor.setText("");
        comboSelection.setSelectedItem("");
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CMIS242Project4().setVisible(true);
            }
        });
    }//end main
    
}
