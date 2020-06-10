package JavaFX;


import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class EventsFXMLController implements Initializable {
    
        File FILE = new File ("Event.txt");
        Storage storage = (readFromFile(FILE));
        Organisers currentOrganiser;
        public Event currentEvent;

    @FXML
    private TreeView<String> Organisers;

    @FXML
    private Button register;


    @FXML
    private TreeView<String> Events;

    @FXML
    private TextArea Details;
    
    @FXML
    private Slider Slider;
    
    @FXML
    private Text currentTime;
     
    @FXML
    private Text mandatory;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        OrganisersEvent();
        currentTime.setText(storage.timeToString());
    }
    /**
     * Opens registration page
     * @param event 
     */
        @FXML
    void newRegisterPage(ActionEvent event) {
        try{
     FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterForm.fxml"));
     Parent rootRegister = loader.load();

     RegisterFormController controller = loader.getController();
     controller.setEventName(currentEvent.getEventName());
     Stage stage = new Stage();
     stage.setScene(new Scene(rootRegister));
     stage.show();
     stage.setResizable(false);
             }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }     
    }
    /**
     * Shows all the organizers in the treeView
     */
    @FXML
    void OrganisersEvent(){
        TreeItem<String> root = new TreeItem<> ("Organisers"); 
        root.setExpanded(true);
        Organisers.setRoot(root);
        for(int i = 0;i<storage.getOrganisersCount();i++)
        {
        root.getChildren().add(new TreeItem<>(storage.getOrganisers()[i].getOrganisersName()));
        }
    }
/**
 * Shows all the events of given organizer in the treeView
 * (needs to be called)
 * @param organiser 
 */
   @FXML
   void EventsEvent(Organisers organiser){ 
        TreeItem<String> root = new TreeItem<> ("Events"); 
        root.setExpanded(true);
        Events.setRoot(root);
        TreeItem<String> UpcomingEvents=new TreeItem<> ("Upcoming Events");
        root.getChildren().add(UpcomingEvents);
        for(int i = 0;i<organiser.getEventsAmount();i++)
        {
        TreeItem<String> Event=new TreeItem<>(organiser.getEvents()[i].getEventName());
        UpcomingEvents.getChildren().add(Event);
        }
        UpcomingEvents.setExpanded(true);
        TreeItem<String> PastEvents=new TreeItem<> ("Past Events");
        root.getChildren().add(PastEvents);
        for(int i = 0;i<organiser.getPreviousEventsAmount();i++)
        {
        TreeItem<String> Event=new TreeItem<>(organiser.getPreviousEvents()[i].getEventName());
        PastEvents.getChildren().add(Event);
        }
        PastEvents.setExpanded(true);
    }
/**
 * Shows all events of selected organizer
 * @param mouseEvent 
 */
    @FXML
    void selectNode(MouseEvent mouseEvent){
    
        TreeItem<String> item = Organisers.getSelectionModel().getSelectedItem();
        for(int i=0;i<storage.getOrganisersCount();i++)
        {
        if(item.getValue().equals(storage.organisers[i].getOrganisersName()))
        {
        currentOrganiser=storage.organisers[i];
        EventsEvent(storage.organisers[i]);
        }
        }
    }
    /**
     * Shows information of selected event
     */
    @FXML
    void showInfo(){
        
        TreeItem<String> item = Events.getSelectionModel().getSelectedItem();

        for(int i=0; i<currentOrganiser.getEventsAmount(); i++)
        {
            if(item.getValue().equals(currentOrganiser.getEvents()[i].getEventName()))
        {
        Details.setText(currentOrganiser.getEvents()[i].toString());
        currentEvent=currentOrganiser.getEvents()[i];
        
        if(!(currentEvent.isFull()))
        {
            register.setVisible(true);
            mandatory.setVisible(true);
        }
        }
        }
        for(int i=0; i<currentOrganiser.getPreviousEventsAmount(); i++)
        {
            if(item.getValue().equals(currentOrganiser.getPreviousEvents()[i].getEventName()))
        {
        Details.setText(currentOrganiser.getPreviousEvents()[i].toString());
        currentEvent=currentOrganiser.getPreviousEvents()[i];
        register.setVisible(false);
        mandatory.setVisible(false);
        
        }
        }
    }
/**
 * Methods for time simulation
 */
 @FXML
    void addHours() {
    storage.getTime().addHours(1);
    currentTime.setText(storage.timeToString());
    storage.refreshOrganisers();
         EventsEvent(currentOrganiser);

    }
    
    @FXML
    void addMonths() {
    storage.getTime().addMonths(1);
    currentTime.setText(storage.timeToString());
    storage.refreshOrganisers();
         EventsEvent(currentOrganiser);

    }
    
    @FXML
    void addYears() {
    storage.getTime().addYears(1);
    currentTime.setText(storage.timeToString());
        storage.refreshOrganisers();
             EventsEvent(currentOrganiser);

    }
    @FXML
    void addDays() {
        storage.getTime().addDays(1);
        currentTime.setText(storage.timeToString());
        storage.refreshOrganisers();
             EventsEvent(currentOrganiser);
    }
    
    
    
    @FXML
    void submitVote() {
        
        currentEvent.setRating(Slider.getValue());
        showInfo();

    }
    
    Storage readFromFile(File myObj){
        Storage mainStorage = new Storage();
    try {
      String arrOfStr[]= new String[13];
      Scanner myReader = new Scanner(myObj);
      
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();        
        arrOfStr = data.split(" ");
            mainStorage.addEvent(new Event(arrOfStr[0],arrOfStr[1],Double.parseDouble(arrOfStr[2]),
             Boolean.parseBoolean(arrOfStr[3]), arrOfStr[4], Boolean.parseBoolean(arrOfStr[5]),
              Integer.parseInt(arrOfStr[6]) ,Integer.parseInt(arrOfStr[7]), Integer.parseInt(arrOfStr[8]),
                Integer.parseInt(arrOfStr[9]), Integer.parseInt(arrOfStr[10]), Integer.parseInt(arrOfStr[11]), arrOfStr[12]));
      }
      
      myReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    return mainStorage;
    
    }
    

}