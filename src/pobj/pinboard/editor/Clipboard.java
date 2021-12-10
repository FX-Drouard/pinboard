package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	private List<Clip> copied=new ArrayList<>();
	private static Clipboard cb= null;
	private List<ClipboardListener> listenerl=new ArrayList<>();
	
	private Clipboard() {};
	
	
	public static Clipboard getInstance() {
		if(cb==null) {
			cb=new Clipboard();
		}
		
		return cb;
	}
	public void copyToClipboard(List<Clip> clips) {
		for(Clip c: clips) {
			copied.add(c.copy());
		}
		for(ClipboardListener cl: listenerl) {
			cl.clipboardChanged();
		}
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> resc=new ArrayList<>();
		for(Clip c: copied) {
			resc.add(c.copy());
		}
		return resc;
	}
	
	public void clear() {
		copied.clear();
		for(ClipboardListener cl: listenerl) {
			cl.clipboardChanged();
		}
	}
	
	public boolean isEmpty() {
		return copied.size()==0;
	}

	public void addListener(ClipboardListener listener) {
		if(!this.listenerl.contains(listener))
			this.listenerl.add(listener);
	}
	
	public void removeListener(ClipboardListener listener) {
		this.listenerl.remove(listener);
	}



}
