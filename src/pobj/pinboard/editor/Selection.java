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
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.setStroke(Color.BLACK);
		for(Clip c: selection) {
			c.draw(gc);
		}
	}

}
