package com.example.foodapp;

import java.util.Comparator;

public class RecipeElement {
	private String name;
	private int number;
	
	void putNumber (int number) {
		this.number = number;
	}
	
	void putName (String name) {
		this.name = name;
	}
	
	String getName() {
		return this.name;
	}
	
	int getNumer() {
		return this.number;
	}
}

class NumberComparator implements Comparator<RecipeElement> {
    public int compare(RecipeElement recipe1, RecipeElement recipe2) {
        return recipe2.getNumer() - recipe1.getNumer();
    }
}
