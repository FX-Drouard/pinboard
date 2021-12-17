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
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface,ClipboardListener {
	private Stage stage;
	private Board board;
	private Tool tool;
	private Selection select=new Selection();
	private MenuItem paste=new MenuItem("Paste");
	private GraphicsContext gc;

	
	public EditorWindow(Stage stage){
		
		this.stage=stage;
		this.board=new Board();
		EditorInterface ei=(EditorInterface) this;
		ClipboardListener ci=(ClipboardListener) this;
		Clipboard.getInstance().addListener(ci);
		
		Canvas canvas = new Canvas(800, 600);
		gc=canvas.getGraphicsContext2D();
		MenuBar mb= new MenuBar(new Menu("File"),new Menu("Edit"), new Menu("Tools"));
		VBox vbox= new VBox();
		Scene scene=new Scene(vbox);
		List<Button> listb=new ArrayList<>();
		listb.add(new Button("Box"));
		listb.add(new Button("Ellipse"));
		listb.add(new Button("Image"));
		listb.get(2).setDisable(true);
		listb.add(new Button(" Select "));
		ToolBar tb= new ToolBar();
		
		
		
		//Attention l'ordre des elements comptent
		Label labt= new Label("Actuellement Vide");
		vbox.getChildren().addAll(mb,tb,canvas, new Separator(), labt);
		
		
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
		
		//Edit menu
		MenuItem itemc= new MenuItem("Close");
		itemc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Clipboard.getInstance().removeListener(ci);
				stage.close();
			}
		});
		mb.getMenus().get(1).getItems().add(itemc);
		
		//Menu edit
		Menu ed=mb.getMenus().get(1);
		
		MenuItem cop=new MenuItem("Copy");
		cop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Clipboard.getInstance().copyToClipboard(board.getContents());
			}
			
		});
		ed.getItems().add(cop);
		
		
		if(Clipboard.getInstance().isEmpty()) {
			paste.setDisable(true);
		}
		paste.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				List<Clip> cp=Clipboard.getInstance().copyFromClipboard();
				board.addClip(cp);
				board.draw(gc);
			}
			
		});
		ed.getItems().add(paste);
		
		MenuItem delete=new MenuItem("Delete");
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				board.removeClip(select.getContents());
			}
			
		});
		ed.getItems().add(delete);
		
		
		//Menu tool
		Menu tl=mb.getMenus().get(2);
		
		MenuItem rec=new MenuItem("Rectangle");
		rec.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!(tool instanceof ToolRect)) {
					tool = new ToolRect();
					labt.setText(tool.getName(ei));
				}
			}
			
		});
		tl.getItems().add(rec);
		
		MenuItem ell=new MenuItem("Ellipse");
		ell.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!(tool instanceof ToolEllipse)) {
					tool = new ToolEllipse();
					labt.setText(tool.getName(ei));
				}
			}
			
		});
		tl.getItems().add(ell);
		
		
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
					labt.setText(tool.getName(ei));
				}
			}
			
		});
		
		listb.get(1).setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!(tool instanceof ToolEllipse)) {
					tool = new ToolEllipse();
					labt.setText(tool.getName(ei));
				}
			}
		});
		
		listb.get(3).setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!(tool instanceof ToolSelection)) {
					tool = new ToolSelection();
					labt.setText(tool.getName(ei));
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
				if(tool instanceof ToolSelection) {
					tool.drawFeedback(ei, gc);
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
		return select;
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clipboardChanged() {
		// TODO Auto-generated method stub
		if(Clipboard.getInstance().isEmpty()) {
			paste.setDisable(true);
			board.draw(gc);
		}else {
			paste.setDisable(false);
			board.draw(gc);
		}
	}
	
	
}
