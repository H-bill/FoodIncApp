package com.example.foodapp;

import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DisplayRecipe extends Activity {
	
	WebView mWebView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();
        String recipe = intent.getExtras().getString("recipe");
        
        mWebView = (WebView) findViewById (R.id.webView);
        
        mWebView.loadUrl("file:///android_asset/untitled.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSaveFormData(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int duration = Toast.LENGTH_SHORT;
    	Context context = getApplicationContext();
        	        	
	    Toast toast = Toast.makeText(context, "search", duration);
	    toast.show();			
		
		
    	return true;
	}

}

class MyWebViewClient extends WebViewClient 
{ 
    @Override 
    //show the web page in webview but not in web browser
    public boolean shouldOverrideUrlLoading(WebView view, String url) { 
        view.loadUrl (url); 
        return true;
    }
}
