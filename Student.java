package cmis.pkg242.project.pkg4;

/**
 * 
 * @author AArgento
 * @date 5 October 2016
 * @class CMIS 242
 * @purpose 
 * 
 */

public class Student {
    
    //define required variables for class
    private String studentName;
    private String studentMajor;
    private int creditsCompleted;
    private int qualityPoints;
    
    //constructor
    public Student(String name, String major){
        this.studentName = name;
        this.studentMajor = major;
        this.qualityPoints = 0;
        this.creditsCompleted = 0;
    }//end constructor
    
    public void courseCompleted(char finalGrade, double classCredits){

        this.creditsCompleted += classCredits;   
        
        switch (finalGrade) {
            case 'A':
                qualityPoints += (classCredits * 4);
                break;
            case 'B':
                qualityPoints += (classCredits * 3);
                break; 
            case 'C':
                qualityPoints += (classCredits * 2);
                break;
            case 'D':
                qualityPoints += (classCredits * 1);
                break; 
            case 'F':
                qualityPoints += (classCredits * 0);
                break;
            default:
                break;  
        }
    }//end courseCompleted
    
    @Override
    public String toString(){
        
        double gpa = 0;
        
        if (creditsCompleted == 0){
            gpa = 4.0;
        } else {
            gpa = qualityPoints/creditsCompleted;
        }
        
        return "Name: " + studentName + "\nMajor: " + studentMajor +
                "\nGPA: " + gpa;
    }//end toString
    
}//end Student
