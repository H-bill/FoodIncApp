package com.example.foodapp;

import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class DisplayRecipe extends Activity {
	
	WebView mWebView;
	String url;
	ShareActionProvider provider;
	String TAG;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_recipe);
        
        TAG = "MyActivity2";
        
        final String TAG = "MyActivity2";
        url = "http://www.google.com";
        
        Intent intent = getIntent();
        String recipe = intent.getExtras().getString("recipe");

        WebView wv = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        wv.loadUrl(url);
    }
	
	@SuppressLint("NewApi")
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.share_menu, menu);

	    // Get the ActionProvider
	    provider = (ShareActionProvider) menu.findItem(R.id.share_button)
	        .getActionProvider();
	    // Initialize the share intent
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("text/plain");
	    provider.setShareIntent(intent);
        return true;
    }
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.share_button:
	      Log.i(TAG, "okay1");
	      doShare();
	      break;
	    default:
	      break;
	    }
	    return true;
	  }
	
	public void doShare() {
	    // Populare the share intent with data
		Log.i(TAG, "okay2");
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("text/plain");
	    intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
	    provider.setShareIntent(intent);
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
