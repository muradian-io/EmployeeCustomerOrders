/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2.a1;

//Import list
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MyApp extends Application {

//scenes for the application
    Scene ea_scene, ac_scene, ao_scene, el_scene, cr_scene, or_scene, vp_scene;
    String employeeID;

    @Override
    public void start(Stage primaryStage) {
        employeeID="";
//Setting the title of the application
        primaryStage.setTitle("Customer Order Entry");

//Employee Account Scene Setup
        createEmployeeAccountScene(primaryStage);
    
//Add Customer  Scene Setup
        createAddCustomerScene(primaryStage);

//Add Order  Scene Setup
         createAddOrderScene(primaryStage);

//Customer Report  Scene Setup
        createEmployeeListReportScene(primaryStage);
         
//Customer Report  Scene Setup
        createCustomerReportScene(primaryStage);
        

//Order Report  Scene Setup
        createOrderReportScene(primaryStage);
        
//Product Report  Scene Setup
        createProductReportScene(primaryStage);
        

        primaryStage.setScene(ea_scene);
        primaryStage.show();
    }

    //Menu Setup
        public MenuBar addMenu(Stage primaryStage) {
        Menu menu1 = new Menu("Application");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu1);
        MenuItem menu11 = new MenuItem("Employee Access");
        MenuItem menu12 = new MenuItem("Add Customer");
        MenuItem menu13 = new MenuItem("Add Order");

        Menu menu2 = new Menu("Report");
        menuBar.getMenus().add(menu2);
        MenuItem menu21 = new MenuItem("Employee List Report");
        MenuItem menu22 = new MenuItem("Customer Report");
        MenuItem menu23 = new MenuItem("Order Report");
        MenuItem menu24 = new MenuItem("Product Report");

        menu1.getItems().addAll(menu11, menu12, menu13);
        menu2.getItems().addAll(menu21, menu22, menu23, menu24);

        menu11.setOnAction(e -> primaryStage.setScene(ea_scene));
        menu12.setOnAction(e -> primaryStage.setScene(ac_scene));
        menu13.setOnAction(e -> primaryStage.setScene(ao_scene));
        menu21.setOnAction(e -> primaryStage.setScene(el_scene));
        menu22.setOnAction(e -> primaryStage.setScene(cr_scene));
        menu23.setOnAction(e -> primaryStage.setScene(or_scene));
        menu24.setOnAction(e -> primaryStage.setScene(vp_scene));

        return menuBar;
    }
    
/****************************************
 * Methods to setup each of the screens
 * 
*****************************************/

    //Employee Account Scene Setup
    public void createEmployeeAccountScene(Stage primaryStage){
          Label label1; Label label2; Label status;
    TextField tf1; 
    Button button;
    
       label1 = new Label();
        label1.setText("Employee Account");
        
        label2 = new Label();
        label2.setText("Employee ID");
        
        tf1 = new TextField ();
        employeeID = tf1.getText();
        
        // create button for the Add New Customer 
        button = new Button();
        // to add text to the button
        button.setText("Sign in");
        // call the layout and locate the button
        
        status = new Label("");

        button.setOnAction(e -> employeeSignIn(tf1, status));

        VBox ea_layout = new VBox(20);
        ea_layout.getChildren().addAll(addMenu(primaryStage), label1, label2, tf1, button, status);
        ea_scene = new Scene(ea_layout, 800, 600);
        
        ea_scene.getStylesheets().add("u2/a1/StyleSheet.css");
        ea_layout.getStyleClass().add("vbox");
    }
    
    //Add Customer  Scene Setup
    public void createAddCustomerScene(Stage primaryStage){
         // create label for the Add Customer
         Label label1 = new Label();
        label1.setText("Add Customer");
       
        
        // create labe2 for the First Name
        Label label2 = new Label();
        label2.setText("First Name");
        // create Text Field for the Labe2 (First Name)
        TextField tf1 = new TextField ();
        
        // create labe3 for the Last Name
        Label label3 = new Label();
        label3.setText("Last Name");
        // create Text Field for the Labe3 (Last Name)
        TextField tf2 = new TextField ();
        
        // create labe4 for the Email
        Label label4 = new Label();
        label4.setText("Email");
        // create Text Field for the Labe4 (Email)
        TextField tf3 = new TextField ();
        
        // create labe5 for the Address
        Label label5 = new Label();
        label5.setText("Address");
        // create Text Field for the Labe5 (Address)
        TextField tf4 = new TextField ();
        
       //create labe6 for the State
       Label label6 = new Label();
       label6.setText("State");
       //create Text Field for the Labe6 (State)
       TextField tf5 = new TextField ();
        
        // create labe7 for the City
        Label label7 = new Label();
        label7.setText("City");
        //create Text Field for the Labe7 (City)
        TextField tf6 = new TextField ();
        
        // create labe8 for the Zip Code
        Label label8 = new Label();
        label8.setText("Zip Code");
        //create Text Field for the Labe8 (Zip Code)
        TextField tf7 = new TextField ();
        
        // create labe9 for the Phone #
        Label label9 = new Label();
        label9.setText("Phone #");
        //create Text Field for the Labe9 (Phone #)
        TextField tf8 = new TextField ();
        
        
        // create button for the Add New Customer 
        Button button = new Button();
        // to add text to the button
        button.setText("Add New Customer");
        button.setOnAction(a -> addCustomer(tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8));
        // call the layout and locate the button
        
      

        VBox ac_layout = new VBox(20);
        ac_layout.setSpacing(0);
        ac_layout.getChildren().addAll(addMenu(primaryStage), 
                label1, 
                label2, 
                tf1, 
                label3, 
                tf2, 
                label4, 
                tf3, 
                label5,
                tf4,
                label6,
                tf5,
                label7,
                tf6,
                label8,
                tf7,
                label9,
                tf8,
                button
        );
        ac_scene = new Scene(ac_layout, 800, 1200);
        
        ac_scene.getStylesheets().add("u2/a1/StyleSheet.css");
        ac_layout.getStyleClass().add("vbox");
    }
    
