package com.sunner.imageatagger.model.response;

public class Name{
	private String en;

	public String getEn(){
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	@Override
 	public String toString(){
		return "'" + en + "'";
		}
}
