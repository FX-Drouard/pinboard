package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip{
	
	public ClipRect (double left, double top, double right, double bottom, Color color) {
		super(left,top,right,bottom,color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(), getTop(), getRight()-getLeft(), getBottom()-getTop());
		ctx.setStroke(Color.BLUE);
		
	}

	/*
	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return bottom;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		left+=x;
		right+=x;
		top+=y;
		bottom+=y;
	}*/

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return (x<=getRight() && x>=getLeft() && y<=getBottom() && y>=getTop());
	}

	/*
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		color=c;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}*/

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return new ClipRect(getLeft(),getTop(),getRight(),getBottom(),getColor());
	}
	
}
