package pobj.pinboard.editor.tools;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool{
	public ToolRect() {}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		/*i.setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return null;
	}

}
