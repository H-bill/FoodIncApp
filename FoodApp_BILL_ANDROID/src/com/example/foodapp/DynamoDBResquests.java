package com.example.foodapp;

import java.util.ArrayList;
import java.util.Map;

import android.os.AsyncTask;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.ScanRequest;

public class DynamoDBResquests {

}

class SearchInDynamo extends AsyncTask<String, Void, ArrayList<String>> {
	
	private ArrayList<String> returnList;

	@Override
	protected ArrayList<String> doInBackground(String... params) {
		String tableName = params[0];
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient (
				new BasicAWSCredentials(Constants.ACCESS_KEY_ID,
						Constants.SECRET_KEY));
		
		ScanRequest scanRequest = new ScanRequest()
			.withTableName(tableName);
			//.withAttributesToGet("name");
		com.amazonaws.services.dynamodb.model.ScanResult result = dynamoDBClient.scan(scanRequest);
		

		for (Map<String, AttributeValue> item : result.getItems()) {
			tempList.add(item.toString());
			//analizeItem(tempList, item);
		}

		return tempList;
	}
	
	@Override
	protected void onPostExecute(ArrayList<String> result) {
		this.returnList = result;
	}
	/*
	protected void analizeItem (ArrayList<String> tempList, Map<String, AttributeValue> attributeList) {
		final String TAG = "MyActivity2";
		for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
            String attributeName = item.getKey();
            AttributeValue value = item.getValue();
            ArrayList<String> s = (ArrayList<String>) value.getSS();
            Log.i(TAG, s.get(0));
            Log.i(TAG, attributeName);
        }
	}
	*/
}
