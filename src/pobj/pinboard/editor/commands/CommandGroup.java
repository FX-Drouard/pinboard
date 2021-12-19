package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command {
	
	private EditorInterface editor;
	private List<Clip> toAdd=new ArrayList<>();
	private ClipGroup cg;
	
	
	public CommandGroup(EditorInterface editor, List<Clip> toAdd) {
		this.editor=editor;
		this.toAdd=toAdd;
		cg= new ClipGroup();
		for(Clip c : this.toAdd) cg.addClip(c);
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(Clip c : toAdd)editor.getBoard().getContents().remove(c);
		
		editor.getBoard().getContents().add(cg);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
		editor.getBoard().getContents().remove(cg);
		/*for(Clip c : editor.getBoard().getContents()) {
			if(c.equals(cg)) {
				//On verifie l'instance, qui doit etre celui en attribut, on retire et on sort
				editor.getBoard().getContents().remove(c);
				break;
			}
		}*/
		
		for(Clip c : toAdd) {
			editor.getBoard().getContents().add(c);
		}
	}

}
