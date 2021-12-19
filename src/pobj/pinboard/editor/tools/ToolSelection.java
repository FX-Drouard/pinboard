package pobj.pinboard.editor.tools;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.CommandStack;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool{
	private double x1,vx2,y1,vy2;
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x1=e.getX();
		y1=e.getY();
		vx2=x1;
		vy2=y1;
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
		
		
		for(Clip c:i.getSelection().getContents()) {
			c.move(e.getX()-x1, e.getY()-y1);
		}
		x1=e.getX();
		y1=e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		Selection select=i.getSelection();
		List<Clip> clips=select.getContents();
		CommandStack cs= i.getUndoStack();
		vx2= x1-vx2;
		vy2= y1-vy2;
		CommandMove cm = new CommandMove(i,clips,vx2,vy2);
		cs.addCommand(cm);
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
