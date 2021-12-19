package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	private List<Command> redop = new ArrayList<>();
	private List<Command> undop = new ArrayList<>();
	
	public void addCommand(Command cmd) {
		undop.add(cmd);
		redop.clear();
	}
	
	public void undo() {
		Command toex=undop.get(undop.size()-1);
		undop.remove(undop.size()-1);
		toex.undo();
		redop.add(toex);
	}
	
	public void redo() {
		Command toex=redop.get(redop.size()-1);
		redop.remove(redop.size()-1);
		toex.execute();
		undop.add(toex);
	}
	
	public boolean isUndoEmpty() {
		return undop.size()==0;
	}
	
	public boolean isRedoEmpty() {
		return redop.size()==0;
	}
	
}
