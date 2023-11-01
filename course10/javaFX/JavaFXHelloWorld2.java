package ro.ase.course10.javaFX;/*
package examples.course10.javaFX;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class JavaFXHelloWorld2 extends Application {
 
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("Inside init() method! " +
				"Perform necessary initializations here.");
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		System.out.println("Inside stop() method! " +
				"Destroy resources. Perform Cleanup.");
	}
	
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        
        //adaugare scena
        Label label = new Label("Hello World, JavaFX !");
        label.setAlignment(Pos.TOP_LEFT);
        //Scene scene = new Scene(label, 400, 200);
        //primaryStage.setScene(scene);
        
        //adaugare buton
        Button btn = new Button();
        btn.setAlignment(Pos.TOP_RIGHT);
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(label);
        root.getChildren().add(btn);
        
        ObservableList<Node> childs = root.getChildren();
        if (childs.size()>1)
        {
        	Node topNode = childs.get(childs.size()-1);
        	//topNode.setVisible(false);
        	topNode.toBack();
        }
        
        Scene scene1 = new Scene(root, 300, 250);
        primaryStage.setScene(scene1);
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}*/
