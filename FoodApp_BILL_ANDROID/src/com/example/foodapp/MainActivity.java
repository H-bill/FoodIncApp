package com.example.foodapp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFoodButtonListener();
        createDrinkButtonListener();
    }

    private void createFoodButtonListener() {
        Button button = (Button) findViewById(R.id.mainButtonFood);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent Food = new Intent(getApplicationContext(), Food.class);
				startActivity(Food);
			}
		});
    }

    private void createDrinkButtonListener() {
        Button button = (Button) findViewById(R.id.mainButtonDrink);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent Drink = new Intent(getApplicationContext(), Drink.class);
				startActivity(Drink);
			}
		});
    }

}
