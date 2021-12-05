package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board{
	private List<Clip> clips=new ArrayList<>();

	public Board() {}
	
	public List<Clip> getContents(){
		return clips;
	}
	
	public void addClip(Clip clip) {
		clips.add(clip);
	}
	
	public void addClip(List<Clip> clip) {
		for(Clip c: clip) clips.add(c);
	}
	
	public void removeClip(Clip clip) {
		clips.remove(clip);
	}
	
	public void removeClip(List<Clip> clip) {
		for(Clip c : clip) clips.remove(c);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.setStroke(Color.BLACK);
		for(Clip c: clips) {
			c.draw(gc);
		}
	}

}
