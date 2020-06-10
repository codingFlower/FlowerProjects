
package JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Pijus_Ir_Vykintas
 */
public class Main extends Application{
    

    public static void main(String[] args) {

        launch(args);

    }
/**
 * Creates GUI main window
 * @param stage
 * @throws Exception 
 */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EventsFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Event Project");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        
    }
    
    
}
