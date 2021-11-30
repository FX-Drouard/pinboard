package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect implements Clip{
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public ClipRect (double left, double top, double right, double bottom, Color color) {
		this.left=left;
		this.right=right;
		this.bottom=bottom;
		this.top=top;
		this.color=color;
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
