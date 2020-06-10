
package JavaFX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterFormController{
    

    EventsFXMLController fatherController = new EventsFXMLController();
    
    private String eventName;
      @FXML
    private TextField nameField;
      @FXML
    private TextField lastNameField;
      @FXML
    private TextField numberField;

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
    /**
     * Creates different files to store data of people who registered for event
     * @param event
     * @throws IOException 
     */
    @FXML
    void goToMain(ActionEvent event) throws IOException {
        if(!nameField.getText().equals("") && !lastNameField.getText().equals("") && !numberField.getText().equals("")){
        write(this.eventName, nameField.getText()+" "+lastNameField.getText()+" "+numberField.getText()+"\r\n");
    }
       ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
       
    }
    private static void write(String eventName, String data){
        File participants = new File(eventName + ".txt");
    try{
    if(participants.exists()==false){
            participants.createNewFile();
    }
    PrintWriter out = new PrintWriter(new FileWriter(participants, true));
    out.append(data);
    out.close();
    }catch(IOException e){
        System.out.println("COULD NOT LOG!!");
    }
    }
}
