package pobj.pinboard.editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;

public class EditorWindow {
	private Stage stage;
	private Board board;

	
	public EditorWindow(Stage stage) {
		this.stage=stage;
		this.board=new Board();
		Canvas canvas = new Canvas(800, 600);
		MenuBar mb= new MenuBar(new Menu("File"),new Menu("Edit"), new Menu("Tools"));
		VBox vbox= new VBox();
		Scene scene=new Scene(vbox);
		ToolBar tb= new ToolBar(new Button("Box"),new Button("Ellipse"), new Button("Image"));
		
		
		//Attention l'ordre des elements comptent
		vbox.getChildren().addAll(mb,tb,canvas, new Separator(), new Label ("Actuellement Vide"));
		
		MenuItem itemn= new MenuItem("New");
		itemn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new EditorWindow(new Stage());
			}
		});
		
		mb.getMenus().get(0).getItems().add(itemn);
		MenuItem itemc= new MenuItem("Close");
		itemc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.close();
			}
		});
		mb.getMenus().get(1).getItems().add(itemc);
		
		
		stage.setTitle("PinBoard - <untitled>");
		stage.setScene(scene);
		stage.show();
	}
}
