package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {
	private EditorInterface editor;
	private List<Clip> toAdd=new ArrayList<>();
	//private List<Clip> toAdd;
	private double x;
	private double y;
	
	
	public CommandMove(EditorInterface editor, Clip toAdd,double x, double y) {
		this.editor=editor;
		//this.toAdd.add(toAdd);
		this.toAdd.add(toAdd);
		this.x=x;
		this.y=y;
		
	}
	
	public CommandMove(EditorInterface editor, List<Clip> toAdd,double x, double y) {
		this.editor=editor;
		this.toAdd=toAdd;
		this.x=x;
		this.y=y;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(Clip c: toAdd)c.move(x, y);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for(Clip c: toAdd)c.move(-x, -y);
	}

}
