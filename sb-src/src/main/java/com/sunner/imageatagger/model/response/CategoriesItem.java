package com.sunner.imageatagger.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriesItem{
	private double confidence;
	@JsonProperty("tag")
	@JsonAlias("name")
	private Name name;

	public double getConfidence(){
		return confidence;
	}

	public Name getName(){
		return name;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
 	public String toString(){
		return "tag: " + name + "\nconfidence: " + confidence + "\n";
		}
}
