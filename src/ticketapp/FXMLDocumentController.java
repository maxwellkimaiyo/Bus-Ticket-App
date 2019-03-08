package ticketapp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.util.regex.*; 
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    ObservableList<String> zone = FXCollections.observableArrayList("Kisii","Kericho","Kemera","Nyamira","Mosocho","Oyugis","Kisumu");
    
    private Label label;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField days;
    @FXML
    private ComboBox<String> combo1;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private Label price;
    @FXML
    private TextArea display;
    private String discount;
    @FXML
    private Label validate1;
    @FXML
    private Label validate2;
    @FXML
    private Label validate3;
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
    combo1.setValue("Origin");
    combo1.setItems(zone);
    combo2.setValue("Destination");
    combo2.setItems(zone);
    }    
@FXML
private void showTicket(ActionEvent event) {
        if(name.getText().isEmpty() ){
              Alert box = new Alert(AlertType.INFORMATION);
              box.setTitle("Missing Field");
              box.setHeaderText(null);
              box.setContentText("Please Input Your Name");
              box.showAndWait();

        }else if(age.getText().isEmpty()){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Missing Field");
              box.setHeaderText(null);
              box.setContentText("Your Age Is Missing");
              box.showAndWait();
              

        }else if(this.days.getText().isEmpty()){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Missing Field");
              box.setHeaderText(null);
              box.setContentText("The Duration Of Your Ticket Is Missing ");
              box.showAndWait();
        
        }else if(this.price.getText().isEmpty()){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Missing Field");
              box.setHeaderText(null);
              box.setContentText("Please First Find Your Price  Viewing Ticket ");
              box.showAndWait();
        }else{
            int d = Integer.parseInt(days.getText());
            int a = Integer.parseInt(age.getText());
            int discount = 0;
              if(
                  (a<=10 || a>=50)&&(d >= 7 && d <= 59)){
                discount=40+10;
        }else if(
                  (a<=10 || a>=50)&&(d>=60)){
                discount = 40+40;
        }else if(
                  a<=10 || a>=50){
                discount = 40;
        }else if(
                d >= 7 && d <= 59){
                discount = 10;
        }else if (d>=60){
                discount =40;
        }
     display.appendText(
                "===  New Wave Buses Prepaid Ticket  ===\n\n"+
                " >Passenger Name: " +name.getText()+"\n\n"+
                " >Ticket For How Many Days: "+this.days.getText().toUpperCase()+"\n\n"+
                " >Discount Received: "+discount+"%"+"\n\n"+  
                " >Total Amount Paid: "+this.price.getText().toUpperCase()+"/="+"\n\n"+
                " >Ticket valid for 10 day(s)"+"\n\n"+
                "===  Thank you for travelling with us  ===");
            }
    }
