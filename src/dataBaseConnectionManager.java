
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Class responsible for managing the connection to the database and performing various operations.
 */
public class dataBaseConnectionManager {
    index i; // Reference to the index class
    Statement st; // Database statement object
    
/**
* Constructor to initialize the class with the index reference and establish a database connection.
*/
    public dataBaseConnectionManager(index index){
        i = index;
        try{
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srms", "root", "Qwerty@2109");
            
            st = con.createStatement();
        }catch(Exception e){
            // Show an error message if the connection fails
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
/**
* Checks if a student is registered and if their result is updated.
*/
    boolean isStudentRegistered(String roll){
        try{
            // Execute query to check if student is registered
            ResultSet rs = st.executeQuery("SELECT * FROM students WHERE rollno = '"+roll+"'");
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            // Show an error message if an exception occurs
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    boolean isResultUpdated(String roll){
        try{
           // Execute query to check if the result of the given student is updated
                ResultSet rs = st.executeQuery("SELECT * FROM result WHERE rollno = '"+roll+"'");
                // Show error if the result is not updated
                if(rs.next()){
                    return true;
                }
                return false;
        }
        catch(Exception e){
            // Show an error message if an exception occurs
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
/**
* Retrieves and displays the result of a given student.
*/
    void showStudentResult(String roll){
        try{
            // Execute query to get information of given roll no from students and result table from the database
            ResultSet rs = st.executeQuery("SELECT * FROM students INNER JOIN result WHERE students.rollno = '"+roll+"'"
                    + "AND result.rollno='"+roll+"'");
            if(rs.next()){
                // Update content in the content panel
                i.contentPanel_studentResult_rollNo.setText(roll);
                i.contentPanel_studentResult_course.setText(rs.getString(2));
                i.contentPanel_studentResult_branch.setText(rs.getString(3));
                i.contentPanel_studentResult_name.setText(rs.getString(4));
                i.contentPanel_studentResult_gender.setText(rs.getString(5));
                i.contentPanel_studentResult_fatherName.setText(rs.getString(6));
                
                i.contentPanel_studentResult_physics.setText(rs.getString(8));
                i.contentPanel_studentResult_maths.setText(rs.getString(9));
                i.contentPanel_studentResult_em.setText(rs.getString(10));
                i.contentPanel_studentResult_dbms.setText(rs.getString(11));
                i.contentPanel_studentResult_os.setText(rs.getString(12));
                
                // Gets total marks
                int totalMarks = Integer.parseInt(i.contentPanel_studentResult_physics.getText());
                totalMarks += Integer.parseInt(i.contentPanel_studentResult_maths.getText());
                totalMarks += Integer.parseInt(i.contentPanel_studentResult_em.getText());
                totalMarks += Integer.parseInt(i.contentPanel_studentResult_dbms.getText());
                totalMarks += Integer.parseInt(i.contentPanel_studentResult_os.getText());
                
                i.contentPanel_studentResult_totalMarksObtained.setText(String.valueOf(totalMarks));
                
                // Pass/fail condition
                if(Integer.parseInt(i.contentPanel_studentResult_physics.getText())>=30 &&
                        Integer.parseInt(i.contentPanel_studentResult_maths.getText())>=30 &&
                        Integer.parseInt(i.contentPanel_studentResult_em.getText())>=30 &&
                        Integer.parseInt(i.contentPanel_studentResult_dbms.getText())>=30 &&
                        Integer.parseInt(i.contentPanel_studentResult_os.getText())>=30){
                    i.contentPanel_studentResult_passFail.setText("PASS");
                    i.contentPanel_studentResult_passFail.setBackground(Color.green);
                }
                else{
                    i.contentPanel_studentResult_passFail.setText("FAIL");
                    i.contentPanel_studentResult_passFail.setBackground(Color.red);
                }
            }
        }
        catch(Exception e){
            // Show an error message if an exception occurs
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
/**
* Checks if entered credentials are correct
*/
    boolean checkCredentials(String username, String password){
        try{
            
            // Execute a query to select the row with the specified username from the 'credentials' table
            ResultSet rs = st.executeQuery("SELECT * FROM credentials WHERE username='"+username+"'");
            
            // Check if the entered username exists in the database
            if(rs.next()){
                // Compare entered values with the values from the result set
                if(username.compareTo(rs.getString(1))==0 && password.compareTo(rs.getString(2))==0){
                    // Return true if the credentials match
                    return true;
                }
                else{
                    // Show an error message if the entered credentials are incorrect
                    JOptionPane.showMessageDialog(null, "Incorrect username or password!", "Error!", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else{
                // Show an error message if the entered username is not found in the database
                JOptionPane.showMessageDialog(null, "Incorrect username or password!", "Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }catch(Exception e){
            // Show an error message if an exception occurs during the database operation
            JOptionPane.showMessageDialog(null, e.toString());
        }
        // Return true by default; this should not be reached if there's an error in the database operation
        return true;
    }
    
/**
 * Adds a new student to the database with the provided information.
 */
    void addNewStudent(){
        // Extract student information from the content panel
        String rollNo = i.contentPanel_addNewStudent_rollNo.getText();
        String course = i.contentPanel_addNewStudent_course.getSelectedItem().toString();
        String branch = i.contentPanel_addNewStudent_branch.getSelectedItem().toString();
        String studentName = i.contentPanel_addNewStudent_name.getText();
        String fatherName = i.contentPanel_addNewStudent_fatherName.getText();
        String gender = i.contentPanel_addNewStudent_gender.getSelectedItem().toString();
                
        try{
            // Execute a query to insert a new student into the 'students' table
            st.executeUpdate("insert into students(rollno,course,branch,name,gender,fathername) "
                    + "values('"+rollNo+"','"+course+"','"+branch+"','"+studentName+"','"+gender+"','"+fatherName+"')");
            // Show a success message if the operation is successful
            JOptionPane.showMessageDialog(null, "Data Saved Successfully.", "", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            // Show an error message if an exception occurs during the database operation
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
    }

/**
 * Adds the result of a student to the database with the provided information.
 * @return True if the result is successfully added, false otherwise.
 */
    boolean addResult(){
        // Extract result information from the content panel
        String rollNo = i.contentPanel_addResult_rollNo.getText();
        String physics = i.contentPanel_addResult_physics.getText();
        String maths = i.contentPanel_addResult_maths.getText();
        String em = i.contentPanel_addResult_em.getText();
        String dbms = i.contentPanel_addResult_dbms.getText();
        String os = i.contentPanel_addResult_os.getText();
        
        // Check if the roll number is registered
        if(!isStudentRegistered(rollNo)){
            // If the roll number is not registered, show an error message
            JOptionPane.showMessageDialog(null, "No such roll number is registered.", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Check if any roll number field is empty
        if(physics.length()==0 || maths.length()==0 || em.length()==0 || dbms.length()==0 || os.length()==0){
            // If found empty, show an error
            JOptionPane.showMessageDialog(null, "Marks field cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Check if entered value for marks field is greater than 100
        if(Integer.parseInt(physics)>100 || Integer.parseInt(maths)>100 || Integer.parseInt(em)>100 || Integer.parseInt(dbms)>100 || Integer.parseInt(os)>100){
            //If found, then show error
            JOptionPane.showMessageDialog(null, "Marks can not be above 100.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try{
            // If the roll number is registered, execute a query to update the 'result' table
            st.executeUpdate("INSERT INTO RESULT (rollno,physics,maths,em,dbms,os) VALUES ('"+rollNo+"','"+physics+"','"+maths+"',"
                + "'"+em+"','"+dbms+"','"+os+"')");
            // Show a success message if the operation is successful
            JOptionPane.showMessageDialog(null, "Result saved successfully.", "", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        catch(Exception e){
            // Show an error message if an exception occurs during the database operation
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        // Return false by default if there's an error in the database operation
        return false;
    }
    
/**
 * Populates the table in the content panel with all registered students' information.
 */
    void populateAllRegisteredStudents(){
//        JTable table = i.contentPanel_registeredStudents_table;
        try{
            // Execute query to retrieve all values from the 'students' table
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            // Add values from the result set to the table in the content panel
            i.contentPanel_registeredStudents_table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            // Show an error message if an exception occurs during the database operation
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
 
/**
 * Populates the table in the content panel with the result of all students.
 */
    void populateAllStudentsResult(){
        try{
            // Get the result of all students from the 'result' table
            ResultSet rs = st.executeQuery("SELECT * FROM result");

            // Add values from the result set to the table in the content panel
            i.contentPanel_allStudentsResult_table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            // Show an error message if an exception occurs during the database operation
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
