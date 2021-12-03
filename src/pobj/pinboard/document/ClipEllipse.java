package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {
	
	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		setLeft(left);
		setRight(right);
		setBottom(bottom);
		setTop(top);
		setColor(color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.setFill(getColor());
		ctx.fillOval(getTop(), getRight(), getLeft(), getBottom());
		ctx.setStroke(Color.RED);
	}

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		double cx= (super.getLeft()+super.getRight())/2;
		double cy= (super.getTop()+super.getBottom())/2;
		double rx= (super.getRight()-super.getLeft())/2;
		double ry= (super.getBottom()-super.getTop())/2;
		
		double m1=(x-cx)/rx;
		double m2=(y-cy)/ry;
		
		double res=m1*m1 + m2*m2;
		return res<=1;
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return new ClipEllipse(getLeft(),getTop(),getRight(),getBottom(),getColor());
	}

}
