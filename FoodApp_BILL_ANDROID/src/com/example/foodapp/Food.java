package com.example.foodapp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class Food extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
    }

	public void addListenerOnButton() {
	 
	Button button = (Button) findViewById(R.id.mainButtonFood);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent Food = new Intent(getApplicationContext(), Food.class);
				startActivity(Food);
			}
		});
	}

}
