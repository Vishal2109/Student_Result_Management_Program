# Student-Result-Management-Program
The Student Result Management System is a desktop application that provides a user-friendly interface for administrators to manage student information, add new students, record and manage their results. It also allows students to securely log in and check and print their results.

This Java Swing GUI application is designed for managing student results with separate modules for Admin and Students.

## Features

### Admin Module:

1. **Login:**
   - Admin can log in using a username and password.

2. **Add New Student:**
   - Admin can add new student details, including roll number, course, branch, name, gender, and father's name.

3. **Add Result:**
   - Admin can add results for existing students, including scores in physics, maths, EM (Engineering Mathematics), DBMS (Database Management System), and OS (Operating System).

4. **View All Students and Results:**
   - Admin can view and print a list of all registered students along with their results.

### Student Module:

1. **Result Access:**
   - Students can access their results by entering their roll number.

2. **Download Result in PDF:**
   - If the result is updated, students can view and download their results in PDF format.

## Prerequisites

- Java Development Kit (JDK)
- MySQL Server installed and configured
- MySQL Connector/J for Java
- iText library for PDF generation
- rs2xml library to display the data in a table format
- AbsoluteLayout library

### Configure MySQL Connection:
**Open MySQL Workbench or your preferred MySQL client.
Create a new connection with the following details:**

**Connection Name:** localhost

**Username:** root

**Password:** Qwerty@2109

**Verify Connection:** Test the connection to ensure it's established successfully.

## Database Setup

1. **Create 'srms' Database:**
   ```sql
   CREATE DATABASE srms;
   USE srms;
2. **Create 'students' Table:**
   ```sql
   CREATE TABLE students(
    rollno VARCHAR(15) PRIMARY KEY,
    course VARCHAR(20),
    branch VARCHAR(20),
    name VARCHAR(30),
    gender VARCHAR(6),
    fathername VARCHAR(30));
4. **Create 'result' Table:**
   ```sql
   CREATE TABLE result(
    rollno VARCHAR(20) PRIMARY KEY,
    physics VARCHAR(3),
    maths VARCHAR(3),
    em VARCHAR(3),
    dbms VARCHAR(3),
    os VARCHAR(3));
5. **Create 'credentials' Table:**
   ```sql
   CREATE TABLE credentials(
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(350));
   INSERT INTO credentials(username, password) VALUES('admin', '1234');

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/student-result-management.git
2. Compile and run the application
   ```java
   javaac index.java
   java main
3. Follow on-screen instruction to use application

**Or**
   
  Go to https://github.com/Vishal2109/Student_Result_Management_Program/releases/
  
  Download and install latest release.
  
  After installation launch the program.



