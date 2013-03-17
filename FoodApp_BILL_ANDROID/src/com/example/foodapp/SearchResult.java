package com.example.foodapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SearchResult extends ListActivity {
	
	ArrayList<String> ingredients, searchList;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //final String TAG = "MyActivity";
        //int number = 0;
        //String s = new String();
        //String sComp = new String();
        ingredients = getIngredients();
        ArrayList<String> finalList = new ArrayList<String>();
        
        Intent intent = getIntent();
        String tableName = new String();
        tableName = intent.getExtras().getString("tableName");
        
        
        //search ---------------------------------
        SearchInDynamo search = new SearchInDynamo();
        search.execute(tableName);
        
        try {
			searchList = search.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ParseSearch parse = new ParseSearch();
        parse.execute(searchList, ingredients);
        
        try {
			finalList = parse.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        setListAdapter(new ArrayAdapter<String>(this, 
                R.layout.bucket_list, finalList));

        ListView lv = getListView();
        if (finalList.isEmpty()) {
        	/*
			int duration = Toast.LENGTH_SHORT;
			Context context = getApplicationContext();
			
        	
    		Toast toast = Toast.makeText(context, "There is no recipe with your ingredients", duration);
    		toast.show();
    		
    		this.finishActivity(0);
    		*/
			AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
			helpBuilder.setTitle("Time to shop...");
			helpBuilder.setMessage("There is no food with your ingredients");
			 
			helpBuilder.setNeutralButton("okay", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finishActivity(0);
				}
			});
					 
			 // Remember, create doesn't show the dialog
			 AlertDialog helpDialog = helpBuilder.create();
			 helpDialog.show();

        } else {      
    	lv.setOnItemClickListener(new OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parentView, View view, int position, long id) {
    			/*
    			int duration = Toast.LENGTH_SHORT;
    			Context context = getApplicationContext();
    			*/
    			String item = (String) parentView.getItemAtPosition(position);
    			/*
        		Toast toast = Toast.makeText(context, ""+item, duration);
        		toast.show();
        		*/
    			
    			Intent i = new Intent(SearchResult.this, DisplayRecipe.class);
				i.putExtra("recipe", item);
				startActivity(i);	       		
    		}       	
    	});
        }
	}
	
	ArrayList<String> getIngredients() {
        //final String TAG = "MyActivity2";
        Intent intent = getIntent();
        ArrayList<String> tempList = new ArrayList<String>();
        String name = new String();
        int number = Integer.parseInt(intent.getExtras().getString("number"));
        
        //Log.i(TAG, Integer.toString(number));
        
        for (int i=1; i<number+1; i++) {
        	name = "ingr" + i;
        	tempList.add(intent.getExtras().getString(name));
        	//Log.i(TAG, name);
        }
        
		return tempList;
		
	}
}

class ParseSearch extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {
	
	private ArrayList<String> returnList;

	@Override
	protected ArrayList<String> doInBackground(ArrayList<String>... params) {
		ArrayList<String> searchList = params[0];
		ArrayList<String> ingredients = params[1];
		
		final String TAG = "MyActivity";
		String temps = new String();
		
		String s = new String();
		int number = 0;
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		ArrayList<RecipeElement> temp = new ArrayList<RecipeElement>();
        Iterator <String> interator = searchList.iterator();
        do {
        	s = interator.next();
        	number = 0;
        	Iterator<String> intIngr = ingredients.iterator();
        	do {
        		if (s.toLowerCase().contains(intIngr.next().toLowerCase())) {
        			number++;
        		}
        	} while (intIngr.hasNext());

        	
        	if (number > 0) {
        		RecipeElement element = new RecipeElement();
        		element.putNumber(number);
        		temps = s.substring(s.lastIndexOf("name={S: ")+8, s.lastIndexOf(","));
        		temps = temps.substring(0, temps.indexOf(", }"));
        		element.putUrl(temps);
        		element.putName(temps);
        		element.putName(temps.substring(temps.lastIndexOf("wiki/")+5));
        		//element.putName(temps.substring(s.lastIndexOf("wiki/"), s.lastIndexOf("")));
        		
        		//s.substring(s.indexOf("name={S: "), s.lastIndexOf(","))
        		temp.add(element);
                //----------------------------
                Log.i(TAG, Integer.toString(number));
                Log.i(TAG, temps);
                //----------------------------
        	}
        } while (interator.hasNext());
		
        if (!temp.isEmpty()) {
        	tempList = organizeList(temp);
        }
		return tempList;
	}
	
	@Override
	protected void onPostExecute(ArrayList<String> result) {
		this.returnList = result;
	}
	
	ArrayList<String> organizeList (ArrayList<RecipeElement> list) {
		ArrayList<String> newList = new ArrayList<String>();
		
		Collections.sort(list, new NumberComparator());
		
		Iterator<RecipeElement> itList = list.iterator();
		do {
			newList.add(itList.next().getName());
		} while (itList.hasNext());
		
		return newList;	
	}
}
