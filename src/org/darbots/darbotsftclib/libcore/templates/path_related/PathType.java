package org.darbots.darbotsftclib.libcore.templates.path_related;

public enum PathType {
	LinearPath(0);
	
	private int value = 0;
	private PathType(int value) {
		this.value = value;
	}
	public static PathType valueOf(int value) {
		switch(value) {
		case 0:
			return LinearPath;
		default:
			return null;
		}
	}
	public int value() {
		return this.value;
	}
}
