package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command{
	
	private EditorInterface editor;
	private List<ClipGroup> toAdd= new ArrayList<>();
	private List<Clip> ucg=new ArrayList<>();
	
	
	public CommandUngroup(EditorInterface editor, ClipGroup toAdd) {
		this.editor=editor;
		this.toAdd.add(toAdd);
		for(ClipGroup c : this.toAdd) {
			for(Clip c2: c.getClips()) ucg.add(c2);
		}
		
		
	}
	
	public CommandUngroup(EditorInterface editor, List<ClipGroup> toAdd) {
		this.editor=editor;
		this.toAdd=toAdd;
		for(ClipGroup c : this.toAdd) {
			for(Clip c2: c.getClips()) ucg.add(c2);
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(Clip c : toAdd)editor.getBoard().getContents().remove(c);
		
		for(Clip c : ucg) editor.getBoard().getContents().add(c);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for(Clip c : ucg) editor.getBoard().getContents().remove(c);
		
		for(Clip c : toAdd) editor.getBoard().getContents().add(c);
		
	}

}