@FXML
private void PrintTicket(ActionEvent event) {
      Document document = new Document();
      try{
          PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));
          document.open();
          int d = Integer.parseInt(days.getText());
          int a = Integer.parseInt(age.getText());
          int discount = 0;
          int low=1;
          int high=54;
          int random = (int)(Math.random()*(high-low+1))+1;
        if(
            (a<=10 || a>=50)&&(d >= 7 && d <= 59)){
             discount=40+10;
        }else if((a<=10 || a>=50)&&(d>=60)){
             discount = 40+40;
         }else if (
             a<=10 || a>=50){
             discount = 40;
        }else if (d >= 7 && d <= 59){
             discount = 10;
        }else if (d>=60){
             discount =40;
        }          
        if(display.getText().isEmpty()){
             Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Empty TextArea");
              box.setHeaderText(null);
              box.setContentText("Please First View Your Ticket Before Printig");
              box.showAndWait();
        }else{
          document.add(new Paragraph(               
                "======================New Wave Buses Prepaid Ticket====================\n\n\n\n"+
                "                        ~ Passenger Name: " +name.getText().toUpperCase()+"\n\n"+
                "                        ~ Ticket For How Many Days: "+this.days.getText().toUpperCase()+"\n\n"+
                "                        ~ Discount Received: "+discount+"%"+"\n\n"+  
                "                        ~ Total Amount Paid: "+this.price.getText().toUpperCase()+"/="+"\n\n"+
                "                        ~Your Seat Number: "+random+"."+"\n\n"+
                "                        ~ Ticket valid for 10 day(s)"+"\n\n"+
                "                        ~How Servace Number Is: 020351802"+"\n\n\n\n"+                                               
                "=====================Thank You For Travelling With Us=================="));
              Alert box = new Alert(AlertType.CONFIRMATION);
              box.setTitle("Confirmation Message");
              box.setHeaderText(null);
              box.setContentText("Your Ticket Has Been Printed");
              box.showAndWait();
           }
          document.close();
          writer.close();
      }catch(DocumentException | FileNotFoundException e){}
      
}
@FXML
private void cancelProgram(ActionEvent event) throws IOException, SQLException {
   
    
name.setText(null);
age.setText(null);
days.setText(null);
 display.setText(null);
 price.setText(null);
 
    }
    @FXML
    private void showPrice(ActionEvent event) { 
            String zone2[] = {"kericho","nyamira","kemera"};
            String zone3[] = {"mosocho","oyugis","kisumu"};
            String origin= combo1.getSelectionModel().getSelectedItem();
            String destination= combo2.getSelectionModel().getSelectedItem();
               
    
          if(
              name.getText().isEmpty() ){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Empty Field");
              box.setHeaderText(null);
              box.setContentText("Please Input Your Name");
              box.showAndWait();
         
          }else if(age.getText().isEmpty()){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Empty Field");
              box.setHeaderText(null);
              box.setContentText("Please Enter Your Age");
              box.showAndWait();
             

          }else if(combo1.getSelectionModel().getSelectedItem().equalsIgnoreCase("origin")){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Unselected Field");
              box.setHeaderText(null);
              box.setContentText("Please Select Place Of Origin ");
              box.showAndWait();
          }else if(combo2.getSelectionModel().getSelectedItem().equalsIgnoreCase("destination")){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Unselected Field");
              box.setHeaderText(null);
              box.setContentText("Please Select Place Of Destination ");
              box.showAndWait();
          }else if(combo1.getSelectionModel().getSelectedItem().equalsIgnoreCase(combo2.getSelectionModel().getSelectedItem())){
              Alert box = new Alert(AlertType.ERROR);
              box.setTitle("Invalid Choice");
              box.setHeaderText(null);
              box.setContentText("Your Place Of Origin And Place Of Destination Are The Same");
              box.showAndWait();

          }else if(     
                     origin.equalsIgnoreCase(zone2[0]) && destination.equalsIgnoreCase(zone3[0])||
                     origin.equalsIgnoreCase(zone2[0]) && destination.equalsIgnoreCase(zone3[1])||
                     origin.equalsIgnoreCase(zone2[0]) && destination.equalsIgnoreCase(zone3[2])||
                     origin.equalsIgnoreCase(zone2[1]) && destination.equalsIgnoreCase(zone3[0])||
                     origin.equalsIgnoreCase(zone2[1]) && destination.equalsIgnoreCase(zone3[1])||
                     origin.equalsIgnoreCase(zone2[1]) && destination.equalsIgnoreCase(zone3[2])||
                     origin.equalsIgnoreCase(zone2[2]) && destination.equalsIgnoreCase(zone3[0])||
                     origin.equalsIgnoreCase(zone2[2]) && destination.equalsIgnoreCase(zone3[1])||
                     origin.equalsIgnoreCase(zone2[2]) && destination.equalsIgnoreCase(zone3[2])||
                     origin.equalsIgnoreCase(zone3[0]) && destination.equalsIgnoreCase(zone2[0])||
                     origin.equalsIgnoreCase(zone3[0]) && destination.equalsIgnoreCase(zone2[1])||
                     origin.equalsIgnoreCase(zone3[0]) && destination.equalsIgnoreCase(zone2[2])||
                     origin.equalsIgnoreCase(zone3[1]) && destination.equalsIgnoreCase(zone2[0])||
                     origin.equalsIgnoreCase(zone3[1]) && destination.equalsIgnoreCase(zone2[1])||
                     origin.equalsIgnoreCase(zone3[1]) && destination.equalsIgnoreCase(zone2[2])||
                     origin.equalsIgnoreCase(zone3[2]) && destination.equalsIgnoreCase(zone2[0])||
                     origin.equalsIgnoreCase(zone3[2]) && destination.equalsIgnoreCase(zone2[1])||
                     origin.equalsIgnoreCase(zone3[2]) && destination.equalsIgnoreCase(zone2[2]))
                     {
                                      Alert box = new Alert(AlertType.ERROR);
                                      box.setTitle("Invalid Choice");
                                      box.setHeaderText(null);
                                      box.setContentText("There Is No Direct Buses.\nAll Buses Stops In Kisii.");
                                      box.showAndWait();
                  
                     }else if(
                     (origin.equalsIgnoreCase(zone2[0]) && destination.equalsIgnoreCase(zone2[1]))||
                     (origin.equalsIgnoreCase(zone2[0]) && destination.equalsIgnoreCase(zone2[2]))||
                     (origin.equalsIgnoreCase(zone2[1]) && destination.equalsIgnoreCase(zone2[2]))||
                     (origin.equalsIgnoreCase(zone2[1]) && destination.equalsIgnoreCase(zone2[0]))||
                     (origin.equalsIgnoreCase(zone2[2]) && destination.equalsIgnoreCase(zone2[0]))||
                     (origin.equalsIgnoreCase(zone2[2]) && destination.equalsIgnoreCase(zone2[1]))||
                     (origin.equalsIgnoreCase(zone3[0]) && destination.equalsIgnoreCase(zone3[1]))||
                     (origin.equalsIgnoreCase(zone3[0]) && destination.equalsIgnoreCase(zone3[2]))||
                     (origin.equalsIgnoreCase(zone3[1]) && destination.equalsIgnoreCase(zone3[0]))||
                     (origin.equalsIgnoreCase(zone3[1]) && destination.equalsIgnoreCase(zone3[2]))||
                     (origin.equalsIgnoreCase(zone3[2]) && destination.equalsIgnoreCase(zone3[0]))||
                     (origin.equalsIgnoreCase(zone3[2]) && destination.equalsIgnoreCase(zone3[3])))
                     {
                                      Alert box = new Alert(AlertType.ERROR);
                                      box.setTitle("Invalid Choice");
                                      box.setHeaderText(null);
                                      box.setContentText("All Buses Travel Directly To Kisii( No Stop Over ).");
                                      box.showAndWait();
            
        }else if(days.getText().isEmpty()){
              Alert box = new Alert(AlertType.WARNING);
              box.setTitle("Empty Field");
              box.setHeaderText(null);
              box.setContentText("Please Input The Duration Of The Ticket ");
              box.showAndWait();


    
          }else{

            int dy = Integer.parseInt(days.getText());
            int ag = Integer.parseInt(age.getText());
            int cost = 0; 
            int discount;
            int discount1;
            int discount2;
            String kericho ="kericho";
            String kisii="kisii";
            String nyamira="nyamira";
            String kemera="kemera";
            String mosocho="mosocho";
            String oyugis="oyugis";
            String kisumu="kisumu";
            
                    try{
           int ag1 = Integer.parseInt(age.getText()); 
           validate1.setText("");
        }catch (NumberFormatException e){
            validate1.setText("Enter Only Numeric values");
        }          
                        
                     if (
                     (origin.equalsIgnoreCase(kericho) && destination.equalsIgnoreCase(kisii))||
                     (destination.equalsIgnoreCase(kericho)&& origin.equalsIgnoreCase(kisii))||
                     (origin.equalsIgnoreCase(nyamira) && destination.equalsIgnoreCase(kisii))||
                     (destination.equalsIgnoreCase(nyamira) && origin.equalsIgnoreCase(kisii))||
                     (origin.equalsIgnoreCase(kemera) && destination.equalsIgnoreCase(kisii))||
                     (destination.equalsIgnoreCase(kemera) && origin.equalsIgnoreCase(kisii)))
                     {
                cost = 250 * dy ;
                if((ag<=10 || ag>=50) &&(dy >=7 && dy<=59) ){
                 discount1 =(cost*40)/100;
                 discount2 = (cost*10)/100;
                 discount = discount1 + discount2;
                 cost = cost- discount;
                }else if((ag<=10 || ag>=50)&&(dy>=60)){
                 discount1 =(cost*40)/100;
                 discount2 = (cost*40)/100;
                 discount = discount1 + discount2;
                 cost = cost- discount;
                }else if (ag<=10 || ag>=50){
                 discount =(cost*40)/100;
                 cost = cost- discount;
                }else if (dy >=7 && dy<=59){
                 discount=(cost*10)/100;
                 cost = cost- discount;
                }else if (dy>=60){
                 discount=(cost*40)/100;
                 cost = cost - discount;
                }
                NumberFormat nf = NumberFormat.getCurrencyInstance() ;
                String cost1 = nf.format(cost);
                price.setText(cost1);
                }else if(
                        (origin.equalsIgnoreCase(mosocho) && destination.equalsIgnoreCase(kisii))||
                        (destination.equalsIgnoreCase(mosocho) && origin.equalsIgnoreCase(kisii))||
                        ( origin.equalsIgnoreCase(oyugis) && destination.equalsIgnoreCase(kisii))||
                        (destination.equalsIgnoreCase(oyugis) && origin.equalsIgnoreCase(kisii))||
                        (origin.equalsIgnoreCase(kisumu) && destination.equalsIgnoreCase(kisii))||
                        (destination.equalsIgnoreCase(kisumu) && origin.equalsIgnoreCase(kisii))){
                cost = 200*dy;
                     if( 
                        (ag<=10 || ag>=50) &&(dy >=7 && dy<=59) ){
                        discount1 =(cost*40)/100;
                        discount2 = (cost*10)/100;
                        discount = discount1 + discount2;
                        cost = cost- discount;
                }else if( 
                        (ag<=10 || ag>=50)&&(dy>=60)){
                        discount1 =(cost*40)/100;
                        discount2 = (cost*40)/100;
                        discount = discount1 + discount2;
                        cost = cost- discount;
                }else if( 
                        ag<=10 || ag>=50){
                        discount =(cost*40)/100;
                        cost = cost- discount;
                }else if(
                        dy >=7 &&dy<=59){
                        discount=(cost*10)/100;
                        cost = cost- discount;
                }else if(
                        dy>=60){
                        discount=(cost*40)/100;
                        cost = cost - discount;
        }
         NumberFormat nf = NumberFormat.getCurrencyInstance() ;
         String cost1 = nf.format(cost);
         price.setText(cost1);
               
              }
           }

    }                                        

    private void saveDetails(ActionEvent event) throws SQLException {
       DBConnector connect = new DBConnector();
       Connection  con =connect.getConnection();
       String sql=("INSERT INTO PASSENGERDETAILS (name,age,origin,destination,days,price) VALUES('"+name.getText()+"','"+age.getText()+"','"+combo1.getSelectionModel().getSelectedItem()+"','"+combo1.getSelectionModel().getSelectedItem()+"','"+days.getText()+"','"+price.getText()+")");
       Statement statement = con.createStatement();
       statement.executeUpdate(sql);
 if(con == null){
           Alert box = new Alert(AlertType.ERROR);
                                      box.setTitle("Invalid Choice");
                                      box.setHeaderText(null);
                                      box.setContentText("connection fail");
                                      box.showAndWait();
                                      System.exit(1);
 }else{
           Alert box = new Alert(AlertType.ERROR);
                                      box.setTitle("Invalid Choice");
                                      box.setHeaderText(null);
                                      box.setContentText("connection successful");
                                      box.showAndWait();
 }

    }

    @FXML
    private void name(KeyEvent event) {
                try{
           String n =name.getText();
           validate1.setText("");
    }catch(Exception e){
        validate1.setText("Enter Only Characters ");}
    }

    @FXML
    private void age(KeyEvent event) {
        try{
           int x =Integer.parseInt(age.getText());
           validate1.setText("");
    }catch(NumberFormatException e){
        validate1.setText("Enter Only Numeric Values");
    }}

    @FXML
    private void days(KeyEvent event) {
         try{
           int x =Integer.parseInt(days.getText());
           validate3.setText("");
    }catch(NumberFormatException e){
        validate3.setText("Enter Only Numeric Values");
    }
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
             Platform.exit();
        System.exit(0);
    }

}
  
    

