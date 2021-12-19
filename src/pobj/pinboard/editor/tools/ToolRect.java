package pobj.pinboard.editor.tools;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.CommandStack;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolRect implements Tool{
	private ClipRect cr;
	private double x1,x2,y1,y2;
	public ToolRect() {}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x1=e.getX();
		y1=e.getY();
		cr=new ClipRect(x1,y1,x1,y1,Color.BLUE);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		double top,bottom,left,right;
		x2=e.getX();
		y2=e.getY();
		if(x1<x2) {
			left=x1;
			right=x2;
		}
		else {
			left=x2;
			right=x1;
		}
		
		if(y1<y2) {
			top=y1;
			bottom=y2;
		}else {
			top=y2;
			bottom=y1;
		}
		
		cr.setGeometry(left, top, right, bottom);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		//i.getBoard().addClip(cr);
		
		CommandStack stack = i.getUndoStack();
		CommandAdd ca= new CommandAdd(i,cr);
		ca.execute();
		stack.addCommand(ca);
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setStroke(Color.BLACK);
		gc.strokeRect(cr.getLeft(), cr.getTop(), cr.getRight()-cr.getLeft(), cr.getBottom()-cr.getTop());
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return "ToolRect";
	}

}
