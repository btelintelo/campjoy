package org.campjoy.identitree.starter.model;

public class Choice {

	private String text;
	private String nextId;
	private String treeId;
	
	public Choice() {
		text = "";
		nextId = "";
		treeId = "";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
}
