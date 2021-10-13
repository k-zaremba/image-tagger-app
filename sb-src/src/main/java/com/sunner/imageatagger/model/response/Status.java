package com.sunner.imageatagger.model.response;

public class Status{
	private String text;
	private String type;

	public String getText(){
		return text;
	}

	public String getType(){
		return type;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
 	public String toString(){
		return 
			"Status{" + 
			"text = '" + text + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}