//Add Order  Scene Setup
    public void createAddOrderScene(Stage primaryStage){
         Label label1; Label label2; Label label3; Label label4; Label label5;
    TextField tf1; TextField tf2; TextField tf3;TextField tf5;
    Button button;
        // create label for the Employee ID
        
         label5 = new Label();
        label5.setText("Employee ID");
        tf5 = new TextField ();

        // create label for the Place Order
        
         label1 = new Label();
        label1.setText("Plce Order");
        
        // create labe2 for the Customer name
        
         label2 = new Label();
        label2.setText("Customer ID");
        
        // create Text Field for the Labe2 (Customer name)  

         tf1 = new TextField ();
        
        // create labe3 for the Product name
        
         label3 = new Label();
        label3.setText("Product ID");
        
        // create Text Field for the Labe3 (Product name) 

         tf2 = new TextField ();
        
        // create labe4 for the Quantity
        
         label4 = new Label();
        label4.setText("Quantity");
        
        // create Text Field for the Labe4 (Quantity)

         tf3 = new TextField ();
        
        
        // create button for the Submit 
        
        button = new Button();
        // to add text to the button
        button.setText("Place Order");
        // call the layout and locate the button
        
       Label status = new Label("");
        button.setOnAction(e -> addOrder(tf1, tf2, tf3,status));
        
        VBox ad_layout = new VBox(20);
        ad_layout.setSpacing(5);
        ad_layout.getChildren().addAll(addMenu(primaryStage), label1, 
                label2,
                tf1,
                label3,
                tf2,
                label4,
                tf3,
                button,
                status);
        ao_scene = new Scene(ad_layout, 800, 500);
        ao_scene.getStylesheets().add("u2/a1/StyleSheet.css");
        ad_layout.getStyleClass().add("vbox");
    }
    
    
    public void createEmployeeListReportScene(Stage primaryStage){
 
        Label el_label = new Label("Employee List Screen");

//create the table
        TableView<Employee> employeeTable;

//create each individual column and map them to the corresponding attribute in the Employee class
        TableColumn<Employee,Integer> employeeIdColumn = new TableColumn<>("Employee ID");
        employeeIdColumn.setMinWidth(100);
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee,String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setMinWidth(200);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee,String> lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setMinWidth(200);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employee,String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(300);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

//set the data of the table and add in each of the columns
        employeeTable = new TableView();
        employeeTable.setItems(getEmployeeList());
        employeeTable.getColumns().addAll(employeeIdColumn,firstNameColumn,lastNameColumn,emailColumn);
        
        VBox el_layout = new VBox(20);
        el_layout.getChildren().addAll(addMenu(primaryStage), el_label, employeeTable);
        el_scene = new Scene(el_layout, 800, 500); 
        el_scene.getStylesheets().add("u2/a1/StyleSheet.css");
        el_layout.getStyleClass().add("vbox");
    }
