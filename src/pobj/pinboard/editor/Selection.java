package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private List<Clip> selection=new ArrayList<>();
	
	public void select(Board board,double x, double y) {
		selection.clear();
		for(Clip c : board.getContents()) {
			if(c.isSelected(x, y)){
				selection.add(c);
				break;
			}
		}
	}
	
	public void toogleSelect(Board board, double x, double y) {
		for(Clip c : board.getContents()) {
			if(c.isSelected(x, y)){
				if(selection.contains(c)) {
					selection.remove(c);
				}else {
					selection.add(c);
				}
				break;
			}
		}
	}
	
	public void clear() {
		selection.clear();
	}
	
	public List<Clip> getContents(){
		return selection;
	}
	
	public void drawFeedBack(GraphicsContext gc) {
		if(!selection.isEmpty()) {	
			double top=selection.get(0).getTop();
			double bottom=selection.get(0).getBottom();
			double right=selection.get(0).getRight();
			double left=selection.get(0).getLeft();
			for(Clip c: selection) {
				c.draw(gc);
				if(c.getTop()<top) top=c.getTop();
				if(c.getLeft()<left) left=c.getLeft();
				if(c.getBottom()>bottom) bottom =c.getBottom();
				if(c.getRight()>right) right =c.getRight();
			}
			gc.setStroke(Color.BLACK);
			gc.strokeRect(left, top, right-left, bottom-top);
		}
	}

}
