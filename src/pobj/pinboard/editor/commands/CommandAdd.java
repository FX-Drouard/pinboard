package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command {
	private EditorInterface editor;
	private List<Clip> toAdd=new ArrayList<>();
	
	
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.editor=editor;
		this.toAdd.add(toAdd);
		
	}
	
	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		this.editor=editor;
		this.toAdd=toAdd;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		editor.getBoard().addClip(toAdd);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(toAdd);
	}

}