// Creates a list to set to the table
    public ObservableList<Employee> getEmployeeList(){
	ObservableList<Employee> employees = FXCollections.observableArrayList();
        DB myDB = new DB();
        employees = myDB.getEmployeeList();
        return employees;
}
    
    //Customer Report  Scene Setup
     public void createCustomerReportScene(Stage primaryStage){
    Label label1 = new Label("View Customer Screen");
       
        Label label2 = new Label("Customer ID");
        TextField tf1 = new TextField();
        tf1.setPromptText("Enter Customer ID");
        
        //create the table
        TableView<Order> tabel1;

//create each individual column and map them to the corresponding attribute in the Employee class
        TableColumn<Order,Integer> orderIdColumn = new TableColumn<>("Order ID");
        orderIdColumn.setMinWidth(100);
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Order,String> orderDateColumn = new TableColumn<>("Order Date");
        orderDateColumn.setMinWidth(200);
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        
        
        //set the data of the table and add in each of the columns
        tabel1 = new TableView();
        tabel1.getColumns().addAll(orderIdColumn,orderDateColumn);
        
        Label firstName = new Label();
        Label lastName = new Label();
        Label email = new Label();
        
         Button cr_button = new Button("View Customer");
        cr_button.setOnAction(e -> displayCustomer(tf1,
                tabel1,
                firstName,
                lastName,
                email));
        
        VBox cr_layout = new VBox(20);
        cr_layout.setSpacing(0);
        cr_layout.getChildren().addAll(addMenu(primaryStage), 
                label1, 
                label2,
                tf1,
                cr_button,
                firstName,
                lastName,
                email,
                tabel1
                );
        cr_scene = new Scene(cr_layout, 800, 500);
        cr_scene.getStylesheets().add("u2/a1/StyleSheet.css");
        cr_layout.getStyleClass().add("vbox");
    }
    
    
    //Order Report  Scene Setup
    public void createOrderReportScene(Stage primaryStage){
    Label or_label = new Label("View Order Screen");
        Button or_button = new Button("Example Button");
        or_button.setOnAction(e -> primaryStage.setScene(or_scene));
        
        VBox or_layout = new VBox(20);
        or_layout.getChildren().addAll(addMenu(primaryStage), or_label, or_button);
        or_scene = new Scene(or_layout, 800, 500);
        or_scene.getStylesheets().add("u2/a1/StyleSheet.css");
        or_layout.getStyleClass().add("vbox");
    }
    
    //Product Report  Scene Setup
    public void createProductReportScene(Stage primaryStage){
    Label vp_label = new Label("View Product Screen");
        Button vp_button = new Button("Example Button");
        vp_button.setOnAction(e -> primaryStage.setScene(vp_scene));
        
        VBox vp_layout = new VBox(20);
        vp_layout.getChildren().addAll(addMenu(primaryStage), vp_label, vp_button);
        vp_scene = new Scene(vp_layout, 800, 500);
        vp_scene.getStylesheets().add("u2/a1/StyleSheet.css");
        vp_layout.getStyleClass().add("vbox");
    }
    
 /****************************************
 * Added Functionality
 * 
*****************************************/
    
    //Signing in as the employee
     public void employeeSignIn(TextField employeeId_tf, Label status_label) {
         employeeID = employeeId_tf.getText();
        DB myDB = new DB();
        myDB.showEmployee(employeeId_tf, status_label);
    }
    
    //adding a customer
    public void addCustomer(TextField tf1, TextField tf2, TextField tf3, TextField tf4, TextField tf6, TextField tf5, TextField tf7, TextField tf8) {
        DB myDB = new DB();
        myDB.creatCustomer( tf1,  tf2,  tf3,  tf4,  tf6,  tf5,  tf7, tf8);
        
        
        Alert alert = new Alert( Alert.AlertType.INFORMATION, "Added Customer?", ButtonType.OK);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.OK)
            
            System.out.println("Need Action");
        
        
    }
    
    
     //adding an order
     public void addOrder(TextField customerID,
         TextField productID,
        TextField quantityID,
                Label status_label) {
        
    
        // add customer content with he DB
        
        DB myDB = new DB();
        if (employeeID.length() == 0) {
        status_label.setText("Employee must sign in first.");
    
    }
    
    else{
            myDB.createOrder(customerID,
                             productID,
                             quantityID,
                             employeeID,
                              status_label);
}

    }
     
     //displaying a customer
      public void displayCustomer(TextField tf1,
               TableView tabel1,
               Label label1,
               Label label2,
               Label label3){
          DB myDB = new DB();
           if (tf1.getText().length() == 0){
               label1.setText("Please enter in the Customer ID");
     
           }
           else{
                myDB.displayCustomer(tf1,
                      tabel1,
                      label1,
                      label2,
                      label3);
           }
          
      }
        


    public static void main(String[] args) {
        launch(args);
    }

}
