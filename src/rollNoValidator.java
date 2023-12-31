
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author visha
 */
public class rollNoValidator {
    
    public rollNoValidator(){}
    
    boolean checkRollNo(String rollNo, String branch){
        // Check if the roll number is empty
        if(rollNo.length()==0){
            JOptionPane.showMessageDialog(null, "Roll number cannot be empty.", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Check if the roll number length is less than or equal to 2 for selected branch
        if(rollNo.length()<=2){
            JOptionPane.showMessageDialog(null, "Invalid roll number for selected branch.", "Warning!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Check roll number format based on the selected branch
        
        // For Mechanical branch, check if roll number starts with "ME" and the third character is a digit
        if(branch.compareTo("Mechanical")==0){
            if(!rollNo.startsWith("ME") || rollNo.charAt(2)<'0' || rollNo.charAt(2)>'9'){
                JOptionPane.showMessageDialog(null, "Invalid roll number for selected branch.", "Warning!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        // For Civil branch, check if roll number starts with "CE" and the third character is a digit
        if(branch.compareTo("Civil")==0){
            if(!rollNo.startsWith("CE") || rollNo.charAt(2)<'0' || rollNo.charAt(2)>'9'){
                JOptionPane.showMessageDialog(null, "Invalid roll number for selected branch.", "Warning!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        // For Civil branch, check if roll number starts with "CE" and the third character is a digit
        if(branch.compareTo("Electronics")==0){
            if(!rollNo.startsWith("EE") || rollNo.charAt(2)<'0' || rollNo.charAt(2)>'9'){
                JOptionPane.showMessageDialog(null, "Invalid roll number for selected branch.", "Warning!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        // For Computer Science branch, check if roll number starts with "CSE" and the fourth character is a digit
        if(branch.compareTo("Computer Science")==0){
            if(rollNo.length()<=3 || !rollNo.startsWith("CSE") || rollNo.charAt(3)<'0' || rollNo.charAt(3)>'9'){
                JOptionPane.showMessageDialog(null, "Invalid roll number for selected branch.", "Warning!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        
        // If all checks pass, return true
        return true;
    }
}
