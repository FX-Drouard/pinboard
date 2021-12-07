package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;

public class EditorWindow implements EditorInterface {
	private Stage stage;
	private Board board;
	private Tool tool;

	
	public EditorWindow(Stage stage){
		this.stage=stage;
		this.board=new Board();
		EditorInterface ei=(EditorInterface) this;
		
		Canvas canvas = new Canvas(800, 600);
		GraphicsContext gc=canvas.getGraphicsContext2D();
		MenuBar mb= new MenuBar(new Menu("File"),new Menu("Edit"), new Menu("Tools"));
		VBox vbox= new VBox();
		Scene scene=new Scene(vbox);
		List<Button> listb=new ArrayList<>();
		listb.add(new Button("Box"));
		listb.add(new Button("Ellipse"));
		listb.add(new Button("Image"));
		ToolBar tb= new ToolBar();
		
		
		
		//Attention l'ordre des elements comptent
		vbox.getChildren().addAll(mb,tb,canvas, new Separator(), new Label ("Actuellement Vide"));
		
		
		//Menu button
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
		
		Label lb=(Label)vbox.getChildren().get(4);
		if(lb instanceof Label) {
			lb=(Label) lb;
		}
		
		
		//Creation of tools
			
		listb.get(0).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!(tool instanceof ToolRect)) {
					tool = new ToolRect();
					
				}
			}
			
		});
		
		listb.get(1).setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!(tool instanceof ToolEllipse)) {
					tool = new ToolEllipse();
				}
			}
		});
		
		tb.getItems().addAll(listb);
		
			
		
		
		
		//Mouse actions
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(tool!=null) {
					tool.press(ei, arg0);
				}
			}
			
		});
		
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(tool !=null) {
					tool.drag(ei, arg0);
					draw(gc);
					tool.drawFeedback(ei, gc);
				}
				
			}
			
		});
		
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(tool!=null) {
					tool.release(ei, arg0);
					draw(gc);
				}
			}
			
		});
		
		
		
		
		
		stage.setTitle("PinBoard - <untitled>");
		stage.setScene(scene);
		stage.show();
	}
	
	public void draw(GraphicsContext gc) {
		board.draw(gc);
	}

	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}

	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
