/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2.a1;

import java.sql.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



/**
 *
 * @author D
 */
public class DB {
    
    private Statement stmt;
    private Connection connection;

    private void initializeDB() {
        try {
            //load the appropriate driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            String databaseName = "mydb";
            String databaseUserName = "user";
            String databasePassword = "Password1";

            // Establish a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, databaseUserName, databasePassword);
            System.out.println("Database connected");

            // Create a statement
            stmt = connection.createStatement();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
     public void showEmployee(TextField employeeId_label, Label status_label) {
        
         initializeDB();
        String employeeId = employeeId_label.getText();
        try {
            String queryString = "select firstName, lastName, email from employee where employeeId = '" + employeeId + "' ";
            ResultSet rset = stmt.executeQuery(queryString);
           
            if (rset.next()) {
                 
                String firstName = rset.getString(1);
                String lastName = rset.getString(2);
                String email = rset.getString(3);
                
                status_label.setText(firstName + " " + lastName + " : " + email);
            
            } else {
                status_label.setText("Not found");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
       closeDB(); 
    }
    
    public void creatCustomer(TextField tf1, TextField tf2, TextField tf3, TextField tf4, TextField tf6, TextField tf5, TextField tf7, TextField tf8) {
     
        initializeDB();
        String firstName = tf1.getText();
        String lastName = tf2.getText();
        String email = tf3.getText();
        String address = tf4.getText();
        String city = tf6.getText();
        String State = tf5.getText();
        String zipCode = tf7.getText();
        String Phone = tf8.getText();
                
       System.out.println(firstName + " "+ lastName + " " + email + " " + address + " " + city + " " + State + " " + zipCode + " " + Phone);
        
       try {
       String query = "INSERT into customer (firstName, lastName, email, address, city, State, zipCode, phoneNumber) values (?, ?, ?, ?, ?, ?, ?, ?)";
       PreparedStatement ps = connection.prepareStatement(query);
       ps.setString(1, firstName);
       ps.setString(2, lastName);
       ps.setString(3, email);
       ps.setString(4, address);
       ps.setString(5, city);
       ps.setString(6, State);
       ps.setString(7, zipCode);
       ps.setString(8, Phone);
       
       ps.execute();
       
       }catch (Exception ex) {
           ex.printStackTrace();
           
       }
                        
        closeDB(); 
    }         
     
    //return the list of employees based on the data from the database.
    public ObservableList<Employee> getEmployeeList() {
        initializeDB();
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        try {
            String queryString = "select employeeId, firstName, lastName, email from employee order by employeeId ";
            ResultSet rset = stmt.executeQuery(queryString);
            while (rset.next()) {
                int employeeId = rset.getInt(1);
                String firstName = rset.getString(2);
                String lastName = rset.getString(3);
                String email = rset.getString(4);
                employees.add(new Employee(employeeId, firstName, lastName, email));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        closeDB();
        return employees;
    }

    public void createOrder (TextField customerID_tf,
         TextField productID_tf,
        TextField quantityID_tf,
        String employeeID,
        Label status_label
            
            ){
        initializeDB();
        String customerID = customerID_tf.getText();
        String productID = productID_tf.getText();
        String quantityID = quantityID_tf.getText();
        
        try{
            String queryString = "select count(*) as customerExists from customer where customerID = '" + customerID + "' ";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            System.out.println(queryString);
            ResultSet rset = stmt.executeQuery(queryString);      
            int customerExistsCount=0;
            if (rset.next()) {
                customerExistsCount = rset.getInt(1);
                if (customerExistsCount == 0) {
                    status_label.setText("Customer not found, try another");
                return;
                
                }
                
            }
            
            queryString = "select productName, quantityRemaining from product where productID = '" + productID + "' ";
            stmt = connection.prepareStatement(queryString);
            System.out.println(queryString);
            rset = stmt.executeQuery(queryString);
            int availableQuantity=0;
            String productName="";
            if (rset.next()) {
                productName = rset.getString(1);
                availableQuantity = rset.getInt(2);
                if (availableQuantity == 0){
                    status_label.setText("product "+productName+" has no quantity available");
                    return;
                }
                
                if (availableQuantity < Integer.parseInt(quantityID)){
                status_label.setText("product "+productName+" only has "+availableQuantity+" available. Please enter in a smaller number");
                 return;
            }
            
        }
            else{
                 status_label.setText("Product not found, try another");
                 return;
    }    
            
            connection.setAutoCommit(false);
            
            String query = "insert into `orders` (customerID, employeeiID, orederDate) values (?,?, now())";
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, customerID);
            ps.setString(2, employeeID);
            System.out.println(query);
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            int orderID = 0;
            if (rs.next()){
                orderID = rs.getInt(1);
            }
            
            
            query = "insert into orderdetails (orederId, productID, quantity) values (?,?,?) ";
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderID);
            ps.setString(2, productID);
            ps.setString(3, quantityID);
            System.out.println(query);
            ps.execute();
            
            query = "update product set quantityRemaining = quantityRemaining - ? where productID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(quantityID));
            ps.setString(2, productID);
            System.out.println(query);
            ps.execute();
            
             connection.commit();
            connection.setAutoCommit(true);
            
            status_label.setText("Order Successfully Completed");
            customerID_tf.clear();
            productID_tf.clear();
            quantityID_tf.clear();
        }
            catch (Exception ex){
            
            ex.printStackTrace();
            status_label.setText("Error in creating your order.");
            
            
        }
            closeDB();
            
    }
     //Displaying Customer Information
    public void displayCustomer(TextField tf1,
               TableView orderTable,
               Label label1,
               Label label2,
               Label label3){
        initializeDB();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String customerID = tf1.getText();
        
        try {          
            //get data from order table
            String queryString = "select orderId, orederDate from `orders` where customerID = "+customerID;
            PreparedStatement stmt = connection.prepareStatement(queryString);
            System.out.println(queryString);
            ResultSet rset = stmt.executeQuery(queryString);
            
            while (rset.next()) {
                int orderID = rset.getInt(1);
                String orderDate = rset.getString(2);
                orders.add(new Order(orderID, orderDate));
            }
            orderTable.setItems(orders);
            //Display Customer Information
            queryString = "select firstName, lastName, email from customer where customerID = "+customerID;
            stmt = connection.prepareStatement(queryString);
            System.out.println(queryString);
            rset = stmt.executeQuery(queryString);
            //check the first record to set the customer as the customerID shoudl be unique.
            if (rset.next()) {
                String firstName = rset.getString(1);
                String lastName = rset.getString(2);
                String email = rset.getString(3);
                label1.setText("First Name: "+ firstName);
                label2.setText("Last Name: "+ lastName);
                label3.setText("Email: "+ email );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        closeDB();
    }
            
            
            
    
    private void closeDB() {
        try {
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}