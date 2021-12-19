package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup implements Composite {
	private List<Clip> clips=new ArrayList<>();

	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		for(Clip c : clips) {
			c.draw(ctx);
		}
	}

	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		double min=clips.get(0).getTop();
		for(Clip c:clips) {
			if(c.getTop()<min) min=c.getTop();
		}
		return min;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		double min=clips.get(0).getLeft();
		for(Clip c:clips) {
			if(c.getLeft()<min) min=c.getLeft();
		}
		return min;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		double max=clips.get(0).getBottom();
		for(Clip c:clips) {
			if(c.getBottom()>max) max=c.getBottom();
		}
		return max;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		double max=clips.get(0).getRight();
		for(Clip c:clips) {
			if(c.getRight()>max) max=c.getRight();
		}
		return max;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		for(Clip c : clips) {
			c.setGeometry(left, top, right, bottom);
		}
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		for(Clip c : clips) {
			c.move(x, y);
		}
	}

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return (x<=getRight() && x>=getLeft() && y<=getBottom() && y>=getTop());
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		for(Clip cl : clips) {
			cl.setColor(c);
		}
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return clips.get(0).getColor();
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		Clip res = new ClipGroup();
		ClipGroup tmp = (ClipGroup) res;
		for(Clip c : clips) {
			tmp.addClip(c.copy());
		}
		return res;
	}

	@Override
	public List<Clip> getClips() {
		// TODO Auto-generated method stub

		return clips;
	}

	@Override
	public void addClip(Clip toAdd) {
		// TODO Auto-generated method stub
		clips.add(toAdd);
	}

	@Override
	public void removeClip(Clip toRemove) {
		// TODO Auto-generated method stub
		clips.remove(toRemove);
	}

}
