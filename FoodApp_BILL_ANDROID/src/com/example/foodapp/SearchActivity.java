package com.example.foodapp;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	
	TextView newIngr1;
	TextView newIngr2;
	TextView newIngr3;
	TextView newIngr4;
	TextView newIngr5;
	TextView newIngr6;
	TextView newIngr7;
	TextView newIngr8;
	TextView newIngr9;
	TextView newIngr10;
	
	Button add1;
	Button add2;
	Button add3;
	Button add4;
	Button add5;
	Button add6;
	Button add7;
	Button add8;
	Button add9;
	Button add10;
	
	ArrayList<String> listIngr;
	int cont = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        
        Intent intent = getIntent();
        String type = intent.getExtras().getString("type");
        
        listIngr = new ArrayList<String>();
        
        newIngr1 = (TextView) findViewById (R.id.newIngr1);
        newIngr2 = (TextView) findViewById (R.id.newIngr2);
    	newIngr3 = (TextView) findViewById (R.id.newIngr3);
    	newIngr4 = (TextView) findViewById (R.id.newIngr4);
    	newIngr5 = (TextView) findViewById (R.id.newIngr5);
    	newIngr6 = (TextView) findViewById (R.id.newIngr6);
    	newIngr7 = (TextView) findViewById (R.id.newIngr7);
    	newIngr8 = (TextView) findViewById (R.id.newIngr8);
    	newIngr9 = (TextView) findViewById (R.id.newIngr9);
    	newIngr10 = (TextView) findViewById (R.id.newIngr10);
    	
    	add1 = (Button) findViewById (R.id.add1);
    	add2 = (Button) findViewById (R.id.add2);
    	add3 = (Button) findViewById (R.id.add3);
    	add4 = (Button) findViewById (R.id.add4);
    	add5 = (Button) findViewById (R.id.add5);
    	add6 = (Button) findViewById (R.id.add6);
    	add7 = (Button) findViewById (R.id.add7);
    	add8 = (Button) findViewById (R.id.add8);
    	add9 = (Button) findViewById (R.id.add9);
    	add10 = (Button) findViewById (R.id.add10);
        
        add1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr1.getText().toString());
            	newIngr2.setVisibility(View.VISIBLE);
            	newIngr2.requestFocus();
            	add2.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr2.getText().toString());
            	newIngr3.setVisibility(View.VISIBLE);
            	newIngr3.requestFocus();
            	add3.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr3.getText().toString());
            	newIngr4.setVisibility(View.VISIBLE);
            	newIngr4.requestFocus();
            	add4.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr4.getText().toString());
            	newIngr5.setVisibility(View.VISIBLE);
            	newIngr5.requestFocus();
            	add5.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr5.getText().toString());
            	newIngr6.setVisibility(View.VISIBLE);
            	newIngr6.requestFocus();
            	add6.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr6.getText().toString());
            	newIngr7.setVisibility(View.VISIBLE);
            	newIngr7.requestFocus();
            	add7.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr7.getText().toString());
            	newIngr8.setVisibility(View.VISIBLE);
            	newIngr8.requestFocus();
            	add8.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr8.getText().toString());
            	newIngr9.setVisibility(View.VISIBLE);
            	newIngr9.requestFocus();
            	add9.setVisibility(View.VISIBLE);
            	cont++;
            }
        });

        add9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr9.getText().toString());
            	newIngr10.setVisibility(View.VISIBLE);
            	newIngr10.requestFocus();
            	add10.setVisibility(View.VISIBLE);
            	cont++;
            }
        });
        
        add10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	listIngr.add(newIngr10.getText().toString());
            	cont++;
            }
        });              
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int duration = Toast.LENGTH_SHORT;
    	Context context = getApplicationContext();
    	String name = new String();
    	int cont1 = 1;
		
		if(listIngr.isEmpty()) {
	    	Toast toast = Toast.makeText(context, "insert an ingridient", duration);
	    	toast.show();	
		} else {
			Intent i = new Intent(SearchActivity.this, SearchResult.class);
			Iterator <String> iterator = listIngr.iterator();
			i.putExtra("number", Integer.toString(cont));
			do {
				name = "ingr" + cont1;
				cont1++;
				i.putExtra(name, iterator.next());
			} while (iterator.hasNext());

			startActivity(i);
        	        	
	    	//Toast toast = Toast.makeText(context, "search", duration);
	    	//toast.show();			
		}
		
    	return true;
	}

}
