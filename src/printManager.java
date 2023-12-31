import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import javax.swing.JTable;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author visha
 */
public class printManager {
    index i;
    public printManager(index index){
        i=index;
    }
    
    void studentResult(){
        // Initialize path variable
        String path = "";
        
        // Create a file chooser dialog
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
        
        // Show the save dialog and get the user's choice
        int x = j.showSaveDialog(i);
        
        // Check if the user chose to save
        if(x == JFileChooser.APPROVE_OPTION){
            // Get the selected file path
            path = j.getSelectedFile().getPath();
        }
        else{
             // Show an error message if something went wrong and return from the method
            JOptionPane.showMessageDialog(null, "Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create a new document for PDF
        Document doc = new Document();
        try{
            // Initialize PDF writer with the specified file path
            PdfWriter.getInstance(doc, new FileOutputStream(path + "\\"+i.contentPanel_studentResult_rollNo.getText()+"Result.pdf"));
            
            doc.open();
            
             // Add student information to the document
            doc.add(new Paragraph("Roll Number : " + i.contentPanel_studentResult_rollNo.getText()));
            doc.add(new Paragraph("Name : " + i.contentPanel_studentResult_name.getText()));
            doc.add(new Paragraph("Course : " + i.contentPanel_studentResult_course.getText()));
            doc.add(new Paragraph("Branch : " + i.contentPanel_studentResult_branch.getText()));
            doc.add(new Paragraph("Father's Name : " + i.contentPanel_studentResult_fatherName.getText()));
            doc.add(new Paragraph("Gender : " + i.contentPanel_studentResult_gender.getText()));
            doc.add(new Paragraph("\n"));
            
            // Create a table for subjects and marks
            PdfPTable table = new PdfPTable(4); 
            table.addCell("Subject");
            table.addCell("Total Marks");
            table.addCell("Passing Marks");
            table.addCell("Marks Obtained");
            
            // Add rows for each subject with corresponding marks
            table.addCell("Physics");
            table.addCell("100");
            table.addCell("30");
            table.addCell(i.contentPanel_studentResult_physics.getText());
            table.addCell("Maths");
            table.addCell("100");
            table.addCell("30");
            table.addCell(i.contentPanel_studentResult_maths.getText());
            table.addCell("EM");
            table.addCell("100");
            table.addCell("30");
            table.addCell(i.contentPanel_studentResult_em.getText());
            table.addCell("DBMS");
            table.addCell("100");
            table.addCell("30");
            table.addCell(i.contentPanel_studentResult_dbms.getText());
            table.addCell("OS");
            table.addCell("100");
            table.addCell("30");
            table.addCell(i.contentPanel_studentResult_os.getText());
            
            // Add the table to the document
            doc.add(table);
            
            // Add pass/fail status to the document
            doc.add(new Paragraph(i.contentPanel_studentResult_passFail.getText()));
            
            // Show a success message and close the document
            JOptionPane.showMessageDialog(null, "Pdf saved. ","", JOptionPane.INFORMATION_MESSAGE);
            doc.close();
            
        }catch(Exception e){
            // Show an error message if an exception occurs
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void registeredStudents(){
        // Get reference to the JTable containing registered students' information
        JTable registeredStudentsTable = i.contentPanel_registeredStudents_table;
        
        // Initialize path variable for file save location
        String path = "";
        
        // Create a file chooser dialog
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
        
        // Show the save dialog and get the user's choice
        int x = j.showSaveDialog(i);
        
        // Check if the user chose to save
        if(x == JFileChooser.APPROVE_OPTION){
            // Get the selected file path
            path = j.getSelectedFile().getPath();
        }
        else{
            // Show an error message if something went wrong and return from the method
            JOptionPane.showMessageDialog(null, "Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a new document for PDF
        Document doc = new Document();
        try{
            // Initialize PDF writer with the specified file path
            PdfWriter.getInstance(doc, new FileOutputStream(path + "\\All Students.pdf"));

            doc.open();
            
            // Add a title to the document
            doc.add(new Paragraph("All Registered Students. \n\n"));
         
            // Create a table with 6 columns for student information
            PdfPTable table = new PdfPTable(6);
  
            // Add column headings to the table
            table.addCell("Roll No.");
            table.addCell("Name");
            table.addCell("Course");
            table.addCell("Branch");
            table.addCell("Father's Name");
            table.addCell("Gender");

            // Iterate through the rows of the JTable and add values to the table
            for(int i=0; i<registeredStudentsTable.getRowCount(); i++){

                String roll = registeredStudentsTable.getValueAt(i, 0).toString();
                String course = registeredStudentsTable.getValueAt(i, 1).toString();
                String branch = registeredStudentsTable.getValueAt(i, 2).toString();
                String name = registeredStudentsTable.getValueAt(i, 3).toString();
                String gender = registeredStudentsTable.getValueAt(i, 4).toString();
                String father = registeredStudentsTable.getValueAt(i, 5).toString();

                // Add values to the table
                table.addCell(roll);
                table.addCell(name);
                table.addCell(course);
                table.addCell(branch);
                table.addCell(father);
                table.addCell(gender);

            }
            
            // Add the table to the document
            doc.add(table);
            
            // Show a success message
            JOptionPane.showMessageDialog(null, "Pdf saved. ","", JOptionPane.INFORMATION_MESSAGE);

            doc.close();

        }catch(Exception e){
            // Show an error message if an exception occurs
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void allStudentsResult(){
        // Get reference to the JTable containing all students' result information
        JTable allStudentsResult_table = i.contentPanel_allStudentsResult_table;
        
        // Get the path where the file is to be stored
        String path = "";
        
        // Create a file chooser dialog
        JFileChooser j = new JFileChooser();
        
        // Show the save dialog and get the user's choice
        j.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
        
        // Check if the user chose to save
        int x = j.showSaveDialog(i);
        // Check if the user chose to save
        if(x == JFileChooser.APPROVE_OPTION){
            // Show an error message if something went wrong and return from the method
            path = j.getSelectedFile().getPath();
        }
        else{
            // Show an error message if something went wrong and return from the method
            JOptionPane.showMessageDialog(null, "Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create a new document for PDF
        Document doc = new Document();
        try{
            // Initialize PDF writer with the specified file path
            PdfWriter.getInstance(doc, new FileOutputStream(path + "\\Result.pdf"));
            
            doc.open();

            // Create a table with 6 columns for student result information
            PdfPTable table = new PdfPTable(6);
            
            // Add column headings to the table
            table.addCell("Roll No.");
            table.addCell("Physics");
            table.addCell("Maths");
            table.addCell("EM");
            table.addCell("DBMS");
            table.addCell("OS");
            
            // Add values to the table
            for(int i=0; i<allStudentsResult_table.getRowCount(); i++){
                
                String roll = allStudentsResult_table.getValueAt(i, 0).toString();
                String phy = allStudentsResult_table.getValueAt(i, 1).toString();
                String mat = allStudentsResult_table.getValueAt(i, 2).toString();
                String em = allStudentsResult_table.getValueAt(i, 3).toString();
                String dbms = allStudentsResult_table.getValueAt(i, 4).toString();
                String os = allStudentsResult_table.getValueAt(i, 5).toString();
                
                table.addCell(roll);
                table.addCell(phy);
                table.addCell(mat);
                table.addCell(em);
                table.addCell(dbms);
                table.addCell(os);
                
            }
            
            // Add the table to the document
            doc.add(table);
            
             // Show a success message
            JOptionPane.showMessageDialog(null, "Pdf saved. ","", JOptionPane.INFORMATION_MESSAGE);

            doc.close();
            
        }catch(Exception e){
            // Show an error message if an exception occurs
            JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
