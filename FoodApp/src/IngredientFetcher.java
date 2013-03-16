import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.StringTokenizer;
import java.net.URL;
import java.net.URLConnection;


import org.apache.http.client.HttpClient;



import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;



/*
 * AngelTest for http://clickslide.co/angelhack6 test site
 */
public class IngredientFetcher {
	
	/*
	 * Refactored by AGF: Move the URLs into variables so they can change dynamically
	 */
	public String posturl;
	public String geturl; 
	public String loginurl;
	public String puturl;
	public String ing;
	public String theurl;
	
	/*
	 * Refactored by AGF
	 * 
	 * Moved HttpClient into a protected variable for use through out the class. 
	 * There is no need to create a new instance every time you want to use it. 
	 * Create it once and use it throughout the class. Saves time and overhead.
	 */
	public HttpClient httpclient;
	public IngredientFetcher (){
		// set up the URLs we need for testing.
		// TODO: by AGF, eventually automate these via data in a database
		this.posturl= "http://recipes.wikia.com/wiki/Special:Random";

		
		// the put url needs the ID added dynamically like so:
		// http://clickslide.co/angelhack6/basicpages/ + ID + .xml

		
		/*
		 * ADDED by AGF 8/5/12
		 * Instantiate the private static httpclient variable in the constructor
		 */
		this.httpclient = new DefaultHttpClient();
		// set the httpclient cookie policy to ignore cookies
		// TODO: this doesn't seem to affect anything.
		this.httpclient.getParams().setParameter("CookiePolicy", CookiePolicy.IGNORE_COOKIES);
	}
	
	
	/**
	 * Displays the data from a page if the correct session ID is entered.
	 * If unsuccessful an auto-generated catch block is displayed instead.
	 *//*
	public String getIt(){
		String htmlString = "";

		String info = "";
		
		System.out.println("Executing post request now");
        HttpGet httpget = new HttpGet(posturl);

        try {
            // Execute HTTP Post Request
        	HttpResponse response = this.httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            info = (""+EntityUtils.toString(entity));
            Header[] headers = response.getAllHeaders();
           // for(int i=0;i<headers.length;i++){
            	System.out.println();
            	System.out.println();
            	
           // 	System.out.println(i);
            	System.out.println();
            	System.out.println();
            	htmlString = ""+headers[20];
            	
            //	System.out.println(htmlString);

            	
          //  }
            System.out.println("\n"+info);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
	
	// Return a string
    return htmlString;
}
        
        */
        public boolean isRecipe() throws IOException {
            URL yahoo = new URL(posturl);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder a = new StringBuilder();

            while ((inputLine = in.readLine()) != null)
                a.append(inputLine);
            in.close();
        //    System.out.println(a.toString());

            ing= a.toString();
            String abba = ing.substring(a.indexOf("canonical")+10);
            String baab = abba.substring(abba.indexOf("=")+2,abba.indexOf(">")-3);
            theurl = baab;
    		if(ing.indexOf("Ingredients")==-1)
    			return false;
    		if(ing.indexOf("Directions")==-1)
    			return false;
    		if(ing.indexOf("<ul>") ==-1)
    			return false;
    		if(ing.indexOf("title")==-1)
    			return false;
    		else
    			return true;
            
        }
        public String theURL(){
        	return theurl;
        }
		

	
/*
	protected HttpContext manageCookies(String sessid){
		CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("Set-Cookie", sessid);
        cookie.setPath("/");
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        return localContext;
	}*//*
    public boolean isRecipe() throws IOException{
    	
		
		int a = ing.indexOf("Ingredients");
		if(a==-1)
			return false;
		int b = ing.indexOf("Directions");
		if(b==-1)
			return false;
		String ingr = ing.substring(a,b);
		a = ingr.indexOf("<ul>");
		b = ingr.indexOf("</ul");
		if((a) ==-1)
			return false;
		ingr = ingr.substring(a, b);
		if(ingr.indexOf("title")==-1)
			return false;
		else
		return true;
    	
    }*/
	public ArrayList<String> getIngredients() throws IOException{
	//	String rustic = "http://recipes.wikia.com/wiki/Curried_Pork_Medley";
	//	IngredientFetcher infe = new IngredientFetcher(rustic);

		ArrayList<String> ingredients = new ArrayList<String>();
		int a = ing.indexOf("Ingredients");
		int b = ing.indexOf("Directions");
		String ingr = ing.substring(a,b);
		a = ingr.indexOf("<ul>");
		b = ingr.indexOf("</ul");
		ingr = ingr.substring(a, b);

		StringTokenizer st = new StringTokenizer(ingr,">");
		while (st.hasMoreTokens()) {//while there are more chunks
			String c = st.nextToken();//gets the next chunk
		//	System.out.println(c);
			if(c.contains("title")){
				String d = st.nextToken();
				int da = d.indexOf("<");

				ingredients.add(d.substring(0,da));
			}
			/*
			int ca = c.indexOf(">");
			ca++;
			int cb = c.indexOf("</a>");*/
		//	ingredients.add(c.substring(ca,cb));
			
	 }
		for(int j = 0; j<ingredients.size();j++){
			System.out.println(ingredients.get(j));
		}
		return(ingredients);
	}
	
	

}
