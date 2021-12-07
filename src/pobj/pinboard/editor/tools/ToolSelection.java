package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;

public class ToolSelection implements Tool{
	private double x1,x2,y1,y2;
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x1=e.getX();
		y1=e.getY();
		if(e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(), x1, y1);
		}
		else{
			i.getSelection().select(i.getBoard(), x1, y1);
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		
		x2=e.getX();
		y2=e.getY();
		
		for(Clip c:i.getSelection().getContents()) {
			c.move(x2-x1, y2-y1);
		}
		x1=x2;
		y1=y2;
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		i.getSelection().drawFeedBack(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return "Tool Selection";
	}
	

}
