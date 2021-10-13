package com.sunner.imageatagger.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Result{
	@JsonProperty("tags")
	@JsonAlias("categories")
	private List<CategoriesItem> categories;

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setCategories(List<CategoriesItem> categories) {
		this.categories = categories;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"categories = '" + categories + '\'' + 
			"}";
		}
}